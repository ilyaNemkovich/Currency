package com.vironit.android.currency.di.module;

import android.content.Context;

import com.vironit.android.currency.App;
import com.vironit.android.currency.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @ApplicationScope
    @Provides
    Context provideContext(App application) {
        return application;
    }
}
