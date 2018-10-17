package app.boilerplate.ui.detail;

import app.boilerplate.data.DataManager;
import app.boilerplate.utils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class RestaurantDetailActivityModule {
    @Provides
    RestaurantDetailActivityViewModel provideRestaurantDetailActivityViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new RestaurantDetailActivityViewModel(dataManager, schedulerProvider);
    }

    @Provides
    RestaurantDetailPageAdapter provideRestaurantPageAdapter(RestaurantDetailActivity activity) {
        return new RestaurantDetailPageAdapter(activity.getSupportFragmentManager());
    }
}
