package modul.backend.WebSer;

import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless
@WebService(portName = "ObjekatPort",
        serviceName = "ObjekatService",
        targetNamespace = "http://www.megatravell.com/ws/objekat",
        endpointInterface = "modul.backend.WebSer.Objekat")
public class ObjekatImpl implements Objekat{
    public ObjekatImpl() {
        System.out.println("Created ObjekatImpl object");
    }

    @Override
    public String objekatProvera(String text) {
        System.out.println("Objekat ");
        return "Objekat:  " + text;
    }
}
