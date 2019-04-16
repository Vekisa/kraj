package xmlb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xmlb.model.User.User;
import xmlb.model.User.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByVerificationToken (String token);

    VerificationToken findByUser (User user);

}
