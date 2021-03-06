package com.vironit.android.currency.ui.fragments.currencyList;

import com.arellomobile.mvp.MvpView;
import com.vironit.android.currency.ui.fragments.currencyList.data.CryptoCurrencyResponseUI;

import java.util.ArrayList;

public interface ICurrencyListView extends MvpView {
    void showCurrencyList(ArrayList<CryptoCurrencyResponseUI> currencyList);

    void signOut();

    void changeProgressBarState();

    void showError();
}
