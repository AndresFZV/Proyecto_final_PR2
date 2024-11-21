package co.edu.uniquindio.marketplace.marketplace.model;

import co.edu.uniquindio.marketplace.marketplace.model.builder.AdminBuilder;

public class Administrador extends Persona {

    public Administrador() {

    }

    public Administrador(String nombre, String apellido, String cedula,
                         String direccion, String telefono, String correo, Usuario usuario) {
        super(nombre, apellido, cedula, direccion, telefono, correo, usuario);
    }

    public static AdminBuilder builder(){
        return new AdminBuilder();
    }
}
