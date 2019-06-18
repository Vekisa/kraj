package modul.auth.service;

import modul.auth.model.Users.Role;
import modul.auth.model.Users.User;
import modul.auth.repository.RoleRepository;
import modul.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private Logging logging = new Logging(getClass());

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

   /* @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private EndPointsService endPointsService;*/

    @Autowired
    private UserService userService;


    public void deleteRole(Long id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByUsername(auth.getName());

        Optional<Role> role = roleRepository.findById(id);
        if (!role.isPresent()) {
            logging.printError("IN FUNC: Role does not exist");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role does not exist");
        }
        logging.printInfo("IN FUNC: Success");

        roleRepository.deleteById(id);
    }

    public List<Role> allRoles() {


        List<Role> allRoles = roleRepository.findAll();

        Optional<Role> role = roleRepository.findByName("ROLE_MAIN_ADMIN");

        if (!role.isPresent()) {
            logging.printError("IN FUNC: Requested role does not exist");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role doesn't exist!");
        }
        allRoles.remove(role.get());

        logging.printInfo("IN FUNC: Success");
        return allRoles;

    }


}
