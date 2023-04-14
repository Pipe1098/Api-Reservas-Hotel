package HotelReservations;

import HotelReservations.repository.HabitacionRepository;
import HotelReservations.dto.HabitacionDTO;
import HotelReservations.model.Habitacion;
import HotelReservations.model.TipoHabitacion;
import HotelReservations.service.HabitacionService;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
//pruebas unitarias
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
