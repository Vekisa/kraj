package module.agent.config;

import module.agent.services.ObjectClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class BeanConfig {

    private static final String WS_URI = "http://localhost:8765/soapws/";

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("module.agent.model.web");
        return marshaller;
    }

    @Bean
    public ObjectClient objectClient(Jaxb2Marshaller marshaller) {
        ObjectClient client = new ObjectClient();
        client.setDefaultUri(WS_URI+"wobject.wsdl");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }


}
