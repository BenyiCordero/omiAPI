package com.bcss.omiapp.dto.request;

import com.bcss.omiapp.domain.Rol;

public record TrabajadorRegisterRequest(
        //Persona
        String nombre,
        String primerApellido,
        String segundoApellido,
        String numeroTelefono,
        //Trabajador
        Rol rol,
        String email,
        String password,
        Float horasSemana,
        Float salario
) {
}
