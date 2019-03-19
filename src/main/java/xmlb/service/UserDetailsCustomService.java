package xmlb.service;


import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import xmlb.model.User;
import xmlb.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    protected final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with username or email : " + username));

        return user;

    }

    public void changePass(String oldPass,String newPass) {

        Authentication current = SecurityContextHolder.getContext().getAuthentication();
        String username = current.getName();

        if (authenticationManager !=null) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,oldPass));
        }else {
            return;
        }

        Optional<User> userOpt = userRepository.findByUsername(username);

        userOpt.get().setPassword(passwordEncoder.encode(newPass));

        userRepository.save(userOpt.get());


    }






}
