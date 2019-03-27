package co.edu.usbcali.vas.security.control;

import co.edu.usbcali.vas.model.Users;


public interface ISecurityLogic {
   
    //AUTH-----------------------------------------------------------------------
	public Users authenticate(String usuLogin, String usuPassword) throws Exception;
	//SEGURIDAD
	public String generatePassword() throws Exception;
	public String generateToken() throws Exception;
	public String generateSecretKey() throws Exception;
	public String generateRandomString() throws Exception;
	public String generateHashCode() throws Exception;
	//USERS
	public Boolean validateUserLogin(String login, String id) throws Exception;
	public Users getUserByLogin(String usuLogin) throws Exception;
	public void restoreUserPassword(String usuLogin) throws Exception;
	public void userSessionStatusUpdateOffline(Users entity) throws Exception;
	public void userSessionStatusUpdateOnline(Users entity) throws Exception;
	



	
}
