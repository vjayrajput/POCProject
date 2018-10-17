package app.boilerplate;


import android.content.Context;
import android.support.multidex.MultiDex;

import javax.inject.Inject;

import app.boilerplate.di.component.AppComponent;
import app.boilerplate.di.component.DaggerAppComponent;
import app.boilerplate.utils.AppLogger;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MyApplication extends DaggerApplication {

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppLogger.init();
        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
