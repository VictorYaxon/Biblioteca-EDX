package org.victoryaxon.firebase.booksList;

/**
 * Created by VictorYaxon on 06/07/2016.
 */
public interface BookListInteractor {
    void subscribe();
    void onsubscribe();
    void destroyListener();
    void removeBooks(String book);
}
