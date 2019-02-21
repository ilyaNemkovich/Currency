package com.vironit.android.currency.ui.activities.currencies;

import android.content.Context;

import com.vironit.android.currency.di.qualifiers.ActivityContext;
import com.vironit.android.currency.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class CurrenciesActivityModule {
    CurrenciesActivity currenciesActivity;

    @Provides
    @ActivityScope
    @ActivityContext
    Context provideContext(CurrenciesActivity currenciesActivity) {
        return currenciesActivity;
    }
}
