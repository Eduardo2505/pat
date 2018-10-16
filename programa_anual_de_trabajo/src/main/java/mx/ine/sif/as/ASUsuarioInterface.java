/**
 * @(#)MBCalculoRecuento.java 09/06/2014
 *
 * Copyright (C) 2014 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.sif.as;

import java.util.List;

import mx.org.ine.servicios.dto.db.DTODistrito;
import mx.org.ine.servicios.dto.db.DTOEstado;

public interface ASUsuarioInterface {
	
	/**
	 * Método que obtiene la lista de Distritos del estado que se pasa como parametro
	 *  
	 * @param idEstado
	 * @return
	 * @author Alfredo González
	 * @since 09/06/2014
	 * @versión 1.0
	 */
	public List<DTODistrito> getDistritosPorEstadoAs(Integer idEstado);
	
	/**
	 * Método para obtener la información de un Estado por medio de su id 
	 * @param idEstado
	 * @return
	 */
	public DTOEstado getEstadoAs(Integer idEstado);


}
