package com.bcss.omiapp.dto.response;

public record TrabajadorDetailResponse(
    Integer idUsuario,
    String email,
    Float horasSemana,
    Float salario,
    String rol,
    String nombre,
    String primerApellido,
    String segundoApellido,
    String numeroTelefono,
    SucursalBasicResponse sucursal
) {}