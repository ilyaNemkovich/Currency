package com.vironit.android.currency.data;

import com.vironit.android.currency.data.Network.CoinMarketCapAPI;
import com.vironit.android.currency.data.database.CurrencyDao;
import com.vironit.android.currency.data.database.FavoriteDao;
import com.vironit.android.currency.data.database.FavoriteCurrencies;
import com.vironit.android.currency.dto.CryproCurrency.CryptoCurrencyResponse;
import com.vironit.android.currency.dto.CryproCurrency.Datum;
import com.vironit.android.currency.dto.CryproCurrencyDetail.CryptoDetailsResponse;
import com.vironit.android.currency.ui.fragments.currencyList.data.CryptoCurrencyResponseUI;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class Repository {

    @Inject
    CoinMarketCapAPI capAPI;

    @Inject
    FavoriteDao favoriteDao;

    @Inject
    CurrencyDao currencyDao;

    @Inject
    Repository() {
    }

    /*Database methods*/
        /*FavoriteDao*/
    public void insertFavorite(Integer cryptoId) {
        FavoriteCurrencies favoriteCurrencies = new FavoriteCurrencies();
        favoriteCurrencies.setCryptoId(cryptoId);
        favoriteDao.insertFavorite(favoriteCurrencies);
    }

    public void deleteFavoriteByCryptoId(Integer cryptoId){
        favoriteDao.deleteFavorite(cryptoId);
    }

    public void changeFavoriteState(Integer cryptoId) {
        if (favoriteDao.getFavoriteByCryptoId(cryptoId) == null) {
            insertFavorite(cryptoId);
        } else {
            deleteFavoriteByCryptoId(cryptoId);
        }
    }

    public boolean isActive(Integer id) {
        return favoriteDao.getFavoriteByCryptoId(id) != null;
    }

    /*Database methods*/
        /*CurrencyDao*/
    public void insertCurrencies(List<CryptoCurrencyResponseUI> cryptoCurrencyResponseUI){
        currencyDao.insertFavorite(cryptoCurrencyResponseUI);
    }

    public Single<List<CryptoCurrencyResponseUI>> getCryptoListFromDB(){
        return currencyDao.getAll();
    }

    public void deleteCurrencies() {
        currencyDao.deleteAll();
    }

    /*Network methods*/
    public Single<CryptoCurrencyResponse> getCryptoList(int limit) {
        return capAPI.getCryptoCurrency(limit);
    }

    public Single<CryptoDetailsResponse> getCryptoListDetails(CryptoCurrencyResponse currencyResponse) {
        StringBuilder sb = new StringBuilder();
        for (Datum datum : currencyResponse.getData()) {
            sb.append(datum.getId()).append(",");
        }
        String s = sb.substring(0, sb.length() - 1);
        return capAPI.getCryptoCurrencyDetails(s);
    }
}