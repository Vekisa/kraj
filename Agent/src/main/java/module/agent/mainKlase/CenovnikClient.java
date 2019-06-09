package module.agent.mainKlase;

import module.agent.generated.Cenovnik;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class CenovnikClient {
    public static void main(String[] args) {
        try {
            URL wsdl = new URL("http://localhost:8083/cenovnikService?wsdl");
            QName serviceName = new QName("http://www.megatravell.com/ws/cenovnik", "CenovnikService");
            QName portName = new QName("http://www.megatravell.com/ws/cenovnik", "CenovnikPort");

            Service service = Service.create(wsdl, serviceName);
            Cenovnik cenovnik =service.getPort(portName, Cenovnik.class);

            String response = cenovnik.cenovnikProvera("radi! ");
            System.out.println("Response from WS: " + response);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ;
    }
}
