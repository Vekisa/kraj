//package modul.backend.WebSer;
//
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.ws.config.annotation.EnableWs;
//import org.springframework.ws.config.annotation.WsConfigurerAdapter;
//import org.springframework.ws.transport.http.MessageDispatcherServlet;
//import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
//import org.springframework.xml.xsd.XsdSchema;
//
//@EnableWs
//@Configuration
//public class ConfigWebService extends WsConfigurerAdapter {
//
//    @Bean
//    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext)
//    {
//        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
//        servlet.setApplicationContext(applicationContext);
//        servlet.setTransformWsdlLocations(true);
//        return new ServletRegistrationBean<>(servlet, "/service/*");
//    }
//
//    @Bean(name = "objekatService")
//    public DefaultWsdl11Definition defaultWsdl11Definition()
//    {
//        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
//        wsdl11Definition.setPortTypeName("ObjectPort");
//        wsdl11Definition.setLocationUri("/service/object/");
//        wsdl11Definition.setTargetNamespace("http://www.megatravell.com/ws/objekat");
//        return wsdl11Definition;
//    }
//
//
//
//}
