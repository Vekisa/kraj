package modul.backend.WebSer;

import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless
@WebService(portName = "SmestajnaJedinicaPort",
        serviceName = "SmestajnaJedinicaService",
        targetNamespace = "http://www.megatravell.com/ws/smestajnaJedinica",
        endpointInterface = "modul.backend.WebSer.SmestajnaJedinica")
public class SmestajnaJedinicaImpl implements SmestajnaJedinica{
    public SmestajnaJedinicaImpl() {
        System.out.println("Created SmestajnaJedinicaImpl object");
    }

    @Override
    public String smestajnaJedinicaProvera(String text) {
        System.out.println("SmestajnaJedinica ");
        return "SmestajnaJedinica:  " + text;
    }
}
