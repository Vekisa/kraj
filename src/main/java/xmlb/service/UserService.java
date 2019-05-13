package xmlb.service;

import net.bytebuddy.asm.Advice;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import xmlb.model.Company;
import xmlb.model.User.Group;
import xmlb.model.User.Role;
import xmlb.model.User.User;
import xmlb.model.User.VerificationToken;
import xmlb.repository.UserRepository;
import xmlb.repository.VerificationTokenRepository;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    protected final Log LOGGER = LogFactory.getLog(getClass());


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private GroupService groupService;

    private String getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByUsername(auth.getName());
        return user.get().getUsername();
    }

    public boolean checkIfEmailExists(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            LOGGER.info("IN FUNC: TRUE");
            return true;
        }
        LOGGER.info("IN FUNC: FALSE");
        return false;
    }

    public List<User> search(String text){
        List<User> users = userRepository.findAll();
        List<User> list = new ArrayList<>();

        for(User user : users){
            if(user.getFirstName().contains(text) || user.getLastName().contains(text) || user.getUsername().contains(text)){
                list.add(user);
            }
        }
        return list;
    }

    public User getUser(String verificationToken) {
        User user = tokenRepository.findByVerificationToken(verificationToken).getUser();
        return user;
    }

    public VerificationToken getToken(String VerificationToken) {
        return tokenRepository.findByVerificationToken(VerificationToken);
    }


    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        user.setVerificationToken(myToken);
        tokenRepository.save(myToken);
    }


    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);

    }

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> getAll(){
        List<User> users = userRepository.findAll();
        User user = null;
        for(User usr : users){
            if(usr.getId() == 1) {
                user = usr;
                break;
            }
        }

        users.remove(user);

        LOGGER.info("IN FUNC: Success");
        return users;
    }

    public User enableUser(Long id){
        System.out.println("BBB");
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) {
            LOGGER.error("IN FUNC: Requested user does not exist");
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested user does not exist");
        }
            user.get().setEnabled(true);
        userRepository.save(user.get());

        LOGGER.info("IN FUNC: Success");
        return user.get();
    }

    public User disableUser(Long id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) {
            LOGGER.error("IN FUNC: Requested user does not exist");
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested user does not exist");
        }
            user.get().setEnabled(false);
        userRepository.save(user.get());

        LOGGER.info("IN FUNC: Success");
        return user.get();
    }

    public User getUser(Principal user){

        Optional<User> optionalUser = userRepository.findByUsername(user.getName());


        if(!optionalUser.isPresent()) {
            LOGGER.error("IN FUNC: Requested user does not exist");
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested user does not exist");
        }

        LOGGER.info("IN FUNC: Success");
        return optionalUser.get();

    }

    public User saveCompany(Long id, Company company){
        Optional<User> optionalUser = userRepository.findById(id);

        if(!optionalUser.isPresent()) {
            LOGGER.error("IN FUNC: Requested user does not exist");
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested user does not exist");
        }
        User user = optionalUser.get();
        user.setCompany(company);

        LOGGER.info("IN FUNC: Success");
        return userRepository.save(user);
    }

    public User getUser(Long id){

        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            LOGGER.error("IN FUNC: Requested user does not exist");
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested user does not exist");

        }
        LOGGER.info("IN FUNC: Success");
        return optionalUser.get();
    }

    public User rolesToUser(Long id,List<Long> ids){

        User user = getUser(id);

        for (Long idRole:ids){

            Role role = roleService.getRole(idRole);

            role.getUsers().add(user);
            roleService.saveRole(role);

            user.getRoles().add(role);
        }


        LOGGER.info("IN FUNC: Success");
        return userRepository.save(user);

    }

    public User groupsToUser(Long id,List<Long> ids){

        User user = getUser(id);

        for (Long idGroup:ids){
            Group group = groupService.getGroup(idGroup);
            group.getUsers().add(user);

            groupService.saveGroup(group);
            user.getGroup().add(group);
        }

        LOGGER.info("IN FUNC: Success");

        return userRepository.save(user);
    }

    public User removeRoleFromUser(Long userId,Long roleId){
        User user = getUser(userId);
        Role role = roleService.getRole(roleId);

        user.getRoles().remove(role);
        role.getUsers().remove(user);

        roleService.saveRole(role);

        LOGGER.info("IN FUNC: Success");

        return userRepository.save(user);

    }

    public User removeGroupFromUser(Long userId,Long groupId){
        User user = getUser(userId);
        Group group = groupService.getGroup(groupId);

        user.getGroup().remove(group);
        group.getUsers().remove(user);

        groupService.saveGroup(group);


        LOGGER.info("IN FUNC: Success");
        return userRepository.save(user);

    }

}
