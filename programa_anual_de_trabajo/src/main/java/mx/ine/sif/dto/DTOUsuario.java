package mx.ine.sif.dto;

import java.io.Serializable;
import java.util.Date;

public class DTOUsuario extends DTOValoresGenerales implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2366767217987207593L;

	private Integer idUsuario;
	private String usuarioLdap;
	private Integer idGrupo;
	private Integer idEstadoVisibilidad;
	private Date fechaPrimeraVisita;
	private Date fechaUltimaVisita;
	private Integer permisosRecibos;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuarioLdap() {
		return usuarioLdap;
	}

	public void setUsuarioLdap(String usuarioLdap) {
		this.usuarioLdap = usuarioLdap;
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	public Integer getIdEstadoVisibilidad() {
		return idEstadoVisibilidad;
	}

	public void setIdEstadoVisibilidad(Integer idEstadoVisibilidad) {
		this.idEstadoVisibilidad = idEstadoVisibilidad;
	}

	public Date getFechaPrimeraVisita() {
		return fechaPrimeraVisita;
	}

	public void setFechaPrimeraVisita(Date fechaPrimeraVisita) {
		this.fechaPrimeraVisita = fechaPrimeraVisita;
	}

	public Date getFechaUltimaVisita() {
		return fechaUltimaVisita;
	}

	public void setFechaUltimaVisita(Date fechaUltimaVisita) {
		this.fechaUltimaVisita = fechaUltimaVisita;
	}

	public Integer getPermisosRecibos() {
		return permisosRecibos;
	}

	public void setPermisosRecibos(Integer permisosRecibos) {
		this.permisosRecibos = permisosRecibos;
	}

}
