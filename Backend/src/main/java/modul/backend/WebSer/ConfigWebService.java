package modul.backend.WebSer;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class ConfigWebService extends WsConfigurerAdapter {


    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/soapws/*");
    }
    @Bean(name = "reservation")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema reservationSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ReservationPort");
        wsdl11Definition.setLocationUri("/soapws");
        wsdl11Definition.setTargetNamespace("http://www.megatravell.com/service");
        wsdl11Definition.setSchema(reservationSchema);
        return wsdl11Definition;
    }
    @Bean
    public XsdSchema reservationSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/reservation.xsd"));

    }


}
