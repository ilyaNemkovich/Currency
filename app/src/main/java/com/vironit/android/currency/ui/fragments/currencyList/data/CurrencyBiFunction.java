package com.vironit.android.currency.ui.fragments.currencyList.data;

import com.vironit.android.currency.dto.CryproCurrency.CryptoCurrencyResponse;
import com.vironit.android.currency.dto.CryproCurrency.Datum;
import com.vironit.android.currency.dto.CryproCurrencyDetail.CryptoDetailsResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.functions.BiFunction;

public class CurrencyBiFunction
        implements BiFunction<CryptoCurrencyResponse, CryptoDetailsResponse, ArrayList<CryptoCurrencyResponseUI>> {
    @Override
    public ArrayList<CryptoCurrencyResponseUI> apply(
            CryptoCurrencyResponse currencyResponse,
            CryptoDetailsResponse cryptoDetailsResponse) throws Exception {

        ArrayList<CryptoCurrencyResponseUI> cryptoList = new ArrayList<>();
        for (int i = 0; i < currencyResponse.getData().size(); i++) {
            CryptoCurrencyResponseUI uiItem = new CryptoCurrencyResponseUI();
            List<Datum> currencyList = currencyResponse.getData();
            uiItem.setCryptoId(currencyList.get(i).getId());
            uiItem.setName(currencyList.get(i).getName());
            uiItem.setSymbol(currencyList.get(i).getSymbol());
            uiItem.setPercentChange24h(currencyList.get(i).getQuote().getUSD().getPercentChange24h());
            uiItem.setPrice(currencyList.get(i).getQuote().getUSD().getPrice());
            uiItem.setVolume24h(currencyList.get(i).getQuote().getUSD().getVolume24h());
            uiItem.setLogoUrl(Objects.requireNonNull(cryptoDetailsResponse.getData()
                    .get(String.valueOf(currencyList.get(i).getId()))).getLogo());
            cryptoList.add(uiItem);
        }
        return cryptoList;
    }
}
