package org.victoryaxon.firebase.booksList.events;

import org.victoryaxon.firebase.entities.Book;

/**
 * Created by VictorYaxon on 06/07/2016.
 */
public class BookListEvent {
    public final static int onBookAdd = 0;
    public final static int onBookRemoved = 1;

    private Book book;
    private int eventType;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
