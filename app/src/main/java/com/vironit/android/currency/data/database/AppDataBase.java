package com.vironit.android.currency.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.vironit.android.currency.ui.fragments.currencyList.data.CryptoCurrencyResponseUI;

@Database(entities = {FavoriteCurrencies.class,
        CryptoCurrencyResponseUI.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract FavoriteDao favoriteDao();
    public abstract CurrencyDao currencyDao();
}
