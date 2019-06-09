package modul.backend.WebSer;

import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless
@WebService(portName = "PorukaPort",
        serviceName = "PorukaService",
        targetNamespace = "http://www.megatravell.com/ws/poruka",
        endpointInterface = "modul.backend.WebSer.Poruka")
public class PorukaImpl implements Poruka{
    public PorukaImpl() {
        System.out.println("Created PorukaImpl object");
    }

    @Override
    public String porukaProvera(String text) {
        System.out.println("Poruka ");
        return "Poruka:  " + text;
    }
}
