package xmlb.service;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import xmlb.model.Certificate;
import xmlb.model.Company;
import xmlb.model.User;
import xmlb.repository.CompanyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserService userService;

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

    public List<Company> getAllCompanies(){
        List<Company> orgList = companyRepository.findAll();

        ArrayList<Company> sendList = new ArrayList<>();

        for (Company company : orgList){
                if (!company.getName().equals("rootComp")){
                    sendList.add(company);
                }
        }

        return sendList;
    }

    public User setCompany(Long id,Long companyId){

        if (companyId==0){
            return userService.saveCompany(id,null);
        }

        Optional<Company> optionalCompany = companyRepository.findById(companyId);

        if (!optionalCompany.isPresent())
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested company does not exist");

        return userService.saveCompany(id,optionalCompany.get());
    }
}
