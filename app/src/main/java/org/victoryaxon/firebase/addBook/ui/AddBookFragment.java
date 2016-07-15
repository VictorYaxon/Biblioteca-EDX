package org.victoryaxon.firebase.addBook.ui;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.victoryaxon.firebase.R;
import org.victoryaxon.firebase.addBook.AddBookPresenter;
import org.victoryaxon.firebase.addBook.AddBookPresenterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddBookFragment extends DialogFragment implements AddBookView, DialogInterface.OnShowListener {


    @Bind(R.id.editTxtTitulo)
    EditText editTxtTitulo;
    @Bind(R.id.editTxtAutor)
    EditText editTxtAutor;
    @Bind(R.id.editTxtSinopsis)
    EditText editTxtSinopsis;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private AddBookPresenter presenter;

    public AddBookFragment() {
        presenter = new AddBookPresenterImpl(this);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.addcontact_message_title)
                .setPositiveButton(R.string.addcontact_message_add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }

                }).setNegativeButton(R.string.addcontact_message_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }

                });

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_add_book, null);
        ButterKnife.bind(this, view);

        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(this);

        return dialog;
    }

    @Override
    public void showInput() {
        editTxtTitulo.setVisibility(View.VISIBLE);
        editTxtAutor.setVisibility(View.VISIBLE);
        editTxtSinopsis.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideInput() {
        editTxtTitulo.setVisibility(View.GONE);
        editTxtAutor.setVisibility(View.GONE);
        editTxtSinopsis.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void bookAdded() {
        Toast.makeText(getActivity(), R.string.addcontact_message_contactadded, Toast.LENGTH_SHORT).show();
        dismiss();
    }

    @Override
    public void bookNotAdded() {
        editTxtTitulo.setText("");
        editTxtTitulo.setError(getString(R.string.addcontact_error_message));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onShow(DialogInterface dialogInterface) {
       final AlertDialog dialog = (AlertDialog) getDialog();
        if (dialog != null) {
            Button positiveButton = dialog.getButton(Dialog.BUTTON_POSITIVE);
            Button negativeButton = dialog.getButton(Dialog.BUTTON_NEGATIVE);

            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    validacion();
                }
            });

            negativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
        }
        presenter.onShow();
    }

    public void validacion(){
        System.out.println("----- Boton agregar presionado -----");
        if (editTxtTitulo.length()==0) {
            editTxtTitulo.setText("");
            editTxtTitulo.setError("Ingrese el titulo del libro");
        }
        if (editTxtAutor.length()==0) {
            editTxtAutor.setText("");
            editTxtAutor.setError("Ingrese un autor");
        }
        if (editTxtSinopsis.length()==0) {
            editTxtSinopsis.setText("");
            editTxtSinopsis.setError("Ingrese una sinopsis del libro");
        }
        if (editTxtSinopsis.length()==0||editTxtAutor.length()==0||editTxtSinopsis.length()==0){

        }else {
            presenter.addBook(editTxtTitulo.getText().toString(),editTxtAutor.getText().toString(),editTxtSinopsis.getText().toString());
        }
    }
}
