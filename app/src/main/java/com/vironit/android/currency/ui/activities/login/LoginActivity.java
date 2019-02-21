package com.vironit.android.currency.ui.activities.login;

import android.os.Bundle;

import com.vironit.android.currency.R;
import com.vironit.android.currency.ui.base.BaseActivity;
import com.vironit.android.currency.ui.fragments.login.LoginFragment;

public class LoginActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment fragment = LoginFragment.getInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout_login, fragment)
                .commit();
    }
}

