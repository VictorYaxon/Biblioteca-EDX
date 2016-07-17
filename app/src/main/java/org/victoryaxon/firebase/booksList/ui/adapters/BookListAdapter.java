package org.victoryaxon.firebase.booksList.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.victoryaxon.firebase.R;
import org.victoryaxon.firebase.entities.Book;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by VictorYaxon on 06/07/2016.
 */
public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder>{
    private List<Book> booksList;
    private OnItemClickListener onItemClickListener;

    public BookListAdapter(List<Book> booksList, OnItemClickListener onItemClickListener) {
        this.booksList = booksList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_contacts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book book = booksList.get(position);
        holder.setClickListener(book, onItemClickListener);

        String titulo = book.getTitulo();

        holder.txtUser.setText(titulo);
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    private boolean alreadyInAdapter(Book newUser){
        boolean alreadyInAdapter = false;
        for (Book book : this.booksList) {
            if (book.getTitulo().equals(newUser.getTitulo())) {
                alreadyInAdapter = true;
                break;
            }
        }

        return alreadyInAdapter;
    }
    public void add(Book book) {
        if (!alreadyInAdapter(book)) {
            this.booksList.add(book);
            this.notifyDataSetChanged();
        }
    }
    public int getPositionByUsername(String booknew) {
        int position = 0;
        for (Book book : booksList) {
            if (book.getTitulo().equals(booknew)) {
                break;
            }
            position++;
        }

        return position;
    }
    public void remove(Book book) {
        int pos = getPositionByUsername(book.getTitulo());
        booksList.remove(pos);
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txtUser)
        TextView txtUser;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            this.view = itemView;
        }

        private void setClickListener(final Book book, final OnItemClickListener listener){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(book);
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onItemLongClick(book);
                    return false;
                }
            });
        }
    }
}
