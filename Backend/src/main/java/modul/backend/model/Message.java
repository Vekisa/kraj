package modul.backend.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
 *         &lt;element name="fromUser" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
        "fromUser",
        "id"
})
@Entity
@XmlRootElement(name = "Message", namespace = "http://www.megatravell.com/user")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(namespace = "http://www.megatravell.com/user")
    protected long id;
    @Column
    @XmlElement(name = "Text", namespace = "http://www.megatravell.com/user", required = true)
    protected String text;
    @Size(min = 3, max = 250)
    @Column
    @XmlElement(namespace = "http://www.megatravell.com/user", required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date postingDate;
    @ManyToOne
    @XmlElement(name = "Agent", namespace = "http://www.megatravell.com/user", required = true)
    protected Agent agent;
    @ManyToOne
    @XmlElement(name = "RegisteredUser", namespace = "http://www.megatravell.com/user", required = true)
    protected RegisteredUser registeredUser;
    @Column
    @XmlElement(namespace = "http://www.megatravell.com/user")
    protected boolean seen;
    @Column
    @XmlElement(namespace = "http://www.megatravell.com/user")
    protected long fromUser;

    public Message() {
    }

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
     * Gets the value of the fromUser property.
     *
     */
    public long getFromUser() {
        return fromUser;
    }

    /**
     * Sets the value of the fromUser property.
     *
     */
    public void setFromUser(long value) {
        this.fromUser = value;
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
