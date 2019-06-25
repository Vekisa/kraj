
package modul.backend.model;

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
 *         &lt;element name="postingDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" form="qualified"/>
 *         &lt;element ref="{http://www.megatravell.com/user}Agent"/>
 *         &lt;element ref="{http://www.megatravell.com/user}RegisteredUser"/>
 *         &lt;element name="seen" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="from" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
    "postingDate",
    "agent",
    "registeredUser",
    "seen",
    "from",
    "id"
})
@XmlRootElement(name = "Message", namespace = "http://www.megatravell.com/user")
@Entity
@Table
public class Message {

    @XmlElement(name = "Text", namespace = "http://www.megatravell.com/user", required = true)
    @Column
    protected String text;
    @XmlElement(namespace = "http://www.megatravell.com/user", required = true)
    @XmlSchemaType(name = "dateTime")
    @Column
    protected Date postingDate;
    @XmlElement(name = "Agent", namespace = "http://www.megatravell.com/user", required = true)
    @ManyToOne
    protected Agent agent;
    @XmlElement(name = "RegisteredUser", namespace = "http://www.megatravell.com/user", required = true)
    @ManyToOne
    protected RegisteredUser registeredUser;
    @XmlElement(namespace = "http://www.megatravell.com/user")
    @Column
    protected boolean seen;
    @XmlElement(namespace = "http://www.megatravell.com/user")
    @Column
    protected long from;
    @XmlElement(namespace = "http://www.megatravell.com/user")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    public Message(){}
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
     * Gets the value of the postingDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public Date getPostingDate() {
        return postingDate;
    }

    /**
     * Sets the value of the postingDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setPostingDate(Date value) {
        this.postingDate = value;
    }

    /**
     * Gets the value of the agent property.
     *
     * @return
     *     possible object is
     *     {@link Agent }
     *
     */
    public Agent getAgent() {
        return agent;
    }

    /**
     * Sets the value of the agent property.
     *
     * @param value
     *     allowed object is
     *     {@link Agent }
     *
     */
    public void setAgent(Agent value) {
        this.agent = value;
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
     * Gets the value of the seen property.
     * 
     */
    public boolean isSeen() {
        return seen;
    }

    /**
     * Sets the value of the seen property.
     * 
     */
    public void setSeen(boolean value) {
        this.seen = value;
    }

    /**
     * Gets the value of the from property.
     * 
     */
    public long getFrom() {
        return from;
    }

    /**
     * Sets the value of the from property.
     * 
     */
    public void setFrom(long value) {
        this.from = value;
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
