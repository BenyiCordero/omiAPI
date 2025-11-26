package com.bcss.omiapp.controller;

import com.bcss.omiapp.dto.request.CreditoRequest;
import com.bcss.omiapp.dto.request.EstadoCreditoRequest;
import com.bcss.omiapp.service.CreditoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit")
public class CreditoController {
    private final CreditoService service;

    public CreditoController(CreditoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreditoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @RequestBody CreditoRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<?> getByCliente(@PathVariable Integer idCliente) {
        return ResponseEntity.ok(service.getByCliente(idCliente));
    }

    @GetMapping("/venta/{idVenta}")
    public ResponseEntity<?> getByVenta(@PathVariable Integer idVenta) {
        return ResponseEntity.ok(service.getByVenta(idVenta));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> getByEstado(@PathVariable EstadoCreditoRequest estado) {
        return ResponseEntity.ok(service.getByEstado(estado.estado()));
    }

    @PatchMapping("/{id}/saldo")
    public ResponseEntity<?> updateSaldo(
            @PathVariable Integer id,
            @RequestParam Double nuevoSaldo) {

        return ResponseEntity.ok(service.updateSaldo(id, nuevoSaldo));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelarCredito(@PathVariable Integer id) {
        return ResponseEntity.ok(service.cancelar(id));
    }

    @GetMapping("/morosos")
    public ResponseEntity<?> getMorosos() {
        return ResponseEntity.ok(service.getMorosos());
    }
}
