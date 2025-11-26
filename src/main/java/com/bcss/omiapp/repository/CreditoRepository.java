package com.bcss.omiapp.repository;

import com.bcss.omiapp.domain.Credito;
import com.bcss.omiapp.domain.EnumEstadoCredito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CreditoRepository extends JpaRepository<Credito, Integer> {
    List<Credito> findByCliente_IdCliente(Integer idCliente);
    Optional<Credito> findByVenta_IdVenta(Integer idVenta);
    List<Credito> findByEstado(EnumEstadoCredito estado);
}
