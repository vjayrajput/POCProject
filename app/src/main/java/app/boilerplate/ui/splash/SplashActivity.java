package app.boilerplate.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import app.boilerplate.BR;
import app.boilerplate.R;
import app.boilerplate.databinding.ActivitySplashBinding;
import app.boilerplate.ui.base.BaseActivity;
import app.boilerplate.ui.restaurant.RestaurantActivity;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements SplashNavigator {

    @Inject
    SplashViewModel mSplashViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, SplashActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel getViewModel() {
        return mSplashViewModel;
    }

    @Override
    public void openRestaurantActivity() {
        Intent intent = RestaurantActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSplashViewModel.setNavigator(this);
        mSplashViewModel.startTimer();
    }
}
