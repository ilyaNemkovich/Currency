package com.vironit.android.currency.di.module;

import com.vironit.android.currency.di.scopes.ActivityScope;
import com.vironit.android.currency.ui.activities.currencies.CurrenciesActivity;
import com.vironit.android.currency.ui.activities.currencies.CurrenciesActivityModule;
import com.vironit.android.currency.ui.activities.login.LoginActivity;
import com.vironit.android.currency.ui.activities.login.LoginActivityModule;
import com.vironit.android.currency.ui.activities.splash.SplashActivity;
import com.vironit.android.currency.ui.fragments.currencyList.CurrencyListFragmentProvider;
import com.vironit.android.currency.ui.fragments.login.LoginFragmentProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ActivitiesModule {


    @ActivityScope
    @ContributesAndroidInjector
    SplashActivity contributeSplashActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {
            LoginActivityModule.class,
            LoginFragmentProvider.class
    })
    LoginActivity contributeMainActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = {
            CurrenciesActivityModule.class,
            CurrencyListFragmentProvider.class
    })
    CurrenciesActivity contributeCurrenciesActivityInjector();
}
