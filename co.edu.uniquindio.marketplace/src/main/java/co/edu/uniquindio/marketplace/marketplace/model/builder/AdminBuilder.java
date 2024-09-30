package co.edu.uniquindio.marketplace.marketplace.model.builder;

import co.edu.uniquindio.marketplace.marketplace.model.Administrador;
import co.edu.uniquindio.marketplace.marketplace.model.Usuario;
import co.edu.uniquindio.marketplace.marketplace.model.Vendedor;

public class AdminBuilder {

   protected String nombre;
   protected String apellido;
   protected String cedula;
   protected String direccion;
   protected String telefono;
   protected String correo;
   protected Usuario usuario;

   public AdminBuilder nombre(String nombre) {
       this.nombre = nombre;
       return this;
   }
   public AdminBuilder apellido(String apellido) {
       this.apellido = apellido;
       return this;
   }
   public AdminBuilder cedula(String cedula) {
       this.cedula = cedula;
       return this;
   }
   public AdminBuilder direccion(String direccion) {
       this.direccion = direccion;
       return this;
   }
   public AdminBuilder telefono(String telefono) {
       this.telefono = telefono;
       return this;
   }
   public AdminBuilder correo(String correo) {
       this.correo = correo;
       return this;
   }
   public AdminBuilder usuario(Usuario usuario) {
       this.usuario = usuario;
       return this;
   }

   public Administrador build(){
       return new Administrador(nombre, apellido, cedula, direccion, telefono, correo, usuario);
   }
}
