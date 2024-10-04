package co.edu.uniquindio.marketplace.marketplace.viewcontroller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class PrincipalViewController {

    public Optional<ButtonType> mostrarConfirmacion(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        ButtonType botonSi = new ButtonType("Sí");
        ButtonType botonNo = new ButtonType("No");
        alert.getButtonTypes().setAll(botonSi, botonNo);
        return alert.showAndWait();
    }

    // Método para mostrar alertas (ya existente)
    public void mostrarAlerta(Alert.AlertType tipo, String title, String content) {
        Alert alert = new Alert(tipo);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
        // Método para mostrar alerta de confirmación
        public boolean mostrarAlertaConfirmacion(String titulo, String mensaje) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(titulo);
            alert.setHeaderText(null);
            alert.setContentText(mensaje);

            // Botones de confirmación
            ButtonType botonSi = new ButtonType("Sí");
            ButtonType botonNo = new ButtonType("No");
            alert.getButtonTypes().setAll(botonSi, botonNo);

            // Mostrar alerta y devolver el resultado
            Optional<ButtonType> resultado = alert.showAndWait();
            return resultado.isPresent() && resultado.get() == botonSi;
        }

        // Método para cargar una nueva vista
        public void navegarDatos(String nombreArchivoFxml) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreArchivoFxml));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
}

