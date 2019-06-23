package modul.backend.repository;

import modul.backend.model.AccommodationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationTypeRepository extends JpaRepository<AccommodationType, Long> {
}
