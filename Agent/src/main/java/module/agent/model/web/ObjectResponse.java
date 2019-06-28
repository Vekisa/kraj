
package module.agent.model.web;

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
 *         &lt;element name="Object" type="{http://www.megatravell.com/wobject}ObjectWS"/>
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
    "object"
})
@XmlRootElement(name = "ObjectResponse", namespace = "http://www.megatravell.com/wobject")
public class ObjectResponse {

    @XmlElement(name = "Object", namespace = "http://www.megatravell.com/wobject", required = true)
    protected ObjectWS object;

    /**
     * Gets the value of the object property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectWS }
     *     
     */
    public ObjectWS getObject() {
        return object;
    }

    /**
     * Sets the value of the object property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectWS }
     *     
     */
    public void setObject(ObjectWS value) {
        this.object = value;
    }

}
