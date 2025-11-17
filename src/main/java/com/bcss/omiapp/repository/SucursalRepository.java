package com.bcss.omiapp.repository;

import com.bcss.omiapp.domain.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {
    Optional<Sucursal> findByNombre(String nombre);
    List<Sucursal> findByActivo(Boolean activo);
}
