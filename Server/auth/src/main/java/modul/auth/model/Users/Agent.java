
package modul.auth.model.Users;

import modul.auth.model.Adress;
import modul.auth.model.Message;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "bussinesRegistrationNumber",
        "message"
})
@XmlRootElement(name = "Agent", namespace = "http://www.megatravell.com/user")
@Entity
@Table
public class Agent
        extends User {
    @XmlElement(name = "BussinesRegistrationNumber", namespace = "http://www.megatravell.com/user", required = true)
    @Column
    protected String bussinesRegistrationNumber;
    @XmlElement(name = "Message", namespace = "http://www.megatravell.com/user")
    @OneToMany(mappedBy = "agent")
    protected List<Message> message;

    public Agent() {
    }

    public Agent(String username, String firstName, String lastName, String email, String password, Adress adress, Boolean isEnabled, Boolean isVerified, List<Role> roles) {
        super(username, firstName, lastName, email, password, adress, isEnabled, isVerified, roles);
    }

    /**
     * Gets the value of the bussinesRegistrationNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getBussinesRegistrationNumber() {
        return bussinesRegistrationNumber;
    }

    /**
     * Sets the value of the bussinesRegistrationNumber property.
     *
     * @param value allowed object is
     *              {@link String }
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
     */
    public List<Message> getMessage() {
        if (message == null) {
            message = new ArrayList<Message>();
        }
        return this.message;
    }

}
