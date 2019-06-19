package module.agent.services;

import module.agent.model.Unit;
import module.agent.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {
    @Autowired
    private UnitRepository unitRepository;

    public Unit create(Unit unit){
        return unitRepository.save(unit);
    }

    public List<Unit> getAll(){
        return unitRepository.findAll();
    }
}
