package app.boilerplate.ui.restaurant.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import app.boilerplate.ViewModelProviderFactory;
import app.boilerplate.data.DataManager;
import app.boilerplate.utils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class RestaurantFragmentModule {
    @Provides
    RestaurantViewModel restaurantViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new RestaurantViewModel(dataManager, schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(RestaurantFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    RestaurantAdapter provideRestaurantAdapter() {
        return new RestaurantAdapter();
    }

    @Provides
    ViewModelProvider.Factory provideOpenSourceViewModel(RestaurantViewModel restaurantViewModel) {
        return new ViewModelProviderFactory<>(restaurantViewModel);
    }
}
