package module.pretraga.search.model;


import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Date;
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
 *         &lt;element name="Name">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="40"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Description">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="150"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Cancellation" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="Category">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="5"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.megatravell.com/address}Adress"/>
 *         &lt;element ref="{http://megatravell.com/object}Image" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://megatravell.com/object}Comment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://megatravell.com/object}ExtraOption" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://megatravell.com/object}Unit" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://megatravell.com/object}Rating" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://megatravell.com/object}ObjectType"/>
 *         &lt;element ref="{http://www.megatravell.com/user}Agent" maxOccurs="unbounded" minOccurs="0"/>
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
        "name",
        "category",
        "persons",
        "distance",
})
@XmlRootElement(name = "Search", namespace = "http://megatravell.com/user")
@Entity
@Table(name = "search")
public class Search {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @XmlElement(name = "Start", namespace = "http://megatravell.com/object", required = true)
    @XmlSchemaType(name = "date")
    @Column
    protected Date start;
    @XmlElement(name = "End", namespace = "http://megatravell.com/object", required = true)
    @XmlSchemaType(name = "date")
    @Column
    protected Date end;

    @XmlElement(name = "Name", namespace = "http://megatravell.com/object", required = true)
    @Column
    protected String name;

    @XmlElement(name = "Category", namespace = "http://megatravell.com/object")
    @Column
    protected int category;

    @XmlElement(name = "Persons", namespace = "http://megatravell.com/object", required = true)
    @Column
    protected double persons;

    @XmlElement(name = "Distance", namespace = "http://megatravell.com/object", required = true)
    @Column
    protected Long distance;

    public Search() {
    }


    public Date getStart() {
        return start;
    }

    /**
     * Sets the value of the start property.
     *
     * @param value
     *     allowed object is
     *     {@link Date }
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
     *     {@link Date }
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
     *     {@link Date }
     *
     */
    public void setEnd(Date value) {
        this.end = value;
    }

    public double getPersons() {
        return persons;
    }

    /**
     * Sets the value of the price property.
     *
     */

    public void setPersons(double value) {
        this.persons = value;
    }

    public Long getDistance() {
        return distance;
    }

    /**
     * Sets the value of the price property.
     *
     */

    public void setDistance(Long value) {
        this.distance = value;
    }



    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Sets the value of the price property.
     *
     */

    public int getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     *
     */
    public void setCategory(int value) {
        this.category = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
