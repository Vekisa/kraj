package modul.oauth.repository;

import modul.oauth.model.User;
import modul.oauth.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByVerificationToken(String token);

    VerificationToken findByUser(User user);

}
