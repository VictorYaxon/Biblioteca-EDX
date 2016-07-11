package org.victoryaxon.firebase.booksList;

/**
 * Created by VictorYaxon on 06/07/2016.
 */
public interface BookListRepository {
    void signOff();
    String getCurrentUserEmail();
    void removeBook(String book);
    void destroyListener();
    void subscribeToBookListEvent();
    void onsubscribeToBookListEvent();
}
