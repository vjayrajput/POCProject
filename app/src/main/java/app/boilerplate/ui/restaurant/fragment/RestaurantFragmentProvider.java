package app.boilerplate.ui.restaurant.fragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RestaurantFragmentProvider {
    @ContributesAndroidInjector(modules = RestaurantFragmentModule.class)
    abstract RestaurantFragment provideRestaurantFragmentFactory();
}
