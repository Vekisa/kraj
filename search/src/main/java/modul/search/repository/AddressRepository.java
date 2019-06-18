package modul.search.repository;

import modul.search.model.Adress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Adress, Long> {
}
