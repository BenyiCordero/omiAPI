package com.bcss.omiapp.controller;

import com.bcss.omiapp.domain.Cliente;
import com.bcss.omiapp.dto.request.ClienteNombreRequest;
import com.bcss.omiapp.dto.request.ClienteRegisterRequest;
import com.bcss.omiapp.dto.request.ClienteUpdateRequest;
import com.bcss.omiapp.dto.response.ClienteListResponse;
import com.bcss.omiapp.dto.response.ClienteDetailResponse;
import com.bcss.omiapp.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ClienteRegisterRequest clienteRegisterRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(clienteRegisterRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.getByIdDetail(id));
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.getAllList());
    }

    @GetMapping("/nombre")
    public ResponseEntity<?> findByName(@RequestParam String nombre) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findByName(nombre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable Integer id,@RequestBody ClienteUpdateRequest clienteUpdateRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.updateCliente(id, clienteUpdateRequest));
    }

}
