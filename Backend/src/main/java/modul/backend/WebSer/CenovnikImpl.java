package modul.backend.WebSer;

import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless
@WebService(portName = "CenovnikPort",
        serviceName = "CenovnikService",
        targetNamespace = "http://www.megatravell.com/ws/cenovnik",
        endpointInterface = "modul.backend.WebSer.Cenovnik")
public class CenovnikImpl implements Cenovnik{
    public CenovnikImpl() {
        System.out.println("Created CenovnikImpl object");
    }

    @Override
    public String cenovnikProvera(String text) {
        System.out.println("Cenovnik ");
        return "Cenovnik:  " + text;
    }



}
