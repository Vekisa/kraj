
package module.agent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import module.agent.model.web.ObjectWS;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
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
 *         &lt;element name="Category">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="5"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.megatravell.com/address}Adress"/>
 *         &lt;element ref="{http://megatravell.com/object}Comment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://megatravell.com/object}ExtraOption" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://megatravell.com/object}Unit" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://megatravell.com/object}Rating" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://megatravell.com/object}ObjectType"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
        "category",
        "adress",
        "comment",
        "extraOption",
        "unit",
        "rating",
        "objectType",
        "id",
        "agent"
})
@Entity
@Table
@XmlRootElement(name = "Object", namespace = "http://megatravell.com/object")
public class Object {

    @Size(min = 3, max = 30)
    @Column
    @XmlElement(name = "Name", namespace = "http://megatravell.com/object", required = true)
    protected String name;
    @Size(max = 250)
    @Column
    @XmlElement(name = "Description", namespace = "http://megatravell.com/object", required = true)
    protected String description;
    @Column
    @XmlElement(name = "Category", namespace = "http://megatravell.com/object")
    protected int category;
    @ManyToOne
    @XmlElement(name = "Adress", namespace = "http://www.megatravell.com/address", required = true)
    protected Adress adress;
    @JsonIgnore
    @OneToMany(mappedBy = "object")
    @XmlElement(name = "Comment", namespace = "http://megatravell.com/object")
    protected List<Comment> comment;
    @JsonIgnore
    @OneToMany
    @XmlElement(name = "ExtraOption", namespace = "http://megatravell.com/object")
    protected List<ExtraOption> extraOption;
    @JsonIgnore
    @OneToMany(mappedBy = "object")
    @XmlElement(name = "Unit", namespace = "http://megatravell.com/object")
    protected List<Unit> unit;
    @JsonIgnore
    @OneToMany(mappedBy = "object")
    @XmlElement(name = "Rating", namespace = "http://megatravell.com/object")
    protected List<Rating> rating;
    @ManyToOne
    @XmlElement(name = "ObjectType", namespace = "http://megatravell.com/object", required = true)
    protected ObjectType objectType;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(namespace = "http://megatravell.com/object")
    protected long id;
    @JsonIgnore
    @ManyToMany(mappedBy = "object")
    @XmlElement(name = "Agent", namespace = "http://www.megatravell.com/user")
    protected List<Agent> agent;

    public Object() {
    }

    public Object(ObjectWS objectWS){
        this.id=objectWS.getId();
        this.adress=objectWS.getAdress();
        this.category=objectWS.getCategory();
        this.name = objectWS.getName();
        this.description=objectWS.getDescription();
        this.objectType=objectWS.getObjectTypeId();
    }

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
    public List<Agent> getAgent() {
        if (agent == null) {
            agent = new ArrayList<Agent>();
        }
        return this.agent;
    }

}
