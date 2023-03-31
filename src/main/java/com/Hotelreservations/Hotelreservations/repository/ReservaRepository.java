package com.Hotelreservations.Hotelreservations.repository;

import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.model.Reserva;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Long> {
        List<Reserva> findByFechaReserva(LocalDate fechaReserva);
}

