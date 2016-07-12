package org.victoryaxon.firebase.addBook.evens;

/**
 * Created by VictorYaxon on 11/07/2016.
 */
public class AddBookEvent {
    boolean error = false;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
