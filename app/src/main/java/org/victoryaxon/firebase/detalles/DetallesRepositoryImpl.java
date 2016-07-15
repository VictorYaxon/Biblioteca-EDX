package org.victoryaxon.firebase.detalles;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.victoryaxon.firebase.FirebaseHelper;
import org.victoryaxon.firebase.booksList.events.BookListEvent;
import org.victoryaxon.firebase.detalles.events.DetallesEvent;
import org.victoryaxon.firebase.entities.BookDetalle;
import org.victoryaxon.firebase.lib.EventBus;
import org.victoryaxon.firebase.lib.GreenRobotEventBus;

/**
 * Created by VictorYaxon on 13/07/2016.
 */
public class DetallesRepositoryImpl implements DetallesRepository {
    private String recipient;
    private EventBus eventBus;
    private FirebaseHelper helper;
    private ChildEventListener chatEventListener;

    public DetallesRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public void subscribe() {
        final String titulo = recipient;
        System.out.println(helper.getContactsReferences(recipient).child("Titulo")+"TTITU");
        helper.getContactsReferences(recipient).addListenerForSingleValueEvent(
            new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    BookDetalle user = dataSnapshot.getValue(BookDetalle.class);
                    System.out.println("esto es el datasnapshot "+dataSnapshot.toString());
                    System.out.println("esto es tu enlace "+helper.getContactsReferences(recipient));
                    System.out.println("esto es tu titulo "+"------"+user.getTitulo()+"------"+user.getAutor()+"------"+user.getSinopsis());
                    post(user);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

    }

    @Override
    public void unsubscribe() {

    }

    public void post(BookDetalle user){
        DetallesEvent event = new DetallesEvent();
        event.setBookNew(user);
        eventBus.post(event);
    }

    @Override
    public void destroyListener() {
        chatEventListener = null;
    }
}
