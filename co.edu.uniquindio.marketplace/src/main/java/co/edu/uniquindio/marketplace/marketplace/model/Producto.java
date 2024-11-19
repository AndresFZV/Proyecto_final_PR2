package co.edu.uniquindio.marketplace.marketplace.model;

import co.edu.uniquindio.marketplace.marketplace.model.builder.ProductoBuilder;
import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Producto {

    private String productoId;
    private String nombre;
    private String descripcion;
    private Image imagen;
    private String categoria;
    private double precio;
    private String estado;
    private Vendedor vendedor;
    private LocalDate fechaPublicacion;
    private Publicacion publicacion;
    private List<String> etiquetas;

    public Producto() {
        this.etiquetas = new ArrayList<>();
    }

    public Producto(String productoId, String nombre, String descripcion, Image imagen,
                    String categoria, double precio) {
        this.productoId = productoId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.categoria = categoria;
        this.precio = precio;
        this.etiquetas = new ArrayList<>();
        asignarEtiquetasPorCategoria();
    }

    public static ProductoBuilder builder() {
        return new ProductoBuilder();
    }

    private void asignarEtiquetasPorCategoria() {
        if (this.categoria != null) {
            switch (this.categoria) {
                case "ROPA":
                    etiquetas.add("Moda");
                    etiquetas.add("Ropa");
                    break;
                case "HOGAR":
                    etiquetas.add("Decoración");
                    etiquetas.add("Muebles");
                    break;
                case "MUSICA":
                    etiquetas.add("Hip Hop");
                    etiquetas.add("Vinilos");
                    break;
                case "ALIMENTOS":
                    etiquetas.add("Comida");
                    etiquetas.add("Bebidas");
                    break;
                case "LIBROS":
                    etiquetas.add("Ficción");
                    etiquetas.add("Literatura");
                    break;
                case "DEPORTES":
                    etiquetas.add("Fitness");
                    etiquetas.add("Equipamiento Deportivo");
                    break;
                case "JUGUETES":
                    etiquetas.add("Niños");
                    etiquetas.add("Juguetes Educativos");
                    break;
                case "BELLEZA":
                    etiquetas.add("Cuidado Personal");
                    etiquetas.add("Maquillaje");
                    break;
                case "MASCOTAS":
                    etiquetas.add("Perros");
                    etiquetas.add("Gatos");
                    break;
                case "TECNOLOGIA":
                    etiquetas.add("Electrónica");
                    etiquetas.add("Gadgets");
                    break;
                case "SALUD":
                    etiquetas.add("Bienestar");
                    etiquetas.add("Medicamentos");
                    break;
                case "OTROS":
                    etiquetas.add("General");
                    break;
                default:
                    etiquetas.add("General");
                    break;
            }
        }
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

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public List<String> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<String> etiquetas) {
        this.etiquetas = etiquetas;
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
                ", vendedor=" + vendedor +
                ", fechaPublicacion=" + fechaPublicacion +
                '}';
    }
}
