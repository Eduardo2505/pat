package mx.ine.sif.as;

import java.util.List;

import mx.ine.sif.dto.DTOUsuarioLogin;

	public interface ASUsuarioLoginInterface {
	/**
	 * Método para obtener la información de un Usuario por medio de su nombre de usuario 
	 * @param usuario
	 * @return
	 */
	public DTOUsuarioLogin consultarUsuarioLogin(DTOUsuarioLogin dtoUsuarioLogin, String rol);
	public int consultarSujetosObligados(int idSO, int idTSO);
	public int consultarSujetosObligadosIND(int idSO, int idTSO,String usuario,int idrol);
	List<Object[]> consultaEtapasMenu(Integer ejercicio, Integer grupo);
	List<Object[]> consultaMenu(Integer ejercicio, Integer grupo);
}
