
package modul.backend.model.web;

import modul.backend.model.Image;
import modul.backend.model.PriceSchedule;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for unit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="unit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Person" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Beds" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cancellation" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="objectId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="accomodationTypeId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="reservationId" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Price_schedule" type="{http://www.megatravell.com/plan}price_schedule" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="image" type="{http://www.megatravell.com/unit}image" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "unit", namespace = "http://www.megatravell.com/unit", propOrder = {
    "id",
    "person",
    "beds",
    "cancellation",
    "objectId",
    "accomodationTypeId",
    "reservationId",
    "priceSchedule",
    "image"
})
public class UnitWS {

    @XmlElement(namespace = "http://www.megatravell.com/unit")
    protected long id;
    @XmlElement(name = "Person", namespace = "http://www.megatravell.com/unit")
    protected int person;
    @XmlElement(name = "Beds", namespace = "http://www.megatravell.com/unit")
    protected int beds;
    @XmlElement(namespace = "http://www.megatravell.com/unit")
    protected int cancellation;
    @XmlElement(namespace = "http://www.megatravell.com/unit")
    protected long objectId;
    @XmlElement(namespace = "http://www.megatravell.com/unit")
    protected long accomodationTypeId;
    @XmlElement(namespace = "http://www.megatravell.com/unit", type = Long.class)
    protected List<Long> reservationId;
    @XmlElement(name = "Price_schedule", namespace = "http://www.megatravell.com/unit")
    protected List<PriceSchedule> priceSchedule;
    @XmlElement(namespace = "http://www.megatravell.com/unit")
    protected List<Image> image;

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
     * Gets the value of the person property.
     * 
     */
    public int getPerson() {
        return person;
    }

    /**
     * Sets the value of the person property.
     * 
     */
    public void setPerson(int value) {
        this.person = value;
    }

    /**
     * Gets the value of the beds property.
     * 
     */
    public int getBeds() {
        return beds;
    }

    /**
     * Sets the value of the beds property.
     * 
     */
    public void setBeds(int value) {
        this.beds = value;
    }

    /**
     * Gets the value of the cancellation property.
     * 
     */
    public int getCancellation() {
        return cancellation;
    }

    /**
     * Sets the value of the cancellation property.
     * 
     */
    public void setCancellation(int value) {
        this.cancellation = value;
    }

    /**
     * Gets the value of the objectId property.
     * 
     */
    public long getObjectId() {
        return objectId;
    }

    /**
     * Sets the value of the objectId property.
     * 
     */
    public void setObjectId(long value) {
        this.objectId = value;
    }

    /**
     * Gets the value of the accomodationTypeId property.
     * 
     */
    public long getAccomodationTypeId() {
        return accomodationTypeId;
    }

    /**
     * Sets the value of the accomodationTypeId property.
     * 
     */
    public void setAccomodationTypeId(long value) {
        this.accomodationTypeId = value;
    }

    /**
     * Gets the value of the reservationId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reservationId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReservationId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getReservationId() {
        if (reservationId == null) {
            reservationId = new ArrayList<Long>();
        }
        return this.reservationId;
    }

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

    /**
     * Gets the value of the image property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the image property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Image }
     * 
     * 
     */
    public List<Image> getImage() {
        if (image == null) {
            image = new ArrayList<Image>();
        }
        return this.image;
    }

}
