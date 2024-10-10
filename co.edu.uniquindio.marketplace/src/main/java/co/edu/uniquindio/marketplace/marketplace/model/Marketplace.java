package co.edu.uniquindio.marketplace.marketplace.model;

import co.edu.uniquindio.marketplace.marketplace.mapping.dto.ProductoDto;
import co.edu.uniquindio.marketplace.marketplace.mapping.dto.VendedorDto;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Marketplace {

    private String nombre;
    List<Usuario> listaUsuarios = new ArrayList<>();
    List<Vendedor> listaVendedores = new ArrayList<>();
    List<Producto> listaProductos = new ArrayList<>();
    List<Administrador> listaAdministradores = new ArrayList<>();

    public Marketplace() {
    }

    public boolean actualizarVendedor(VendedorDto vendedorDto) {
        for (Vendedor vendedor : listaVendedores) {
            if (vendedor.getCedula().equals(vendedorDto.cedula())) {
                vendedor.setNombre(vendedorDto.nombre());
                vendedor.setApellido(vendedorDto.apellido());
                vendedor.setDireccion(vendedorDto.direccion());
                vendedor.setCorreo(vendedorDto.correo());
                vendedor.setTelefono(vendedorDto.telefono());
                vendedor.getUsuario().setNombreUsuario(vendedorDto.usuario().getNombreUsuario());
                vendedor.getUsuario().setPassword(vendedorDto.usuario().getPassword());
                return true;
            }
        }
        return false;
    }

    public Boolean eliminarVendedor(String cedula) {
        Vendedor vendedor = obtenerVendedor(cedula);
        if (vendedor != null) {
            getListaVendedores().remove(vendedor);
            return true;
        }
        return false;
    }

    public boolean validarVendedor(String nombreUsuario, String contrasena) {
        for(Vendedor vendedorValidado : getListaVendedores()){
            if(vendedorValidado.getUsuario().getNombreUsuario().equals(nombreUsuario) &&
                    vendedorValidado.getUsuario().getPassword().equals(contrasena)){
                return true;
            }
        }
        return false;
    }

    public Boolean crearVendedor(Vendedor nuevoVendedor) {
        Vendedor vendedorEncontrado = obtenerVendedor(nuevoVendedor.getCedula());
        if (vendedorEncontrado == null) {
            getListaVendedores().add(nuevoVendedor);
            return true;
        }
        return false;
    }

    private Vendedor getBuildVendedor(String nombre, String apellido, String cedula, String direccion,
                                      String telefono, String correo, Usuario usuario){
        return Vendedor.builder()
                .nombre(nombre)
                .apellido(apellido)
                .cedula(cedula)
                .direccion(direccion)
                .telefono(telefono)
                .correo(correo)
                .usuario(usuario)
                .build();
    }

    private Producto getBuildProducto(String productoId,String nombre, String descripcion, Image imagen,
                                      String categoria, double precio, String estado){
        return Producto.builder()
                .productoId(productoId)
                .nombre(nombre)
                .descripcion(descripcion)
                .imagen(imagen)
                .categoria(categoria)
                .precio(precio)
                .estado(estado)
                .build();
    }

    private Vendedor obtenerVendedor(String cedula) {
        Vendedor vendedor = null;
        for (Vendedor vendedor1 : getListaVendedores()) {
            if (vendedor1.getCedula().equalsIgnoreCase(cedula)) {
                vendedor = vendedor1;
                break;
            }
        }

        return vendedor;
    }

    public Boolean crearProducto(Producto nuevoProducto) {
        Producto productoEncontrado = obtenerProducto(nuevoProducto.getProductoId());
        if (productoEncontrado == null) {
            getListaProductos().add(nuevoProducto);
            return true;
        }
        return false;
    }

    public boolean actualizarProducto(ProductoDto productoDto) {
        for (Producto producto : listaProductos) {
            if (producto.getProductoId().equals(productoDto.productoId())) {
                producto.setNombre(productoDto.nombre());
                producto.setDescripcion(productoDto.descripcion());
                producto.setImagen(productoDto.imagen());
                producto.setCategoria(productoDto.categoria());
                producto.setPrecio(productoDto.precio());
                producto.setEstado(productoDto.estado());
                return true;
            }
        }
        return false;
    }

    public Boolean eliminarProducto(String productoId) {
        Producto producto = obtenerProducto(productoId);
        if (producto != null) {
            getListaProductos().remove(producto);
            return true;
        }
        return false;
    }


    private Producto obtenerProducto(String productoId){
        Producto producto = null;
        for (Producto producto1 : getListaProductos()) {
            if (producto1.getProductoId().equalsIgnoreCase(productoId)) {
                producto = producto1;
                break;
            }
        }

        return producto;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Vendedor> getListaVendedores() {
        return listaVendedores;
    }

    public List<Usuario> getListaUsuarios(){
        return listaUsuarios;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public List<Administrador> getListaAdministradores() {
        return listaAdministradores;
    }

}
