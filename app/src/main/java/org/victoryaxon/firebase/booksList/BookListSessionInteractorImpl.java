package org.victoryaxon.firebase.booksList;

/**
 * Created by VictorYaxon on 06/07/2016.
 */
public class BookListSessionInteractorImpl implements BookListSessionInteractor {
    BookListRepository repository;

    public BookListSessionInteractorImpl() {
        repository = new BookListRepositoryImpl();
    }

    @Override
    public void signOff() {
        repository.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return repository.getCurrentUserEmail();
    }
}
