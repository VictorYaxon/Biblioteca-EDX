package org.victoryaxon.firebase.addBook.ui;

/**
 * Created by VictorYaxon on 11/07/2016.
 */
public interface AddBookView {
    void showInput();
    void hideInput();
    void showProgress();
    void hideProgress();

    void bookAdded();
    void bookNotAdded();
}
