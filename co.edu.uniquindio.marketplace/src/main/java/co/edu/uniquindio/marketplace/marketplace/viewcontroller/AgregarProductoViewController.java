package co.edu.uniquindio.marketplace.marketplace.viewcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class AgregarProductoViewController {

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnAgregarImagen;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnSalir;

    @FXML
    private ComboBox<?> cbCategoria;

    @FXML
    private ComboBox<?> cbEstado;

    @FXML
    private TableView<?> tablaProducto;

    @FXML
    private TableColumn<?, ?> tcCategoria;

    @FXML
    private TableColumn<?, ?> tcDescripcion;

    @FXML
    private TableColumn<?, ?> tcEstado;

    @FXML
    private TableColumn<?, ?> tcID;

    @FXML
    private TableColumn<?, ?> tcNombre;

    @FXML
    private TableColumn<?, ?> tcPrecio;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    void onSalir(ActionEvent event) {

    }
}
