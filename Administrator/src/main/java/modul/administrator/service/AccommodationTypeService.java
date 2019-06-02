package modul.administrator.service;

import modul.administrator.dto.AccommodationTypeDTO;
import modul.administrator.model.AccommodationType;
import modul.administrator.repository.AccommodationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationTypeService {

    @Autowired
    private AccommodationTypeRepository accommodationTypeRepository;

    public List<AccommodationTypeDTO> getAll(){
        return DTOList.accommodationTypes(accommodationTypeRepository.findAll());
    }


    public AccommodationTypeDTO create(AccommodationTypeDTO accommodationTypeDTO){
        AccommodationType accommodationType = new AccommodationType();
        accommodationType.setName(accommodationTypeDTO.getName());
        accommodationType.setDescription(accommodationTypeDTO.getDescription());
        accommodationTypeRepository.save(accommodationType);

        return new AccommodationTypeDTO(accommodationType);
    }

    public AccommodationTypeDTO update(AccommodationTypeDTO accommodationTypeDTO){
        Optional<AccommodationType> accommodationType = accommodationTypeRepository.findById(accommodationTypeDTO.getId());
        if(!accommodationType.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Accommodation type does not exist!");

        accommodationType.get().setName(accommodationTypeDTO.getName());
        accommodationType.get().setDescription(accommodationTypeDTO.getDescription());
        accommodationTypeRepository.save(accommodationType.get());

        return new AccommodationTypeDTO(accommodationType.get());
    }

    public void delete(Long id){
        Optional<AccommodationType> accommodationType = accommodationTypeRepository.findById(id);
        if(!accommodationType.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Accommodation type does not exist!");

        accommodationTypeRepository.delete(accommodationType.get());
    }
}
