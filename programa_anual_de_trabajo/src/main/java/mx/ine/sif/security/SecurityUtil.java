package mx.ine.sif.security;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.ine.sif.dto.DTOUsuarioLogin;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component("securityUtil")
public class SecurityUtil implements  Serializable{

	private static final long serialVersionUID = 8370763206241802940L;
	private static final Logger logger = Logger.getLogger(SecurityUtil.class);
	
	public SecurityContext getSecurityContext(){
		return SecurityContextHolder.getContext();
	}
	
	public Authentication getAuthentication(){
		SecurityContext sc = getSecurityContext();
		if(sc!=null)
			return sc.getAuthentication();
		return null;
	}
	
	public void setAuthentication( Authentication auth ){
		SecurityContext sc = getSecurityContext();
		sc.setAuthentication( auth );
	}
	
	public UserDetails getPrincipal(){
		Authentication auth = getAuthentication();
		if(auth!=null)
			return (UserDetails) auth.getPrincipal();
		return null;
	}
	
	
	public DTOUsuarioLogin getDTOUsuarioLogin(){
		Authentication auth=getAuthentication();
		if(auth!=null){
			Object obj=auth.getPrincipal();
			if(obj instanceof DTOUsuarioLogin){
				return (DTOUsuarioLogin)obj;
			}
		}
		return null;
	}
	
//	public void changeRole(ROLES_SISTEMA  newRole){
//		
//		List<GrantedAuthority> newRoleList = new ArrayList<GrantedAuthority>(); 
//		
//		GrantedAuthority newGrAut = new SimpleGrantedAuthority( newRole.getRol() );
//		newRoleList.add(newGrAut);
//		
//		DTOUsuarioLogin dtoUsr =  this.getDTOUsuarioLogin();
//		dtoUsr.setIdRol(newRole.getIdRol());
//		
//		Authentication newAuth =  new UsernamePasswordAuthenticationToken( dtoUsr, null, newRoleList );
//		this.setAuthentication( newAuth );
//	}
		
	public String getUsuario(){
		DTOUsuarioLogin dtoUsuarioLogin = getDTOUsuarioLogin();
		return dtoUsuarioLogin.getUsuario();
	}
	
	public String getNombreUsuario(){
		DTOUsuarioLogin dtoUsuarioLogin = getDTOUsuarioLogin();
		return dtoUsuarioLogin.getNombreUsuario();
	}

//	public boolean hasRole(final ROLES_SISTEMA role) {
//		final DTOUsuarioLogin usuarioLogin = this.getDTOUsuarioLogin();
//		if (usuarioLogin != null) {
//			List<Integer> roleList = usuarioLogin.getAllRoles();
//			for (Integer idRole : roleList) {
//				if (idRole.equals(role.getIdRol())){
//					return true;
//				}
//			}
//		}
//		return false;
//	}
	
//	public ROLES_SISTEMA getActualRol() {
//		final DTOUsuarioLogin dtoUsuarioLogin = getDTOUsuarioLogin();
//		ROLES_SISTEMA rol;
//		if (dtoUsuarioLogin == null) {
//			rol = null;
//		} else {
//			
//			if(dtoUsuarioLogin.getAllRoles().size()==1){
//				changeRole(ROLES_SISTEMA.getEnumById(dtoUsuarioLogin.getAllRoles().get(0)));
//			}
//			
//			rol = ROLES_SISTEMA.getEnumById(dtoUsuarioLogin.getIdRol());
//		}
//		return rol;
//	}
//	
//	public List<ROLES_SISTEMA> allRoles(){
//		
//		List<ROLES_SISTEMA> listRole = new ArrayList<ROLES_SISTEMA>();
//		for (Integer role : getDTOUsuarioLogin().getAllRoles()) {
//			listRole.add(ROLES_SISTEMA.getEnumById(role));
//		}
//		return listRole;
//	}
	public String getProjectUrl(){
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return req == null ? null : req.getRequestURL().toString().replace(req.getRequestURI(), "") + req.getContextPath();
	}


}
