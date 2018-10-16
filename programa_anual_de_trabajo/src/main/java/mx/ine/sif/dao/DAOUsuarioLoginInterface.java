package mx.ine.sif.dao;

import java.util.List;

import mx.ine.sif.dto.DTOUsuarioLogin;

public interface DAOUsuarioLoginInterface {	
	
	public DTOUsuarioLogin DAOconsultarUsuarioLogin(DTOUsuarioLogin dtoUsuarioLogin, String rol);
	public int consultarSujetosObligados(int idSO, int idTSO);
	public int consultarSujetosObligadosIND(int idSO, int idTSO,String usuario,int idrol);
	List<Object[]> consultaMenu(Integer ejercicio, Integer grupo);
	List<Object[]> consultaEtapasMenu(Integer ejercicio, Integer grupo);
}
