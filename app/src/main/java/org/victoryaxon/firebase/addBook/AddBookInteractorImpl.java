package org.victoryaxon.firebase.addBook;

/**
 * Created by VictorYaxon on 11/07/2016.
 */
public class AddBookInteractorImpl implements AddBookInteractor {

    AddBookRepository addBookRepository;

    public AddBookInteractorImpl() {
        this.addBookRepository = new AddBookRepositoryImpl();
    }

    @Override
    public void execute(String titulo) {
        addBookRepository.addBook(titulo);
    }
}
