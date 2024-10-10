package co.edu.uniquindio.marketplace.marketplace.mapping.dto;

import javafx.scene.image.Image;

public record ProductoDto(
        String productoId,
        String nombre,
        String descripcion,
        Image imagen,
        String categoria,
        double precio,
        String estado) {
}
