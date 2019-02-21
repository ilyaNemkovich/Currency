package com.vironit.android.currency.ui.activities.login;

import android.content.Context;

import com.vironit.android.currency.di.qualifiers.ActivityContext;
import com.vironit.android.currency.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginActivityModule {
    LoginActivity mainActivity;

    @Provides
    @ActivityScope
    @ActivityContext
    Context provideContext(LoginActivity loginActivity) {
        return loginActivity;
    }
}
