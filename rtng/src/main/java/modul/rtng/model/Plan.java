
package modul.rtng.model;

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
 *         &lt;element name="FromDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="ToDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Price">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Per_person" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "fromDate",
    "toDate",
    "price",
    "perPerson",
    "id"
})

@Entity
@Table(name = "plan")
@XmlRootElement(name = "Plan", namespace = "http://megatravell.com/object")
public class Plan {

    @Column
    @XmlElement(name = "FromDate", namespace = "http://megatravell.com/object", required = true)
    @XmlSchemaType(name = "date")
    protected Date fromDate;
    @Column
    @XmlElement(name = "ToDate", namespace = "http://megatravell.com/object", required = true)
    @XmlSchemaType(name = "date")
    protected Date toDate;
    @Column
    @XmlElement(name = "Price", namespace = "http://megatravell.com/object")
    protected double price;
    @Column
    @XmlElement(name = "Per_person", namespace = "http://megatravell.com/object")
    protected boolean perPerson;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(namespace = "http://megatravell.com/object")
    protected long id;

    public Plan() {
    }

    /**
     * Gets the value of the fromDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * Sets the value of the fromDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFromDate(Date value) {
        this.fromDate = value;
    }

    /**
     * Gets the value of the toDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getToDate() {
        return toDate;
    }

    /**
     * Sets the value of the toDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setToDate(Date value) {
        this.toDate = value;
    }

    /**
     * Gets the value of the price property.
     * 
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(double value) {
        this.price = value;
    }

    /**
     * Gets the value of the perPerson property.
     * 
     */
    public boolean isPerPerson() {
        return perPerson;
    }

    /**
     * Sets the value of the perPerson property.
     * 
     */
    public void setPerPerson(boolean value) {
        this.perPerson = value;
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
