package org.victoryaxon.firebase.booksList;

/**
 * Created by VictorYaxon on 06/07/2016.
 */
public class BookListInteractorImpl implements BookListInteractor{

    BookListRepository repository;

    public BookListInteractorImpl() {
        repository = new BookListRepositoryImpl();
    }

    @Override
    public void subscribe() {
        repository.subscribeToBookListEvent();
    }

    @Override
    public void onsubscribe() {
        repository.onsubscribeToBookListEvent();
    }

    @Override
    public void destroyListener() {
        repository.destroyListener();
    }

    @Override
    public void removeBooks(String book) {
        repository.removeBook(book);
    }
}
