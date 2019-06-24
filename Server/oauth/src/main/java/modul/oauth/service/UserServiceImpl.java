package modul.oauth.service;

import com.sun.org.apache.regexp.internal.RE;
import modul.oauth.model.Users.RegisteredUser;
import modul.oauth.model.Users.Role;
import modul.oauth.model.Users.User;
import modul.oauth.model.Users.VerificationToken;
import modul.oauth.repository.RoleRepository;
import modul.oauth.repository.UserRepository;
import modul.oauth.repository.VerificationTokenRepository;
import modul.oauth.security.EmailSenderService;
import modul.oauth.security.PasswordHashingService;
import modul.oauth.security.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

@Service("userService")
public class UserServiceImpl implements UserDetailsService {

    private Logging logging = new Logging(getClass());

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = getUser(username);

        System.out.println("USAO U USER");

        return user;
    }

    public User getUser(String username){
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with username or email : " + username));
        return user;
    }

    public Map<String, Object> getUserInfo (OAuth2Authentication user) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", user.getUserAuthentication().getPrincipal());
        userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
        return userInfo;
    }

    public User getUser(OAuth2Authentication oAuth2Authentication){

        String username = oAuth2Authentication.getUserAuthentication().getName();

        User user = getUser(username);

        return user;
    }


    public RegisteredUser addUser(SignUpRequest signUpRequest){

        String passwordHash = "";

        passwordHash = passwordEncoder.encode(signUpRequest.getPassword());


        // Creating user's account

        Role role = roleRepository.findByName("ROLE_REG")
                .orElseThrow(() -> new RuntimeException("Role can't be found!"));

        RegisteredUser registeredUser = new RegisteredUser(signUpRequest.getUsername(),
                signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getEmail(), passwordHash, null, false, false, new ArrayList<Role>());

        registeredUser.getRoles().add(role);
        //role.getUsers().add(registeredUser);

        String token = UUID.randomUUID().toString();
        userRepository.save(registeredUser);
        createVerificationToken(registeredUser, token);

        emailSenderService.sendCompleteRegistration(registeredUser);

        logging.printInfo("User "+ registeredUser.getUsername() +  " registered.");

        return registeredUser;
    }

    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        user.setVerificationToken(myToken);
        verificationTokenRepository.save(myToken);
    }

    public String confirmUser(String token){



        VerificationToken verificationToken = getToken(token);

        Calendar cal = Calendar.getInstance();

        if (verificationToken==null){
            return "invalid";
        }else if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0){
            return "expired";
        }

        User user = verificationToken.getUser();

        logging.printInfo("User " + user.getUsername() + " confirming email .");

        user.setVerified(true);
        user.setEnabled(true);
        userRepository.save(user);

        return "pass";

    }

    public VerificationToken getToken(String VerificationToken) {
        return verificationTokenRepository.findByVerificationToken(VerificationToken);
    }

    public boolean checkByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public boolean checkByUsername(String username){
        return userRepository.existsByUsername(username);
    }



}
