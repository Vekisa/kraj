package modul.backend.WebSer;

import modul.backend.model.Object;

import modul.backend.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ObjectWebServiceImpl implements ObjectWebService {


    @Autowired
    ObjectRepository objectRepository;

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
}
