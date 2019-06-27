package modul.backend.WebSer;

import modul.backend.dto.AddressDTO;
import modul.backend.dto.ObjectDTO;
import modul.backend.model.Adress;
import modul.backend.model.Object;
import modul.backend.repository.ObjectRepository;
import modul.backend.service.ObjectService;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@Component
@Stateless
@WebService(portName = "ObjekatPort",
        serviceName = "ObjekatService",
        targetNamespace = "http://www.megatravell.com/ws/objekat",
        endpointInterface = "modul.backend.WebSer.Objekat")
public class ObjekatImpl implements Objekat{

    @Autowired
    private ObjectRepository objectRepository;

    @Override
    public String objekatProvera(String text) {
        return "Poruka" + text;
    }

    @Override
    public ObjectDTO getObject(long id) {
        Adress adress = new Adress();
        objectRepository.findAll();
        return new ObjectDTO(null,"Nesto","Nesto",2,adress);
    }
}
