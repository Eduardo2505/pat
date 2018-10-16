/**
 * @(#)DAODistritosComputosInterface.java 28/O4/2014
 *
 * Copyright (C) 2014 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.sif.dao;

import java.util.List;

import mx.org.ine.servicios.dao.DAOGenericInterface;
import mx.org.ine.servicios.dto.db.DTODistrito;
import mx.org.ine.servicios.dto.db.DTODistritoId;

/**
 * Interface de persitencia de datos para la 
 * administración de la tabla de DISTRITOS del esquema de COMPUTOS2015.
 * 
 * @author  Roberto Shirásago Domínguez
 * @since   28/04/2014
 */
public interface DAODistritosInterface extends DAOGenericInterface<DTODistrito, DTODistritoId> {

	/**
	 * Método que obtiene todos los distritos ordenados por el nombre de estos
	 * y por el id de la entidad a la que pertenecen
	 * 
	 * @return List<DTODistritosComputos> : lista de distritos ordenada por nombre y id entidad
	 * 
	 * @author Roberto Shirásago Domínguez
	 * @since  28/04/2014 
	 */
	public List<DTODistrito> getTodosDistritos();
	
	/**
	 * Método que obtiene todos los distritos  de un estado ordenados por ID.
	 * 
	 * @return List<DTODistritosComputos> : lista de distritos de un estado ordenada por id de distrito.
	 * 
	 * @author Roberto Shirásago Domínguez
	 * @since  28/04/2014 
	 */
	public List<DTODistrito> getDistritosEstado(String estado);
	
	/**
	 * Método que obtiene los distritos de un estado determinado
	 * 
	 * @return <code>List<DTODistritosComputos></code> Una lista con los distritos del estado
	 * enviado como parametro
	 * @author Alfredo González
	 * @since 09/06/2014
	 * @version 1.0
	 */
	public List<DTODistrito> getDistritosPorEstadoDao(Integer idEstado);
	
	/**
	 * Método que obtiene el nombre del distrito a partir del id del distrito
	 * 
	 * @param idEstado : identificador del estado.
	 * @param idDistrito : identificador del distrito.
	 * 
	 * @return String : nombre del distrito.
	 * 
	 * @author Jorge Alberto Fuentes Pacheco
	 * @since  10/06/2014 
	 */
	public String getNombreDistrito(Integer idEstado, Integer idDistrito);
	


	/**
	 * Método traer datos de un distrito.
	 * @param estado : estado del distrito.
	 * @param distrito : distrito buscado.
	 * @return DTODistritosComputos : datos del distrito.
	 * 
	 * @author Samuel Barrios González
	 * @since 22/04/2014
	 * @copyright INE
	 * 
	 */
	public DTODistrito getDatosDistrito(String estado, String distrito);
	
}
