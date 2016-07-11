package org.victoryaxon.firebase.booksList.ui.adapters;

import org.victoryaxon.firebase.entities.Book;

/**
 * Created by VictorYaxon on 06/07/2016.
 */
public interface OnItemClickListener {
    void onItemClick(Book book);
    void onItemLongClick(Book book);
}
