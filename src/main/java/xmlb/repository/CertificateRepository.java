package xmlb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xmlb.model.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {

    Certificate findByAlias(String alias);
    Certificate findByCompany(String company);
    boolean existsByCompany(String company);
}
