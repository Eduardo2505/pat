
package mx.ine.sif.soapws.adminusuarios;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para OperacionGrupoRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="OperacionGrupoRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="parametrosLdap" type="{http://www.ine.mx/au/ws}ParametrosLdap"/>
 *         &lt;element name="uids" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="grupo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idSistema" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "OperacionGrupoRequest", propOrder = {
    "parametrosLdap",
    "uids",
    "grupo",
    "idSistema",
    "usuario",
    "debugMode"
})
public class OperacionGrupoRequest {

    @XmlElement(required = true)
    protected ParametrosLdap parametrosLdap;
    @XmlElement(required = true)
    protected List<String> uids;
    @XmlElement(required = true)
    protected String grupo;
    protected int idSistema;
    @XmlElement(required = true)
    protected String usuario;
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
     * Gets the value of the uids property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the uids property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUids().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getUids() {
        if (uids == null) {
            uids = new ArrayList<String>();
        }
        return this.uids;
    }

    /**
     * Obtiene el valor de la propiedad grupo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGrupo() {
        return grupo;
    }

    /**
     * Define el valor de la propiedad grupo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrupo(String value) {
        this.grupo = value;
    }

    /**
     * Obtiene el valor de la propiedad idSistema.
     * 
     */
    public int getIdSistema() {
        return idSistema;
    }

    /**
     * Define el valor de la propiedad idSistema.
     * 
     */
    public void setIdSistema(int value) {
        this.idSistema = value;
    }

    /**
     * Obtiene el valor de la propiedad usuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Define el valor de la propiedad usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuario(String value) {
        this.usuario = value;
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
