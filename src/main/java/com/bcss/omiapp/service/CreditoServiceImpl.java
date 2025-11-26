package com.bcss.omiapp.service;

import com.bcss.omiapp.domain.Cliente;
import com.bcss.omiapp.domain.Credito;
import com.bcss.omiapp.domain.EnumEstadoCredito;
import com.bcss.omiapp.domain.Venta;
import com.bcss.omiapp.dto.request.CreditoRequest;
import com.bcss.omiapp.dto.response.CreditoResponse;
import com.bcss.omiapp.exception.NotFoundException;
import com.bcss.omiapp.mappers.CreditoMapper;
import com.bcss.omiapp.repository.ClienteRepository;
import com.bcss.omiapp.repository.CreditoRepository;
import com.bcss.omiapp.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreditoServiceImpl implements CreditoService {
    private final CreditoRepository repository;
    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
    private final CreditoMapper mapper;

    public CreditoServiceImpl(CreditoRepository repository,
                              VentaRepository ventaRepository,
                              ClienteRepository clienteRepository,
                              CreditoMapper mapper) {
        this.repository = repository;
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CreditoResponse> getAll() {
        List<Credito> creditos;
        List<CreditoResponse> responses = new ArrayList<>();
        if (!repository.findAll().isEmpty()) creditos =  repository.findAll();
        else throw new NotFoundException("No hay creditos para mostrar");
        for(Credito c: creditos) responses.add(mapper.mapToResponse(c));
        return responses;
    }

    @Override
    public CreditoResponse getById(Integer id) {
        Credito credito;
        if (repository.findById(id).isPresent()) return mapper.mapToResponse(repository.findById(id).get());
        else throw new NotFoundException("No hay credito con el id " + id);
    }

    @Override
    public Credito create(CreditoRequest request) {
        Cliente cliente = clienteRepository.findById(request.idCliente())
                .orElseThrow(() ->
                        new RuntimeException("Cliente no encontrado con id: " + request.idCliente()));

        Venta venta = null;
        if (request.idVenta() != null) {
            venta = ventaRepository.findById(request.idVenta())
                    .orElseThrow(() ->
                            new RuntimeException("Venta no encontrada con id: " + request.idVenta()));
        }

        LocalDate fechaInicio = request.fechaInicio() != null
                ? LocalDate.parse(request.fechaInicio())
                : null;

        LocalDate fechaVencimiento = request.fechaVencimiento() != null
                ? LocalDate.parse(request.fechaVencimiento())
                : null;

        Credito credito = Credito.builder()
                .cliente(cliente)
                .venta(venta)
                .montoInicial(request.montoInicial())
                .saldo(request.saldo())
                .tasaInteres(request.tasaInteres())
                .plazoMeses(request.plazoMeses())
                .estado(request.estado())
                .fechaInicio(fechaInicio)
                .fechaVencimiento(fechaVencimiento)
                .build();

        return repository.save(credito);
    }

    @Override
    public Credito update(Integer id, CreditoRequest request) {
        Credito credito = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Crédito no encontrado con id: " + id));

        Cliente cliente = clienteRepository.findById(request.idCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + request.idCliente()));
        credito.setCliente(cliente);

        Venta venta = ventaRepository.findById(request.idVenta())
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con id: " + request.idVenta()));
        credito.setVenta(venta);

        credito.setTasaInteres(request.tasaInteres());
        credito.setSaldo(request.saldo());
        credito.setEstado(request.estado());
        credito.setCliente(cliente);
        credito.setVenta(venta);

        return repository.save(credito);
    }

    @Override
    public void delete(Integer id) {
        Optional<Credito> credito = repository.findById(id);
        if (!credito.isPresent()) throw new NotFoundException("Credito con id " + id + " no encontrado");
        else {
            credito.get().setEstado(EnumEstadoCredito.PAGADO);
        }
    }

    @Override
    public List<CreditoResponse> getByCliente(Integer idCliente) {
        List<Credito> creditos = repository.findByCliente_IdCliente(idCliente);
        List<CreditoResponse> responses = new ArrayList<>();
        if (creditos.isEmpty()) throw new NotFoundException("No hay creditos relacionados al cliente " + idCliente);
        else for (Credito c: creditos) responses.add(mapper.mapToResponse(c));
        return responses;
    }

    @Override
    public CreditoResponse getByVenta(Integer idVenta) {
        Optional<Credito> credito = repository.findByVenta_IdVenta(idVenta);
        if (!credito.isPresent()) throw new NotFoundException("No hay creditos relacionados a la venta " + idVenta);
        else return mapper.mapToResponse(credito.get());
    }

    @Override
    public List<CreditoResponse> getByEstado(EnumEstadoCredito estado) {
        List<Credito> creditos = repository.findByEstado(estado);
        List<CreditoResponse> responses = new ArrayList<>();
        if (creditos.isEmpty()) throw new NotFoundException("No hay creditos en estado " + estado);
        else for (Credito c: creditos) responses.add(mapper.mapToResponse(c));
        return responses;
    }

    @Override
    public Credito updateSaldo(Integer id, Double nuevoSaldo) {
        Optional<Credito> credito = repository.findById(id);
        if (!credito.isPresent()) throw new NotFoundException("No hay credito con el id " + id);
        credito.get().setSaldo(nuevoSaldo);
        return credito.get();
    }

    @Override
    public Credito cancelar(Integer id) {
        Optional<Credito> credito = repository.findById(id);
        if (!credito.isPresent()) throw new NotFoundException("Credito con id " + id + " no encontrado");
        else {
            credito.get().setEstado(EnumEstadoCredito.CANCELADO);
            return credito.get();
        }
    }

    @Override
    public List<Credito> getMorosos() {
        List<Credito> creditos = repository.findByEstado(EnumEstadoCredito.MOROSO);
        if (creditos.isEmpty()) throw new NotFoundException("No hay creditos en estado moroso");
        else return creditos;
    }
}
