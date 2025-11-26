package com.bcss.omiapp.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Pago")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPago;
    @ManyToOne
    @JoinColumn(name = "idVenta", referencedColumnName = "idVenta")
    private Venta venta;
    @ManyToOne
    @JoinColumn(name = "idCredito", referencedColumnName = "idCredito")
    private Credito credito;
    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente", nullable = false)
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "idPersona", referencedColumnName = "idPersona", nullable = false) //Quien registró el pago
    private Persona persona;
    @Column(nullable = false)
    private LocalDate fecha;
    @Column(nullable = false)
    private Double monto;
    @Column(nullable = false)
    private EnumMetodoPago metodo;
    @Column(nullable = false)
    private String referencia;
    @Column(nullable = false)
    private String notas;
    @Column(nullable = false)
    private LocalDate createdAt;
}
