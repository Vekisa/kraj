package modul.auth.controller;

import modul.auth.model.Users.Regex;
import modul.auth.model.Users.RegisteredUser;
import modul.auth.model.Users.Role;
import modul.auth.model.Users.User;
import modul.auth.model.Users.VerificationToken;
import modul.auth.repository.RoleRepository;
import modul.auth.repository.UserRepository;
import modul.auth.security.*;
import modul.auth.service.*;
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


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private Logging logging = new Logging(getClass());

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
    AuthenticationService authenticationService;

    @Autowired
    UserDetailsCustomService userDetails;

    @Autowired
    EmailSenderService emailSenderService;

    //@Autowired
    //LoginService loginService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest hr) {
       /* logging.printInfo("ENDPOINT: " + hr.getRequestURL() + " USER: " + loginRequest.getUsername() + " IP ADDRESS: " + hr.getRemoteAddr() + " PARAMETERS: " + loginRequest.getUsername());
        String ip = userDetails.getClientIP();
        if (loginService.isBlockedIP(ip)) {
            logging.printError("IN FUNC: Ip is blocked");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ip is blocked");
        }

        if (loginService.isBlockedUser(loginRequest.getUsername())) {
            logging.printError("IN FUNC: User is blocked");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is blocked");
        }*/

        if (!Pattern.matches(Regex.username, loginRequest.getUsername()) || !Pattern.matches(Regex.password, loginRequest.getPassword())) {
            logging.printError("IN FUNC: Bad parameters");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad parameters");
        }

        logging.printInfo("IN FUNC: Success");
        return ResponseEntity.ok(authenticationService.authenticateUser(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest, HttpServletRequest hr) {
        logging.printInfo("ENDPOINT: " + hr.getRequestURL() + " USER: " + signUpRequest.getUsername() + " IP ADDRESS: "
                + hr.getRemoteAddr() + " PARAMETERS: " + signUpRequest.getEmail() + ", " + signUpRequest.getFirstName() + ", " + signUpRequest.getLastName() + ", " + signUpRequest.getEmail());
        if (!Pattern.matches(Regex.flNames, signUpRequest.getFirstName()) || !Pattern.matches(Regex.flNames, signUpRequest.getLastName()) ||
                !Pattern.matches(Regex.password, signUpRequest.getPassword()) || !Pattern.matches(Regex.email, signUpRequest.getEmail())) {
            logging.printError("IN FUNC: Bad parameters");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad parameters");
        }

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            logging.printError("IN FUNC: Username is already taken");
            return new ResponseEntity<>(new ResponseMessage("Username is already taken!"), HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            logging.printError("IN FUNC: Email is already in use");
            return new ResponseEntity<>(new ResponseMessage("Email is already in use!"), HttpStatus.BAD_REQUEST);
        }

        RegisteredUser registeredUser = userService.registerUser(signUpRequest);

        emailSenderService.sendCompleteRegistration(registeredUser);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }

    @RequestMapping(value = "/confirmReg")
    public ResponseEntity<?> confirmReg(@RequestParam("token") String token, HttpServletRequest hr) {
        //logging.printInfo("ENDPOINT: " + hr.getRequestURL() + " USER: " + userService.getCurrentUser() + " IP ADDRESS: " + hr.getRemoteAddr() + " PARAMETERS: " + token);
        VerificationToken verificationToken = userService.getToken(token);
        if (verificationToken == null) {
            logging.printError("IN FUNC: Inavlid token");
            return new ResponseEntity<>(new ResponseMessage("INVALID Token!"), HttpStatus.BAD_REQUEST);
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            logging.printError("IN FUNC: Token expired");
            return new ResponseEntity<>(new ResponseMessage("Token Expired!"), HttpStatus.BAD_REQUEST);
        }

        user.setVerified(true);
        userRepository.save(user);

        logging.printInfo("IN FUNC: Success");
        return new ResponseEntity<>(new ResponseMessage("Account verified successfully!"), HttpStatus.OK);

    }

    /*@RequestMapping(value = "/checkIP")
    public ResponseEntity<?> checkIP(HttpServletRequest hr) {
        if (loginService.isBlockedIP(hr.getRemoteAddr())) {
            logging.printError("IN FUNC: Ip is blocked");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ip is blocked");
        }
        return new ResponseEntity<>(new ResponseMessage("Account verified successfully!"), HttpStatus.OK);
    }

    @RequestMapping(value = "/checkUser")
    public ResponseEntity<?> checkUser(@RequestBody String username) {
        if (loginService.isBlockedUser(username)) {
            logging.printError("IN FUNC: User is blocked");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is blocked");
        }
        return new ResponseEntity<>(new ResponseMessage("Account verified successfully!"), HttpStatus.OK);
    }*/

}
