package module.pretraga.search.repository;

import module.pretraga.search.model.AccommodationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Date;

public interface AccommodationTypeRepository extends JpaRepository<AccommodationType, Long> {
}