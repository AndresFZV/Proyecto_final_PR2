package co.edu.uniquindio.marketplace.marketplace.model;

public class SolicitudAmistad {
    private Vendedor vendedorSolicitante;
    private String mensaje;

    public SolicitudAmistad(Vendedor vendedorSolicitante, String mensaje) {
        this.vendedorSolicitante = vendedorSolicitante;
        this.mensaje = mensaje;
    }
    public Vendedor getVendedorSolicitante() {
        return vendedorSolicitante;
    }
    public String getMensaje() {
        return mensaje;
    }
}

