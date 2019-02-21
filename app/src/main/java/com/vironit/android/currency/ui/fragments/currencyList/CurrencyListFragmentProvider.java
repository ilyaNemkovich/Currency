package com.vironit.android.currency.ui.fragments.currencyList;

import com.vironit.android.currency.di.scopes.FragmentScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface CurrencyListFragmentProvider {
    @FragmentScope
    @ContributesAndroidInjector()
    CurrencyListFragment bindWeatherFragment();
}
