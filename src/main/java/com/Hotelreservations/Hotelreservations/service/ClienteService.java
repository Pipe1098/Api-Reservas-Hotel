package com.Hotelreservations.Hotelreservations.service;

import com.Hotelreservations.Hotelreservations.exception.ApiRequestException;
import com.Hotelreservations.Hotelreservations.model.Cliente;
import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.model.TipoHabitacion;
import com.Hotelreservations.Hotelreservations.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteService() {
    }

    public Cliente crear(Cliente cliente) {
        if(validarCliente(cliente)) {
            this.clienteRepository.save(cliente);
            return cliente;
        }else {
            throw new ApiRequestException("cedula no numerica o El nombre o el apellido están vacíos o son nulos");
        }
    }
    public boolean validarCliente(Cliente cliente) {
        if (cliente.getCedula() == null || !cliente.getCedula().toString().matches("\\d+")) {
            // La cédula no es numérica o es nula
            return false;
        }

        if (cliente.getNombre() == null || cliente.getNombre().isEmpty() || cliente.getApellido() == null || cliente.getApellido().isEmpty()) {
            // El nombre o el apellido están vacíos o son nulos
            return false;
        }

        return true;
    }


    public void crearClientes() {
       this.clienteRepository.save(new Cliente(121L,"rICA","MAZ","cRA 40 45",21,"example@hotmail.com"));

        this.clienteRepository.save(new Cliente(12661L,"ESTE","MAZ","cRA 40 45",21,"example@hotmail.com"));

    }
}
