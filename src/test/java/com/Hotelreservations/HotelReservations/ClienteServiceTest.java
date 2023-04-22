package com.Hotelreservations.HotelReservations;

import HotelReservations.dto.ClienteDTO;
import HotelReservations.model.Cliente;
import HotelReservations.repository.ClienteRepository;
import HotelReservations.service.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ClienteServiceTest {
    ClienteRepository clienteRepository;

    ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        this.clienteRepository = mock(ClienteRepository.class);
        this.clienteService = new ClienteService(clienteRepository);
    }

    @org.junit.jupiter.api.Test
    public void testValidarClienteCedulaNull() {
        //Arrange
        setUp();
        Cliente cliente = new Cliente(null, "Juan", "Pérez", "Calle 123", 30, "juan@example.com");
        // Act
        boolean isValid = clienteService.validarCliente(cliente);
        // Assert
        Assertions.assertFalse(isValid);
    }

    @org.junit.jupiter.api.Test
    public void testValidarClienteNombreNull() {
        //Arrange
        setUp();
        Cliente cliente = new Cliente(123L, null, "Pérez", "Calle 123", 30, "juan@example.com");
        // Act
        boolean isValid = clienteService.validarCliente(cliente);
        // Assert
        Assertions.assertFalse(isValid);
    }

    @org.junit.jupiter.api.Test
    public void testValidarClienteNombreVacio() {
        // Arrange
        setUp();
        // Act
        Cliente cliente = new Cliente(123L, "", "Pérez", "Calle 123", 30, "juan@example.com");
        boolean isValid = clienteService.validarCliente(cliente);
        // Assert
        Assertions.assertFalse(isValid);
    }

    @org.junit.jupiter.api.Test
    public void testValidarClienteApellidoNull() {
        // Arrange
        setUp();
        // Act
        Cliente cliente = new Cliente(123L, "Juan", null, "Calle 123", 30, "juan@example.com");
        boolean isValid = clienteService.validarCliente(cliente);
        // Assert
        Assertions.assertFalse(isValid);
    }

    @org.junit.jupiter.api.Test
    public void testValidarClienteApellidoVacio() {
        // Arrange
        setUp();
        // Act
        Cliente cliente = new Cliente(123L, "Juan", "", "Calle 123", 30, "juan@example.com");
        boolean isValid = clienteService.validarCliente(cliente);
        // Assert
        Assertions.assertFalse(isValid);
    }

    @org.junit.jupiter.api.Test
    public void testValidarClienteCorrecto() {
        // Arrange
        setUp();
        // Act
        Cliente cliente = new Cliente(123L, "Juan", "Pérez", "Calle 123", 30, "juan@example.com");
        boolean isValid = clienteService.validarCliente(cliente);
        // Assert
        Assertions.assertTrue(isValid);
    }

    @org.junit.jupiter.api.Test
    public void testCrearCliente() {

        //Arrange
        setUp();
        Cliente cliente = new Cliente(123L, "Juan", "Pérez", "Calle 123", 30, "juan@example.com");
        when(clienteRepository.findById(any())).thenReturn(Optional.of(cliente));

        // Act
        ClienteDTO clienteCreado = clienteService.crear(cliente);
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(123L);

        // Assert
        Assertions.assertNotNull(clienteCreado);
        Assertions.assertEquals(cliente, clienteEncontrado.get());
        Mockito.verify(clienteRepository, Mockito.times(1)).save(any(Cliente.class));
    }
}