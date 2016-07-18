package org.victoryaxon.firebase.login.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.victoryaxon.firebase.R;
import org.victoryaxon.firebase.booksList.ui.BookActivity;
import org.victoryaxon.firebase.login.LoginPresenter;
import org.victoryaxon.firebase.login.LoginPresenterImpl;
import org.victoryaxon.firebase.signup.ui.SignUpActivity;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity
        implements LoginView {
    @Bind(R.id.btnSignin)
    Button btnSignIn;
    @Bind(R.id.btnSignup)
    Button btnSignUp;
    @Bind(R.id.editTxtEmail)
    EditText inputEmail;
    @Bind(R.id.editTxtPassword)
    EditText inputPassword;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.layoutMainContainer)
    RelativeLayout container;


    private CallbackManager callbackManager;
    private LoginPresenter loginPresenter;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        loginPresenter = new LoginPresenterImpl(this);
        loginPresenter.onCreate();
        loginPresenter.checkForAuthenticatedUser();
    }


    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    @OnClick(R.id.btnSignup)
    public void handleSignUp() {
        startActivity(new Intent(this, SignUpActivity.class));
        /*loginPresenter.registerNewUser(inputEmail.getText().toString(),
                inputPassword.getText().toString());*/
    }

    @Override
    @OnClick(R.id.btnSignin)
    public void handleSignIn() {
        loginPresenter.validateLogin(inputEmail.getText().toString(),
                inputPassword.getText().toString());
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
    public void onFinish() {
        onFinalized();
    }

    @Override
    public void error() {
        onFail();
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
    public void loginError(String error) {
        inputPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signin), error);
        inputPassword.setError(msgError);
    }

    @Override
    public void navigateToMainScreen() {
        Intent intent = new Intent(this, BookActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void newUserError(String error) {
        inputPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signup), error);
        inputPassword.setError(msgError);
    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(container, R.string.login_notice_message_useradded, Snackbar.LENGTH_SHORT).show();
    }

    private void setInputs(boolean enabled) {
        btnSignIn.setEnabled(enabled);
        btnSignUp.setEnabled(enabled);
        inputEmail.setEnabled(enabled);
        inputPassword.setEnabled(enabled);
    }
    public void onFinalized() {
        final NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this);
        notificacion.setSmallIcon(R.mipmap.ic_book);
        notificacion.setTicker("Ha ingresado exitosamente");
        notificacion.setWhen(System.currentTimeMillis());
        notificacion.setContentTitle("Bienvenido al sistema");
        notificacion.setContentText("Gracias por usar nuestros servicios");
        mp = MediaPlayer.create(this,R.raw.echo);
        PendingIntent myPendingIntent;
        Intent myIntent = new Intent();
        Context myContext = getApplicationContext();
        myPendingIntent = PendingIntent.getActivity(myContext,0 , myIntent, 0);
        notificacion.setContentIntent(myPendingIntent);

        Notification n = notificacion.build();
        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nm.notify(1,notificacion.build());
        mp.start();
    }
    public void onFail() {
        final NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this);
        notificacion.setSmallIcon(R.mipmap.ic_book);
        notificacion.setTicker("Error");
        notificacion.setWhen(System.currentTimeMillis());
        notificacion.setContentTitle("Error al acceder sistema");
        notificacion.setContentText("Compruebe sus datos");
        mp = MediaPlayer.create(this,R.raw.error);
        PendingIntent myPendingIntent;
        Intent myIntent = new Intent();
        Context myContext = getApplicationContext();
        myPendingIntent = PendingIntent.getActivity(myContext,0 , myIntent, 0);
        notificacion.setContentIntent(myPendingIntent);

        Notification n = notificacion.build();
        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nm.notify(1,notificacion.build());
        mp.start();
    }
}
