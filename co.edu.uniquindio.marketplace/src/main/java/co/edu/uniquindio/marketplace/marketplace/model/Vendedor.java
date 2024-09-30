package co.edu.uniquindio.marketplace.marketplace.model;

import co.edu.uniquindio.marketplace.marketplace.model.builder.VendedorBuilder;

public class Vendedor extends Persona {

    // Constructor vacío
    public Vendedor() {

    }
    // Constructor con todos sus atributos
    public Vendedor(String nombre, String apellido, String cedula,
                         String direccion, String telefono, String correo, Usuario usuario) {
        super(nombre, apellido, cedula, direccion, telefono, correo, usuario);
    }
    // Builder de la clase Vendedor
    public static VendedorBuilder builder(){
        return new VendedorBuilder();
    }

}
