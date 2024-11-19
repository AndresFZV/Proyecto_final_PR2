package co.edu.uniquindio.marketplace.marketplace.factory;

import co.edu.uniquindio.marketplace.marketplace.mapping.dto.ProductoDto;
import co.edu.uniquindio.marketplace.marketplace.mapping.dto.VendedorDto;
import co.edu.uniquindio.marketplace.marketplace.mapping.mappers.MarketplaceMappingImpl;
import co.edu.uniquindio.marketplace.marketplace.model.Enum.Categoria;
import co.edu.uniquindio.marketplace.marketplace.model.Marketplace;
import co.edu.uniquindio.marketplace.marketplace.model.Producto;
import co.edu.uniquindio.marketplace.marketplace.model.Vendedor;
import co.edu.uniquindio.marketplace.marketplace.services.*;
import co.edu.uniquindio.marketplace.marketplace.utils.DataUtil;
import javafx.collections.ObservableList;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelFactory implements IModelFactoryServices, IProductoService, IReporte{

    private static ModelFactory modelFactory;
    private Marketplace marketplace;
    private IMarketplaceMapping mapper;
    private Vendedor vendedor;
    private Map<String, Vendedor> vendedores;

    public Marketplace getMarketplace() {
        return marketplace;
    }



    private static class SingletonHolder {
        private final static ModelFactory eINSTANCE = new ModelFactory();
    }

    public static ModelFactory getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    private ModelFactory() {
        mapper = new MarketplaceMappingImpl();
        marketplace = DataUtil.inicializarDatos();
        vendedores = new HashMap<>();
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
    public Vendedor validarVendedor(String nombreUsuario, String contrasena) {
        Vendedor vendedor = marketplace.validarVendedor(nombreUsuario, contrasena);
        return vendedor;
    }

    @Override
    public Vendedor obtenerVendedorUsuario(String nombreUsuario) {
        return marketplace.obtenerVendedorUsuario(nombreUsuario);
    }

    @Override
    public Vendedor validarVendedorCedula(String cedula) {
        return  marketplace.validarVendedorCedula(cedula);
    }

    @Override
    public List<Vendedor> obtenerVendedoresAsociados(String nombreUsuario) {
        return marketplace.obtenerVendedoresAsociados(nombreUsuario);
    }

    @Override
    public List<ProductoDto> obtenerProducto() {
        return mapper.getProductosDto(marketplace.getListaProductos());
    }

    public String crearProductoID(){
        return marketplace.crearProductoId();
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

    @Override
    public List<ProductoDto> obtenerProductosVendedor(Vendedor vendedor) {
        return marketplace.obtenerProductosVendedor(vendedor);
    }

    @Override
    public List<Producto> obtenerProductosFecha(LocalDate fecha) {
        return marketplace.obtenerProductosFecha(fecha);
    }

    @Override
    public List<Producto> obtenerTopProductos(int limite) {
        return marketplace.obtenerTopProductos(limite);
    }

    @Override
    public void generarReporte() {
        marketplace.generarReporte();
    }

    @Override
    public ObservableList<ProductoDto> obtenerProductosReporte() {
        return marketplace.obtenerProductosReporte();
    }

    @Override
    public void guardarReporte(File archivo, String contenido) {
        marketplace.guardarReporte(archivo, contenido);
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    public void cerrarSesion(){
        vendedor = null;
    }
}
