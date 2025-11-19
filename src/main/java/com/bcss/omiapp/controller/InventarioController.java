package com.bcss.omiapp.controller;

import com.bcss.omiapp.domain.Inventario;
import com.bcss.omiapp.service.InventarioService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventary")
public class InventarioController {

    private final InventarioService service;

    public InventarioController(InventarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Inventario inventario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(inventario));
    }

    @GetMapping("/sucursalId")
    public ResponseEntity<?> getBySucursal(@PathVariable Integer sucursalId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getInventarioPerSucursal(sucursalId));
    }
}
