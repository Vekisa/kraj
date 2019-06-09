package module.agent.mainKlase;

import module.agent.generated.SmestajnaJedinica;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class SmestajnaJedinicaClient {
    public static void main(String[] args) {
        try {
            URL wsdl = new URL("http://localhost:8084/smestajnaJedinicaService?wsdl");
            QName serviceName = new QName("http://www.megatravell.com/ws/smestajnaJedinica", "SmestajnaJedinicaService");
            QName portName = new QName("http://www.megatravell.com/ws/smestajnaJedinica", "SmestajnaJedinicaPort");

            Service service = Service.create(wsdl, serviceName);
            SmestajnaJedinica smestajnaJedinica =service.getPort(portName, SmestajnaJedinica.class);

            String response = smestajnaJedinica.smestajnaJedinicaProvera("radi! ");
            System.out.println("Response from WS: " + response);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ;
    }
}
