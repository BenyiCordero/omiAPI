package com.bcss.omiapp.repository;

import com.bcss.omiapp.domain.Venta;
import com.bcss.omiapp.domain.VentaDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaDetailsRepository extends JpaRepository<VentaDetails, Integer> {
    List<VentaDetails> findByVenta(Venta venta);
}
