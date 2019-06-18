
package modul.auth.model;

import modul.auth.model.Users.RegisteredUser;

import javax.persistence.*;
import javax.xml.bind.annotation.*;


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
 *         &lt;element name="mark">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="1"/>
 *               &lt;maxInclusive value="5"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element ref="{http://megatravell.com/object}Object"/>
 *         &lt;element ref="{http://www.megatravell.com/user}RegisteredUser"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "mark",
        "object",
        "registeredUser"
})
@XmlRootElement(name = "Rating", namespace = "http://megatravell.com/object")
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @XmlElement(namespace = "http://megatravell.com/object")
    @Column
    protected int mark;
    @XmlElement(name = "Object", namespace = "http://megatravell.com/object", required = true)
    @ManyToOne
    protected Object object;
    @XmlElement(name = "RegisteredUser", namespace = "http://www.megatravell.com/user", required = true)
    @ManyToOne
    protected RegisteredUser registeredUser;

    public Rating() {
    }

    public Long getId() {
        return id;
    }

    /**
     * Gets the value of the mark property.
     */
    public int getMark() {
        return mark;
    }

    /**
     * Sets the value of the mark property.
     */
    public void setMark(int value) {
        this.mark = value;
    }

    /**
     * Gets the value of the object property.
     *
     * @return possible object is
     * {@link Object }
     */
    public Object getObject() {
        return object;
    }

    /**
     * Sets the value of the object property.
     *
     * @param value allowed object is
     *              {@link Object }
     */
    public void setObject(Object value) {
        this.object = value;
    }

    /**
     * Gets the value of the registeredUser property.
     *
     * @return possible object is
     * {@link RegisteredUser }
     */
    public RegisteredUser getRegisteredUser() {
        return registeredUser;
    }

    /**
     * Sets the value of the registeredUser property.
     *
     * @param value allowed object is
     *              {@link RegisteredUser }
     */
    public void setRegisteredUser(RegisteredUser value) {
        this.registeredUser = value;
    }

}
