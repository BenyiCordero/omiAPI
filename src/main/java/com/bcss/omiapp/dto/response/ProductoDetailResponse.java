package com.bcss.omiapp.dto.response;

import java.util.List;

public record ProductoDetailResponse(
    Integer idProducto,
    String nombre,
    String descripcion,
    String marca,
    String modelo,
    String imei,
    Float precio,
    Float costo,
    Boolean activo,
    List<String> imagenesUrls
) {}