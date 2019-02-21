package com.vironit.android.currency.ui.base;

import android.support.annotation.CallSuper;

import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<T extends IBaseView> extends MvpPresenter<T> {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected abstract void onCreateView();

    @CallSuper
    public void onDestroyView() {
        compositeDisposable.dispose();
    }
}
