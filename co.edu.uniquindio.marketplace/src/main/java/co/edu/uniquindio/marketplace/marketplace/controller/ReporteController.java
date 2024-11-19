package co.edu.uniquindio.marketplace.marketplace.controller;

import co.edu.uniquindio.marketplace.marketplace.factory.ModelFactory;
import co.edu.uniquindio.marketplace.marketplace.mapping.dto.ProductoDto;
import javafx.collections.ObservableList;

import java.io.File;

public class ReporteController {

    private ModelFactory modelFactory;

    public ReporteController(){
        modelFactory = ModelFactory.getInstance();
    }

    public void generarReporte() {
        modelFactory.generarReporte();
    }

    public ObservableList<ProductoDto> obtenerProductosReporte() {
        return modelFactory.obtenerProductosReporte();
    }

    public void guardarReporte(File archivo, String contenido) {
        modelFactory.guardarReporte(archivo, contenido);
    }
}
