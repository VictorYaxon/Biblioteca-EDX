package org.victoryaxon.firebase.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by VictorYaxon on 13/07/2016.
 */
@JsonIgnoreProperties
public class BookDetalle {
        String Titulo;
        String Autor;
        String Sinopsis;

        public BookDetalle() {
        }

    public BookDetalle(String titulo, String autor, String sinopsis) {
        Titulo = titulo;
        Autor = autor;
        Sinopsis = sinopsis;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public String getSinopsis() {
        return Sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        Sinopsis = sinopsis;
    }

    @Override
    public boolean equals(Object o) {
        boolean equal = false;

        if(o instanceof BookDetalle){
            BookDetalle msg = (BookDetalle)o;
            equal = this.getTitulo().equals(msg.getTitulo());
        }
        return equal;
    }
}
