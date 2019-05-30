package xmlb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xmlb.model.Certificate;

import java.util.Collection;
import java.util.Optional;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    Certificate findByAlias(String alias);

    Certificate findByCompany(String company);

    Certificate findBySerialNumber(String serialNumber);
}
