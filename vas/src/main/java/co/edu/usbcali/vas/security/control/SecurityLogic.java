package co.edu.usbcali.vas.security.control;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import org.jboss.aerogear.security.otp.api.Base32;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.vas.dataaccess.dao.IUsersDAO;
import co.edu.usbcali.vas.mail.IEmailTemplateLogic;
import co.edu.usbcali.vas.model.Users;
import co.edu.usbcali.vas.model.control.ISystemLogLogic;
import co.edu.usbcali.vas.utilities.Constantes;
import co.edu.usbcali.vas.utilities.FacesUtils;


@Scope("singleton")
@Service("SecurityLogic")
public class SecurityLogic implements ISecurityLogic {
	private static final Logger log = LoggerFactory.getLogger(SecurityLogic.class);

	Crypt crypt = new Crypt();

	@Autowired
	private IUsersDAO usersDAO;
	
	@Autowired
	private IEmailTemplateLogic emailTemplateLogic;
	
	@Autowired
	private ISystemLogLogic systemLogLogic;
	
	
	// USER AUTHENTICATE-------------------------------------------------------------------------------------

 	@Override
 	@Transactional(readOnly = true)
 	public Users authenticate(String usuLogin, String usuPassword) throws Exception {
 		//log.info("SecurityLogic authenticate");
 		
 		String mensaje = "Autenticación Inválida, Inténtalo de nuevo";
 		String securePassword = crypt.securePassword(usuPassword);
 		String login = usuLogin.toString().trim();
 		Users user = null;
 		String userType = "";

 		try {

 	 		user = usersDAO.getUserByLogin(login);
 
			if (user != null) {
				userType = user.getUserType().getCode();
				log.info("authenticate userType: "+userType);
				
				if (user.getActive().booleanValue() == false) {
					throw new Exception("Su usuario esta inactivo, favor verifique con su administrador");
				}
				if (user.getPassword().trim().equals(securePassword) == false) {
					throw new Exception(mensaje);
				}
				
				//UPLOAD SESSION DATA /USER/CUSTOMER/PLAN
				this.data_session_upload(user);


			} else {
				throw new Exception(mensaje);
			}
			
		} catch (Exception e) {
			log.error("SecurityLogic authentication failed");
			throw e;
		}

 		return user;

 	}
 	
 	//UPLOAD SESSION DATA /USER/CUSTOMER/PLAN
 	public void data_session_upload(Users user) throws Exception {
 		//log.info("SecurityLogic data_session_upload");
 		try {
 			if(user != null){
 	 			//USER
 	 			FacesUtils.putinSession(Constantes.SESSION_USER_OBJECT, user);
 	 			FacesUtils.putinSession(Constantes.SESSION_USER_ID, user.getId());
 	 			FacesUtils.putinSession(Constantes.SESSION_USER_TYPE, user.getUserType().getCode());
 	 			FacesUtils.putinSession(Constantes.SESSION_USER_LOGIN, user.getLogin());
 	 			FacesUtils.putinSession(Constantes.SESSION_USER_NAME, user.getFirstname());
 	 			
 			}
 			
 		}catch (Exception e) {
 			log.error("SecurityLogic data_session_upload failed",e);
 		}
 	}

	public void data_session_download() throws Exception {
		//log.info("SecurityLogic data_session_download");
		try {

			// bajar datos de la sesion
			String txtName = FacesUtils.getfromSession(Constantes.SESSION_USER_NAME).toString();
			String txtRol = FacesUtils.getfromSession(Constantes.SESSION_USER_TYPE).toString();
			log.info("txtName" + txtName);
			log.info("txtRol" + txtRol);

		} catch (RuntimeException e) {
			log.error(e.getMessage(), e);
			throw e;
		}

	}

	// VALIDATE USERS--------------------------------------------------------------------------------

	@Override
	@Transactional(readOnly = true)
	public Users getUserByLogin(String usuLogin) throws Exception {

		Users usuario = null;
		String login = usuLogin.toString().trim();

		try {

			if (login.toString().trim().equals("") == true) {
				throw new Exception("Debe ingresar un Email");
			}
			usuario = usersDAO.getUserByLogin(login);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}

		return usuario;

	}

	@Override
	@Transactional(readOnly = true)
	public Boolean validateUserLogin(String login, String id) throws Exception {

		Users usuarioConsultado = null;
		try {

			usuarioConsultado = getUserByLogin(login);
			if (id.equals(usuarioConsultado.getId().toString().trim()) != true) {
				return false;
			}

		} catch (Exception e) {
			log.error("validaLogin Usuario failed", e);
		}
		return true;
	}

	// GENERAR CONTRASEÑA RANDOM---------------------------------------------------------------------------------------------------------
	@Override
	public String generatePassword() throws Exception {
		String contraseñaTemp = "";
		try {
			String cadenaAleatoria = "";
			long milis = new java.util.GregorianCalendar().getTimeInMillis();
			Random r = new Random(milis);
			int i = 0;
			while (i < 8) {
				char c = (char) r.nextInt(255);
				if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
					cadenaAleatoria += c;
					i++;
				}
			}
			contraseñaTemp = cadenaAleatoria;

		} catch (Exception e) {
			log.error("generarContraseña Usuario failed", e);
		}
		return contraseñaTemp;
	}

	@Override
	public String generateRandomString() throws Exception {

		String randomStr = "";
		try {
			long milis = new java.util.GregorianCalendar().getTimeInMillis();
			Random r = new Random(milis);
			int i = 0;
			while (i < 32) {
				char c = (char) r.nextInt(255);
				if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
					randomStr += c;
					i++;
				}
			}

		} catch (Exception e) {
			log.error("generarContraseña Usuario failed", e);
		}
		return randomStr.toLowerCase();

	}

	@Override
	public String generateToken() throws Exception {
		String token = "";
		try {

			long milis = new java.util.GregorianCalendar().getTimeInMillis();
			Random r = new Random(milis);
			int i = 0;
			while (i < 7) {
				char c = (char) r.nextInt(255);
				if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
					token += c;
					i++;
				}
			}

		} catch (Exception e) {
			log.error("generarToken failed", e);
		}
		return token;

	}

	// GENERAR SECRETKEY-------------------------------------------------------------------------------
	@Override
	public String generateSecretKey() throws Exception {
		String secretKey = "";

		try {
			SecureRandom random = new SecureRandom();
			String numeroRandom = new BigInteger(64, random).toString(32);
			// Convertimos el secret key en byte
			byte[] hash = new byte[0];
			hash = numeroRandom.toUpperCase().getBytes();
			// Convertimos el arreglo de byte en la clase Base32 del algoritmo
			secretKey = Base32.encode(hash);

		} catch (Exception e) {
			log.error("generarSecretKey failed", e);
		}
		return secretKey.trim();

	}

	// GENERAR HASHCODE
	@Override
	public String generateHashCode() throws Exception {
		String hashCode = "";
		try {

			String secretKey_first = generateRandomString().toLowerCase();
			String randomStr = generateSecretKey();
			String secretKey_sec = crypt.securePassword(randomStr);

			SecureRandom random_third = new SecureRandom();
			String numeroRandom_third = new BigInteger(64, random_third).toString(64);
			// Convertimos el secret key en byte
			byte[] hash_third = new byte[0];
			hash_third = numeroRandom_third.getBytes();
			// Convertimos el arreglo de byte en la clase Base32 del algoritmo
			String secretKey_third = Base32.encode(hash_third).toLowerCase();

			hashCode = secretKey_first + "-" + secretKey_sec + "-" + secretKey_third;
		} catch (Exception e) {
			log.error("generarHashCode failed", e);
		}
		return hashCode;

	}
	
	//CONSULTAR USUARIO POR LOGIN REESTABLECER CONTRASEÑA------------------------------------------------------------------
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void restoreUserPassword(String usuLogin) throws Exception {
		//log.info("restoreUserPassword");
		Users entity = null;
	 		String mensaje = "El mail no esta registrado en el sistema, intente de nuevo";
	 		
	 		try {
	 			
	 			if (usuLogin.toString().trim().equals("") == true) {
	 				throw new RuntimeException("Debe ingresar un Email");
	 			}
	 			
	 			entity = getUserByLogin(usuLogin);
	 			
	 			if (entity != null) {
	 				
	 				if (entity.getActive().booleanValue() == false) {
		 				throw new RuntimeException("Su usuario esta inactivo, favor verifique con su administrador");
		 			}
	 				
	 				String contraseñaTemp = generatePassword();
		 			
		 			// Cifrado clave------------------------------------------------------------------
		 			String usuClaveCript = crypt.securePassword(contraseñaTemp);			
		 			entity.setPassword(usuClaveCript);
		 			
		 			usersDAO.update(entity);
		 	
		 			//Enviar mail
		 			emailTemplateLogic.sendMailRestorePassword(entity, contraseñaTemp);
	 				
	 				
				}else{
					throw new RuntimeException(mensaje);
				}
	 		
	 		} catch (RuntimeException e) {
	 			log.error(e.getMessage(), e);
	 		}

	 	}
	
	//STATUS ON LINE
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void userSessionStatusUpdateOnline(Users userOnSession) throws Exception {
		//log.info("userSessionStatusUpdateOnline");
	 		try {

	 			if (userOnSession != null) {
	 				
		 			usersDAO.update(userOnSession);
	
				}
	 		} catch (Exception e) {
	 			log.error(e.getMessage(), e);
	 		}
	}
	//STATUS OFF LINE
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void userSessionStatusUpdateOffline(Users userOnSession) throws Exception {

	 		try {

	 			if (userOnSession != null) {
	 				userOnSession.setOnSession(false);
		 			usersDAO.update(userOnSession);
			
				}
	 		} catch (Exception e) {
	 			log.error(e.getMessage(), e);
	 		}
	}
	
	

}
