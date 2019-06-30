package modul.backend.repository;

import modul.backend.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    @Query(
            value = "SELECT * FROM reservation r WHERE r.unit_id = ?1", nativeQuery = true)
    List<Reservation> getReservationsForObject(Long objectId);

}
