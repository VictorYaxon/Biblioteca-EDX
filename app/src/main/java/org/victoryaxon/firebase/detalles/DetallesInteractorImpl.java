package org.victoryaxon.firebase.detalles;

/**
 * Created by VictorYaxon on 13/07/2016.
 */
public class DetallesInteractorImpl implements DetallesInteractor {
    DetallesRepository repository;

    public DetallesInteractorImpl() {
        repository=new DetallesRepositoryImpl();
    }

    @Override
    public void setRecipient(String recipient) {
        repository.setRecipient(recipient);
    }

    @Override
    public void subscribe() {
        repository.subscribe();
    }

    @Override
    public void unsubscribe() {
        repository.unsubscribe();
    }

    @Override
    public void destroyListener() {
        repository.destroyListener();
    }
}
