package app.boilerplate.di.component;

import android.app.Application;

import javax.inject.Singleton;

import app.boilerplate.MyApplication;
import app.boilerplate.di.builder.ActivityBuilder;
import app.boilerplate.di.module.AppModule;
import app.boilerplate.di.module.NetworkModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class,
        NetworkModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(MyApplication app);

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}