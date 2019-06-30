package modul.backend.WebSer;

import modul.backend.model.web.MessageWS;

import java.util.List;

public interface MessageWebService {

    List<MessageWS> getAll();
    boolean sendMessage(MessageWS messageWS);
}
