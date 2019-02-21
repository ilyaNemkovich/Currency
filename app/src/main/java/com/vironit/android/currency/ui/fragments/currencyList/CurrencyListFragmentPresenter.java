package com.vironit.android.currency.ui.fragments.currencyList;

import com.arellomobile.mvp.InjectViewState;
import com.vironit.android.currency.dto.CryproCurrency.CryptoCurrencyResponse;
import com.vironit.android.currency.dto.CryproCurrencyDetail.CryptoDetailsResponse;
import com.vironit.android.currency.interactor.ICurrencyListInteractor;
import com.vironit.android.currency.ui.base.BasePresenter;
import com.vironit.android.currency.ui.fragments.currencyList.data.CryptoCurrencyResponseUI;
import com.vironit.android.currency.ui.fragments.currencyList.data.CurrencyBiFunction;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kotlin.Unit;

@InjectViewState
public class CurrencyListFragmentPresenter extends BasePresenter<ICurrencyListView> {

    @Inject
    ICurrencyListInteractor interactor;

    @Inject
    CurrencyListFragmentPresenter() {
    }

    boolean isActive(Integer cryptoId) {
        return interactor.isFavorite(cryptoId);
    }

    Unit changeFavoriteState(Integer cryptoId) {
        Disposable disposable = Completable.fromAction(() -> interactor.changeFavoriteState(cryptoId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                }, Throwable::printStackTrace);
        compositeDisposable.add(disposable);
        return Unit.INSTANCE;
    }

    private void getCryptoList() {
        getViewState().changeProgressBarState();
        Single<CryptoCurrencyResponse> ccr = interactor.getCryptoList(100);
        Single<CryptoDetailsResponse> cdr = ccr.flatMap(cryptoCurrencyResponse ->
                interactor.getCryptoListDetails(cryptoCurrencyResponse));
        Disposable disposable = Single.zip(ccr, cdr, new CurrencyBiFunction())
                .flatMap(list -> {
                    interactor.updateCurrencies(list);
                    return interactor.getCryptoListFromDB();
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

    @Override
    protected void onCreateView() {
        getCryptoList();
    }

}