package mx.ine.sif.bsd;

import java.util.List;

import mx.ine.sif.dto.DTOUsuario;
import mx.org.ine.servicios.dto.db.DTODistrito;
import mx.org.ine.servicios.dto.db.DTOEstado;

public interface BSDUsuarioInterface {
	
	/**
	 * Método que obtiene la lista de Distritos del estado que se pasa como parametro
	 *  
	 * @param idEstado
	 * @returns
	 * @author Alfredo González
	 * @since 09/06/2014
	 * @versión 1.0
	 */
	public List<DTODistrito> getDistritosPorEstadoBsd(Integer idEstado);
	
	/**
	 * Método para obtener la información de un estado a partir de su identificador
	 * @param idEstado
	 * @return
	 */
	public DTOEstado getEstadoBsd(Integer idEstado);
	

}
