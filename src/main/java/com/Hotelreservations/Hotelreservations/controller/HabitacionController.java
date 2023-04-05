package com.Hotelreservations.Hotelreservations.controller;

import com.Hotelreservations.Hotelreservations.dto.ClienteDTO;
import com.Hotelreservations.Hotelreservations.dto.HabitacionDTO;
import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class HabitacionController {

    private HabitacionService habitacionService;

    @Autowired
    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @PostMapping("/habitacion")
    public Habitacion crear(@RequestBody HabitacionDTO habitacionDTO) {
        return this.habitacionService.crearHabitacion(habitacionDTO);
    }


    @PostMapping("/habitaciones")
    public ResponseEntity<Habitacion> crearHabitacion() {
        this.habitacionService.crearHabitaciones();
        return new ResponseEntity("se crearon las habitaciones por defecto", HttpStatus.CREATED);
    }
    @GetMapping("/habitaciones")
    public ResponseEntity<List<Habitacion>> obtenerClientes() {
        List<Habitacion> habitaciones = this.habitacionService.crearHabitaciones();
        return new ResponseEntity<>(habitaciones, HttpStatus.OK);
    }
}