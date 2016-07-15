package org.victoryaxon.firebase.booksList.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.victoryaxon.firebase.R;
import org.victoryaxon.firebase.addBook.ui.AddBookFragment;
import org.victoryaxon.firebase.booksList.BookListPresenter;
import org.victoryaxon.firebase.booksList.BookListPresenterImpl;
import org.victoryaxon.firebase.booksList.ui.adapters.BookListAdapter;
import org.victoryaxon.firebase.booksList.ui.adapters.OnItemClickListener;
import org.victoryaxon.firebase.detalles.ui.DetallesActivity;
import org.victoryaxon.firebase.entities.Book;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookActivity extends AppCompatActivity implements BookListView, OnItemClickListener {

    @Bind(R.id.recyclerViewContacts)
    RecyclerView recyclerViewContacts;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    private BookListAdapter adapter;
    private BookListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        ButterKnife.bind(this);
        setupAdapter();
        setupRecyclerView();
        presenter = new BookListPresenterImpl(this);
        presenter.onCreate();
    }

    private void setupRecyclerView() {
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewContacts.setAdapter(adapter);
    }

    private void setupAdapter() {
        adapter = new BookListAdapter(new ArrayList<Book>(),this);
        //adapter = new BookListAdapter( Arrays.asList(new Book[]{book}),this);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();

    }

    @Override
    protected void onResume() {
        presenter.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @OnClick(R.id.fab)
    public void addBook(){
        AddBookFragment frag = new AddBookFragment();
        frag.show(getSupportFragmentManager(), "");
    }

    @Override
    public void onBookAdd(Book book) {
        adapter.add(book);
    }

    @Override
    public void onBookRemoved(Book book) {
        adapter.remove(book);
    }

    @Override
    public void onItemClick(Book book) {
        Intent intent = new Intent(this, DetallesActivity.class);
        intent.putExtra(DetallesActivity.BOOK_KEY,book.getTitulo());
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(Book book) {
        presenter.removeBook(book.getTitulo());
    }

}
