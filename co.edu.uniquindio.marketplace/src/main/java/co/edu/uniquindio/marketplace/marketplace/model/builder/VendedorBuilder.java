package co.edu.uniquindio.marketplace.marketplace.model.builder;

import co.edu.uniquindio.marketplace.marketplace.model.Usuario;
import co.edu.uniquindio.marketplace.marketplace.model.Vendedor;

public class VendedorBuilder {

    protected String nombre;
    protected String apellido;
    protected String cedula;
    protected String direccion;
    protected String telefono;
    protected String correo;
    protected Usuario usuario;

    public VendedorBuilder nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }
    public VendedorBuilder apellido(String apellido) {
        this.apellido = apellido;
        return this;
    }
    public VendedorBuilder cedula(String cedula) {
        this.cedula = cedula;
        return this;
    }
    public VendedorBuilder direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }
    public VendedorBuilder telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }
    public VendedorBuilder correo(String correo) {
        this.correo = correo;
        return this;
    }
    public VendedorBuilder usuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public Vendedor build(){
        return new Vendedor(nombre, apellido, cedula, direccion, telefono, correo, usuario);
    }
}
