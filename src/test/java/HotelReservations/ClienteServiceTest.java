package HotelReservations;

import HotelReservations.repository.ClienteRepository;
import HotelReservations.dto.ClienteDTO;
import HotelReservations.model.Cliente;
import HotelReservations.service.ClienteService;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClienteServiceTest {
    ClienteRepository clienteRepository;
    ClienteService clienteService;

    @Before
    public void setUp() {
        this.clienteRepository = mock(ClienteRepository.class);
        this.clienteService = new ClienteService(clienteRepository);
    }
    @Test
    public void testValidarClienteCedulaNull() {
        //Arrange
        ClienteService clienteService = new ClienteService();
        // Act
        Cliente cliente = new Cliente(null, "Juan", "Pérez", "Calle 123", 30, "juan@example.com");
        // Assert
        assertFalse(clienteService.validarCliente(cliente));
    }

    @Test
    public void testValidarClienteNombreNull() {
        //Arrange
        ClienteService clienteService = new ClienteService();
        // Act
        Cliente cliente = new Cliente(123L, null, "Pérez", "Calle 123", 30, "juan@example.com");
        // Assert
        assertFalse(clienteService.validarCliente(cliente));
    }

    @Test
    public void testValidarClienteNombreVacio() {
        // Arrange
        ClienteService clienteService = new ClienteService();
        // Act
        Cliente cliente = new Cliente(123L, "", "Pérez", "Calle 123", 30, "juan@example.com");
        // Assert
        assertFalse(clienteService.validarCliente(cliente));
    }

    @Test
    public void testValidarClienteApellidoNull() {
        // Arrange
        ClienteService clienteService = new ClienteService();
        // Act
        Cliente cliente = new Cliente(123L, "Juan", null, "Calle 123", 30, "juan@example.com");
        // Assert
        assertFalse(clienteService.validarCliente(cliente));
    }

    @Test
    public void testValidarClienteApellidoVacio() {
        // Arrange
        ClienteService clienteService = new ClienteService();
        // Act
        Cliente cliente = new Cliente(123L, "Juan", "", "Calle 123", 30, "juan@example.com");
        // Assert
        assertFalse(clienteService.validarCliente(cliente));
    }

    @Test
    public void testValidarClienteCorrecto() {
        // Arrange
        ClienteService clienteService = new ClienteService();
        // Act
        Cliente cliente = new Cliente(123L, "Juan", "Pérez", "Calle 123", 30, "juan@example.com");
        // Assert
        assertTrue(clienteService.validarCliente(cliente));
    }
    @Test
    public void testCrearCliente() {
        //Arrange
        Cliente cliente = new Cliente(123L, "Juan", "Pérez", "Calle 123", 30, "juan@example.com");
        ClienteDTO clientedto = new ClienteDTO(123L,"Juan", "Pérez", "juan@example.com");

        // Act
        ClienteDTO clienteCreado = clienteService.crear(cliente);
        when(clienteRepository.findById(123L)).thenReturn(Optional.of(cliente));

        // Assert
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(123L);
        assertEquals(clientedto, clienteCreado);
        assertTrue(clienteEncontrado.isPresent());
        assertEquals(cliente, clienteEncontrado.get());
    }
}
