package modul.backend.repository;


import modul.backend.model.Object;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ObjectRepository extends CrudRepository<Object, Long> {
}
