package xmlb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xmlb.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query(value = "SELECT * FROM company c WHERE  c.name = :name", nativeQuery = true)
    Company findCompanyByName(@Param("name") String name);

}
