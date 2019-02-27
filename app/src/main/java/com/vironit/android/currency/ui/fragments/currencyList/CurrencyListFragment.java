package com.vironit.android.currency.ui.fragments.currencyList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.vironit.android.currency.R;
import com.vironit.android.currency.ui.activities.login.LoginActivity;
import com.vironit.android.currency.ui.base.BaseFragment;
import com.vironit.android.currency.ui.fragments.currencyList.adapter.CurrencyListAdapter;
import com.vironit.android.currency.ui.fragments.currencyList.data.CryptoCurrencyResponseUI;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import nz.co.trademe.covert.Covert;

public class CurrencyListFragment extends BaseFragment implements ICurrencyListView {

    private CurrencyListAdapter mAdapter;
    private ProgressBar mProgressBar;
    Covert covert;
    private Covert.Config covertConfig = new Covert.Config(
            new Covert.Icon(
                    R.drawable.ic_star_black_24dp,
                    R.color.secondaryColor,
                    R.color.secondaryColor),
            new Covert.Icon(
                    R.drawable.ic_star_border_black_24dp,
                    R.color.secondaryColor,
                    R.color.secondaryColor
            ),
            R.color.colorPrimaryDark,
            R.color.colorPrimaryDark,
            true,
            Covert.CornerFlag.Round.INSTANCE
    );

    @Inject
    @InjectPresenter
    CurrencyListFragmentPresenter currencyListPresenter;

    @ProvidePresenter
    public CurrencyListFragmentPresenter getCurrencyListPresenter() {
        return currencyListPresenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency_list, container, false);

        RecyclerView mRecyclerView = view.findViewById(R.id.rv_currency_list);
        mProgressBar = view.findViewById(R.id.pb_currency_list);

        covert = Covert.with(covertConfig)
                .setIsActiveCallback(viewHolder ->
                        currencyListPresenter.isActive(
                                mAdapter.getCryptoIdByPosition(viewHolder.getAdapterPosition())))
                .doOnSwipe((viewHolder, swipeDirection) -> currencyListPresenter.changeFavoriteState(
                        mAdapter.getCryptoIdByPosition(viewHolder.getAdapterPosition())
                ))
                .attachTo(mRecyclerView);

        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CurrencyListAdapter(covert);
        mRecyclerView.setAdapter(mAdapter);

        currencyListPresenter.getCryptoList();

        return view;
    }

    public static CurrencyListFragment getInstance() {
        CurrencyListFragment fragment = new CurrencyListFragment();
        Bundle arg = new Bundle();
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_sign_out:
                signOut();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showCurrencyList(ArrayList<CryptoCurrencyResponseUI> currencyList) {
        mAdapter.addCryptoList(currencyList);
    }

    @Override
    public void signOut() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(
                Objects.requireNonNull(getContext()),
                gso);
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(Objects.requireNonNull(getActivity()), task -> mGoogleSignInClient.signOut());
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        Objects.requireNonNull(getActivity())
                .overridePendingTransition(R.anim.fade_in_activities, R.anim.fade_out_activities);
        getActivity().finish();
    }

    @Override
    public void changeProgressBarState() {
        if (mProgressBar.getVisibility() == View.GONE) {
            mProgressBar.setVisibility(View.VISIBLE);
            return;
        }
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
    }
}
