package app.boilerplate.ui.restaurant;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import app.boilerplate.BR;
import app.boilerplate.R;
import app.boilerplate.databinding.ActivityRestaurantBinding;
import app.boilerplate.ui.base.BaseActivity;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class RestaurantActivity extends BaseActivity<ActivityRestaurantBinding, RestaurantActivityViewModel> implements HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    RestaurantActivityViewModel mRestaurantActivityViewModel;

    @Inject
    RestaurantPageAdapter mPagerAdapter;

    private ActivityRestaurantBinding mActivityRestaurantBinding;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, RestaurantActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_restaurant;
    }

    @Override
    public RestaurantActivityViewModel getViewModel() {
        return mRestaurantActivityViewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityRestaurantBinding = getViewDataBinding();
        mActivityRestaurantBinding.setLifecycleOwner(this);
        setUp();
    }

    private void setUp() {
        setSupportActionBar(mActivityRestaurantBinding.toolbar);
        if (getSupportActionBar() != null) {
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        mPagerAdapter.setCount(4);
        mActivityRestaurantBinding.restaurantViewPager.setOffscreenPageLimit(4);
        mActivityRestaurantBinding.restaurantViewPager.setAdapter(mPagerAdapter);

        final Observer<Integer> tabSelectedObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable final Integer newTab) {
                mRestaurantActivityViewModel.setCurrentPage(newTab);
            }
        };

        mRestaurantActivityViewModel.getTabSelected().observe(this, tabSelectedObserver);
        mRestaurantActivityViewModel.setTabSelected(0);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
