package xmlb.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import xmlb.model.Certificate;
import xmlb.model.Company;
import xmlb.repository.CompanyRepository;

import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Boolean hasCertificateWithAlias(Long id, String alias){
        Optional<Company> company = companyRepository.findById(id);
        if(!company.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company does not exist");

        for(Certificate certificate : company.get().getCertificates()){
            if(certificate.getAlias().equals(alias))
                return true;
        }

        return false;
    }
}
