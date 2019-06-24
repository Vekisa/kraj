
package modul.search.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
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
 *         &lt;element name="From" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="To" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Price">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}double">
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Month" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Per_person" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "price",
        "month",
        "fromDate",
        "toDate",
        "perPerson"
})
@XmlRootElement(name = "Plan", namespace = "http://megatravell.com/object")
@Entity
@Table(name = "plan")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @XmlElement(name = "FromDate", namespace = "http://megatravell.com/object", required = true)
    @XmlSchemaType(name = "date")
    @Column
    protected Date fromDate;
    @XmlElement(name = "ToDate", namespace = "http://megatravell.com/object", required = true)
    @XmlSchemaType(name = "date")
    @Column
    protected Date toDate;
    @XmlElement(name = "Price", namespace = "http://megatravell.com/object")
    @Column
    protected Double price;
    @XmlElement(name = "Month", namespace = "http://megatravell.com/object")
    @Column
    protected Integer month;
    @XmlElement(name = "Per_person", namespace = "http://megatravell.com/object")
    @Column
    protected Boolean perPerson;

    public Plan() {
    }

    public Long getId() {
        return id;
    }

    /**
     * Gets the value of the from property.
     *
     * @return possible object is
     * {@link Date }
     */
    public Date getFrom() {
        return fromDate;
    }

    /**
     * Sets the value of the from property.
     *
     * @param value allowed object is
     *              {@link Date }
     */
    public void setFrom(Date value) {
        this.fromDate = value;
    }

    /**
     * Gets the value of the to property.
     *
     * @return possible object is
     * {@link Date }
     */
    public Date getTo() {
        return toDate;
    }

    /**
     * Sets the value of the to property.
     *
     * @param value allowed object is
     *              {@link Date }
     */
    public void setTo(Date value) {
        this.toDate = value;
    }

    /**
     * Gets the value of the price property.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     */
    public void setPrice(double value) {
        this.price = value;
    }

    /**
     * Gets the value of the month property.
     */
    public int getMonth() {
        return month;
    }

    /**
     * Sets the value of the month property.
     */
    public void setMonth(int value) {
        this.month = value;
    }

    /**
     * Gets the value of the perPerson property.
     */
    public boolean isPerPerson() {
        return perPerson;
    }

    /**
     * Sets the value of the perPerson property.
     */
    public void setPerPerson(boolean value) {
        this.perPerson = value;
    }

}
