package mx.ine.sif.as.impl;

import java.util.List;

import mx.ine.sif.as.ASUsuarioLoginInterface;
import mx.ine.sif.dao.DAOUsuarioLoginInterface;
import mx.ine.sif.dto.DTOUsuarioLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype")
@Service("asUsuarioLogin")
@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
public class ASUsuarioLogin implements  ASUsuarioLoginInterface{
	
	@Autowired
	@Qualifier("daoUsuarioLogin")
	private DAOUsuarioLoginInterface daoUsuarioLogin;

	@Override
	public DTOUsuarioLogin consultarUsuarioLogin(DTOUsuarioLogin dtoUsuarioLogin, String rol) {		
		return daoUsuarioLogin.DAOconsultarUsuarioLogin(dtoUsuarioLogin,rol);
	}

	@Override
	public int consultarSujetosObligados(int idSO, int idTSO) {
		return daoUsuarioLogin.consultarSujetosObligados(idSO,idTSO);
	}

	@Override
	public int consultarSujetosObligadosIND(int idSO, int idTSO,String usuario, int idrol) {
		return daoUsuarioLogin.consultarSujetosObligadosIND(idSO, idTSO, usuario, idrol);
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Object[]> consultaEtapasMenu(Integer ejercicio, Integer grupo){
		return daoUsuarioLogin.consultaEtapasMenu(ejercicio, grupo);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Object[]> consultaMenu(Integer ejercicio, Integer grupo){
		return daoUsuarioLogin.consultaMenu(ejercicio, grupo);
	}


}
