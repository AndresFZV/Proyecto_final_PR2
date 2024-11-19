package co.edu.uniquindio.marketplace.marketplace.model;

import co.edu.uniquindio.marketplace.marketplace.model.builder.VendedorBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vendedor extends Persona {
    private List<Producto> listaProductosAsociados = new ArrayList<>();
    private List<Vendedor> listaVendedoresAsociados = new ArrayList<>();

    public Vendedor() {

    }

    public Vendedor(String nombre, String apellido, String cedula,
                    String direccion, String telefono, String correo, Usuario usuario) {
        super(nombre, apellido, cedula, direccion, telefono, correo, usuario);
    }

    public static VendedorBuilder builder() {
        return new VendedorBuilder();
    }

    public List<Producto> getListaProductosAsociados() {
        return listaProductosAsociados;
    }

    public void setListaProductosAsociados(List<Producto> listaProductosAsociados) {
        this.listaProductosAsociados = listaProductosAsociados;
    }

    public List<Vendedor> getListaVendedoresAsociados() {
        return listaVendedoresAsociados;
    }

    public void setListaVendedoresAsociados(List<Vendedor> listaVendedoresAsociados) {
        this.listaVendedoresAsociados = listaVendedoresAsociados;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vendedor vendedor = (Vendedor) obj;
        return Objects.equals(getCedula(), vendedor.getCedula());
    }

    public boolean involucra(Vendedor vendedor) {
        return this.listaVendedoresAsociados.contains(vendedor);
    }
}
