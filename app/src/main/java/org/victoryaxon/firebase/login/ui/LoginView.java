package org.victoryaxon.firebase.login.ui;

/**
 * Created by VictorYaxon.
 */
public interface LoginView {
    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();
    void onFinish();
    void error();

    void handleSignUp();
    void handleSignIn();

    void navigateToMainScreen();
    void loginError(String error);

    void newUserSuccess();
    void newUserError(String error);
}
