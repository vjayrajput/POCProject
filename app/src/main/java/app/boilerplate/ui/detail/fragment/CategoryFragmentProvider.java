package app.boilerplate.ui.detail.fragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CategoryFragmentProvider {

    @ContributesAndroidInjector(modules = CategoryFragmentModule.class)
    abstract CategoryFragment provideCategoryFragmentFactory();

}
