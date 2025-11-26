package com.bcss.omiapp.mappers;

import com.bcss.omiapp.domain.Trabajador;
import com.bcss.omiapp.dto.response.TrabajadorResponse;

public class TrabajadorMapper {
    public TrabajadorResponse mapToResponse(Trabajador trabajador) {
        return new TrabajadorResponse(
                trabajador.getIdUsuario(),
                trabajador.getEmail(),
                trabajador.getHorasSemana(),
                trabajador.getRol().name(),
                trabajador.getPersona(),
                trabajador.getSucursal()
        );
    }

}
