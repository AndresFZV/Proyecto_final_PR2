package co.edu.uniquindio.marketplace.marketplace.viewcontroller;

import co.edu.uniquindio.marketplace.marketplace.controller.ProductoController;
import co.edu.uniquindio.marketplace.marketplace.mapping.dto.ProductoDto;
import co.edu.uniquindio.marketplace.marketplace.model.Enum.Categoria;
import co.edu.uniquindio.marketplace.marketplace.model.Enum.Estado;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Random;

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

    private File archivoImagen;

    @FXML
    private ImageView previsualizarImagen;

    @FXML
    private ComboBox<Categoria> cbCategoria;

    @FXML
    private ComboBox<Estado> cbEstado;

    @FXML
    private TableView<ProductoDto> tablaProducto;

    @FXML
    private TableColumn<ProductoDto, String> tcID, tcNombre, tcPrecio, tcDescripcion, tcCategoria, tcEstado;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    private ProductoDto productoSeleccionado;
    private PrincipalViewController principalViewController;
    private ProductoController productoController;
    private ObservableList<ProductoDto> listaProductos = FXCollections.observableArrayList();
    private final ObservableList<Categoria> listaCategorias = FXCollections.observableArrayList();
    private final ObservableList<Estado> listaEstados = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        productoController = new ProductoController();
        principalViewController = new PrincipalViewController();
        initView();
        llenarCbCategoria();
        llenarCbEstado();
        btnNuevo.setOnAction(this::onNuevoProducto);
        btnAgregar.setOnAction(this::onAgregarProducto);
        btnActualizar.setOnAction(this::onActualizarProducto);
        btnEliminar.setOnAction(this::onEliminarProducto);
        btnSalir.setOnAction(this::onSalir);
        btnAgregarImagen.setOnAction(this::onAgregarImagen);
    }

    private void llenarCbEstado() {
        listaEstados.setAll(Estado.values());
        cbEstado.setItems(listaEstados);
    }

    private void llenarCbCategoria() {
        listaCategorias.setAll(Categoria.values());
        cbCategoria.setItems(listaCategorias);
    }

    private void initView() {
        initDataBinding();
        obtenerProductos();
        tablaProducto.setItems(listaProductos);
        listenerSelection();
    }

    private void listenerSelection() {
        tablaProducto.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            productoSeleccionado = newSelection;
            if (productoSeleccionado != null) {
                mostrarInformacionProductos(productoSeleccionado);
                mostrarImagenProducto(productoSeleccionado);
            } else {
                limpiarFormulario();
            }
        });
    }

    private void initDataBinding() {
        tcID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().productoId()));
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        tcDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().descripcion()));
        tcCategoria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().categoria()));
        tcEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().estado()));
        tcPrecio.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().precio())));
    }

    private void obtenerProductos() {
        listaProductos.clear();
        listaProductos.addAll(productoController.obtenerProducto());
        tablaProducto.setItems(listaProductos);
    }

    private void mostrarInformacionProductos(ProductoDto productoSeleccionado) {
        txtID.setText(productoSeleccionado.productoId());
        txtNombre.setText(productoSeleccionado.nombre());
        txtDescripcion.setText(productoSeleccionado.descripcion());
        txtPrecio.setText(String.valueOf(productoSeleccionado.precio()));
    }

    private void mostrarImagenProducto(ProductoDto productoSeleccionado){
        if(productoSeleccionado != null && productoSeleccionado.imagen() != null){
            previsualizarImagen.setImage(productoSeleccionado.imagen());
        }else{
            previsualizarImagen.setImage(null);
        }
    }

    private ProductoDto crearProductoDto() {
        String idProducto = (productoSeleccionado != null) ? productoSeleccionado.productoId() : crearIdProducto();
        Image imagenProducto = (archivoImagen != null) ? new Image(archivoImagen.toURI().toString()) : null;
        Categoria categoria = cbCategoria.getValue();
        Estado estado = cbEstado.getValue();
        if (categoria == null) {
            principalViewController.mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Seleccione una categoría.");
            return null;
        }
        if (estado == null) {
            principalViewController.mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Seleccione un estado.");
            return null;
        }
        if (archivoImagen == null) {
            principalViewController.mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Seleccione una imagen para el producto.");
            return null;
        }
        ProductoDto productoDto = new ProductoDto(
                idProducto,
                txtNombre.getText(),
                txtDescripcion.getText(),
                imagenProducto,
                categoria.toString(), //
                parsePrecio(txtPrecio.getText()),
                estado.toString()
        );
        if (!validarProducto(productoDto)) {
            return null;
        }
        return productoDto;
    }

    private void agregarProducto() {
        ProductoDto productoDto = crearProductoDto();
        if (productoDto == null) {
            return;
        }
        if (validarProducto(productoDto)) {
            if (productoController.crearProducto(productoDto)) {
                listaProductos.add(productoDto);
                principalViewController.mostrarAlerta(Alert.AlertType.INFORMATION,
                        "Éxito", "Producto agregado");
                tablaProducto.getSelectionModel().clearSelection();
                limpiarFormulario();
            } else {
                principalViewController.mostrarAlerta(Alert.AlertType.ERROR,
                        "Error", "Producto no agregado");
            }
        }
    }

    private void actualizarProducto() {
        if (productoSeleccionado != null) {
            ProductoDto productoDto = crearProductoDto();
            if (validarProducto(productoDto)) {
                if (productoController.actualizarProducto(productoDto)) {
                    listaProductos.set(listaProductos.indexOf(productoSeleccionado), productoDto);
                    tablaProducto.refresh();
                    tablaProducto.getSelectionModel().clearSelection();
                    limpiarFormulario();
                    principalViewController.mostrarAlerta(Alert.AlertType.INFORMATION, "Producto actualizado", "Producto actualizado exitosamente");
                } else {
                    principalViewController.mostrarAlerta(Alert.AlertType.ERROR, "Error", "Producto no actualizado");
                }
            }
        } else {
            principalViewController.mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Seleccione un producto para actualizar.");
        }
    }

    private void eliminarProducto() {
        if (productoSeleccionado != null) {
            if (principalViewController.mostrarAlertaConfirmacion(
                    "Confirmación de eliminación", "¿Estás seguro de que desear eliminar el producto?")) {
                if (productoController.eliminarProducto(productoSeleccionado.productoId())) {
                    listaProductos.remove(productoSeleccionado);
                    tablaProducto.refresh();
                    productoSeleccionado = null;
                    tablaProducto.getSelectionModel().clearSelection();
                    limpiarFormulario();
                    principalViewController.mostrarAlerta(Alert.AlertType.INFORMATION,
                            "Éxito", "Producto eliminado con éxito.");
                } else {
                    principalViewController.mostrarAlerta(Alert.AlertType.ERROR,
                            "Error", "No se pudo eliminar el producto.");
                }
            }
        } else {
            principalViewController.mostrarAlerta(Alert.AlertType.WARNING,
                    "Advertencia", "Seleccione un producto para eliminar.");
        }
    }

    private void seleccionarImagen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccione la imagen del producto");
        fileChooser.setInitialDirectory(new File("src/main/resources/img/productos"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imagenes",
                        "*.jpg", "*.png", "*.jpeg"));
        archivoImagen = fileChooser.showOpenDialog(null);
        if (archivoImagen != null) {
            Image imagen = new Image(archivoImagen.toURI().toString());
            previsualizarImagen.setImage(imagen);
        }
    }

    private void cerrarSesion() {
        principalViewController.navegarDatos("/co/edu/uniquindio/marketplace/marketplace/login.fxml");
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }

    private void limpiarCamposTexto() {
        limpiarFormulario();
        productoSeleccionado = null;
        tablaProducto.getSelectionModel().clearSelection();
    }

    @FXML
    void onNuevoProducto(ActionEvent actionEvent) {
        limpiarCamposTexto();
    }

    @FXML
    void onAgregarProducto(ActionEvent actionEvent) {
        agregarProducto();
    }

    @FXML
    void onActualizarProducto(ActionEvent actionEvent) {
        actualizarProducto();
    }

    @FXML
    void onEliminarProducto(ActionEvent actionEvent) {
        eliminarProducto();
    }

    @FXML
    void onSalir(ActionEvent event) {
        cerrarSesion();
    }

    @FXML
    void onAgregarImagen(ActionEvent event) {
        seleccionarImagen();
    }

    private void limpiarFormulario() {
        txtID.clear();
        txtNombre.clear();
        txtDescripcion.clear();
        txtPrecio.clear();
        previsualizarImagen.setImage(null);
        cbCategoria.getSelectionModel().clearSelection();
        cbEstado.getSelectionModel().clearSelection();
    }

    private boolean validarProducto(ProductoDto productoDto) {
        if (productoDto.nombre() == null || productoDto.nombre().isEmpty()) {
            principalViewController.mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "El nombre es obligatorio.");
            return false;
        }
        if (productoDto.descripcion() == null || productoDto.descripcion().isEmpty()) {
            principalViewController.mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "La descripción es obligatoria.");
            return false;
        }
        if (productoDto.categoria() == null || productoDto.categoria().isEmpty()) {
            principalViewController.mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "La categoría es obligatoria.");
            return false;
        }
        if (productoDto.estado() == null || productoDto.estado().isEmpty()) {
            principalViewController.mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "El estado es obligatorio.");
            return false;
        }
        return true;
    }

    private double parsePrecio(String precioStr) {
        try {
            return Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            principalViewController.mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "El precio debe ser válid, automáticamente se pondrá en 0.");
            return 0;
        }
    }

    private String crearIdProducto() {
        Random random = new Random();
        return String.valueOf(random.nextInt(10000));
    }
}
