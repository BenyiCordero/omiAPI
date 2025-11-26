package com.bcss.omiapp.mappers;

import com.bcss.omiapp.domain.Producto;
import com.bcss.omiapp.dto.response.ProductoBasicResponse;
import com.bcss.omiapp.dto.response.ProductoDetailResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductoMapper {

    public ProductoBasicResponse mapToBasic(Producto producto) {
        return new ProductoBasicResponse(
            producto.getIdProducto(),
            producto.getDescripcion(),
            producto.getNombre(),
            producto.getMarca(),
            producto.getModelo(),
            producto.getPrecio(),
            producto.getActivo()
        );
    }

    public ProductoDetailResponse mapToDetail(Producto producto) {
        List<String> imagenesUrls = producto.getImagenes() != null ? 
            producto.getImagenes().stream()
                .map(img -> "/uploads/productos/" + img.getNombreArchivo())
                .collect(Collectors.toList()) : List.of();
            
        return new ProductoDetailResponse(
            producto.getIdProducto(),
            producto.getNombre(),
            producto.getDescripcion(),
            producto.getMarca(),
            producto.getModelo(),
            producto.getImei(),
            producto.getPrecio(),
            producto.getCosto(),
            producto.getActivo(),
            imagenesUrls
        );
    }
}