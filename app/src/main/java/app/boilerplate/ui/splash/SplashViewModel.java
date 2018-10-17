package app.boilerplate.ui.splash;

import android.os.Handler;

import app.boilerplate.data.DataManager;
import app.boilerplate.ui.base.BaseViewModel;
import app.boilerplate.utils.rx.SchedulerProvider;

public class SplashViewModel extends BaseViewModel<SplashNavigator> {

    public SplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void startTimer() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getNavigator().openRestaurantActivity();
            }
        }, 3000);
    }
}
