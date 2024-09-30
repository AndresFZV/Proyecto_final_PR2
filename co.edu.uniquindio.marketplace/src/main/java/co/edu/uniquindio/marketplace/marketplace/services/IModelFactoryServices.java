package co.edu.uniquindio.marketplace.marketplace.services;

import co.edu.uniquindio.marketplace.marketplace.mapping.dto.VendedorDto;

import java.util.List;

public interface IModelFactoryServices {
    List<VendedorDto> obtenerVendedor();
    boolean crearVendedor(VendedorDto vendedorDto);
    boolean actualizarVendedor(VendedorDto vendedorDto);
    boolean eliminarVendedor(String cedula);
    boolean validarVendedor(String nombreUsuario, String contrasena);
}
