package mx.ine.sif.mb;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;





import mx.ine.sif.dao.DAOAdminPermisosInterface;
//import mx.ine.sif.dao.DAOUsuarioInterface;
import mx.ine.sif.dto.DTOUsuarioLogin;
import mx.ine.sif.util.Constantes;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

public class MBAdminPermisos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2598941823897567805L;

	private static final Logger logger = Logger.getLogger(MBAdminPermisos.class);
	
	private List<Integer> permisos;
	//El estatus de modificar para esta sesion
	private boolean permisoConsultar;
	private boolean permisoEntrar;
	private boolean permisoTrabajarContabilidad;
	
	
	
	@Autowired
	@Qualifier("daoAdminPermisos")
	private transient DAOAdminPermisosInterface daoAdminPermisos;
	
	
	
	public void init(int modulo,int operacion){
		logger.info("Entro permisos  Modulo:" + modulo+  "  --  Operacion:"+ operacion);
		DTOUsuarioLogin usuarioLogin = (DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String rolUsuario=usuarioLogin.getRolSistema();
		permisos = new ArrayList<Integer>();
		/*TEST ONLY!! ELIMINAR ESTO ANTES DE PRODUCCIÓN*/
		/*
		try{
			String mostrarConta = Utils.mensajeProperties("testers.mostrar_id_contabilidad");
			if (mostrarConta.equals("true")  && usuarioLogin != null) {
				FacesContext.getCurrentInstance().addMessage("mensaje_testers", 
						new FacesMessage("ID OPERACIÓN: " + usuarioLogin.getIdcontabilidad()));
			}
		}catch (Exception e) {}
		/*END OF TEST*/
		
		//Consulta realizada , que obtiene lista de permisos asignados al modulo 
		setPermisos(daoAdminPermisos.getPermisosEnModulo(Constantes.IDSISTEMA, modulo, usuarioLogin.getIdRol(),2017));
		
		//Settear ids de modulo y pantalla (operacion)
		usuarioLogin.setIdModulo(modulo);
		usuarioLogin.setIdPantalla(operacion);
		
		// inicializa la varible que determia el permiso 
		
		setPermisoEntrar(false);
		for(int x :permisos){
			logger.debug("permiso---->" +x);
		}
		// Verificación del permiso  
		if(permisos.size()>0 && permisos.contains(operacion)){
			setPermisoEntrar(true);
		}
		try{
		// Variable para que indica si ya eligio una contabilidad en el modulo home para poder operar. (Consultada por modulos que requieren de una contabilidad para trabjaar)
			if(usuarioLogin.getIdContabilidad() != null ){
				setPermisoTrabajarContabilidad(true);	
		}
		}catch(Exception e){
			
		}
		logger.debug("permiso entrar ---->" +permisoEntrar);

		permisoConsultar = permisos.contains(Constantes.PERMISO_CONSULTAR)? true : false;
	
	}
	
	public String getIpAddress() {

		try {
			InetAddress address = InetAddress.getLocalHost();
			return address.getHostAddress();

		} catch (UnknownHostException e) {
			logger.error(e);
			return null;
		}

	}

	public boolean isPermisoConsultar() {
		return permisoConsultar;
	}

	public void setPermisoConsultar(boolean permisoConsultar) {
		this.permisoConsultar = permisoConsultar;
	}

	public boolean isPermisoEntrar() {
		return permisoEntrar;
	}

	public void setPermisoEntrar(boolean permisoEntrar) {
		this.permisoEntrar = permisoEntrar;
	}

	public List<Integer> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<Integer> permisos) {
		this.permisos = permisos;
	}

	public boolean isPermisoTrabajarContabilidad() {
		return permisoTrabajarContabilidad;
	}

	public void setPermisoTrabajarContabilidad(boolean permisoTrabajarContabilidad) {
		this.permisoTrabajarContabilidad = permisoTrabajarContabilidad;
	}
}
