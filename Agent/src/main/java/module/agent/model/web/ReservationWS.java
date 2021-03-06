
package module.agent.model.web;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <p>Java class for ReservationWS complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ReservationWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Start" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="End" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Confirmed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="cancelled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="PossibleCancellationDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Price">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="AgentId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="RegisteredUserId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Includes" type="{http://www.megatravell.com/reservation}IncludesWS" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReservationWS", namespace = "http://www.megatravell.com/reservation", propOrder = {
        "start",
        "end",
        "confirmed",
        "cancelled",
        "possibleCancellationDate",
        "price",
        "id",
        "unitId",
        "registeredUserId",
        "includes"
})
public class ReservationWS {

    @XmlElement(name = "Start", namespace = "http://www.megatravell.com/reservation", required = true)
    @XmlSchemaType(name = "date")
    protected Date start;
    @XmlElement(name = "End", namespace = "http://www.megatravell.com/reservation", required = true)
    @XmlSchemaType(name = "date")
    protected Date end;
    @XmlElement(name = "Confirmed", namespace = "http://www.megatravell.com/reservation", defaultValue = "false")
    protected boolean confirmed;
    @XmlElement(namespace = "http://www.megatravell.com/reservation")
    protected boolean cancelled;
    @XmlElement(name = "PossibleCancellationDate", namespace = "http://www.megatravell.com/reservation", required = true)
    @XmlSchemaType(name = "date")
    protected Date possibleCancellationDate;
    @XmlElement(name = "Price", namespace = "http://www.megatravell.com/reservation")
    protected double price;
    @XmlElement(namespace = "http://www.megatravell.com/reservation")
    protected long id;
    @XmlElement(namespace = "http://www.megatravell.com/reservation")
    protected long unitId;
    @XmlElement(name = "RegisteredUserId", namespace = "http://www.megatravell.com/reservation")
    protected long registeredUserId;
    @XmlElement(name = "Includes", namespace = "http://www.megatravell.com/reservation", required = true)
    protected List<IncludesWS> includes;

    public ReservationWS() {
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
     * Gets the value of the unitId property.
     *
     */
    public long getUnitId() {
        return unitId;
    }

    /**
     * Sets the value of the unitId property.
     *
     */
    public void setUnitId(long value) {
        this.unitId = value;
    }

    /**
     * Gets the value of the registeredUserId property.
     *
     */
    public long getRegisteredUserId() {
        return registeredUserId;
    }

    /**
     * Sets the value of the registeredUserId property.
     *
     */
    public void setRegisteredUserId(long value) {
        this.registeredUserId = value;
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
     * {@link IncludesWS }
     *
     *
     */
    public List<IncludesWS> getIncludes() {
        if (includes == null) {
            includes = new ArrayList<IncludesWS>();
        }
        return this.includes;
    }

}
