package co.edu.uniquindio.marketplace.marketplace.services;

import co.edu.uniquindio.marketplace.marketplace.mapping.dto.ProductoDto;
import co.edu.uniquindio.marketplace.marketplace.model.Enum.Categoria;
import co.edu.uniquindio.marketplace.marketplace.model.Producto;
import co.edu.uniquindio.marketplace.marketplace.model.Vendedor;

import java.time.LocalDate;
import java.util.List;

public interface IProductoService {
    List<ProductoDto> obtenerProducto();
    boolean crearProducto(ProductoDto productoDto);
    boolean actualizarProducto(ProductoDto productoDto);
    boolean eliminarProducto(String productoId);
    List<ProductoDto> obtenerProductosVendedor(Vendedor vendedor);
    List<Producto> obtenerProductosFecha(LocalDate fecha);
    List<Producto> obtenerTopProductos(int limite);


}
