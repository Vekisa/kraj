
package newmodel;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ObjectWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ObjectWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
 *         &lt;element name="ObjectType" type="{http://www.megatravell.com/wobject}ObjectType"/>
 *         &lt;element name="CommentId" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ExtraOptionId" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="UnitId" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="RatingId" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AgentId" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectWS", namespace = "http://www.megatravell.com/wobject", propOrder = {
    "id",
    "name",
    "description",
    "category",
    "adress",
    "objectType",
    "commentId",
    "extraOptionId",
    "unitId",
    "ratingId",
    "agentId"
})
public class ObjectWS {

    @XmlElement(namespace = "http://www.megatravell.com/wobject")
    protected long id;
    @XmlElement(name = "Name", namespace = "http://www.megatravell.com/wobject", required = true)
    protected String name;
    @XmlElement(name = "Description", namespace = "http://www.megatravell.com/wobject", required = true)
    protected String description;
    @XmlElement(name = "Category", namespace = "http://www.megatravell.com/wobject")
    protected int category;
    @XmlElement(name = "Adress", namespace = "http://www.megatravell.com/address", required = true)
    protected Adress adress;
    @XmlElement(name = "ObjectType", namespace = "http://www.megatravell.com/wobject", required = true)
    protected ObjectType objectType;
    @XmlElement(name = "CommentId", namespace = "http://www.megatravell.com/wobject", type = Long.class)
    protected List<Long> commentId;
    @XmlElement(name = "ExtraOptionId", namespace = "http://www.megatravell.com/wobject", type = Long.class)
    protected List<Long> extraOptionId;
    @XmlElement(name = "UnitId", namespace = "http://www.megatravell.com/wobject", type = Long.class)
    protected List<Long> unitId;
    @XmlElement(name = "RatingId", namespace = "http://www.megatravell.com/wobject", type = Long.class)
    protected List<Long> ratingId;
    @XmlElement(name = "AgentId", namespace = "http://www.megatravell.com/wobject", type = Long.class)
    protected List<Long> agentId;

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
     * Gets the value of the commentId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the commentId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCommentId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getCommentId() {
        if (commentId == null) {
            commentId = new ArrayList<Long>();
        }
        return this.commentId;
    }

    /**
     * Gets the value of the extraOptionId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extraOptionId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtraOptionId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getExtraOptionId() {
        if (extraOptionId == null) {
            extraOptionId = new ArrayList<Long>();
        }
        return this.extraOptionId;
    }

    /**
     * Gets the value of the unitId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the unitId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUnitId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getUnitId() {
        if (unitId == null) {
            unitId = new ArrayList<Long>();
        }
        return this.unitId;
    }

    /**
     * Gets the value of the ratingId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ratingId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRatingId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getRatingId() {
        if (ratingId == null) {
            ratingId = new ArrayList<Long>();
        }
        return this.ratingId;
    }

    /**
     * Gets the value of the agentId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the agentId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAgentId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getAgentId() {
        if (agentId == null) {
            agentId = new ArrayList<Long>();
        }
        return this.agentId;
    }

}
