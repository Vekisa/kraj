
package module.agent.model.web;

import module.agent.model.Plan;

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
 *         &lt;element name="Plan" type="{http://www.megatravell.com/plan}plan"/>
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
    "plan"
})
@XmlRootElement(name = "PlanUpdateRequest", namespace = "http://www.megatravell.com/plan")
public class PlanUpdateRequest {

    @XmlElement(name = "Plan", namespace = "http://www.megatravell.com/plan", required = true)
    protected Plan plan;

    /**
     * Gets the value of the plan property.
     * 
     * @return
     *     possible object is
     *     {@link Plan }
     *     
     */
    public Plan getPlan() {
        return plan;
    }

    /**
     * Sets the value of the plan property.
     * 
     * @param value
     *     allowed object is
     *     {@link Plan }
     *     
     */
    public void setPlan(Plan value) {
        this.plan = value;
    }

}
