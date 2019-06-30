package modul.backend.WebSer;

import com.netflix.discovery.converters.Auto;
import modul.backend.model.AccommodationType;
import modul.backend.model.ExtraOption;
import modul.backend.model.Object;

import modul.backend.model.ObjectType;
import modul.backend.repository.ObjectRepository;
import modul.backend.service.AccommodationTypeService;
import modul.backend.service.ExtraOptionService;
import modul.backend.service.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ObjectWebServiceImpl implements ObjectWebService {


    @Autowired
    ObjectRepository objectRepository;

    @Autowired
    ObjectService objectService;

    @Override
    public List<Object> getAllObjects() {
        List<Object> list= new ArrayList<>() ;
        objectRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    public Object getObjectById(long objectId) {
        return objectRepository.findById(objectId).get();
    }

    @Override
    public void addObject(Object object) {
        objectRepository.save(object);
    }

    @Override
    public boolean updateObject(Object object) {

        Optional<Object> objectOptional = objectRepository.findById(object.getId());

        if (object == null) {
            return false;
        } else {
            objectRepository.save(object);
        }

        return true;

    }

    @Override
    public boolean deleteObject(long id) {

        Optional<Object> object = objectRepository.findById(id);

        if (object == null) {
            return false;
        } else {
            objectRepository.deleteById(id);
        }

        return true;
    }

    @Override
    public List<ObjectType> getAllObjectTypes() {
        return objectService.getObjectTypes();
    }
}
