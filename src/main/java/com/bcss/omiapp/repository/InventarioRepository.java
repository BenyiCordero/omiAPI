package com.bcss.omiapp.repository;

import com.bcss.omiapp.domain.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario, Integer> {
    Optional<Inventario> findBySucursal(Integer id);
}
