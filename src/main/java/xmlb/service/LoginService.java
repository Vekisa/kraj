package xmlb.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlb.model.User.User;
import xmlb.repository.UserRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class LoginService {
    private final int MAX_IP = 10;
    private final int MAX_USER=3;
    private LoadingCache<String, Integer> attemptsCache;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    EmailSenderService emailSenderService;

    public LoginService() {
        super();
        attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build(new CacheLoader<String, Integer>() {
            @Override
            public Integer load(final String key) {
                return 0;
            }
        });
    }

    public void loginSucceeded(String key) {
        attemptsCache.invalidate(key);
    }

    public void loginSucceededU(String username) {
        Optional<User> u= userRepository.findByUsername(username);

        if(u.isPresent()){
           u.get().setNumF(0);
           userRepository.save(u.get());
        }

    }

    public void loginFailed(final String key) {
        int attempts;
        try {
            attempts = attemptsCache.get(key);
        } catch (final ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        attemptsCache.put(key, attempts);
    }

    public void loginFailedU(String username ){
        Optional<User> u= userRepository.findByUsername(username);

        if(u.isPresent()){
            u.get().setNumF(u.get().getNumF()+1);
            if(u.get().getNumF()>=MAX_USER) {
                u.get().setDateBlock(new Date());
                emailSenderService.sendWrongPassword(u.get());
            }
            userRepository.save(u.get());
        }
    }

    public boolean isBlockedIP(String key) {
        try {
            return attemptsCache.get(key) >= MAX_IP ;
        } catch (ExecutionException e) {
            return false;
        }
    }

    public boolean isBlockedUser(String username) {
        Optional<User> u= userRepository.findByUsername(username);

        if(u.isPresent()){
            if(u.get().getNumF()>=MAX_USER){
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                Date pom=u.get().getDateBlock();
                Date pom1= new Date(pom.getTime()+(24*60*60*1000));
                if((simpleDateFormat.format(pom1)).compareTo( simpleDateFormat.format(new Date())) < 0){
                    return false;
                }else{
                    return true;
                }
            }
        }
        return false;
    }
}
