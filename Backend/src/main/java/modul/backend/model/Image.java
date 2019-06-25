
package modul.backend.model;

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
 *         &lt;element name="source" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "id",
        "source"
})
@XmlRootElement(name = "Image", namespace = "http://megatravell.com/object")
@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(name="id",namespace = "http://megatravell.com/object", required = true)
    private Long id;

    @XmlElement(namespace = "http://megatravell.com/object", required = true)
    @Column
    protected byte[] source;

    public Image() {
    }

    public Long getId() {
        return id;
    }

    /**
     * Gets the value of the source property.
     *
     * @return possible object is
     * byte[]
     */
    public byte[] getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     *
     * @param value allowed object is
     *              byte[]
     */
    public void setSource(byte[] value) {
        this.source = value;
    }

}
