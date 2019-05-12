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

    protected final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private String getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByUsername(auth.getName());
        return user.get().getUsername();
    }

    public Boolean hasCertificateWithAlias(Long id, String alias){
        Optional<Company> company = companyRepository.findById(id);
        if(!company.isPresent()) {
            LOGGER.error("IN FUNC: Company does not exist");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company does not exist");
        }

        for(Certificate certificate : company.get().getCertificates()){
            if(certificate.getAlias().equals(alias)) {
                LOGGER.info("IN FUNC: TRUE");
                return true;
            }
        }

        LOGGER.info("IN FUNC: FALSE");
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

        LOGGER.info("IN FUNC: Success");
        return sendList;
    }

    public User setCompany(Long id,Long companyId){

        if (companyId==0){
            LOGGER.info("IN FUNC: Id = 0");
            return userService.saveCompany(id,null);
        }

        Optional<Company> optionalCompany = companyRepository.findById(companyId);

        if (!optionalCompany.isPresent()) {
            LOGGER.error("IN FUNC: Requested company does not exist");
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested company does not exist");
        }

        LOGGER.info("IN FUNC: Success");
        return userService.saveCompany(id,optionalCompany.get());
    }
}
