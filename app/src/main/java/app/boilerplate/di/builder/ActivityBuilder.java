package app.boilerplate.di.builder;

import app.boilerplate.ui.detail.RestaurantDetailActivity;
import app.boilerplate.ui.detail.RestaurantDetailActivityModule;
import app.boilerplate.ui.detail.fragment.CategoryFragmentProvider;
import app.boilerplate.ui.restaurant.RestaurantActivity;
import app.boilerplate.ui.restaurant.RestaurantActivityModule;
import app.boilerplate.ui.restaurant.fragment.RestaurantFragmentProvider;
import app.boilerplate.ui.splash.SplashActivity;
import app.boilerplate.ui.splash.SplashActivityModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = {
            RestaurantActivityModule.class,
            RestaurantFragmentProvider.class})
    abstract RestaurantActivity bindRestaurantActivity();

    @ContributesAndroidInjector(modules = {
            RestaurantDetailActivityModule.class,
            CategoryFragmentProvider.class})
    abstract RestaurantDetailActivity bindRestaurantDetailActivity();
}