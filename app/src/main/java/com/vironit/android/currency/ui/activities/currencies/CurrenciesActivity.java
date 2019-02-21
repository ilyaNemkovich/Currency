package com.vironit.android.currency.ui.activities.currencies;

import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.vironit.android.currency.R;
import com.vironit.android.currency.ui.base.BaseActivity;
import com.vironit.android.currency.ui.fragments.currencyList.CurrencyListFragment;

import java.util.Objects;

public class CurrenciesActivity extends BaseActivity {

    Toolbar toolbar;
    TextView tvToolbarAppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currencies);

        toolbar = findViewById(R.id.toolbar_currency);
        tvToolbarAppName = findViewById(R.id.tv_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        GoogleSignInAccount account =
                GoogleSignIn.getLastSignedInAccount(this);

        if (account != null) {
            tvToolbarAppName.setText(account.getDisplayName());
        }

        Shader textShader = new LinearGradient(
                0,
                tvToolbarAppName.getLineHeight(),
                tvToolbarAppName.getLineHeight() * 2,
                0,
                new int[]{getResources().getColor(R.color.buttonColorPurple),
                        getResources().getColor(R.color.buttonColorPink)},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        tvToolbarAppName.getPaint().setShader(textShader);

        CurrencyListFragment fragment = CurrencyListFragment.getInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout_currency_list, fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list_activity, menu);
        return true;
    }
}