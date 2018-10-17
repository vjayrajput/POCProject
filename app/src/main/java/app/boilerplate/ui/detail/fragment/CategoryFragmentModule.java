package app.boilerplate.ui.detail.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import app.boilerplate.ViewModelProviderFactory;
import app.boilerplate.data.DataManager;
import app.boilerplate.utils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class CategoryFragmentModule {


    @Provides
    CategoryViewModel restaurantViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new CategoryViewModel(dataManager, schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(CategoryFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    CategoryAdapter provideRestaurantAdapter() {
        return new CategoryAdapter();
    }

    @Provides
    ViewModelProvider.Factory provideOpenSourceViewModel(CategoryViewModel categoryViewModel) {
        return new ViewModelProviderFactory<>(categoryViewModel);
    }
}
