package co.edu.usbcali.vas.mail;

import co.edu.usbcali.vas.model.Users;

public interface IEmailTemplateLogic {

	// USERS
	public void sendMailSaveUser(Users entity, String passwordGenerated) throws Exception;

	public void sendMailUpdateUser(Users entity) throws Exception;

	public void sendMailRestorePassword(Users entity, String contraseñaTemp) throws Exception;

	public void sendMailSystemKeepAlive(String mail) throws Exception;

	public void sendMailSaveUserDefault(Users entity, String passwordGenerated) throws Exception;


}
