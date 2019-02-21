package com.vironit.android.currency.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.vironit.android.currency.data.database.AppDataBase;
import com.vironit.android.currency.data.database.CurrencyDao;
import com.vironit.android.currency.data.database.FavoriteDao;
import com.vironit.android.currency.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    @ApplicationScope
    AppDataBase getAppDataBase(Context context){
        return Room.databaseBuilder(context.getApplicationContext(),
                AppDataBase.class, "currency_database")
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @ApplicationScope
    CurrencyDao getCurrencyDao(AppDataBase appDataBase){
        return appDataBase.currencyDao();
    }

    @Provides
    @ApplicationScope
    FavoriteDao getFavoriteDao(AppDataBase appDataBase){
        return appDataBase.favoriteDao();
    }
}
