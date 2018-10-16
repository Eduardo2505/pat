package mx.ine.sif.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.org.ine.servicios.utils.ApplicationContextUtils;
//import nl.captcha.Captcha;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class FiltroSeguridadComplementaria extends UsernamePasswordAuthenticationFilter {

	private static final Logger logger = Logger.getLogger(FiltroSeguridadComplementaria.class);
	private String succesUrl = "";
	private String errorUrl = "";
	private String captchaParamName = "";
	private SimpleUrlAuthenticationSuccessHandler simpleUrlSucces = new SimpleUrlAuthenticationSuccessHandler();
	private SimpleUrlAuthenticationFailureHandler simpleUrlFailure = new SimpleUrlAuthenticationFailureHandler();
	@Autowired
	private MessageSource mensaje;

	@Autowired
	@Qualifier("ldapUserSearchUID")
	private transient FilterBasedLdapUserSearch ldapUserSearch;

	@Autowired
	@Qualifier("ldapAuthoritiesPopulator")
	private transient LdapAuthoritiesPopulator ldapAuthoritiesPopulator;

	public FiltroSeguridadComplementaria() {
		super();
	}

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		String password = super.obtainPassword(request);
		if (password == null || password.isEmpty()) {
			throw new BadCredentialsException("El campo contraseña es obligatorio, favor de verificarlo");
		}
		return password;
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		String userName = super.obtainUsername(request);
		if (userName == null || userName.isEmpty()) {
			throw new BadCredentialsException("El campo Nombre de usuario es obligatorio, favor de verificarlo");
		}
		return userName;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		Authentication auth = null;
		logger.info("El usuario: " + this.obtainUsername(request) + " trata de entrar al sistema.");
		if (captchaParamName.isEmpty()) {
			throw new BadCredentialsException("El c\u00f3digo de seguridad es incorrecto, favor de introducirlo nuevamente");
		}

//		Captcha captcha = (Captcha) (request.getSession().getAttribute(Captcha.NAME));
//		String codigoSeguridad = request.getParameter(this.captchaParamName);
//
//		if (captcha == null) {
//			logger.info("El objeto captcha es nulo ... ");
//		}
//		//Obteniendo el valor del resources
//		ResourceBundleMessageSource messageSource = ((ResourceBundleMessageSource) ApplicationContextUtils.getApplicationContext().getBean("messageSource"));
//		String llave =  messageSource.getMessage("application.captcha", null, null);
//
//		//Validando captcha vacio
//		if(!"false".equals(llave))
//		{
//			if (captchaParamName.isEmpty()) {
//				//logger.debug("Debe especificar el nombre del parametro captcha");
//				throw new BadCredentialsException("El c\u00f3digo de seguridad es incorrecto, favor de introducirlo nuevamente");
//			}
//		}	
//
//		if ( ( captcha != null && captcha.isCorrect(codigoSeguridad) ) ||  ( "false".equals(llave)) ) {
//			try {
//				auth = super.attemptAuthentication(request, response);
//				if (!auth.isAuthenticated()) {
//					throw new BadCredentialsException("El usuario y/o la contraseña son incorrectos, favor de introducirlos nuevamente.");
//				}
//			} catch (BadCredentialsException e){
//				throw new BadCredentialsException("El usuario y/o la contraseña son incorrectos, favor de introducirlos nuevamente.");
//			}
//
//		} else {
//			throw new BadCredentialsException("El c\u00f3digo de seguridad es incorrecto, favor de introducirlo nuevamente");
//		}

//		List<String> listaRoles = new ArrayList<String>();
//		if (auth.getAuthorities()!= null)
//			for (GrantedAuthority aut : auth.getAuthorities()) {
//				listaRoles.add(aut.getAuthority());
//			}
//
//		if (listaRoles.isEmpty()){
//			logger.info("No tiene roles");
//			throw new BadCredentialsException("El usuario no cuenta con permisos para el sistema");
//		}

		return auth;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		logger.info("La autentificacion fue exitosa ");
		simpleUrlSucces.setDefaultTargetUrl(succesUrl);
		this.setAuthenticationSuccessHandler(simpleUrlSucces);
		super.successfulAuthentication(request, response, chain, authResult);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
					throws IOException, ServletException {

		logger.info("La autentificacion fue errónea ");
		simpleUrlFailure.setDefaultFailureUrl(errorUrl);
		this.setAuthenticationFailureHandler(simpleUrlFailure);
		super.unsuccessfulAuthentication(request, response, failed);

	}

	public String getSuccesUrl() {
		return succesUrl;
	}

	public void setSuccesUrl(String succesUrl) {
		this.succesUrl = succesUrl;
	}

	public String getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}

	public String getCaptchaParamName() {
		return captchaParamName;
	}

	public void setCaptchaParamName(String captchaParamName) {
		this.captchaParamName = captchaParamName;
	}
}
