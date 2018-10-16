/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.ine.sif.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mx.ine.sif.as.ASUsuarioLoginInterface;
import mx.ine.sif.dto.DTOUsuarioLogin;
import mx.ine.sif.util.Constantes;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

/**
 *
 * @author martha
 */
public class CustomUserDetailsMapper implements UserDetailsContextMapper{
	@Autowired
	@Qualifier("asUsuarioLogin")
	private transient ASUsuarioLoginInterface asUsuarioLoginInterface;

	private static final Logger logger = Logger.getLogger(CustomUserDetailsMapper.class);
	private List<Integer> rolesSOasociado = new ArrayList<Integer>();
	private List<Integer> rolesCOASasociado = new ArrayList<Integer>();
	private List<Integer> rolesIndependientes = new ArrayList<Integer>();
	private List<Integer> rolesCdatos= new ArrayList<Integer>();




	@Override
	public UserDetails mapUserFromContext(DirContextOperations arg0,
			String userName, Collection<? extends GrantedAuthority> arg2) {  

		// Roles asociados con un partido Politico
		try {
			rolesSOasociado.add(Constantes.Roles.ROL_CANDIDATO.getIdRol());
			rolesSOasociado.add(Constantes.Roles.ROL_CANDIDATORP.getIdRol());
			rolesSOasociado.add(Constantes.Roles.ROL_CAPTURISTA.getIdRol());
			rolesSOasociado.add(Constantes.Roles.ROL_PRECANDIDATO.getIdRol());
			rolesSOasociado.add(Constantes.Roles.ROL_ADMINSO.getIdRol());
			rolesSOasociado.add(Constantes.Roles.ROL_REPRESENTANTEFINANZAS.getIdRol());
			rolesSOasociado.add(Constantes.Roles.ROL_REPRESENTANTEFINANZAS_JL.getIdRol());

			// Roles asociados con una Coalicion

			rolesCOASasociado.add(Constantes.Roles.ROL_ADMINSO.getIdRol());
			rolesCOASasociado.add(Constantes.Roles.ROL_REPRESENTANTEFINANZAS.getIdRol());
			rolesCOASasociado.add(Constantes.Roles.ROL_REPRESENTANTEFINANZAS_JL.getIdRol());

			//independientes 
			rolesIndependientes.add(Constantes.Roles.ROL_CANDIDATO_IND.getIdRol());
			rolesIndependientes.add(Constantes.Roles.ROL_ASPIRANTE.getIdRol());


			//Candidatos-precandidatos 
			rolesCdatos.add(Constantes.Roles.ROL_CANDIDATO_IND.getIdRol());
			rolesCdatos.add(Constantes.Roles.ROL_ASPIRANTE.getIdRol());
			rolesCdatos.add(Constantes.Roles.ROL_CANDIDATO.getIdRol());
			rolesCdatos.add(Constantes.Roles.ROL_PRECANDIDATO.getIdRol());
			rolesCdatos.add(Constantes.Roles.ROL_CANDIDATORP.getIdRol());
			
			rolesCdatos.add(Constantes.Roles.ROL_AUDITOREXTERNO.getIdRol());


			// Llena los campos en la session 

			String rol = getRolSistema(arg2);

			DTOUsuarioLogin user = new DTOUsuarioLogin(userName, "", true, true, true, true, arg2);

			//String idEstado = ((arg0.getAttributes().get("idEstado")!= null )? arg0.getAttributes().get("idEstado").toString().split(": ")[1]:"0");
			String idEstado = "0";
            if (arg0.getAttributes().get("idEstado")!= null){
            	if (Integer.parseInt(arg0.getAttributes().get("idEstado").toString().split(": ")[1]) < 33){
            		idEstado = arg0.getAttributes().get("idEstado").toString().split(": ")[1];
            	}
            }
			String idDistrito = (String) ((arg0.getAttributes().get("idDistrito")!= null )? arg0.getAttributes().get("idDistrito").toString().split(": ")[1]:"0");
			String nombreUsuario = (arg0.getAttributes().get("givenName")!= null )? arg0.getAttributes().get("givenName").toString().split(": ")[1]:null;
			String apellidosUsuario = (arg0.getAttributes().get("sn")!= null )? arg0.getAttributes().get("sn").toString().split(": ")[1]:null;
			String rfc = (arg0.getAttributes().get("RFC")!= null )? arg0.getAttributes().get("RFC").toString().split(": ")[1]:null;
			String curp = (arg0.getAttributes().get("CURP")!= null )? arg0.getAttributes().get("CURP").toString().split(": ")[1]:null;
			String nombreUbicacion = (arg0.getStringAttribute("OU")!= null )?arg0.getStringAttribute("OU"):null;
			Integer idSO  = (arg0.getStringAttribute("idSO")!= null )?Integer.parseInt(arg0.getStringAttribute("idSO")):null;
			Integer idTSO =(arg0.getStringAttribute("idTipoSO")!= null )?Integer.parseInt(arg0.getStringAttribute("idTipoSO")):null;
			Integer IdESO = (arg0.getStringAttribute("idEntidadSO")!= null )?Integer.parseInt(arg0.getStringAttribute("idEntidadSO")):null;
			//Integer IdSOCIND = (arg0.getStringAttribute("IDSOSIF")!= null )?Integer.parseInt(arg0.getStringAttribute("IDSOSIF")):null;



			user.setUsuario(userName);
			user.setNombreUbicacion(nombreUbicacion);
			user.setIdEstado(Integer.parseInt(idEstado));
			user.setIdDistrito(Integer.parseInt(idDistrito));
			user.setNombreUsuario(nombreUsuario);
			user.setApellidosUsuario(apellidosUsuario);
			user.setRfc(rfc);
			user.setCurp(curp);  

			if(user != null && rol !=null ){
				try{

					//Consulta de datos a tablas de Usuario
					user = asUsuarioLoginInterface.consultarUsuarioLogin(user,rol.toUpperCase());

					//Consultar Partido Politico 

					if(rolesSOasociado.contains(user.getIdRol())){
							user.setIdSO_sif(asUsuarioLoginInterface.consultarSujetosObligados(idSO, idTSO));
					}else if(rolesIndependientes.contains(user.getIdRol())){

						user.setIdSO_sif(asUsuarioLoginInterface.consultarSujetosObligadosIND(idSO, idTSO,user.getUsername(),user.getIdRol()));


					}

					user.setRolSistema(Constantes.Roles.getRol(user.getIdRol()));
					user.setIdEstadoVisibilidad(user.getIdEstado());
					user.setIdRol(Constantes.Roles.getIdRol(rol));

				}catch(Exception e){
					logger.error("Error de inicio de session Usuario:  " + user);
					logger.error("Error de inicio de session: " + e.getMessage());
				}
			}


			user.setIdSistema(Constantes.IDSISTEMA);
			user.setIdVersion(Constantes.IDVERSION);
			user.setIdProceso(Constantes.ORDINARIO);
			user.setIdEntidadSO((arg0.getStringAttribute("idEntidadSO")!= null )?Integer.parseInt(arg0.getStringAttribute("idEntidadSO")):null);
			user.setIdTipoSO((arg0.getStringAttribute("idTipoSO")!= null )?Integer.parseInt(arg0.getStringAttribute("idTipoSO")):null);
			user.setIdSO((arg0.getStringAttribute("idSO")!= null )?Integer.parseInt(arg0.getStringAttribute("idSO")):null);

			if (rol == null) {
				logger.error("Error de inicio de session rol NULO:  " + user);
				user.setIdRol(-1);
			}

			return user;
		}catch(Exception e){
			logger.error("Error de inicio de session Usuario: " +e);
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public void mapUserToContext(UserDetails arg0, DirContextAdapter arg1) {	

	}

	private String getRolSistema(Collection<? extends GrantedAuthority> arg2){       	
		for (GrantedAuthority grantedAuthority : arg2) {    		
			Pattern p = Pattern.compile("ROLE_(.*)");
			Matcher m = p.matcher(grantedAuthority.getAuthority());

			String rol = null;
			if (m.find())
				rol=m.group(1);

			Integer idRol = Constantes.Roles.getIdRol(rol);

			if(idRol!=null){
				return Constantes.Roles.getRol(idRol);
			}
		}    	
		return null;
	}

}
