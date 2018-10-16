/**
 * @(#)DTOBase.java 21/02/2014
 *
 * Copyright (C) 2014 INE.
 * Todos los derechos reservados.
 */

package mx.ine.sif.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import mx.org.ine.servicios.dto.db.DTOEstado;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Clase que contiene los datos del usuario que se autentica.
 * 
 * @author Martha Castañón
 * @since 20 de marzo de 2014
 */
public class DTOUsuarioLogin extends User implements Serializable {

	/**
	 * Elemento necesario para la serialización de los objetos generados de
	 * esta clase.
	 */
	private static final long serialVersionUID = -6309588765960326593L;

	/**
	 * Username del usuario que se autentica.
	 */
	private String usuario;

	/**
	 * Nombre del usuario que se autentica.
	 */
	private String nombreUsuario;
	private String apellidosUsuario;
	private Integer idEstado;
	private Integer idDistrito;
	private Integer idEstadoVisibilidad;
	private Integer idSistema;
	private Integer idUsuario;
	private Integer idEntidadSO;
	private Integer idTipoSO;
	private Integer idSO;
	private Integer idSO_sif;
	private Integer idRol;
	private String rfc;
	private String curp;
	private ArrayList<Integer> idsContbilidadesAsociadas;
	private String nombreCandidato;
	private Integer idContabilidad;
	private Boolean concentradora;
	private Integer idEstadoSeleccionado;
	private Integer idDistritoSeleccionado;
	private String nombreUbicacion;
	private DTOEstado estado;
	private String idVersion;
	private int  idProceso;
	private int  idModulo;
	private int  idPantalla;
	private  String  ambito;
	private  String  entidadCandidadatura;
	private  String  subentidadCandidadatura;
	private  String  cargo;
	private  int  estatusContabilidad;
	private  String  curpCandidato;
	private  String  rfcCandidato;
	private  String  nombreSO;
	private String comite;
	private String siglasPartido;
	private String siglasComite;
	private Integer idEntidadCandidatura;
	private Integer idCircunscripcionCandidatura;
	private Integer idAmbitoCandidatura;
	private Integer idCandidatoRNC;
	private Integer idTablaCandidato;
	private String nombreCandidatoInformes;
	private String primerApellidoInformes;
	private String segundoApellidoInformes;
	private Boolean coalicion;
	private Object[] coaliciones;
	private String tipoAsociacion;
	private int permisoConta;
	private Date fechaInicio;
	private Map<Integer, String> idsSOAsignados =   new HashMap<Integer, String>() ;
	private Map<Integer, Integer> permisoSO =   new HashMap<Integer, Integer>() ;
	private Integer idProcesoElectoral; 
	private Integer idProcesoPadre;
	private Integer anio;
	private String anioProcesoPadre;
	private Long idBitacora;
	private Integer principio;
	private String partidosCOA;
	private Integer contabilidad;
	private Integer idTipoCandidatura;
	private Integer idTipoAsociacion;


	/**
	 * Rol simulado a partir de los filtros elegidos por el usuario al inicio
	 * del sistema (sÃ³lo aplica para usuarios de OC y JL).
	 */
	private String rolSistema;

	public DTOUsuarioLogin(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {

		super(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);

	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getApellidosUsuario() {
		return apellidosUsuario;
	}

	public void setApellidosUsuario(String apPaterno) {
		this.apellidosUsuario = apPaterno;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public Integer getIdDistrito() {
		return idDistrito;
	}

	public void setIdDistrito(Integer idDistrito) {
		this.idDistrito = idDistrito;
	}

	public Integer getIdEstadoVisibilidad() {
		return idEstadoVisibilidad;
	}

	public void setIdEstadoVisibilidad(Integer idEstadoVisibilidad) {
		this.idEstadoVisibilidad = idEstadoVisibilidad;
	}

	public Integer getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(Integer idSistema) {
		this.idSistema = idSistema;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdSO() {
		return idSO;
	}

	public void setIdSO(Integer idSO) {
		this.idSO = idSO;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public ArrayList<Integer> getIdsContbilidadesAsociadas() {
		return idsContbilidadesAsociadas;
	}

	public void setIdsContbilidadesAsociadas(
			ArrayList<Integer> idsContbilidadesAsociadas) {
		this.idsContbilidadesAsociadas = idsContbilidadesAsociadas;
	}

	public String getNombreCandidato() {
		return nombreCandidato;
	}

	public void setNombreCandidato(String nombreCandidato) {
		this.nombreCandidato = nombreCandidato;
	}

	public Integer getIdContabilidad() {
		return idContabilidad;
	}

	public void setIdContabilidad(Integer idContabilidad) {
		this.idContabilidad = idContabilidad;
	}

	public Boolean getConcentradora() {
		return concentradora;
	}

	public void setConcentradora(Boolean concentradora) {
		this.concentradora = concentradora;
	}

	public Integer getIdEstadoSeleccionado() {
		return idEstadoSeleccionado;
	}

	public void setIdEstadoSeleccionado(Integer idEstadoSeleccionado) {
		this.idEstadoSeleccionado = idEstadoSeleccionado;
	}

	public Integer getIdDistritoSeleccionado() {
		return idDistritoSeleccionado;
	}

	public void setIdDistritoSeleccionado(Integer idDistritoSeleccionado) {
		this.idDistritoSeleccionado = idDistritoSeleccionado;
	}

	public String getNombreUbicacion() {
		return nombreUbicacion;
	}

	public void setNombreUbicacion(String nombreUbicacion) {
		this.nombreUbicacion = nombreUbicacion;
	}

	public DTOEstado getEstado() {
		return estado;
	}

	public void setEstado(DTOEstado estado) {
		this.estado = estado;
	}

	public String getIdVersion() {
		return idVersion;
	}

	public void setIdVersion(String idVersion) {
		this.idVersion = idVersion;
	}

	public String getRolSistema() {
		return rolSistema;
	}

	public void setRolSistema(String rolSistema) {
		this.rolSistema = rolSistema;
	}

	public Integer getIdEntidadSO() {
		return idEntidadSO;
	}

	public void setIdEntidadSO(Integer idEntidadSO) {
		this.idEntidadSO = idEntidadSO;
	}

	public Integer getIdTipoSO() {
		return idTipoSO;
	}

	public void setIdTipoSO(Integer idTipoSO) {
		this.idTipoSO = idTipoSO;
	}

	public Integer getIdSO_sif() {
		return idSO_sif;
	}

	public void setIdSO_sif(Integer idSO_sif) {
		this.idSO_sif = idSO_sif;
	}

	public int getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(int idProceso) {
		this.idProceso = idProceso;
	}

	public int getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(int idModulo) {
		this.idModulo = idModulo;
	}

	public int getIdPantalla() {
		return idPantalla;
	}

	public void setIdPantalla(int idPantalla) {
		this.idPantalla = idPantalla;
	}
	

	public String getAmbito() {
		return ambito;
	}

	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}

	public String getEntidadCandidadatura() {
		return entidadCandidadatura;
	}

	public void setEntidadCandidadatura(String entidadCandidadatura) {
		this.entidadCandidadatura = entidadCandidadatura;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public int getEstatusContabilidad() {
		return estatusContabilidad;
	}

	public void setEstatusContabilidad(int estatusContabilidad) {
		this.estatusContabilidad = estatusContabilidad;
	}

	public String getCurpCandidato() {
		return curpCandidato;
	}

	public void setCurpCandidato(String curpCandidato) {
		this.curpCandidato = curpCandidato;
	}

	public String getRfcCandidato() {
		return rfcCandidato;
	}

	public void setRfcCandidato(String rfcCandidato) {
		this.rfcCandidato = rfcCandidato;
	}

	public String getNombreSO() {
		return nombreSO;
	}

	public void setNombreSO(String nombreSO) {
		this.nombreSO = nombreSO;
	}
	

	public String getSubentidadCandidadatura() {
		return subentidadCandidadatura;
	}

	public void setSubentidadCandidadatura(String subentidadCandidadatura) {
		this.subentidadCandidadatura = subentidadCandidadatura;
	}
	
	
	public String getSiglasPartido() {
		return siglasPartido;
	}

	public void setSiglasPartido(String siglasPartido) {
		this.siglasPartido = siglasPartido;
	}

	public String getSiglasComite() {
		return siglasComite;
	}

	public void setSiglasComite(String siglasComite) {
		this.siglasComite = siglasComite;
	}

	public String getComite() {
		return comite;
	}

	public void setComite(String comite) {
		this.comite = comite;
	}

	public Integer getIdEntidadCandidatura() {
		return idEntidadCandidatura;
	}

	public void setIdEntidadCandidatura(Integer idEntidadCandidatura) {
		this.idEntidadCandidatura = idEntidadCandidatura;
	}

	public Integer getIdCircunscripcionCandidatura() {
		return idCircunscripcionCandidatura;
	}

	public void setIdCircunscripcionCandidatura(Integer idCircunscripcionCandidatura) {
		this.idCircunscripcionCandidatura = idCircunscripcionCandidatura;
	}

	public Integer getIdAmbitoCandidatura() {
		return idAmbitoCandidatura;
	}

	public void setIdAmbitoCandidatura(Integer idAmbitoCandidatura) {
		this.idAmbitoCandidatura = idAmbitoCandidatura;
	}
	
	public Integer getIdCandidatoRNC() {
		return idCandidatoRNC;
	}

	public void setIdCandidatoRNC(Integer idCandidatoRNC) {
		this.idCandidatoRNC = idCandidatoRNC;
	}

	public Integer getIdTablaCandidato() {
		return idTablaCandidato;
	}

	public void setIdTablaCandidato(Integer idTablaCandidato) {
		this.idTablaCandidato = idTablaCandidato;
	}

	public String getNombreCandidatoInformes() {
		return nombreCandidatoInformes;
	}

	public void setNombreCandidatoInformes(String nombreCandidatoInformes) {
		this.nombreCandidatoInformes = nombreCandidatoInformes;
	}

	public String getPrimerApellidoInformes() {
		return primerApellidoInformes;
	}

	public void setPrimerApellidoInformes(String primerApellidoInformes) {
		this.primerApellidoInformes = primerApellidoInformes;
	}

	public String getSegundoApellidoInformes() {
		return segundoApellidoInformes;
	}

	public void setSegundoApellidoInformes(String segundoApellidoInformes) {
		this.segundoApellidoInformes = segundoApellidoInformes;
	}

	public Boolean getCoalicion() {
		return coalicion;
	}

	public void setCoalicion(Boolean coalicion) {
		this.coalicion = coalicion;
	}
	
	
	public Object[] getCoaliciones() {
		return coaliciones;
	}

	public void setCoaliciones(Object[] coaliciones) {
		this.coaliciones = coaliciones;
	}

	public int getPermisoConta() {
		return permisoConta;
	}

	public void setPermisoConta(int permisoConta) {
		this.permisoConta = permisoConta;
	}
	
	public Map<Integer, Integer> getPermisoSO() {
		return permisoSO;
	}

	public void setPermisoSO(Map<Integer, Integer> permisoSO) {
		this.permisoSO = permisoSO;
	}

	public String getTipoAsociacion() {
		return tipoAsociacion;
	}

	public void setTipoAsociacion(String tipoAsociacion) {
		this.tipoAsociacion = tipoAsociacion;
	}
	
	public Map<Integer, String> getIdsSOAsignados() {
		return idsSOAsignados;
	}

	public void setIdsSOAsignados(Map<Integer, String> idsSOAsignados) {
		this.idsSOAsignados = idsSOAsignados;
	}
	

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public Integer getIdProcesoElectoral() {
		return idProcesoElectoral;
	}

	public void setIdProcesoElectoral(Integer idProcesoElectoral) {
		this.idProcesoElectoral = idProcesoElectoral;
	}

	@Override
	public String toString() {
		return "[usuario=" + usuario + ", nombreUsuario="
				+ nombreUsuario + ", apellidosUsuario=" + apellidosUsuario
				+ ", idEstado=" + idEstado + ", idDistrito=" + idDistrito
				+ ", idEstadoVisibilidad=" + idEstadoVisibilidad
				+ ", 2016" + idSistema + ", 2016" + idUsuario
				+ ", idEntidadSO=" + idEntidadSO + ", 2015" + idTipoSO
				+ ", 2222" + idSO + ", 2223" + idSO_sif + ", 2120"
				+ idRol + ", rfc=" + rfc + ", curp=" + curp
				+ ", 1234567895245" + idsContbilidadesAsociadas
				+ ", nombreCandidato=" + nombreCandidato + ", 45285695"
				+ idContabilidad + ", concentradora=" + concentradora
				+ ", idEstadoSeleccionado=" + idEstadoSeleccionado
				+ ", idDistritoSeleccionado=" + idDistritoSeleccionado
				+ ", nombreUbicacion=" + nombreUbicacion + ", estado=" + estado
				+ ", 852582" + idVersion + ", 456321" + idProceso
				+ ", 7896321147" + idModulo + ", 4563214569" + idPantalla
				+ ", ambito=" + ambito + ", entidadCandidadatura="
				+ entidadCandidadatura + ", subentidadCandidadatura="
				+ subentidadCandidadatura + ", cargo=" + cargo
				+ ", estatusContabilidad=" + estatusContabilidad
				+ ", curpCandidato=" + curpCandidato + ", rfcCandidato="
				+ rfcCandidato + ", nombreSO=" + nombreSO + ", comite="
				+ comite + ", siglasPartido=" + siglasPartido
				+ ", siglasComite=" + siglasComite + ", idEntidadCandidatura="
				+ idEntidadCandidatura + ", idCircunscripcionCandidatura="
				+ idCircunscripcionCandidatura + ", idAmbitoCandidatura="
				+ idAmbitoCandidatura + ", 7852369852147" + idCandidatoRNC
				+ ", 753951753951" + idTablaCandidato
				+ ", nombreCandidatoInformes=" + nombreCandidatoInformes
				+ ", primerApellidoInformes=" + primerApellidoInformes
				+ ", segundoApellidoInformes=" + segundoApellidoInformes
				+ ", 7859" + rolSistema + "idBitatora = " +idBitacora + ", contabilidad = "+contabilidad+"]";
	}


	public String toStringInformes(){
		return usuario+","+nombreUsuario+","+apellidosUsuario+","+idSO_sif;
	}
	
	public String toStringRfcCurp(){
		return rfc+","+curp;
	}

	public Integer getIdProcesoPadre() {
		return idProcesoPadre;
	}

	public void setIdProcesoPadre(Integer idProcesoPadre) {
		this.idProcesoPadre = idProcesoPadre;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public String getAnioProcesoPadre() {
		return anioProcesoPadre;
	}

	public void setAnioProcesoPadre(String anioProcesoPadre) {
		this.anioProcesoPadre = anioProcesoPadre;
	}
	
	public Long getIdBitacora() {
		return idBitacora;
	}

	public void setIdBitacora(Long idBitacora) {
		this.idBitacora = idBitacora;
	}

	/**
	 * @return the principio
	 */
	public Integer getPrincipio() {
		return principio;
	}

	/**
	 * @param principio the principio to set
	 */
	public void setPrincipio(Integer principio) {
		this.principio = principio;
	}

	public String getPartidosCOA() {
		return partidosCOA;
	}

	public void setPartidosCOA(String partidosCOA) {
		this.partidosCOA = partidosCOA;
	}
	
	public Integer getContabilidad() {
		return contabilidad;
	}

	public void setContabilidad(Integer contabilidad) {
		this.contabilidad = contabilidad;
	}

	public Integer getIdTipoCandidatura() {
		return idTipoCandidatura;
	}

	public void setIdTipoCandidatura(Integer idTipoCandidatura) {
		this.idTipoCandidatura = idTipoCandidatura;
	}

	public Integer getIdTipoAsociacion() {
		return idTipoAsociacion;
	}

	public void setIdTipoAsociacion(Integer idTipoAsociacion) {
		this.idTipoAsociacion = idTipoAsociacion;
	}
}
