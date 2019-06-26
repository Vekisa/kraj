package modul.backend;

import modul.backend.WebSer.*;
import modul.backend.service.ObjectService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.xml.ws.Endpoint;

@SpringBootApplication
@EnableEurekaClient
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);

        Endpoint.publish("http://localhost:8081/rezervacijaService", new RezervacijaImpl());
        //Endpoint.publish("http://localhost:8082/objekatService", new ObjectService());
        Endpoint.publish("http://localhost:8083/cenovnikService", new CenovnikImpl());
        Endpoint.publish("http://localhost:8084/smestajnaJedinicaService", new SmestajnaJedinicaImpl());
        Endpoint.publish("http://localhost:8085/porukaService", new PorukaImpl());

    }

}
