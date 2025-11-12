package com.bcss.omiapp.dto.request;

import com.bcss.omiapp.auth.Rol;

public record TrabajadorRegisterRequest(
        //Persona
        String nombre,
        String primerApellido,
        String segundoApellido,
        String numeroTelefono,
        Rol rol,
        //Trabajador
        String email,
        String password,
        Float horasSemana,
        Float salario
) {
}
