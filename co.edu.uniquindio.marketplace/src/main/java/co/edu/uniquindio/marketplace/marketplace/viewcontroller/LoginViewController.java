package co.edu.uniquindio.marketplace.marketplace.viewcontroller;

import co.edu.uniquindio.marketplace.marketplace.controller.VendedorController;
import javafx.event.ActionEvent;
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
    private PrincipalViewController principalViewController;

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

    private void inicioSesion(){
        String nombreUsuario = txtUsuarioLogin.getText();
        String password = pwfLogin.getText();
        if(validarAdmin(nombreUsuario, password)){
            principalViewController.navegarDatos("/co/edu/uniquindio/marketplace/marketplace/panelControl.fxml");
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.close();
        }else if(vendedorController.validarVendedor(nombreUsuario, password)){
            principalViewController.navegarDatos("/co/edu/uniquindio/marketplace/marketplace/panelControl.fxml");
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.close();
        }else{
            principalViewController.mostrarAlerta(AlertType.ERROR, "Error de Inicio de Sesión",
                    "Nombre de usuario o contraseña incorrectos.");
        }
    }

    // Método para validar las credenciales del administrador
    private boolean validarAdmin(String nombreUsuario, String password) {
        return "AndresFZV".equals(nombreUsuario) && "1234567".equals(password);
    }

}
