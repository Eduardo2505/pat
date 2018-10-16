/**
 * @(#)DAOEstadosComputos.java 28/O4/2014
 *
 * Copyright (C) 2014 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.sif.dao.impl;

import java.util.ArrayList;
import java.util.List;

import mx.ine.sif.dao.DAOEstadosInterface;
import mx.ine.sif.dto.DtoEstado;
import mx.org.ine.servicios.dto.db.DTOEstado;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Clase de persitencia de datos para la 
 * administración de la tabla de ESTADOS del esquema de COMPUTOS2015.
 * 
 * @author  Roberto Shirásago Domínguez
 * @since   28/04/2014
 */
@Scope("prototype")
@Repository("daoEstados")
public class DAOEstados extends DAOGenericSif<DTOEstado, Integer> implements DAOEstadosInterface {
	private static final Logger logger = Logger.getLogger(DAOEstados.class);
	
	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.computos.dao.DAOEstadosComputosInterface#getTodosEstados()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<DTOEstado> getTodosEstados() {
		
		Criteria criteria = getSession().createCriteria(DTOEstado.class);
		
		criteria.addOrder(Order.asc("idEstado"));
		
		return (List<DTOEstado>)criteria.list();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.computos.dao.DAOEstadosComputosInterface#getNombreEstado(java.lang.Integer)
	 */
	@Override
	public String getNombreEstado(Integer idEstado){
		Criteria criteria = getSession().createCriteria(DTOEstado.class);
		criteria.add(Restrictions.eq("idEstado", idEstado));
		DTOEstado res = (DTOEstado) criteria.uniqueResult();
		return res.getNombreEstado();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.computos.dao.DAOEstadosComputosInterface#getNumeroRomanoCircunscripcion(java.lang.Integer)
	 */
	@Override
	public String getNumeroRomanoCircunscripcion(Integer idEstado){
		Criteria criteria = getSession().createCriteria(DTOEstado.class);
		criteria.add(Restrictions.eq("idEstado", idEstado));
		DTOEstado res = (DTOEstado) criteria.uniqueResult();
		return res.getCircunscripcion();	
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.computos.dao.DAOEstadosComputosInterface#getDatosEstado(java.lang.String)
	 */
	@Override
	public DTOEstado getDatosEstado(String estado) {
		
			Criteria criteria = getSession().createCriteria(DTOEstado.class);					
			criteria.add(Restrictions.eq("idEstado", Integer.parseInt(estado)));		
			
			return (DTOEstado) criteria.uniqueResult();
	}	
	
	@Override
	public List<mx.ine.sif.dto.DtoEstado> consultaMunicipioPorEstado(int idEstado) {
		List<mx.ine.sif.dto.DtoEstado> municipios = new ArrayList<mx.ine.sif.dto.DtoEstado>();
		
		String q = "select m.id_municipio, m.nombre_municipio from geografico2015.municipios m where m.id_estado = :idEstado order by m.nombre_municipio";
		
		SQLQuery query = getSession().createSQLQuery(q);
		query.setParameter("idEstado", idEstado);
		
		List<Object[]> lista = query.list();
		for(Object[] arreglo:lista)
			municipios.add(new mx.ine.sif.dto.DtoEstado(Integer.valueOf(arreglo[0].toString()),arreglo[1].toString()));
		return municipios;
	}
	
	@Override
	public String consultaNombreMunicipio(int idEstado, int idMunicipio) {
		String q = "select m.nombre_municipio from geografico2015.municipios m where m.id_estado = :idEstado and m.ID_MUNICIPIO = :idMunicipio";
		SQLQuery query = getSession().createSQLQuery(q);
		query.setParameter("idEstado", idEstado);
		query.setParameter("idMunicipio", idMunicipio);
		
		return query.uniqueResult().toString();
	}

	@Override
	public List<DtoEstado> consultaDistritoPorEstado(Integer idEstado) {
		List<mx.ine.sif.dto.DtoEstado> distritos = new ArrayList<mx.ine.sif.dto.DtoEstado>();
		
		String q = 	 "select id_distrito, cabecera_distrital "
					+"from geografico2015.distritos  "
					+"where id_estado = :idEstado "
					+"  and id_distrito != 0 "
					+"order by id_distrito ";
		
		SQLQuery query = getSession().createSQLQuery(q);
		query.setParameter("idEstado", idEstado);
		
		List<Object[]> lista = query.list();

		for(Object[] arreglo:lista){
		//	logger.debug(arreglo[1].toString());
			distritos.add(new mx.ine.sif.dto.DtoEstado(Integer.valueOf(arreglo[0].toString()),arreglo[1].toString()));
		}
		
		getSession().clear();
		return distritos;
	}
	
	@Override
	public List<DtoEstado> consultaDistritoLocalPorEstado(Integer idEstado) {
		List<mx.ine.sif.dto.DtoEstado> distritos = new ArrayList<mx.ine.sif.dto.DtoEstado>();
		
		String q = 	 "select id_distrito, cabecera_distrital "
					+"from geografico_local2015.distritos_locales  "
					+"where id_estado = :idEstado "
					+"  and id_distrito != 0 "
					+"order by id_distrito ";
		
		SQLQuery query = getSession().createSQLQuery(q);
		query.setParameter("idEstado", idEstado);
		
		List<Object[]> lista = query.list();

		for(Object[] arreglo:lista){
			distritos.add(new mx.ine.sif.dto.DtoEstado(Integer.valueOf(arreglo[0].toString()),arreglo[1].toString()));
		}
		
		getSession().clear();
		return distritos;
	}
	
}
