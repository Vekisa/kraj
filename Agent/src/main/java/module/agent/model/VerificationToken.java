
package module.agent.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Timestamp;
import java.util.Calendar;
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
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="verificationToken" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="expiryDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
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
    "id",
    "verificationToken",
    "expiryDate"
})
@Entity
@XmlRootElement(name = "VerificationToken", namespace = "http://www.megatravell.com/user")
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(namespace = "http://www.megatravell.com/user")
    protected long id;

    @Column
    @XmlElement(namespace = "http://www.megatravell.com/user", required = true)
    protected String verificationToken;

    @Column
    @XmlElement(namespace = "http://www.megatravell.com/user", required = true)
    @XmlSchemaType(name = "date")
    protected Date expiryDate;

    @OneToOne
    private User user;

    public VerificationToken() {
    }

    public VerificationToken(String verificationToken, Date expiryDate, User user) {
        this.verificationToken = verificationToken;
        this.expiryDate = expiryDate;
        this.user = user;
    }


    private Date expirationTime(int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, minutes);
        return new Date(cal.getTime().getTime());
    }

    public VerificationToken(String token, User user) {
        this.user = user;
        verificationToken = token;

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
     * Gets the value of the verificationToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerificationToken() {
        return verificationToken;
    }

    /**
     * Sets the value of the verificationToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerificationToken(String value) {
        this.verificationToken = value;
    }

    /**
     * Gets the value of the expiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the value of the expiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpiryDate(Date value) {
        this.expiryDate = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
