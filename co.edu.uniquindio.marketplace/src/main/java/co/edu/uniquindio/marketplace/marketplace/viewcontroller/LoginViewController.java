package co.edu.uniquindio.marketplace.marketplace.viewcontroller;

import co.edu.uniquindio.marketplace.marketplace.controller.VendedorController;
//import co.edu.uniquindio.marketplace.marketplace.model.Sesion;
import co.edu.uniquindio.marketplace.marketplace.factory.ModelFactory;
import co.edu.uniquindio.marketplace.marketplace.model.Vendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginViewController {
    @FXML
    private Button btnLogin;
    @FXML
    private PasswordField pwfLogin;
    @FXML
    private TextField txtUsuarioLogin;
    private VendedorController vendedorController;
    private PrincipalViewController principalViewController;
    private final ModelFactory modelFactory = ModelFactory.getInstance();

    @FXML
    public void initialize() {
        principalViewController = new PrincipalViewController();
        vendedorController = new VendedorController();
        btnLogin.setOnAction(this::onIniciarSesion);
    }

    @FXML
    void onIniciarSesion(ActionEvent event) {
        inicioSesion();
    }

    // Método que se llama cuando el inicio de sesión es exitoso
    private void inicioSesion() {
        String nombreUsuario = txtUsuarioLogin.getText();
        String password = pwfLogin.getText();
        if (validarAdmin(nombreUsuario, password)) {
            // Indicamos que es un admin pasando true
            principalViewController.ingresarSesion("/co/edu/uniquindio/marketplace/marketplace/panelControl.fxml", true, nombreUsuario);
            cerrarVentana();
        } else {
            Vendedor vendedor = vendedorController.validarVendedor(nombreUsuario, password);
            if (vendedor != null) {
                modelFactory.setVendedor(vendedor);
                // Indicamos que es un vendedor pasando false
                principalViewController.ingresarSesion("/co/edu/uniquindio/marketplace/marketplace/panelControl.fxml", false, vendedor.getUsuario().getNombreUsuario());
                cerrarVentana();
            } else {
                principalViewController.mostrarAlerta(Alert.AlertType.ERROR, "Error de Inicio de Sesión",
                        "Nombre de usuario o contraseña incorrectos.");
            }
        }
    }

    private boolean validarAdmin(String nombreUsuario, String password) {
        return "admin".equals(nombreUsuario) && "admin123".equals(password);
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        stage.close();
    }
}
