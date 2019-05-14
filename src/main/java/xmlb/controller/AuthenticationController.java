package xmlb.controller;

import javafx.scene.shape.PathElement;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import xmlb.model.Regex;
import xmlb.model.User.Role;
import xmlb.model.User.User;
import xmlb.model.User.VerificationToken;
import xmlb.repository.RoleRepository;
import xmlb.repository.UserRepository;
import xmlb.security.*;
import xmlb.service.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;
import java.util.regex.Pattern;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    protected final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JWToken jwtProvider;

    @Autowired
    UserService userService;

    @Autowired
    UserDetailsCustomService userDetails;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    LoginService loginService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        String ip = userDetails.getClientIP();
        if (loginService.isBlocked(ip)) {
            System.out.println("Blokirano!");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Bad parameters");
        }else{
            System.out.println("NJet!!!!!!!!!!!!!!");
        }

        if(!Pattern.matches(Regex.username,loginRequest.getUsername()) || !Pattern.matches(Regex.password,loginRequest.getPassword())){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Bad parameters");
        }


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJWToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        LOGGER.info("Usepsno logovanje " + loginRequest.getUsername() );
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        if(!Pattern.matches(Regex.flNames,signUpRequest.getFirstName()) || !Pattern.matches(Regex.flNames,signUpRequest.getLastName()) ||
                !Pattern.matches(Regex.password,signUpRequest.getPassword()) || !Pattern.matches(Regex.email,signUpRequest.getEmail()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad parameters");

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Username is already taken!"), HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Email is already in use!"), HttpStatus.BAD_REQUEST);
        }

        String passwordHash = "";
        try {
            passwordHash = PasswordHashingService.generateStrongPasswordHash(signUpRequest.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        // Creating user's account
        User user = new User( signUpRequest.getUsername(), passwordHash,
                signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getEmail(),false,null,false);




        Role role = roleRepository.findByName("ROLE_REG")
                .orElseThrow(() -> new RuntimeException("Role can't be found!"));

        user.getRoles().add(role);
        role.getUsers().add(user);

        String token = UUID.randomUUID().toString();
        userRepository.save(user);
        userService.createVerificationToken(user, token);

        emailSenderService.sendCompleteRegistration(user);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }

    @RequestMapping(value = "/confirmReg")
    public ResponseEntity<?> confirmReg(@RequestParam("token") String token) {

        VerificationToken verificationToken = userService.getToken(token);
        if (verificationToken == null) {
            return new ResponseEntity<>(new ResponseMessage("INVALID Token!"), HttpStatus.BAD_REQUEST);
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return new ResponseEntity<>(new ResponseMessage("Token Expired!"), HttpStatus.BAD_REQUEST);
        }

        user.setVerified(true);
        userRepository.save(user);

        return new ResponseEntity<>(new ResponseMessage("Account verified successfully!"), HttpStatus.OK);

    }
}
