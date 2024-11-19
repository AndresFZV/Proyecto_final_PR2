package co.edu.uniquindio.marketplace.marketplace.utils;

import co.edu.uniquindio.marketplace.marketplace.model.*;
import co.edu.uniquindio.marketplace.marketplace.model.Enum.Categoria;
import co.edu.uniquindio.marketplace.marketplace.model.Enum.Estado;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DataUtil {

    static int generateRandomId(Random random) {
        return random.nextInt(9000) + 1000;
    }

    public static Marketplace inicializarDatos() {
        Marketplace marketplace = new Marketplace();
        Random random = new Random();

        Vendedor vendedor = Vendedor.builder()
                .nombre("Andrés")
                .apellido("Zambrano")
                .cedula("1012317108")
                .direccion("Bogotá")
                .telefono("3145065645")
                .correo("andres@gmail.com")
                .build();
        marketplace.getListaVendedores().add(vendedor);


        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("AndresFZV");
        usuario.setPassword("andres123");
        vendedor.setUsuario(usuario);
        marketplace.getListaUsuarios().add(usuario);

        Vendedor vendedor2 = Vendedor.builder()
                .nombre("John")
                .apellido("Cardona")
                .cedula("1090567890")
                .direccion("Puerto Espejo")
                .telefono("3108609088")
                .correo("john@gmail.com")
                .build();

        marketplace.getListaVendedores().add(vendedor2);

        Usuario usuario2 = new Usuario();
        usuario2.setNombreUsuario("JohnPapaz");
        usuario2.setPassword("isamrm123");
        vendedor2.setUsuario(usuario2);
        marketplace.getListaUsuarios().add(usuario2);

        Vendedor vendedor3 = Vendedor.builder()
                .nombre("Stiven")
                .apellido("Robles")
                .cedula("1012318208")
                .direccion("La Tebaida")
                .telefono("3105456867")
                .correo("stiven@gmail.com")
                .build();
        marketplace.getListaVendedores().add(vendedor3);

        Usuario usuario3 = new Usuario();
        usuario3.setNombreUsuario("StivenSARG");
        usuario3.setPassword("1234567");
        vendedor3.setUsuario(usuario3);
        marketplace.getListaUsuarios().add(usuario3);

        Vendedor vendedor4 = Vendedor.builder()
                .nombre("Angelica")
                .apellido("Reyes")
                .cedula("1090287231")
                .direccion("Armenia")
                .telefono("3006188127")
                .correo("angelica@gmail.com")
                .build();
        marketplace.getListaVendedores().add(vendedor4);

        Usuario usuario4 = new Usuario();
        usuario4.setNombreUsuario("AngelicaRx");
        usuario4.setPassword("angelica00");
        vendedor4.setUsuario(usuario4);
        marketplace.getListaUsuarios().add(usuario4);

        Vendedor vendedor5 = Vendedor.builder()
                .nombre("David")
                .apellido("Bedoya")
                .cedula("1122334455")
                .direccion("Cali")
                .telefono("3176544129")
                .correo("david1@gmail.com")
                .build();
        marketplace.getListaVendedores().add(vendedor5);

        Usuario usuario5 = new Usuario();
        usuario5.setNombreUsuario("David_Bedoyax");
        usuario5.setPassword("lemonwolf");
        vendedor5.setUsuario(usuario5);
        marketplace.getListaUsuarios().add(usuario5);

        vendedor.getListaVendedoresAsociados().add(vendedor2);
        vendedor.getListaVendedoresAsociados().add(vendedor3);
        vendedor2.getListaVendedoresAsociados().add(vendedor);
        vendedor2.getListaVendedoresAsociados().add(vendedor4);
        vendedor3.getListaVendedoresAsociados().add(vendedor5);
        vendedor4.getListaVendedoresAsociados().add(vendedor);
        vendedor5.getListaVendedoresAsociados().add(vendedor2);
        vendedor5.getListaVendedoresAsociados().add(vendedor4);

        Administrador administrador = Administrador.builder()
                .nombre("Jesús")
                .apellido("Cardona")
                .cedula("25120101")
                .direccion("Bogotá")
                .telefono("3112425124")
                .correo("jesus@gmail.com")
                .build();
        marketplace.getListaAdministradores().add(administrador);

        Usuario adminUsuario = new Usuario();
        adminUsuario.setNombreUsuario("admin");
        adminUsuario.setPassword("admin123");
        administrador.setUsuario(adminUsuario);
        marketplace.getListaUsuarios().add(adminUsuario);

        Producto producto = new Producto();
        producto.setProductoId(String.valueOf(generateRandomId(random)));
        producto.setNombre("El principito");
        producto.setDescripcion("Un libro clásico");
        producto.setCategoria(Categoria.LIBROS.toString());
        producto.setPrecio(55000);
        producto.setEstado(Estado.PUBLICADO.toString());
        producto.setFechaPublicacion(LocalDate.now());
        producto.setVendedor(vendedor3);
        vendedor3.getListaProductosAsociados().add(producto);
        String rutaImagen = "src/main/resources/img/productos/el_principito.jpg";
        try {
            Image imagen = new Image(new FileInputStream(rutaImagen));
            producto.setImagen(imagen);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Publicacion publicacion = new Publicacion();
        publicacion.setLikes(8);
        producto.setPublicacion(publicacion);
        marketplace.getListaProductos().add(producto);
        marketplace.getListaPublicaciones().add(publicacion);

        Producto producto2 = new Producto();
        producto2.setProductoId(String.valueOf(generateRandomId(random)));
        producto2.setNombre("Vinilo DAMN");
        producto2.setDescripcion("Vinilo de Kendrick Lamar");
        producto2.setCategoria(Categoria.MUSICA.toString());
        producto2.setPrecio(300000);
        producto2.setEstado(Estado.PUBLICADO.toString());
        producto2.setVendedor(vendedor);
        producto2.setFechaPublicacion(LocalDate.now());
        vendedor.getListaProductosAsociados().add(producto2);
        String rutaImagen2 = "src/main/resources/img/productos/damn.jpg";
        try {
            Image imagen = new Image(new FileInputStream(rutaImagen2));
            producto2.setImagen(imagen);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Publicacion publicacion2 = new Publicacion();
        publicacion2.setLikes(12);
        producto2.setPublicacion(publicacion2);
        marketplace.getListaProductos().add(producto2);
        marketplace.getListaPublicaciones().add(publicacion2);

        Producto producto3 = new Producto();
        producto3.setProductoId(String.valueOf(generateRandomId(random)));
        producto3.setNombre("Vinilo Blonde");
        producto3.setDescripcion("Vinilo de Frank Ocean");
        producto3.setCategoria(Categoria.MUSICA.toString());
        producto3.setPrecio(295000);
        producto3.setEstado(Estado.PUBLICADO.toString());
        producto3.setVendedor(vendedor);
        producto3.setFechaPublicacion(LocalDate.now());
        vendedor.getListaProductosAsociados().add(producto3);
        String rutaImagen3 = "src/main/resources/img/productos/blonde.jpg";
        try {
            Image imagen = new Image(new FileInputStream(rutaImagen3));
            producto3.setImagen(imagen);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Publicacion publicacion3 = new Publicacion();
        publicacion3.setLikes(15);
        producto3.setPublicacion(publicacion3);
        marketplace.getListaProductos().add(producto3);
        marketplace.getListaPublicaciones().add(publicacion3);

        Producto producto4 = new Producto();
        producto4.setProductoId(String.valueOf(generateRandomId(random)));
        producto4.setNombre("PlayStation5");
        producto4.setDescripcion("Consola de videojuegos");
        producto4.setCategoria(Categoria.TECNOLOGIA.toString());
        producto4.setPrecio(3750000);
        producto4.setEstado(Estado.PUBLICADO.toString());
        producto4.setVendedor(vendedor);
        producto4.setFechaPublicacion(LocalDate.now());
        vendedor.getListaProductosAsociados().add(producto4);
        String rutaImagen4 = "src/main/resources/img/productos/ps5.jpg";
        try {
            Image imagen = new Image(new FileInputStream(rutaImagen4));
            producto4.setImagen(imagen);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Publicacion publicacion4 = new Publicacion();
        publicacion4.setLikes(20);
        producto4.setPublicacion(publicacion4);
        marketplace.getListaProductos().add(producto4);
        marketplace.getListaPublicaciones().add(publicacion4);

        Producto producto5 = new Producto();
        producto5.setProductoId(String.valueOf(generateRandomId(random)));
        producto5.setNombre("Camiseta Real Madrid");
        producto5.setDescripcion("Camiseta de la temporada pasada");
        producto5.setCategoria(Categoria.ROPA.toString());
        producto5.setPrecio(200000);
        producto5.setEstado(Estado.PUBLICADO.toString());
        producto5.setVendedor(vendedor2);
        producto5.setFechaPublicacion(LocalDate.now());
        vendedor2.getListaProductosAsociados().add(producto5);
        String rutaImagen5 = "src/main/resources/img/productos/camiseta_real.jpg";
        try {
            Image imagen = new Image(new FileInputStream(rutaImagen5));
            producto5.setImagen(imagen);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Publicacion publicacion5 = new Publicacion();
        publicacion5.setLikes(5);
        producto5.setPublicacion(publicacion5);
        marketplace.getListaProductos().add(producto5);
        marketplace.getListaPublicaciones().add(publicacion5);

        Producto producto6 = new Producto();
        producto6.setProductoId(String.valueOf(generateRandomId(random)));
        producto6.setNombre("Bolsa de papas");
        producto6.setDescripcion("Bolsas de papas para fritar");
        producto6.setCategoria(Categoria.ALIMENTOS.toString());
        producto6.setPrecio(25000);
        producto6.setEstado(Estado.PUBLICADO.toString());
        producto6.setVendedor(vendedor2);
        producto6.setFechaPublicacion(LocalDate.now());
        vendedor2.getListaProductosAsociados().add(producto6);
        String rutaImagen6 = "src/main/resources/img/productos/papas.jpg";
        try {
            Image imagen = new Image(new FileInputStream(rutaImagen6));
            producto6.setImagen(imagen);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Publicacion publicacion6 = new Publicacion();
        publicacion6.setLikes(10);
        producto6.setPublicacion(publicacion6);
        marketplace.getListaProductos().add(producto6);
        marketplace.getListaPublicaciones().add(publicacion6);

        Producto producto7 = new Producto();
        producto7.setProductoId(String.valueOf(generateRandomId(random)));
        producto7.setNombre("Six Pack de Poker lata");
        producto7.setDescripcion("Poker Lata x6");
        producto7.setCategoria(Categoria.OTROS.toString());
        producto7.setPrecio(24000);
        producto7.setEstado(Estado.PUBLICADO.toString());
        producto7.setVendedor(vendedor3);
        producto7.setFechaPublicacion(LocalDate.now());
        vendedor3.getListaProductosAsociados().add(producto7);
        String rutaImagen7 = "src/main/resources/img/productos/six_pack.jpg";
        try {
            Image imagen = new Image(new FileInputStream(rutaImagen7));
            producto7.setImagen(imagen);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Publicacion publicacion7 = new Publicacion();
        publicacion7.setLikes(18);
        producto7.setPublicacion(publicacion7);
        marketplace.getListaProductos().add(producto7);
        marketplace.getListaPublicaciones().add(publicacion7);

        Producto producto8 = new Producto();
        producto8.setProductoId(String.valueOf(generateRandomId(random)));
        producto8.setNombre("Raqueta de tenis");
        producto8.setDescripcion("Raqueta profesional");
        producto8.setCategoria(Categoria.DEPORTES.toString());
        producto8.setPrecio(125000);
        producto8.setEstado(Estado.PUBLICADO.toString());
        producto8.setVendedor(vendedor3);
        vendedor3.getListaProductosAsociados().add(producto8);
        String rutaImagen8 = "src/main/resources/img/productos/raqueta.jpg";
        try {
            Image imagen = new Image(new FileInputStream(rutaImagen8));
            producto8.setImagen(imagen);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Publicacion publicacion8 = new Publicacion();
        publicacion8.setLikes(25);
        producto8.setPublicacion(publicacion8);
        marketplace.getListaProductos().add(producto8);
        marketplace.getListaPublicaciones().add(publicacion8);

        Producto producto9 = new Producto();
        producto9.setProductoId(String.valueOf(generateRandomId(random)));
        producto9.setNombre("Matematicas Simplificadas");
        producto9.setDescripcion("Libro de matematicas");
        producto9.setCategoria(Categoria.LIBROS.toString());
        producto9.setPrecio(223200);
        producto9.setEstado(Estado.PUBLICADO.toString());
        producto9.setFechaPublicacion(LocalDate.now());
        producto9.setVendedor(vendedor4);
        vendedor4.getListaProductosAsociados().add(producto9);
        String rutaImagen9 = "src/main/resources/img/productos/libro_matematicas.jpg";
        try {
            Image imagen = new Image(new FileInputStream(rutaImagen9));
            producto9.setImagen(imagen);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Publicacion publicacion9 = new Publicacion();
        publicacion9.setLikes(30);
        producto9.setPublicacion(publicacion9);
        marketplace.getListaProductos().add(producto9);
        marketplace.getListaPublicaciones().add(publicacion9);

        Producto producto10 = new Producto();
        producto10.setProductoId(String.valueOf(generateRandomId(random)));
        producto10.setNombre("Gorra");
        producto10.setDescripcion("Gorra de color gris");
        producto10.setCategoria(Categoria.ROPA.toString());
        producto10.setPrecio(25000);
        producto10.setEstado(Estado.PUBLICADO.toString());
        producto10.setVendedor(vendedor4);
        producto10.setFechaPublicacion(LocalDate.now());
        vendedor4.getListaProductosAsociados().add(producto10);
        String rutaImagen10 = "src/main/resources/img/productos/gorra.jpg";
        try {
            Image imagen = new Image(new FileInputStream(rutaImagen10));
            producto10.setImagen(imagen);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Publicacion publicacion10 = new Publicacion();
        publicacion10.setLikes(7);
        producto10.setPublicacion(publicacion10);
        marketplace.getListaProductos().add(producto10);
        marketplace.getListaPublicaciones().add(publicacion10);

        Producto producto11 = new Producto();
        producto11.setProductoId(String.valueOf(generateRandomId(random)));
        producto11.setNombre("Cuadros");
        producto11.setDescripcion("Cuadros de decoración");
        producto11.setCategoria(Categoria.HOGAR.toString());
        producto11.setPrecio(55000);
        producto11.setEstado(Estado.PUBLICADO.toString());
        producto11.setVendedor(vendedor4);
        producto11.setFechaPublicacion(LocalDate.now());
        vendedor4.getListaProductosAsociados().add(producto11);
        String rutaImagen11 = "src/main/resources/img/productos/cuadros.jpg";
        try {
            Image imagen = new Image(new FileInputStream(rutaImagen11));
            producto11.setImagen(imagen);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Publicacion publicacion11 = new Publicacion();
        publicacion11.setLikes(7);
        producto11.setPublicacion(publicacion11);
        marketplace.getListaProductos().add(producto11);
        marketplace.getListaPublicaciones().add(publicacion11);

        Producto producto12 = new Producto();
        producto12.setProductoId(String.valueOf(generateRandomId(random)));
        producto12.setNombre("PlayStation4");
        producto12.setDescripcion("Consola de videojuegos play4");
        producto12.setCategoria(Categoria.TECNOLOGIA.toString());
        producto12.setPrecio(1560000);
        producto12.setEstado(Estado.PUBLICADO.toString());
        producto12.setVendedor(vendedor5);
        producto12.setFechaPublicacion(LocalDate.now());
        vendedor5.getListaProductosAsociados().add(producto12);
        String rutaImagen12 = "src/main/resources/img/productos/ps4.jpg";
        try {
            Image imagen = new Image(new FileInputStream(rutaImagen12));
            producto12.setImagen(imagen);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Publicacion publicacion12 = new Publicacion();
        publicacion12.setLikes(7);
        producto12.setPublicacion(publicacion12);
        marketplace.getListaProductos().add(producto12);
        marketplace.getListaPublicaciones().add(publicacion12);

        Producto producto13 = new Producto();
        producto13.setProductoId(String.valueOf(generateRandomId(random)));
        producto13.setNombre("RTX 3060");
        producto13.setDescripcion("Tarjeta grafica");
        producto13.setCategoria(Categoria.TECNOLOGIA.toString());
        producto13.setPrecio(1700000);
        producto13.setEstado(Estado.PUBLICADO.toString());
        producto13.setVendedor(vendedor5);
        producto13.setFechaPublicacion(LocalDate.now());
        vendedor5.getListaProductosAsociados().add(producto13);
        String rutaImagen13 = "src/main/resources/img/productos/rtx3060.jpg";
        try {
            Image imagen = new Image(new FileInputStream(rutaImagen13));
            producto13.setImagen(imagen);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Publicacion publicacion13 = new Publicacion();
        publicacion13.setLikes(7);
        producto13.setPublicacion(publicacion13);
        marketplace.getListaProductos().add(producto13);
        marketplace.getListaPublicaciones().add(publicacion13);

        return marketplace;
    }

}
