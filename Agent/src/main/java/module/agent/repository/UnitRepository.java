package module.agent.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import module.agent.model.Unit;
public interface UnitRepository extends JpaRepository<Unit, Long> {
}
