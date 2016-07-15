package org.victoryaxon.firebase.detalles.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.victoryaxon.firebase.R;
import org.victoryaxon.firebase.detalles.ui.DetallesActivity;
import org.victoryaxon.firebase.entities.BookDetalle;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by VictorYaxon on 13/07/2016.
 */
public class DetallesAdapter extends RecyclerView.Adapter<DetallesAdapter.ViewHolder> {
    private Context context;
    private List<BookDetalle> detallesList;

    public DetallesAdapter(Context context, List<BookDetalle> detallesList) {
        this.context = context;
        this.detallesList = detallesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_books, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BookDetalle bookDetalle = detallesList.get(position);
        String title = bookDetalle.getTitulo();
        String sinopsis = bookDetalle.getSinopsis();
        String autor = bookDetalle.getAutor();
        holder.txtMessage.setText(title);
        holder.txtAutor.setText(autor);
        holder.txtSinopsis.setText(sinopsis);

        int gravity = Gravity.VERTICAL_GRAVITY_MASK;

    }
    public void add(BookDetalle titulo) {
        if (!detallesList.contains(titulo)){
            detallesList.add(titulo);
        }
    }
    @Override
    public int getItemCount() {
        return detallesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.txtMessage)
        TextView txtMessage;
        @Bind(R.id.txtAutor)
        TextView txtAutor;
        @Bind(R.id.txtSinopsis)
        TextView txtSinopsis;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
