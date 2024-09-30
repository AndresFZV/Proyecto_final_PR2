package co.edu.uniquindio.marketplace.marketplace.mapping.dto;

import co.edu.uniquindio.marketplace.marketplace.model.Usuario;

public record VendedorDto(
        String nombre,
        String apellido,
        String cedula,
        String direccion,
        String telefono,
        String correo,
        Usuario usuario) {
}
