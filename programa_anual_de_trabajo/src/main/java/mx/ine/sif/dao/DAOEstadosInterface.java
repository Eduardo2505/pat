/**
 * @(#)DAOEstadosComputosInterface.java 28/O4/2014
 *
 * Copyright (C) 2014 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.sif.dao;

import java.util.List;

import mx.ine.sif.dto.DtoEstado;
import mx.org.ine.servicios.dao.DAOGenericInterface;
import mx.org.ine.servicios.dto.db.DTOEstado;

/**
 * Interface de persitencia de datos para la 
 * administración de la tabla de ESTADOS del esquema de COMPUTOS2015.
 * 
 * @author  Roberto Shirásago Domínguez
 * @since   28/04/2014
 */
public interface DAOEstadosInterface extends DAOGenericInterface<DTOEstado, Integer> {

	/**
	 * Método que obtiene todos los estados ordenados por el nombre de estos
	 * 
	 * @return List<DTOEstadosComputos> : lista de estados ordenada por nombre
	 * 
	 * @author Roberto Shirásago Domínguez
	 * @since  28/04/2014 
	 */
	public List<DTOEstado> getTodosEstados();
	
	/**
	 * Método que obtiene el nombre del estado a partir del id del estado
	 * 
	 * @param idEstado : identificador del Estado.
	 * 
	 * @return String : nombre del estado.
	 * 
	 * @author Jorge Alberto Fuentes Pacheco
	 * @since  10/06/2014 
	 */
	public String getNombreEstado(Integer idEstado);
	
	/**
	 * Método que obtiene el número de circunscripción plurinominal
	 * al que pertenece el estado
	 * 
	 * @param idEstado : identificador del Estado.
	 * 
	 * @return String : número romano de la circunscripción
	 * 
	 * @author Jorge Alberto Fuentes Pacheco
	 * @since  10/06/2014 
	 */
	public String getNumeroRomanoCircunscripcion(Integer idEstado);
	
	/**
	 * Método traer  datos de un  estado.
	 * 
	 * @param estado.
	 * @return DTOEstadosComputos : datos del estado.
	 * 
	 * @author Samuel Barrios González
	 * @since 22/04/2014
	 * @copyright INE
	 */
	public DTOEstado getDatosEstado(String estado);
	
	public List<mx.ine.sif.dto.DtoEstado> consultaMunicipioPorEstado(int idEstado);
	public String consultaNombreMunicipio(int idEstado, int idMunicipio);

	public List<DtoEstado> consultaDistritoPorEstado(Integer idEstado);

	List<DtoEstado> consultaDistritoLocalPorEstado(Integer idEstado);
	
}
