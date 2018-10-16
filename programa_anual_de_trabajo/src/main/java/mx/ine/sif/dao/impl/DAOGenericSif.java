/**
 * @(#)DAOGenericSistemax.java 20/03/2014
 *
 * Copyright (C) 2014 Instituto Federal Electoral (IFE).
 * Todos los derechos reservados.
 */
package mx.ine.sif.dao.impl;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

import mx.ine.sif.dto.DTOUsuarioLogin;
import mx.ine.sif.dto.DTOValoresGenerales;
import mx.org.ine.servicios.dao.impl.DAOGeneric;
import mx.org.ine.servicios.query.QRYContainerInterface;

/**
 * Esta clase es un ejemplo para saber como extender y que poner de la clase DAOGeneric
 * @author IFE
 */
public class DAOGenericSif<T extends Serializable, ID extends Serializable> extends DAOGeneric<T, ID> {

	/**
	 * Contenedor de queries.
	 */
	@Autowired
	@Qualifier("qryContainer")
	@SessionScoped
	private QRYContainerInterface qryContainer;
	
	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.servicios.dao.impl.DAOGeneric#getContainer()
	 */
	@Override
	public QRYContainerInterface getContainer() {
		return qryContainer;
	}
	
	
	public static void cambiaFechasYUsuarios(DTOValoresGenerales dto, boolean insert){
		DTOUsuarioLogin usu = (DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(insert){
		dto.setFecha_Hora(new Date());
		dto.setFechaHora_creacion(new Date());
		dto.setUsuario(usu.getUsuario());
		dto.setId_usuario_modif(usu.getIdUsuario());
		dto.setId_usuario_creacion(usu.getIdUsuario());
		dto.setUsuario_creacion(usu.getUsuario());
		}else 
		{
			dto.setFecha_Hora(new Date());
			dto.setUsuario(usu.getUsername());
			dto.setId_usuario_modif(usu.getIdUsuario());
		}
		
		dto.setVersion(usu.getIdVersion());
		dto.setPantalla(usu.getIdPantalla());
		dto.setModulo(usu.getIdModulo());
		
  	}

}
