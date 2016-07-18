package org.victoryaxon.firebase.signup.ui;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import org.victoryaxon.firebase.R;
import org.victoryaxon.firebase.booksList.ui.BookActivity;
import org.victoryaxon.firebase.login.LoginPresenter;
import org.victoryaxon.firebase.login.LoginPresenterImpl;
import org.victoryaxon.firebase.login.ui.LoginView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity implements LoginView{

    @Bind(R.id.btnSignup)           Button btnSignUp;
    @Bind(R.id.editTxtEmail)
    EditText inputEmail;
    @Bind(R.id.editTxtPassword)     EditText inputPassword;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.layoutMainContainer)
    RelativeLayout container;

    private LoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        setTitle(R.string.sign_message_signup);

        loginPresenter = new LoginPresenterImpl(this);
        loginPresenter.onCreate();
    }


    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    @OnClick(R.id.btnSignup)
    public void handleSignUp() {
        if (inputEmail.length()==0 || inputPassword.getText().length()==0) {
            inputPassword.setText("");
            String msgError = "Llene los campos";
            inputPassword.setError(msgError);
        }else{
            loginPresenter.registerNewUser(inputEmail.getText().toString(),
                    inputPassword.getText().toString());
        }
    }

    @Override
    public void handleSignIn() {
        throw new UnsupportedOperationException("Operation is not valid in sign");
    }

    @Override
    public void navigateToMainScreen() {
        Intent intent = new Intent(this, BookActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void loginError(String error) {
        inputPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signin), error);
        inputPassword.setError(msgError);
    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(container, R.string.login_notice_message_useradded, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void newUserError(String error) {
        inputPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signup), error);
        inputPassword.setError(msgError);
    }

    private void setInputs(boolean enabled){
        btnSignUp.setEnabled(true);
        inputEmail.setEnabled(true);
        inputPassword.setEnabled(true);
    }
}
