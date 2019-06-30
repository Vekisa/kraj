
package module.agent.model.web;

import javax.xml.bind.annotation.*;
import java.util.Date;


/**
 * <p>Java class for message complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="message">
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
 *         &lt;element name="seen" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fromUser" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="AgentId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="RegisteredUserId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "message", namespace = "http://www.megatravell.com/message", propOrder = {
    "text",
    "postingDate",
    "seen",
    "fromUser",
    "id",
    "agentId",
    "registeredUserId"
})
public class MessageWS {

    @XmlElement(name = "Text", namespace = "http://www.megatravell.com/message", required = true)
    protected String text;
    @XmlElement(namespace = "http://www.megatravell.com/message", required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date postingDate;
    @XmlElement(namespace = "http://www.megatravell.com/message")
    protected boolean seen;
    @XmlElement(namespace = "http://www.megatravell.com/message")
    protected long fromUser;
    @XmlElement(namespace = "http://www.megatravell.com/message")
    protected long id;
    @XmlElement(name = "AgentId", namespace = "http://www.megatravell.com/message")
    protected long agentId;
    @XmlElement(name = "RegisteredUserId", namespace = "http://www.megatravell.com/message")
    protected long registeredUserId;

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
     *     {@link Date }
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
     *     {@link Date }
     *     
     */
    public void setPostingDate(Date value) {
        this.postingDate = value;
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

    /**
     * Gets the value of the agentId property.
     * 
     */
    public long getAgentId() {
        return agentId;
    }

    /**
     * Sets the value of the agentId property.
     * 
     */
    public void setAgentId(long value) {
        this.agentId = value;
    }

    /**
     * Gets the value of the registeredUserId property.
     * 
     */
    public long getRegisteredUserId() {
        return registeredUserId;
    }

    /**
     * Sets the value of the registeredUserId property.
     * 
     */
    public void setRegisteredUserId(long value) {
        this.registeredUserId = value;
    }

}
