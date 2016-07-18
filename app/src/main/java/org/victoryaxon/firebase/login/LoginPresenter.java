package org.victoryaxon.firebase.login;


import org.victoryaxon.firebase.login.events.LoginEvent;

/**
 * Created by VictorYaxon.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void onEventMainThread(LoginEvent event);
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
}
