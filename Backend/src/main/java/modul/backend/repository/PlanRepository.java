package modul.backend.repository;

import modul.backend.model.Plan;
import org.springframework.data.repository.CrudRepository;

public interface PlanRepository extends CrudRepository<Plan,Long> {
}
