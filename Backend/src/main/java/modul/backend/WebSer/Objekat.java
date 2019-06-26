package modul.backend.WebSer;

import modul.backend.dto.ObjectDTO;
import modul.backend.model.Object;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;

@WebService(targetNamespace="http://www.megatravell.com/ws/objekat")
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public interface Objekat {
    public String objekatProvera(@WebParam(name = "text") String text);
    public Object getObject(@WebParam(name = "id") long id);
}
