package modul.backend.WebSer;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace="http://www.megatravell.com/ws/rezervacija")
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public interface Rezervacija {
    public String rezervacijaProvera(@WebParam(name = "text") String text);
}
