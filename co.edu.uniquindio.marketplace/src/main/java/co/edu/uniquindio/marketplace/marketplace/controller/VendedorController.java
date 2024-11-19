package co.edu.uniquindio.marketplace.marketplace.controller;

import co.edu.uniquindio.marketplace.marketplace.factory.ModelFactory;
import co.edu.uniquindio.marketplace.marketplace.mapping.dto.VendedorDto;
import co.edu.uniquindio.marketplace.marketplace.model.Vendedor;

import java.util.List;

public class VendedorController {

    private ModelFactory modelFactory;

    public VendedorController() {
        modelFactory = ModelFactory.getInstance();
    }

    public List<VendedorDto> obtenerVendedor() {
        return modelFactory.obtenerVendedor();
    }

    public boolean crearVendedor(VendedorDto vendedorDto) {
        return modelFactory.crearVendedor(vendedorDto);
    }

    public boolean actualizarVendedor(VendedorDto vendedorDto) {
        return modelFactory.actualizarVendedor(vendedorDto);
    }

    public boolean eliminarVendedor(String cedula) {
        return modelFactory.eliminarVendedor(cedula);
    }

    public Vendedor validarVendedor(String nombreUsuario, String contrasena){
        return modelFactory.validarVendedor(nombreUsuario, contrasena);
    }

    public Vendedor validarVendedorCedula(String cedula){
        return modelFactory.validarVendedorCedula(cedula);
    }

    public List<Vendedor> obtenerVendedoresAsociados(String nombreUsuario) {
        return modelFactory.obtenerVendedoresAsociados(nombreUsuario);
    }
}
