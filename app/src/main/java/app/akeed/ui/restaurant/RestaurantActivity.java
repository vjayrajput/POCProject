package app.akeed.ui.restaurant;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.akeed.R;
import app.akeed.databinding.ActivityRestaurantBinding;

public class RestaurantActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRestaurantBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant);
        binding.setViewModel(new RestaurantViewModel(this));
    }
}
