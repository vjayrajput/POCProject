package app.akeed.ui.detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import app.akeed.R;
import app.akeed.databinding.ActivityRestaurantDetailBinding;
import app.akeed.model.Category;
import app.akeed.model.Item;
import app.akeed.model.Restaurant;

public class RestaurantDetailActivity extends AppCompatActivity {
    private static final String TAG = RestaurantDetailActivity.class.getSimpleName();
    Restaurant restaurant = null;
    ActivityRestaurantDetailBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle data = getIntent().getExtras();
        restaurant = (Restaurant) data.getParcelable("restaurant");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant_detail);
        binding.setViewModel(new RestaurantDetailViewModel(this, restaurant));
        binding.setRestaurant(restaurant);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(TotalPrice totalPrice) {
        int total = 0;
        for (Category category : restaurant.getCategorys()) {
            for (Item item : category.getItems()) {
                total = total + (item.getPrice() * item.getQuantity());
            }
        }
        binding.txtTotalPrice.setText(getResources().getString(R.string.item_price, total));
        binding.bottomView.setVisibility(total > 0 ? View.VISIBLE : View.GONE);
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
