package org.victoryaxon.firebase.addBook;

import org.greenrobot.eventbus.Subscribe;
import org.victoryaxon.firebase.addBook.evens.AddBookEvent;
import org.victoryaxon.firebase.addBook.ui.AddBookFragment;
import org.victoryaxon.firebase.addBook.ui.AddBookView;
import org.victoryaxon.firebase.lib.EventBus;
import org.victoryaxon.firebase.lib.GreenRobotEventBus;

/**
 * Created by VictorYaxon on 11/07/2016.
 */
public class AddBookPresenterImpl implements AddBookPresenter {
    private EventBus eventBus;
    private AddBookView view;
    private AddBookInteractor interactor;

    public AddBookPresenterImpl(AddBookView view) {
        this.eventBus = GreenRobotEventBus.getInstance();
        this.view = view;
        this.interactor = new  AddBookInteractorImpl();
    }

    @Override
    public void onShow() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }
    @Override
    public void addBook(String titulo) {
        if (view != null){
            view.hideInput();
            view.showProgress();
            System.out.println("Vamos por aqui y ya paso");
        }
        interactor.execute(titulo);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AddBookEvent event) {
        if (view != null){
            view.hideProgress();
            view.showInput();
            if(event.isError()){
                view.bookNotAdded();
            }else{
                view.bookAdded();
            }
        }
    }
}
