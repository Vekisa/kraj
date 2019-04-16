package xmlb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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
        return roleRepository.findAll();
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

    public Group addRoleToGroup(Long groupId, Long roleId){
        Optional<Group> group = groupRepository.findById(groupId);
        Optional<Role> role = roleRepository.findById(roleId);

        if(!group.isPresent() || !role.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad parameters");

        group.get().getRoles().add(role.get());

        groupRepository.save(group.get());
        roleRepository.save(role.get());

        return group.get();
    }

    public Group removeRoleFromGroup(Long groupId, Long roleId){
        Optional<Group> group = groupRepository.findById(groupId);
        Optional<Role> role = roleRepository.findById(roleId);

        if(!group.isPresent() || !role.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad parameters");

        group.get().getRoles().remove(role.get());

        groupRepository.save(group.get());
        roleRepository.save(role.get());

        return group.get();
    }
}
