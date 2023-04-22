package com.Hotelreservations.HotelReservations;

import HotelReservations.repository.HabitacionRepository;
import HotelReservations.dto.HabitacionDTO;
import HotelReservations.model.Habitacion;
import HotelReservations.model.TipoHabitacion;
import HotelReservations.service.HabitacionService;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//Pruebas unitarias
public class HabitacionServiceTest {
    private HabitacionRepository habitacionRepository;
    public HabitacionService habitacionService;

    @Before
    public void setUp() {
        this.habitacionRepository = mock(HabitacionRepository.class);
        this.habitacionService = new HabitacionService(habitacionRepository);
    }
    @org.junit.jupiter.api.Test
    public void testCrearHabitacion() {
        // Arrange
        setUp();
        Habitacion habitacion = new Habitacion("1", TipoHabitacion.ESTANDAR, 50.0);
        HabitacionDTO habitacionDTO = new HabitacionDTO(TipoHabitacion.ESTANDAR, 50.0);

        // Act
        Habitacion habitacionCreada = habitacionService.crearHabitacion(habitacionDTO);
        when(habitacionRepository.findById("1")).thenReturn(Optional.of(habitacion));

        // Assert
        Optional<Habitacion> habitacionEncontrada = habitacionRepository.findById("1");
        assertNotNull(habitacionCreada);
        assertTrue(habitacionEncontrada.isPresent());
        assertEquals(habitacion, habitacionEncontrada.get());
    }

}
