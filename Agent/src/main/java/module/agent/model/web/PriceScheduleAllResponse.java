
package module.agent.model.web;

import module.agent.model.PriceSchedule;

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
 *         &lt;element name="Price_schedule" type="{http://www.megatravell.com/plan}price_schedule" maxOccurs="unbounded" minOccurs="0"/>
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
    "priceSchedule"
})
@XmlRootElement(name = "PriceScheduleAllResponse", namespace = "http://www.megatravell.com/plan")
public class PriceScheduleAllResponse {

    @XmlElement(name = "Price_schedule", namespace = "http://www.megatravell.com/plan")
    protected List<PriceSchedule> priceSchedule;

    /**
     * Gets the value of the priceSchedule property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the priceSchedule property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPriceSchedule().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PriceSchedule }
     * 
     * 
     */
    public List<PriceSchedule> getPriceSchedule() {
        if (priceSchedule == null) {
            priceSchedule = new ArrayList<PriceSchedule>();
        }
        return this.priceSchedule;
    }

}
