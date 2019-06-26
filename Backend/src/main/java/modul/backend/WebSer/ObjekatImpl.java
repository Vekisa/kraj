//package modul.backend.WebSer;
//
//import modul.backend.dto.ObjectDTO;
//import modul.backend.model.Object;
//import modul.backend.repository.ObjectRepository;
//import modul.backend.service.ObjectService;
//import org.hibernate.service.spi.InjectService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.ejb.Stateless;
//import javax.jws.WebService;
//import java.util.ArrayList;
//import java.util.List;
//
//@Stateless
//@WebService(portName = "ObjekatPort",
//        serviceName = "ObjekatService",
//        targetNamespace = "http://www.megatravell.com/ws/objekat",
//        endpointInterface = "modul.backend.WebSer.Objekat")
//public class ObjekatImpl implements Objekat{
//
//    @Autowired
//    private ObjectRepository objectRepository;
//
//    public ObjekatImpl() {
//        System.out.println("Created ObjekatImpl object");
//    }
//
//    @Override
//    public String objekatProvera(String text) {
//        System.out.println("Objekat ");
//        return "Objekat:  "  + text;
//    }
//
//    @Override
//    public ArrayList<Object> getAllObjects() {
//        return (ArrayList<Object>)objectRepository.findAll();
//    }
//}
