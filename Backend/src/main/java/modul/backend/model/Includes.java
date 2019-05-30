
package modul.backend.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="quantity">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element ref="{http://megatravell.com/object}Reservation"/>
 *         &lt;element ref="{http://megatravell.com/object}ExtraOption"/>
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
    "quantity",
    "reservation",
    "extraOption"
})
@XmlRootElement(name = "Includes", namespace = "http://megatravell.com/object")
@Entity
@Table(name = "includes")
public class Includes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @XmlElement(namespace = "http://megatravell.com/object")
    @Column
    protected int quantity;
    @XmlElement(name = "Reservation", namespace = "http://megatravell.com/object", required = true)
    protected Reservation reservation;
    @XmlElement(name = "ExtraOption", namespace = "http://megatravell.com/object", required = true)
    protected ExtraOption extraOption;

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
     * Gets the value of the reservation property.
     * 
     * @return
     *     possible object is
     *     {@link Reservation }
     *     
     */
    public Reservation getReservation() {
        return reservation;
    }

    /**
     * Sets the value of the reservation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reservation }
     *     
     */
    public void setReservation(Reservation value) {
        this.reservation = value;
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

}
