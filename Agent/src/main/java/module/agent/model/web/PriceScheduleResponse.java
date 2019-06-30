
package module.agent.model.web;

import module.agent.model.PriceSchedule;

import javax.xml.bind.annotation.*;


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
 *         &lt;element name="Price_schedule" type="{http://www.megatravell.com/plan}price_schedule"/>
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
@XmlRootElement(name = "PriceScheduleResponse", namespace = "http://www.megatravell.com/plan")
public class PriceScheduleResponse {

    @XmlElement(name = "Price_schedule", namespace = "http://www.megatravell.com/plan", required = true)
    protected PriceSchedule priceSchedule;

    /**
     * Gets the value of the priceSchedule property.
     * 
     * @return
     *     possible object is
     *     {@link PriceSchedule }
     *     
     */
    public PriceSchedule getPriceSchedule() {
        return priceSchedule;
    }

    /**
     * Sets the value of the priceSchedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link PriceSchedule }
     *     
     */
    public void setPriceSchedule(PriceSchedule value) {
        this.priceSchedule = value;
    }

}
