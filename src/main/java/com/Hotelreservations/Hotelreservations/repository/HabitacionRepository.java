package com.Hotelreservations.Hotelreservations.repository;

import com.Hotelreservations.Hotelreservations.model.Habitacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HabitacionRepository extends CrudRepository <Habitacion, Long>{
    Optional<Habitacion> findById(Long id);
}
