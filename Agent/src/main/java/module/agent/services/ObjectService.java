package module.agent.services;

import module.agent.model.Agent;
import module.agent.model.Object;
import module.agent.model.Unit;
import module.agent.model.User;
import module.agent.model.web.ObjectWS;
import module.agent.repository.AgentRepository;
import module.agent.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ObjectService {

    @Autowired
    private ObjectRepository objectRepository;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private ObjectClient objectClient;

    public Object create(Object object, OAuth2Authentication oAuth2Authentication){
        User u= userService.getUser(oAuth2Authentication.getName());
        Agent a= agentRepository.findById(u.getId()).get();
        object.getAgent().add(a);
        System.out.println(objectClient.addObject(new ObjectWS(object)));
        return objectRepository.save(object);
    }

    public List<Object> getAll(OAuth2Authentication oAuth2Authentication){
        User u= userService.getUser(oAuth2Authentication.getName());
        Agent a= agentRepository.findById(u.getId()).get();
        List<Object> pom=new ArrayList<>();
        for(Object o:  objectRepository.findAll()){
            if(o.getAgent().contains(a))
                pom.add(o);
        }
        return pom;
    }

    public List<Unit> getUnits(Long id, OAuth2Authentication oAuth2Authentication){
        Optional<Object> o=objectRepository.findById(id);
        if(!o.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Object not found!");
        User user= userService.getUser(oAuth2Authentication.getName());
        Optional<Agent> a= agentRepository.findById(user.getId());
        if(o.get().getAgent().contains(a.get()))
            return o.get().getUnit();


        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong agent!");
    }
}
