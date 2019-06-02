
package modul.administrator.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
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
    "name",
    "description",
    "cancellation",
    "category",
    "adress",
    "image",
    "comment",
    "extraOption",
    "unit",
    "rating",
    "objectType"
    //"agent"
})
@XmlRootElement(name = "Object", namespace = "http://megatravell.com/object")
@Entity
@Table(name = "object")
public class Object {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @XmlElement(name = "Name", namespace = "http://megatravell.com/object", required = true)
    @Column
    protected String name;
    @XmlElement(name = "Description", namespace = "http://megatravell.com/object", required = true)
    @Column
    protected String description;
    @XmlElement(name = "Cancellation", namespace = "http://megatravell.com/object", required = true)
    @XmlSchemaType(name = "positiveInteger")
    @Column
    protected BigInteger cancellation;
    @XmlElement(name = "Category", namespace = "http://megatravell.com/object")
    @Column
    protected int category;
    @XmlElement(name = "Adress", namespace = "http://www.megatravell.com/address", required = true)
    @ManyToOne
    protected Adress adress;
    @XmlElement(name = "Image", namespace = "http://megatravell.com/object")
    @OneToMany
    protected List<Image> image;
    @XmlElement(name = "Comment", namespace = "http://megatravell.com/object")
    @OneToMany(mappedBy = "object")
    protected List<Comment> comment;
    @XmlElement(name = "ExtraOption", namespace = "http://megatravell.com/object")
    @OneToMany
    protected List<ExtraOption> extraOption;
    @XmlElement(name = "Unit", namespace = "http://megatravell.com/object")
    @OneToMany(mappedBy = "object")
    protected List<Unit> unit;
    @XmlElement(name = "Rating", namespace = "http://megatravell.com/object")
    @OneToMany(mappedBy = "object")
    protected List<Rating> rating;
    @XmlElement(name = "ObjectType", namespace = "http://megatravell.com/object", required = true)
    @ManyToOne
    protected ObjectType objectType;
    /**
     * @XmlElement(name = "Agent", namespace = "http://www.megatravell.com/user")
    @OneToMany
    protected List<Agent> agent;
     */

    public Object(){}
    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
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
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the cancellation property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCancellation() {
        return cancellation;
    }

    /**
     * Sets the value of the cancellation property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCancellation(BigInteger value) {
        this.cancellation = value;
    }

    /**
     * Gets the value of the category property.
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

    /**
     * Gets the value of the adress property.
     * 
     * @return
     *     possible object is
     *     {@link Adress }
     *     
     */
    public Adress getAdress() {
        return adress;
    }

    /**
     * Sets the value of the adress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Adress }
     *     
     */
    public void setAdress(Adress value) {
        this.adress = value;
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
     * Gets the value of the comment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Comment }
     * 
     * 
     */
    public List<Comment> getComment() {
        if (comment == null) {
            comment = new ArrayList<Comment>();
        }
        return this.comment;
    }

    /**
     * Gets the value of the extraOption property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extraOption property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtraOption().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExtraOption }
     * 
     * 
     */
    public List<ExtraOption> getExtraOption() {
        if (extraOption == null) {
            extraOption = new ArrayList<ExtraOption>();
        }
        return this.extraOption;
    }

    /**
     * Gets the value of the unit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the unit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUnit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Unit }
     * 
     * 
     */
    public List<Unit> getUnit() {
        if (unit == null) {
            unit = new ArrayList<Unit>();
        }
        return this.unit;
    }

    /**
     * Gets the value of the rating property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rating property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRating().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Rating }
     * 
     * 
     */
    public List<Rating> getRating() {
        if (rating == null) {
            rating = new ArrayList<Rating>();
        }
        return this.rating;
    }

    /**
     * Gets the value of the objectType property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectType }
     *     
     */
    public ObjectType getObjectType() {
        return objectType;
    }

    /**
     * Sets the value of the objectType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectType }
     *     
     */
    public void setObjectType(ObjectType value) {
        this.objectType = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the value of the agent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the agent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAgent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Agent }
     * 
     * 
     */
   /** public List<Agent> getAgent() {
        if (agent == null) {
            agent = new ArrayList<Agent>();
        }
        return this.agent;
    }*/

}
