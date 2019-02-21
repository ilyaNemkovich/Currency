package com.vironit.android.currency.data;

import com.vironit.android.currency.data.database.FavoriteCurrencies;
import com.vironit.android.currency.data.database.FavoriteDao;

import javax.inject.Inject;

public class FavoriteRepository implements IFavoriteRepository {

    private final FavoriteDao favoriteDao;

    @Inject
    public FavoriteRepository(FavoriteDao favoriteDao) {
        this.favoriteDao = favoriteDao;
    }

    @Override
    public void insertFavorite(Integer cryptoId) {
        FavoriteCurrencies favoriteCurrencies = new FavoriteCurrencies();
        favoriteCurrencies.setCryptoId(cryptoId);
        favoriteDao.insertFavorite(favoriteCurrencies);
    }

    @Override
    public void deleteFavoriteByCryptoId(Integer cryptoId){
        favoriteDao.deleteFavorite(cryptoId);
    }

    @Override
    public void changeFavoriteState(Integer cryptoId) {
        if (favoriteDao.getFavoriteByCryptoId(cryptoId) == null) {
            insertFavorite(cryptoId);
        } else {
            deleteFavoriteByCryptoId(cryptoId);
        }
    }

    @Override
    public boolean isFavorite(Integer id) {
        return favoriteDao.getFavoriteByCryptoId(id) != null;
    }
}
