package com.bcss.omiapp.controller;

import com.bcss.omiapp.service.TrabajadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/worker")
public class TrabajadorController {

    private final TrabajadorService trabajadorService;

    public TrabajadorController(TrabajadorService trabajadorService) {
        this.trabajadorService = trabajadorService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK).body(trabajadorService.findByEmail(email));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(trabajadorService.findById(id));
    }

}
