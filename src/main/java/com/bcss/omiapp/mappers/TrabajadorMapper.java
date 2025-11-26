package com.bcss.omiapp.mappers;

import com.bcss.omiapp.domain.Trabajador;
import com.bcss.omiapp.dto.response.TrabajadorResponse;
import com.bcss.omiapp.dto.response.TrabajadorBasicResponse;
import com.bcss.omiapp.dto.response.TrabajadorDetailResponse;
import com.bcss.omiapp.dto.response.SucursalBasicResponse;
import org.springframework.stereotype.Component;

@Component
public class TrabajadorMapper {
    
    private final SucursalMapper sucursalMapper;

    public TrabajadorMapper(SucursalMapper sucursalMapper) {
        this.sucursalMapper = sucursalMapper;
    }

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

    public TrabajadorBasicResponse mapToBasic(Trabajador trabajador) {
        var persona = trabajador.getPersona();
        String nombreCompleto = persona.getNombre() + " " + 
                               (persona.getPrimerApellido() != null ? persona.getPrimerApellido() : "") + " " +
                               (persona.getSegundoApellido() != null ? persona.getSegundoApellido() : "");
        
        return new TrabajadorBasicResponse(
            trabajador.getIdUsuario(),
            trabajador.getEmail(),
            trabajador.getRol().name(),
            nombreCompleto.trim(),
            trabajador.getSalario()
        );
    }

    public TrabajadorDetailResponse mapToDetail(Trabajador trabajador) {
        var persona = trabajador.getPersona();
        SucursalBasicResponse sucursalBasic = trabajador.getSucursal() != null ? 
            sucursalMapper.mapToBasic(trabajador.getSucursal()) : null;
            
        return new TrabajadorDetailResponse(
            trabajador.getIdUsuario(),
            trabajador.getEmail(),
            trabajador.getHorasSemana(),
            trabajador.getSalario(),
            trabajador.getRol().name(),
            persona.getNombre(),
            persona.getPrimerApellido(),
            persona.getSegundoApellido(),
            persona.getNumeroTelefono(),
            sucursalBasic
        );
    }
}
