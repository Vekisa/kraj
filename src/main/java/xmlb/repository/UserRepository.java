package xmlb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xmlb.model.User.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User findByEmail(String email);
    Optional<User> findById(Long id);


}
