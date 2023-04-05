package com.Hotelreservations.Hotelreservations.service;

import com.Hotelreservations.Hotelreservations.dto.ClienteDTO;
import com.Hotelreservations.Hotelreservations.dto.HabitacionDTO;
import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.model.TipoHabitacion;
import com.Hotelreservations.Hotelreservations.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HabitacionService {

    private HabitacionRepository habitacionRepository;
    @Autowired
    public HabitacionService(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
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
