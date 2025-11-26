package com.bcss.omiapp.mappers;

import com.bcss.omiapp.domain.Venta;
import com.bcss.omiapp.dto.response.TrabajadorResponse;
import com.bcss.omiapp.dto.response.VentaResponse;
import com.bcss.omiapp.dto.response.VentaBasicResponse;
import com.bcss.omiapp.dto.response.VentaDetailResponse;
import com.bcss.omiapp.dto.response.TrabajadorBasicResponse;
import com.bcss.omiapp.dto.response.ClienteBasicResponse;
import com.bcss.omiapp.dto.response.SucursalBasicResponse;
import org.springframework.stereotype.Component;

@Component
public class VentaMapper {
    private final TrabajadorMapper trabajadorMapper;
    private final ClienteMapper clienteMapper;
    private final SucursalMapper sucursalMapper;

    public VentaMapper(TrabajadorMapper trabajadorMapper, 
                      ClienteMapper clienteMapper,
                      SucursalMapper sucursalMapper) {
        this.trabajadorMapper = trabajadorMapper;
        this.clienteMapper = clienteMapper;
        this.sucursalMapper = sucursalMapper;
    }

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

    public VentaBasicResponse mapToBasic(Venta venta) {
        var clientePersona = venta.getCliente().getPersona();
        var trabajadorPersona = venta.getTrabajador().getPersona();
        
        String nombreCliente = clientePersona.getNombre() + " " + 
                              (clientePersona.getPrimerApellido() != null ? clientePersona.getPrimerApellido() : "") + " " +
                              (clientePersona.getSegundoApellido() != null ? clientePersona.getSegundoApellido() : "");
        
        String nombreTrabajador = trabajadorPersona.getNombre() + " " + 
                                 (trabajadorPersona.getPrimerApellido() != null ? trabajadorPersona.getPrimerApellido() : "") + " " +
                                 (trabajadorPersona.getSegundoApellido() != null ? trabajadorPersona.getSegundoApellido() : "");
        
        return new VentaBasicResponse(
            venta.getIdVenta(),
            venta.getSucursal().getNombre(),
            nombreCliente.trim(),
            nombreTrabajador.trim(),
            venta.getFechaVenta().toString(),
            venta.getTotalVenta(),
            venta.getEstado().name()
        );
    }

    public VentaDetailResponse mapToDetail(Venta venta) {
        TrabajadorBasicResponse trabajadorBasic = trabajadorMapper.mapToBasic(venta.getTrabajador());
        ClienteBasicResponse clienteBasic = clienteMapper.mapToBasic(venta.getCliente());
        SucursalBasicResponse sucursalBasic = sucursalMapper.mapToBasic(venta.getSucursal());

        return new VentaDetailResponse(
            venta.getIdVenta(),
            sucursalBasic,
            clienteBasic,
            trabajadorBasic,
            venta.getFechaVenta(),
            venta.getTotalVenta(),
            venta.getDescuento(),
            venta.getImpuesto(),
            venta.getEstado(),
            venta.getNotas()
        );
    }
}
