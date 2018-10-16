package mx.ine.sif.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mx.ine.sif.dto.DTOUsuarioLogin;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class PreAuthenticationFilter extends
		AbstractPreAuthenticatedProcessingFilter {

	private static final Logger logger = Logger
			.getLogger(PreAuthenticationFilter.class);

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		DTOUsuarioLogin user = null;
		try {
//			logger.info("PRE");
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("SIN_ROL"));
			
			
			
			logger.info("********************: " + authorities);
			
			logger.info("********************: " + request.getRemoteUser());
			
			user = new DTOUsuarioLogin(request.getRemoteUser(), "", true, true, true, true, authorities);			
		} catch (Exception e) {
			throw new AuthenticationServiceException("Error....", e);
		}

		return user;
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return "NA";
	}
}
