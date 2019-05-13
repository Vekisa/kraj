package xmlb.service;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class Logging {

    protected final Log LOGGER;

    public Logging(Object object){
        LOGGER = LogFactory.getLog(object.getClass());
    }

    public void printInfo(String message){
        try{
            LOGGER.info(message);
        }catch(Exception e){
            System.out.println("Logger does not work");
        }
    }

    public void printError(String message){
        try{
            LOGGER.error(message);
        }catch(Exception e){
            System.out.println("Logger does not work");
        }
    }

    public void printWarning(String message){
        try {
            LOGGER.warn(message);
        }catch (Exception e){
            System.out.println("Logger does not work");
        }
    }
}
