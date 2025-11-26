package com.bcss.omiapp.dto.response;

public record ClienteBasicResponse(
    Integer idCliente,
    String nombreCompleto,
    String telefono,
    Boolean creditoActivo
) {}