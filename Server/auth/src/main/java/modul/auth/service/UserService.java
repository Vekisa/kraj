package modul.auth.service;

import modul.auth.model.Users.RegisteredUser;
import modul.auth.model.Users.Role;
import modul.auth.model.Users.User;
import modul.auth.model.Users.VerificationToken;
import modul.auth.repository.RoleRepository;
import modul.auth.repository.UserRepository;
import modul.auth.repository.VerificationTokenRepository;
import modul.auth.security.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    private Logging logging = new Logging(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private VerificationTokenRepository tokenRepository;


    public String getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByUsername(auth.getName());
        return user.get().getUsername();
    }

    public boolean checkIfEmailExists(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            logging.printInfo("IN FUNC: TRUE");
            return true;
        }
        logging.printInfo("IN FUNC: FALSE");
        return false;
    }

    public List<User> search(String text) {
        List<User> users = userRepository.findAll();
        List<User> list = new ArrayList<>();

        for (User user : users) {
            if (user.getFirstName().contains(text) || user.getLastName().contains(text) || user.getUsername().contains(text)) {
                list.add(user);
            }
        }
        logging.printInfo("IN FUNC: Success");
        return list;
    }

    public User getUser(String verificationToken) {
        User user = tokenRepository.findByVerificationToken(verificationToken).getUser();
        return user;
    }

    public VerificationToken getToken(String VerificationToken) {
        return tokenRepository.findByVerificationToken(VerificationToken);
    }

    public RegisteredUser registerUser(SignUpRequest signUpRequest) {

        String passwordHash = "";
        try {
            passwordHash = PasswordHashingService.generateStrongPasswordHash(signUpRequest.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        // Creating user's account

        Role role = roleRepository.findByName("ROLE_REG")
                .orElseThrow(() -> new RuntimeException("Role can't be found!"));

        RegisteredUser registeredUser = new RegisteredUser(signUpRequest.getUsername(),
                signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getEmail(), passwordHash, null, true, true, new ArrayList<Role>());

        registeredUser.getRoles().add(role);
        //role.getUsers().add(registeredUser);

        String token = UUID.randomUUID().toString();
        userRepository.save(registeredUser);
        createVerificationToken(registeredUser, token);

        logging.printInfo("User registered.");

        return registeredUser;
    }


    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        user.setVerificationToken(myToken);
        tokenRepository.save(myToken);
    }


    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);

    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        User user = null;
        for (User usr : users) {
            if (usr.getId() == 1) {
                user = usr;
                break;
            }
        }

        users.remove(user);

        logging.printInfo("IN FUNC: Success");
        return users;
    }

    public User enableUser(Long id) {
        System.out.println("BBB");
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            logging.printInfo("Requested user does't exist.");
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested useruser does't exist");
        }
        user.get().setEnabled(true);
        userRepository.save(user.get());

        logging.printInfo("User enabled.");
        return user.get();
    }

    public User disableUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            logging.printError("IN FUNC: Requested user does not exist");
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested user does not exist");
        }
        user.get().setEnabled(false);
        userRepository.save(user.get());

        logging.printError("IN FUNC: Success");
        return user.get();
    }

    public User getUser(Principal user) {

        Optional<User> optionalUser = userRepository.findByUsername(user.getName());


        if (!optionalUser.isPresent()) {
            logging.printError("IN FUNC: Requested user does not exist");
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested user does not exist");
        }

        logging.printInfo("IN FUNC: Success");
        return optionalUser.get();

    }

    public User getUser(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            logging.printError("IN FUNC: Requested user does not exist");
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested user does not exist");

        }
        logging.printInfo("IN FUNC: Success");
        return optionalUser.get();
    }


}
