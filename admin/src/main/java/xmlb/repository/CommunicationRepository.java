package xmlb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xmlb.model.Communication;

public interface CommunicationRepository extends JpaRepository<Communication, Long> {

}
