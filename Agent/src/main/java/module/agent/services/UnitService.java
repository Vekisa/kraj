package module.agent.services;

import module.agent.model.Image;
import module.agent.model.Unit;
import module.agent.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitService {
    @Autowired
    private UnitRepository unitRepository;

    public Unit create(Unit unit){
        return unitRepository.save(unit);
    }

    public Optional<Unit> findById(Long id) {
        return unitRepository.findById(id);
    }


    public List<Unit> getAll(){
        return unitRepository.findAll();
    }
    public List<Image> getImages(Long id){
        Unit unit=unitRepository.findById(id).get();
        System.out.println("unit " + unit.getId() + " br fotki " + unit.getImage().size() + " id je " + id);
        return unit.getImage();
    }
}
