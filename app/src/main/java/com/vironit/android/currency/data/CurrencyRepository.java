package com.vironit.android.currency.data;

import com.vironit.android.currency.data.Network.CoinMarketCapAPI;
import com.vironit.android.currency.data.database.CurrencyDao;
import com.vironit.android.currency.dto.CryproCurrency.CryptoCurrencyResponse;
import com.vironit.android.currency.dto.CryproCurrency.Datum;
import com.vironit.android.currency.dto.CryproCurrencyDetail.CryptoDetailsResponse;
import com.vironit.android.currency.ui.fragments.currencyList.data.CryptoCurrencyResponseUI;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class CurrencyRepository implements ICurrencyRepository {
    private final CoinMarketCapAPI capAPI;
    private final CurrencyDao currencyDao;

    @Inject
    CurrencyRepository(CoinMarketCapAPI capAPI, CurrencyDao currencyDao) {
        this.capAPI = capAPI;
        this.currencyDao = currencyDao;
    }

    @Override
    public void insertCurrencies(List<CryptoCurrencyResponseUI> cryptoCurrencyResponseUI){
        currencyDao.insertFavorite(cryptoCurrencyResponseUI);
    }

    @Override
    public Single<List<CryptoCurrencyResponseUI>> getCryptoListFromDB(){
        return currencyDao.getAll();
    }

    @Override
    public void deleteCurrencies() {
        currencyDao.deleteAll();
    }

    @Override
    public Single<CryptoCurrencyResponse> getCryptoList(int limit) {
        return capAPI.getCryptoCurrency(limit);
    }

    @Override
    public Single<CryptoDetailsResponse> getCryptoListDetails(CryptoCurrencyResponse currencyResponse) {
        StringBuilder sb = new StringBuilder();
        for (Datum datum : currencyResponse.getData()) {
            sb.append(datum.getId()).append(",");
        }
        String s = sb.substring(0, sb.length() - 1);
        return capAPI.getCryptoCurrencyDetails(s);
    }
}