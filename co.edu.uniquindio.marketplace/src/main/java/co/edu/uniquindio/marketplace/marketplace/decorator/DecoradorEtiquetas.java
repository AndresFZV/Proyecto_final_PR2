package co.edu.uniquindio.marketplace.marketplace.decorator;

import co.edu.uniquindio.marketplace.marketplace.model.Producto;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class DecoradorEtiquetas extends Producto {
    private Producto productoOriginal;
    private List<String> etiquetas;

    public DecoradorEtiquetas(Producto productoOriginal, String... etiquetas) {
        super(productoOriginal.getProductoId(), productoOriginal.getNombre(), productoOriginal.getDescripcion(),
                productoOriginal.getImagen(), productoOriginal.getCategoria(), productoOriginal.getPrecio());
        this.productoOriginal = productoOriginal;
        this.etiquetas = new ArrayList<>(productoOriginal.getEtiquetas());
        for (String etiqueta : etiquetas) {
            this.etiquetas.add(etiqueta);
        }
    }

    @Override
    public List<String> getEtiquetas() {
        return etiquetas;
    }
}
