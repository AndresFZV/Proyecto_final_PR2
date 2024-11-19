package co.edu.uniquindio.marketplace.marketplace.viewcontroller;

import co.edu.uniquindio.marketplace.marketplace.factory.ModelFactory;
import co.edu.uniquindio.marketplace.marketplace.model.Marketplace;
import co.edu.uniquindio.marketplace.marketplace.model.Producto;
import co.edu.uniquindio.marketplace.marketplace.model.Vendedor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MuroViewController {

    @FXML
    private GridPane gridPaneProductos;

    private Marketplace marketplace;
    private ModelFactory modelFactory;
    private Vendedor vendedor;

    public void initialize() {
        modelFactory = ModelFactory.getInstance();
        marketplace = modelFactory.getMarketplace();
        cargarProductosEnMuro();
    }
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
        cargarProductosEnMuro();
    }

    private void cargarProductosEnMuro() {
        int fila = 0;
        int columna = 0;
        if (marketplace.getListaProductos().isEmpty()) {
            System.out.println("No hay productos para cargar.");
            return;
        }
        List<Producto> productosMuro;
        if (vendedor != null) {
            productosMuro = marketplace.obtenerProductosMuro(vendedor);
        } else {
            productosMuro = marketplace.getListaProductos();
        }
        if (productosMuro.isEmpty()) {
            System.out.println("No se encontraron productos para este vendedor o globalmente.");
            return;
        }
        gridPaneProductos.getChildren().clear();
        for (Producto producto : productosMuro) {
            VBox vbox = new VBox();
            vbox.setSpacing(10);
            vbox.setStyle("-fx-background-color: white; -fx-border-radius: 8px; -fx-padding: 10; -fx-background-insets: 0; -fx-border-color: #ddd; -fx-border-width: 1px;");
            DropShadow dropShadow = new DropShadow();
            dropShadow.setRadius(10);
            dropShadow.setOffsetX(0);
            dropShadow.setOffsetY(5);
            dropShadow.setColor(Color.color(0.5, 0.5, 0.5));
            vbox.setEffect(dropShadow);
            Text nombreProducto = new Text(producto.getNombre());
            nombreProducto.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-alignment: center;");
            ImageView imageView = new ImageView(producto.getImagen());
            imageView.setFitWidth(120);
            imageView.setFitHeight(120);
            imageView.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0.0, 0, 5);");
            Text precioProducto = new Text("$" + producto.getPrecio());
            precioProducto.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: green; -fx-alignment: center;");
            vbox.getChildren().addAll(nombreProducto, imageView, precioProducto);
            vbox.setOnMouseClicked(event -> mostrarDetallesProducto(producto));
            gridPaneProductos.add(vbox, columna, fila);
            columna++;
            if (columna > 2) {
                columna = 0;
                fila++;
            }
        }
    }

    private void mostrarDetallesProducto(Producto producto) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketplace/marketplace/productoDetails.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            ProductoDetailsViewController controller = loader.getController();
            controller.mostrarDetallesProducto(producto);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

