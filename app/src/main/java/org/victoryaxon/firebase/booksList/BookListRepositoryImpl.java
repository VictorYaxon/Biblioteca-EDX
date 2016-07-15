package org.victoryaxon.firebase.booksList;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import org.victoryaxon.firebase.FirebaseHelper;
import org.victoryaxon.firebase.booksList.events.BookListEvent;
import org.victoryaxon.firebase.entities.Book;
import org.victoryaxon.firebase.lib.EventBus;
import org.victoryaxon.firebase.lib.GreenRobotEventBus;

/**
 * Created by VictorYaxon on 06/07/2016.
 */
public class BookListRepositoryImpl implements BookListRepository {
    private FirebaseHelper helper;

    private ChildEventListener eventtListEventListener;

    public BookListRepositoryImpl(){
        helper = FirebaseHelper.getInstance();
    }

    @Override
    public void signOff() {
        //helper.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return helper.getAuthUserEmail();
    }

    @Override
    public void removeBook(String book) {
        String currentUserEmail = helper.getAuthUserEmail();
        //helper.getOneContactReference(currentUserEmail, book).removeValue();
        //helper.getOneContactReference(book, currentUserEmail).removeValue();
    }

    @Override
    public void destroyListener() {
       eventtListEventListener = null;
    }

    @Override
    public void subscribeToBookListEvent() {
        if (eventtListEventListener == null) {
            eventtListEventListener = new ChildEventListener() {

                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String titulo = dataSnapshot.getKey();
                    String autor = dataSnapshot.getKey();
                    String sinopsis = dataSnapshot.getKey();
                    Book book = new Book(titulo,autor,sinopsis);
                    postEvent(BookListEvent.onBookAdd, book);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    String titulo = dataSnapshot.getKey();
                    String autor = dataSnapshot.getKey();
                    String sinopsis = dataSnapshot.getKey();
                    Book book = new Book(titulo,autor,sinopsis);
                    postEvent(BookListEvent.onBookRemoved, book);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }

            };
        }
        helper.getBooksReference().addChildEventListener(eventtListEventListener);
    }

    @Override
    public void onsubscribeToBookListEvent() {
        if (eventtListEventListener != null) {
            helper.getMyContactsReference().removeEventListener(eventtListEventListener);
        }
    }
    private void postEvent(int type, Book book) {
        BookListEvent contactListEvent = new BookListEvent();
        contactListEvent.setEventType(type);
        contactListEvent.setBook(book);
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(contactListEvent);
    }
}
