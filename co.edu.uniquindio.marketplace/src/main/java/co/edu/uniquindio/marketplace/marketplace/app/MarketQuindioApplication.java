package co.edu.uniquindio.marketplace.marketplace.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MarketQuindioApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MarketQuindioApplication.class.getResource("/co/edu/uniquindio/marketplace/marketplace/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("MARKETQUINDÍO");
        stage.setResizable(false);
        Image icono = new Image(getClass().getResourceAsStream("/img/carritoIcono.png"));
        stage.getIcons().add(icono);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}