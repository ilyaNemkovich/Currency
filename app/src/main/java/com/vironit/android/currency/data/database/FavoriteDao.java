package com.vironit.android.currency.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FavoriteDao {
    @Query("SELECT * FROM favorite_currencies")
    List<FavoriteCurrencies> getAll();

    @Query("DELETE FROM favorite_currencies")
    void deleteAll();

    @Query("DELETE FROM favorite_currencies WHERE crypto_id = :cryptoId")
    void deleteFavorite(Integer cryptoId);

    @Query("SELECT id FROM favorite_currencies WHERE crypto_id = :cryptoId")
    Integer getFavoriteByCryptoId(Integer cryptoId);

    @Insert
    void insertFavorite(FavoriteCurrencies favorite);
}
