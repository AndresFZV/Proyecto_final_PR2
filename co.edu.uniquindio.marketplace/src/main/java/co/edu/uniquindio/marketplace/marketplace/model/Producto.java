package co.edu.uniquindio.marketplace.marketplace.model;

import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Producto {

    private String nombre;
    private String descripcion;
    private Image imagen;
    private String categoria;
    private double precio;
    private String estado;

    public Producto() {
    }
    public Producto(String nombre, String descripcion, String imagenPath, String categoria, double precio, String estado) throws FileNotFoundException {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = new Image(new FileInputStream(imagenPath));
        this.categoria = categoria;
        this.precio = precio;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Image getImagen() {
        return imagen;
    }
    public void setImagen(String imagenPath) throws FileNotFoundException {
        this.imagen = new Image(new FileInputStream(imagenPath));
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", categoria='" + categoria + '\'' +
                ", precio=" + precio +
                ", estado='" + estado + '\'' +
                '}';
    }
}
