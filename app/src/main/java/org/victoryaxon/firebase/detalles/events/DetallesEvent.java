package org.victoryaxon.firebase.detalles.events;

import org.victoryaxon.firebase.entities.BookDetalle;

/**
 * Created by VictorYaxon on 13/07/2016.
 */
public class DetallesEvent {
    BookDetalle bookNew;

    public BookDetalle getBookNew() {
        return bookNew;
    }

    public void setBookNew(BookDetalle bookNew) {
        this.bookNew = bookNew;
    }
}
