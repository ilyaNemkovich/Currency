package com.vironit.android.currency.ui.fragments.login;

import com.vironit.android.currency.di.scopes.FragmentScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface LoginFragmentProvider {
    @FragmentScope
    @ContributesAndroidInjector()
    LoginFragment bindWeatherFragment();
}
