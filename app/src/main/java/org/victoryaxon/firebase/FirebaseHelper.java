package org.victoryaxon.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import org.victoryaxon.firebase.entities.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by VictorYaxon on 05/07/2016.
 */
public class FirebaseHelper {
    private DatabaseReference dataReference;
    private final static String SEPARATOR = "___";
    private final static String CHATS_PATH = "chats";
    private final static String USERS_PATH = "users";
    public final static String CONTACTS_PATH = "books";

    private static class SingletonHolder {
        private static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }

    public static FirebaseHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public FirebaseHelper(){
        dataReference = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getDataReference() {
        return dataReference;
    }

    public String getAuthUserEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = null;
        if (user != null) {
            email = user.getEmail();
        }
        return email;
    }

    public DatabaseReference getUserReference(String email){
        DatabaseReference userReference = null;
        if (email != null) {
            String emailKey = email.replace(".", "_");
            userReference = dataReference.getRoot().child(USERS_PATH).child(emailKey);
        }
        return userReference;
    }
    public DatabaseReference getDetalles(String titulo){
        DatabaseReference userReference = null;
            userReference = dataReference.getRoot().child(CONTACTS_PATH).child(titulo);
        return userReference;
    }
    public DatabaseReference getBooksReferebces(String titulo){
        DatabaseReference userReference = null;
            userReference = dataReference.getRoot().child(USERS_PATH);
        return userReference;
    }


    public DatabaseReference getUserReferences2(){
        DatabaseReference userReference = null;
            userReference = dataReference.getRoot().child(USERS_PATH);
        return userReference;
    }


    public DatabaseReference getBooksReference(){
        DatabaseReference booksReference = null;
            booksReference = dataReference.getRoot().child(CONTACTS_PATH);

        return booksReference;
    }

    public DatabaseReference getMyUserReference() {
        return getUserReference(getAuthUserEmail());
    }

    public DatabaseReference getContactsReference(){
        return getBooksReference().child(CONTACTS_PATH);
    }
    public DatabaseReference getContactsReferences(String titulo){
        return getBooksReference().child(titulo);
    }
    public DatabaseReference getMyContactsReference(){
        return getContactsReference();
        //return getContactsReference(getAuthUserEmail());
    }

    public DatabaseReference getOneContactReference(String mainEmail){
        return getUserReference(mainEmail).child(CONTACTS_PATH);
    }

    public DatabaseReference getChatsReference(String receiver){
        return dataReference.getRoot().child(CONTACTS_PATH);
    }

    public void changeUserConnectionStatus(boolean online) {
        if (getMyUserReference() != null) {
            Map<String, Object> updates = new HashMap<String, Object>();
            updates.put("online", online);
            getMyUserReference().updateChildren(updates);

            //notifyContactsOfConnectionChange(online);
        }
    }

    public void notifyContactsOfConnectionChange(final boolean online, final boolean signoff) {
        getMyContactsReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    DatabaseReference reference = getMyContactsReference();
                }
                if (signoff){
                    FirebaseAuth.getInstance().signOut();
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
            }
        });
    }

    public void notifyContactsOfConnectionChange(boolean online) {
        notifyContactsOfConnectionChange(online, false);
    }

    public void signOff(){
        notifyContactsOfConnectionChange(User.OFFLINE, true);
    }
}