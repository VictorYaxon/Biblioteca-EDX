package org.victoryaxon.firebase.entities;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by VictorYaxon on 06/07/2016.
 */
@IgnoreExtraProperties
public class Book {
    String titulo;
    String autor;
    String sinopsis;

    public Book() {
    }

    public Book(String titulo, String autor, String sinopsis) {
        this.titulo = titulo;
        this.autor = autor;
        this.sinopsis = sinopsis;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Titulo", titulo);
        result.put("Autor",  autor);
        result.put("Sinopsis", sinopsis);

        return result;
    }
}
