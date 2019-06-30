package modul.backend.repository;

import modul.backend.model.PriceSchedule;
import modul.backend.model.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface PriceScheduleRepository extends CrudRepository<PriceSchedule, Long> {
}
