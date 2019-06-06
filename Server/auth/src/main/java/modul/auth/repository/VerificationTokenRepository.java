package modul.auth.repository;

import modul.auth.model.User;
import modul.auth.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByVerificationToken(String token);

    VerificationToken findByUser(User user);

}
