package module.agent.mainKlase;

import module.agent.generated.Poruka;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class PorukaClient {
    public static void main(String[] args) {
        try {
            URL wsdl = new URL("http://localhost:8085/porukaService?wsdl");
            QName serviceName = new QName("http://www.megatravell.com/ws/poruka", "PorukaService");
            QName portName = new QName("http://www.megatravell.com/ws/poruka", "PorukaPort");

            Service service = Service.create(wsdl, serviceName);
            Poruka poruka =service.getPort(portName, Poruka.class);

            String response =  poruka.porukaProvera("radi! ");
            System.out.println("Response from WS: " + response);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ;
    }
}
