package org.victoryaxon.firebase.detalles;

import org.greenrobot.eventbus.Subscribe;
import org.victoryaxon.firebase.detalles.events.DetallesEvent;
import org.victoryaxon.firebase.detalles.ui.DetallesView;
import org.victoryaxon.firebase.lib.EventBus;
import org.victoryaxon.firebase.lib.GreenRobotEventBus;

/**
 * Created by VictorYaxon on 13/07/2016.
 */
public class DetallesPresenterImpl implements DetallesPresenter {
    private EventBus eventBus;
    private DetallesView view;
    private DetallesInteractor chatInteractor;
    private DetallesSessionInteractor sessionInteractor;

    public DetallesPresenterImpl(DetallesView view) {
        this.view= view;
        eventBus = GreenRobotEventBus.getInstance();
        this.chatInteractor = new DetallesInteractorImpl();
    }

    @Override
    public void onPause() {
        chatInteractor.unsubscribe();
    }

    @Override
    public void onResume() {
        chatInteractor.subscribe();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        chatInteractor.destroyListener();
        view = null;
    }

    @Override
    public void setChatRecipient(String recipient) {
        chatInteractor.setRecipient(recipient);
    }

    @Override
    @Subscribe
    public void onEventMainThread(DetallesEvent event) {
        if (view != null) {
            view.onBookReceived(event.getBookNew());
        }
    }
}
