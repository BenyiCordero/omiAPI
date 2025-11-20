package com.bcss.omiapp.dto.request;

public record InventarioDetailsRequest(
        Integer cantidad,
        String estado,
        boolean disponible,
        Integer idProducto,
        Integer idInventario
) {}
