package com.vironit.android.currency.ui.fragments.currencyList;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vironit.android.currency.data.Repository;
import com.vironit.android.currency.dto.CryproCurrency.CryptoCurrencyResponse;
import com.vironit.android.currency.dto.CryproCurrencyDetail.CryptoDetailsResponse;
import com.vironit.android.currency.ui.fragments.currencyList.data.CryptoCurrencyResponseUI;
import com.vironit.android.currency.ui.fragments.currencyList.data.CurrencyBiFunction;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kotlin.Unit;

@InjectViewState
public class CurrencyListFragmentPresenter extends MvpPresenter<ICurrencyListView> {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    Repository repository;

    @Inject
    CurrencyListFragmentPresenter() {
    }

    boolean isActive(Integer cryptoId) {
        return repository.isActive(cryptoId);
    }

    Unit changeFavoriteState(Integer cryptoId) {
        Disposable disposable = Completable.fromAction(() -> repository.changeFavoriteState(cryptoId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                }, Throwable::printStackTrace);
        compositeDisposable.add(disposable);
        return Unit.INSTANCE;
    }

    void getCryptoList() {
        getViewState().changeProgressBarState();
        Single<CryptoCurrencyResponse> ccr = repository.getCryptoList(100);
        Single<CryptoDetailsResponse> cdr = ccr.flatMap(cryptoCurrencyResponse ->
                repository.getCryptoListDetails(cryptoCurrencyResponse));
        Disposable disposable = Single.zip(ccr, cdr, new CurrencyBiFunction())
                .flatMap(list -> {
                    repository.deleteCurrencies();
                    repository.insertCurrencies(list);
                    return repository.getCryptoListFromDB();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((cryptoCurrencyResponseUIS) -> {
                    getViewState().changeProgressBarState();
                    getViewState().showCurrencyList((ArrayList<CryptoCurrencyResponseUI>) cryptoCurrencyResponseUIS);
                }, throwable -> {
                    getViewState().changeProgressBarState();
                    getViewState().showError();
                });
        compositeDisposable.add(disposable);
    }

}