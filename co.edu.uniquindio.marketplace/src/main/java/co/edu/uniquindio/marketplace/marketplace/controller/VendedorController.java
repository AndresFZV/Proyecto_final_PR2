package co.edu.uniquindio.marketplace.marketplace.controller;

import co.edu.uniquindio.marketplace.marketplace.factory.ModelFactory;
import co.edu.uniquindio.marketplace.marketplace.mapping.dto.VendedorDto;
import co.edu.uniquindio.marketplace.marketplace.model.Usuario;

import java.util.List;

public class VendedorController {

    private ModelFactory modelFactory;

    public VendedorController() {
        modelFactory = ModelFactory.getInstance();
    }

    // Método para obtener la lista de vendedores
    public List<VendedorDto> obtenerVendedor() {
        return modelFactory.obtenerVendedor();
    }

    // Método para crear un vendedor
    public boolean crearVendedor(String nombre, String apellido, String cedula, String direccion,
                                 String telefono, String correo, Usuario usuario) {
        VendedorDto vendedorDto = new VendedorDto(
                nombre, apellido, cedula, direccion, telefono, correo, usuario
        );
        return modelFactory.crearVendedor(vendedorDto);
    }

    // Método para actualizar un vendedor
    public boolean actualizarVendedor(String nombre, String apellido, String cedula, String direccion,
                                      String telefono, String correo, Usuario usuario) {
        VendedorDto vendedorDto = new VendedorDto(
                nombre, apellido, cedula, direccion, telefono, correo, usuario
        );
        return modelFactory.actualizarVendedor(vendedorDto);
    }

    // Método para eliminar un vendedor por cédula
    public boolean eliminarVendedor(String cedula) {
        return modelFactory.eliminarVendedor(cedula);
    }

    // Método para validad a un vendedor
    public boolean validarVendedor(String nombreUsuario, String contrasena){
        return modelFactory.validarVendedor(nombreUsuario, contrasena);
    }

}
