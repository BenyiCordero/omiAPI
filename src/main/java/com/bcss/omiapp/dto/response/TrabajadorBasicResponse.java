package com.bcss.omiapp.dto.response;

public record TrabajadorBasicResponse(
    Integer idUsuario,
    String email,
    String rol,
    String nombreCompleto,
    Float salario
) {}