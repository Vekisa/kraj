package modul.auth.service;

import modul.auth.model.Role;
import modul.auth.model.User;
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

    public User addRoleToUser(Long userId, Long roleId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(roleId);

        if (!user.isPresent() || !role.isPresent()) {
            logging.printError("IN FUNC: Bad parameters");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad parameters");
        }
        user.get().getRoles().add(role.get());

        userRepository.save(user.get());
        roleRepository.save(role.get());

        logging.printInfo("IN FUNC: Success");
        return user.get();
    }

    public User removeRoleFromUser(Long userId, Long roleId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(roleId);

        if (!user.isPresent() || !role.isPresent()) {
            logging.printError("IN FUNC: Bad parameters");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad parameters");
        }
        user.get().getRoles().remove(role);

        userRepository.save(user.get());
        roleRepository.save(role.get());

        logging.printInfo("IN FUNC: Success");
        return user.get();
    }


   /* public Group removeRoleFromGroup(Long groupId, Long roleId) {

        Group group = groupService.getGroup(groupId);

        Role role = getRole(roleId);

        group.getRoles().remove(role);

        roleRepository.save(role);

        logging.printInfo("IN FUNC: Success");
        return groupRepository.save(group);
    }
*/

    public Role getRole(Long id) {
        Optional<Role> optionalRole = roleRepository.findById(id);

        if (!optionalRole.isPresent()) {
            logging.printError("IN FUNC: Role doesn't exist!");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role doesn't exist!");
        }

        logging.printInfo("IN FUNC: Success");
        return optionalRole.get();

    }

    /*public Role addEndPointsToRole(Long roleId, List<Long> endPointsId) {

        Role role = getRole(roleId);

        List<EndPoint> roleEndPoints = role.getEndPoints();

        for (Long id : endPointsId) {

            System.out.println("Id" + id);

            EndPoint endPoint = endPointsService.getEndPoint(id);

            System.out.println(endPoint);

            role.getEndPoints().add(endPoint);

            endPoint.getRoles().add(role);

            endPointsService.saveEndPoint(endPoint);

        }

        logging.printInfo("IN FUNC: Success");
        return roleRepository.save(role);
    }*/

    /*public Role deleteEndPointFromRole(Long id, Long endPointId) {
        Role role = getRole(id);
        EndPoint endPoint = endPointsService.getEndPoint(endPointId);

        role.getEndPoints().remove(endPoint);
        endPoint.getRoles().remove(role);
        endPointsService.saveEndPoint(endPoint);

        logging.printInfo("IN FUNC: Success");
        return roleRepository.save(role);

    }

    public Role editRole(Long id, String name) {

        Role role = getRole(id);

        role.setName(name);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByUsername(auth.getName());
        logging.printInfo("IN FUNC: Success");

        return roleRepository.save(role);
    }

    public List<Role> allAddedRoles(Long id) {

        Group group = groupService.getGroup(id);

        List<Role> roles = group.getRoles();

        return roles;
    }

    public List<Role> allMissingRoles(Long id) {

        Group group = groupService.getGroup(id);

        List<Role> allRoles = roleRepository.findAll();

        List<Role> roles = group.getRoles();

        for (Role role : roles) {
            allRoles.remove(role);
        }

        return allRoles;
    }

    public List<Role> allAddedRolesUser(Long id) {

        User user = userService.getUser(id);

        List<Role> roles = user.getRoles();

        return roles;
    }

    public List<Role> allMissingRolesUser(Long id) {

        List<Role> allRoles = roleRepository.findAll();

        List<Role> roles = allAddedRolesUser(id);

        for (Role role : roles) {
            allRoles.remove(role);
        }

        return allRoles;
    }*/

    public Role saveRole(Role role) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByUsername(auth.getName());
        logging.printInfo("IN FUNC: Success");

        return roleRepository.save(role);
    }


}
