package modul.reservation.service;


import modul.reservation.repository.UserRepository;
import modul.reservation.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserDetailsService {


    @Autowired
    UserRepository userRepository;


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




}
