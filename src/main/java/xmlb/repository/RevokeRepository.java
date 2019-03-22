package xmlb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import xmlb.model.Revoke;

import java.util.Collection;

public interface RevokeRepository extends JpaRepository<Revoke, Long> {
    @Modifying
    @Query(value = "select * from revokes where alias= ?1", nativeQuery = true)
    Collection<Revoke> findByAlias(String aliasString);
}
