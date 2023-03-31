package com.Hotelreservations.Hotelreservations.service;

import com.Hotelreservations.Hotelreservations.exception.ApiRequestException;
import com.Hotelreservations.Hotelreservations.model.Cliente;
import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.model.Reserva;
import com.Hotelreservations.Hotelreservations.model.TipoHabitacion;
import com.Hotelreservations.Hotelreservations.repository.ClienteRepository;
import com.Hotelreservations.Hotelreservations.repository.HabitacionRepository;
import com.Hotelreservations.Hotelreservations.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    private ClienteRepository clienteRepository;
    private HabitacionRepository habitacionRepository;
    private ReservaRepository reservaRepository;

    @Autowired
    public ReservaService(ClienteRepository clienteRepository, HabitacionRepository habitacionRepository, ReservaRepository reservaRepository) {
        this.clienteRepository = clienteRepository;
        this.habitacionRepository = habitacionRepository;
        this.reservaRepository = reservaRepository;
    }
public boolean validarIdHabitacion(List<Habitacion> disponibles, long id) {
    boolean habitacionDisponible = disponibles.stream()
            .anyMatch(habitacion -> habitacion.getId() == id);

    if (!habitacionDisponible) {
        // La habitación con el id especificado no está disponible
        return false;
    }
    return habitacionDisponible;
}
    public boolean validarFormatoFecha(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(fecha, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    public Reserva generar(String fecha, long id, long cedula) {
        if(validarFormatoFecha(fecha)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(fecha, formatter);
            Optional<Habitacion> habitacion = habitacionRepository.findById(id);
            Optional<Cliente> cliente = clienteRepository.findById(cedula);
            // validacion que existen habitacion y cliente enviados para la reserva
                if (habitacion.isPresent() && cliente.isPresent()) {
                    List<Habitacion> disponibles = (validarDisponibilidadFecha(fecha));
                    boolean habitacionValido = validarIdHabitacion(disponibles, id);
                        if (habitacionValido) {
                            Cliente clienteQueReserva = cliente.get();
                            Habitacion habitacionReservada = habitacion.get();
                            double totalPagar = calcularPrecioTotal(habitacionReservada);
                            Reserva reserva1 = new Reserva(habitacionReservada, clienteQueReserva, date, totalPagar);
                            this.reservaRepository.save(reserva1);
                            return reserva1;

                        } else {
                            throw new ApiRequestException("Habitacion no disponible para esa fecha");
                        }
                } else {
                    throw new ApiRequestException("Cliente o habitacion nulas");
                }
        }else {
            throw new ApiRequestException("Formato fecha invalido");
        }
   }


    public double calcularPrecioTotal(Habitacion habitacion) {
        double precioBase = habitacion.getPrecioBase();
        String tipo = String.valueOf(habitacion.getTipo());
        if (tipo.equalsIgnoreCase("ESTANDAR")) {
            return precioBase;
        } else {
            double descuento = precioBase * 0.05;
            return precioBase - descuento;
        }
    }
    public List<Habitacion> validarDisponibilidadFecha(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(fecha, formatter);
        List<Habitacion> disponibles = new ArrayList<>();

        // Obtener todas las habitaciones
        List<Habitacion> habitaciones = (List<Habitacion>) habitacionRepository.findAll();

        // Obtener las reservas para la fecha especificada
        List<Reserva> reservas = reservaRepository.findByFechaReserva(date);

        // Filtrar las habitaciones disponibles
        for (Habitacion habitacion : habitaciones) {
            boolean disponible = true;
            for (Reserva reserva : reservas) {
                if (reserva.getHabitacion().equals(habitacion)) {
                    disponible = false;
                    break;
                }
            }
            if (disponible) {
                disponibles.add(habitacion);
            }
        }
        return disponibles;
    }
    public List<Habitacion> validarDisponibilidadFechaPremium(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(fecha, formatter);
        List<Habitacion> disponiblesPremium = new ArrayList<>();
        List<Habitacion> disponibles = validarDisponibilidadFecha(fecha);

        disponiblesPremium = disponibles.stream()
                .filter(habitacion -> habitacion.getTipo() == TipoHabitacion.PREMIUM)
                .collect(Collectors.toList());

        return disponiblesPremium;
    }

    public List<Habitacion> validarDisponibilidadFechaEstandar(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(fecha, formatter);
        List<Habitacion> disponiblesEstandar = new ArrayList<>();
        List<Habitacion> disponibles = validarDisponibilidadFecha(fecha);

        disponiblesEstandar = disponibles.stream()
                .filter(habitacion -> habitacion.getTipo() == TipoHabitacion.ESTANDAR)
                .collect(Collectors.toList());

        return disponiblesEstandar;
    }

    public List<Reserva> verReservasCliente(long cedula) {
        List<Reserva> listaDeTodasLasReservas = (List<Reserva>) reservaRepository.findAll();

        List<Reserva> listaReservasId = listaDeTodasLasReservas.stream()
                .filter(reserva -> reserva.getCliente().getCedula() == cedula)
                .collect(Collectors.toList());

        return listaReservasId;
    }

}
