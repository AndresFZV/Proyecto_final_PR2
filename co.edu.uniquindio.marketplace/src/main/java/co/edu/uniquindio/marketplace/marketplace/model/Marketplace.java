package co.edu.uniquindio.marketplace.marketplace.model;

import co.edu.uniquindio.marketplace.marketplace.factory.ModelFactory;
import co.edu.uniquindio.marketplace.marketplace.mapping.dto.ProductoDto;
import co.edu.uniquindio.marketplace.marketplace.mapping.dto.VendedorDto;
import co.edu.uniquindio.marketplace.marketplace.model.Enum.Categoria;
import co.edu.uniquindio.marketplace.marketplace.viewcontroller.PrincipalViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Marketplace {

    private String nombre;
    List<Usuario> listaUsuarios = new ArrayList<>();
    List<Vendedor> listaVendedores = new ArrayList<>();
    List<Producto> listaProductos = new ArrayList<>();
    List<Administrador> listaAdministradores = new ArrayList<>();
    List<Publicacion> listaPublicaciones = new ArrayList<>();
    private PrincipalViewController principalViewController = new PrincipalViewController();
    ModelFactory modelFactory = ModelFactory.getInstance();

    public Marketplace() {
    }

    public boolean actualizarVendedor(VendedorDto vendedorDto) {
        for (Vendedor vendedor : listaVendedores) {
            if (vendedor.getCedula().equals(vendedorDto.cedula())) {
                vendedor.setNombre(vendedorDto.nombre());
                vendedor.setApellido(vendedorDto.apellido());
                vendedor.setDireccion(vendedorDto.direccion());
                vendedor.setCorreo(vendedorDto.correo());
                vendedor.setTelefono(vendedorDto.telefono());
                vendedor.getUsuario().setNombreUsuario(vendedorDto.usuario().getNombreUsuario());
                vendedor.getUsuario().setPassword(vendedorDto.usuario().getPassword());
                return true;
            }
        }
        return false;
    }

    public Boolean eliminarVendedor(String cedula) {
        Vendedor vendedor = obtenerVendedor(cedula);
        if (vendedor != null) {
            getListaVendedores().remove(vendedor);
            return true;
        }
        return false;
    }

    public Vendedor validarVendedor(String nombreUsuario, String contrasena) {
        for (Vendedor vendedorValidado : getListaVendedores()) {
            if (vendedorValidado.getUsuario().getNombreUsuario().equals(nombreUsuario) &&
                    vendedorValidado.getUsuario().getPassword().equals(contrasena)) {
                return vendedorValidado ;
            }
        }
        return null;
    }

    public Boolean crearVendedor(Vendedor nuevoVendedor) {
        Vendedor vendedorEncontrado = obtenerVendedor(nuevoVendedor.getCedula());
        if (vendedorEncontrado == null) {
            getListaVendedores().add(nuevoVendedor);
            return true;
        }
        return false;
    }

    private Vendedor getBuildVendedor(String nombre, String apellido, String cedula, String direccion,
                                      String telefono, String correo, Usuario usuario) {
        return Vendedor.builder()
                .nombre(nombre)
                .apellido(apellido)
                .cedula(cedula)
                .direccion(direccion)
                .telefono(telefono)
                .correo(correo)
                .usuario(usuario)
                .build();
    }

    private Producto getBuildProducto(String productoId, String nombre, String descripcion, Image imagen,
                                      String categoria, double precio, String estado) {
        return Producto.builder()
                .productoId(productoId)
                .nombre(nombre)
                .descripcion(descripcion)
                .imagen(imagen)
                .categoria(categoria)
                .precio(precio)
                .estado(estado)
                .build();
    }

    private Vendedor obtenerVendedor(String cedula) {
        Vendedor vendedor = null;
        for (Vendedor vendedor1 : getListaVendedores()) {
            if (vendedor1.getCedula().equalsIgnoreCase(cedula)) {
                vendedor = vendedor1;
                break;
            }
        }

        return vendedor;
    }

    public Vendedor validarVendedorCedula(String cedula) {
        for (Vendedor vendedor : getListaVendedores()) {
            if (vendedor.getCedula().equalsIgnoreCase(cedula)) {
                return vendedor;
            }
        }
        return null;
    }

    public Boolean crearProducto(Producto nuevoProducto) {
        Producto productoEncontrado = obtenerProducto(nuevoProducto.getProductoId());
        if (productoEncontrado == null) {
            getListaProductos().add(nuevoProducto);
            return true;
        }
        return false;
    }

    public boolean actualizarProducto(ProductoDto productoDto) {
        for (Producto producto : listaProductos) {
            if (producto.getProductoId().equals(productoDto.productoId())) {
                producto.setNombre(productoDto.nombre());
                producto.setDescripcion(productoDto.descripcion());
                producto.setImagen(productoDto.imagen());
                producto.setCategoria(productoDto.categoria());
                producto.setPrecio(productoDto.precio());
                producto.setEstado(productoDto.estado());
                return true;
            }
        }
        return false;
    }

    public Boolean eliminarProducto(String productoId) {
        Producto producto = obtenerProducto(productoId);
        if (producto != null) {
            getListaProductos().remove(producto);
            return true;
        }
        return false;
    }


    public Producto obtenerProducto(String productoId) {
        Producto producto = null;
        for (Producto producto1 : getListaProductos()) {
            if (producto1.getProductoId().equalsIgnoreCase(productoId)) {
                producto = producto1;
                break;
            }
        }

        return producto;
    }

    public String crearProductoId(){
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }


    public String getNombre() {
        return nombre;
    }

    public List<Vendedor> getListaVendedores() {
        return listaVendedores;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public List<Administrador> getListaAdministradores() {
        return listaAdministradores;
    }

    public List<Publicacion> getListaPublicaciones() {
        return listaPublicaciones;
    }

    public List<ProductoDto> obtenerProductosVendedor(Vendedor vendedor) {
        return vendedor.getListaProductosAsociados().stream().map(producto -> new ProductoDto(
                producto.getProductoId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getImagen(),
                producto.getCategoria(),
                producto.getPrecio(),
                producto.getEstado()
        )).collect(Collectors.toList());
    }

    public Vendedor obtenerVendedorUsuario(String nombreUsuario) {
        for (Vendedor vendedor : getListaVendedores()) {
            if(vendedor.getUsuario().getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                return vendedor;
            }
        }
        return null;
    }

    public List<Producto> obtenerProductosFecha(LocalDate fecha) {
        List<Producto> productosFiltrados = new ArrayList<>();
        for (Producto producto : getListaProductos()) {
            if (producto.getFechaPublicacion() != null && producto.getFechaPublicacion().equals(fecha)) {
                productosFiltrados.add(producto);
            }
        }
        return productosFiltrados;
    }

    public List<Vendedor> obtenerVendedoresAsociados(String nombreUsuario) {
        List<Vendedor> vendedoresFiltrados = new ArrayList<>();
        for (Vendedor vendedor : getListaVendedores()) {
            if (vendedor.getUsuario().getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                vendedoresFiltrados.addAll(vendedor.getListaVendedoresAsociados());
            }
        }
        return vendedoresFiltrados;
    }

    public List<Producto> obtenerTopProductos(int limite) {
        return getListaProductos().stream()
                .sorted((p1, p2) -> Integer.compare(p2.getPublicacion().getLikes(), p1.getPublicacion().getLikes()))
                .limit(limite)
                .collect(Collectors.toList());
    }

    public ObservableList<ProductoDto> obtenerProductosReporte() {
        List<ProductoDto> listaProductosDto = listaProductos.stream()
                .map(producto -> new ProductoDto(
                        producto.getProductoId(),
                        producto.getNombre(),
                        producto.getDescripcion(),
                        producto.getImagen(),
                        producto.getCategoria(),
                        producto.getPrecio(),
                        producto.getEstado()
                ))
                .collect(Collectors.toList());

        return FXCollections.observableArrayList(listaProductosDto);
    }


    public void generarReporte() {
        String usuarioLogeado = getListaAdministradores().get(0).getUsuario().getNombreUsuario();
        StringBuilder reporte = new StringBuilder();
        reporte.append("=== Reporte del Marketplace ===\n");
        reporte.append("Nombre del Marketplace: MarketQuindío").append("\n\n");
        reporte.append("Título: Reporte general Marketplace").append("\n\n");
        reporte.append("Generado por: ").append(usuarioLogeado).append("\n");
        reporte.append("Fecha de generación: ").append(java.time.LocalDateTime.now()).append("\n\n");
        reporte.append("=== Vendedores ===\n");
        for (Vendedor vendedor : listaVendedores) {
            reporte.append("Nombre: ").append(vendedor.getNombre()).append(" ").append(vendedor.getApellido()).append("\n");
            reporte.append("Cédula: ").append(vendedor.getCedula()).append("\n");
            reporte.append("Correo: ").append(vendedor.getCorreo()).append("\n");
            reporte.append("Teléfono: ").append(vendedor.getTelefono()).append("\n");
            reporte.append("Productos:\n");
            for (Producto producto : vendedor.getListaProductosAsociados()) {
                reporte.append("  - ").append(producto.getNombre()).append(" (Estado: ").append(producto.getEstado()).append(")\n");
            }
            reporte.append("\n");
        }
        reporte.append("=== Productos ===\n");
        for (Producto producto : listaProductos) {
            reporte.append("ID: ").append(producto.getProductoId()).append("\n");
            reporte.append("Nombre: ").append(producto.getNombre()).append("\n");
            reporte.append("Categoría: ").append(producto.getCategoria()).append("\n");
            reporte.append("Precio: $").append(producto.getPrecio()).append("\n");
            reporte.append("Estado: ").append(producto.getEstado()).append("\n\n");
        }
        guardarReporte(new File("reporte_marketplace.txt"), reporte.toString());
    }

    public void guardarReporte(File archivo, String contenido) {
        try {
            String rutaReportes = "src/main/resources/Reportes";
            File carpeta = new File(rutaReportes);
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }
            File archivoReporte = new File(carpeta, "reporte_marketplace.txt");

            try (FileWriter writer = new FileWriter(archivoReporte)) {
                writer.write(contenido);
                principalViewController.mostrarAlerta(Alert.AlertType.INFORMATION,
                        "Éxito", "Reporte guardado exitosamente en: " + archivoReporte.getAbsolutePath());
            }
        } catch (IOException e) {
            principalViewController.mostrarAlerta(Alert.AlertType.ERROR,
                    "Error", "Error al guardar el reporte: " + e.getMessage());
        }
    }

    public List<String> asignarEtiquetasCategoria(Categoria categoria) {
        List<String> listaEtiquetas = new ArrayList<>();
        if (categoria != null) {
            switch (categoria) {
                case ROPA:
                    listaEtiquetas.add("Moda");
                    listaEtiquetas.add("Ropa");
                    break;
                case HOGAR:
                    listaEtiquetas.add("Decoración");
                    listaEtiquetas.add("Muebles");
                    break;
                case MUSICA:
                    listaEtiquetas.add("Hip Hop");
                    listaEtiquetas.add("Vinilos");
                    break;
                case ALIMENTOS:
                    listaEtiquetas.add("Comida");
                    listaEtiquetas.add("Bebidas");
                    break;
                case LIBROS:
                    listaEtiquetas.add("Ficción");
                    listaEtiquetas.add("Literatura");
                    break;
                case DEPORTES:
                    listaEtiquetas.add("Fitness");
                    listaEtiquetas.add("Equipamiento Deportivo");
                    break;
                case JUGUETES:
                    listaEtiquetas.add("Niños");
                    listaEtiquetas.add("Juguetes Educativos");
                    break;
                case BELLEZA:
                    listaEtiquetas.add("Cuidado Personal");
                    listaEtiquetas.add("Maquillaje");
                    break;
                case MASCOTAS:
                    listaEtiquetas.add("Perros");
                    listaEtiquetas.add("Gatos");
                    break;
                case TECNOLOGIA:
                    listaEtiquetas.add("Electrónica");
                    listaEtiquetas.add("Gadgets");
                    break;
                case SALUD:
                    listaEtiquetas.add("Bienestar");
                    listaEtiquetas.add("Medicamentos");
                    break;
                case OTROS:
                    listaEtiquetas.add("General");
                    break;
                default:
                    listaEtiquetas.add("General");
                    break;
            }
        }
        return listaEtiquetas;
    }

    public List<Producto> obtenerProductosMuro(Vendedor vendedor) {
        List<Producto> productosMuro = new ArrayList<>(vendedor.getListaProductosAsociados());
        for (Vendedor aliado : vendedor.getListaVendedoresAsociados()) {
            productosMuro.addAll(aliado.getListaProductosAsociados());
        }
        return productosMuro;
    }



}
