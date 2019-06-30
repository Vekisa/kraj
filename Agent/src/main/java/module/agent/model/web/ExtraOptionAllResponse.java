
package module.agent.model.web;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ExtraOption" type="{http://www.megatravell.com/reservation}ExtraOption" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "extraOption"
})
@XmlRootElement(name = "ExtraOptionAllResponse", namespace = "http://www.megatravell.com/reservation")
public class ExtraOptionAllResponse {

    @XmlElement(name = "ExtraOption", namespace = "http://www.megatravell.com/reservation")
    protected List<ExtraOption> extraOption;

    /**
     * Gets the value of the extraOption property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extraOption property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtraOption().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExtraOption }
     * 
     * 
     */
    public List<ExtraOption> getExtraOption() {
        if (extraOption == null) {
            extraOption = new ArrayList<ExtraOption>();
        }
        return this.extraOption;
    }

}
