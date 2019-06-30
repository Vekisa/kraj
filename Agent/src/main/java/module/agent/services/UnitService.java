package module.agent.services;

import module.agent.model.*;
import module.agent.repository.AgentRepository;
import module.agent.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnitService {
    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AgentRepository agentRepository;

    public Unit create(Unit unit, OAuth2Authentication oAuth){

        if(!checkUnitAgent(unit, oAuth))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong agent!");


        return unitRepository.save(unit);
    }

    public Unit update(Long unit, PriceSchedule priceSchedule, OAuth2Authentication oAuth){
        User u= userService.getUser(oAuth.getName());
        System.out.println("ID korisnik "+u.getId());
        Agent a= agentRepository.findById(u.getId()).get();
        Unit im=unitRepository.findById(unit).get();
        if(!checkUnitAgent(im, oAuth))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong agent!");


        im.getPriceSchedule().add(priceSchedule);
        return unitRepository.save(im);
    }

    public Unit findById(Long id, OAuth2Authentication oAuth) {
        User u= userService.getUser(oAuth.getName());
        Agent a= agentRepository.findById(u.getId()).get();
        Unit unit= unitRepository.findById(id).get();
        if(checkUnitAgent(unit, oAuth))
            return unit;

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong agent!");
    }


    public List<Unit> getAll(OAuth2Authentication oAuth){
        User user= userService.getUser(oAuth.getName());
        Agent a= agentRepository.findById(user.getId()).get();
        List<Unit> p=new ArrayList<>();
        for(Unit u: unitRepository.findAll()){
            if(checkUnitAgent(u, oAuth))
                p.add(u);
        }
        return p;
    }

    public List<Image> getImages(Long id, OAuth2Authentication oAuth){
        User user= userService.getUser(oAuth.getName());
        Agent a= agentRepository.findById(user.getId()).get();
        if(checkUnitAgent(unitRepository.findById(id).get(), oAuth))
            return unitRepository.findById(id).get().getImage();

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong agent!");
    }

    public boolean checkUnitAgent(Unit unit, OAuth2Authentication oAuth2Authentication){
        User user= userService.getUser(oAuth2Authentication.getName());
        Agent a= agentRepository.findById(user.getId()).get();
        for(Agent p: unit.getObject().getAgent()){
            if(p.getId()==a.getId())
                return true;
        }
        return false;
    }
}
