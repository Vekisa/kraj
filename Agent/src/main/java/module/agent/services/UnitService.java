package module.agent.services;

import module.agent.model.Image;
import module.agent.model.PriceSchedule;
import module.agent.model.Unit;
import module.agent.model.User;
import module.agent.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UnitService {
    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private UserServiceImpl userService;

    public Unit create(Unit unit, OAuth2Authentication oAuth){
        User u= userService.getUser(oAuth.getName());
        System.out.println("ID korisnik "+u.getId());
        unit.setAgent(  u);
        System.out.println("ID "+unit.getAgent().getId());
        return unitRepository.save(unit);
    }

    public Unit update(Long unit, PriceSchedule priceSchedule){
        Unit im=unitRepository.findById(unit).get();
        im.getPriceSchedule().add(priceSchedule);
        return unitRepository.save(im);
    }

    public Optional<Unit> findById(Long id, OAuth2Authentication oAuth) {
        User a= userService.getUser(oAuth.getName());
        if(unitRepository.findById(id).get().getAgent().equals(a))
            return unitRepository.findById(id);
        else
            return null;
    }


    public List<Unit> getAll(OAuth2Authentication oAuth){
        User user= userService.getUser(oAuth.getName());
        List<Unit> p=new ArrayList<>();
        for(Unit u: unitRepository.findAll()){
            if(u.getAgent().equals(user))
                p.add(u);
        }
        return p;
    }

    public List<Image> getImages(Long id, OAuth2Authentication oAuth){
        User a= userService.getUser(oAuth.getName());
        if(unitRepository.findById(id).get().getAgent().equals(a))
            return unitRepository.findById(id).get().getImage();
        else
            return null;
    }
}
