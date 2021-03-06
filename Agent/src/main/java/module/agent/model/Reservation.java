
package module.agent.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Date;
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
 *         &lt;element name="Start" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="End" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Confirmed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="PossibleCancellationDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Price">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element ref="{http://megatravell.com/object}Unit"/>
 *         &lt;element ref="{http://megatravell.com/object}Includes" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.megatravell.com/user}RegisteredUser"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="cancelled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
        "start",
        "end",
        "confirmed",
        "possibleCancellationDate",
        "price",
        "unit",
        "includes",
        "registeredUser",
        "id",
        "cancelled"
})
@Entity
@Table(name = "reservation")
@XmlRootElement(name = "Reservation", namespace = "http://megatravell.com/object")
public class Reservation {

    @XmlElement(name = "Start", namespace = "http://megatravell.com/object", required = true)
    @XmlSchemaType(name = "date")
    @Column
    protected Date start;
    @XmlElement(name = "End", namespace = "http://megatravell.com/object", required = true)
    @XmlSchemaType(name = "date")
    @Column
    protected Date end;
    @XmlElement(name = "Confirmed", namespace = "http://megatravell.com/object", defaultValue = "false")
    @Column
    protected boolean confirmed;
    @XmlElement(name = "PossibleCancellationDate", namespace = "http://megatravell.com/object", required = true)
    @XmlSchemaType(name = "date")
    @Column
    protected Date possibleCancellationDate;
    @XmlElement(name = "Price", namespace = "http://megatravell.com/object")
    @Column
    protected double price;
    @XmlElement(name = "Unit", namespace = "http://megatravell.com/object", required = true)
    @ManyToOne
    protected Unit unit;
    @XmlElement(name = "Includes", namespace = "http://megatravell.com/object")
    @OneToMany(mappedBy = "reservation")
    protected List<Includes> includes;
    @ManyToOne
    @XmlElement(name = "RegisteredUser", namespace = "http://www.megatravell.com/user", required = true)
    protected RegisteredUser registeredUser;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(namespace = "http://megatravell.com/object")
    protected long id;
    @Column
    @XmlElement(namespace = "http://megatravell.com/object")
    protected boolean cancelled;

    public Reservation() {
    }

    public Reservation(Date start, Date end, boolean confirmed, Date possibleCancellationDate, double price, Unit unit, boolean cancelled) {
        this.start = start;
        this.end = end;
        this.confirmed = confirmed;
        this.possibleCancellationDate = possibleCancellationDate;
        this.price = price;
        this.unit = unit;
        this.cancelled = cancelled;
    }

    /**
     * Gets the value of the start property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public Date getStart() {
        return start;
    }

    /**
     * Sets the value of the start property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setStart(Date value) {
        this.start = value;
    }

    /**
     * Gets the value of the end property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public Date getEnd() {
        return end;
    }

    /**
     * Sets the value of the end property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setEnd(Date value) {
        this.end = value;
    }

    /**
     * Gets the value of the confirmed property.
     *
     */
    public boolean isConfirmed() {
        return confirmed;
    }

    /**
     * Sets the value of the confirmed property.
     *
     */
    public void setConfirmed(boolean value) {
        this.confirmed = value;
    }

    /**
     * Gets the value of the possibleCancellationDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public Date getPossibleCancellationDate() {
        return possibleCancellationDate;
    }

    /**
     * Sets the value of the possibleCancellationDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setPossibleCancellationDate(Date value) {
        this.possibleCancellationDate = value;
    }

    /**
     * Gets the value of the price property.
     *
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     *
     */
    public void setPrice(double value) {
        this.price = value;
    }

    /**
     * Gets the value of the unit property.
     *
     * @return
     *     possible object is
     *     {@link Unit }
     *
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     *
     * @param value
     *     allowed object is
     *     {@link Unit }
     *
     */
    public void setUnit(Unit value) {
        this.unit = value;
    }

    /**
     * Gets the value of the includes property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the includes property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIncludes().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Includes }
     *
     *
     */
    public List<Includes> getIncludes() {
        if (includes == null) {
            includes = new ArrayList<Includes>();
        }
        return this.includes;
    }

    /**
     * Gets the value of the registeredUser property.
     *
     * @return
     *     possible object is
     *     {@link RegisteredUser }
     *
     */
    public RegisteredUser getRegisteredUser() {
        return registeredUser;
    }

    /**
     * Sets the value of the registeredUser property.
     *
     * @param value
     *     allowed object is
     *     {@link RegisteredUser }
     *
     */
    public void setRegisteredUser(RegisteredUser value) {
        this.registeredUser = value;
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

    /**
     * Gets the value of the cancelled property.
     *
     */
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Sets the value of the cancelled property.
     *
     */
    public void setCancelled(boolean value) {
        this.cancelled = value;
    }

}
