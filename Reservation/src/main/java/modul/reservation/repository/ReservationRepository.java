package modul.reservation.repository;

import modul.backend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
