package module.agent.generated;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace="http://www.megatravell.com/ws/poruka")
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public interface Poruka {
    public String porukaProvera(@WebParam(name = "text") String text);
}
