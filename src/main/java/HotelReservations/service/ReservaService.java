package HotelReservations.service;

import HotelReservations.exception.ApiRequestException;
import HotelReservations.repository.ClienteRepository;
import HotelReservations.repository.HabitacionRepository;
import HotelReservations.repository.ReservaRepository;
import HotelReservations.dto.ClienteDTO;
import HotelReservations.dto.HabitacionDTO;
import HotelReservations.dto.ReservaDTO;
import HotelReservations.model.Cliente;
import HotelReservations.model.Habitacion;
import HotelReservations.model.Reserva;
import HotelReservations.model.TipoHabitacion;
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

    public boolean validarIdHabitacion(List<Habitacion> disponibles, String id) {
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

    public ReservaDTO generar(String fecha, String id, long cedula) {
        if (validarFormatoFecha(fecha)) {
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
                    ClienteDTO clienteDTO = new ClienteDTO(clienteQueReserva.getCedula(), clienteQueReserva.getNombre(),
                            clienteQueReserva.getApellido(), clienteQueReserva.getCorreoElectronico());
                    Habitacion habitacionReservada = habitacion.get();
                    HabitacionDTO habitacionDTO = new HabitacionDTO(habitacionReservada.getTipo(),habitacionReservada.getPrecioBase());
                    double totalPagar = calcularPrecioTotal(habitacionReservada);
                    Reserva reserva1 = new Reserva(habitacionReservada, clienteQueReserva, date, totalPagar);
                    this.reservaRepository.save(reserva1);
                    ReservaDTO reservaDTO = new ReservaDTO(habitacionDTO, clienteDTO, date, totalPagar);
                    return reservaDTO;

                } else {
                    throw new ApiRequestException("Habitacion no disponible para la fecha: "+fecha);
                }
            } else {
                throw new ApiRequestException("El cliente con cedula = "+ cedula+" o la habitacion con id = "+ id+ "no registrado/s");
            }
        } else {
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
        if (validarFormatoFecha(fecha)) {
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
            if(disponibles.isEmpty()){
                throw new ApiRequestException("No hay habitaciones disponibles para la fecha: "+fecha);
            }
            return disponibles;
        } else {
            throw new ApiRequestException("Formato fecha ingresado no es valido");
        }
    }

    public List<Habitacion> validarDisponibilidadFechaPremium(String fecha) {
        if (validarFormatoFecha(fecha)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(fecha, formatter);
            List<Habitacion> disponiblesPremium = new ArrayList<>();
            List<Habitacion> disponibles = validarDisponibilidadFecha(fecha);

            disponiblesPremium = disponibles.stream()
                    .filter(habitacion -> habitacion.getTipo() == TipoHabitacion.PREMIUM)
                    .collect(Collectors.toList());
            if(disponiblesPremium.isEmpty()){
                throw new ApiRequestException("No hay habitaciones de tipo premium disponibles para la fecha: "+fecha);
            }
            return disponiblesPremium;
        } else {
            throw new ApiRequestException("Formato fecha ingresado no es valido");
        }
    }

    public List<Habitacion> validarDisponibilidadFechaEstandar(String fecha) {
        if (validarFormatoFecha(fecha)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(fecha, formatter);
            List<Habitacion> disponiblesEstandar = new ArrayList<>();
            List<Habitacion> disponibles = validarDisponibilidadFecha(fecha);

            disponiblesEstandar = disponibles.stream()
                    .filter(habitacion -> habitacion.getTipo() == TipoHabitacion.ESTANDAR)
                    .collect(Collectors.toList());
            if(disponiblesEstandar.isEmpty()){
                throw new ApiRequestException("No hay habitaciones de tipo estandar disponibles para la fecha: "+fecha);
            }

            return disponiblesEstandar;
        } else {
            throw new ApiRequestException("Formato fecha  ingresado no es valido");
        }
    }

    public List<Reserva> verReservasCliente(long cedula) {
        List<Reserva> listaDeTodasLasReservas = (List<Reserva>) reservaRepository.findAll();
        if(listaDeTodasLasReservas.isEmpty()){
           throw new ApiRequestException("El cliente con cedula="+ cedula+" "+"no ha realizado ninguna reserva");
        }
        List<Reserva> listaReservasId = listaDeTodasLasReservas.stream()
                .filter(reserva -> reserva.getCliente().getCedula() == cedula)
                .collect(Collectors.toList());

        return listaReservasId;
    }

}
