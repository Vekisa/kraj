package module.agent;

import module.agent.model.Adress;
import module.agent.model.Object;
import module.agent.model.ObjectType;
import module.agent.model.web.*;
import module.agent.services.ObjectClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class AgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgentApplication.class, args);
    }


    @Bean
    CommandLineRunner lookup(ObjectClient objectClient) {
        return args -> {
            System.out.println("--- Get by Id ---");
            ObjectResponse getObject =objectClient.getObject(2);
            ObjectWS objectWS= getObject.getObject();
            System.out.println(objectWS.getId() + ", "+ objectWS.getCategory()
                    + ", " + objectWS.getDescription());

            System.out.println("--- Get all  ---");
            ObjectAllResponse allObjects = objectClient.allObject();
            allObjects.getObject().stream()
                    .forEach(e -> System.out.println(e.getId() + ", "+ e.getName() + ", " + e.getCategory()));

            System.out.println("--- Add  ---");
            Adress adress = new Adress();
            adress.setId(1);
            ObjectType objectType = new ObjectType();
            objectType.setId(1);
            ObjectWS objectWS1 = new ObjectWS();
            objectWS1.setName("Smesto");
            objectWS1.setCategory(5);
            objectWS1.setDescription("Neki smestajjj!");
            objectWS1.setAdress(adress);
            objectWS1.setObjectTypeId(objectType);


            ObjectAddResponse objectAddResponse = objectClient.addObject(objectWS1);
            ResponseMessage responseMessage = objectAddResponse.getResponseMessage();

            System.out.println("Message: " + responseMessage.getMessage());

            System.out.println("--- Get all  ---");
            allObjects = objectClient.allObject();
            allObjects.getObject().stream()
                    .forEach(e -> System.out.println(e.getId() + ", "+ e.getName() + ", " + e.getCategory()));


            System.out.println("--- Update ---");

            objectWS1 = new ObjectWS();
            objectWS1.setId(2);
            objectWS1.setName("Novi Naziv");
            objectWS1.setObjectTypeId(objectType);
            objectWS1.setAdress(adress);
            objectWS1.setCategory(4);
            objectWS1.setDescription("NEKI OPISSS");

            ObjectUpdateResponse objectUpdateResponse = objectClient.updateObject(objectWS1);

            responseMessage = objectUpdateResponse.getResponseMessage();

            System.out.println("--- Get all  ---");
            allObjects = objectClient.allObject();
            allObjects.getObject().stream()
                    .forEach(e -> System.out.println(e.getId() + ", "+ e.getName() + ", " + e.getCategory()));

            System.out.println("Message: " + responseMessage.getMessage());
            System.out.println("--- Delete Article ---");
            long id = 1;
            ObjectDeleteResponse deleteResponse = objectClient.deleteObject(id);
            responseMessage = deleteResponse.getResponseMessage();
            System.out.println("Message: " + responseMessage.getMessage());

            System.out.println("--- Get all  ---");
            allObjects = objectClient.allObject();
            allObjects.getObject().stream()
                    .forEach(e -> System.out.println(e.getId() + ", "+ e.getName() + ", " + e.getCategory()));

        };



    }


}
