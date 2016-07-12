package org.victoryaxon.firebase.addBook;

import org.victoryaxon.firebase.addBook.evens.AddBookEvent;
import org.victoryaxon.firebase.addBook.ui.AddBookFragment;
import org.victoryaxon.firebase.addBook.ui.AddBookView;

/**
 * Created by VictorYaxon on 11/07/2016.
 */
public class AddBookFragmentImpl implements AddBookPresenter {
    private AddBookView view;

    public AddBookFragmentImpl(AddBookView view) {
        this.view = view;
    }

    @Override
    public void onShow() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void addBook(String titulo, String autor, String sinopsis) {

    }

    @Override
    public void onEventMainThread(AddBookEvent event) {

    }
}
