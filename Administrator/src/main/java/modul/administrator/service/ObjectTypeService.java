package modul.administrator.service;

import modul.administrator.dto.ObjectTypeDTO;
import modul.administrator.model.ObjectType;
import modul.administrator.repository.ObjectTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ObjectTypeService {

    @Autowired
    private ObjectTypeRepository objectTypeRepository;

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public List<ObjectTypeDTO> getAll(){
        return DTOList.objectTypes(objectTypeRepository.findAll());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ObjectTypeDTO create(ObjectTypeDTO objectTypeDTO){
        ObjectType objectType = new ObjectType();
        objectType.setName(objectTypeDTO.getName());
        objectType.setDescription(objectTypeDTO.getDescription());
        objectTypeRepository.save(objectType);
        return new ObjectTypeDTO(objectType);
    }

    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public ObjectTypeDTO update(ObjectTypeDTO objectTypeDTO){
        Optional<ObjectType> objectType = objectTypeRepository.findById(objectTypeDTO.getId());
        if(!objectType.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Object type does not exist!");

        objectType.get().setName(objectTypeDTO.getName());
        objectType.get().setDescription(objectTypeDTO.getDescription());
        objectTypeRepository.save(objectType.get());

        return new ObjectTypeDTO(objectType.get());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void delete(Long id){
        Optional<ObjectType> objectType = objectTypeRepository.findById(id);
        if(!objectType.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Object type does not exist!");

        objectTypeRepository.delete(objectType.get());
        System.out.println("OBRISAO");
    }

}
