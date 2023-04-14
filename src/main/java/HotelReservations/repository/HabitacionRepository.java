package HotelReservations.repository;

import HotelReservations.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, String> {
    Optional<Habitacion> findById(String id);

}
