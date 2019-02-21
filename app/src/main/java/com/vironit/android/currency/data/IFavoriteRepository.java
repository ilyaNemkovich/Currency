package com.vironit.android.currency.data;

public interface IFavoriteRepository {
    void insertFavorite(Integer cryptoId);
    void deleteFavoriteByCryptoId(Integer cryptoId);
    void changeFavoriteState(Integer cryptoId);
    boolean isFavorite(Integer id);
}
