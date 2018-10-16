/**
 * @(#)QRYContainer.java 21/O2/2014
 *
 * Copyright (C) 2014 Instituto Federal Electoral (IFE).
 * Todos los derechos reservados.
 */
package mx.ine.sif.query;

import java.util.Properties;

import mx.org.ine.servicios.query.QRYContainerInterface;

import org.springframework.stereotype.Component;

/**
 * Esta clase define e implementa los métodos básicos para administrar 
 * los queries almacenados en el archivo </strong>QuerySource.properties</strong>
 * 
 * NOTA IMPORTANTE: Esta clase es única! y con única nos referimos a que
 *                  no deben crearse más de este tipo ya que solo esta podra
 *                  contener el properties de los querys que se utilizen, esto 
 *                  para tener una mejor adminitración de los querys.
 * 
 * @author IFE - Roberto Shirásago Domínguez
 * @since 21/02/2012
 */
@Component("qryContainer")
public class QRYContainer implements QRYContainerInterface {

	/* ------------------------------- ATRIBUTOS ------------------------------------- */

	/**
	 * Archivo properties que contiene todos los queries de la aplicación.
	 */
	private Properties querySource;
	
	/* -------------------------------------------------------------------------------- */
	/* --------------------------------- METODOS -------------------------------------- */
	/* -------------------------------------------------------------------------------- */
	
	public QRYContainer() throws Exception {
		
		querySource = new Properties();
		querySource.load( QRYContainer.class.getResourceAsStream( "/QuerySource.properties" ) );
	}

	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.sif.query.QRYContainerInterface#getQuery(java.lang.String)
	 */
	@Override
	public String getQuery(String nombreQuery) {
		return querySource.getProperty(nombreQuery);
	}

}
