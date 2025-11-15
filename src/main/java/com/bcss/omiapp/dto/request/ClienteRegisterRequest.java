package com.bcss.omiapp.dto.request;

import com.bcss.omiapp.auth.Rol;

public record ClienteRegisterRequest(
        //Persona
        String nombre,
        String primerApellido,
        String segundoApellido,
        String numeroTelefono,
        //Cliente
        Boolean creditoActivo
) {
}
