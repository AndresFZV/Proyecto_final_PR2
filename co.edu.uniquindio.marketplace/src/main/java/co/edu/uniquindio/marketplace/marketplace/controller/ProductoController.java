package co.edu.uniquindio.marketplace.marketplace.controller;

import co.edu.uniquindio.marketplace.marketplace.factory.ModelFactory;
import co.edu.uniquindio.marketplace.marketplace.mapping.dto.ProductoDto;

import java.util.List;

public class ProductoController {

    private ModelFactory modelFactory;

    public ProductoController() {
        modelFactory = ModelFactory.getInstance();
    }

    public List<ProductoDto> obtenerProducto(){
        return modelFactory.obtenerProducto();
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
}
