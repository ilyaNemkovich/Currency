package com.vironit.android.currency.ui.activities.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.vironit.android.currency.R;
import com.vironit.android.currency.ui.activities.currencies.CurrenciesActivity;
import com.vironit.android.currency.ui.activities.login.LoginActivity;
import com.vironit.android.currency.ui.base.BaseActivity;

public class SplashActivity extends BaseActivity {
    ImageView mImageViewLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Animation scale = AnimationUtils.loadAnimation(this, R.anim.logo_anim);
        mImageViewLogo = findViewById(R.id.iv_logo);
        mImageViewLogo.startAnimation(scale);

        GoogleSignInAccount account =
                GoogleSignIn.getLastSignedInAccount(this);

        scale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                updateUI(account);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void updateUI(Object o) {
        if (o == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in_activities, R.anim.fade_out_activities);
            finish();
        } else {
            Intent intent = new Intent(this, CurrenciesActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in_activities, R.anim.fade_out_activities);
            finish();
        }
    }
}
