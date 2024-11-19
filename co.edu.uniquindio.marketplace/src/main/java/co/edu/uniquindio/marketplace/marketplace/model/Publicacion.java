package co.edu.uniquindio.marketplace.marketplace.model;

import java.util.ArrayList;
import java.util.List;

public class Publicacion {

    private int likes;
    private List<String> comentarios;

    public Publicacion() {

    }

    public Publicacion(int likes, List<String> comentarios) {
        this.likes = 0;
        this.comentarios = new ArrayList<>();
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<String> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<String> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "Publicacion{" +
                "likes=" + likes +
                ", comentarios=" + comentarios +
                '}';
    }
}
