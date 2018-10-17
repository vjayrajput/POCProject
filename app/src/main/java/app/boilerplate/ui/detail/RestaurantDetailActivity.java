package app.boilerplate.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import app.boilerplate.BR;
import app.boilerplate.R;
import app.boilerplate.utils.ShopStatus;
import app.boilerplate.data.model.api.Category;
import app.boilerplate.data.model.api.Item;
import app.boilerplate.databinding.ActivityRestaurantDetailBinding;
import app.boilerplate.ui.base.BaseActivity;
import app.boilerplate.ui.detail.fragment.CategoryFragment;
import app.boilerplate.ui.restaurant.fragment.RestaurantItemViewModel;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class RestaurantDetailActivity extends BaseActivity<ActivityRestaurantDetailBinding, RestaurantDetailActivityViewModel> implements HasSupportFragmentInjector {
    private static final String TAG = RestaurantDetailActivity.class.getSimpleName();

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    RestaurantDetailActivityViewModel mRestaurantDetailActivityViewModel;

    @Inject
    RestaurantDetailPageAdapter adapter;

    RestaurantItemViewModel restaurantItemViewModel;

    private ActivityRestaurantDetailBinding mActivityRestaurantDetailBinding;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, RestaurantDetailActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_restaurant_detail;
    }

    @Override
    public RestaurantDetailActivityViewModel getViewModel() {
        return mRestaurantDetailActivityViewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle data = getIntent().getExtras();
        restaurantItemViewModel = (RestaurantItemViewModel) data.getParcelable("restaurant");
        mActivityRestaurantDetailBinding = getViewDataBinding();
        mActivityRestaurantDetailBinding.setRestaurant(restaurantItemViewModel);
        mActivityRestaurantDetailBinding.setLifecycleOwner(this);
        setUp();
    }

    private void setUp() {

        for (int i = 0; i < restaurantItemViewModel.categorys.get().size(); i++) {
            Category category = restaurantItemViewModel.categorys.get().get(i);
            adapter.addFrag(CategoryFragment.newInstance(category.getItems()), category.getName());
        }

        mActivityRestaurantDetailBinding.viewPager.setOffscreenPageLimit(restaurantItemViewModel.categorys.get().size());
        mActivityRestaurantDetailBinding.viewPager.setAdapter(adapter);

        mActivityRestaurantDetailBinding.txtStatus.setText(showStatus(restaurantItemViewModel.openStatus.get()));
        mActivityRestaurantDetailBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private String showStatus(String status) {
        if (status.equals(String.valueOf(ShopStatus.CLOSE))) {
            return getApplicationContext().getResources().getString(R.string.shop_status_close);
        } else if (status.equals(String.valueOf(ShopStatus.OPEN))) {
            return getApplicationContext().getResources().getString(R.string.shop_status_open);
        } else if (status.equals(String.valueOf(ShopStatus.PRE_OPEN))) {
            return getApplicationContext().getResources().getString(R.string.shop_status_preorder);
        }
        return "";
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(TotalPrice totalPrice) {
        int total = 0;
        for (Category category : restaurantItemViewModel.categorys.get()) {
            for (Item item : category.getItems()) {
                total = total + (item.getPrice() * item.getQuantity());
            }
        }
        mActivityRestaurantDetailBinding.txtTotalPrice.setText(getResources().getString(R.string.item_price, total));
        mActivityRestaurantDetailBinding.bottomView.setVisibility(total > 0 ? View.VISIBLE : View.GONE);
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
