package module.agent.services;

import module.agent.model.Object;
import module.agent.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectService {

    @Autowired
    private ObjectRepository objectRepository;

    public Object create(Object object){


        return objectRepository.save(object);
    }

    public List<Object> getAll(){
        return objectRepository.findAll();
    }
}
