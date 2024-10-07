package co.edu.uniquindio.marketplace.marketplace.viewcontroller;

import co.edu.uniquindio.marketplace.marketplace.controller.VendedorController;
import co.edu.uniquindio.marketplace.marketplace.mapping.dto.VendedorDto;
import co.edu.uniquindio.marketplace.marketplace.model.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AgregarVendedorViewController {

    private VendedorController vendedorController;
    private ObservableList<VendedorDto> listaVendedor = FXCollections.observableArrayList();
    private VendedorDto vendedorSeleccionado;
    private PrincipalViewController principalViewController;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button btnActualizar, btnAgregar, btnEliminar, btnNuevo, btnSalir;
    @FXML
    private PasswordField pwfContrasena;
    @FXML
    private TextField txtApellido, txtCedula, txtCorreo, txtNombre, txtTelefono, txtUsuario, txtdireccion;
    @FXML
    private TableView<VendedorDto> tablaVendedor;
    @FXML
    private TableColumn<VendedorDto, String> tcNombre, tcApellido,
            tcCedula, tcCorreo, tcDireccion, tcTelefono, tcUsuario, tcContrasena;

    @FXML
    void initialize() {
        vendedorController = new VendedorController();
        principalViewController = new PrincipalViewController();
        initView();
        btnNuevo.setOnAction(event -> onNuevoVendedor());
        btnAgregar.setOnAction(this::onAgregarVendedor);
        btnActualizar.setOnAction(this::onActualizarVendedor);
        btnEliminar.setOnAction(this::onEliminarVendedor);
        btnSalir.setOnAction(this::onSalir);
    }

    private void initView() {
        initDataBinding();
        obtenerVendedores();
        tablaVendedor.setItems(listaVendedor);
        listenerSelection();
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

    private void obtenerVendedores() {
        listaVendedor.clear();
        listaVendedor.addAll(vendedorController.obtenerVendedor());
        tablaVendedor.setItems(listaVendedor);
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

    private VendedorDto crearVendedorDto() {
        Usuario usuario;
        if (vendedorSeleccionado != null) {
            usuario = vendedorSeleccionado.usuario();
        } else {
            usuario = new Usuario();
        }
        usuario.setNombreUsuario(txtUsuario.getText());
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

    private void agregarVendedor() {
        VendedorDto vendedorDto = crearVendedorDto();
        if (validarDatos(vendedorDto)) {
            if (vendedorController.crearVendedor(vendedorDto)) {
                listaVendedor.add(vendedorDto);
                principalViewController.mostrarAlerta(Alert.AlertType.INFORMATION,
                        "Éxito", "Vendedor agregado");
                tablaVendedor.getSelectionModel().clearSelection();
                limpiarFormulario();
            } else {
                principalViewController.mostrarAlerta(Alert.AlertType.ERROR,
                        "Error", "Vendedor no agregado");
            }
        }
    }

    private void actualizarVendedor() {
        if (vendedorSeleccionado != null) {
            VendedorDto vendedorDto = crearVendedorDto();
            if (validarDatos(vendedorDto)) {
                if (vendedorController.actualizarVendedor(vendedorDto)) {
                    listaVendedor.set(listaVendedor.indexOf(vendedorSeleccionado), vendedorDto);
                    tablaVendedor.getSelectionModel().clearSelection();
                    limpiarFormulario();
                    principalViewController.mostrarAlerta(Alert.AlertType.INFORMATION,
                            "Vendedor actualizado", "Vendedor actualizado exitosamente");
                } else {
                    principalViewController.mostrarAlerta(Alert.AlertType.ERROR,
                            "Error", "Vendedor no actualizado");
                }
            }
        } else {
            principalViewController.mostrarAlerta(Alert.AlertType.WARNING,
                    "Advertencia", "Seleccione un vendedor para actualizar.");
        }
    }

    private void eliminarVendedor() {
        if (vendedorSeleccionado != null) {
            if (principalViewController.mostrarAlertaConfirmacion(
                    "Confirmación de Eliminación", "¿Estás seguro de que deseas eliminar al vendedor?")) {
                if (vendedorController.eliminarVendedor(vendedorSeleccionado.cedula())) {
                    listaVendedor.remove(vendedorSeleccionado);
                    tablaVendedor.refresh();
                    vendedorSeleccionado = null;
                    tablaVendedor.getSelectionModel().clearSelection();
                    limpiarFormulario();
                    principalViewController.mostrarAlerta(Alert.AlertType.INFORMATION,
                            "Éxito", "Vendedor eliminado con éxito.");
                } else {
                    principalViewController.mostrarAlerta(Alert.AlertType.ERROR,
                            "Error", "No se pudo eliminar el vendedor.");
                }
            }
        } else {
            principalViewController.mostrarAlerta(Alert.AlertType.WARNING,
                    "Advertencia", "Seleccione un vendedor para eliminar.");
        }
    }

    public void onNuevoVendedor() {
        limpiarFormulario();
        vendedorSeleccionado = null;
        tablaVendedor.getSelectionModel().clearSelection();
    }

    public void onAgregarVendedor(ActionEvent actionEvent) {
        agregarVendedor();
    }

    public void onActualizarVendedor(ActionEvent actionEvent) {
        actualizarVendedor();
    }

    public void onEliminarVendedor(ActionEvent actionEvent) {
        eliminarVendedor();
    }

    @FXML
     public void onSalir(ActionEvent event) {
        principalViewController.navegarDatos("/co/edu/uniquindio/marketplace/marketplace/login.fxml");
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }

    private void listenerSelection() {
        tablaVendedor.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            vendedorSeleccionado = newSelection;
            mostrarInformacionVendedor(vendedorSeleccionado);
        });
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

    private boolean validarDatos(VendedorDto vendedorDto) {
        if (vendedorDto.nombre().isEmpty() || vendedorDto.apellido().isEmpty()
                || vendedorDto.cedula().isEmpty() || vendedorDto.direccion().isEmpty()
                || vendedorDto.correo().isEmpty() || vendedorDto.telefono().isEmpty()
                || vendedorDto.usuario().getNombreUsuario().isEmpty() || vendedorDto.usuario().getPassword().isEmpty()) {
            principalViewController.mostrarAlerta(Alert.AlertType.WARNING,
                    "Advertencia", "Complete todos los campos.");
            return false;
        }
        if (vendedorDto.usuario().getPassword().length() < 6) {
            principalViewController.mostrarAlerta(Alert.AlertType.WARNING,
                    "Advertencia", "La contraseña debe tener al menos 6 caracteres.");
            return false;
        }
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if (!vendedorDto.correo().matches(emailRegex)) {
            principalViewController.mostrarAlerta(Alert.AlertType.WARNING,
                    "Advertencia", "El correo electrónico no es válido.");
            return false;
        }
        if (!vendedorDto.cedula().matches("\\d+")) {
            principalViewController.mostrarAlerta(Alert.AlertType.WARNING,
                    "Advertencia", "La cédula solo debe tener números.");
            return false;
        }
        if (!vendedorDto.telefono().matches("\\d+")) {
            principalViewController.mostrarAlerta(Alert.AlertType.WARNING,
                    "Advertencia", "El teléfono solo debe tener números.");
            return false;
        }
        if (vendedorDto.telefono().length() < 10) {
            principalViewController.mostrarAlerta(Alert.AlertType.WARNING,
                    "Advertencia", "El teléfono debe tener al menos 10 dígitos.");
            return false;
        }
        return true;
    }
}
