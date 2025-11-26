package com.bcss.omiapp.mappers;

import com.bcss.omiapp.domain.Sucursal;
import com.bcss.omiapp.dto.response.SucursalBasicResponse;
import org.springframework.stereotype.Component;

@Component
public class SucursalMapper {

    public SucursalBasicResponse mapToBasic(Sucursal sucursal) {
        return new SucursalBasicResponse(
            sucursal.getIdSucursal(),
            sucursal.getNombre(),
            sucursal.getSucursalKey(),
            sucursal.getActivo()
        );
    }
}