package co.edu.uniquindio.marketplace.marketplace.model;

import co.edu.uniquindio.marketplace.marketplace.model.builder.AdminBuilder;

public class Administrador extends Persona {

    // Constructor vacio
    public Administrador() {

    }
    // Construcor con todos sus atributos
    public Administrador(String nombre, String apellido, String cedula,
                         String direccion, String telefono, String correo, Usuario usuario) {
        super(nombre, apellido, cedula, direccion, telefono, correo, usuario);
    }
    // Builder de la clase Administrador
    public static AdminBuilder builder(){
        return new AdminBuilder();
    }
}
