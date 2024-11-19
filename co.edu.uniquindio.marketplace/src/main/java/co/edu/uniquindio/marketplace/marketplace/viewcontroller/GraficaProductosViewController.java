package co.edu.uniquindio.marketplace.marketplace.viewcontroller;

import co.edu.uniquindio.marketplace.marketplace.controller.ProductoController;
import co.edu.uniquindio.marketplace.marketplace.model.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.List;

public class GraficaProductosViewController {

    @FXML
    private BarChart<String, Number> graficaProductosLikes;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Button btnRegresar;
    private ProductoController productoController = new ProductoController();
    @FXML
    void initialize() {
        cargarGraficaTopProductos();
        btnRegresar.setOnAction(this::onRegresar);
    }

    private void cargarGraficaTopProductos() {
        List<Producto> productosTopLikes = productoController.obtenerTopProductos(10);
        if (productosTopLikes.isEmpty()) {
            graficaProductosLikes.getData().clear();
            return;
        }
        xAxis.setLabel("Productos");
        yAxis.setLabel("Likes");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Likes por producto");
        for (Producto producto : productosTopLikes) {
            series.getData().add(new XYChart.Data<>(producto.getNombre(), producto.getPublicacion().getLikes()));
        }
        graficaProductosLikes.getData().add(series);
    }

    @FXML
    private void onRegresar(ActionEvent event) {
        regresar();
    }

    private void regresar() {
        Stage stage = (Stage) btnRegresar.getScene().getWindow();
        stage.close();
    }
}
