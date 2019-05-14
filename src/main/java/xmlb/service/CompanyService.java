package xmlb.service;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import xmlb.model.Certificate;
import xmlb.model.Company;
import xmlb.model.User.User;
import xmlb.repository.CompanyRepository;
import xmlb.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private Logging logging = new Logging(getClass());

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserService userService;

    public Boolean hasCertificateWithAlias(Long id, String alias){
        Optional<Company> company = companyRepository.findById(id);
        if(!company.isPresent()) {
            logging.printError("IN FUNC: Company does not exist");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company does not exist");
        }

        for(Certificate certificate : company.get().getCertificates()){
            if(certificate.getAlias().equals(alias)) {
                logging.printInfo("IN FUNC: TRUE");
                return true;
            }
        }

        logging.printInfo("IN FUNC: FALSE");
        return false;
    }

    public List<Company> getAllCompanies(){
        List<Company> orgList = companyRepository.findAll();

        ArrayList<Company> sendList = new ArrayList<>();

        for (Company company : orgList){
                if (!company.getName().equals("root")){
                    sendList.add(company);
                }
        }

        logging.printInfo("IN FUNC: Success");
        return sendList;
    }

    public User setCompany(Long id,Long companyId){

        if (companyId==0){
            logging.printInfo("IN FUNC: Id = 0");
            return userService.saveCompany(id,null);
        }

        Optional<Company> optionalCompany = companyRepository.findById(companyId);

        if (!optionalCompany.isPresent()) {
            logging.printError("IN FUNC: Requested company does not exist");
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested company does not exist");
        }

        logging.printInfo("IN FUNC: Success");
        return userService.saveCompany(id,optionalCompany.get());
    }
}
