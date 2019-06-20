package modul.backend.service;

import modul.backend.dto.AccommodationTypeDTO;
import modul.backend.dto.ExtraOptionDTO;
import modul.backend.dto.ObjectDTO;
import modul.backend.model.AccommodationType;
import modul.backend.model.ExtraOption;
import modul.backend.model.Object;
import modul.backend.model.Unit;
import modul.backend.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ObjectService {

    @Autowired
    private ObjectRepository objectRepository;

    public ObjectDTO findById(Long id){
        Optional<Object> object = objectRepository.findById(id);
        if(!object.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Object does not exist");

        return new ObjectDTO(object.get());
    }

    public List<ObjectDTO> getAll(){
        return DTOList.objects(objectRepository.findAll());
    }

    public  List<ExtraOptionDTO> getExtraOptions(Long id){
        Optional<Object> object = objectRepository.findById(id);
        if(!object.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Object does not exist");

        return DTOList.extraOptions(object.get().getExtraOption());
    }

    public  List<AccommodationTypeDTO> getAccommodationTypes(Long id){
        Optional<Object> object = objectRepository.findById(id);
        if(!object.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Object does not exist");

        List<AccommodationType> accommodationTypes = new ArrayList<>();
        for(Unit unit : object.get().getUnit())
            if(!accommodationTypes.contains(unit.getAccommodationType()))
                accommodationTypes.add(unit.getAccommodationType());

        return DTOList.accommodationTypes(accommodationTypes);
    }

}