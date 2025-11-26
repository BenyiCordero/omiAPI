package com.bcss.omiapp.mappers;

import com.bcss.omiapp.domain.Inventario;
import com.bcss.omiapp.dto.response.InventarioBasicResponse;
import com.bcss.omiapp.dto.response.InventarioDetailResponse;
import com.bcss.omiapp.dto.response.InventarioDetailsBasicResponse;
import com.bcss.omiapp.dto.response.SucursalBasicResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InventarioMapper {
    
    private final SucursalMapper sucursalMapper;
    private final InventarioDetailsMapper inventarioDetailsMapper;

    public InventarioMapper(SucursalMapper sucursalMapper, InventarioDetailsMapper inventarioDetailsMapper) {
        this.sucursalMapper = sucursalMapper;
        this.inventarioDetailsMapper = inventarioDetailsMapper;
    }

    public InventarioBasicResponse mapToBasic(Inventario inventario) {
        return new InventarioBasicResponse(
            inventario.getIdInventario(),
            inventario.getTitulo(),
            inventario.getDescripcion(),
            inventario.getFechaCreacion().toString(),
            inventario.getSucursal() != null ? inventario.getSucursal().getNombre() : "Sin sucursal"
        );
    }

    public InventarioDetailResponse mapToDetail(Inventario inventario) {
        SucursalBasicResponse sucursalBasic = inventario.getSucursal() != null ? 
            sucursalMapper.mapToBasic(inventario.getSucursal()) : null;
            
        List<InventarioDetailsBasicResponse> detallesBasic = inventario.getDetalles() != null ? 
            inventario.getDetalles().stream()
                .map(inventarioDetailsMapper::mapToBasic)
                .collect(Collectors.toList()) : List.of();
            
        return new InventarioDetailResponse(
            inventario.getIdInventario(),
            inventario.getTitulo(),
            inventario.getDescripcion(),
            inventario.getFechaCreacion().toString(),
            sucursalBasic,
            detallesBasic
        );
    }
}