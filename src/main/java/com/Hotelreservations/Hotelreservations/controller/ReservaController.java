package com.Hotelreservations.Hotelreservations.controller;

import com.Hotelreservations.Hotelreservations.dto.HabitacionDTO;
import com.Hotelreservations.Hotelreservations.dto.ReservaDTO;
import com.Hotelreservations.Hotelreservations.model.Cliente;
import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.model.Reserva;
import com.Hotelreservations.Hotelreservations.repository.ClienteRepository;
import com.Hotelreservations.Hotelreservations.repository.HabitacionRepository;
import com.Hotelreservations.Hotelreservations.repository.ReservaRepository;
import com.Hotelreservations.Hotelreservations.service.ClienteService;
import com.Hotelreservations.Hotelreservations.service.ReservaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class ReservaController {

    private ReservaService reservaService;
    private ClienteService clienteService;
    private HabitacionRepository habitacionRepository;
    private ClienteRepository clienteRepository;
    private ReservaRepository reservaRepository;

    @Autowired
    public ReservaController(ReservaService reservaService, ClienteService clienteService, HabitacionRepository habitacionRepository, ClienteRepository clienteRepository, ReservaRepository reservaRepository) {
        this.reservaService = reservaService;
        this.clienteService = clienteService;
        this.habitacionRepository = habitacionRepository;
        this.clienteRepository = clienteRepository;
        this.reservaRepository = reservaRepository;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reserva creada con exito"),
            @ApiResponse(code = 404, message = "No se pudo generar la reserva con los datos proporcionados"),
            @ApiResponse(code = 500, message = "Error de conexion")
    })
    @ApiOperation(value = "Crear una Reserva", notes = "Crea una nueva reserva en la base de datos con la información proporcionada en los parametros del endpoint", response = ReservaDTO.class)
    @PostMapping("/reservas/{fecha}/{id}/{cedula}")
    public ResponseEntity<ReservaDTO> crearReserva(@ApiParam(value = "Digite la fecha en la que desea hacer su reserva con el formato yyyy-MM-dd", required = true)
                                                   @PathVariable String fecha,
                                                   @ApiParam(value = "Digite el número de una habitación creada previamente", required = true)
                                                   @PathVariable String id, @ApiParam(value = "Digite la cédula de un cliente creado previamente", required = true)
                                                   @PathVariable long cedula) {
        ReservaDTO reservaDTO = reservaService.generar(fecha, id, cedula);
        return new ResponseEntity(reservaDTO, HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Habitaciones disponibles"),
            @ApiResponse(code = 404, message = "Fecha mal ingresada"),
            @ApiResponse(code = 500, message = "Error de conexion")
    })
    @ApiOperation(value = "Ver habitaciones disponibles", notes = "Muestra las habitaciones disponibles en la base de datos para una fecha especifica ", response = Habitacion.class)
    @PostMapping("/reservas/disponibles/{fecha}")
    public List<Habitacion> VerDisponibles(@ApiParam(value = "Digite la fecha en el formato yyyy-MM-dd", required = true) @PathVariable String fecha) {
        List<Habitacion> reserva = reservaService.validarDisponibilidadFecha(fecha);
        return (reserva);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Hay habitaciones premium disponibles"),
            @ApiResponse(code = 404, message = "Fecha mal ingresada"),
            @ApiResponse(code = 500, message = "Error de conexion")
    })
    @ApiOperation(value = "Ver habitaciones premium disponibles", notes = "Muestra las habitaciones de tipo premium disponibles en la base de datos para una fecha especifica ", response = Habitacion.class)
    @PostMapping("/reservas/disponibles/premium/{fecha}")
    public List<Habitacion> verDisponiblesPremiun(@ApiParam(value = "Digite la fecha en el formato yyyy-MM-dd", required = true) @PathVariable String fecha) {
        List<Habitacion> reserva = reservaService.validarDisponibilidadFechaPremium(fecha);
        return (reserva);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Hay habitaciones estandar disponibles"),
            @ApiResponse(code = 404, message = "Fecha mal ingresada"),
            @ApiResponse(code = 500, message = "Error de conexion")
    })
    @ApiOperation(value = "Ver habitaciones estandar disponibles", notes = "Muestra las habitaciones de tipo estandar disponibles en la base de datos para una fecha especifica ", response = Habitacion.class)
    @PostMapping("/reservas/disponibles/estandar/{fecha}")
    public List<Habitacion> verDisponiblesEstandar(@ApiParam(value = "Digite la fecha en el formato yyyy-MM-dd", required = true) @PathVariable String fecha) {
        List<Habitacion> reserva = reservaService.validarDisponibilidadFechaEstandar(fecha);
        return (reserva);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservas del cliente encontradas con exito"),
            @ApiResponse(code = 400, message = "Cedula mal ingresada"),
            @ApiResponse(code = 500, message = "Error de conexion")
    })
    @ApiOperation(value = "Ver reservas por cedula", notes = "Muestra las reservas de un cliente dada su cedula ", response = Reserva.class)
    @GetMapping("/reservas/{cedula}")
    public List<Reserva> VerReservasCliente(@ApiParam(value = "Digite la cedula del cliente previamente registrado", required = true) @PathVariable long cedula) {
        return this.reservaService.verReservasCliente(cedula);
    }

}




