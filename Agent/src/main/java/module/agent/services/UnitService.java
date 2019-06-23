package module.agent.services;

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

}
