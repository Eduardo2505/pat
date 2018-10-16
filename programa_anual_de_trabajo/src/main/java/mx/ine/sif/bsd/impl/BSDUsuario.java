package mx.ine.sif.bsd.impl;

import java.util.List;

import mx.ine.sif.as.ASUsuarioInterface;
import mx.ine.sif.bsd.BSDUsuarioInterface;
import mx.ine.sif.dto.DTOUsuario;
import mx.org.ine.servicios.dto.db.DTODistrito;
import mx.org.ine.servicios.dto.db.DTOEstado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("bsdUsuario")
@Scope("prototype")
public class BSDUsuario implements BSDUsuarioInterface{
	
	/*
	 * Propiedades inyectadas 
	 */
	
	@Autowired
	@Qualifier("asUsuario")	
	private ASUsuarioInterface asUsuario;
	

	@Override
	public List<DTODistrito> getDistritosPorEstadoBsd(Integer idEstado){
		return asUsuario.getDistritosPorEstadoAs(idEstado);
	}

	@Override
	public DTOEstado getEstadoBsd(Integer idEstado){
		return asUsuario.getEstadoAs(idEstado);
	}
	
}
