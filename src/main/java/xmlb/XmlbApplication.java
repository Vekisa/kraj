package xmlb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xmlb.services.EmailSenderService;

@SpringBootApplication
public class XmlbApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmlbApplication.class, args);
	}

}
