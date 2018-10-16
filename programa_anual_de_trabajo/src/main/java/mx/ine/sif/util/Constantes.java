package mx.ine.sif.util;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jdom2.Namespace;
import org.springframework.stereotype.Component;

/**
 * @author IFE
 * @version 1.0 @since 19 de marzo del 2014
 */

@Component("constante")
public class Constantes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8622621984437959203L;
	/**
	 * Identificador Serializable autogenerado
	 */
	
	public static final String rutaGluster = System.getProperty("glusterFS");

	/**
	 * TamaÒo 1MB = 1048576 bytes m·ximo de archivos csv
	 * TamaÒ0 5MB = 5242880 bytes m·ximo de archivos csv 
	 */
	public static final long tamanioMaxCSV = 5242880;
	
	/**
	 * Arreglo con los nombres de los archivos .cer de los certificados del servidor
	 */
	public static final String CERTIFICADOS[] = new String[]{"AC0_SAT.cer", "AC1_SAT.cer",
														     "AC2_SAT.cer", "AC3_SAT.cer",
														     "AC4_SAT.cer", "ac2_4096.cer"};
	
	/**
	 * Regex para las validaciones de los formularios
	 * 
	 * 
	 * */
	public static final String REG_EX_VALIDA_CVE_ELECTOR_PRIMERA_PARTE			= "[a-zA-Z]{6}";
	public static final String REG_EX_VALIDA_CVE_ELECTOR_SEGUNDA_PARTE			= "(\\d{2}((0[13578]|1[02])(0[1-9]|[12]\\d|3[01])|(0[13456789]|1[012])(0[1-9]|[12]\\d|30)|02(0[1-9]|1\\d|2[0-9])))|([02468][048]|[13579][26])0229";
	public static final String REG_EX_VALIDA_CVE_ELECTOR_TERCERA_PARTE			= "((0[0-9]|[12][0-9]|3[0-2])|87|88)(H|M|h|m)[0-9]{3}";
	public static final String REG_EX_VALIDA_NOMBRE_PERSONA						= "[a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò][a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò ]{0,49}";
	public static final String REG_EX_VALIDA_APELLIDOS_PERSONA					= "[a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò‹¸][a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò‹¸ ]{0,39}";
	//public static final String PATRON_CURP										= "^[a-zA-Z]{4}[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[hmHM]{1}(as|AS|bc|BC|bs|BS|cc|CC|cs|CS|ch|CH|cl|CL|cm|CM|df|DF|dg|DG|gt|GT|gr|GR|hg|HG|jc|JC|mc|MC|mn|MN|ms|MS|nt|NT|nl|NL|oc|OC|pl|PL|qt|QT|qr|QR|sp|SP|sl|SL|sr|SR|tc|TC|ts|TS|tl|TL|vz|VZ|yn|YN|zs|ZS|ne|NE)[b-df-hj-np-tv-z-B-DF-HJ-NP-TV-Z]{3}[0-9a-zA-Z]{1}[0-9]{1}$";
	public static final String PATRON_CURP										= "^[a-zA-Z][a,A,e,E,i,I,o,O,u,U][a-zA-Z]{2}[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[hmHM]{1}(as|AS|bc|BC|bs|BS|cc|CC|cs|CS|ch|CH|cl|CL|cm|CM|df|DF|dg|DG|gt|GT|gr|GR|hg|HG|jc|JC|mc|MC|mn|MN|ms|MS|nt|NT|nl|NL|oc|OC|pl|PL|qt|QT|qr|QR|sp|SP|sl|SL|sr|SR|tc|TC|ts|TS|tl|TL|vz|VZ|yn|YN|zs|ZS|ne|NE)[b-df-hj-np-tv-z-B-DF-HJ-NP-TV-Z]{3}[0-9a-zA-Z]{1}[0-9]{1}$";
	public static final String PATRON_RFC										= "^[a-zA-Z&]{4}[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])([a-zA-Z,0-9]{3})?";
	public static final String RFC_ORGANIZACIONES								= "^[a-zA-Z—Ò&]{3,4}([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])([a-zA-Z,0-9]{3})?";
	public static final String REG_EX_VALIDA_OCUPACION							= "[0-9a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò‹¸‰ÎÔˆ¸ƒÀœ÷‹().:;,\\-_\\'¥\\#][0-9a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò‹¸‰ÎÔˆ¸ƒÀœ÷‹().:;,\\-_\\'¥\\# ]{0,49}";
	public static final String REG_EX_VALIDA_OCUPACION1							= "[0-9a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò‹¸‰ÎÔˆ¸ƒÀœ÷‹().:;,\\-_\\'¥\\#][0-9a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò‹¸‰ÎÔˆ¸ƒÀœ÷‹().:;,\\-_\\'¥\\# ]{0,254}";
	public static final String REG_EX_VALIDA_RAZON_SOCIAL						= "[0-9a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò‹¸‰ÎÔˆ¸ƒÀœ÷‹().:;,\\-_\\'¥\\#][0-9a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò‹¸‰ÎÔˆ¸ƒÀœ÷‹().:;,\\-_\\'¥\\# ]{0,199}";
	public static final String REG_EX_VALIDA_EMAIL								= "^[a-zA-Z0-9+&*-]+(?:.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+.)+[a-zA-Z]{2,7}$";
	public static final String REG_EX_VALIDA_TOPE								= "([0-9]{1,13})(.)([0-9]{2})";
	
	public static final String REG_EX_VALIDA_CALLE		 						= "[0-9a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò‹¸‰ÎÔˆ¸ƒÀœ÷‹().;,\\-_\\'¥\\#/][0-9a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò‹¸‰ÎÔˆ¸ƒÀœ÷‹().;,\\-_\\'¥\\#/ ]{0,79}";
	public static final String REG_EX_VALIDA_NUM_EXT_INT						= "[0-9a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò‹¸‰ÎÔˆ¸ƒÀœ÷‹().;,\\-_\\'¥\\#/][0-9a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò‹¸‰ÎÔˆ¸ƒÀœ÷‹().;,\\-_\\'¥\\#/ ]{0,14}";
	public static final String REG_EX_VALIDA_COLONIA							= "[0-9a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò‹¸‰ÎÔˆ¸ƒÀœ÷‹().;,\\-\\'¥\\#/][0-9a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò‹¸‰ÎÔˆ¸ƒÀœ÷‹().;,\\-\\'¥\\#/ ]{1,99}";
	public static final String PATRON_CODIGO_POSTAL								= "^([1-9]{2}|[0-9][1-9]|[1-9][0-9])[0-9]{3}$";
	public static final String REG_EX_VALIDA_LADA								= "[0-9]{2,5}";
	public static final String REG_EX_VALIDA_TELEFONO							= "[0-9]{7,15}";
	public static final String REG_EX_VALIDA_EXTENSION							= "[0-9]{1,20}";
	public static final String ERROR_VALIDACION									= "ERROR_VALIDACION";
//Constantes para Cuentas Bancarias
	public static final String REG_EX_VALIDA_NUMERALIA_ORD						= "[1-9]";
	public static final String REG_EX_VALIDA_CLAVE_BANCO						= "[0-9]{18}";
	public static final String REG_EX_VALIDA_CUENTA								= "[0-9]{6,15}";
	public static final String REG_EX_VALIDA_RAZON								= "[0-9a-zA-Z.,:;][0-9a-zA-Z.:;, ]{0,255}";
	public static final String PATRON_SIN_ACENTOS								= "/[^·¡È…ÌÕÛ”˙⁄]/";
	public static final String REG_EX_VALIDA_SINACENTOS							= "[0-9a-zA-Z]{0,255}";
	public static final String REG_EX_VALIDA_CAMPOS								= "[0-9a-zA-Z—Ò #.,-:;/()]{0,1500}";

	public static final String PATRON_OBSERVACIONES								= "/[_\\w\\s\\,\\.\\-$%\\+]+/";
	public static final String PATRON_OBSERVACIONES_2							= "/[A-Za-z0-9-\\.\\,\\/]+/";
//  id's permisos
	public static final Integer PERMISO_CAPTURAR = 1;
	public static final Integer PERMISO_CONSULTAR = 2;
	public static final Integer PERMISO_MODIFICAR  = 3;
	public static final Integer PERMISO_ELIMINAR = 4;
//Constantes para Inversion de Valores.
	public static final String REG_EX_VALIDA_CAMPO_LEYENDAOTRO					= "[0-9a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò‹¸‰ÎÔˆ¸ƒÀœ÷‹().:;,\\-_\\'¥\\#][0-9a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò‹¸‰ÎÔˆ¸ƒÀœ÷‹().:;,\\-_\\'¥\\# ]{0,49}";
	public static final String PATRON_NUMEROS									= "/[0-9]/";
	
	public static final String[] PERMISOS={"","Capturar","Consultar","Modificar","Eliminar"};
	public static final Integer[] PERMISOSEXISTENTES={1,2,3,4};
	
	//id's procesos electorales
	public static final Integer ORDINARIO = 1;
	public static final Integer PRECAMPANIA = 2;
	public static final Integer CAMPANIA  = 3;
	
	
	public static final String SERVLET_DOWNLOADER_NAME = "/media";
	
	// id sistema 
	
		public static final Integer IDSISTEMA = 96;
	
	// id sistema 
		public static final String IDVERSION = "3.0";
		private String version=IDVERSION;
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++++++++++++++++++++++++++++++++       enum Roles                            ++++++++++++++++++++++++++++++++++++++++++++++++++++
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		public static final String ROL_INE_ADMIN=Roles.ROL_INE_ADMIN.getRol();
		public static final String ROL_REPRESENTANTEFINANZAS=Roles.ROL_REPRESENTANTEFINANZAS.getRol();
		public static final String ROL_REPRESENTANTEFINANZAS_JL=Roles.ROL_REPRESENTANTEFINANZAS_JL.getRol();
		public static final String ROL_ADMINSO=Roles.ROL_ADMINSO.getRol();
		public static final String ROL_AUDITOR=Roles.ROL_AUDITOR.getRol();
		public static final String ROL_SUPERVISORFISCALIZACION=Roles.ROL_SUPERVISORFISCALIZACION.getRol();
		public static final String ROL_CAPTURISTA=Roles.ROL_CAPTURISTA.getRol();
		public static final String ROL_CONSULTARESTRINGIDA=Roles.ROL_CONSULTARESTRINGIDA.getRol();
		public static final String ROL_CONSULTA=Roles.ROL_CONSULTA.getRol();
		public static final String ROL_CANDIDATO=Roles.ROL_CANDIDATO.getRol();
		public static final String ROL_CANDIDATO_IND=Roles.ROL_CANDIDATO_IND.getRol();
		public static final String ROL_CANDIDATORP=Roles.ROL_CANDIDATORP.getRol();
		public static final String ROL_PRECANDIDATO=Roles.ROL_PRECANDIDATO.getRol();
		public static final String ROL_ASPIRANTE=Roles.ROL_ASPIRANTE.getRol();
		public static final String ROL_ADAPPAPO_OC	= Roles.ROL_ADAPPAPO_OC.getRol();
		public static final String ROL_RPCAMPANIA	= Roles.ROL_RPCAMPANIA.getRol();
		public static final String ROL_CONSULTATEPJ_OC	=Roles.ROL_CONSULTATEPJ_OC	.getRol();
		public static final String ROL_ADRN_OC=Roles.ROL_ADRN_OC.getRol();
		public static final String ROL_AUDITOREXTERNO=Roles.ROL_AUDITOREXTERNO.getRol(); 

		
public enum Roles{
			
			ROL_INE_ADMIN					(1,"SIF.INE_ADMIN.OC",				"ADMINISTRADOR INE"),
			ROL_ADMINSO						(2,"SIF.ADMINSO.OC",				"ADMINISTRADOR DE SUJETO OBLIGADO"),
			ROL_SUPERVISORFISCALIZACION		(3,"SIF.SUPERVISORFISCALIZACION.OC","SUPERVISOR DE FISCALIZACI”N"),
			ROL_AUDITOR						(4,"SIF.AUDITOR.OC",				"AUDITOR"),
			ROL_CONSULTARESTRINGIDA			(5,"SIF.CONSULTARESTRINGIDA.OC",	"CONSULTA RESTRINGIDA"),
			ROL_CONSULTA					(6,"SIF.CONSULTA.OC",				"CONSULTA"),
			ROL_CANDIDATO					(7,"SIF.CANDIDATO.OC",				"CANDIDATO"),
			ROL_CANDIDATO_IND				(8,"SIF.CANDIDATOIND.OC",			"CANDIDATO INDEPENDIENTE"),
			ROL_CAPTURISTA					(9,"SIF.CAPTURISTA.OC",				"CAPTURISTA"),
			ROL_REPRESENTANTEFINANZAS		(10,"SIF.REPRESENTANTEFINANZAS.OC",	"RESPONSABLE DE FINANZAS CEN"),
			ROL_REPRESENTANTEFINANZAS_JL	(11,"SIF.REPRESENTANTEFINANZAS.JL",	"RESPONSABLE DE FINANZAS LOCAL"),
			ROL_CANDIDATORP					(12,"SIF.CANDIDATORP.OC",			"CANDIDATO RP"),
			ROL_PRECANDIDATO				(13,"SIF.PRECANDIDATO.OC",			"PRECANDIDATO"),
			ROL_ASPIRANTE					(14,"SIF.ASPIRANTE.OC",				"ASPIRANTE"),
			ROL_RPCAMPANIA					(15,"SIF.RPCAMPANIA.OC",			"CANDIDATO RP"),
			ROL_CONSULTATEPJ_OC				(16,"SIF.CONSULTATEPJ.OC",				"CONSULTA TEPJ"),
			ROL_ADAPPAPO_OC					(18,"SIF.ADAPPAPO.OC",			"ADMINISTRADOR ADAPPAPO"),
			ROL_ADRN_OC						(19,"SIF.ADRN.OC", 				"ADMINISTRADOR ADRN"),
			ROL_AUDITOREXTERNO				(28,"SIF.AUDITOREXTERNO.OC", 				"AUDITOR EXTERNO");
			;
			
			
			private final int id;
			private final String rol;
			private final String descricpion;
			private static final Map<String, Integer> mapIds= Collections.unmodifiableMap(inicializarMapIds());
			private static final Map<Integer, String> mapRoles= Collections.unmodifiableMap(inicializarMapRoles());
			private static final Map<Integer, String> mapDescripciones= Collections.unmodifiableMap(inicializarMapDescripciones());
			
			private Roles(int id, String rol, String descripcion){
				this.id=id;
				this.rol=rol;
				this.descricpion=descripcion;
			}
			
			public Integer getIdRol(){
				return id;
			}
			
			public String getRol(){
				return rol;
			}
			
			public String getDescripcion(){
				return rol;
			}
			
			public static Integer getIdRol(String rol){
				if(mapIds.containsKey(rol))
					return mapIds.get(rol);
				return null;
			}
			
			
			
			public static String getRol(Integer id){
				if(mapRoles.containsKey(id))
					return mapRoles.get(id);
				return null;
			}
			
			public static String getDescripcion(Integer id){
				if(mapDescripciones.containsKey(id))
					return mapDescripciones.get(id);
				return null;
			}
			
			private static Map<String, Integer> inicializarMapIds(){
				Map<String,Integer> map=new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
				for(Roles r: Roles.values()){
					map.put(r.rol, r.id);
				}
				return map;
			}
			
			private static Map<Integer, String> inicializarMapRoles(){
				Map<Integer,String> map=new HashMap<Integer, String>();
				for(Roles r: Roles.values()){
					map.put(r.id,r.rol);
				}
				return map;
			}
			
			private static Map<Integer, String> inicializarMapDescripciones(){
				Map<Integer,String> map=new HashMap<Integer, String>();
				for(Roles r: Roles.values()){
					map.put(r.id,r.descricpion);
				}
				return map;
			}
		}

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++++++++++++++++++++++++++++++++       Termina enum Roles                            ++++++++++++++++++++++++++++++++++++++++++++++++++++
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		
		public static final Integer USUARIO_EXTERNO=1;
		public static final Integer USUARIO_INTERNO=2;
		
		public static final Integer ID_PROCESO_PRE_CAM=2;
		
//		// Padilla validaciones //
//		public static final String REG_PATR_MILITANTES		 			= "[0-9a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò‹¸‰ÎÔˆ¸ƒÀœ÷‹().;,\\-_\\'¥\\#/][0-9a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò‹¸‰ÎÔˆ¸ƒÀœ÷‹().;,\\-_\\'¥\\#/ ]{0,79}";
//		public static final String SOLO_NUMEROS							= "[0-9]+";
//// Padilla validaciones //
//				public static final String REG_PATR_MILITANTES		 			= "[0-9a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò‹¸‰ÎÔˆ¸ƒÀœ÷‹().;,\\-_\\'¥\\#/][0-9a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò‹¸‰ÎÔˆ¸ƒÀœ÷‹().;,\\-_\\'¥\\#/ ]{0,79}";
//				public static final String SOLO_NUMEROS							= "[0-9]+";
	
		// Padilla validaciones //
		public static final String REG_PATR_MILITANTES		 			= "[0-9a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò‹¸‰ÎÔˆ¸ƒÀœ÷‹().;,\\-_\\'¥\\#/][0-9a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò‹¸‰ÎÔˆ¸ƒÀœ÷‹().;,\\-_\\'¥\\#/ ]{0,79}";
		public static final String SOLO_NUMEROS							= "[0-9]+";
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
		
		//Constante polizas
		public static final String descripcionAvisosDeContratacion = "AVISO DE CONTRATACION";
		
		//Contantes parse Xml
		//public static final String REG_PATR_MILITANTES		 			= "[0-9a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò‹¸‰ÎÔˆ¸ƒÀœ÷‹().;,\\-_\\'¥\\#/][0-9a-zA-Z·ÈÌÛ˙¡…Õ”⁄—Ò‹¸‰ÎÔˆ¸ƒÀœ÷‹().;,\\-_\\'¥\\#/ ]{0,79}";
		//public static final String SOLO_NUMEROS							= "[0-9]+";
		public static final String RFC_MORAL								= "[a-zA-Z&]{3}[0-9]{2}((02)(0[1-9]|1[0-9]|2[0-9])|(0[469]|11)(0[1-9]|1[0-9]|2[0-9]|3[0])|(0[13578]|1[02])(0[1-9]|1[0-9]|2[0-9]|3[0-1]))[a-zA-Z,0-9]{3}";
		public static final String RFC_FISICA								= "^[a-zA-Z&]{4}[0-9]{2}((02)(0[1-9]|1[0-9]|2[0-9])|(0[469]|11)(0[1-9]|1[0-9]|2[0-9]|3[0])|(0[13578]|1[02])(0[1-9]|1[0-9]|2[0-9]|3[0-1]))([a-zA-Z,0-9]{3})?";
		public static final Namespace SAT_XMLNS_V3 = Namespace.getNamespace("http://www.sat.gob.mx/cfd/3");
		public static final String SAT_RECEPTOR = "Receptor";
		public static final String SAT_EMISOR = "Emisor";
		public static final String SAT_RFC = "rfc";
		public static final String SAT_NOMBRE = "nombre";
		public static final String SAT_NOMBRE_RECEPTOR = "nombreReceptor";
		public static final String SAT_TOTAL = "total";
		public static final String SAT_SUBTOTAL = "subTotal";
		public static final String SAT_COMPROBANTE = "Comprobante";
		public static final String SAT_FIEL = "noCertificado";
		public static final String SAT_FECHA = "fecha";
		public static final String SAT_FIELINICIO = "fechaFielInicio";
		public static final String SAT_FIELFIN = "fechaFielFin";
		public static final String SAT_UUID="UUID";
		public static final String SAT_IVA="IVA";
		public static final String SAT_IMPORTE="importe";
		public static final String SAT_IMPUESTO="impuesto";
		public static final String SAT_TIPO_COMPROBANTE = "tipoDeComprobante";
		public static final String SAT_FORMA_PAGO = "formaDePago"; 
		public static final String SAT_METODO_PAGO = "metodoDePago"; 
		public static final String SAT_FECHA_EXPEDICION = "fecha"; 
		public static final String SAT_FECHA_TIMBRADO = "FechaTimbrado";
		public static final String SAT_CONCEPTO = "Concepto";
		public static final String SAT_CONCEPTOS = "Conceptos";
		public static final String SAT_FOLIO = "folio";
		public static final String SAT_VALOR_UNITARIO ="valorUnitario";
		public static final String SAT_DESCRIPCION = "descripcion";
		public static final String SAT_UNIDAD = "unidad";
		public static final String SAT_CANTIDAD = "cantidad";
		public static final String SAT_TIMBRE_FISCAL = "TimbreFiscalDigital";
		public static final String SAT_IMPUESTOS = "Impuestos";
		public static final String SAT_TOTAL_IMPUESTOS_TRASLADADOS = "totalImpuestosTrasladados";
		public static final String SAT_TOTAL_IMPUESTOS_RETENIDOS = "totalImpuestosRetenidos";
		public static final String SAT_NOMINA = "Nomina";
		public static final String SAT_COMPLEMENTO = "Complemento";
		public static final String SAT_REGISTRO_PATRONAL = "RegistroPatronal";
		public static final String SAT_NUM_EMPLEADO = "NumEmpleado";
		public static final String SAT_CURP = "CURP";
		public static final String SAT_TIPO_REGIMEN = "TipoRegimen";
		public static final String SAT_NUM_SEGURIDAD_SOCIAL = "NumSeguridadSocial";
		public static final String SAT_FECHA_PAGO = "FechaPago";
		public static final String SAT_FECHA_INICIAL_PAGO  = "FechaInicialPago";
		public static final String SAT_FECHA_FINAL_PAGO = "FechaFinalPago";
		public static final String SAT_NUM_DIAS_PAGADOS = "NumDiasPagados";
		public static final String SAT_FECHA_INICIAL_LABORAL = "FechaInicioRelLaboral";
		public static final String SAT_ANTIGUEDAD = "Antiguedad";
		public static final String SAT_ANTIGUEDAD3 = "Antig¸edad";
		public static final String SAT_PUESTO = "Puesto";
		public static final String SAT_TIPO_CONTRATO = "TipoContrato";
		public static final String SAT_TIPO_JORNADA = "TipoJornada";
		public static final String SAT_PERIODICIDAD_PAGO = "PeriodicidadPago";
		public static final String SAT_SALARIO_DIARIO_INTEGRADO = "SalarioDiarioIntegrado";
		public static final String SAT_TOTAL_GRAVADO = "TotalGravado";
		public static final String SAT_TOTAL_EXENTO = "TotalExento";
		public static final String SAT_TIPO_PERCEPCION = "TipoPercepcion";
		public static final String SAT_TIPO_DEDUCCION = "TipoDeduccion";
		public static final String SAT_CLAVE = "Clave";
		public static final String SAT_IMPORTE_GRAVADO = "ImporteGravado";
		public static final String SAT_IMPORTE_EXENTO = "ImporteExento";
		public static final String SAT_PERCEPCIONES = "Percepciones";
		public static final String SAT_RETENCIONES = "Retenciones";
		public static final String SAT_RETENCION = "Retencion";
		public static final String SAT_DEDUCCIONES = "Deducciones";
		public static final String SAT_LUGAR_EXPEDICION = "LugarExpedicion";
		public static final String SAT_ = "";
		public static final String SIN_DATO = "SIN DATO DE ORIGEN";
		public static final String SAT_INE = "INE";
		public static final String SAT_TIPO_PROCESO = "TipoProceso";
		public static final String SAT_TIPO_COMITE = "TipoComite";
		public static final String SAT_ID_CONTABILIDAD = "IdContabilidad";
		public static final String SAT_AMBITO = "Ambito";
		public static final String SAT_CLAVE_ENTIDAD = "ClaveEntidad";
		public static final String SAT_CURP12 = "Curp";
		public static final String SAT_TOTAL_IMPUESTOS_RETENIDOS12 = "TotalImpuestosRetenidos";
		public static final String SAT_PERCEPCION = "Percepcion";
		public static final String SAT_SUBTOTAL3 = "SubTotal";
		public static final String SAT_TOTAL3 = "Total";
		public static final String SAT_METODO_PAGO3 = "MetodoPago";
		public static final String SAT_FORMA_PAGO3 = "FormaPago";
		public static final String SAT_TIPO_COMPROBANTE3 = "TipoDeComprobante";
		public static final String SAT_FECHA_EXPEDICION3 = "Fecha";
		public static final String SAT_RFC3 = "Rfc";
		public static final String SAT_CANTIDAD3 = "Cantidad";
		public static final String SAT_DESCRIPCION3 = "Descripcion";
		public static final String SAT_UNIDAD3 = "Unidad";
		public static final String SAT_FOLIO3 = "Folio";
		public static final String SAT_NOMBRE3 = "Nombre";
		public static final String SAT_VALOR_UNITARIO3 ="ValorUnitario";
		public static final String SAT_TOTAL_IMPUESTOS_TRASLADADOS3 = "TotalImpuestosTrasladados";
		public static final String SAT_TOTAL_IMPUESTOS_RETENIDOS3 = "TotalImpuestosRetenidos";
		public static final String SAT_IMPORTE3="Importe";
		public static final String SAT_IMPUESTO3="Impuesto";
		
		
		//Constantes reporte Evidencias
		public static final int VALOR_ = 0;
		public static final int VALOR_0 = 0;
		public static final int VALOR_1 = 1;
		public static final int VALOR_2 = 2;
		public static final int VALOR_3 = 3;
		public static final int VALOR_4 = 4;
		public static final int VALOR_5 = 5;
		public static final int VALOR_6 = 6;
		public static final int VALOR_7 = 7;
		public static final int VALOR_8 = 8;
		public static final int VALOR_9 = 9;
		public static final int VALOR_10 = 10;
		public static final int VALOR_11 = 11;
		public static final int VALOR_12 = 12;
		public static final int VALOR_13 = 13;
		public static final int VALOR_14 = 14;
		public static final int VALOR_15 = 15;
		public static final int VALOR_16 = 16;
		public static final int VALOR_17 = 17;
		public static final int VALOR_18 = 18;
		public static final int VALOR_19 = 19;
		public static final int VALOR_20 = 20;
		public static final int VALOR_21 = 21;
		public static final int VALOR_22 = 22;
		public static final int VALOR_23 = 23;
		public static final int VALOR_24 = 24;
		public static final int VALOR_25 = 25;
		public static final int VALOR_26 = 26;
		public static final int VALOR_27 = 27;
		public static final int VALOR_28 = 28;
		public static final int VALOR_29 = 29;
		public static final int VALOR_30 = 30;
		public static final int VALOR_31 = 31;
		public static final int VALOR_32 = 32;
		public static final int VALOR_33 = 33;
		public static final int VALOR_34 = 34;
		public static final int VALOR_35 = 35;
		public static final int VALOR_36 = 36;
		public static final int VALOR_37 = 37;
		public static final int VALOR_38 = 38;
		public static final int VALOR_39 = 39;
		public static final int VALOR_40 = 40;
		public static final int VALOR_41 = 41;
		public static final int VALOR_42 = 42;
		public static final int VALOR_43 = 43;
		public static final int VALOR_44 = 44;
		public static final int VALOR_45 = 45;
		public static final int VALOR_46 = 46;
		public static final int VALOR_47 = 47;
		public static final int VALOR_48 = 48;
		public static final int VALOR_49 = 49;
		public static final int VALOR_50 = 50;
		public static final int VALOR_51 = 51;
		public static final int VALOR_52 = 52;
		public static final int VALOR_53 = 53;
		public static final int VALOR_54 = 54;
		public static final int VALOR_55 = 55;
		public static final int VALOR_56 = 56;
		public static final int VALOR_57 = 57;
		public static final int VALOR_58 = 58;
		public static final int VALOR_59 = 59;
		public static final int VALOR_60 = 60;
		public static final int VALOR_61 = 61;
		public static final int VALOR_62 = 62;
		public static final int VALOR_63 = 63;
		public static final int VALOR_64 = 64;
		public static final int VALOR_65 = 65;
		public static final int VALOR_66 = 66;
		public static final int VALOR_67 = 67;
		public static final int VALOR_68 = 68;
		public static final int VALOR_69 = 69;
		public static final int VALOR_70 = 70;
		public static final int VALOR_71 = 71;
		public static final int VALOR_72 = 72;
		public static final int VALOR_73 = 73;
		public static final int VALOR_74 = 74;
		public static final int VALOR_75 = 75;
		public static final int VALOR_76 = 76;
		public static final int VALOR_77 = 77;
		public static final int VALOR_78 = 78;
		public static final int VALOR_79 = 79;
		public static final int VALOR_80 = 80;
		public static final int VALOR_81 = 81;
		public static final int VALOR_82 = 82;
		public static final int VALOR_83 = 83;
		public static final int VALOR_84 = 84;
		public static final int VALOR_85 = 85;
		public static final int VALOR_86 = 86;
		public static final int VALOR_87 = 87;
		public static final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		public static final SimpleDateFormat FORMATO_FECHA_GUIONES = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		public static final String FECHA_CREACION = "Fecha de GeneraciÛn de Vista XML:";
		public static final String FECHA_ALTA_EV = "Fecha de Alta de la Evidencia:";
		public static final String FECHA_CREACION_XML =  "Fecha y hora de GeneraciÛn de Reporte:";
		public static final String USUARIO = "Usuario que genero la descarga:";
		public static final String MENSAJE_EV = "La presente informaciÛn, representa una ayuda para visualizar los principales datos del archivo XML, sin embargo no es la totalidad de la informaciÛn del mismo";
		public static final String CEDULA_PRORRATEO = "CEDULA DE PRORRATEO";
		public static final String MENSAJE_SIN_REGISTROS = "SIN REGISTROS";
		public static final String FOLIO_FISCAL = "Folio fiscal:";
		public static final String FOLIO = "Folio:";
		public static final String FECHA_CERTIFICACION = "Fecha CertificaciÛn:";
		public static final String FECHA_EXPEDICION = "Fecha ExpediciÛn:";
		public static final String LUGAR_EXPEDICION = "Lugar ExpediciÛn:";
		public static final String NOMBRE_EM = "Nombre Emisor:";
		public static final String NOMBRE_RE = "Nombre Receptor:";
		public static final String RFC_EM = "RFC Emisor:";
		public static final String RFC_RE = "RFC Receptor:";
		public static final String TIPO_COMPROBANTE = "Tipo de Comprobante:";
		public static final String METODO_PAGO = "MÈtodo de Pago:";
		public static final String FORMA_PAGO = "Forma de Pago:";
		public static final String SUBTOTAL = "Subtotal:";
		public static final String IMPUESTOS_RETENIDOS= "Impuestos Retenidos:";
		public static final String IMPUESTOS_TRASLADADOS= "Impuestos Trasladados:";
		public static final String TOTAL= "Total:";
		public static final String CONCEPTOS = "CONCEPTOS";
		public static final String DESCRIPCION= "DescripciÛn:";
		public static final String VALOR_UNITARIO= "Valor Unitario:";
		public static final String UNIDAD= "Unidad:";
		public static final String CANTIDAD= "Cantidad:";
		public static final String REGISTRO= "Registro Patronal:";
		public static final String NUMERO_EMPLEADO= "N˙mero de Empleado:";
		public static final String CURP= "CURP:";
		public static final String REGIMEN= "Tipo de RÈgimen:";
		public static final String NSS= "N˙mero de Seguridad Social:";
		public static final String FECHA_PAGO= "Fecha de Pago:";
		public static final String FECHA_INICIAL= "Fecha Inicial de Pago:";
		public static final String FECHA_FINAL= "Fecha Final de Pago:";
		public static final String DIAS_PAGADOS= "N˙mero de DÌas Pagados:";
		public static final String FECHA_INICIO_LABORAL= "Fecha de Inicio de RelaciÛn Laboral:";
		public static final String ANTIGUEDAD= "Antig¸edad:";
		public static final String PUESTO= "Puesto:";
		public static final String TIPO_CONTRTO= "Tipo de Contrato:";
		public static final String TIPO_JORNADA= "Tipo de Jornada:";
		public static final String PERIDIOCIDAD_PAGO= "Peridiocidad de Pago:";
		public static final String SALARIO_DIARIO= "Salario Diario Integrado:";
		public static final String TOTAL_GRAVADO_PERCEPCION= "Total Gravado PercepciÛn:";
		public static final String TOTAL_EXENTO_PERCEPCION= "Total Exento PercepciÛn:";
		public static final String TOTAL_GRAVADO_DEDUCCION= "Total Gravado DeducciÛn:";
		public static final String TOTAL_EXENTO_DEDUCCION= "Total Exento DeducciÛn:";
		public static final String TIPO_PERCEPCION= "Tipo PercepciÛn:";
		public static final String TIPO_DEDUCCION= "Tipo DeducciÛn:";
		public static final String CLAVE_PERCEPCION= "Clave PercepciÛn:";
		public static final String CLAVE_DEDUCCION= "Clave DeducciÛn:";
		public static final String CONCEPTO_PERCEPCION= "Concepto PercepciÛn:";
		public static final String CONCEPTO_DEDUCCION= "Concepto DeducciÛn:";
		public static final String IMPORTE_GRAVADO_PERCEPCION= "Importe Gravado PercepciÛn:";
		public static final String IMPORTE_EXENTO_PERCEPCION= "Importe Exento PercepciÛn:";
		public static final String IMPORTE_GRAVADO_DEDUCCION= "Importe Gravado DeducciÛn:";
		public static final String IMPORTE_EXENTO_DEDUCCION= "Importe Exento DeducciÛn:";
		public static final String COMPLEMENTO_INE = "COMPLEMENTO INE";
		public static final String TIPO_PROCESO = "Tipo proceso:";
		public static final String TIPO_COMITE = "Tipo comitÈ:";
		public static final String CONTABILIDAD = "Contabilidad:";
		public static final String CLAVE_ENTIDAD = "Clave entidad:";
		public static final String DATO_AMBITO = "¡mbito:";
		public static final String CONTABILIDADES = "Contabilidades:";
		public static final String SUJETO_OBLIGADO = "SUJETO OBLIGADO";
		public static final String PROCESO = "PROCESO";
		public static final String AMBITO = "AMBITO";
		public static final String COMITE = "COMITE";
		public static final String TIPO_CANDIDATURA = "TIPO CANDIDATURA";
		public static final String ENTIDAD= "ENTIDAD";
		public static final String ENTIDAD_CIRCUNSCRIPCION= "ENTIDAD/CIRCUNSCRIPCION";
		public static final String CIRCUNSCRIPCION = "CIRCUNSCRIPCION";
		public static final String DISTRITO= "DISTRITO";
		public static final String MUNICIPIO_DELEGACION = "MUNICIPIO/DELEGACION";
		public static final String CANDIDATO = "CANDIDATO";
		public static final String NUMERO_POLIZA = "NUMERO POLIZA";
		public static final String FECHA_OPERACION = "FECHA DE OPERACION";
		public static final String FECHA_REGISTRO = "FECHA DE REGISTRO";
		public static final String ETAPA = "ETAPA";
		public static final String PERIODO = "PERIODO";
		public static final String SUBTIPO_POLIZA = "SUBTIPO DE POLIZA";
		public static final String HEADER_FOLIO_FISCAL = "FOLIO FISCAL";
		public static final String HEADER_FOLIO = "FOLIO";
		public static final String HEADER_FECHA_CERTIFICACION = "FECHA CERTIFICACION";
		public static final String HEADER_FECHA_EXPEDICION = "FECHA EXPEDICION";
		public static final String HEADER_LUGAR_EXPEDICION = "LUGAR EXPEDICION";
		public static final String HEADER_NOMBRE_EM = "NOMBRE EMISOR";
		public static final String HEADER_NOMBRE_RE = "NOMBRE RECEPTOR";
		public static final String HEADER_RFC_EM = "RFC EMISOR";
		public static final String HEADER_RFC_RE = "RFC RECEPTOR";
		public static final String HEADER_TIPO_COMPROBANTE = "TIPO DE COMPROBANTE";
		public static final String HEADER_METODO_PAGO = "METODO DE PAGO";
		public static final String HEADER_FORMA_PAGO = "FORMA DE PAGO";
		public static final String HEADER_SUBTOTAL = "SUBTOTAL";
		public static final String HEADER_IMPUESTOS_RETENIDOS= "IMPUESTOS RETENIDOS";
		public static final String HEADER_IMPUESTOS_TRASLADADOS= "IMPUESTOS TRASLADADOS";
		public static final String HEADER_TOTAL= "TOTAL";
		public static final String HEADER_CONCEPTOS = "CONCEPTOS";
		public static final String HEADER_DESCRIPCION= "DESCRIPCION";
		public static final String HEADER_VALOR_UNITARIO= "VALOR UNITARIO";
		public static final String HEADER_UNIDAD= "UNIDAD";
		public static final String HEADER_CANTIDAD= "CANTIDAD";
		public static final String HEADER_REGISTRO= "REGISTRO PATRONAL";
		public static final String HEADER_NUMERO_EMPLEADO= "NUMERO DE EMPLEADO";
		public static final String HEADER_CURP= "CURP";
		public static final String HEADER_REGIMEN= "TIPO DE REGIMEN";
		public static final String HEADER_NSS= "NUMERO DE SEGURIDAD SOCIAL";
		public static final String HEADER_FECHA_PAGO= "FECHA DE PAGO";
		public static final String HEADER_FECHA_INICIAL= "FECHA INICIAL DE PAGO";
		public static final String HEADER_FECHA_FINAL= "FECHA FINAL DE PAGO";
		public static final String HEADER_DIAS_PAGADOS= "NUMERO DE DIAS PAGADOS";
		public static final String HEADER_FECHA_INICIO_LABORAL= "FECHA DE INICIO DE RELACION LABORAL";
		public static final String HEADER_ANTIGUEDAD= "ANTIG‹EDAD";
		public static final String HEADER_PUESTO= "PUESTO";
		public static final String HEADER_TIPO_CONTRTO= "TIPO DE CONTRATO";
		public static final String HEADER_TIPO_JORNADA= "TIPO DE JORNADA";
		public static final String HEADER_PERIDIOCIDAD_PAGO= "PERIODICIDAD DE PAGO";
		public static final String HEADER_SALARIO_DIARIO= "SALARIO DIARIO INTEGRADO";
		public static final String HEADER_TOTAL_GRAVADO_PERCEPCION= "TOTAL GRAVADO PERCEPCION";
		public static final String HEADER_TOTAL_EXENTO_PERCEPCION= "TOTAL EXENTO PERCEPCION";
		public static final String HEADER_TOTAL_GRAVADO_DEDUCCION= "TOTAL GRAVADO DEDUCCION";
		public static final String HEADER_TOTAL_EXENTO_DEDUCCION= "TOTAL EXENTO DEDUCCION";
		public static final String HEADER_TIPO_PERCEPCION= "TIPO PERCEPCION";
		public static final String HEADER_TIPO_DEDUCCION= "TIPO DEDUCCION";
		public static final String HEADER_CLAVE_PERCEPCION= "CLAVE PERCEPCION";
		public static final String HEADER_CLAVE_DEDUCCION= "CLAVE DEDUCCION";
		public static final String HEADER_CONCEPTO_PERCEPCION= "CONCEPTO PERCEPCION";
		public static final String HEADER_CONCEPTO_DEDUCCION= "CONCEPTO DEDUCCION";
		public static final String HEADER_IMPORTE_GRAVADO_PERCEPCION= "IMPORTE GRAVADO PERCEPCION";
		public static final String HEADER_IMPORTE_EXENTO_PERCEPCION= "IMPORTE EXENTO PERCEPCION";
		public static final String HEADER_IMPORTE_GRAVADO_DEDUCCION= "IMPORTE GRAVADO DEDUCCION";
		public static final String HEADER_IMPORTE_EXENTO_DEDUCCION= "IMPORTE EXENTO DEDUCCION";
		public static final String HEADER_TIPO_EVIDENCIA = "TIPO DE EVIDENCIA";
		public static final String HEADER_ESTATUS_EVIDENCIA = "ESTATUS DE EVIDENCIA";
		public static final String HEADER_FECHA_SIN_EFECTO ="FECHA EN QUE SE DEJO SIN EFECTO";
		public static final String HEADER_CONTABILIDAD = "CONTABILIDAD";
		public static final String HEADER_TIPO_PROCESO = "TIPO DE PROCESO";
		public static final String HEADER_TIPO_COMITE = "TIPO COMITE";
		public static final String HEADER_CLAVE_ENTIDAD = "CLAVE ENTIDAD";
		public static final String HEADER_ = "";
		public static final String ENCABEZADO_REPORTE_XML = "REPORTE XML";
		public static final String ENCABEZADO_REPORTE_XML_NOMINA = "REPORTE XML NOMINA";
		public static final String ENCABEZADO_REPORTE_EVIDENCIAS = "REPORTE EVIDENCIAS";
		public static final String ENCABEZADO_REPORTE_XMLINE = "REPORTE XML COMPLEMENTO INE";
		
	   public static final long[] DEPRECIACION_AMORTIZACION = {1202000000L, 1204000000L};
	   public static final long[] ACTIVIDADES_OPERACION = {1104000000L, 1105000000L, 1106000000L, 1107000000L, 1203000000L, 1205000000L, 1207000000L, 2101000000L, 2102000000L, 2203000000L, 2103000000L};
	   public static final long[] ACTIVIDADES_INVERSION = {1103000000L, 1201000000L};
	   public static final long[] ACTIVIDADES_FINANCIAMIENTO = {2201000000L, 2202000000L};
	   public static final long[] CAJA_BANCO= {1101000000L, 1102000000L};
	   public static final long[] SUPERAVIT_DEFICIT= {3101000000L};
	   public static final long[][] CUENTAS_SECCION_FE = {DEPRECIACION_AMORTIZACION,ACTIVIDADES_OPERACION,ACTIVIDADES_INVERSION, ACTIVIDADES_FINANCIAMIENTO, CAJA_BANCO,SUPERAVIT_DEFICIT};
	   public static final String[] ENCABEZADOS_ACTIVIDADES_FE= {"INVERSION","OPERACI”N","INVERSION","FINANCIAMIENTO"};  
	   String[] MESES = {"", 
				  "ENERO", 		"FEBRERO", 		"MARZO", 
				  "ABRIL", 		"MAYO", 		"JUNIO", 
				  "JULIO", 		"AGOSTO", 		"SEPTIEMBRE", 
				  "OCTUBRE", 	"NOVIEMBRE", 	"DICIEMBRE"};
		
}
