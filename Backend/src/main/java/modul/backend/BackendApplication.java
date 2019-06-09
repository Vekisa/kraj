package modul.backend;

import modul.backend.WebSer.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.ws.Endpoint;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);

        Endpoint.publish("http://localhost:8081/rezervacijaService", new RezervacijaImpl());
        Endpoint.publish("http://localhost:8082/objekatService", new ObjekatImpl());
        Endpoint.publish("http://localhost:8083/cenovnikService", new CenovnikImpl());
        Endpoint.publish("http://localhost:8084/smestajnaJedinicaService", new SmestajnaJedinicaImpl());
        Endpoint.publish("http://localhost:8085/porukaService", new PorukaImpl());

    }

}
