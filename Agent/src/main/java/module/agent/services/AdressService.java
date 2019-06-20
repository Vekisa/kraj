package module.agent.services;

import module.agent.model.Adress;
import module.agent.repository.AdressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdressService {
    @Autowired
    private AdressRepository adressRepository;

    public Adress create(Adress adress){
        return adressRepository.save(adress);
    }
}
