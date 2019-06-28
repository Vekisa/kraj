
package modul.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
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
 *         &lt;element name="Text">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="150"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="DateOfPublication" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="approved" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element ref="{http://www.megatravell.com/user}RegisteredUser"/>
 *         &lt;element ref="{http://megatravell.com/object}Unit"/>
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
    "text",
    "dateOfPublication",
    "approved",
    "registeredUser",
    "unit",
    "id"
})
@XmlRootElement(name = "Comment", namespace = "http://megatravell.com/object")
@Entity
@Table
public class Comment {

    @XmlElement(name = "Text", namespace = "http://megatravell.com/object", required = true)
    @Column
    protected String text;
    @XmlElement(name = "DateOfPublication", namespace = "http://megatravell.com/object", required = true)
    @XmlSchemaType(name = "dateTime")
    @Column
    protected Date dateOfPublication;
    @XmlElement(namespace = "http://megatravell.com/object", defaultValue = "false")
    @Column
    protected boolean approved;
    @XmlElement(name = "RegisteredUser", namespace = "http://www.megatravell.com/user", required = true)
    @ManyToOne
    protected RegisteredUser registeredUser;
    @XmlElement(name = "Unit", namespace = "http://megatravell.com/object", required = true)
    @ManyToOne
    @JsonIgnore
    protected Unit unit;
    @XmlElement(namespace = "http://megatravell.com/object")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    public Comment(){}
    /**
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value) {
        this.text = value;
    }

    /**
     * Gets the value of the dateOfPublication property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getDateOfPublication() {
        return dateOfPublication;
    }

    /**
     * Sets the value of the dateOfPublication property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateOfPublication(Date value) {
        this.dateOfPublication = value;
    }

    /**
     * Gets the value of the approved property.
     * 
     */
    public boolean isApproved() {
        return approved;
    }

    /**
     * Sets the value of the approved property.
     * 
     */
    public void setApproved(boolean value) {
        this.approved = value;
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
