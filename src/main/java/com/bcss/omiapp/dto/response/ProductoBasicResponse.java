package com.bcss.omiapp.dto.response;

public record ProductoBasicResponse(
    Integer idProducto,
    String descripcion,
    String nombre,
    String marca,
    String modelo,
    Float precio,
    Boolean activo
) {}