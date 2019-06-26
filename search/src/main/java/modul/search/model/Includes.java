
package modul.search.model;

import javax.persistence.*;
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
 *         &lt;element name="quantity">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element ref="{http://megatravell.com/object}Reservation"/>
 *         &lt;element ref="{http://megatravell.com/object}ExtraOption"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
    "extraOption",
    "id"
})
@XmlRootElement(name = "Includes", namespace = "http://megatravell.com/object")
@Entity
@Table
public class Includes {

    @XmlElement(namespace = "http://megatravell.com/object")
    @Column
    protected int quantity;
    @XmlElement(name = "Reservation", namespace = "http://megatravell.com/object", required = true)
    @ManyToOne
    protected Reservation reservation;
    @XmlElement(name = "ExtraOption", namespace = "http://megatravell.com/object", required = true)
    @ManyToOne
    protected ExtraOption extraOption;
    @XmlElement(namespace = "http://megatravell.com/object")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    public Includes(){}
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

}
