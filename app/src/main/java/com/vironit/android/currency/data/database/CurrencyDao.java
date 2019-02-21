package com.vironit.android.currency.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.vironit.android.currency.ui.fragments.currencyList.data.CryptoCurrencyResponseUI;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface CurrencyDao {
    @Query("SELECT * FROM currencies_data")
    Single<List<CryptoCurrencyResponseUI>> getAll();

    @Query("DELETE FROM currencies_data")
    void deleteAll();

    @Insert
    void insertFavorite(List<CryptoCurrencyResponseUI> list);
}
