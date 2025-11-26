package com.bcss.omiapp.controller;

import com.bcss.omiapp.domain.EnumEstadoVenta;
import com.bcss.omiapp.domain.Sucursal;
import com.bcss.omiapp.domain.Venta;
import com.bcss.omiapp.dto.request.EstadoVentaRequest;
import com.bcss.omiapp.dto.request.VentaRequest;
import com.bcss.omiapp.service.VentaService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sell")
public class VentaController {
    //Ventar per sucursal
    private final VentaService service;

    public VentaController(VentaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(required = false) EnumEstadoVenta estado) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllList(estado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getByIdDetail(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody VentaRequest venta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(venta));
    }

    @PutMapping("/id")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody VentaRequest venta) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, venta));
    }

    @PatchMapping("/id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/estado/{id}")
    public ResponseEntity<?> changeStatus(@RequestBody EstadoVentaRequest estado, @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.changeStatus(id, estado.estado()));
    }

    @GetMapping("/sucursal/{id}")
    public ResponseEntity<?> getBySucursal(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getBySucursal(id));
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> getByCliente(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getByCliente(id));
    }

    @GetMapping("/trabajador/{id}")
    public ResponseEntity<?> getByTrabajador(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getByTrabajador(id));
    }
}
