package HotelReservations.service;

import HotelReservations.repository.HabitacionRepository;
import HotelReservations.dto.HabitacionDTO;
import HotelReservations.model.Habitacion;
import HotelReservations.model.TipoHabitacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HabitacionService {

    private HabitacionRepository habitacionRepository;
    @Autowired
    public HabitacionService(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    }

    public HabitacionService() {

    }

    public Habitacion crearHabitacion(HabitacionDTO habitacionDTO) {
        UUID IdHabitacion=UUID.randomUUID();
        Habitacion habitacion= new Habitacion(IdHabitacion.toString(),habitacionDTO.getTipo(),habitacionDTO.getPrecioBase());
        this.habitacionRepository.save(habitacion);
        return habitacion;
    }

    public List<Habitacion> crearHabitaciones(){
        this.habitacionRepository.save(new Habitacion("1", TipoHabitacion.ESTANDAR,30));
        this.habitacionRepository.save(new Habitacion("2", TipoHabitacion.ESTANDAR,30));
        this.habitacionRepository.save(new Habitacion("3", TipoHabitacion.ESTANDAR,30));
        this.habitacionRepository.save(new Habitacion("4", TipoHabitacion.ESTANDAR,30));
        this.habitacionRepository.save(new Habitacion("5", TipoHabitacion.ESTANDAR,60));
        this.habitacionRepository.save(new Habitacion("6", TipoHabitacion.PREMIUM,60));
        this.habitacionRepository.save(new Habitacion("7", TipoHabitacion.PREMIUM,60));
        this.habitacionRepository.save(new Habitacion("8", TipoHabitacion.PREMIUM,60));
        this.habitacionRepository.save(new Habitacion("9", TipoHabitacion.PREMIUM,60));
        this.habitacionRepository.save(new Habitacion("10", TipoHabitacion.PREMIUM,60));
        this.habitacionRepository.save(new Habitacion("11", TipoHabitacion.PREMIUM,60));
        return habitacionRepository.findAll();
    }

}
