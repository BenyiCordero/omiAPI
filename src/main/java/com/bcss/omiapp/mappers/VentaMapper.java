package com.bcss.omiapp.mappers;

import com.bcss.omiapp.domain.Venta;
import com.bcss.omiapp.dto.response.TrabajadorResponse;
import com.bcss.omiapp.dto.response.VentaResponse;
import org.springframework.stereotype.Component;

@Component
public class VentaMapper {
    private final TrabajadorMapper trabajadorMapper = new TrabajadorMapper();

    public VentaResponse mapToResponse(Venta venta) {

        TrabajadorResponse trabajadorResponse = trabajadorMapper.mapToResponse(venta.getTrabajador());

        return new VentaResponse(
                venta.getIdVenta(),
                venta.getSucursal(),
                venta.getCliente(),
                trabajadorResponse,
                venta.getFechaVenta(),
                venta.getTotalVenta(),
                venta.getDescuento(),
                venta.getImpuesto(),
                venta.getEstado(),
                venta.getNotas()
        );
    }
}
