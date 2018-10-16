package mx.ine.soa.servicio.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;




@Remote
public interface CandidatosRemote {
	public Long getCadidatos() throws SQLException;
	public Map<String,String> getInformacionCadidatos(List<Integer> idconfiguracion);
	public Long getIdCadidatos() throws SQLException;
	public Map<String, Map<String, String>> pruebaHashMap();
	public List<Map<String, String>> pruebaListHashMap();
	public List<Map<String, String>> obtenerDatosCandidatos();
	public List<Map<String, String>> obtenerDatosCandidatos(List<Integer> idconfiguracion);
	public List<Map<String, String>> obtenerDatosCandidatos(Integer id );
	public List<Map<String, String>> obtenerDatosCandidatos(Integer idcandidato,Integer idTabla );
	public List<Map<String, String>> obtenerDatosCandidatos(Map<String, String> filtros);
	public List<Map<String, String>> obtenerDatosConfiguracion();
	public List<Map<String, String>> obtenerDatosConfiguracion(List<Integer> idconfiguracion);
	public List<Map<String, String>> obtenerDatosConfiguracion(Integer id );
	public List<Map<String, String>> obtenerDatosConfiguracion(Map<String, String> filtros);
}
