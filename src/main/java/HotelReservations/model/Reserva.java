package HotelReservations.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @Column(name = "id_reserva")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_habitacion", nullable = false)
    private Habitacion habitacion;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Column(name = "fecha_reserva", nullable = false)
    private LocalDate fechaReserva;


    @Column(name = "total_a_pagar", nullable = false)
    private double totalAPagar;

    // Constructor vacío necesario para JPA
    public Reserva() {}

    public Reserva(Habitacion habitacion, Cliente cliente, LocalDate fechaReserva,  double totalAPagar) {

        this.habitacion = habitacion;
        this.cliente = cliente;
        this.fechaReserva = fechaReserva;
        this.totalAPagar = totalAPagar;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }



    public void setTotalAPagar(double totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    public Long getId() {
        return id;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }



    public double getTotalAPagar() {
        return totalAPagar;
    }
}


