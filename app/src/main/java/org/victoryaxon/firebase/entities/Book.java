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


    public Book() {
    }

    public Book(String titulo) {
        this.titulo = titulo;

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

        return result;
    }
}
