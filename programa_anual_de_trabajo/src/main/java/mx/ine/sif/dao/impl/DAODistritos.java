/**
 * @(#)DAODistritosComputos.java 28/O4/2014
 *
 * Copyright (C) 2014 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.sif.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import mx.ine.sif.dao.DAODistritosInterface;
import mx.org.ine.servicios.dto.db.DTODistrito;
import mx.org.ine.servicios.dto.db.DTODistritoId;
/**
 * Clase de persitencia de datos para la 
 * administración de la tabla de DISTRITOS del esquema de COMPUTOS2015.
 * 
 * @author  Roberto Shirásago Domínguez
 * @since   28/04/2014
 */
@Scope("prototype")
@Repository("daoDistritos")
public class DAODistritos extends DAOGenericSif<DTODistrito, DTODistritoId> implements DAODistritosInterface {

	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.computos.dao.DAODistritosComputosInterface#getTodosDistritos()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTODistrito> getTodosDistritos() {
		
		Criteria criteria = getSession().createCriteria(DTODistrito.class);
		
		criteria.addOrder(Order.asc("idEstado"));
		criteria.addOrder(Order.asc("cabeceraDistrital"));
		
		return (List<DTODistrito>)criteria.list();
	}
	
	/* (non-Javadoc)
	 * @see mx.org.ine.computos.dao.DAODistritosComputosInterface#getDistritosEstado(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DTODistrito> getDistritosEstado(String estado) {
		Criteria criteria = getSession().createCriteria(DTODistrito.class);
		
		criteria.add(Restrictions.eq("idEstado", Integer.parseInt(estado)))
				.add(Restrictions.gt("idDistrito", 0));
		criteria.addOrder(Order.asc("idEstado"));		
		
		return (List<DTODistrito>)criteria.list();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.computos.dao.DAODistritosComputosInterface#getDistritosPorEstadoDao(java.lang.Integer)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTODistrito> getDistritosPorEstadoDao(Integer idEstado) {
		
		Criteria criteria = getSession().createCriteria(DTODistrito.class);
		criteria.add(Restrictions.eq("idEstado", idEstado));
		
		criteria.addOrder(Order.asc("idDistrito"));
		
		return (List<DTODistrito>)criteria.list();
	}
	
	/* (non-Javadoc)
	 * @see mx.org.ine.computos.dao.DAODistritosComputosInterface#getNombreDistrito(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public String getNombreDistrito(Integer idEstado, Integer idDistrito){
		Criteria criteria = getSession().createCriteria(DTODistrito.class);
		criteria.add(Restrictions.eq("idEstado", idEstado))
				.add(Restrictions.eq("idDistrito", idDistrito));
		DTODistrito res = (DTODistrito) criteria.uniqueResult();
		return res.getCabeceraDistrital();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.computos.dao.DAODistritosComputosInterface#getDatosDistrito(java.lang.String, java.lang.String)
	 */
	@Override
	public DTODistrito getDatosDistrito(String estado, String distrito) {
		Criteria criteria = getSession().createCriteria(DTODistrito.class);					
		criteria.add(Restrictions.eq("idEstado", Integer.parseInt(estado)));		
		criteria.add(Restrictions.eq("idDistrito", Integer.parseInt(distrito)));
		
		return (DTODistrito) criteria.uniqueResult();
	}
}
