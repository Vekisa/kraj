package xmlb.controller;

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
import xmlb.model.Role;
import xmlb.model.User;
import xmlb.model.VerificationToken;
import xmlb.repository.RoleRepository;
import xmlb.repository.UserRepository;
import xmlb.security.*;
import xmlb.service.PasswordHashingService;
import xmlb.service.UserDetailsCustomService;
import xmlb.service.UserService;
import xmlb.service.EmailSenderService;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

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


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJWToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
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




        Role role = roleRepository.findByName("ROLE_USER_REG")
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
