package co.edu.uniquindio.marketplace.marketplace.viewcontroller;

import co.edu.uniquindio.marketplace.marketplace.model.Producto;
import co.edu.uniquindio.marketplace.marketplace.decorator.DecoradorEtiquetas;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductoDetailsViewController {

    @FXML
    private ImageView imgProducto;
    @FXML
    private Text nombreProducto;
    @FXML
    private Text descripcionProducto;
    @FXML
    private Text precioProducto;
    @FXML
    private TextArea comentarioArea;
    @FXML
    private Text meGustaProducto;
    @FXML
    private Text categoriaProducto;
    @FXML
    private Text etiquetasProducto;
    @FXML
    private Button cerrarVentana;
    @FXML
    private Button botonMeGusta;
    @FXML
    private Button comentarButton;

    private Producto producto;
    private List<String> comentarios = new ArrayList<>();
    private int meGustaCount = 0;
    private Set<String> usuariosQueDieronLike = new HashSet<>();
    private String usuarioActual = "Usuario Anónimo"; // Por defecto, reemplazar por el usuario real en tu sistema.

    public void mostrarDetallesProducto(Producto producto) {
        this.producto = producto;
        imgProducto.setImage(producto.getImagen());
        nombreProducto.setText(producto.getNombre());
        descripcionProducto.setText(producto.getDescripcion());
        precioProducto.setText("Precio: $" + producto.getPrecio());
        StringBuilder comentariosText = new StringBuilder();
        for (String comentario : comentarios) {
            comentariosText.append(comentario).append("\n");
        }
        comentarioArea.setText(comentariosText.toString());
        meGustaProducto.setText("Me Gusta: " + meGustaCount);
        categoriaProducto.setText("Categoría: " + producto.getCategoria());
        StringBuilder etiquetasText = new StringBuilder();
        if (producto instanceof DecoradorEtiquetas) {
            DecoradorEtiquetas decorador = (DecoradorEtiquetas) producto;
            for (String etiqueta : decorador.getEtiquetas()) {
                etiquetasText.append(etiqueta).append(", ");
            }
        } else {
            for (String etiqueta : producto.getEtiquetas()) {
                if (etiqueta != null && !etiqueta.isEmpty()) {
                    etiquetasText.append(etiqueta).append(", ");
                }
            }
        }
        if (etiquetasText.length() > 0) {
            etiquetasText.delete(etiquetasText.length() - 2, etiquetasText.length());
        }
        etiquetasProducto.setText("Etiquetas: " + etiquetasText.toString());
    }

    @FXML
    private void agregarComentario() {
        String nuevoComentario = comentarioArea.getText();
        if (!nuevoComentario.isEmpty()) {
            comentarios.add(usuarioActual + ": " + nuevoComentario);
            comentarioArea.clear();
            mostrarDetallesProducto(producto);
        }
    }

    @FXML
    private void agregarMeGusta() {
        if (!usuariosQueDieronLike.contains(usuarioActual)) {
            usuariosQueDieronLike.add(usuarioActual);
            meGustaCount++;
            meGustaProducto.setText("Me Gusta: " + meGustaCount);
        } else {
            meGustaProducto.setText("Ya diste 'Me Gusta'.");
        }
    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) cerrarVentana.getScene().getWindow();
        stage.close();
    }

    // Método para establecer el nombre del usuario actual.
    public void setUsuarioActual(String usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
}
