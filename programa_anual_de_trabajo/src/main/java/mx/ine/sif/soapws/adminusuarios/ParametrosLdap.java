
package mx.ine.sif.soapws.adminusuarios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ParametrosLdap complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ParametrosLdap">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alias" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="base" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ldapUserDn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ldapPass" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParametrosLdap", propOrder = {
    "alias",
    "base",
    "ldapUserDn",
    "ldapPass"
})
public class ParametrosLdap {

    @XmlElement(required = true)
    protected String alias;
    @XmlElement(required = true, nillable = true)
    protected String base;
    @XmlElement(required = true, nillable = true)
    protected String ldapUserDn;
    @XmlElement(required = true, nillable = true)
    protected String ldapPass;

    /**
     * Obtiene el valor de la propiedad alias.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Define el valor de la propiedad alias.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlias(String value) {
        this.alias = value;
    }

    /**
     * Obtiene el valor de la propiedad base.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBase() {
        return base;
    }

    /**
     * Define el valor de la propiedad base.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBase(String value) {
        this.base = value;
    }

    /**
     * Obtiene el valor de la propiedad ldapUserDn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLdapUserDn() {
        return ldapUserDn;
    }

    /**
     * Define el valor de la propiedad ldapUserDn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLdapUserDn(String value) {
        this.ldapUserDn = value;
    }

    /**
     * Obtiene el valor de la propiedad ldapPass.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLdapPass() {
        return ldapPass;
    }

    /**
     * Define el valor de la propiedad ldapPass.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLdapPass(String value) {
        this.ldapPass = value;
    }

}
