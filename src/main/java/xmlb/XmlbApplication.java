package xmlb;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xmlb.service.CertificateService;

@SpringBootApplication
public class XmlbApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmlbApplication.class, args);
		//CertificateService cs=new CertificateService();
		//System.out.println(" aaaa " + cs.showKeyStoreContent("self"));
	}

}
