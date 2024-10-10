package co.edu.uniquindio.marketplace.marketplace.model;

import co.edu.uniquindio.marketplace.marketplace.model.builder.VendedorBuilder;

public class Vendedor extends Persona {

    public Vendedor() {

    }
    public Vendedor(String nombre, String apellido, String cedula,
                         String direccion, String telefono, String correo, Usuario usuario) {
        super(nombre, apellido, cedula, direccion, telefono, correo, usuario);
    }

    public static VendedorBuilder builder(){
        return new VendedorBuilder();
    }

}
