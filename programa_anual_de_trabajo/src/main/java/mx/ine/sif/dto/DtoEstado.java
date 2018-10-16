package mx.ine.sif.dto;

import java.io.Serializable;

public class DtoEstado implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2550021549869444563L;
	private Integer idEstado;
	private String nombreEstado;
	private String abreviatura;
	
	public DtoEstado() {
	}
	public DtoEstado(Integer idEstado, String nombreEstado) {
		this.idEstado = idEstado;
		this.nombreEstado = nombreEstado;
	}
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	
}
