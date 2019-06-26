package module.agent.mainKlase;

import module.agent.generated.Objekat;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class ObjekatClient {
    public static void main(String[] args) {
        try {
            URL wsdl = new URL("http://localhost:8765/objekatService?wsdl");
            QName serviceName = new QName("http://www.megatravell.com/ws/objekat", "ObjekatService");
            QName portName = new QName("http://www.megatravell.com/ws/objekat", "ObjekatPort");

            Service service = Service.create(wsdl, serviceName);
            Objekat objekat =service.getPort(portName, Objekat.class);

            String response = objekat.objekatProvera("radi! ");
            System.out.println("Response from WS: " + response);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ;
    }
}
