
package modul.rtng.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * <p>Java class for User complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="User">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="FirstName">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="40"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LastName">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="40"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Email">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="40"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Password">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="5"/>
 *               &lt;maxLength value="40"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.megatravell.com/address}Adress"/>
 *         &lt;element name="isEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="lastPasswordResetDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="isVerified" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element ref="{http://www.megatravell.com/user}VerificationToken"/>
 *         &lt;element ref="{http://www.megatravell.com/user}Role" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "User", namespace = "http://www.megatravell.com/user", propOrder = {
    "id",
    "firstName",
    "lastName",
    "email",
    "username",
    "password",
    "adress",
    "isEnabled",
    "lastPasswordResetDate",
    "isVerified",
    "verificationToken",
    "role"
})
@XmlSeeAlso({
    RegisteredUser.class,
    Agent.class
})
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @XmlElement(namespace = "http://www.megatravell.com/user")
    protected long id;
    @Size(min = 3, max = 30)
    @Column
    @XmlElement(name = "FirstName", namespace = "http://www.megatravell.com/user", required = true)
    protected String firstName;
    @Size(min = 3, max = 30)
    @Column
    @XmlElement(name = "LastName", namespace = "http://www.megatravell.com/user", required = true)
    protected String lastName;
    @Size(min = 3, max = 30)
    @Column
    @XmlElement(name = "Email", namespace = "http://www.megatravell.com/user", required = true)
    protected String email;
    @Size(max = 15)
    @Column
    @XmlElement(namespace = "http://www.megatravell.com/user", required = true)
    protected String username;
    @JsonIgnore
    @Column
    @XmlElement(name = "Password", namespace = "http://www.megatravell.com/user", required = true)
    protected String password;
    @ManyToOne
    @XmlElement(name = "Adress", namespace = "http://www.megatravell.com/address", required = true)
    protected Adress adress;
    @JsonIgnore
    @Column
    @XmlElement(namespace = "http://www.megatravell.com/user")
    protected boolean isEnabled;
    @Column
    @XmlElement(namespace = "http://www.megatravell.com/user", required = true)
    @XmlSchemaType(name = "date")
    protected Date lastPasswordResetDate;
    @JsonIgnore
    @Column
    @XmlElement(namespace = "http://www.megatravell.com/user")
    protected boolean isVerified;
    @JsonIgnore
    @OneToOne
    @XmlElement(name = "VerificationToken", namespace = "http://www.megatravell.com/user", required = true)
    protected VerificationToken verificationToken;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    @XmlElement(name = "Role", namespace = "http://www.megatravell.com/user")
    protected Set<Role> role;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public User() {
    }

    public User(String firstName, String lastName, String email, @Size(max = 15) String username, String password, Adress adress, boolean isEnabled, XMLGregorianCalendar lastPasswordResetDate, boolean isVerified,Set<Role> role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.adress = adress;
        this.isEnabled = isEnabled;
        this.isVerified = isVerified;
        this.role = role;
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
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the adress property.
     * 
     * @return
     *     possible object is
     *     {@link Adress }
     *     
     */
    public Adress getAdress() {
        return adress;
    }

    /**
     * Sets the value of the adress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Adress }
     *     
     */
    public void setAdress(Adress value) {
        this.adress = value;
    }

    /**
     * Gets the value of the isEnabled property.
     * 
     */
    public boolean isIsEnabled() {
        return isEnabled;
    }

    /**
     * Sets the value of the isEnabled property.
     * 
     */
    public void setIsEnabled(boolean value) {
        this.isEnabled = value;
    }

    /**
     * Gets the value of the lastPasswordResetDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    /**
     * Sets the value of the lastPasswordResetDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastPasswordResetDate(Date value) {
        this.lastPasswordResetDate = value;
    }

    /**
     * Gets the value of the isVerified property.
     * 
     */
    public boolean isIsVerified() {
        return isVerified;
    }

    /**
     * Sets the value of the isVerified property.
     * 
     */
    public void setIsVerified(boolean value) {
        this.isVerified = value;
    }

    /**
     * Gets the value of the verificationToken property.
     * 
     * @return
     *     possible object is
     *     {@link VerificationToken }
     *     
     */
    public VerificationToken getVerificationToken() {
        return verificationToken;
    }

    /**
     * Sets the value of the verificationToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationToken }
     *     
     */
    public void setVerificationToken(VerificationToken value) {
        this.verificationToken = value;
    }

    /**
     * Gets the value of the role property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the role property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRole().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Role }
     * 
     * 
     */
    public Set<Role> getRole() {
        if (role == null) {
            role = new HashSet<>();
        }
        return this.role;
    }



}
