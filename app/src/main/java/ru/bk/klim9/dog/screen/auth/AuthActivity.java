package ru.bk.klim9.dog.screen.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;
import ru.bk.klim9.dog.R;
import ru.bk.klim9.dog.screen.general.LoadingDialog;
import ru.bk.klim9.dog.screen.general.LoadingView;
import ru.bk.klim9.dog.screen.repositories.RepositoriesActivity;

public class AuthActivity extends AppCompatActivity implements AuthView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.loginEdit)
    EditText mLoginEdit;

    @BindView(R.id.passwordEdit)
    EditText mPasswordEdit;

    @BindView(R.id.loginInputLayout)
    TextInputLayout mLoginInputLayout;

    @BindView(R.id.passwordInputLayout)
    TextInputLayout mPasswordInputLayout;

    private LoadingView mLoadingView;

    private AuthPresenter mPresenter;

    public static void start(@NonNull Activity activity) {
        Intent intent = new Intent(activity, AuthActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mLoginEdit.setText("klm10");
        mPasswordEdit.setText("Abcd1111");
        mLoadingView = LoadingDialog.view(getSupportFragmentManager());
        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        mPresenter = new AuthPresenter(lifecycleHandler, this);
        mPresenter.init();
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.logInButton)
    public void onLogInButtonClick() {
        String login = mLoginEdit.getText().toString();
        String password = mPasswordEdit.getText().toString();
        mPresenter.tryLogIn(login, password);
    }

    @Override
    public void showLoading() {
        mLoadingView.showLoading();
    }

    @Override
    public void hideLoading() {
        mLoadingView.hideLoading();
    }

    @Override
    public void showLoginError() {
        mLoginInputLayout.setError(getString(R.string.error));
        showAlert();
    }

    @Override
    public void showPasswordError() {
        mPasswordInputLayout.setError(getString(R.string.error));
        showAlert();
    }

    @Override
    public void openRepositoriesScreen() {
        RepositoriesActivity.start(this);
//        finish();
    }

    private void showAlert(){
        Snackbar.make(mPasswordInputLayout, "Ошибка соединения, которую мы можем как-то обработать", Snackbar.LENGTH_LONG).show();
    }
}
