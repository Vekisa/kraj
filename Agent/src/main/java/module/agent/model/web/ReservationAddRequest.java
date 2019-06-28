
package module.agent.model.web;

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
 *         &lt;element name="Reservation" type="{http://www.megatravell.com/reservation}ReservationWS"/>
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
    "reservation"
})
@XmlRootElement(name = "ReservationAddRequest", namespace = "http://www.megatravell.com/reservation")
public class ReservationAddRequest {

    @XmlElement(name = "Reservation", namespace = "http://www.megatravell.com/reservation", required = true)
    protected ReservationWS reservation;

    /**
     * Gets the value of the reservation property.
     * 
     * @return
     *     possible object is
     *     {@link ReservationWS }
     *     
     */
    public ReservationWS getReservation() {
        return reservation;
    }

    /**
     * Sets the value of the reservation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReservationWS }
     *     
     */
    public void setReservation(ReservationWS value) {
        this.reservation = value;
    }

}
