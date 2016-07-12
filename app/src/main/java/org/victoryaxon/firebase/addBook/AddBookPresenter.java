package org.victoryaxon.firebase.addBook;

import org.victoryaxon.firebase.addBook.evens.AddBookEvent;

/**
 * Created by VictorYaxon on 11/07/2016.
 */
public interface AddBookPresenter {
    void onShow();
    void onDestroy();

    void addBook(String titulo,String autor,String sinopsis);
    void onEventMainThread(AddBookEvent event);
}
