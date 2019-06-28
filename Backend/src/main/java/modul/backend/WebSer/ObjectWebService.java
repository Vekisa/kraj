package modul.backend.WebSer;

import modul.backend.model.Object;

import java.util.List;

public interface ObjectWebService {

    List<Object> getAllObjects();
    Object getObjectById(long objectId);
    void addObject(Object object);
    boolean updateObject(Object object);
    boolean deleteObject(long id);

}
