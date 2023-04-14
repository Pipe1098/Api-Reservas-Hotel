package HotelReservations;

import HotelReservations.repository.ClienteRepository;
import HotelReservations.repository.HabitacionRepository;
import HotelReservations.repository.ReservaRepository;
import HotelReservations.dto.ReservaDTO;
import HotelReservations.model.Cliente;
import HotelReservations.model.Habitacion;
import HotelReservations.model.Reserva;
import HotelReservations.model.TipoHabitacion;
import HotelReservations.service.ReservaService;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ReservaServiceTest {
    HabitacionRepository habitacionRepository;
    ClienteRepository clienteRepository;
    ReservaRepository reservaRepository;
    ReservaService reservaService;

    @Before
    public void setUp() {
        this.reservaRepository = mock(ReservaRepository.class);
        this.habitacionRepository = mock(HabitacionRepository.class);
        this.clienteRepository = mock(ClienteRepository.class);
        this.reservaService = new ReservaService(clienteRepository, habitacionRepository, reservaRepository);
    }

    @Test
    public void testValidarIdHabitacion() {
        //Arrange
        List<Habitacion> disponibles = new ArrayList<>();
        disponibles.add(new Habitacion("1", TipoHabitacion.PREMIUM, 100));
        disponibles.add(new Habitacion("2", TipoHabitacion.ESTANDAR, 200));
        disponibles.add(new Habitacion("3", TipoHabitacion.PREMIUM, 300));
        //act
        ReservaService reservaService = new ReservaService(null, null, null);
        //assert
        assertTrue(reservaService.validarIdHabitacion(disponibles, "1"));
    }

    @Test
    public void testValidarFormatoFecha() {
        //Arrange
        ReservaService reservaService = new ReservaService(null, null, null);
        //act
        boolean valido = reservaService.validarFormatoFecha("2023-03-30");
        //assert
        assertTrue(valido);
    }

    @Test
    public void testCalcularPrecioTotal() {
        //Arrange
        Habitacion habitacion1 = new Habitacion("1", TipoHabitacion.PREMIUM, 100);
        Habitacion habitacion2 = new Habitacion("2", TipoHabitacion.ESTANDAR, 100);

        //act
        ReservaService reservaService = new ReservaService(null, null, null);
        double precioTotal1 = reservaService.calcularPrecioTotal(habitacion1);
        double precioTotal2 = reservaService.calcularPrecioTotal(habitacion2);

        //assert
        assertEquals(95, precioTotal1, 0.0);
        assertEquals(100, precioTotal2, 0.0);
    }

    @Test
    public void testValidarDisponibilidadFecha() {
        // Arrange
        List<Habitacion> habitaciones = new ArrayList<>();
        Cliente cliente = new Cliente(1L, "Juan", "Perez", "CR30", 25, "Juan@gmail.com");
        Habitacion habitacion1 = new Habitacion("1", TipoHabitacion.ESTANDAR, 100.0);
        Habitacion habitacion2 = new Habitacion("2", TipoHabitacion.PREMIUM, 200.0);

        habitaciones.add(habitacion1);
        habitaciones.add(habitacion2);

        LocalDate fechaReserva = LocalDate.parse("2022-01-01");
        LocalDate fechaReserva2 = LocalDate.parse("2022-01-02");

        Reserva reserva1 = new Reserva(habitacion1, cliente, fechaReserva, 100.0);
        Reserva reserva2 = new Reserva(habitacion2, cliente, fechaReserva2, 200.0);

        when(habitacionRepository.findAll()).thenReturn(habitaciones);
        when(reservaRepository.findByFechaReserva(eq(fechaReserva))).thenReturn(Collections.singletonList(reserva1));
        when(reservaRepository.findByFechaReserva(eq(fechaReserva2))).thenReturn(Collections.singletonList(reserva2));

        // Act
        List<Habitacion> disponibles1 = reservaService.validarDisponibilidadFecha("2022-01-03");
        List<Habitacion> disponibles2 = reservaService.validarDisponibilidadFecha("2022-01-01");

        // Assert
        assertEquals(2, disponibles1.size());
        assertTrue(disponibles1.contains(habitacion2));
        assertTrue(disponibles1.contains(habitacion1));
        assertEquals(1, disponibles2.size());
        assertTrue(disponibles2.contains(habitacion2));
        assertFalse(disponibles2.contains(habitacion1));
    }

    @Test
    public void testValidarDisponibilidadFechaPremium() {
        // Arrange
        List<Habitacion> habitaciones = new ArrayList<>();
        List<Reserva> reservas = new ArrayList<>();
        Cliente cliente = new Cliente(1L, "Juan", "Perez", "CR30", 25, "Juan@gmail.com");
        Habitacion habitacion1 = new Habitacion("1", TipoHabitacion.ESTANDAR, 100.0);
        Habitacion habitacion2 = new Habitacion("2", TipoHabitacion.PREMIUM, 200.0);
        Habitacion habitacion3 = new Habitacion("3", TipoHabitacion.PREMIUM, 250.0);

        habitaciones.add(habitacion1);
        habitaciones.add(habitacion2);
        habitaciones.add(habitacion3);
        LocalDate fechaReserva = LocalDate.parse("2022-01-01");
        LocalDate fechaReserva2 = LocalDate.parse("2022-01-01");
        Reserva reserva1 = new Reserva(habitacion1, cliente, fechaReserva, 100.0);
        Reserva reserva2 = new Reserva(habitacion2, cliente, fechaReserva2, 200.0);
        reservas.add(reserva1);
        reservas.add(reserva2);
        when(habitacionRepository.findAll()).thenReturn(habitaciones);
        when(reservaRepository.findByFechaReserva(eq(fechaReserva))).thenReturn(Collections.singletonList(reserva1));
        when(reservaRepository.findByFechaReserva(eq(fechaReserva2))).thenReturn(Collections.singletonList(reserva2));

        // Act
        List<Habitacion> disponibles = reservaService.validarDisponibilidadFechaPremium("2022-01-01");

        // Assert
        assertEquals(1, disponibles.size());
        assertFalse(disponibles.contains(habitacion2));
        assertFalse(disponibles.contains(habitacion1));
        assertTrue(disponibles.contains(habitacion3));

    }
    @Test
    public void testValidarDisponibilidadFechaEstandar() {
        // Arrange
        List<Habitacion> habitaciones = new ArrayList<>();
        List<Reserva> reservas = new ArrayList<>();
        Cliente cliente = new Cliente(1L, "Juan", "Perez", "CR30", 25, "Juan@gmail.com");
        Habitacion habitacion1 = new Habitacion("1", TipoHabitacion.PREMIUM, 100.0);
        Habitacion habitacion2 = new Habitacion("2", TipoHabitacion.ESTANDAR, 200.0);
        Habitacion habitacion3 = new Habitacion("3", TipoHabitacion.ESTANDAR, 250.0);

        habitaciones.add(habitacion1);
        habitaciones.add(habitacion2);
        habitaciones.add(habitacion3);
        LocalDate fechaReserva = LocalDate.parse("2022-01-01");
        LocalDate fechaReserva2 = LocalDate.parse("2022-01-01");
        Reserva reserva1 = new Reserva(habitacion1, cliente, fechaReserva, 100.0);
        Reserva reserva2 = new Reserva(habitacion2, cliente, fechaReserva2, 200.0);
        reservas.add(reserva1);
        reservas.add(reserva2);
        when(habitacionRepository.findAll()).thenReturn(habitaciones);
        when(reservaRepository.findByFechaReserva(eq(fechaReserva))).thenReturn(Collections.singletonList(reserva1));
        when(reservaRepository.findByFechaReserva(eq(fechaReserva2))).thenReturn(Collections.singletonList(reserva2));

        // Act
        List<Habitacion> disponibles = reservaService.validarDisponibilidadFechaEstandar("2022-01-01");

        // Assert
        assertEquals(1, disponibles.size());
        assertFalse(disponibles.contains(habitacion2));
        assertTrue(disponibles.contains(habitacion3));

    }
    @Test
    public void testVerReservasCliente() {
        // Arrange
        Cliente cliente1 = new Cliente(1L, "Juan", "Perez", "CR30", 25, "Juan@gmail.com");
        Cliente cliente2 = new Cliente(2L, "Pedro", "Gomez", "CR31", 30, "Pedro@gmail.com");
        Habitacion habitacion1 = new Habitacion("1", TipoHabitacion.ESTANDAR, 100.0);
        Habitacion habitacion2 = new Habitacion("2", TipoHabitacion.PREMIUM, 200.0);

        LocalDate fechaReserva1 = LocalDate.parse("2022-01-01");
        LocalDate fechaReserva2 = LocalDate.parse("2022-02-01");

        Reserva reserva1 = new Reserva(habitacion1, cliente1, fechaReserva1, 100.0);
        Reserva reserva2 = new Reserva(habitacion2, cliente1, fechaReserva2, 200.0);
        Reserva reserva3 = new Reserva(habitacion1, cliente2, fechaReserva1.plusDays(2), 100.0);

        List<Reserva> reservas = new ArrayList<>();
        reservas.add(reserva1);
        reservas.add(reserva2);
        reservas.add(reserva3);

        when(reservaRepository.findAll()).thenReturn(reservas);

        // Act
        List<Reserva> reservasCliente1 = reservaService.verReservasCliente(1L);
        List<Reserva> reservasCliente2 = reservaService.verReservasCliente(2L);

        // Assert
        assertEquals(2, reservasCliente1.size());
        assertEquals(1, reservasCliente2.size());
        assertTrue(reservasCliente1.contains(reserva1));
        assertTrue(reservasCliente1.contains(reserva2));
        assertTrue(reservasCliente2.contains(reserva3));
    }

    @Test
    public void testGenerarReserva() {
        // Arrange
        LocalDate fechaReserva1 = LocalDate.parse("2022-01-01");
        LocalDate fechaReserva2 = LocalDate.parse("2022-01-02");
        List<Habitacion> habitaciones = new ArrayList<>();
        List<Reserva> reservas = new ArrayList<>();
        Cliente cliente = new Cliente(1L, "Juan", "Perez", "CR30", 25, "Juan@gmail.com");
        Habitacion habitacion1 = new Habitacion("1", TipoHabitacion.ESTANDAR, 100.0);
        Habitacion habitacion2 = new Habitacion("2", TipoHabitacion.PREMIUM, 100.0);
        habitaciones.add(habitacion1);
        habitaciones.add(habitacion2);
        Reserva reserva1 = new Reserva(habitacion1, cliente, fechaReserva1, 100.0);
        Reserva reserva2 = new Reserva(habitacion2, cliente, fechaReserva2, 200.0);
        reservas.add(reserva1);
        reservas.add(reserva2);

        when(habitacionRepository.findAll()).thenReturn(habitaciones);
        when(reservaRepository.findAll()).thenReturn(reservas);
        when(reservaRepository.findByFechaReserva(eq(fechaReserva1))).thenReturn(Collections.singletonList(reserva1));
        when(reservaRepository.findByFechaReserva(eq(fechaReserva2))).thenReturn(Collections.singletonList(reserva2));
        when(habitacionRepository.findById(habitacion1.getId())).thenReturn(Optional.of(habitacion1));
        when(habitacionRepository.findById(habitacion2.getId())).thenReturn(Optional.of(habitacion2));
        when(clienteRepository.findById(any())).thenReturn(Optional.of(cliente));

        // Act
        ReservaDTO reservaDTO = reservaService.generar("2022-01-01", habitacion2.getId(), cliente.getCedula());

        // Assert
        assertNotNull(reservaDTO);
        assertEquals(cliente.getCedula(), reservaDTO.getClienteDTO().getCedula());
        assertEquals(habitacion2.getTipo(), reservaDTO.getHabitacionDTO().getTipo());
        assertEquals(LocalDate.parse("2022-01-01"), reservaDTO.getFechaReserva());
        verify(reservaRepository, times(1)).save(any());
    }
}

