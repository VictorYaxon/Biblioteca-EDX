package org.victoryaxon.firebase.login;

/**
 * Created by VictorYaxon.
 */
public interface LoginInteractor {
    void checkAlreadyAuthenticated();
    void doSignUp(String email, String password);
    void doSignIn(String email, String password);
}
