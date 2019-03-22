package xmlb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xmlb.model.Revoke;

public interface RevokeRepository extends JpaRepository<Revoke, String> {
}
