package modul.oauth.service;

import modul.oauth.dto.AgentDTO;
import modul.oauth.model.*;
import modul.oauth.repository.RoleRepository;
import modul.oauth.repository.UserRepository;
import modul.oauth.repository.VerificationTokenRepository;
import modul.oauth.security.AuthorizationServerConfig;
import modul.oauth.security.EmailSenderService;
import modul.oauth.security.PasswordHashingService;
import modul.oauth.security.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Service;

import java.lang.Object;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

@Service("userService")
public class UserServiceImpl implements UserDetailsService {

    private Logging logging = new Logging(getClass());


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthorizationServerConfig authorizationServerConfig;

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

        Map<String, java.lang.Object> userInfo = new HashMap<>();
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

        RegisteredUser registeredUser = new RegisteredUser(
                signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getEmail(),signUpRequest.getUsername(),passwordHash,null,false,null, false, new HashSet<Role>());

        registeredUser.getRole().add(role);
        //role.getUsers().add(registeredUser);

        String token = UUID.randomUUID().toString();
        userRepository.save(registeredUser);
        createVerificationToken(registeredUser, token);

        emailSenderService.sendCompleteRegistration(registeredUser);

        logging.printInfo("User "+ registeredUser.getUsername() +  " registered.");

        return registeredUser;
    }

    public Agent addAgent(AgentDTO agentDto){

        String passwordHash = "";

        passwordHash = passwordEncoder.encode(agentDto.getPassword());

        Role role_agent = roleRepository.findByName("ROLE_AGENT")
                .orElseThrow(() -> new RuntimeException("Role can't be found!"));

        Role role_reg = roleRepository.findByName("ROLE_REG")
                .orElseThrow(() -> new RuntimeException("Role can't be found!"));

        Agent agent = new Agent(agentDto.getFirstName(),agentDto.getLastName(),
                agentDto.getEmail(),agentDto.getUsername(),passwordHash,null,false,null,false,new HashSet<Role>(),agentDto.getBussinesRegistrationNumber());

        agent.getRole().add(role_agent);
        agent.getRole().add(role_reg);

        String token = UUID.randomUUID().toString();
        userRepository.save(agent);
        createVerificationToken(agent, token);

        emailSenderService.sendCompleteAgentRegistration(agentDto,agent.getVerificationToken());

        logging.printInfo("Agent "+ agent.getUsername() +  " registered.");

        return agent;
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

        user.setIsVerified(true);
        user.setIsEnabled(true);
        userRepository.save(user);

        return "pass";

    }

    public VerificationToken getToken(String VerificationToken) {
        return verificationTokenRepository.findByVerificationToken(VerificationToken);
    }


    public void changeEmail(String email){

        Authentication current = SecurityContextHolder.getContext().getAuthentication();
        String username = current.getName();

        if (authenticationManager == null){
            return;
        }

        User user = getUser(username);
        user.setEmail(email);

        userRepository.save(user);
    }

    public void changeName(String first,String last){


        Authentication current = SecurityContextHolder.getContext().getAuthentication();
        String username = current.getName();

        if (authenticationManager == null){
            return;
        }

        User ud = (User) loadUserByUsername(username);
        ud.setFirstName(first);
        ud.setLastName(last);

        userRepository.save(ud);
    }

    public void changePass(String oldPass,String newPass) {

        Authentication current = SecurityContextHolder.getContext().getAuthentication();
        String username = current.getName();

        User user = (User) loadUserByUsername(username);

        if (!passwordEncoder.matches(oldPass,user.getPassword())) {
            throw new RuntimeException("Password doesn't match!");

        }else{
            user.setPassword(passwordEncoder.encode(newPass));

            user.setLastPasswordResetDate(new Date());

            userRepository.save(user);
        }



    }

    public OAuth2AccessToken changeUsername(OAuth2Authentication oAuth2Authentication,String username){



        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("as466gf");

        JwtTokenStore tokenStore = new JwtTokenStore(converter);


        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore);
        defaultTokenServices.setTokenEnhancer(converter);
        defaultTokenServices.setSupportRefreshToken(true);

        User user = getUser(oAuth2Authentication.getUserAuthentication().getName());

        user.setUsername(username);

        userRepository.save(user);

        UserDetails ud = loadUserByUsername(username);

       OAuth2Authentication oAuth2Authentication1 = new OAuth2Authentication(oAuth2Authentication.getOAuth2Request(),new UsernamePasswordAuthenticationToken(
              ud,"N/A",ud.getAuthorities()
       ));

        OAuth2AccessToken token = defaultTokenServices.createAccessToken(oAuth2Authentication1);


        return token ;
    }


    public boolean checkByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public boolean checkByUsername(String username){
        return userRepository.existsByUsername(username);
    }



}
