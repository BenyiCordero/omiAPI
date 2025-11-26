package com.bcss.omiapp.controller;

import com.bcss.omiapp.domain.Trabajador;
import com.bcss.omiapp.dto.response.TrabajadorListResponse;
import com.bcss.omiapp.dto.response.TrabajadorDetailResponse;
import com.bcss.omiapp.service.TrabajadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/worker")
public class TrabajadorController {

    private final TrabajadorService trabajadorService;

    public TrabajadorController(TrabajadorService trabajadorService) {
        this.trabajadorService = trabajadorService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(trabajadorService.getAllList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(trabajadorService.getByIdDetail(id));
    }

    @GetMapping("/email")
    public ResponseEntity<?> getByEmail(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(trabajadorService.findByEmail(email));
    }
}
