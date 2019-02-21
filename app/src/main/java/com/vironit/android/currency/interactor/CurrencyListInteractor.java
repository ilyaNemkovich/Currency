package com.vironit.android.currency.interactor;

import com.vironit.android.currency.data.ICurrencyRepository;
import com.vironit.android.currency.data.IFavoriteRepository;
import com.vironit.android.currency.dto.CryproCurrency.CryptoCurrencyResponse;
import com.vironit.android.currency.dto.CryproCurrencyDetail.CryptoDetailsResponse;
import com.vironit.android.currency.ui.fragments.currencyList.data.CryptoCurrencyResponseUI;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class CurrencyListInteractor implements ICurrencyListInteractor {

    private final ICurrencyRepository iCurrencyRepository;
    private final IFavoriteRepository iFavoriteRepository;

    @Inject
    public CurrencyListInteractor(ICurrencyRepository iCurrencyRepository, IFavoriteRepository iFavoriteRepository) {
        this.iCurrencyRepository = iCurrencyRepository;
        this.iFavoriteRepository = iFavoriteRepository;
    }


    @Override
    public void insertFavorite(Integer cryptoId) {
        iFavoriteRepository.insertFavorite(cryptoId);
    }

    @Override
    public void deleteFavoriteByCryptoId(Integer cryptoId) {
        iFavoriteRepository.deleteFavoriteByCryptoId(cryptoId);
    }

    @Override
    public void changeFavoriteState(Integer cryptoId) {
        iFavoriteRepository.changeFavoriteState(cryptoId);
    }

    @Override
    public boolean isFavorite(Integer id) {
        return iFavoriteRepository.isFavorite(id);
    }

    @Override
    public void updateCurrencies(List<CryptoCurrencyResponseUI> cryptoCurrencyResponseUI) {
        iCurrencyRepository.deleteCurrencies();
        iCurrencyRepository.insertCurrencies(cryptoCurrencyResponseUI);
    }

    @Override
    public Single<List<CryptoCurrencyResponseUI>> getCryptoListFromDB() {
        return iCurrencyRepository.getCryptoListFromDB();
    }

    @Override
    public Single<CryptoCurrencyResponse> getCryptoList(int limit) {
        return iCurrencyRepository.getCryptoList(limit);
    }

    @Override
    public Single<CryptoDetailsResponse> getCryptoListDetails(CryptoCurrencyResponse currencyResponse) {
        return iCurrencyRepository.getCryptoListDetails(currencyResponse);
    }
}
