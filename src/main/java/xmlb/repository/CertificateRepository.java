package xmlb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xmlb.model.CertificateDB;

public interface CertificateRepository extends JpaRepository<CertificateDB, Long> {

    CertificateDB findByAlias(String alias);

}
