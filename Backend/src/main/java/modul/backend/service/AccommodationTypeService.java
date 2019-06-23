package modul.backend.service;

import modul.backend.dto.AccommodationTypeDTO;
import modul.backend.repository.AccommodationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationTypeService {

    @Autowired
    private AccommodationTypeRepository accommodationTypeRepository;

    public List<AccommodationTypeDTO> getAll(){
        return DTOList.accommodationTypes(accommodationTypeRepository.findAll());
    }

}
