package modul.auth.service;

import modul.auth.security.JWToken;
import modul.auth.security.JwtResponse;
import modul.auth.security.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private Logging logging = new Logging(getClass());

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWToken jwtProvider;


    public JwtResponse authenticateUser(LoginRequest loginRequest) {


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJWToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        logging.printInfo("User " + loginRequest.getUsername() + " authenticated.");

        return new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities());

    }

}
