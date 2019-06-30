
package modul.search.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
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
 *         &lt;element ref="{http://megatravell.com/object}Comment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://megatravell.com/object}Reservation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.megatravell.com/user}Message" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://megatravell.com/object}Rating" maxOccurs="unbounded" minOccurs="0"/>
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
    "comment",
    "reservation",
    "message",
    "rating"
})
@Entity
@Table
@XmlRootElement(name = "RegisteredUser", namespace = "http://www.megatravell.com/user")
public class RegisteredUser
    extends User
{

    @XmlElement(name = "Comment", namespace = "http://megatravell.com/object")
    @JsonIgnore
    @OneToMany(mappedBy = "registeredUser")
    protected List<Comment> comment;
    @JsonIgnore
    @OneToMany(mappedBy = "registeredUser")
    @XmlElement(name = "Reservation", namespace = "http://megatravell.com/object")
    protected List<Reservation> reservation;
    @JsonIgnore
    @OneToMany(mappedBy = "registeredUser")
    @XmlElement(name = "Message", namespace = "http://www.megatravell.com/user")
    protected List<Message> message;
    @JsonIgnore
    @OneToMany(mappedBy = "registeredUser")
    @XmlElement(name = "Rating", namespace = "http://megatravell.com/object")
    protected List<Rating> rating;

    public RegisteredUser() {
    }

    public RegisteredUser(String firstName, String lastName, String email, @Size(max = 15) String username, String password, Adress adress, boolean isEnabled, XMLGregorianCalendar lastPasswordResetDate, boolean isVerified, Set<Role> role) {
        super(firstName, lastName, email, username, password, adress, isEnabled, lastPasswordResetDate, isVerified, role);
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
     * 
     * 
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

}
