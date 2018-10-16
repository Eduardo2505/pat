
package mx.ine.sif.soapws.adminusuarios;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.ine.sif.soapws.adminusuarios package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AgregarUsuariosAGrupoRequest_QNAME = new QName("http://www.ine.mx/au/ws", "AgregarUsuariosAGrupoRequest");
    private final static QName _EliminarUsuariosDeGrupoRequest_QNAME = new QName("http://www.ine.mx/au/ws", "EliminarUsuariosDeGrupoRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.ine.sif.soapws.adminusuarios
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultarUsuarioRequest }
     * 
     */
    public ConsultarUsuarioRequest createConsultarUsuarioRequest() {
        return new ConsultarUsuarioRequest();
    }

    /**
     * Create an instance of {@link ParametrosLdap }
     * 
     */
    public ParametrosLdap createParametrosLdap() {
        return new ParametrosLdap();
    }

    /**
     * Create an instance of {@link CrearUsuarioRequest }
     * 
     */
    public CrearUsuarioRequest createCrearUsuarioRequest() {
        return new CrearUsuarioRequest();
    }

    /**
     * Create an instance of {@link Persona }
     * 
     */
    public Persona createPersona() {
        return new Persona();
    }

    /**
     * Create an instance of {@link Atributo }
     * 
     */
    public Atributo createAtributo() {
        return new Atributo();
    }

    /**
     * Create an instance of {@link Respuesta }
     * 
     */
    public Respuesta createRespuesta() {
        return new Respuesta();
    }

    /**
     * Create an instance of {@link Usuario }
     * 
     */
    public Usuario createUsuario() {
        return new Usuario();
    }

    /**
     * Create an instance of {@link AutenticarUsuarioRequest }
     * 
     */
    public AutenticarUsuarioRequest createAutenticarUsuarioRequest() {
        return new AutenticarUsuarioRequest();
    }

    /**
     * Create an instance of {@link OperacionGrupoRequest }
     * 
     */
    public OperacionGrupoRequest createOperacionGrupoRequest() {
        return new OperacionGrupoRequest();
    }

    /**
     * Create an instance of {@link EliminarUsuarioRequest }
     * 
     */
    public EliminarUsuarioRequest createEliminarUsuarioRequest() {
        return new EliminarUsuarioRequest();
    }

    /**
     * Create an instance of {@link RestablecerPasswordRequest }
     * 
     */
    public RestablecerPasswordRequest createRestablecerPasswordRequest() {
        return new RestablecerPasswordRequest();
    }

    /**
     * Create an instance of {@link ModificarUsuarioRequest }
     * 
     */
    public ModificarUsuarioRequest createModificarUsuarioRequest() {
        return new ModificarUsuarioRequest();
    }

    /**
     * Create an instance of {@link ConsultarUsuarioPorCurpRequest }
     * 
     */
    public ConsultarUsuarioPorCurpRequest createConsultarUsuarioPorCurpRequest() {
        return new ConsultarUsuarioPorCurpRequest();
    }

    /**
     * Create an instance of {@link CambiarPasswordRequest }
     * 
     */
    public CambiarPasswordRequest createCambiarPasswordRequest() {
        return new CambiarPasswordRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperacionGrupoRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ine.mx/au/ws", name = "AgregarUsuariosAGrupoRequest")
    public JAXBElement<OperacionGrupoRequest> createAgregarUsuariosAGrupoRequest(OperacionGrupoRequest value) {
        return new JAXBElement<OperacionGrupoRequest>(_AgregarUsuariosAGrupoRequest_QNAME, OperacionGrupoRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperacionGrupoRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ine.mx/au/ws", name = "EliminarUsuariosDeGrupoRequest")
    public JAXBElement<OperacionGrupoRequest> createEliminarUsuariosDeGrupoRequest(OperacionGrupoRequest value) {
        return new JAXBElement<OperacionGrupoRequest>(_EliminarUsuariosDeGrupoRequest_QNAME, OperacionGrupoRequest.class, null, value);
    }

}
