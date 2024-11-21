package co.edu.uniquindio.marketplace.marketplace.viewcontroller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import co.edu.uniquindio.marketplace.marketplace.controller.ProductoController;
import co.edu.uniquindio.marketplace.marketplace.controller.ReporteController;
import co.edu.uniquindio.marketplace.marketplace.controller.VendedorController;
import co.edu.uniquindio.marketplace.marketplace.factory.ModelFactory;
import co.edu.uniquindio.marketplace.marketplace.mapping.dto.ProductoDto;
import co.edu.uniquindio.marketplace.marketplace.model.Producto;
import co.edu.uniquindio.marketplace.marketplace.model.Vendedor;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ReportesViewController {

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnInfoAdmin;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnRefrescarTabla;

    @FXML
    private Button btnProductosLikes;

    @FXML
    private Button btnReporteGeneral;

    @FXML
    private Button btnBuscarContactos;

    @FXML
    private Button btnBuscarFecha;

    @FXML
    private DatePicker dtpFecha;

    @FXML
    private ListView<Vendedor> listViewContactos;

    @FXML
    private ListView<String> listViewProductosFecha;

    @FXML
    private TableView<ProductoDto> tablaProductos;

    @FXML
    private TableColumn<ProductoDto, String> tcID, tcNombre, tcPrecio, tcDescripcion, tcCategoria, tcEstado;

    @FXML
    private TextField txtProductos;

    @FXML
    private TextField txtVerContactos;

    private ObservableList<ProductoDto> listaProductosReportes = FXCollections.observableArrayList();
    private ProductoController productoController = new ProductoController();
    private VendedorController vendedorController = new VendedorController();
    private PrincipalViewController principalViewController = new PrincipalViewController();
    private ReporteController reporteController = new ReporteController();

    private ModelFactory modelFactory = ModelFactory.getInstance();

    @FXML
    void initialize() {
        initView();
        btnBuscar.setOnAction(this::onBuscar);
        btnRefrescarTabla.setOnAction(this::onLimpiarTabla);
        btnCerrarSesion.setOnAction(this::onCerrarSesion);
        btnBuscarFecha.setOnAction(this::onBuscarFecha);
        btnBuscarContactos.setOnAction(this::onBuscarContactos);
        btnProductosLikes.setOnAction(this::onMostrarGraficaLikes);
        btnReporteGeneral.setOnAction(this::onGenerarReporte);
    }

    private void initView() {
        initDataBinding();
    }

    private void initDataBinding() {
        tcID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().productoId()));
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        tcDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().descripcion()));
        tcCategoria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().categoria()));
        tcEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().estado()));
        tcPrecio.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().precio())));
    }

    @FXML
    void onBuscar(ActionEvent event) {
        buscarProductosVendedor();
    }
    @FXML
    void onLimpiarTabla(ActionEvent event) {
        refrescarTabla();
    }
    @FXML
    void onCerrarSesion(ActionEvent event) {
        cerrarSesion();
        modelFactory.cerrarSesion();
    }

    @FXML
    void onBuscarContactos(ActionEvent event) {
        buscarContactos();
    }

    @FXML
    void onMostrarGraficaLikes(ActionEvent event) {
        mostrarGrafica();
    }

    @FXML
    void onGenerarReporte(ActionEvent event) {
        generarReportes();
    }

    private void generarReportes() {
        try {
            reporteController.generarReporte();
        } catch (Exception e) {
            principalViewController.mostrarAlerta(Alert.AlertType.ERROR,
                    "Error al generar reporte", "Hubo un problema al generar el reporte: " + e.getMessage());
        }
    }

    private void mostrarGrafica() {
        principalViewController.navegarDatos("/co/edu/uniquindio/marketplace/marketplace/graficaProductos.fxml");
    }

    private void buscarContactos() {
        String nombreUsuario = txtVerContactos.getText().trim();
        if (nombreUsuario.isEmpty()) {
            principalViewController.mostrarAlerta(Alert.AlertType.WARNING, "Campo vacío", "Por favor, ingrese un nombre de usuario.");
            listViewContactos.setItems(FXCollections.observableArrayList());
            return;
        }
        List<Vendedor> vendedoresAsociados = vendedorController.obtenerVendedoresAsociados(nombreUsuario);
        if (vendedoresAsociados.isEmpty()) {
            principalViewController.mostrarAlerta(Alert.AlertType.ERROR, "Error al buscar", "No se encontraron vendedores asociados con ese usuario.");
            listViewContactos.setItems(FXCollections.observableArrayList());
            return;
        }
        ObservableList<Vendedor> vendedoresObservable = FXCollections.observableArrayList(vendedoresAsociados);
        listViewContactos.setItems(vendedoresObservable);
    }


    @FXML
    void onBuscarFecha(ActionEvent event) {
        buscarFechas();
    }

    private void buscarFechas() {
        LocalDate fechaSeleccionada = dtpFecha.getValue();
        if (fechaSeleccionada == null) {
            principalViewController.mostrarAlerta(Alert.AlertType.ERROR, "Error al buscar", "Debe seleccionar una fecha.");
            return;
        }
        List<Producto> productosFiltrados = productoController.obtenerProductosFecha(fechaSeleccionada);
        if (productosFiltrados.isEmpty()) {
            principalViewController.mostrarAlerta(Alert.AlertType.WARNING, "Sin resultados", "No se encontraron productos para la fecha seleccionada.");
            return;
        }
        ObservableList<String> productosNombres = FXCollections.observableArrayList();
        for (Producto producto : productosFiltrados) {
            productosNombres.add(producto.getNombre());
        }
        listViewProductosFecha.setItems(productosNombres);
    }


    private void cerrarSesion() {
        principalViewController.navegarDatos("/co/edu/uniquindio/marketplace/marketplace/login.fxml");
        Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
        stage.close();
    }

    private void refrescarTabla() {
        tablaProductos.getItems().clear();
        txtProductos.setText("");
    }

    private void buscarProductosVendedor() {
        String nombreUsuario = txtProductos.getText().trim();
        if (!nombreUsuario.isEmpty()) {
            Vendedor vendedor = productoController.obtenerVendedorUsuario(nombreUsuario);
            if (vendedor != null) {
                List<ProductoDto> productos = productoController.obtenerProductosVendedor(vendedor);
                listaProductosReportes.clear();
                listaProductosReportes.addAll(productos);
                tablaProductos.setItems(listaProductosReportes);
            } else {
                principalViewController.mostrarAlerta(Alert.AlertType.ERROR, "Error al buscar", "Vendedor no encontrado");
            }
        }
    }
}
