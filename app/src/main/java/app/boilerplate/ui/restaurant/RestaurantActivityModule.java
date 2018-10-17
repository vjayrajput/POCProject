package app.boilerplate.ui.restaurant;

import app.boilerplate.data.DataManager;
import app.boilerplate.utils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class RestaurantActivityModule {

    @Provides
    RestaurantActivityViewModel provideRestaurantActivityViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new RestaurantActivityViewModel(dataManager, schedulerProvider);
    }

    @Provides
    RestaurantPageAdapter provideRestaurantPageAdapter(RestaurantActivity activity) {
        return new RestaurantPageAdapter(activity.getSupportFragmentManager());
    }
}
