package com.bcss.omiapp.controller;

import com.bcss.omiapp.domain.Inventario;
import com.bcss.omiapp.dto.response.InventarioListResponse;
import com.bcss.omiapp.dto.response.InventarioDetailResponse;
import com.bcss.omiapp.service.InventarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventarioController {

    private final InventarioService service;

    public InventarioController(InventarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getByIdDetail(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Inventario inventario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(inventario));
    }

    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<?> getBySucursal(@PathVariable Integer sucursalId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getInventarioPerSucursal(sucursalId));
    }
}
