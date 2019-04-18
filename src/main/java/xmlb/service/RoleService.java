package xmlb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import xmlb.model.User.EndPoint;
import xmlb.model.User.Group;
import xmlb.model.User.Role;
import xmlb.model.User.User;
import xmlb.repository.GroupRepository;
import xmlb.repository.RoleRepository;
import xmlb.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private EndPointsService endPointsService;

    @Autowired
    private UserService userService;

    public Role createRole(String name){
        Role role = new Role(name);
        roleRepository.save(role);
        return role;
    }

    public void deleteRole(Long id){
        Optional<Role> role = roleRepository.findById(id);
        if(!role.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role does nt exist");

        roleRepository.deleteById(id);
    }

    public List<Role> allRoles(){


        List<Role> allRoles = roleRepository.findAll();

        Optional<Role> role = roleRepository.findByName("ROLE_MAIN_ADMIN");

        if (!role.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role doesn't exist!");

        allRoles.remove(role.get());

        return allRoles;

    }

    public User addRoleToUser(Long userId, Long roleId){
        Optional<User> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(roleId);

        if(!user.isPresent() || !role.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad parameters");

        user.get().getRoles().add(role.get());

        userRepository.save(user.get());
        roleRepository.save(role.get());

        return user.get();
    }

    public User removeRoleFromUser(Long userId, Long roleId){
        Optional<User> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(roleId);

        if(!user.isPresent() || !role.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad parameters");

        user.get().getRoles().remove(role);

        userRepository.save(user.get());
        roleRepository.save(role.get());

        return user.get();
    }


    public Group removeRoleFromGroup(Long groupId, Long roleId){

        Group group = groupService.getGroup(groupId);

        Role role = getRole(roleId);

        group.getRoles().remove(role);

        roleRepository.save(role);

        return groupRepository.save(group);
    }


    public Role getRole(Long id){
        Optional<Role> optionalRole = roleRepository.findById(id);

        if(!optionalRole.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role doesn't exist!");

        return optionalRole.get();

    }

    public Role addEndPointsToRole(Long roleId,List<Long> endPointsId){

        Role role = getRole(roleId);

        List<EndPoint> roleEndPoints = role.getEndPoints();

        for (Long id:endPointsId){

            System.out.println("Id"+id);

            EndPoint endPoint = endPointsService.getEndPoint(id);

            System.out.println(endPoint);

            role.getEndPoints().add(endPoint);

            endPoint.getRoles().add(role);

            endPointsService.saveEndPoint(endPoint);

        }


        return roleRepository.save(role);
    }

    public Role deleteEndPointFromRole(Long id,Long endPointId){
        Role role = getRole(id);
        EndPoint endPoint = endPointsService.getEndPoint(endPointId);

        role.getEndPoints().remove(endPoint);
        endPoint.getRoles().remove(role);
        endPointsService.saveEndPoint(endPoint);

        return roleRepository.save(role);

    }

    public Role editRole(Long id,String name){

        Role role = getRole(id);

        role.setName(name);

        return roleRepository.save(role);
    }

    public List<Role> allAddedRoles(Long id){

        Group group = groupService.getGroup(id);

        List<Role> roles = group.getRoles();

        return roles;
    }

    public List<Role> allMissingRoles(Long id){

        Group group = groupService.getGroup(id);

        List<Role> allRoles = roleRepository.findAll();

        List<Role> roles = group.getRoles();

        for(Role role: roles){
            allRoles.remove(role);
        }

        return allRoles;
    }

    public List<Role> allAddedRolesUser(Long id){

        User user = userService.getUser(id);

        List<Role> roles =user.getRoles();

        return roles;
    }

    public List<Role> allMissingRolesUser(Long id){

        List<Role> allRoles = roleRepository.findAll();

        List<Role> roles = allAddedRolesUser(id);

        for(Role role: roles){
            allRoles.remove(role);
        }

        return allRoles;
    }


    public Role saveRole(Role role){
        return roleRepository.save(role);
    }


}
