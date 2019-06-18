
package modul.auth.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
 *         &lt;element name="Year" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Made" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element ref="{http://megatravell.com/object}Plan" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "year",
        "made",
        "plan"
})
@XmlRootElement(name = "Price_schedule", namespace = "http://megatravell.com/object")
@Entity
@Table(name = "priceSchedule")
public class PriceSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @XmlElement(name = "Year", namespace = "http://megatravell.com/object")
    @Column
    protected int year;
    @XmlElement(name = "Made", namespace = "http://megatravell.com/object", required = true)
    @XmlSchemaType(name = "date")
    @Column
    protected Date made;
    @XmlElement(name = "Plan", namespace = "http://megatravell.com/object")
    @OneToMany
    protected List<Plan> plan;

    public PriceSchedule() {
    }

    public Long getId() {
        return id;
    }

    /**
     * Gets the value of the year property.
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     */
    public void setYear(int value) {
        this.year = value;
    }

    /**
     * Gets the value of the made property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public Date getMade() {
        return made;
    }

    /**
     * Sets the value of the made property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setMade(Date value) {
        this.made = value;
    }

    /**
     * Gets the value of the plan property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the plan property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlan().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Plan }
     */
    public List<Plan> getPlan() {
        if (plan == null) {
            plan = new ArrayList<Plan>();
        }
        return this.plan;
    }

}
