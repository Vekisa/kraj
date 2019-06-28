package module.agent.services;

import module.agent.model.Object;
import module.agent.model.Unit;
import module.agent.model.User;
import module.agent.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ObjectService {

    @Autowired
    private ObjectRepository objectRepository;
    @Autowired
    private UserServiceImpl userService;

    public Object create(Object object){


        return objectRepository.save(object);
    }

    public List<Object> getAll(){
        return objectRepository.findAll();
    }

    public List<Unit> getUnits(Long id, OAuth2Authentication oAuth2Authentication){
        Optional<Object> o=objectRepository.findById(id);
        List<Unit> p=new ArrayList<>();
        User user= userService.getUser(oAuth2Authentication.getName());
        for(Unit u: o.get().getUnit()){
            if(u.getAgent().equals(user))
                p.add(u);
        }
        return p;
    }
}
