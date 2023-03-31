package com.Hotelreservations.Hotelreservations.service;

import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.model.TipoHabitacion;
import com.Hotelreservations.Hotelreservations.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HabitacionService {

    private HabitacionRepository habitacionRepository;
    @Autowired
    public HabitacionService(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    }

    public Habitacion crearHabitacion(Habitacion habitacion) {
        this.habitacionRepository.save(habitacion);
        return habitacion;
    }

    public void crearHabitaciones(){
        this.habitacionRepository.save(new Habitacion(1L, TipoHabitacion.ESTANDAR,30));
        this.habitacionRepository.save(new Habitacion(2L, TipoHabitacion.ESTANDAR,30));
        this.habitacionRepository.save(new Habitacion(3L, TipoHabitacion.ESTANDAR,30));
        this.habitacionRepository.save(new Habitacion(4L, TipoHabitacion.ESTANDAR,30));
        this.habitacionRepository.save(new Habitacion(5L, TipoHabitacion.PREMIUM,60));
        this.habitacionRepository.save(new Habitacion(6L, TipoHabitacion.PREMIUM,60));
        this.habitacionRepository.save(new Habitacion(7L, TipoHabitacion.PREMIUM,60));
        this.habitacionRepository.save(new Habitacion(8L, TipoHabitacion.PREMIUM,60));
        this.habitacionRepository.save(new Habitacion(9L, TipoHabitacion.PREMIUM,60));
        this.habitacionRepository.save(new Habitacion(10L, TipoHabitacion.PREMIUM,60));
        this.habitacionRepository.save(new Habitacion(11L, TipoHabitacion.PREMIUM,60));
    }
}
