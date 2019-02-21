package com.vironit.android.currency.ui.fragments.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.vironit.android.currency.R;
import com.vironit.android.currency.ui.activities.currencies.CurrenciesActivity;
import com.vironit.android.currency.ui.base.BaseFragment;

import java.util.Objects;

public class LoginFragment extends BaseFragment {

    private static int RC_SIGN_IN = 9001;

    SignInButton signInButton;
    GoogleSignInOptions gso;
    GoogleSignInClient mGoogleSignInClient;

    EditText editTextEmail;
    EditText editTextPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(Objects.requireNonNull(getContext()), gso);

        signInButton = view.findViewById(R.id.sign_in_button);
        editTextEmail = view.findViewById(R.id.til_edit_text_email);
        editTextPassword = view.findViewById(R.id.til_edit_text_password);

        editTextEmail.setOnClickListener(this::setViewFocus);
        editTextPassword.setOnClickListener(this::setViewFocus);

        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setColorScheme(SignInButton.COLOR_DARK);
        signInButton.setOnClickListener(v -> signIn());
        return view;
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            updateUI(account);
        } catch (ApiException e) {
            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    private void updateUI(Object o) {
        if (o != null) {
            Intent intent = new Intent(getContext(), CurrenciesActivity.class);
            startActivity(intent);
            Objects.requireNonNull(getActivity())
                    .overridePendingTransition(R.anim.fade_in_activities, R.anim.fade_out_activities);
            getActivity()
                    .finish();
        }
    }

    private void setViewFocus(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        InputMethodManager inputMethodManager =
                (InputMethodManager) Objects.requireNonNull(
                        getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(
                view.getApplicationWindowToken(), InputMethodManager.SHOW_IMPLICIT, 0);
        view.setOnClickListener(null);
    }

    public static LoginFragment getInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle arg = new Bundle();
        fragment.setArguments(arg);
        return fragment;
    }
}
