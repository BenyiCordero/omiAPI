package com.bcss.omiapp.dto.response;

import com.bcss.omiapp.domain.Persona;
import com.bcss.omiapp.domain.Sucursal;
import jakarta.persistence.Entity;

public record TrabajadorResponse(
        Integer idUsuario,
        String email,
        Float horasSemana,
        String rol,
        Persona persona,
        Sucursal sucursal
) {
}
