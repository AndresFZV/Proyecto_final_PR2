package co.edu.uniquindio.marketplace.marketplace.utils;

import co.edu.uniquindio.marketplace.marketplace.model.*;
import co.edu.uniquindio.marketplace.marketplace.model.Enum.Categoria;
import co.edu.uniquindio.marketplace.marketplace.model.Enum.Estado;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.UUID;

public class DataUtil {

    public static Marketplace inicializarDatos(){
        Marketplace marketplace = new Marketplace();
        Vendedor vendedor = Vendedor.builder()
                .nombre("Stiven")
                .apellido("Robles")
                .cedula("1080")
                .direccion("La Tebaida")
                .telefono("3105456867")
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
                .telefono("3145065645")
                .correo("andres@gmail.com")
                .build();
        marketplace.getListaAdministradores().add(administrador);
        Usuario adminUsuario = new Usuario();
        adminUsuario.setNombreUsuario("AndresFZV");
        adminUsuario.setPassword("1234567");
        administrador.setUsuario(adminUsuario);
        marketplace.getListaUsuarios().add(adminUsuario);
        Producto producto = new Producto();
        Random random = new Random();
        int id = random.nextInt(9000) + 1000;
        producto.setProductoId(String.valueOf(id));
        producto.setNombre("El principito");
        producto.setDescripcion("Un libro clásico de Antoine de Saint-Exupéry.");
        producto.setCategoria(Categoria.LIBROS.toString());
        producto.setPrecio(55000);
        producto.setEstado(Estado.PUBLICADO.toString());
        String rutaImagen = "src/main/resources/img/productos/el_principito.jpg";
        try {
            Image imagen = new Image(new FileInputStream(rutaImagen));
            producto.setImagen(imagen);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        marketplace.getListaProductos().add(producto);
        return marketplace;
    }
}
