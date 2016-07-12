package org.victoryaxon.firebase.addBook;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import org.victoryaxon.firebase.FirebaseHelper;
import org.victoryaxon.firebase.addBook.evens.AddBookEvent;
import org.victoryaxon.firebase.entities.Book;
import org.victoryaxon.firebase.entities.User;
import org.victoryaxon.firebase.lib.EventBus;
import org.victoryaxon.firebase.lib.GreenRobotEventBus;

/**
 * Created by VictorYaxon on 11/07/2016.
 */
public class AddBookRepositoryImpl implements AddBookRepository {

    @Override
    public void addBook(final String titulo, final String autor, final String sinopsis) {
        FirebaseHelper helper = FirebaseHelper.getInstance();
        final DatabaseReference userReference = helper.getBooksReferebces(titulo,autor,sinopsis);
        userReference.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                Book user = dataSnapshot.getValue(Book.class);
                AddBookEvent event = new AddBookEvent();
                FirebaseHelper helper = FirebaseHelper.getInstance();
                DatabaseReference reverseUserContactsReference = helper.getContactsReference();
                DatabaseReference userContactsReference = helper.getMyContactsReference();
                EventBus eventBus = GreenRobotEventBus.getInstance();
                eventBus.post(event);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
