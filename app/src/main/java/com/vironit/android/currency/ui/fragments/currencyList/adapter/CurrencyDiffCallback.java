package com.vironit.android.currency.ui.fragments.currencyList.adapter;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.vironit.android.currency.ui.fragments.currencyList.data.CryptoCurrencyResponseUI;

import java.util.List;

public class CurrencyDiffCallback extends DiffUtil.Callback{

    List<CryptoCurrencyResponseUI> newList;
    List<CryptoCurrencyResponseUI> oldList;


    public CurrencyDiffCallback(List<CryptoCurrencyResponseUI> newList, List<CryptoCurrencyResponseUI> oldList) {
        this.newList = newList;
        this.oldList = oldList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getCryptoId().equals(newList.get(newItemPosition).getCryptoId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getCryptoId().equals(newList.get(newItemPosition).getCryptoId());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
