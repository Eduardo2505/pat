package mx.ine.sif.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DTOValoresGenerales implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7067934429798618582L;
	protected Date fechaHora_creacion;
	protected Integer id_usuario_modif;
	protected Integer id_usuario_creacion;
	protected Integer modulo;
	protected Integer pantalla;
	protected String version;
	protected String usuario;
	protected String usuario_creacion;
	protected Date fecha_Hora;
	
	public DTOValoresGenerales() {
		// TODO Auto-generated constructor stub
	}

	public Date getFechaHora_creacion() {
		return fechaHora_creacion;
	}

	public void setFechaHora_creacion(Date fechaHora_creacion) {
		this.fechaHora_creacion = fechaHora_creacion;
	}

	public Integer getId_usuario_modif() {
		return id_usuario_modif;
	}

	public void setId_usuario_modif(Integer id_usuario_modif) {
		this.id_usuario_modif = id_usuario_modif;
	}

	public Integer getId_usuario_creacion() {
		return id_usuario_creacion;
	}

	public void setId_usuario_creacion(Integer id_usuario_creacion) {
		this.id_usuario_creacion = id_usuario_creacion;
	}

	public Integer getModulo() {
		return modulo;
	}

	public void setModulo(Integer modulo) {
		this.modulo = modulo;
	}

	public Integer getPantalla() {
		return pantalla;
	}

	public void setPantalla(Integer pantalla) {
		this.pantalla = pantalla;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getUsuario_creacion() {
		return usuario_creacion;
	}

	public void setUsuario_creacion(String usuario_creacion) {
		this.usuario_creacion = usuario_creacion;
	}

	public Date getFecha_Hora() {
		return fecha_Hora;
	}

	public void setFecha_Hora(Date fecha_Hora) {
		this.fecha_Hora = fecha_Hora;
	}

}