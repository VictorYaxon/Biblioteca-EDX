package org.victoryaxon.firebase.booksList.ui;

import org.victoryaxon.firebase.entities.Book;

/**
 * Created by VictorYaxon on 06/07/2016.
 */
public interface BookListView {
    void onBookAdd(Book book);
    void onBookRemoved(Book book);
}
