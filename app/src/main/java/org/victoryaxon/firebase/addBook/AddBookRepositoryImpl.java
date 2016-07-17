package org.victoryaxon.firebase.addBook;
import com.google.firebase.database.DatabaseReference;

import org.victoryaxon.firebase.FirebaseHelper;
import org.victoryaxon.firebase.addBook.evens.AddBookEvent;
import org.victoryaxon.firebase.entities.Book;
import org.victoryaxon.firebase.lib.EventBus;
import org.victoryaxon.firebase.lib.GreenRobotEventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by VictorYaxon on 11/07/2016.
 */
public class AddBookRepositoryImpl implements AddBookRepository {
    EventBus eventBus;
    FirebaseHelper helper;


    public AddBookRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void addBook(final String titulo,String autor,String sinopsis) {
        DatabaseReference mDatabase =helper.getDataReference();

        String key = mDatabase.child("books").push().getKey();
        Book post = new Book(titulo,autor,sinopsis);
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/books/" + titulo, postValues);

        mDatabase.updateChildren(childUpdates);

        postSuccess();
    }
    private void postSuccess() {
        post(false);
    }

    private void postError(){
        post(true);
    }

    private void post(boolean error) {
        AddBookEvent event= new AddBookEvent();
        event.setError(error);
        eventBus.post(event);
    }
}