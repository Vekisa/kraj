package xmlb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import xmlb.service.EndPointsService;

import java.util.Map;

@SpringBootApplication
@IntegrationComponentScan
@EnableIntegration
public class XmlbApplication implements ApplicationListener {

	@Autowired
	private EndPointsService endPointsService;

	public static void main(String[] args) {
		SpringApplication.run(XmlbApplication.class, args);
	}

	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		if(applicationEvent instanceof ContextRefreshedEvent){
			ApplicationContext applicationContext = ((ContextRefreshedEvent) applicationEvent).getApplicationContext();
			Map<RequestMappingInfo, HandlerMethod> map = applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods();
			endPointsService.updateDatabase(map);
		}
	}
}
