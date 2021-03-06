
package module.agent.model.Users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import module.agent.model.*;

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
 *         &lt;element name="Aktivan" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element ref="{http://megatravell.com/object}Comment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://megatravell.com/object}Reservation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.megatravell.com/user}Message" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://megatravell.com/object}Rating" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "comment",
        "reservation",
        "message",
        "rating"
})
@XmlRootElement(name = "RegisteredUser", namespace = "http://www.megatravell.com/user")
@Entity
@Table
public class RegisteredUser
        extends User {

    @XmlElement(name = "Comment", namespace = "http://megatravell.com/object")
    @JsonIgnore
    @OneToMany(mappedBy = "registeredUser")
    protected List<Comment> comment;
    @XmlElement(name = "Reservation", namespace = "http://megatravell.com/object")
    @JsonIgnore
    @OneToMany(mappedBy = "registeredUser")
    protected List<Reservation> reservation;
    @XmlElement(name = "Message", namespace = "http://www.megatravell.com/user")
    @JsonIgnore
    @OneToMany(mappedBy = "registeredUser")
    protected List<Message> message;
    @XmlElement(name = "Rating", namespace = "http://megatravell.com/object")
    @JsonIgnore
    @OneToMany(mappedBy = "registeredUser")
    protected List<Rating> rating;

    public RegisteredUser() {
    }

    public RegisteredUser(String username, String firstName, String lastName, String email, String password, Adress adress, Boolean isEnabled, Boolean isVerified, List<Role> roles) {
        super(username, firstName, lastName, email, password, adress, isEnabled, isVerified, roles);
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
     */
    public List<Comment> getComment() {
        if (comment == null) {
            comment = new ArrayList<Comment>();
        }
        return this.comment;
    }

    /**
     * Gets the value of the reservation property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reservation property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReservation().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Reservation }
     */
    public List<Reservation> getReservation() {
        if (reservation == null) {
            reservation = new ArrayList<Reservation>();
        }
        return this.reservation;
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
     */
    public List<Rating> getRating() {
        if (rating == null) {
            rating = new ArrayList<Rating>();
        }
        return this.rating;
    }

}
