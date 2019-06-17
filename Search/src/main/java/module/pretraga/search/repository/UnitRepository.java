package module.pretraga.search.repository;


import module.pretraga.search.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UnitRepository extends JpaRepository<Unit, Long> {

    @Query("SELECT u FROM Unit u WHERE u.city = ?1 AND u.adults >= ?2")
    List<Unit> findByCityAndPersons(String city, Integer persons);
}
