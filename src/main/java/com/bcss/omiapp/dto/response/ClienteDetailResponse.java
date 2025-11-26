package com.bcss.omiapp.dto.response;

public record ClienteDetailResponse(
    Integer idCliente,
    String nombre,
    String primerApellido,
    String segundoApellido,
    String numeroTelefono,
    Boolean creditoActivo
) {}