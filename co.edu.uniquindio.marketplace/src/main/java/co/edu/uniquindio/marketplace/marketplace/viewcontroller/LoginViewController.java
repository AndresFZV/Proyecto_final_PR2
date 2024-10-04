package co.edu.uniquindio.marketplace.marketplace.viewcontroller;

import co.edu.uniquindio.marketplace.marketplace.controller.VendedorController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

public class LoginViewController {

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField pwfLogin;

    @FXML
    private TextField txtUsuarioLogin;

    private VendedorController vendedorController;

    @FXML
    public void initialize() {
        vendedorController = new VendedorController();
        btnLogin.setOnAction(event -> iniciarSesion());
    }

    private void iniciarSesion() {
        String nombreUsuario = txtUsuarioLogin.getText();
        String password = pwfLogin.getText();
        if(validarAdmin(nombreUsuario, password)){
            cargarNuevaVista("/co/edu/uniquindio/marketplace/marketplace/panelControl.fxml");
        }
        else if(vendedorController.validarVendedor(nombreUsuario, password)) {
            cargarNuevaVista("/co/edu/uniquindio/marketplace/marketplace/panelControl.fxml");
        }
        else {
            mostrarAlerta(AlertType.ERROR, "Error de Inicio de Sesión", "Nombre de usuario o contraseña incorrectos.");
        }
    }

    // Método para mostrar alertas
    private void mostrarAlerta(AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Método para validar las credenciales del administrador
    private boolean validarAdmin(String nombreUsuario, String password) {
        return "AndresFZV".equals(nombreUsuario) && "1234567".equals(password);
    }

    // Método para cargar una nueva vista
    private void cargarNuevaVista(String rutaFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta(AlertType.ERROR, "Error al cargar la vista", "No se pudo cargar la nueva vista. " + e.getMessage());
        }
    }
}
