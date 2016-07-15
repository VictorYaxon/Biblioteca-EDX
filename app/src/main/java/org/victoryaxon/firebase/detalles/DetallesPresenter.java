package org.victoryaxon.firebase.detalles;

import org.victoryaxon.firebase.detalles.events.DetallesEvent;

/**
 * Created by VictorYaxon on 13/07/2016.
 */
public interface DetallesPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void setChatRecipient(String recipient);
    void onEventMainThread(DetallesEvent event);
}
