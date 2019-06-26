package modul.oauth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="Person" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Beds" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element ref="{http://megatravell.com/object}Price_schedule" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://megatravell.com/object}AccommodationType"/>
 *         &lt;element ref="{http://megatravell.com/object}Image" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://megatravell.com/object}Reservation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://megatravell.com/object}Object"/>
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
        "person",
        "beds",
        "priceSchedule",
        "accommodationType",
        "image",
        "reservation",
        "object",
        "id"
})
@Entity
@XmlRootElement(name = "Unit", namespace = "http://megatravell.com/object")
public class Unit {

    @Column
    @XmlElement(name = "Person", namespace = "http://megatravell.com/object")
    protected int person;
    @Column
    @XmlElement(name = "Beds", namespace = "http://megatravell.com/object")
    protected int beds;
    @OneToMany
    @XmlElement(name = "Price_schedule", namespace = "http://megatravell.com/object")
    protected List<PriceSchedule> priceSchedule;
    @ManyToOne
    @XmlElement(name = "AccommodationType", namespace = "http://megatravell.com/object", required = true)
    protected AccommodationType accommodationType;
    @OneToMany
    @XmlElement(name = "Image", namespace = "http://megatravell.com/object")
    protected List<Image> image;
    @JsonIgnore
    @OneToMany
    @XmlElement(name = "Reservation", namespace = "http://megatravell.com/object")
    protected List<Reservation> reservation;
    @ManyToOne
    @XmlElement(name = "Object", namespace = "http://megatravell.com/object", required = true)
    protected Object object;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(namespace = "http://megatravell.com/object")
    protected long id;

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
     * Gets the value of the accommodationType property.
     *
     * @return
     *     possible object is
     *     {@link AccommodationType }
     *
     */
    public AccommodationType getAccommodationType() {
        return accommodationType;
    }

    /**
     * Sets the value of the accommodationType property.
     *
     * @param value
     *     allowed object is
     *     {@link AccommodationType }
     *
     */
    public void setAccommodationType(AccommodationType value) {
        this.accommodationType = value;
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

    /**
     * Gets the value of the reservation property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reservation property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReservation().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Reservation }
     *
     *
     */
    public List<Reservation> getReservation() {
        if (reservation == null) {
            reservation = new ArrayList<Reservation>();
        }
        return this.reservation;
    }

    /**
     * Gets the value of the object property.
     *
     * @return
     *     possible object is
     *     {@link Object }
     *
     */
    public Object getObject() {
        return object;
    }

    /**
     * Sets the value of the object property.
     *
     * @param value
     *     allowed object is
     *     {@link Object }
     *
     */
    public void setObject(Object value) {
        this.object = value;
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
