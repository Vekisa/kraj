package xmlb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import xmlb.model.User;
import xmlb.model.VerificationToken;
import xmlb.repository.UserRepository;
import xmlb.repository.VerificationTokenRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository tokenRepository;


    public boolean checkIfEmailExists(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }


    public User getUser(String verificationToken) {
        User user = tokenRepository.findByVerificationToken(verificationToken).getUser();
        return user;
    }

    public VerificationToken getToken(String VerificationToken) {
        return tokenRepository.findByVerificationToken(VerificationToken);
    }


    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        user.setVerificationToken(myToken);
        tokenRepository.save(myToken);
    }


    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);

    }

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> getAll(){
        List<User> users = userRepository.findAll();
        User user = null;
        for(User usr : users){
            if(usr.getId() == 1) {
                user = usr;
                break;
            }
        }

        users.remove(user);
        return users;
    }

    public User enableUser(Long id){
        System.out.println("BBB");
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested user does not exist");
        user.get().setEnabled(true);
        userRepository.save(user.get());
        return user.get();
    }

    public User disableUser(Long id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested user does not exist");
        user.get().setEnabled(false);
        userRepository.save(user.get());
        return user.get();
    }
}