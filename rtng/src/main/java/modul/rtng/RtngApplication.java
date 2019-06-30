package modul.rtng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;

@EnableEurekaClient
@SpringBootApplication
public class RtngApplication {

    public static void main(String[] args) {
        SpringApplication.run(RtngApplication.class, args);
    }


}
