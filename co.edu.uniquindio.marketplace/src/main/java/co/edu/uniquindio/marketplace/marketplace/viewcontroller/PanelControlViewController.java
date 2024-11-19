package co.edu.uniquindio.marketplace.marketplace.viewcontroller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.marketplace.marketplace.factory.ModelFactory;
import co.edu.uniquindio.marketplace.marketplace.model.Marketplace;
import co.edu.uniquindio.marketplace.marketplace.model.Vendedor;
import co.edu.uniquindio.marketplace.marketplace.utils.DataUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class PanelControlViewController {
    private static final int MAX_TABS = 10;

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TabPane panelControl;
    @FXML
    private Tab tabAdministrador;

    private PrincipalViewController principalViewController;
    private ModelFactory modelFactory = ModelFactory.getInstance();

    public void configurarVista(boolean esAdmin, String nombreUsuario) {
        if (esAdmin) {
            cargarPanelAdministrador();
            panelControl.getSelectionModel().select(tabAdministrador);
        } else {
            agregarTabVendedor(nombreUsuario);
            tabAdministrador.setDisable(true);
        }
    }

    private void cargarPanelAdministrador() {
        try {
            URL panelAdministrador = getClass().getResource("/co/edu/uniquindio/marketplace/marketplace/panelAdministrador.fxml");
            if (panelAdministrador == null) {
                throw new IOException("No se encontró la ruta");
            }
            FXMLLoader loader = new FXMLLoader(panelAdministrador);
            AnchorPane vistaAdministrador = loader.load();
            PanelAdminViewController adminController = loader.getController();
            adminController.setPanelControlViewController(this);
            tabAdministrador.setContent(vistaAdministrador);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        Marketplace marketplace = DataUtil.inicializarDatos();
        for (Vendedor vendedor : marketplace.getListaVendedores()) {
            agregarTabVendedor(vendedor.getUsuario().getNombreUsuario());
            modelFactory.obtenerProductosVendedor(vendedor);
        }
        cargarPanelAdministrador();
        principalViewController = new PrincipalViewController();
    }

    public void agregarTabVendedor(String nombreUsuario) {
        if (panelControl.getTabs().size() >= MAX_TABS) {
            principalViewController.mostrarAlerta(Alert.AlertType.WARNING, "Límite de pestañas", "No se pueden agregar más de " + MAX_TABS + " pestañas.");
            return;
        }
        if (nombreUsuario == null || nombreUsuario.isEmpty()) {
            principalViewController.mostrarAlerta(Alert.AlertType.ERROR, "Error", "El nombre de usuario debe estar lleno");
            return;
        }
        Vendedor vendedor = modelFactory.obtenerVendedorUsuario(nombreUsuario);
        if (vendedor == null) {
            principalViewController.mostrarAlerta(Alert.AlertType.ERROR, "Error", "Vendedor no encontrado");
            return;
        }
        Tab tabVendedor = new Tab(vendedor.getNombre());
        tabVendedor.setClosable(false);
        try {
            URL vistaProducto = getClass().getResource("/co/edu/uniquindio/marketplace/marketplace/PanelVendedor.fxml");
            if (vistaProducto == null) {
                throw new IOException("No se encontró la ruta");
            }
            FXMLLoader loader = new FXMLLoader(vistaProducto);
            AnchorPane vista = loader.load();
            PanelVendedorViewController vendedorController = loader.getController();
            vendedorController.setVendedor(vendedor);
            tabVendedor.setContent(vista);
        } catch (IOException e) {
            e.printStackTrace();
        }
        panelControl.getTabs().add(tabVendedor);
        panelControl.getSelectionModel().select(tabVendedor);
    }
}