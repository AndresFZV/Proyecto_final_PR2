package co.edu.uniquindio.marketplace.marketplace.model;

import javafx.scene.image.Image;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.UUID;

import co.edu.uniquindio.marketplace.marketplace.model.builder.*;

public class Producto {

    private String productoId;
    private String nombre;
    private String descripcion;
    private Image imagen;
    private String categoria;
    private double precio;
    private String estado;

    public Producto() {
    }
    public Producto(String productoId, String nombre, String descripcion, Image imagen,
                    String categoria, double precio, String estado) {
        this.productoId = productoId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.categoria = categoria;
        this.precio = precio;
        this.estado = estado;
    }

    public static ProductoBuilder builder() {
        return new ProductoBuilder();
    }

    public String getProductoId() {
        return productoId;
    }
    public void setProductoId(String productoId) {
        this.productoId = productoId;
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
    public void setImagen(Image imagen) {
        this.imagen = imagen;
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
                "productoId='" + productoId + '\'' +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", categoria='" + categoria + '\'' +
                ", precio=" + precio +
                ", estado='" + estado + '\'' +
                '}';
    }
}
