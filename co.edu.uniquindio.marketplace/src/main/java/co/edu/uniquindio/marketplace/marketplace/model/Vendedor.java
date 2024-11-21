package co.edu.uniquindio.marketplace.marketplace.model;

import co.edu.uniquindio.marketplace.marketplace.model.builder.VendedorBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vendedor extends Persona {
    private List<Producto> listaProductosAsociados = new ArrayList<>();
    private List<Vendedor> listaVendedoresAsociados = new ArrayList<>();
    private List<SolicitudAmistad> solicitudesAmistadPendientes = new ArrayList<>();
    private List<Vendedor> contactos = new ArrayList<>();


    public Vendedor() {
    }


    public Vendedor(String nombre, String apellido, String cedula, String direccion, String telefono, String correo, Usuario usuario) {
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

    public List<SolicitudAmistad> getSolicitudesAmistadPendientes() {
        return solicitudesAmistadPendientes;
    }

    public List<Vendedor> getContactos() {
        return contactos;
    }


    public void enviarSolicitud(Vendedor destinatario) {
        if (destinatario == null) {
            throw new IllegalArgumentException("El destinatario no puede ser nulo.");
        }

        if (this.equals(destinatario)) {
            throw new IllegalArgumentException("No puedes enviarte una solicitud a ti mismo.");
        }


        if (!tieneSolicitudPendiente(destinatario) && !contactos.contains(destinatario)) {
            SolicitudAmistad solicitud = new SolicitudAmistad(this, "Solicitud de amistad de " + this.getNombre());
            destinatario.recibirSolicitud(solicitud);
        } else {
            throw new IllegalArgumentException("Ya tienes una solicitud pendiente o el destinatario ya es tu contacto.");
        }
    }


    public void recibirSolicitud(SolicitudAmistad solicitud) {
        if (solicitud == null) {
            throw new IllegalArgumentException("La solicitud no puede ser nula.");
        }


        if (!solicitudesAmistadPendientes.contains(solicitud)) {
            solicitudesAmistadPendientes.add(solicitud);
        }
    }


    public boolean tieneSolicitudPendiente(Vendedor vendedorSolicitante) {
        if (vendedorSolicitante == null) {
            return false;
        }
        for (SolicitudAmistad solicitud : solicitudesAmistadPendientes) {
            if (solicitud.getVendedorSolicitante().equals(vendedorSolicitante)) {
                return true;
            }
        }
        return false;
    }


    public void aceptarSolicitud(Vendedor vendedorSolicitante) {
        if (vendedorSolicitante == null) {
            throw new IllegalArgumentException("El vendedor solicitante no puede ser nulo.");
        }

        if (tieneSolicitudPendiente(vendedorSolicitante)) {

            contactos.add(vendedorSolicitante);


            vendedorSolicitante.contactos.add(this);


            solicitudesAmistadPendientes.removeIf(solicitud -> solicitud.getVendedorSolicitante().equals(vendedorSolicitante));
        }
    }


    public void rechazarSolicitud(Vendedor vendedorSolicitante) {
        if (vendedorSolicitante == null) {
            throw new IllegalArgumentException("El vendedor solicitante no puede ser nulo.");
        }

        if (tieneSolicitudPendiente(vendedorSolicitante)) {
            solicitudesAmistadPendientes.removeIf(solicitud -> solicitud.getVendedorSolicitante().equals(vendedorSolicitante));
        }
    }


    public boolean esContacto(Vendedor vendedor) {
        return contactos.contains(vendedor);
    }


    public List<SolicitudAmistad> obtenerSolicitudesPendientes() {
        return new ArrayList<>(solicitudesAmistadPendientes); // Retorna una copia para evitar modificaciones externas
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vendedor vendedor = (Vendedor) obj;
        return Objects.equals(getCedula(), vendedor.getCedula());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getCedula());
    }


    @Override
    public String toString() {
        return "Vendedor{" +
                "nombre='" + getNombre() + '\'' +
                ", cedula='" + getCedula() + '\'' +
                ", contactos=" + contactos.size() +
                ", productos=" + listaProductosAsociados.size() +
                '}';
    }
}
