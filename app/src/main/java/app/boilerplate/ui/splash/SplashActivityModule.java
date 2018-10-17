package app.boilerplate.ui.splash;

import app.boilerplate.data.DataManager;
import app.boilerplate.utils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class SplashActivityModule {

    @Provides
    SplashViewModel provideSplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new SplashViewModel(dataManager, schedulerProvider);
    }
}
