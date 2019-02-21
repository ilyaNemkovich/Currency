package com.vironit.android.currency.interactor;

import com.vironit.android.currency.dto.CryproCurrency.CryptoCurrencyResponse;
import com.vironit.android.currency.dto.CryproCurrencyDetail.CryptoDetailsResponse;
import com.vironit.android.currency.ui.fragments.currencyList.data.CryptoCurrencyResponseUI;

import java.util.List;

import io.reactivex.Single;

public interface ICurrencyListInteractor {
    void insertFavorite(Integer cryptoId);
    void deleteFavoriteByCryptoId(Integer cryptoId);

    void changeFavoriteState(Integer cryptoId);
    boolean isFavorite(Integer id);
    void updateCurrencies(List<CryptoCurrencyResponseUI> cryptoCurrencyResponseUI);
    Single<List<CryptoCurrencyResponseUI>> getCryptoListFromDB();
    Single<CryptoCurrencyResponse> getCryptoList(int limit);
    Single<CryptoDetailsResponse> getCryptoListDetails(CryptoCurrencyResponse currencyResponse);
}
