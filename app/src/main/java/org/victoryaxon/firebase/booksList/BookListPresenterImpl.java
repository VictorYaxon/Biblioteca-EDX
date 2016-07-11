package org.victoryaxon.firebase.booksList;

import org.greenrobot.eventbus.Subscribe;
import org.victoryaxon.firebase.booksList.events.BookListEvent;
import org.victoryaxon.firebase.booksList.ui.BookListView;
import org.victoryaxon.firebase.entities.Book;
import org.victoryaxon.firebase.lib.EventBus;
import org.victoryaxon.firebase.lib.GreenRobotEventBus;

/**
 * Created by VictorYaxon on 06/07/2016.
 */
public class BookListPresenterImpl implements BookListPresenter {
    private EventBus eventBus;
    private BookListView view;
    private BookListInteractor listInteractor;
    private BookListSessionInteractor sessionManager;

    public BookListPresenterImpl(BookListView view) {
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        this.listInteractor = new BookListInteractorImpl();
        this.sessionManager= new BookListSessionInteractorImpl();
                
    }

    @Override
    public void onPause() {
    listInteractor.onsubscribe();
    }

    @Override
    public void onResume() {
    listInteractor.subscribe();
    }

    @Override
    public void onCreate() {
    eventBus.register(this);
    }

    @Override
    public void onDestroy() {
    eventBus.unregister(this);
        listInteractor.destroyListener();
        view = null;
    }

    @Override
    public void signOff() {
        listInteractor.onsubscribe();
        listInteractor.destroyListener();
        sessionManager.signOff();
    }

    @Override
    public String getCurrentEmail() {
       return sessionManager.getCurrentUserEmail();
    }

    @Override
    public void removeBook(String book) {
        listInteractor.removeBooks(book);
    }

    @Override
    @Subscribe
    public void onEventMainThread(BookListEvent event) {
        Book book = event.getBook();
        switch (event.getEventType()){
            case BookListEvent.onBookAdd:
                onBookAdd(book);
                break;
            case BookListEvent.onBookRemoved:
                onBookRemoved(book);
                break;
        }
    }
    private void onBookAdd(Book book){
        if (view != null){
            view.onBookAdd(book);
        }
    }
    private void onBookRemoved(Book book){
        if (view != null){
            view.onBookRemoved(book);
        }
    }


}
