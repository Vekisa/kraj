
package modul.backend.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.math.BigInteger;
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
 *         &lt;element name="Adults" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="Children">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Beds" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="Size" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Smoking" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element ref="{http://megatravell.com/object}Price_schedule" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://megatravell.com/object}AccommodationType"/>
 *         &lt;element ref="{http://megatravell.com/object}Image" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://megatravell.com/object}Reservation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://megatravell.com/object}Object"/>
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
    "adults",
    "children",
    "beds",
    "size",
    "smoking",
    "priceSchedule",
    "accommodationType",
    "image",
    "reservation",
    "object"
})
@XmlRootElement(name = "Unit", namespace = "http://megatravell.com/object")
@Entity
@Table(name = "unit")
public class Unit {

    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @XmlElement(name = "Adults", namespace = "http://megatravell.com/object", required = true)
    @XmlSchemaType(name = "positiveInteger")
    @Column
    protected BigInteger adults;
    @XmlElement(name = "Children", namespace = "http://megatravell.com/object")
    @Column
    protected int children;
    @XmlElement(name = "Beds", namespace = "http://megatravell.com/object", required = true)
    @XmlSchemaType(name = "positiveInteger")
    @Column
    protected BigInteger beds;
    @XmlElement(name = "Size", namespace = "http://megatravell.com/object", required = true)
    @Column
    protected BigDecimal size;
    @XmlElement(name = "Smoking", namespace = "http://megatravell.com/object")
    @Column
    protected boolean smoking;
    @XmlElement(name = "Price_schedule", namespace = "http://megatravell.com/object")
    @OneToMany
    protected List<PriceSchedule> priceSchedule;
    @XmlElement(name = "AccommodationType", namespace = "http://megatravell.com/object", required = true)
    @ManyToOne
    protected AccommodationType accommodationType;
    @XmlElement(name = "Image", namespace = "http://megatravell.com/object")
    @OneToMany
    protected List<Image> image;
    @XmlElement(name = "Reservation", namespace = "http://megatravell.com/object")
    @OneToMany
    protected List<Reservation> reservation;
    @XmlElement(name = "Object", namespace = "http://megatravell.com/object", required = true)
    @ManyToOne
    protected Object object;

    public Unit() {
    }

    /**
     * Gets the value of the adults property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAdults() {
        return adults;
    }

    /**
     * Sets the value of the adults property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAdults(BigInteger value) {
        this.adults = value;
    }

    /**
     * Gets the value of the children property.
     * 
     */
    public int getChildren() {
        return children;
    }

    /**
     * Sets the value of the children property.
     * 
     */
    public void setChildren(int value) {
        this.children = value;
    }

    /**
     * Gets the value of the beds property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBeds() {
        return beds;
    }

    /**
     * Sets the value of the beds property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBeds(BigInteger value) {
        this.beds = value;
    }

    /**
     * Gets the value of the size property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSize(BigDecimal value) {
        this.size = value;
    }

    /**
     * Gets the value of the smoking property.
     * 
     */
    public boolean isSmoking() {
        return smoking;
    }

    /**
     * Sets the value of the smoking property.
     * 
     */
    public void setSmoking(boolean value) {
        this.smoking = value;
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

    public void setId(Long id) {
        this.id = id;
    }
}
