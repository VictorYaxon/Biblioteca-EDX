package org.victoryaxon.firebase;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.FacebookSdk;
import com.firebase.client.Firebase;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
        initFacebook();
    }

    private void initFacebook() {
        FacebookSdk.sdkInitialize(this);
    }

    private void setupFirebase() {
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}
