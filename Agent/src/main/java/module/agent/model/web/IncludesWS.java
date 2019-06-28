
package module.agent.model.web;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IncludesWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IncludesWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="quantity">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ExtraOption" type="{http://www.megatravell.com/reservation}ExtraOption"/>
 *         &lt;element name="ReservationId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IncludesWS", namespace = "http://www.megatravell.com/reservation", propOrder = {
    "id",
    "quantity",
    "extraOption",
    "reservationId"
})
public class IncludesWS {

    @XmlElement(namespace = "http://www.megatravell.com/reservation")
    protected long id;
    @XmlElement(namespace = "http://www.megatravell.com/reservation")
    protected int quantity;
    @XmlElement(name = "ExtraOption", namespace = "http://www.megatravell.com/reservation", required = true)
    protected ExtraOption extraOption;
    @XmlElement(name = "ReservationId", namespace = "http://www.megatravell.com/reservation")
    protected long reservationId;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     */
    public void setQuantity(int value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the extraOption property.
     * 
     * @return
     *     possible object is
     *     {@link ExtraOption }
     *     
     */
    public ExtraOption getExtraOption() {
        return extraOption;
    }

    /**
     * Sets the value of the extraOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtraOption }
     *     
     */
    public void setExtraOption(ExtraOption value) {
        this.extraOption = value;
    }

    /**
     * Gets the value of the reservationId property.
     * 
     */
    public long getReservationId() {
        return reservationId;
    }

    /**
     * Sets the value of the reservationId property.
     * 
     */
    public void setReservationId(long value) {
        this.reservationId = value;
    }

}
