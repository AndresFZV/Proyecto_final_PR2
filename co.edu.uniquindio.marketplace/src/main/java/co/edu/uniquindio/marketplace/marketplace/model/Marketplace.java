package co.edu.uniquindio.marketplace.marketplace.model;

import co.edu.uniquindio.marketplace.marketplace.mapping.dto.VendedorDto;

import java.util.ArrayList;
import java.util.List;

public class Marketplace {

    private String nombre;
    List<Usuario> listaUsuarios = new ArrayList<>();
    List<Vendedor> listaVendedores = new ArrayList<>();
    List<Producto> listaProductos = new ArrayList<>();
    List<Administrador> listaAdministradores = new ArrayList<>();

    // Constructor vacío
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
