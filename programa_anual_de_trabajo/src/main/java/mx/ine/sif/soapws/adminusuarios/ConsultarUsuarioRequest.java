
package mx.ine.sif.soapws.adminusuarios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="parametrosLdap" type="{http://www.ine.mx/au/ws}ParametrosLdap"/>
 *         &lt;element name="uid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="debugMode" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "parametrosLdap",
    "uid",
    "debugMode"
})
@XmlRootElement(name = "ConsultarUsuarioRequest")
public class ConsultarUsuarioRequest {

    @XmlElement(required = true)
    protected ParametrosLdap parametrosLdap;
    @XmlElement(required = true)
    protected String uid;
    protected boolean debugMode;

    /**
     * Obtiene el valor de la propiedad parametrosLdap.
     * 
     * @return
     *     possible object is
     *     {@link ParametrosLdap }
     *     
     */
    public ParametrosLdap getParametrosLdap() {
        return parametrosLdap;
    }

    /**
     * Define el valor de la propiedad parametrosLdap.
     * 
     * @param value
     *     allowed object is
     *     {@link ParametrosLdap }
     *     
     */
    public void setParametrosLdap(ParametrosLdap value) {
        this.parametrosLdap = value;
    }

    /**
     * Obtiene el valor de la propiedad uid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUid() {
        return uid;
    }

    /**
     * Define el valor de la propiedad uid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUid(String value) {
        this.uid = value;
    }

    /**
     * Obtiene el valor de la propiedad debugMode.
     * 
     */
    public boolean isDebugMode() {
        return debugMode;
    }

    /**
     * Define el valor de la propiedad debugMode.
     * 
     */
    public void setDebugMode(boolean value) {
        this.debugMode = value;
    }

}
