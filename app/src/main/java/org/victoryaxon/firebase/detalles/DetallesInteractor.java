package org.victoryaxon.firebase.detalles;

/**
 * Created by VictorYaxon on 13/07/2016.
 */
public interface DetallesInteractor {
    void setRecipient(String recipient);

    void subscribe();
    void unsubscribe();
    void destroyListener();
}
