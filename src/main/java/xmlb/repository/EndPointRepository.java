package xmlb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xmlb.model.User.EndPoint;

public interface EndPointRepository extends JpaRepository<EndPoint, Long> {

    EndPoint findByUrl(String url);
}
