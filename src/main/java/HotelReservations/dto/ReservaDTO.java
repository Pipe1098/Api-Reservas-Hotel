package HotelReservations.dto;

import java.time.LocalDate;

public class ReservaDTO {

    private  HabitacionDTO habitacionDTO;
    private  ClienteDTO clienteDTO;
    private  LocalDate fechaReserva;
    private  double totalAPagar;

    public ReservaDTO(HabitacionDTO habitacionDTO, ClienteDTO clienteDTO, LocalDate fechaReserva, double totalAPagar) {

        this.habitacionDTO = habitacionDTO;
        this.clienteDTO = clienteDTO;
        this.fechaReserva = fechaReserva;
        this.totalAPagar = totalAPagar;
    }

    public ReservaDTO() {
    }

    public HabitacionDTO getHabitacionDTO() {
        return habitacionDTO;
    }

    public ClienteDTO getClienteDTO() {
        return clienteDTO;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public double getTotalAPagar() {
        return totalAPagar;
    }

}
