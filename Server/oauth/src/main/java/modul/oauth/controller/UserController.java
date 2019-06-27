package modul.oauth.controller;

import modul.oauth.dto.AgentDTO;
import modul.oauth.model.*;
import modul.oauth.security.PasswordChange;
import modul.oauth.security.Regex;
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
import java.lang.Object;
import java.security.Principal;
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

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUserInfo(){
        return new ResponseEntity<>("TESTTTTTT", HttpStatus.OK);
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getUserInfo(OAuth2Authentication user){
        return new ResponseEntity<>(userService.getUserInfo(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(OAuth2Authentication user){
        return new ResponseEntity<>(userService.getUser(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/userPrincipal", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Principal> getPrincipal(Principal user){
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping("/auth/save")
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

    @PostMapping("/saveAgent")
    public ResponseEntity<?> saveAgent(@Valid @RequestBody AgentDTO agent, HttpServletRequest hr) {
        logging.printInfo("ENDPOINT: " + hr.getRequestURL() + " USER: " + agent.getUsername() + " IP ADDRESS: "
                + hr.getRemoteAddr() + " PARAMETERS: " + agent.getEmail() + ", " + agent.getFirstName() + ", " + agent.getLastName() + ", " + agent.getEmail());
        if (!Pattern.matches(Regex.flNames, agent.getFirstName()) || !Pattern.matches(Regex.flNames, agent.getLastName()) ||
                !Pattern.matches(Regex.password, agent.getPassword()) || !Pattern.matches(Regex.email, agent.getEmail())) {
            logging.printError("IN FUNC: Bad parameters");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad parameters");
        }

        if (userService.checkByUsername(agent.getUsername())) {
            logging.printError("IN FUNC: Username is already taken");
            return new ResponseEntity<>(new ResponseMessage("Username is already taken!"), HttpStatus.BAD_REQUEST);
        }

        if (userService.checkByEmail(agent.getEmail())) {
            logging.printError("IN FUNC: Email is already in use");
            return new ResponseEntity<>(new ResponseMessage("Email is already in use!"), HttpStatus.BAD_REQUEST);
        }

        userService.addAgent(agent);

        return new ResponseEntity<>(new ResponseMessage("Agent registered successfully!"), HttpStatus.OK);
    }


    @RequestMapping(value = "/auth/confirmToken")
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

    @RequestMapping(value = "/changeEmail", method = RequestMethod.PUT)
    public ResponseEntity<?> changeEmail(@RequestBody SignUpRequest signUpRequest) {

        if (signUpRequest.getEmail().isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("Can't be empty!"), HttpStatus.BAD_REQUEST);
        }

        if (signUpRequest.getEmail().length() > 60) {
            return new ResponseEntity<>(new ResponseMessage("Max 60 characters!"), HttpStatus.BAD_REQUEST);
        }

        if (userService.checkByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Email is already in use!"), HttpStatus.BAD_REQUEST);
        }

        userService.changeEmail(signUpRequest.getEmail());

        return new ResponseEntity<>(new ResponseMessage("Email changed!"), HttpStatus.OK);
    }

    @RequestMapping(value = "/changeName", method = RequestMethod.POST)
    public ResponseEntity<?> changeName(@RequestBody SignUpRequest signUpRequest) {


        String first = signUpRequest.getFirstName();
        String last = signUpRequest.getLastName();

        if (!Pattern.matches(Regex.flNames, signUpRequest.getFirstName()) || !Pattern.matches(Regex.flNames,signUpRequest.getLastName())) {
            logging.printError("IN FUNC: Bad parameters");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad parameters");
        }

        userService.changeName(first, last);

        return new ResponseEntity<>(new ResponseMessage("Name changed!"), HttpStatus.OK);
    }


    @RequestMapping(value = "/changePass", method = RequestMethod.PUT)
    public ResponseEntity<?> changePass(@RequestBody PasswordChange passwordChange) {

        userService.changePass(passwordChange.getOldPass(), passwordChange.getNewPass());

        return new ResponseEntity<>(new ResponseMessage("Password changed!"), HttpStatus.OK);

    }

    @RequestMapping(value = "/changeUsername", method = RequestMethod.PUT)
    public ResponseEntity<?> changeUsername(OAuth2Authentication auth2Authentication,@RequestBody RegisteredUser registeredUser) {

        if (userService.checkByUsername(registeredUser.getUsername())) {
            logging.printError("IN FUNC: Username is already taken");
            return new ResponseEntity<>(new ResponseMessage("Username is already taken!"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userService.changeUsername(auth2Authentication,registeredUser.getUsername()), HttpStatus.OK);

    }


}
