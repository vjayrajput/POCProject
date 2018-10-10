package app.akeed.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import app.akeed.R;
import app.akeed.ui.detail.RestaurantDetailActivity;
import app.akeed.ui.restaurant.RestaurantActivity;

public class SplashActivity extends AppCompatActivity {
    boolean isFinish = false;
    public Runnable mSplashRunnable = new Runnable() {

        @Override
        public void run() {
            isFinish = true;
            Intent intent = new Intent(SplashActivity.this, RestaurantActivity.class);
            startActivity(intent);
            finish();
        }
    };
    Handler mSplashHandler;
    private int SPLASH_TIME_OUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSplashHandler = new Handler();
        mSplashHandler.postDelayed(mSplashRunnable, SPLASH_TIME_OUT);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!isFinish) {
            if (mSplashRunnable != null) {
                if (mSplashHandler != null) {
                    mSplashHandler.removeCallbacks(mSplashRunnable);
                }
            }
        }
    }
}
