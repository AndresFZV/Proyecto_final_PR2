package co.edu.uniquindio.marketplace.marketplace.model.builder;

import co.edu.uniquindio.marketplace.marketplace.model.Producto;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;

public class ProductoBuilder {

   protected String nombre;
   protected String descripcion;
   protected Image imagen;
   protected String categoria;
   protected double precio;
   protected String estado;

   public ProductoBuilder nombre(String nombre) {
       this.nombre = nombre;
       return this;
   }
   public ProductoBuilder descripcion(String descripcion) {
       this.descripcion = descripcion;
       return this;
   }
   public ProductoBuilder imagen(Image imagen) {
       this.imagen = imagen;
       return this;
   }
   public ProductoBuilder categoria(String categoria) {
       this.categoria = categoria;
       return this;
   }
   public ProductoBuilder precio(double precio) {
       this.precio = precio;
       return this;
   }
   public ProductoBuilder estado(String estado) {
       this.estado = estado;
       return this;
   }
   public Producto build() throws FileNotFoundException {
       return new Producto(nombre, descripcion, imagen, categoria, precio, estado);
   }
}
