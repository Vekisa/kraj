package xmlb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import xmlb.model.Certificate;
import xmlb.model.User.EndPoint;
import xmlb.model.User.Group;
import xmlb.model.User.Role;
import xmlb.model.User.User;
import xmlb.repository.CertificateRepository;
import xmlb.repository.EndPointRepository;
import xmlb.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccesControllService {

    @Autowired
    private EndPointRepository endPointRepository;

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private UserRepository userRepository;

    public Boolean hasAccess(String s) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByUsername(auth.getName());
        List<Role> usersRoles = user.get().getRoles();

        for(Role role : usersRoles)
            if(role.getId() == 1)
                return true;

        String[] parts = s.split("/");
        String url = "/" + s.substring(3 + parts[0].length() + parts[1].length()+parts[2].length(),s.length());
        EndPoint endPoint = endPointRepository.findByUrl(url);
        List<Role> endPointRoles = endPoint.getRoles();

        for(Role role : usersRoles)
            if(endPointRoles.contains(role))
                return true;

        for(Group group : user.get().getGroup())
            for(Role role : group.getRoles())
                if(endPointRoles.contains(role))
                    return true;


                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access problem");
    }

    public boolean hasAccessToCertificate(String serialNumber){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByUsername(auth.getName());
        List<Role> usersRoles = user.get().getRoles();

        for(Role role : usersRoles)
            if(role.getId() == 1)
                return true;

        Certificate certificate = certificateRepository.findBySerialNumber(serialNumber);
        if(certificate.getCompany().getId() == user.get().getCompany().getId())
            return true;

        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access problem");
    }
}