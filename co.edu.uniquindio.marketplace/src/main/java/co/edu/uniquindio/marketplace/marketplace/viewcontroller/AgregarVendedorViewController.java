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

    private void agregarVendedor(){
        VendedorDto vendedorDto = crearVendedorDto();
        if(validarDatos(vendedorDto)){
            if(vendedorController.crearVendedor(vendedorDto)){
                listaVendedor.add(vendedorDto);
                limpiarFormulario();
            }else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "Vendedor no agregado");
            }
        }
    }

    private boolean validarDatos(VendedorDto vendedorDto) {
        if (vendedorDto.nombre().isEmpty() || vendedorDto.apellido().isEmpty()
                || vendedorDto.cedula().isEmpty() || vendedorDto.direccion().isEmpty()
                || vendedorDto.correo().isEmpty() || vendedorDto.telefono().isEmpty()
                || vendedorDto.usuario().getNombreUsuario().isEmpty() || vendedorDto.usuario().getPassword().isEmpty()) {
            showAlert("Advertencia", "Complete todos los campos.");
            return false;
        }
        if (vendedorDto.usuario().getPassword().length() < 6) {
            showAlert("Advertencia", "La contraseña debe tener al menos 6 caracteres.");
            return false;
        }
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if (!vendedorDto.correo().matches(emailRegex)) {
            showAlert("Advertencia", "El correo electrónico no es válido.");
            return false;
        }
        if (!vendedorDto.cedula().matches("\\d+")) {
            showAlert("Advertencia", "La cédula solo debe contener números.");
            return false;
        }
        if (!vendedorDto.telefono().matches("\\d+")) {
            showAlert("Advertencia", "El teléfono solo debe contener números.");
            return false;
        }
        if (vendedorDto.telefono().length() < 10) {
            showAlert("Advertencia", "El teléfono debe tener al menos 10 digitos para que sea valido.");
            return false;
        }
        return true;
    }


    private VendedorDto crearVendedorDto(){
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(txtNombre.getText());
        usuario.setPassword(pwfContrasena.getText());
        return new VendedorDto(
                txtNombre.getText(),
                txtApellido.getText(),
                txtCedula.getText(),
                txtdireccion.getText(),
                txtTelefono.getText(),
                txtCorreo.getText(),
                usuario
        );
    }

    public void onAgregarVendedor(ActionEvent actionEvent) {
        agregarVendedor();
    }

    private void actualizarVendedor() {
        if (vendedorSeleccionado != null) {
            VendedorDto vendedorDto = crearVendedorDto();
            if (validarDatos(vendedorDto)) {
                if (vendedorController.actualizarVendedor(vendedorDto)) {
                    listaVendedor.set(listaVendedor.indexOf(vendedorSeleccionado), vendedorDto);
                    limpiarFormulario();
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Vendedor actualizado exitosamente", "Vendedor actualizado");
                } else {
                    mostrarAlerta(Alert.AlertType.ERROR, "Error", "Vendedor no actualizado");
                }
            }
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Seleccione un vendedor para actualizar.");
        }
    }

    public void onActualizarVendedor(ActionEvent actionEvent) {
        actualizarVendedor();
    }


    private void eliminarVendedor() {
        if (vendedorSeleccionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación de Eliminación");
            alert.setHeaderText("¿Estás seguro de que deseas eliminar al vendedor?");
            alert.setContentText("Esta acción no se puede deshacer.");
            ButtonType botonSi = new ButtonType("Sí");
            ButtonType botonNo = new ButtonType("No");
            alert.getButtonTypes().setAll(botonSi, botonNo);
            Optional<ButtonType> resultado = alert.showAndWait();
            if (resultado.isPresent() && resultado.get() == botonSi) {
                if (vendedorController.eliminarVendedor(vendedorSeleccionado.cedula())) {
                    listaVendedor.remove(vendedorSeleccionado);
                    vendedorSeleccionado = null;
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Vendedor eliminado con éxito.");
                } else {
                    mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo eliminar el vendedor.");
                }
            } else {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Cancelado", "Eliminación del vendedor cancelada.");
            }
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Seleccione un vendedor para eliminar.");
        }
    }

    public void onEliminarVendedor(ActionEvent actionEvent) {
        eliminarVendedor();
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
