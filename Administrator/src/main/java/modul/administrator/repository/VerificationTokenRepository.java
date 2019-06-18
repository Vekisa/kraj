package modul.administrator.repository;


import modul.administrator.model.User;
import modul.administrator.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByVerificationToken(String token);

    VerificationToken findByUser(User user);

}
