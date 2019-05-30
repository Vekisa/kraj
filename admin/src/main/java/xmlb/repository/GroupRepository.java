package xmlb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xmlb.model.User.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
