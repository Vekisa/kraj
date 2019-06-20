package module.agent.repository;

import module.agent.model.PriceSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceScheduleRepository extends JpaRepository<PriceSchedule, Long> {
}
