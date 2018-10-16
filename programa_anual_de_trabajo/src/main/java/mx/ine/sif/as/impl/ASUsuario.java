/**
 * @(#)MBCalculoRecuento.java 09/06/2014
 *
 * Copyright (C) 2014 Instituto Nacional Electoral (INE).
 * 
 * Todos los derechos reservados.
 */
package mx.ine.sif.as.impl;

import java.util.List;

import mx.ine.sif.as.ASUsuarioInterface;
import mx.ine.sif.dao.DAODistritosInterface;
import mx.ine.sif.dao.DAOEstadosInterface;
import mx.ine.sif.dto.DTOUsuario;
import mx.ine.sif.util.Utils;
import mx.org.ine.servicios.dto.db.DTODistrito;
import mx.org.ine.servicios.dto.db.DTOEstado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("asUsuario")
@Scope("prototype")
public class ASUsuario implements ASUsuarioInterface{
	
	/*
	 * Propiedades inyectadas
	 */
	
	@Autowired
	@Qualifier("daoDistritos")
	private DAODistritosInterface daoDistritosComputos;
	
	@Autowired
	@Qualifier("daoEstados")
	private DAOEstadosInterface daoEstadosComputos;
	
	
	
	/*
	 * Inician métodos
	 */
	
	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.computos.as.ASUsuarioInterface#getDistritosPorEstadoAs(java.lang.Integer)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<DTODistrito> getDistritosPorEstadoAs(Integer idEstado){
		
		List<DTODistrito> listaDtoDistritosComputos;
		
		listaDtoDistritosComputos = daoDistritosComputos.getDistritosPorEstadoDao(idEstado);
		
		for(DTODistrito dto : listaDtoDistritosComputos){
			if(!dto.getIdEstado().equals(0) && dto.getIdDistrito() == 0){
				dto.setCabeceraDistrital(Utils.mensajeProperties("etiqueta_juntaLocal"));
			}    				
		}
		return listaDtoDistritosComputos;
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.org.ine.computos.as.ASUsuarioInterface#getEstadoAs(java.lang.Integer)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public DTOEstado getEstadoAs(Integer idEstado){
		return daoEstadosComputos.buscarPorId(idEstado);
	}
	
}
