package co.edu.usbcali.vas.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import co.edu.usbcali.vas.model.Users;
import co.edu.usbcali.vas.presentation.businessDelegate.IBusinessDelegatorView;
import co.edu.usbcali.vas.security.control.Crypt;

@Scope("singleton")
@Component("authProvider")
public class AuthProvider implements AuthenticationProvider {
	private static final Logger log = LoggerFactory.getLogger(AuthProvider.class);

	private String login;
	private String password;
	private String userTypeCode;
	private Authentication auth;
	private Users user;
	private Crypt crypt = new Crypt();

	@Autowired
	private IBusinessDelegatorView businessDelegatorView;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		log.info("AuthProvider");
		this.login = authentication.getName();
		this.password = authentication.getCredentials().toString();
		this.auth = null;
		this.userTypeCode = null;
		this.user = null;
		
		String mensaje = "Usuario o Contraseña inválida, Intentalo de nuevo!";
		String securePassword = crypt.securePassword(password);

		try {
			this.user = businessDelegatorView.authenticate(login, password);
			
			if(this.user == null){
				throw new AuthenticationServiceException(mensaje);
			}
			if (this.user.getPassword().trim().equals(securePassword) == false) {
 		 		throw new Exception(mensaje);
 		 	}
			if (this.user.getActive().booleanValue() == false) {
				throw new AuthenticationServiceException("El usuario se encuentra inactivo, consulte con su administrador");
			}
		} catch (Exception e1) {
			log.error("Authentication Error ");
			throw new AuthenticationServiceException(e1.getMessage());
		}

		try {
			
			this.userTypeCode = user.getUserType().getCode();
			
			if (this.userTypeCode.toString().trim().equals("SYSTEM")) {

				grantedAuths("ROLE_SYSTEM", login, password);
				return auth;

			} else if (this.userTypeCode.toString().trim().equals("ADMIN")) {

				grantedAuths("ROLE_ADMIN", login, password);
				return auth;

			}else if (this.userTypeCode.toString().trim().equals("USER")) {

				grantedAuths("ROLE_USER", login, password);
				return auth;

			}


		} catch (Exception e) {
			throw new AuthenticationServiceException(mensaje);

		}
		return auth;
	}

	// grantedAuths
	public void grantedAuths(String rol, String login, String password) throws Exception {
		//log.info("AuthProvider grantedAuths");

		try {
			final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
			grantedAuths.add(new SimpleGrantedAuthority(rol));
			final UserDetails principal = new User(login, password, grantedAuths);
			auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);

		} catch (RuntimeException e) {
			log.error("grantedAuths Error ");
		}

	}

	// -------------------------------------------------------------------------------------
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
