
package modul.backend.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="Made" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element ref="{http://megatravell.com/object}Plan" maxOccurs="unbounded" minOccurs="0"/>
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
    "made",
    "plan",
    "id"
})
@XmlRootElement(name = "Price_schedule", namespace = "http://megatravell.com/object")
public class PriceSchedule {

    @XmlElement(name = "Made", namespace = "http://megatravell.com/object", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar made;
    @XmlElement(name = "Plan", namespace = "http://megatravell.com/object")
    protected List<Plan> plan;
    @XmlElement(namespace = "http://megatravell.com/object")
    protected long id;

    /**
     * Gets the value of the made property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMade() {
        return made;
    }

    /**
     * Sets the value of the made property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMade(XMLGregorianCalendar value) {
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
     * 
     * 
     */
    public List<Plan> getPlan() {
        if (plan == null) {
            plan = new ArrayList<Plan>();
        }
        return this.plan;
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
