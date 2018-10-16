/**
 * @(#)DTOCasillasDiputado.java 19/O4/2014
 *
 * Copyright (C) 2014 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.sif.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import mx.ine.sif.dto.DTOUsuarioLogin;

import mx.org.ine.servicios.utils.ApplicationContextUtils;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import org.apache.poi.util.IOUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.primefaces.util.Constants;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * Clase que contiene metodos genericos para todo el sistema
 * 
 * @author IFE
 *
 */
public class Utils implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2177722327214844044L;
	
	private static final String caracterEspecial = "¡·…ÈÕÌ”Û⁄˙";
	private static final String caracter = "AaEeIiOoUu";
	
	private static final Logger logger = Logger.getLogger(Utils.class);

	public static final String glusterRaiz= System.getProperty("glusterFS");

	/**
	 * Extrae una variable de properties
	 *
	 * @param llave : identificador del valor a extraer
	 * @return String : mensaje en forma de cadena
	 *
	 * @author Gustavo M. L√≥pez Dom√≠nguez
	 * @replico Roberto Shir√°sago Dom√≠nguez
	 */
	public static String mensajeProperties(String llave) {

		ResourceBundleMessageSource messageSource = ((ResourceBundleMessageSource) ApplicationContextUtils.getApplicationContext().getBean("messageSource"));

		return messageSource.getMessage(llave, null, null);
	}
	
	/**
	 * M√©todo para convertir un <code>Collection</code> a un <code>Map</code>.
	 * 
	 * @param metodoGetid M√©todo para obtener la llave de los objetos que se utiliza al insertarlos en el <code>Map</code>.
	 * @param listaObj Colecci√≥n de objetos que ser√° convertido a <code>Map</code>.
	 * @return El <code>Map</code> que contiene todos los objetos de la colecci√≥n.
	 * @throws Exception Se env√≠a un error en caso de que la clase de los Objetos no contenga el m√©todo especificado.
	 * 
	 * @author Roberto Shir√°sago Dom√≠nguez
	 */
	@SuppressWarnings("unchecked")
	public static <K,V> Map<K, V> collectionToMap(String metodoGetid, Collection<V> listaObj) throws Exception {

		Map<K,V> hashMapObj = new HashMap<K,V>();
		K key;
		for(V objeto: listaObj) {
			key = (K)(objeto.getClass().getMethod(metodoGetid, new Class[]{}).invoke(objeto, new Object[]{}));
			hashMapObj.put(key, objeto);
		}
		return hashMapObj;
	}
	
	/**
	 * M√©todo para agregar ceros a la izquierda de un n√∫mero
	 * @param numero es el n√∫mero a completar con ceros a la izquierda
	 * @param cantidad de d√≠gitos al que se quiere acompletar el n√∫mero
	 * @return Se regresa la representaci√≥n del n√∫mero en un tipo String
	 */
	public static String agregarCeros(Integer numero, int largo){
		
		StringBuffer numeroCompleto = new StringBuffer();
		Integer cantidad;
		String numeroString;
		
		if(numero != null){
			
			numeroString = numero.toString();
			cantidad = largo - numeroString.length();
				
			if (cantidad >= 1){
				for(int i = 0; i < cantidad; i++){
					numeroCompleto.append("0");	
				}				
			}
			numeroCompleto.append(numero);			
			
			return numeroCompleto.toString() ;
		}else{
			return null;
		}
		
		
	}
	
	public static Integer quitarCerosIzquierda(String numero){
		
		Integer cadenaResultadoInt; 
		
		if(numero != null){
			cadenaResultadoInt = Integer.parseInt(numero);
			return cadenaResultadoInt;
		}else{
			return null;
		}
	}
	
	
	
	/**
	 * M√©todo que obtiene la descripci√≥n del tipo de elecci√≥n
	 * 
	 * @param Integer: identificador del tipo de elecci√≥n
	 * @return String: Descripci√≥n del tipo de elecci√≥n
	 * 
  	 * @author Israel V√°zquez jim√©nez
	 */
	public static String tipoEleccionDescripcion(Integer identificador){
		
		/*Instancias */
		String descripcion = new String();
		descripcion="";
		
		//Se obtiene descripci√≥n
		if(identificador != null){
		  descripcion =	identificador == 1 ? mensajeProperties("etiqueta_presidente") : (identificador == 2 ? mensajeProperties("etiqueta_diputados") : (identificador == 3 ? Utils.mensajeProperties("etiqueta_senadores"):""));
		}
		
		return descripcion;
	}
	
	
	
	/**
	 * M√©todo que cambia el valor null de un campo por vacio para evitar aparezaca "null", al pintar el valor de dicho campo  
	 * 
	 * @param String campo
	 * @return String: valor del campo
	 * 
  	 * @author Israel V√°zquez jim√©nez
	 */
	public static String nullToVacio(String campo){
		
		//Se realiza la validaci√≥n
		String valorCampo= campo==null?"":campo;

		return valorCampo;
	}
	
	
	/**
	 * M√©todo que remplaza los caracteres Unicode para las vocales acentuadas (√°,√©,√≠,√≥,√∫,√Å,√â,√ç,√ì,√ö), as√≠ como las letras (√±, √ë)  y (√º,√ú)
	 * 
	 * @param String cadena
	 * @return String: cadena formateada.
	 * 
  	 * @author Israel V√°zquez jim√©nez
	 */
	public static String remplazaCaractereUnicode(String cadena){
		
		String cadenaFormateada = new String();

		//Se remplazan caracteres Unicode por su correspondiente vocal
		cadenaFormateada = cadena.replaceAll("\u00E1", "&aacute;").replaceAll("\u00E9", "&eacute;").replaceAll("\u00ED", "&iacute;").replaceAll("\u00F3", "&oacute;").replaceAll("\u00FA", "&uacute;")
				                 .replaceAll("\u00C1", "&Aacute;").replaceAll("\u00C9", "&Eacute;").replaceAll("\u00CD", "&Iacute;").replaceAll("\u00D3", "&Oacute;").replaceAll("\u00DA", "&Uacute;")
				                 .replaceAll("\u00F1", "&ntilde;").replaceAll("\u00D1", "&Ntilde;").replaceAll("\u00FC", "&uuml;").replaceAll("\u00DC", "&Uuml;");
		return cadenaFormateada;
	}
	
	/**
	 * Mensaje Exitoso Para Primefacez
	 * @param mensaje
	 * @return
	 */
	public static final FacesMessage mensajeExitoFaces(String mensaje){
		return new FacesMessage(FacesMessage.SEVERITY_INFO, mensajeProperties("etiqueta_exitoso"), " " + mensaje);
	}
	
	/**
	 * Mensaje Error Para Primefacez
	 * @param mensaje
	 * @return
	 */
	public static final FacesMessage mensajeErrorFaces(String mensaje){
		return new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeProperties("etiqueta_error"), mensaje);
	}
	
	/**
	 * Mensaje Error de vista fondo rojo Para Primefacez
	 * @param mensaje
	 * @return
	 */
	public static final FacesMessage mensajeFatalFaces(String mensaje){
		return new FacesMessage(FacesMessage.SEVERITY_FATAL, mensajeProperties("etiqueta_error"), mensaje);
	}	
	
	/**
	 * Mensaje Advertencia Para Primefacez
	 * @param mensaje
	 * @return
	 */
	public static final FacesMessage mensajeAdvertenciaFaces(String mensaje){
		return new FacesMessage(FacesMessage.SEVERITY_WARN, mensajeProperties("etiqueta_advertencia"), mensaje);
	}
	
	/**
	 * encriptacion de cadena en sha1
	 * @param input
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        } 
        return sb.toString();
	}
	
	public static String sha1sum(InputStream is) throws Exception {
		int bufferSize = 1024;
		byte[] buffer = new byte[bufferSize];
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		int apuntadorLectura = 0;
		while ((apuntadorLectura = is.read(buffer)) != -1) {
			digest.update(buffer, 0, apuntadorLectura);
		}
		byte[] bytesDigest = digest.digest();
		return Hex.encodeHexString(bytesDigest);
	}
	
	public static final String jndiSif2JasperConex = "java:jboss/sif2jndi";
	
	
	public static final String gluster = System.getProperty("glusterFS");
	public static final String rutaGluster = gluster + File.separator + "sifv2" +File.separator + "sif_ord" + File.separator;
	public static final String rutaGluster2018 = gluster + File.separator +"2018"+ File.separator +"sifv2" +File.separator + "sif_ord" + File.separator; 
	public static final String glusterTemp = rutaGluster + "tmp";
	public static final String rutaGlusterDocumentacion = gluster + File.separator + "informesORD" + File.separator;
	public static final String rutaGlusterConciliaciones = rutaGluster + "conciliaciones" + File.separator;
	public static final String rutaGlusterConciliacionesInversiones = rutaGluster + "conciliacionesInversiones" + File.separator;
	public static final String rutaPlantillasReportes = rutaGluster + "utils" + File.separator +"reportes" + File.separator + "plantillas" + File.separator;
	public static final String rutaPlantillasReportesQuerys =  rutaGluster +  "utils" + File.separator +"reportes" + File.separator + "plantillas" + File.separator + "querys" + File.separator;
	public static final String rutaGlusterPolizas = rutaGluster + File.separator + "polizas" + File.separator;
	public static final String rutaGlusterPolizas2018 = rutaGluster2018 + File.separator + "polizas" + File.separator;
	public static final String rutaGlusterPlantillaPoliza = rutaGluster + File.separator + "polizas" + File.separator + "Plantillas" + File.separator;
	public static final String rutaGlusterRepositorioXML = rutaGluster + "repositorio" + File.separator;

	public static final String rutaGlusterConciliaciones2018 = rutaGluster2018 + "conciliaciones" + File.separator;
	public static final String rutaGlusterConciliacionesInversiones2018 = rutaGluster2018 + "conciliacionesInversiones" + File.separator;
	
	/**
	 * MÈtodo que valida que una cadena solo contenga letras
	 * 
	 * @param String cadena
	 * @return boolean
	 * 
  	 * @author Ilse Landa
	 */
    public static boolean validarLetras(String entrada){
        Pattern patron = Pattern.compile("[^A-Za-z]");
        Matcher encaja = patron.matcher(entrada);
        if(!encaja.find())
            return true;
        else
            return false;
    }
	/**
	 * MÈtodo que valida que una cadena solo contenga letras y espacio
	 * 
	 * @param String cadena
	 * @return boolean
	 * 
  	 * @author Ilse Landa
	 */
    public static boolean validarLetrasSpace(String entrada){
        Pattern patron = Pattern.compile("[^A-Za-z\\s]");
        Matcher encaja = patron.matcher(entrada);
        if(!encaja.find())
            return true;
        else
            return false;
    }    
    
	/**
	 * MÈtodo que valida que una cadena solo contenga letras
	 * 
	 * @param String cadena
	 * @return boolean
	 * 
  	 * @author Ilse Landa
	 */
    public static boolean validarNumeros(String entrada){
        Pattern patron = Pattern.compile("[^0-9]");
        Matcher encaja = patron.matcher(entrada);
        if(!encaja.find())
            return true;
        else
            return false;
    }
    
    /**
	 * MÈtodo que valida n˙meros decimales
	 * 
	 * @param String cadena
	 * @return boolean
	 * 
  	 * @author Ilse Landa
	 */
    public static boolean validarNumerosDecimales(String entrada){
        Pattern patron = Pattern.compile ("^\\d+(\\.\\d+)?");
        Matcher encaja = patron.matcher(entrada);
        if(encaja.matches()){
        	return true;
        }else{
            return false;
        }  
    }
    
	/**
	 * MÈtodo que valida que una cadena solo contenga letras y n˙meros
	 * 
	 * @param String cadena
	 * @return boolean
	 * 
  	 * @author Ilse Landa
	 */
    public static boolean validarAlfanumerico(String entrada){
        Pattern patron = Pattern.compile("[^A-Za-z0-9]");
        Matcher encaja = patron.matcher(entrada);
        if(!encaja.find())
            return true;
        else
            return false;
    }
    
	/**
	 * MÈtodo que valida correos
	 * 
	 * @param String cadena
	 * @return boolean
	 * 
  	 * @author Ilse Landa
	 */
    public static boolean validarCorreo(String entrada){
        Pattern patron = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher encaja = patron.matcher(entrada);
        if(!encaja.find())
            return false;
        else
            return true;
    }    
    
	/**
	 * MÈtodo que tiene la funciÛn de validar el curp
	 * 
	 * @param String cadena
	 * @return boolean
	 * 
  	 * @author Ilse Landa
	 */
    public static boolean validarCurp(String curp){
	    curp=curp.toUpperCase().trim();
	    return curp.matches("[A-Z][A,E,I,O,U,X][A-Z]{2}[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[M,H][A-Z]{2}[B,C,D,F,G,H,J,K,L,M,N,P,Q,R,S,T,V,W,X,Y,Z]{3}[0-9,A-Z][0-9]");
    }

    /**
	 * MÈtodo que tiene la funciÛn de validar el n˙mero de credencialn (Nota falta validar)
	 * 
	 * @param String cadena
	 * @return boolean
	 * 
  	 * @author Ilse Landa
	 */
    public static boolean validarNumCreden(String numCrede){
	    numCrede=numCrede.toUpperCase().trim();
	    return numCrede.matches("[A-Za-z]{6}[0-9]{8}[H,M][0-9]{3}");
    }

    /**
	 * MÈtodo que tiene la funciÛn de validar el rfc
	 * 
	 * @param String cadena
	 * @return boolean
	 * 
  	 * @author Ilse Landa
	 */
    public static boolean validarRfc(String rfc){
    	rfc=rfc.toUpperCase().trim();
    	return rfc.toUpperCase().matches("[A-Z&][A,E,I,O,U,X,&][A-Z,&]{2}[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[A-Z0-9]{3}");
    }

     
    /**
	 * MÈtodo para validar si la fecha es correcta
	 * 
	 * @param String cadena
	 * @return boolean
	 * 
  	 * @author Ilse Landa
	 */
    public static boolean validarFecha(String fechax) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = formatoFecha.parse(fechax);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public static boolean esCadenaSimpleEvidencias(String cadena) {
		final String patron = "([_\\w\\s\\.\\-$%\\+])+";
		Pattern pat = Pattern.compile(patron);
		Matcher mat = pat.matcher(cadena);
		return mat.matches(); 
	}
	
	public static String UpperString(String cadena){
		cadena = cadena.replaceAll("[·|¡]", "A").replaceAll("[È|…]", "E").replaceAll("[Ì|Õ]", "I")
				.replaceAll("[Û|”]", "O").replaceAll("[˙|⁄]", "U");
		String salida = cadena.toUpperCase();		
		return salida;
	}

	public static Integer buscaPermisoProcesosEstapa(
			Collection<GrantedAuthority> authorities) {
		for(GrantedAuthority ga : authorities){
			if("ROLE_SIF.AUDITOR.OC".equals(ga.getAuthority())){
				return 2;
			}
			if("ROLE_SIF.SUPERVISORFISCALIZACION.OC".equals(ga.getAuthority())){
				return 2;
			}
		}
		return 1;
	}

	public static String buscaNombreMes(Integer mes) {		
		if(mes == 1){
			return "ENERO";
		}
		if(mes == 2){
			return "FEBRERO";
		}
		if(mes == 3){
			return "MARZO";
		}
		if(mes == 4){
			return "ABRIL";
		}
		if(mes == 5){
			return "MAYO";
		}
		if(mes == 6){
			return "JUNIO";
		}
		if(mes == 7){
			return "JULIO";
		}
		if(mes == 8){
			return "AGOSTO";
		}
		if(mes == 9){
			return "SEPTIEMBRE";
		}
		if(mes == 10){
			return "OCTUBRE";
		}
		if(mes == 11){
			return "NOVIEMBRE";
		}
		if(mes == 12){
			return "DICIEMBRE";
		}if(mes == 13){
			return "CIERRE";
		}
		return null;
	}

	public static Calendar formaCalendar(Integer anio, Integer mes, Integer dia, Integer hora,
			Integer minuto, Integer segundo, Integer miliSegundo) {
		Calendar formaFecha = new GregorianCalendar();		
    	formaFecha.set(Calendar.YEAR, anio);
    	formaFecha.set(Calendar.MONTH, mes);
    	formaFecha.set(Calendar.DAY_OF_MONTH, dia);
    	formaFecha.set(Calendar.HOUR, hora);
    	formaFecha.set(Calendar.MINUTE, minuto);
    	formaFecha.set(Calendar.SECOND, segundo);
    	formaFecha.set(Calendar.MILLISECOND, miliSegundo);
    	return formaFecha;
	}

	public static String regresaFormatoTextoFecha(Date fechaHoraCreacion,
			Boolean tambienHora) {
		SimpleDateFormat sfd = null;
		if(tambienHora){
			sfd = new SimpleDateFormat("dd/MM/yyyy HH:mm");		
		}
		else{
			sfd = new SimpleDateFormat("dd/MM/yyyy");
		}
		return sfd.format(fechaHoraCreacion);
	}
	
	public static String validarAcentos(String cadena) {
		if (cadena == null) {
			return null;
		}

		// Convertir todos los datos registrados en mayuscula
		cadena = cadena.toUpperCase();
		char[] array = cadena.toCharArray();
		for (int index = 0; index < array.length; index++) {
			int pos = caracterEspecial.indexOf(array[index]);
			if (pos > -1) {
				array[index] = caracter.charAt(pos);
			}
		}
		return new String(array);
	}

	/**
	 * MÈtodo para recortar un string.
	 * @param valor
	 * @param i
	 * @return
	 */
	public static String recortarString(String valor, int cuantasLetras) {
		
		if(valor==null || valor.isEmpty()){
			return valor;
		}
		
		if(cuantasLetras>0){
			return valor.substring(0, cuantasLetras);
		}
		else{
			return valor;
		}
	
	}

	public static String agregaAstericoExtensiones(
			String regresaExtensionesValidas) {
		
		if("SIN EXTENSIONES".trim().equals(regresaExtensionesValidas)){
			regresaExtensionesValidas = "";
		}
		String cambio="";
		String[] ord = regresaExtensionesValidas.split(",");
		Arrays.sort(ord, String.CASE_INSENSITIVE_ORDER);
		if(regresaExtensionesValidas.length()>1){
			for(String aux: ord){
				if(cambio.length()>4){
					cambio=cambio+", *."+ aux.toLowerCase();
				}else{
					cambio=cambio+" *."+ aux.toLowerCase();
				}
			}
		 }
		else{
			cambio = "SIN EXTENSIONES";
		}
		return cambio;
	}

	public static String setFormatoDecimal(Double valor) {
		
		try {
			DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
			simbolos.setDecimalSeparator('.');
			simbolos.setCurrencySymbol("$");
			simbolos.setGroupingSeparator(',');

			DecimalFormat formateador = new DecimalFormat("$ ###,###,###,###,###,##0.00",DecimalFormatSymbols.getInstance(Locale.ENGLISH));
			return formateador.format(valor);
		} catch (Exception e) {			
			return "";
		}
	}

	public static String regresaPorcentajeEvidencia(Double tamanioEvidencia,
			Double tamanoTotal) {
		int porciento;
		try {
			
			double porcientoD = (tamanioEvidencia * 100 /tamanoTotal);
			
			porciento = (int)porcientoD;
			if(porcientoD>0 && porcientoD<1){
				porciento = 1;
			}
			if(porcientoD>99.95){
				porciento = 100;
			}
			return String.valueOf(porciento) + " %";
		} catch (Exception e) {
			return "";
		}

	}

	public static Boolean buscaPermisoPolizasPre(
			Collection<GrantedAuthority> authorities) {
		for(GrantedAuthority ga : authorities){			
			if("ROLE_SIF.AUDITOR.OC".equals(ga.getAuthority())){
				return true;
			}
			if("ROLE_SIF.SUPERVISORFISCALIZACION.OC".equals(ga.getAuthority())){
				return true;
			}
			if("ROLE_SIF.INE_ADMIN.OC".equals(ga.getAuthority())){
				return true;
			}
		}
		return false;
	}

	public static String estatusPreregistro(Integer estatus) {
		if(estatus == 1){
			return "PREREGISTRO";
		}
		return "SIN EFECTO";
	}

	public static String regresaOrigen(Integer modulo) {
		if(modulo == 9){
			return Utils.mensajeProperties("etiqueta_poliza_carga_lotes");
		}
		if(modulo == 8){
			return Utils.mensajeProperties("etiqueta_poliza_captura_una_una");
		}if(modulo == 44){
			return Utils.mensajeProperties("etiqueta_poliza_cierre");
		}
		return "";
	}

	public static Double truncateDoubleDecimal(Double cargo, Integer decimales) {
		DecimalFormat formateador = new DecimalFormat("#.##",DecimalFormatSymbols.getInstance(Locale.ENGLISH));
		formateador.setRoundingMode(RoundingMode.DOWN);
		return new Double(formateador.format(cargo));
	}
	

	public static String traeMesAbreviatura(Integer mes) {
		if(mes == 1){
			return "ENE";
		}
		if(mes == 2){
			return "FEB";
		}
		if(mes == 3){
			return "MAR";
		}
		if(mes == 4){
			return "ABR";
		}
		if(mes == 5){
			return "MAY";
		}
		if(mes == 6){
			return "JUN";
		}
		if(mes == 7){
			return "JUL";
		}
		if(mes == 8){
			return "AGO";
		}
		if(mes == 9){
			return "SEP";
		}
		if(mes == 10){
			return "OCT";
		}
		if(mes == 11){
			return "NOV";
		}
		if(mes == 12){
			return "DIC";
		}
		return "";
	}

	public static Double agregaAlTotal(Double agregar, Double total) {		
		try {		
			DecimalFormat formateador = new DecimalFormat("0.00",DecimalFormatSymbols.getInstance(Locale.ENGLISH));
			formateador.setRoundingMode(RoundingMode.DOWN);
			
			String totalS = formateador.format(total);			
			String agregarS =  formateador.format(agregar);

			
			//logger.info(totalS);
			//logger.info(agregarS);
			
			totalS = totalS.replace(".", "");
			agregarS = agregarS.replace(".", "");
			
			Double tatalD = new Double(totalS);
			Double agregarD = new Double(agregarS);
			
			//logger.info("Total : "+ ((tatalD + agregarD)/100));
			
			
			return (tatalD + agregarD)/100;
		} catch (Exception e) {
			return null;
		}
	}

	public static Double quitarAlTotal(Double quitar, Double total) {
		try {
			
			DecimalFormat formateador = new DecimalFormat("0.00",DecimalFormatSymbols.getInstance(Locale.ENGLISH));
			formateador.setRoundingMode(RoundingMode.DOWN);
			
			String totalS = formateador.format(total);			
			String agregarS =  formateador.format(quitar);

			totalS = totalS.replace(".", "");
			agregarS = agregarS.replace(".", "");
			
			Double tatalD = new Double(totalS);
			Double agregarD = new Double(agregarS);						
			
			return (tatalD - agregarD)/100;

		} catch (Exception e) {
			return null;
		}
	}

	public static Boolean buscaPermisoEnviarFirmaInformeOrd(
			Collection<GrantedAuthority> authorities, Integer idOrganizacion) {
		for(GrantedAuthority ga : authorities){			
			if("ROLE_SIF.CAPTURISTA.OC".equals(ga.getAuthority())){
				return true;
			}
			if("ROLE_SIF.REPRESENTANTEFINANZAS.JL".equals(ga.getAuthority()) && idOrganizacion == 3){
				return true;
			}
			if("ROLE_SIF.REPRESENTANTEFINANZAS.OC".equals(ga.getAuthority())  && (idOrganizacion == 1 || idOrganizacion == 2)){
				return true;
			}
		}
		return false;
	}

	public static String traeAbreviacionEtapa(int idEtapa) {
		
		if(6==idEtapa){
			return "TRIM1";
		}
		if(7==idEtapa){
			return "TRIM2";
		}
		if(8==idEtapa){
			return "TRIM3";
		}
		if(9==idEtapa){
			return "TRIM4";
		}
		if(2==idEtapa){
			return "NORMAL";
		}
		if(17==idEtapa){
			return "AJUSTE";
		}
		if(18==idEtapa){
			return "AJUSTE";
		}
		return "";
	}

	public static String traeAbreviacionTipoEvidencia(Integer tipoEvidenciaSelec) {
		
		if(tipoEvidenciaSelec == 111){
			return "ADAF";
		}
		if(tipoEvidenciaSelec == 112){
			return "REPC";
		}
		if(tipoEvidenciaSelec == 113){
			return "INSI";
		}
		if(tipoEvidenciaSelec == 114){
			return "OTRS";
		}
		if(tipoEvidenciaSelec == 115){
			return "RETRO";
		}
	
		return "TODAS";
	}

	public static boolean buscaPermisoContabilidadInforme(
			Collection<GrantedAuthority> authorities, Integer idOrganizacion) {
		for(GrantedAuthority ga : authorities){			
//			if("ROLE_SIF.CAPTURISTA.OC".equals(ga.getAuthority())){
//				return true;
//			}
			if("ROLE_SIF.REPRESENTANTEFINANZAS.JL".equals(ga.getAuthority()) && (idOrganizacion == 1 || idOrganizacion == 2)){
				return false;
			}else if("ROLE_SIF.REPRESENTANTEFINANZAS.OC".equals(ga.getAuthority()) && (idOrganizacion == 3)){
				return false;
			}
//			if("ROLE_SIF.REPRESENTANTEFINANZAS.OC".equals(ga.getAuthority()) && idOrganizacion == 3){
//				return false;
//			}
		}
		return true;
	}


	public static Boolean esAdminIne(Collection<GrantedAuthority> authorities) {
		for(GrantedAuthority ga : authorities){			
			if("ROLE_SIF.INE_ADMIN.OC".equals(ga.getAuthority())){
				return true;
			}
		}
		return false;
	}

	public static String regresaFormatoTextoFechaMes(Date fechaHoraCreacion, Boolean tambienHora) {
		
		Calendar formaFecha;
		formaFecha = new GregorianCalendar();
		formaFecha.setTime(fechaHoraCreacion);
		
		StringBuffer regresaFormato = new StringBuffer();		
		regresaFormato.append(""+ (formaFecha.get(Calendar.DAY_OF_MONTH)<10 ? "0"+formaFecha.get(Calendar.DAY_OF_MONTH) : formaFecha.get(Calendar.DAY_OF_MONTH)));
		String mes = buscaNombreMes(new Integer(formaFecha.get(Calendar.MONTH)+1));
		regresaFormato.append(" "+mes.toLowerCase());
		regresaFormato.append(" "+formaFecha.get(Calendar.YEAR));	
		if(tambienHora){			
			if(formaFecha.get(Calendar.HOUR) == 0 && formaFecha.get(Calendar.AM_PM)==1){
				regresaFormato.append(" 12");
			}
			else{
				regresaFormato.append(" "+ (formaFecha.get(Calendar.HOUR)<10 ? "0"+formaFecha.get(Calendar.HOUR) : formaFecha.get(Calendar.HOUR)));
			}
			regresaFormato.append(":"+(formaFecha.get(Calendar.MINUTE)<10 ? "0"+formaFecha.get(Calendar.MINUTE) : formaFecha.get(Calendar.MINUTE)));		
			regresaFormato.append(" "+(formaFecha.get(Calendar.AM_PM)==0 ? "a.m." : "p.m."));
		}
					
		return regresaFormato.toString();
	}

	public static Double sumar(Double arg1, Double arg2) {		
		try {		
			DecimalFormat formateador = new DecimalFormat("0.00",DecimalFormatSymbols.getInstance(Locale.ENGLISH));			
			
			if(arg1 == null){
				arg1 = 0.00;
			}
			if(arg2 == null){
				arg2 = 0.00;
			}
			
			String totalS = formateador.format(arg1);			
			String agregarS =  formateador.format(arg2);

			
			//logger.info(totalS);
			//logger.info(agregarS);
			
			totalS = totalS.replace(".", "");
			agregarS = agregarS.replace(".", "");
			
			Double tatalD = new Double(totalS);
			Double agregarD = new Double(agregarS);
			
			//logger.info((tatalD + agregarD)/100);
			
			
			return (tatalD + agregarD)/100;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Boolean esCuentaBancaria(Long numeroCuenta, Long numeroCuentaBancos) {
		try {
			//Long numeroCuenta = new Long(Utils.mensajeProperties("constante_polizas_cuenta_banco"));			
			if(numeroCuentaBancos.equals(numeroCuenta)){
				return true;
			}
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
		
	}

	public static Boolean validaCuentaEgresos(Long numeroCuenta) {
		if(numeroCuenta>(new Long("4999999999")) && numeroCuenta<(new Long("5600000000"))){
			return true;
		}
		return false;
	}

	public static void streamContent(InputStream file, String name, String contentType) {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		ExternalContext ec = currentInstance.getExternalContext();
		ec.responseReset();
		ec.setResponseContentType(contentType);
		ec.setResponseCharacterEncoding("UTF-8");
		ec.setResponseHeader("Content-Disposition", "form-data;filename=\"" + name + "\"");
		ec.addResponseCookie(Constants.DOWNLOAD_COOKIE, "true", Collections.<String, Object>emptyMap());
		
		byte[] buffer = new byte[2048];
		
		try {
			java.io.OutputStream respuesta = ec.getResponseOutputStream();
			int length;			
			
			while ((length = file.read(buffer)) != -1)
				respuesta.write(buffer, 0, length);
			
			respuesta.close();
			ec.setResponseStatus(200);
			currentInstance.responseComplete();
			ec.responseFlushBuffer();
			file.close();
		} 
		catch (IOException e) {
			logger.error("", e);
		}
	}
	
	public static void streamXLSFile(String tempFile, String templateFile, String name) {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		ExternalContext ec = currentInstance.getExternalContext();
		ec.responseReset();
		ec.setResponseContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		ec.setResponseCharacterEncoding("UTF-8");
		ec.setResponseHeader("Content-Disposition", "form-data;filename=\"" + name + "\"");
		ec.addResponseCookie(Constants.DOWNLOAD_COOKIE, "true", Collections.<String, Object>emptyMap());
		
		SeekableByteChannel fc = null;
		Path file = Paths.get(tempFile);
		
		try (OutputStream respuesta = ec.getResponseOutputStream();
			ZipOutputStream zipRespuesta = new ZipOutputStream(respuesta);) {
			//zipRespuesta.setMethod(ZipOutputStream.);
			
			File _templateFile = new File(templateFile);
			java.util.zip.ZipInputStream zis = new java.util.zip.ZipInputStream(new FileInputStream(_templateFile));

			ZipEntry _ze;
			
			while ((_ze = zis.getNextEntry()) != null) {
				if (_ze.getName().endsWith("sheet1.xml"))
					continue;
				
				ZipEntry ze = new ZipEntry(_ze.getName());
				zipRespuesta.putNextEntry(ze);
				IOUtils.copy(zis, zipRespuesta);
			}
			
			zis.close();
			_templateFile.delete();
			
			String sheetName = "xl" + File.separator + "worksheets" + File.separator;
			zipRespuesta.putNextEntry(new ZipEntry(sheetName + "sheet1.xml"));
			
			String prefijoXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                                + "<worksheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\"><dimension ref=\"A1\"/><sheetViews><sheetView workbookViewId=\"0\" tabSelected=\"true\"/></sheetViews><sheetFormatPr defaultRowHeight=\"15.0\"/><sheetData>";
			
			String sufijoXML = "</sheetData><pageMargins bottom=\"0.75\" footer=\"0.3\" header=\"0.3\" left=\"0.7\" right=\"0.7\" top=\"0.75\"/></worksheet>";
			
			FileSystem sf = FileSystems.newFileSystem(file, null);
			Path sheetEntry = sf.getPath("/sheet_entry");
			
			fc = Files.newByteChannel(sheetEntry, EnumSet.of(java.nio.file.StandardOpenOption.READ));
			ByteBuffer bb = ByteBuffer.allocate(2048);
			byte buffer[] = new byte[2048];
			int leidos;
			
			zipRespuesta.write(prefijoXML.getBytes());
			
			while ((leidos = fc.read(bb)) > 0) {
				bb.flip();
				bb.get(buffer, 0, leidos);
				zipRespuesta.write(buffer, 0, leidos);
				bb.clear();
			}
			
			zipRespuesta.write(sufijoXML.getBytes());						
		}
		catch (SocketException e) {
			
		}
		catch (Exception e) {
			logger.error("", e);
		}
		finally {
			if (fc != null) {
				try {
					fc.close();
					
					fc = null;
					System.gc();
					
					Files.deleteIfExists(file);
				} 
				catch (IOException e) {
					logger.error("", e);
				}
			}
			
			ec.setResponseStatus(200);
			currentInstance.responseComplete();
			
			try {
				ec.responseFlushBuffer();
			}
			catch (IOException e) {
				logger.error("", e);
			}
		}
	}

	
	//Devuelve el nombre del reporte agregando la fecha y hora de craciÛn en el formato que se necesite
	//Por ejemplo si se le manda "Diario", "06/09/2017 07:12" y "xlsx", regresar· Diario_06092017_09_12.xlsx
	public static String nombreReporteContable(String primitivo, Date fechaCreacion, String extension) {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_HH_mm");
		
		return primitivo + "_" + sdf.format(fechaCreacion) + "." + extension;
	}
	
	public static String prefijo() {
		String ip = "";
		
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
			
			if (ip.equals("10.0.28.20") || ip.equals("10.0.28.21"))
				return "pruebas_";
			else if (ip.equals("10.0.28.131") || ip.equals("10.0.28.132"))
				return "pru_";
			else if (ip.equals("10.0.189.55") || ip.equals("10.0.189.56"))
				return "sif_prb_";
			else if (ip.equals("10.0.189.59") || ip.equals("10.0.189.60"))
				return "sif_us_prb_";
			else if (ip.equals("10.0.41.161") || ip.equals("10.0.41.162") || ip.equals("10.0.41.163") || ip.equals("10.0.41.164") || ip.equals("10.0.41.165")
					 || ip.equals("10.0.41.186") || ip.equals("10.0.41.187") || ip.equals("10.0.41.188") || ip.equals("10.0.41.189") || ip.equals("10.0.41.190") ||
					 "10.0.41.46".equals(ip) || "10.0.41.67".equals(ip) || "10.0.41.68".equals(ip) || "10.0.41.103".equals(ip) || "10.0.41.105".equals(ip) || 
					 "10.0.41.135".equals(ip) || "10.0.41.137".equals(ip) || "10.0.41.138".equals(ip) || "10.0.41.171".equals(ip) || "10.0.41.172".equals(ip) || 
					 "10.0.41.225".equals(ip) || "10.0.41.226".equals(ip) || "10.0.41.227".equals(ip) || "10.0.41.228".equals(ip) || "10.0.41.229".equals(ip))
				return "prod_";
		} 
		catch (UnknownHostException e) {
			logger.error("", e);
		}
		
		return "local_";
	}

	public static String carpetaNube() {
		String pref = Utils.prefijo(), sif;
		
		if (pref == "prod_")
			sif = "/sifv2";
		else
			sif = "/pruebas-sifv2";
		
		return sif;
	}
	
	public static Double truncarADecimales(Double numero, int dec) {
		Integer mult = (int) Math.pow(10, dec);
		
		return Math.rint(numero * mult) / mult;
	}

	public static void beginSession(SessionFactory sf) {
		Session s = sf.openSession();
		SessionHolder sh = new SessionHolder(s);
        TransactionSynchronizationManager.bindResource(sf, sh);
	}
	
	public static void endSession(SessionFactory sf) {
		try {
			sf.getCurrentSession().disconnect();
			sf.getCurrentSession().close();
	        TransactionSynchronizationManager.unbindResource(sf);
		}
		catch (HibernateException he) {
			logger.error("No se pudo terminar la sesiÛn: ", he);
		}
	}
	
	public static <T> String convertirEnConsultaSQL(List<T> lista, String nombreColumna) {
		String resultado = "SELECT /*+ MATERIALIZE*/ DISTINCT columna " + nombreColumna + " "
						 + "  FROM (SELECT COLUMN_VALUE columna "
						 + "          FROM TABLE(sys.odcinumberlist(";
		int contador = 1;
		
		for (T elemento : lista) {
			resultado += elemento;
					
			if (contador % 999 == 0 && contador != lista.size())
				resultado += ")) UNION ALL SELECT COLUMN_VALUE columna "
							 + "             FROM TABLE(sys.odcinumberlist(";
			else if (contador != lista.size())
				resultado += ",";
			
			contador++;
		}
		
		resultado += ")))";
		
		return resultado;
	}
	

	public static String urlArchivoCargaGluster(DTOUsuarioLogin usuarioLogin) {
		String ordNve = rutaGluster + "carga_archivos" + File.separator;
		String ruta = usuarioLogin.getIdContabilidad() + File.separator + usuarioLogin.getIdUsuario();
		
		return ordNve + ruta;
	}
	
	public static Integer obtenerIdAmbito(String ambito) {
		// TODO Auto-generated method stub
		if(ambito.equals("LOCAL"))
			return 2;
		else
			return 1;
	}

	public static String extension(String string) {
		String[] pedazos = string.split("\\.");
		
		return pedazos[pedazos.length - 1];
	}
	
	public static Double truncateDoubleDecimal(Double cargo) {
		DecimalFormat formateador = new DecimalFormat("#.##",DecimalFormatSymbols.getInstance(Locale.ENGLISH));
		return new Double(formateador.format(cargo));
	}
}
