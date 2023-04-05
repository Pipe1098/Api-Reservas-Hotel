package com.Hotelreservations.Hotelreservations.controller;

import com.Hotelreservations.Hotelreservations.dto.ClienteDTO;
import com.Hotelreservations.Hotelreservations.model.Cliente;
import com.Hotelreservations.Hotelreservations.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ClienteController {

    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/cliente")
    public ClienteDTO crear(@RequestBody Cliente cliente) {
        return this.clienteService.crear(cliente);
    }


    @PostMapping("/clientes")
    public ResponseEntity<Cliente> crearClientes() {
        this.clienteService.crearClientes();
        return new ResponseEntity("Se crearon las clientes por defecto.", HttpStatus.CREATED);
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteDTO>> obtenerClientes() {
        List<ClienteDTO> clientes = this.clienteService.crearClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }


}
