package co.edu.uniquindio.marketplace.marketplace.services;

import co.edu.uniquindio.marketplace.marketplace.mapping.dto.ProductoDto;
import co.edu.uniquindio.marketplace.marketplace.mapping.dto.VendedorDto;
import co.edu.uniquindio.marketplace.marketplace.model.Vendedor;

import java.util.List;

public interface IModelFactoryServices {

    List<VendedorDto> obtenerVendedor();
    boolean crearVendedor(VendedorDto vendedorDto);
    boolean actualizarVendedor(VendedorDto vendedorDto);
    boolean eliminarVendedor(String cedula);
    Vendedor validarVendedor(String nombreUsuario, String contrasena);
    Vendedor obtenerVendedorUsuario(String nombreUsuario);
    Vendedor validarVendedorCedula(String cedula);
    List<Vendedor> obtenerVendedoresAsociados(String nombreUsuario);
}
