package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.Producto;
import com.bcss.omiapp.dto.request.ProductoUpdateRequest;
import com.bcss.omiapp.dto.response.ProductoListResponse;
import com.bcss.omiapp.dto.response.ProductoDetailResponse;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    Producto save(Producto producto);
    List<Producto> findByNombre(String nombre);
    Optional<Producto> findById(Integer idProducto);
    ProductoDetailResponse findByIdDetail(Integer idProducto);
    Producto update(Integer id, ProductoUpdateRequest productoUpdateRequest);
    Boolean deleteById(Integer idProducto);
    List<Producto> findAll();
    ProductoListResponse findAllList();
    List<Producto> findAllActivo();
    List<Producto> findAllInactivo();
}
