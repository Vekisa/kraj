package modul.backend.service;

import modul.backend.dto.UnitDTO;
import modul.backend.model.Unit;
import modul.backend.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UnitService {

    @Autowired
    private UnitRepository unitRepository;

    public UnitDTO findById(Long id){
        Optional<Unit> unit = unitRepository.findById(id);
        if(!unit.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unit does not exist");

        return new UnitDTO(unit.get());
    }

    public List<UnitDTO> getAll(){
        return DTOList.units(unitRepository.findAll());
    }
}
