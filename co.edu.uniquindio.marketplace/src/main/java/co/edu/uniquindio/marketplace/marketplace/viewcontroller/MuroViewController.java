package co.edu.uniquindio.marketplace.marketplace.viewcontroller;

import co.edu.uniquindio.marketplace.marketplace.factory.ModelFactory;
import co.edu.uniquindio.marketplace.marketplace.model.Marketplace;
import co.edu.uniquindio.marketplace.marketplace.model.Producto;
import co.edu.uniquindio.marketplace.marketplace.model.SolicitudAmistad;
import co.edu.uniquindio.marketplace.marketplace.model.Vendedor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

    private Vendedor vendedorActual;
    @FXML
    private GridPane gridPaneProductos;

    @FXML
    private ListView<Vendedor> listaVendedores;

    @FXML
    private TextField buscarVendedorField;

    @FXML
    private ListView<SolicitudAmistad> solicitudesPendientesListView;


    private Marketplace marketplace;
    private ModelFactory modelFactory;
    private Vendedor vendedor;

    public void initialize() {
        modelFactory = ModelFactory.getInstance();
        marketplace = modelFactory.getMarketplace();
        cargarProductosEnMuro();
        vendedorActual = obtenerVendedorActual();
        cargarVendedores();
    }


    private Vendedor obtenerVendedorActual() {

        return new Vendedor("Juan", "Pérez", "123", "Calle 123", "1234567890", "juan@mail.com", null);
    }


    private void cargarVendedores() {
        List<Vendedor> vendedores = marketplace.getListaVendedores();
        listaVendedores.getItems().clear();
        listaVendedores.getItems().addAll(vendedores);
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


    @FXML
    private void enviarSolicitudAmistad() {
        Vendedor seleccionado = listaVendedores.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {

            vendedorActual.enviarSolicitud(seleccionado);


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Solicitud de amistad");
            alert.setContentText("Solicitud enviada a " + seleccionado.getNombre());
            alert.showAndWait();


            actualizarSolicitudesPendientes();
        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setContentText("Por favor, selecciona un vendedor.");
            alert.showAndWait();
        }
    }

    private void actualizarSolicitudesPendientes() {

        solicitudesPendientesListView.getItems().clear();

        List<SolicitudAmistad> solicitudesPendientes = vendedorActual.obtenerSolicitudesPendientes();


        solicitudesPendientesListView.getItems().addAll(solicitudesPendientes);
    }



    @FXML
    private void aceptarSolicitudAmistad() {
        Vendedor seleccionado = listaVendedores.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {

            if (vendedorActual.tieneSolicitudPendiente(seleccionado)) {

                vendedorActual.aceptarSolicitud(seleccionado);


                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Solicitud de amistad aceptada");
                alert.setContentText("Solicitud aceptada de " + seleccionado.getNombre());
                alert.showAndWait();
            } else {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No hay solicitud pendiente");
                alert.setContentText("No hay una solicitud pendiente de amistad de " + seleccionado.getNombre());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setContentText("Por favor, selecciona un vendedor.");
            alert.showAndWait();
        }
    }


    @FXML
    private void rechazarSolicitudAmistad() {
        Vendedor seleccionado = listaVendedores.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {

            if (vendedorActual.tieneSolicitudPendiente(seleccionado)) {

                vendedorActual.rechazarSolicitud(seleccionado);


                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Solicitud de amistad rechazada");
                alert.setContentText("Solicitud rechazada de " + seleccionado.getNombre());
                alert.showAndWait();
            } else {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No hay solicitud pendiente");
                alert.setContentText("No hay una solicitud pendiente de amistad de " + seleccionado.getNombre());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setContentText("Por favor, selecciona un vendedor.");
            alert.showAndWait();
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

    @FXML
    private void buscarVendedores() {
        String textoBusqueda = buscarVendedorField.getText().toLowerCase().trim();
        if (!textoBusqueda.isEmpty()) {
            listaVendedores.getItems().clear();
            for (Vendedor vendedor : marketplace.getListaVendedores()) {
                if (vendedor.getNombre().toLowerCase().contains(textoBusqueda)) {
                    listaVendedores.getItems().add(vendedor);
                }
            }
        } else {
            cargarVendedores();
        }
    }
}
