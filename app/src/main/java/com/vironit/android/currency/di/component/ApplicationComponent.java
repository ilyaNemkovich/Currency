package com.vironit.android.currency.di.component;

import com.vironit.android.currency.App;
import com.vironit.android.currency.di.module.ActivitiesModule;
import com.vironit.android.currency.di.module.ApplicationModule;
import com.vironit.android.currency.di.module.DatabaseModule;
import com.vironit.android.currency.di.module.NetworkModule;
import com.vironit.android.currency.di.scopes.ApplicationScope;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@ApplicationScope
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
        DatabaseModule.class,
        AndroidSupportInjectionModule.class,
        ActivitiesModule.class
})
public interface ApplicationComponent extends AndroidInjector<App> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {
    }
}
