package com.bcss.omiapp.repository;

import com.bcss.omiapp.domain.Producto;
import com.bcss.omiapp.domain.ProductoImagen;
import org.springframework.core.io.UrlResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoImagenRepository extends JpaRepository<ProductoImagen, Integer> {
    List<ProductoImagen> findByProducto(Producto producto);
}
