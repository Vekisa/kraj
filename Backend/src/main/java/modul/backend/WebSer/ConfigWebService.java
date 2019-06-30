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
import org.springframework.xml.xsd.XsdSchemaCollection;
import org.springframework.xml.xsd.commons.CommonsXsdSchemaCollection;

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
    public DefaultWsdl11Definition defaultWsdl11DefinitionReservation(XsdSchemaCollection reservationSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ReservationPort");
        wsdl11Definition.setLocationUri("/soapws");
        wsdl11Definition.setTargetNamespace("http://www.megatravell.com/service");
        wsdl11Definition.setSchemaCollection(reservationSchema);
        return wsdl11Definition;
    }
    @Bean
    public XsdSchemaCollection reservationSchema() {
        return new CommonsXsdSchemaCollection(new ClassPathResource("xsd/reservation.xsd"));

    }

    @Bean(name = "wobject")
    public DefaultWsdl11Definition defaultWsdl11DefinitionObject(XsdSchemaCollection objectSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ObjectPort");
        wsdl11Definition.setLocationUri("/soapws");
        wsdl11Definition.setTargetNamespace("http://www.megatravell.com/wobject");
        wsdl11Definition.setSchemaCollection(objectSchema);
        return wsdl11Definition;
    }
    @Bean
    public XsdSchemaCollection objectSchema() {
        return new CommonsXsdSchemaCollection(new ClassPathResource("xsd/wobject.xsd"));
    }
//
    @Bean(name = "unit")
    public DefaultWsdl11Definition defaultWsdl11DefinitionUnit(XsdSchemaCollection unitSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("UnitPort");
        wsdl11Definition.setLocationUri("/soapws");
        wsdl11Definition.setTargetNamespace("http://www.megatravell.com/unit");
        wsdl11Definition.setSchemaCollection(unitSchema);
        return wsdl11Definition;
    }
    @Bean
    public XsdSchemaCollection unitSchema() {
        return new CommonsXsdSchemaCollection(new ClassPathResource("xsd/unit.xsd"));

    }

    @Bean(name = "message")
    public DefaultWsdl11Definition defaultWsdl11DefinitionMessage(XsdSchemaCollection messageSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("MessagePort");
        wsdl11Definition.setLocationUri("/soapws");
        wsdl11Definition.setTargetNamespace("http://www.megatravell.com/message");
        wsdl11Definition.setSchemaCollection(messageSchema);
        return wsdl11Definition;
    }
    @Bean
    public XsdSchemaCollection messageSchema() {
        return new CommonsXsdSchemaCollection(new ClassPathResource("xsd/message.xsd"));

    }

    @Bean(name = "plan")
    public DefaultWsdl11Definition defaultWsdl11DefinitionPlan(XsdSchema planSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("PlanPort");
        wsdl11Definition.setLocationUri("/soapws");
        wsdl11Definition.setTargetNamespace("http://www.megatravell.com/plan");
        wsdl11Definition.setSchema(planSchema);
        return wsdl11Definition;
    }
    @Bean
    public XsdSchema planSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/plan.xsd"));

    }





}
