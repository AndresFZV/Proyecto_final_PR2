package co.edu.uniquindio.marketplace.marketplace.services;

import co.edu.uniquindio.marketplace.marketplace.mapping.dto.ProductoDto;
import co.edu.uniquindio.marketplace.marketplace.model.Producto;
import javafx.collections.ObservableList;

import java.io.File;

public interface IReporte {
    void generarReporte();
    ObservableList<ProductoDto> obtenerProductosReporte();
    void guardarReporte(File archivo, String contenido);
}
