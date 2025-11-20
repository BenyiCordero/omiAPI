package com.bcss.omiapp.repository;

import com.bcss.omiapp.domain.InventarioDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventarioDetailsRepository extends JpaRepository<InventarioDetails, Integer> {
    List<InventarioDetails> findByProductoIdProductoAndDisponibleTrue(Integer idProducto);
    List<InventarioDetails> findByInventarioIdInventarioAndDisponibleTrue(Integer idInventario);
    List<InventarioDetails> findByDisponible(Boolean disponible);
}
