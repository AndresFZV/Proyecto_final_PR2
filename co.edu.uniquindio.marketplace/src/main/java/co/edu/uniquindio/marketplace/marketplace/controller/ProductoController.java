package co.edu.uniquindio.marketplace.marketplace.controller;

import co.edu.uniquindio.marketplace.marketplace.factory.ModelFactory;
import co.edu.uniquindio.marketplace.marketplace.mapping.dto.ProductoDto;
import co.edu.uniquindio.marketplace.marketplace.model.Enum.Categoria;
import co.edu.uniquindio.marketplace.marketplace.model.Producto;
import co.edu.uniquindio.marketplace.marketplace.model.Vendedor;

import java.time.LocalDate;
import java.util.List;

public class ProductoController {

    private ModelFactory modelFactory;

    public ProductoController() {
        modelFactory = ModelFactory.getInstance();
    }

    public String crearProductoId(){
        return modelFactory.crearProductoID();
    }

    public boolean crearProducto(ProductoDto producto){
        return modelFactory.crearProducto(producto);
    }

    public boolean actualizarProducto(ProductoDto producto){
        return modelFactory.actualizarProducto(producto);
    }

    public boolean eliminarProducto(String productoId){
        return modelFactory.eliminarProducto(productoId);
    }

    public List<ProductoDto> obtenerProductosVendedor(Vendedor vendedor) {
        return modelFactory.obtenerProductosVendedor(vendedor);
    }
    public List<ProductoDto> obtenerProductosUsuario(Vendedor nombreUsuario) {
        return modelFactory.obtenerProductosVendedor(nombreUsuario);
    }

    public Vendedor obtenerVendedorUsuario(String nombreUsuario) {
        return modelFactory.obtenerVendedorUsuario(nombreUsuario);
    }

    public List<Producto> obtenerProductosFecha(LocalDate fecha){
        return modelFactory.obtenerProductosFecha(fecha);
    }

    public List<Producto> obtenerTopProductos(int limite){
        return modelFactory.obtenerTopProductos(limite);
    }

}
