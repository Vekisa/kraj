package module.agent.mainKlase;

import module.agent.generated.Rezervacija;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class RezervacijaClient {
    public static void main(String[] args) {
        try {
            URL wsdl = new URL("http://localhost:8081/rezervacijaService?wsdl");
            QName serviceName = new QName("http://www.megatravell.com/ws/rezervacija", "RezervacijaService");
            QName portName = new QName("http://www.megatravell.com/ws/rezervacija", "RezervacijaPort");

            Service service = Service.create(wsdl, serviceName);
            Rezervacija rezervacija =service.getPort(portName, Rezervacija.class);

            String response = rezervacija.rezervacijaProvera("radi! ");
            System.out.println("Response from WS: " + response);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ;
    }
}
