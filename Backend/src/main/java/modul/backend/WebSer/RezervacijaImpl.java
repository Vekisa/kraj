package modul.backend.WebSer;

import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless
@WebService(portName = "RezervacijaPort",
        serviceName = "RezervacijaService",
        targetNamespace = "http://www.megatravell.com/ws/rezervacija",
        endpointInterface = "modul.backend.WebSer.Rezervacija")
public class RezervacijaImpl implements Rezervacija {
    public RezervacijaImpl() {
        System.out.println("Created RezervacijaImpl object");
    }

    @Override
    public String rezervacijaProvera(String text) {
        System.out.println("Rezervacija ");
        return "Rezervacija:  " + text;
    }

}
