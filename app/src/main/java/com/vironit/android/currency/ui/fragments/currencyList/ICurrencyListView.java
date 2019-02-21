package com.vironit.android.currency.ui.fragments.currencyList;

import com.vironit.android.currency.ui.base.IBaseView;
import com.vironit.android.currency.ui.fragments.currencyList.data.CryptoCurrencyResponseUI;

import java.util.ArrayList;

public interface ICurrencyListView extends IBaseView {
    void showCurrencyList(ArrayList<CryptoCurrencyResponseUI> currencyList);

    void signOut();

    void changeProgressBarState();

    void showError();
}
