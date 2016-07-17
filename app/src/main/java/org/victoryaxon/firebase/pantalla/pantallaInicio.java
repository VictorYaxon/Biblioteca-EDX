package org.victoryaxon.firebase.pantalla;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import org.victoryaxon.firebase.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class pantallaInicio extends Activity {

    @Bind(R.id.progressBar2)
    ProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pantalla_inicio);

        ButterKnife.bind(this);


        Thread timer = new Thread() {
            public void run() {
                try {
                    progressBar2.setVisibility(View.VISIBLE);
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent actividaPrincipal = new Intent("android.intent.action.PRINCIPAL");
                    startActivity(actividaPrincipal);
                }
            }
        };
        timer.start();
    }

}