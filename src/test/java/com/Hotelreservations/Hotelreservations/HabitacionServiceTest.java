package com.Hotelreservations.Hotelreservations;

import com.Hotelreservations.Hotelreservations.dto.HabitacionDTO;
import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.model.TipoHabitacion;
import com.Hotelreservations.Hotelreservations.repository.HabitacionRepository;
import com.Hotelreservations.Hotelreservations.service.HabitacionService;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HabitacionServiceTest {
    HabitacionRepository habitacionRepository;
    HabitacionService habitacionService;

    @Before
    public void setUp() {
        this.habitacionRepository = mock(HabitacionRepository.class);
        this.habitacionService = new HabitacionService(habitacionRepository);
    }
    @Test
    public void testCrearHabitacion() {
        //Arrange
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
