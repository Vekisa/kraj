package modul.oauth.controller;

import modul.oauth.model.Users.Regex;
import modul.oauth.model.Users.RegisteredUser;
import modul.oauth.model.Users.User;
import modul.oauth.model.Users.VerificationToken;
import modul.oauth.security.ResponseMessage;
import modul.oauth.security.SignUpRequest;
import modul.oauth.service.Logging;
import modul.oauth.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
public class UserController {

    private Logging logging = new Logging(getClass());

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/roles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getUserInfo(OAuth2Authentication user){
        return new ResponseEntity<>(userService.getUserInfo(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(OAuth2Authentication user){
        return new ResponseEntity<>(userService.getUser(user), HttpStatus.OK);
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

        if (userService.checkByUsername(signUpRequest.getUsername())) {
            logging.printError("IN FUNC: Username is already taken");
            return new ResponseEntity<>(new ResponseMessage("Username is already taken!"), HttpStatus.BAD_REQUEST);
        }

        if (userService.checkByEmail(signUpRequest.getEmail())) {
            logging.printError("IN FUNC: Email is already in use");
            return new ResponseEntity<>(new ResponseMessage("Email is already in use!"), HttpStatus.BAD_REQUEST);
        }

        userService.addUser(signUpRequest);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }

    @RequestMapping(value = "/confirmToken")
    public ResponseEntity<?> confirmReg(@RequestParam("token") String token, HttpServletRequest hr) {
        //logging.printInfo("ENDPOINT: " + hr.getRequestURL() + " USER: " + userService.getCurrentUser() + " IP ADDRESS: " + hr.getRemoteAddr() + " PARAMETERS: " + token);


        String pass = userService.confirmUser(token);

        if (pass.equals("invalid")) {
            logging.printError("IN FUNC: Invalid token");
            return new ResponseEntity<>(new ResponseMessage("INVALID Token!"), HttpStatus.BAD_REQUEST);
        }


        if (pass.equals("expired")) {
            logging.printError("IN FUNC: Token expired");
            return new ResponseEntity<>(new ResponseMessage("Token Expired!"), HttpStatus.BAD_REQUEST);
        }


        logging.printInfo("Email confirmed");
        return new ResponseEntity<>(new ResponseMessage("Account verified successfully!"), HttpStatus.OK);

    }



}
