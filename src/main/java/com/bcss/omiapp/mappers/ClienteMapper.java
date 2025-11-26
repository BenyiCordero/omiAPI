package com.bcss.omiapp.mappers;

import com.bcss.omiapp.domain.Cliente;
import com.bcss.omiapp.dto.response.ClienteBasicResponse;
import com.bcss.omiapp.dto.response.ClienteDetailResponse;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteBasicResponse mapToBasic(Cliente cliente) {
        var persona = cliente.getPersona();
        String nombreCompleto = persona.getNombre() + " " + 
                               (persona.getPrimerApellido() != null ? persona.getPrimerApellido() : "") + " " +
                               (persona.getSegundoApellido() != null ? persona.getSegundoApellido() : "");
        
        return new ClienteBasicResponse(
            cliente.getIdCliente(),
            nombreCompleto.trim(),
            persona.getNumeroTelefono(),
            cliente.getCreditoActivo()
        );
    }

    public ClienteDetailResponse mapToDetail(Cliente cliente) {
        var persona = cliente.getPersona();
        
        return new ClienteDetailResponse(
            cliente.getIdCliente(),
            persona.getNombre(),
            persona.getPrimerApellido(),
            persona.getSegundoApellido(),
            persona.getNumeroTelefono(),
            cliente.getCreditoActivo()
        );
    }
}