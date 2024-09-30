package co.edu.uniquindio.marketplace.marketplace.utils;

import co.edu.uniquindio.marketplace.marketplace.model.Administrador;
import co.edu.uniquindio.marketplace.marketplace.model.Marketplace;
import co.edu.uniquindio.marketplace.marketplace.model.Usuario;
import co.edu.uniquindio.marketplace.marketplace.model.Vendedor;

public class DataUtil {

    public static Marketplace inicializarDatos(){
        Marketplace marketplace = new Marketplace();
        Vendedor vendedor = Vendedor.builder()
                .nombre("Stiven")
                .apellido("Robles")
                .cedula("1080")
                .direccion("La Tebaida")
                .telefono("31055")
                .correo("stiven@gmail.com")
                .build();
        marketplace.getListaVendedores().add(vendedor);
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("StivenSARG");
        usuario.setPassword("1234567");
        vendedor.setUsuario(usuario);
        marketplace.getListaUsuarios().add(usuario);
        Vendedor vendedor2 = Vendedor.builder()
                .nombre("John")
                .apellido("Cardona")
                .cedula("1090")
                .direccion("Puerto Espejo")
                .telefono("3108609088")
                .correo("john@gmail.com")
                .build();
        marketplace.getListaVendedores().add(vendedor2);
        Usuario usuario2 = new Usuario();
        usuario2.setNombreUsuario("JohnPapaz");
        usuario2.setPassword("7654321");
        vendedor2.setUsuario(usuario2);
        marketplace.getListaUsuarios().add(usuario2);
        Administrador administrador = Administrador.builder()
                .nombre("Andrés")
                .apellido("Zambrano")
                .cedula("1012")
                .direccion("Bogotá")
                .telefono("314506")
                .correo("andres@gmail.com")
                .build();
        marketplace.getListaAdministradores().add(administrador);
        Usuario adminUsuario = new Usuario();
        adminUsuario.setNombreUsuario("AndresFZV");
        adminUsuario.setPassword("1234567");
        administrador.setUsuario(adminUsuario);
        marketplace.getListaUsuarios().add(adminUsuario);

        return marketplace;
    }
}
