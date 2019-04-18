package xmlb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import xmlb.model.User.Group;
import xmlb.model.User.Role;
import xmlb.model.User.User;
import xmlb.repository.GroupRepository;
import xmlb.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;



    public Group createGroup(String name){
        Group group = new Group(name);
        groupRepository.save(group);
        return group;
    }

    public Group editGroup(Long id,String name){
        Group group = getGroup(id);

        group.setName(name);

        groupRepository.save(group);
        return group;
    }

    public Group getGroup(Long id){
        Optional<Group> group = groupRepository.findById(id);
        if(!group.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Group does not exist");

        return group.get();
    }

    public void deleteGroup(Long id){
        Optional<Group> group = groupRepository.findById(id);
        if(!group.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Group does not exist");

        groupRepository.deleteById(id);
    }

    public User addUserInGroup(Long userId, Long  groupId){
        Optional<User> user =userRepository.findById(userId);
        Optional<Group> group = groupRepository.findById(groupId);

        if(!user.isPresent() || !group.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad parameters");

        user.get().getGroup().add(group.get());

        userRepository.save(user.get());
        groupRepository.save(group.get());

        return user.get();
    }

    public User removeUserFromGroup(Long userId, Long  groupId){
        Optional<User> user =userRepository.findById(userId);
        Optional<Group> group = groupRepository.findById(groupId);

        if(!user.isPresent() || !group.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad parameters");

        group.get().getUsers().remove(user);

        userRepository.save(user.get());
        groupRepository.save(group.get());

        return user.get();
    }

    public List<User> usersFromGroup(Long id){
        Group group = getGroup(id);

        return group.getUsers();
    }

    public List<Group> allGroups(){
        return groupRepository.findAll();
    }


    public Group rolesToGroup(Long id,List<Long> ids){

        Group group = getGroup(id);

        for (Long roleId:ids){

            Role role = roleService.getRole(roleId);

            role.getGroups().add(group);
            group.getRoles().add(role);

            roleService.saveRole(role);

        }

        return groupRepository.save(group);

    }

    public List<Group> allGroupsUser(Long id){

        User user = userService.getUser(id);

        return user.getGroup();

    }

    public List<Group> allMissingGroupsUser(Long id){

        List<Group> groups = allGroupsUser(id);

        List<Group> allGroups = allGroups();

        for (Group group:groups){
            allGroups.remove(group);
        }

        return allGroups;
    }

    public Group saveGroup(Group group){

        return groupRepository.save(group);

    }



}
