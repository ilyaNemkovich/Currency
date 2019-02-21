package com.vironit.android.currency.data;

import com.vironit.android.currency.dto.CryproCurrency.CryptoCurrencyResponse;
import com.vironit.android.currency.dto.CryproCurrencyDetail.CryptoDetailsResponse;
import com.vironit.android.currency.ui.fragments.currencyList.data.CryptoCurrencyResponseUI;

import java.util.List;

import io.reactivex.Single;

public interface ICurrencyRepository {
    void insertCurrencies(List<CryptoCurrencyResponseUI> cryptoCurrencyResponseUI);
    Single<List<CryptoCurrencyResponseUI>> getCryptoListFromDB();
    void deleteCurrencies();
    Single<CryptoCurrencyResponse> getCryptoList(int limit);
    Single<CryptoDetailsResponse> getCryptoListDetails(CryptoCurrencyResponse currencyResponse);
}
