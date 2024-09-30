package co.edu.uniquindio.marketplace.marketplace.viewcontroller;

import co.edu.uniquindio.marketplace.marketplace.controller.VendedorController;
import co.edu.uniquindio.marketplace.marketplace.mapping.dto.VendedorDto;
import co.edu.uniquindio.marketplace.marketplace.model.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AgregarVendedorViewController {

    private VendedorController vendedorController;
    private ObservableList<VendedorDto> listaVendedor = FXCollections.observableArrayList();
    private VendedorDto vendedorSeleccionado;

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnAgregar; // Asegúrate de que este botón esté conectado en tu FXML
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnNuevo;
    @FXML
    private Button btnSalir;

    @FXML
    private PasswordField pwfContrasena;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtdireccion;

    @FXML
    private TableView<VendedorDto> tablaVendedor;
    @FXML
    private TableColumn<VendedorDto, String> tcNombre;
    @FXML
    private TableColumn<VendedorDto, String> tcApellido;
    @FXML
    private TableColumn<VendedorDto, String> tcCedula;
    @FXML
    private TableColumn<VendedorDto, String> tcCorreo;
    @FXML
    private TableColumn<VendedorDto, String> tcDireccion;
    @FXML
    private TableColumn<VendedorDto, String> tcTelefono;
    @FXML
    private TableColumn<VendedorDto, String> tcUsuario;
    @FXML
    private TableColumn<VendedorDto, String> tcContrasena;

    @FXML
    void initialize() {
        vendedorController = new VendedorController();
        initView();
        btnNuevo.setOnAction(event -> onNuevoVendedor());
        btnAgregar.setOnAction(this::onAgregarVendedor); // Asegúrate de que btnAgregar esté conectado
        btnActualizar.setOnAction(this::onActualizarVendedor); // Asegúrate de que btnActualizar esté conectado
        btnEliminar.setOnAction(this::onEliminarVendedor); // Asegúrate de que btnEliminar esté conectado
        btnSalir.setOnAction(this::onSalir);
    }

    private void initView() {
        initDataBinding();
        obtenerVendedores();
        tablaVendedor.setItems(listaVendedor);
        listenerSelection();
    }

    private void obtenerVendedores() {
        listaVendedor.clear();
        listaVendedor.addAll(vendedorController.obtenerVendedor());
        tablaVendedor.setItems(listaVendedor);
    }

    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        tcApellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().apellido()));
        tcCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().cedula()));
        tcCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().correo()));
        tcDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().direccion()));
        tcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().telefono()));
        tcUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().usuario().getNombreUsuario()));
        tcContrasena.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().usuario().getPassword()));
    }

    private void listenerSelection() {
        tablaVendedor.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            vendedorSeleccionado = newSelection;
            mostrarInformacionVendedor(vendedorSeleccionado);
        });
    }

    private void mostrarInformacionVendedor(VendedorDto vendedorSeleccionado) {
        if (vendedorSeleccionado != null) {
            txtNombre.setText(vendedorSeleccionado.nombre());
            txtApellido.setText(vendedorSeleccionado.apellido());
            txtCedula.setText(vendedorSeleccionado.cedula());
            txtdireccion.setText(vendedorSeleccionado.direccion());
            txtCorreo.setText(vendedorSeleccionado.correo());
            txtTelefono.setText(vendedorSeleccionado.telefono());
            txtUsuario.setText(vendedorSeleccionado.usuario().getNombreUsuario());
            pwfContrasena.setText(vendedorSeleccionado.usuario().getPassword());
        }
    }

    public void onAgregarVendedor(ActionEvent actionEvent) {
        if (validarCampos()) {
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String cedula = txtCedula.getText();
            String direccion = txtdireccion.getText();
            String telefono = txtTelefono.getText();
            String correo = txtCorreo.getText();
            String nombreUsuario = txtUsuario.getText();
            String password = pwfContrasena.getText();
            Usuario usuario = new Usuario();
            usuario.setNombreUsuario(nombreUsuario);
            usuario.setPassword(password);
            boolean resultado = vendedorController.crearVendedor(nombre, apellido, cedula, direccion, telefono, correo, usuario);

            if (resultado) {
                obtenerVendedores();
                limpiarFormulario();
                showAlert("Éxito", "Vendedor agregado correctamente.");
            } else {
                showAlert("Error", "El vendedor ya existe.");
            }
        }
    }

    public void onActualizarVendedor(ActionEvent actionEvent) {
        if (vendedorSeleccionado != null && validarCampos()) {
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String cedula = txtCedula.getText();
            String direccion = txtdireccion.getText();
            String telefono = txtTelefono.getText();
            String correo = txtCorreo.getText();
            String nombreUsuario = txtUsuario.getText();
            String password = pwfContrasena.getText();
            Usuario usuario = new Usuario(nombreUsuario, password);
            boolean resultado = vendedorController.actualizarVendedor(nombre, apellido, cedula, direccion, telefono, correo, usuario);
            if (resultado) {
                obtenerVendedores();
                limpiarFormulario();
                showAlert("Éxito", "Vendedor actualizado correctamente.");
            } else {
                showAlert("Error", "Error al actualizar el vendedor.");
            }
        } else {
            showAlert("Advertencia", "No se ha seleccionado ningún vendedor o los campos están vacíos.");
        }
    }

    public void onEliminarVendedor(ActionEvent actionEvent) {
        if (vendedorSeleccionado != null) {
            String cedula = vendedorSeleccionado.cedula();
            boolean resultado = vendedorController.eliminarVendedor(cedula);
            if (resultado) {
                obtenerVendedores();
                limpiarFormulario();
                showAlert("Éxito", "Vendedor eliminado correctamente.");
            } else {
                showAlert("Error", "Error al eliminar el vendedor.");
            }
        } else {
            showAlert("Advertencia", "No se ha seleccionado ningún vendedor.");
        }
    }

    public void onNuevoVendedor() {
        limpiarFormulario();
        vendedorSeleccionado = null;
    }

    private void limpiarFormulario() {
        txtNombre.clear();
        txtApellido.clear();
        txtCedula.clear();
        txtdireccion.clear();
        txtCorreo.clear();
        txtTelefono.clear();
        txtUsuario.clear();
        pwfContrasena.clear();
    }

    @FXML
    void onSalir(ActionEvent event) {
        cargarNuevaVista("/co/edu/uniquindio/marketplace/marketplace/login.fxml");
    }

    private boolean validarCampos() {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String cedula = txtCedula.getText();
        String direccion = txtdireccion.getText();
        String correo = txtCorreo.getText();
        String telefono = txtTelefono.getText();
        String usuario = txtUsuario.getText();
        String contrasena = pwfContrasena.getText();
        if (nombre.isEmpty() || apellido.isEmpty() || cedula.isEmpty() || direccion.isEmpty() ||
                correo.isEmpty() || telefono.isEmpty() || usuario.isEmpty() || contrasena.isEmpty()) {
            showAlert("Advertencia", "Por favor, complete todos los campos.");
            return false;
        }
        if (contrasena.length() < 6) {
            showAlert("Advertencia", "La contraseña debe tener al menos 6 caracteres.");
            return false;
        }
        if (!correo.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            showAlert("Advertencia", "Por favor, ingrese un correo electrónico válido.");
            return false;
        }

        return true;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void cargarNuevaVista(String rutaFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();
            Stage stage = (Stage) btnSalir.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error al cargar la vista", "No se pudo cargar la nueva vista. " + e.getMessage());
        }
    }

    // Método para mostrar alertas
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
