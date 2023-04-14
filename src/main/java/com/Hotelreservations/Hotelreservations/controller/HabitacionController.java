package com.Hotelreservations.Hotelreservations.controller;

import com.Hotelreservations.Hotelreservations.dto.ClienteDTO;
import com.Hotelreservations.Hotelreservations.dto.HabitacionDTO;
import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.service.HabitacionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Habitacion creada con exito"),
            @ApiResponse(code = 404, message = "No es posible crear la habitacion con los datos proporcionados"),
            @ApiResponse(code = 500, message = "Error de conexion")
    })

    @ApiOperation(value = "Crear una habitacion", notes= "Crea una nueva habitacion en la base de datos con la información proporcionada en el cuerpo de la solicitud.", response = Habitacion.class)
    @PostMapping("/habitacion")
    public Habitacion crear(@RequestBody HabitacionDTO habitacionDTO) {
        return this.habitacionService.crearHabitacion(habitacionDTO);
    }

    @ApiOperation(value = "Crear habitaciones", notes= "Crea una lista de habitaciones por defecto para probar la api", response = Habitacion.class)
    @PostMapping("/habitaciones")
    public ResponseEntity<Habitacion> crearHabitaciones() {
        this.habitacionService.crearHabitaciones();
        return new ResponseEntity("Se crearon las habitaciones por defecto.", HttpStatus.CREATED);
    }
    @ApiOperation(value = "Obtener habitaciones", notes= "Muestra todas las habitaciones registradas en la base de datos", response = ClienteDTO.class)
    @GetMapping("/habitaciones")
    public ResponseEntity<List<Habitacion>> obtenerHabitaciones() {
        List<Habitacion> habitaciones = this.habitacionService.crearHabitaciones();
        return new ResponseEntity<>(habitaciones, HttpStatus.OK);
    }
}