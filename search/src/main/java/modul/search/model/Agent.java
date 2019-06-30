
package modul.search.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.megatravell.com/user}User">
 *       &lt;sequence>
 *         &lt;element name="BussinesRegistrationNumber">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;length value="15"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.megatravell.com/user}Message" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://megatravell.com/object}Object" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "bussinesRegistrationNumber",
        "message",
        "object"
})
@Entity
@Table
@XmlRootElement(name = "Agent", namespace = "http://www.megatravell.com/user")
public class Agent
        extends User
{
    @Size(min = 3, max = 30)
    @Column
    @XmlElement(name = "BussinesRegistrationNumber", namespace = "http://www.megatravell.com/user", required = true)
    protected String bussinesRegistrationNumber;
    @JsonIgnore
    @OneToMany(mappedBy = "agent")
    @XmlElement(name = "Message", namespace = "http://www.megatravell.com/user")
    protected List<Message> message;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(joinColumns = @JoinColumn(name = "AGENT_ID"), inverseJoinColumns = @JoinColumn(name = "OBJECT_ID"))
    @XmlElement(name = "Object", namespace = "http://megatravell.com/object")
    protected Set<Object> object;

    public Agent() {
    }

    /**
     * Gets the value of the bussinesRegistrationNumber property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBussinesRegistrationNumber() {
        return bussinesRegistrationNumber;
    }

    /**
     * Sets the value of the bussinesRegistrationNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBussinesRegistrationNumber(String value) {
        this.bussinesRegistrationNumber = value;
    }

    /**
     * Gets the value of the message property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the message property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessage().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Message }
     *
     *
     */
    public List<Message> getMessage() {
        if (message == null) {
            message = new ArrayList<Message>();
        }
        return this.message;
    }

    /**
     * Gets the value of the object property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the object property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObject().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link java.lang.Object }
     *
     *
     */
    public Set<Object> getObject() {
        if (object == null) {
            object = new HashSet<>();
        }
        return this.object;
    }

}
