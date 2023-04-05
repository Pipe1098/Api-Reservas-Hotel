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
    public ReservaController(ReservaService reservaService, ClienteService clienteService, HabitacionRepository habitacionRepository, ClienteRepository clienteRepository,ReservaRepository reservaRepository) {
        this.reservaService = reservaService;
        this.clienteService = clienteService;
        this.habitacionRepository = habitacionRepository;
        this.clienteRepository = clienteRepository;
        this.reservaRepository = reservaRepository;
    }
    @PostMapping("/reservas/{fecha}/{id}/{cedula}")
    public  ResponseEntity<ReservaDTO> crearReserva(@PathVariable String fecha, @PathVariable String id, @PathVariable long cedula)  {
     ReservaDTO reservaDTO = reservaService.generar(fecha, id, cedula);
     return new ResponseEntity(reservaDTO, HttpStatus.CREATED);
    }

    @PostMapping("/reservas/disponibles/{fecha}")
    public List<Habitacion> VerDisponibles(@PathVariable String fecha)  {
        List<Habitacion> reserva = reservaService.validarDisponibilidadFecha(fecha);
        return (reserva);
    }

    @PostMapping("/reservas/disponibles/premium/{fecha}")
    public List<Habitacion> verDisponiblesPremiun(@PathVariable String fecha)  {
        List<Habitacion> reserva = reservaService.validarDisponibilidadFechaPremium(fecha);
        return (reserva);
    }

    @PostMapping("/reservas/disponibles/estandar/{fecha}")
    public List<Habitacion> verDisponiblesEstandar(@PathVariable String fecha)  {
        List<Habitacion> reserva = reservaService.validarDisponibilidadFechaEstandar(fecha);
        return (reserva);
    }
    @GetMapping("/reservas/{cedula}")
    public List<Reserva> reservasCliente(@PathVariable long cedula){
        return this.reservaService.verReservasCliente(cedula);
    }

}




