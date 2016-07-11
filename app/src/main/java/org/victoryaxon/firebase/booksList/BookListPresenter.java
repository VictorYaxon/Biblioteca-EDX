package org.victoryaxon.firebase.booksList;

import org.victoryaxon.firebase.booksList.events.BookListEvent;

/**
 * Created by VictorYaxon on 06/07/2016.
 */
public interface BookListPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void signOff();
    String getCurrentEmail();
    void removeBook(String book);
    void onEventMainThread(BookListEvent event);
}
