package org.victoryaxon.firebase.detalles.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.victoryaxon.firebase.R;
import org.victoryaxon.firebase.detalles.DetallesPresenter;
import org.victoryaxon.firebase.detalles.DetallesPresenterImpl;
import org.victoryaxon.firebase.detalles.ui.adapter.DetallesAdapter;
import org.victoryaxon.firebase.entities.BookDetalle;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetallesActivity extends AppCompatActivity implements DetallesView
{

    @Bind(R.id.imgAvatar)
    CircleImageView imgAvatar;
    @Bind(R.id.txtUser)
    TextView txtUser;
    @Bind(R.id.txtStatus)
    TextView txtStatus;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.messageRecyclerView)
    RecyclerView messageRecyclerView;

    private DetallesAdapter adapter;
    private DetallesPresenter presenter;

    public final static String BOOK_KEY = "Titulo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        ButterKnife.bind(this);

        setupAdapter();
        setupRecyclerView();

        presenter = new DetallesPresenterImpl(this);
        presenter.onCreate();
        setupToolbar(getIntent());

    }

    private void setupAdapter() {
        adapter = new DetallesAdapter(getApplicationContext(), new ArrayList<BookDetalle>());
    }
    private void setupRecyclerView() {
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageRecyclerView.setAdapter(adapter);
    }

    private void setupToolbar(Intent i) {
        String recipient = i.getStringExtra(BOOK_KEY);
        presenter.setChatRecipient(recipient);

        txtUser.setText(recipient);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        presenter.onResume();
        super.onResume();
    }

    @Override
    public void onBookReceived(BookDetalle detalle) {
        adapter.add(detalle);
        messageRecyclerView.scrollToPosition(adapter.getItemCount());
    }
}
