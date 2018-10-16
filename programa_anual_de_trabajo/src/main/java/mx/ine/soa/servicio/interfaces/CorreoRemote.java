package mx.ine.soa.servicio.interfaces;

import java.util.List;

import javax.ejb.Remote;

import mx.ine.soa.respuesta.dto.CorreoDTO;

//import mx.ine.soa.respuesta.dto.CorreoDTO;

@Remote
public interface CorreoRemote {
	public boolean Envia(CorreoDTO correoDTO);
	public boolean Envia(List<CorreoDTO> correoDTO);
}
