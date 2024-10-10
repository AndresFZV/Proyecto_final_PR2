package co.edu.uniquindio.marketplace.marketplace.factory;

import co.edu.uniquindio.marketplace.marketplace.mapping.dto.ProductoDto;
import co.edu.uniquindio.marketplace.marketplace.mapping.dto.VendedorDto;
import co.edu.uniquindio.marketplace.marketplace.mapping.mappers.MarketplaceMappingImpl;
import co.edu.uniquindio.marketplace.marketplace.model.Marketplace;
import co.edu.uniquindio.marketplace.marketplace.model.Producto;
import co.edu.uniquindio.marketplace.marketplace.model.Vendedor;
import co.edu.uniquindio.marketplace.marketplace.services.IMarketplaceMapping;
import co.edu.uniquindio.marketplace.marketplace.services.IModelFactoryServices;
import co.edu.uniquindio.marketplace.marketplace.utils.DataUtil;

import java.util.List;

public class ModelFactory implements IModelFactoryServices {

    private static ModelFactory modelFactory;
    private Marketplace marketplace;
    private IMarketplaceMapping mapper;

    public static ModelFactory getInstance() {
        if (modelFactory == null) {
            modelFactory = new ModelFactory();

        }
        return modelFactory;
    }

    private ModelFactory() {
        mapper = new MarketplaceMappingImpl();
        marketplace = DataUtil.inicializarDatos();
    }

    @Override
    public List<VendedorDto> obtenerVendedor() {
        return mapper.getVendedoresDto(marketplace.getListaVendedores());
    }

    @Override
    public boolean crearVendedor(VendedorDto vendedorDto) {
        Vendedor vendedor = mapper.vendedorDtoToVendedor(vendedorDto);
        return marketplace.crearVendedor(vendedor);
    }

    @Override
    public boolean actualizarVendedor(VendedorDto vendedorDto) {
        return marketplace.actualizarVendedor(vendedorDto);
    }

    @Override
    public boolean eliminarVendedor(String cedula) {
        return marketplace.eliminarVendedor(cedula);
    }

    @Override
    public boolean validarVendedor(String nombreUsuario, String contrasena) {
        return marketplace.validarVendedor(nombreUsuario, contrasena);
    }

    @Override
    public List<ProductoDto> obtenerProducto() {
        return mapper.getProductosDto(marketplace.getListaProductos());
    }

    @Override
    public boolean crearProducto(ProductoDto productoDto) {
        Producto producto = mapper.productoDtoToProducto(productoDto);
        return marketplace.crearProducto(producto);
    }

    @Override
    public boolean actualizarProducto(ProductoDto productoDto) {
        return marketplace.actualizarProducto(productoDto);
    }

    @Override
    public boolean eliminarProducto(String productoId) {
        return marketplace.eliminarProducto(productoId);
    }
}
