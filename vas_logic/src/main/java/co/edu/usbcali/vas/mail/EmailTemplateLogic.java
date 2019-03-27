package co.edu.usbcali.vas.mail;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.vas.model.SystemCompanyParameter;
import co.edu.usbcali.vas.model.Users;
import co.edu.usbcali.vas.model.control.ISystemCompanyParameterLogic;
import co.edu.usbcali.vas.model.control.ISystemParameterLogic;
import co.edu.usbcali.vas.security.control.Crypt;
import co.edu.usbcali.vas.utilities.Constantes;
import co.edu.usbcali.vas.utilities.FacesUtils;
import co.edu.usbcali.vas.utilities.Fechas;

@Scope("singleton")
@Service("EmailTemplateLogic")
public class EmailTemplateLogic implements IEmailTemplateLogic {
	private static final Logger log = LoggerFactory.getLogger(EmailTemplateLogic.class);

	Crypt crypt = new Crypt();

	@Autowired
	private IEmailController emailController;
	@Autowired
	private ISystemCompanyParameterLogic systemCompanyParameterLogic; 
	@Autowired
	private ISystemParameterLogic systemParameterLogic; 

	//PARAMETERS ----------------------------------------------------------------------------------------------------------------
	private String systemName;
	private String systemUrl;
	private String systemLogo;
	private String companyLogo;
	private String companyName;
	private String companyAddress;
	private String companyPhone;
	private String companyMail;
	private String companyWeb;
	private String systemUrlExternalAccess;
	
	SystemCompanyParameter companyParameter;

	
	//EMAIL FOOTER
	public String getEmailFooter() throws Exception {
		String emailFooter = "<div align='center'>"
				+ "<img src='"+this.getSystemLogo()+"' height='50' width='170'></div>"+ "<br>" 
				+ "<div align='center'>"+this.getCompanyName()+"<br>"
				+ this.getCompanyAddress()+" - "
				+ "<a href='tel:+57-314-811-6299'>"+this.getCompanyPhone()+"</a>"+" - "
				+ "<a href='mailto:"+this.getCompanyMail()+"?Subject=Contacto desde TAMS' target='_top'>"+this.getCompanyMail()+"</a><br>"
				+ "<a href='"+this.getCompanyWeb()+"' target='_blank'>"+ this.getCompanyWeb() +"</a></div>";

			return emailFooter;
	}
	//DATE CONVERSION
	public String emailDateToString() throws Exception {
		Date date = Fechas.actualDateWithTimeStamp();
		String fechaActual = "";

		fechaActual = Fechas.dateToStr(date, "dd/MM/yyyy h:mm a");

		return fechaActual;
	}
	
	// SCHEDULER
	// TEST------------------------------------------------------------------------------------------------------------------------
	@Override
	@Transactional(readOnly = false)
	public void sendMailSystemKeepAlive(String mail) throws Exception {
		//log.info("EmailTemplateLogic sendMailSystemKeepAlive");

		try {
			String subject = "System KeepAlive: " + this.getSystemName();
			String messageBody = "<br>Keep Alive notification" + "<br>" 
					+ "Fecha : " + emailDateToString() + ""+ "<br><br>"
					+ getEmailFooter();

			emailController.send(mail, subject, messageBody);
		} catch (Exception e) {
			log.error("sendMailSystemKeepAlive failed", e);
		}
	}

	
	// USERS-----------------------------------------------------------------------------------------------------------------------------
	@Override
	@Transactional(readOnly = false)
	public void sendMailSaveUser(Users entity, String passwordGenerated) throws Exception {
		log.debug("EmailTemplateLogic sendMailSaveUsuario");

		try {
			String administrador = FacesUtils.getfromSession(Constantes.SESSION_USER_NAME).toString();

			String subject = "Nuevo Usuario "+this.getSystemName()+"";
			String messageBody = "<br> El administrador" + " " + administrador + " "
					+ "ha creado un usuario para ingresar al sistema:" + "<br>" + "<br>" 
					+ "Nombre: " + entity.getFirstname() + "" + "<br>" 
					+ "Usuario: " + entity.getLogin() + "" + "<br>" 
					+ "Password: "+ passwordGenerated + "" + "<br>" 
					+ "Activo: " + entity.getActive() + ""
					+ "<br>" + "Fecha: " + emailDateToString() + "" + "<br>"
					+ "Utilice el password asignado, podrá cambiarlo posteriormente." + "<br>"
					+ "<br> Siga el enlace:" + "<br> <a href='" + this.getSystemUrl()
					+ "'> Click aquí para ingresar al sistema" + "</a><br><br>"
					+ getEmailFooter();

			emailController.send(entity.getLogin(), subject, messageBody);
		} catch (Exception e) {
			log.error("sendMailSaveUser failed", e);
		}

	}
	
	//USER DEFAULT BY CUSTOMER
	@Override
	@Transactional(readOnly = false)
	public void sendMailSaveUserDefault(Users entity, String passwordGenerated) throws Exception {
		log.debug("EmailTemplateLogic sendMailSaveUserDefault");

		try {

			String subject = "Nuevo Usuario "+this.getSystemName()+"";
			String messageBody = "<br> Se ha creado un usuario para ingresar al sistema:" + "<br>" + "<br>" 
					+ "Nombre: "+ entity.getFirstname() + "" + "<br>" 
					+ "Usuario: " + entity.getLogin() + "" + "<br>" 
					+ "Password Generado: " + passwordGenerated + "" + "<br><br>" 
					+ "Fecha: " + emailDateToString() + "" + "<br>"
					+ "Utilice el password asignado, podrá cambiarlo posteriormente." + "<br>"
					+ "<br> Siga el enlace:" + "<br> <a href='" + this.getSystemUrl()
					+ "'> Click aquí para ingresar al sistema" + "</a><br>"+ "<br>"
					+ getEmailFooter();

			emailController.send(entity.getLogin(), subject, messageBody);
		} catch (Exception e) {
			log.error("sendMailSaveUserDefault failed", e);
		}
	}
	
	/*@Override
	@Transactional(readOnly = false)
	public void sendMailNewTicket(Ticket entity, String mailToAdmin) throws Exception {
		log.debug("EmailTemplateLogic sendMailNewTicket");

		try {
			String subject = "Creación Nuevo TICKET DEALER";
			String messageBody = 
					"<br>"
					+ "Fecha Registro: " + emailDateToString() + "" + "<br>"
					+ "Asunto: "+ entity.getSubject() + "" + "<br>" 
					+ "Estado: "+ entity.getStatus() + "" + "<br>" 
					+ "Tipo: "+ entity.getTicketType().getDescription() + "" + "<br>" 
					+ "Mensaje: "+ entity.getMessage() + "" + "<br>" 
					+ "Observaciones: "+ entity.getNote() + "" + "<br>" 
					+ "Cliente: "+ entity.getCustomer().getNit().toString() + "" + "<br>" 

					+ getEmailFooter();

			emailController.send(mailToAdmin, subject, messageBody);
		} catch (Exception e) {
			log.error("sendMailNewTicket failed", e);
		}
	}*/

	
	//---------------------------------------------------
		
	@Override
	@Transactional(readOnly = false)
	public void sendMailUpdateUser(Users entity) throws Exception {
		log.debug("EmailTemplateLogic sendMailUpdateUsuario");

		try {
			String administrador = FacesUtils.getfromSession(Constantes.SESSION_USER_NAME).toString();

			String subject = "Actualización Usuario "+this.getSystemName()+"";
			String messageBody = "<br> El administrador" + " " + administrador + " "
					+ "ha actualizado su usuario para ingresar al sistema:" + "<br>" + "<br>" 
					+ "Nombre: "+ entity.getFirstname() + "" + "<br>" 
					+ "Usuario: " + entity.getLogin() + "" + "<br>" 
					+ "Tipo: " + entity.getUserType().getDescription() + "" + "<br>" 
					+ "Dpto: " + "<br>" 
					+ "Activo: "+ entity.getActive() + "" + "<br>"
					+ emailDateToString() + "<br>"
					+ "Consulte con el administrador el password asignado, podrá cambiarlo posteriormente." + "<br>"
					+ "<br> Siga el enlace:" + "<br> <a href='" + this.getSystemUrl() + "'> Click aquí para ingresar al sistema" + "</a><br>";

			emailController.send(entity.getLogin(), subject, messageBody);

		} catch (Exception e) {
			log.error("sendMailUpdateUser failed", e);
		}

	}

	// AUTENTICACION ---------------------------------------------------------------------------------
	@Override
	@Transactional(readOnly = false)
	public void sendMailRestorePassword(Users entity, String contraseñaTemp) throws Exception {
		log.debug("sendMailRestorePassword");

		try {
	
			String subject = "Restablecimiento Contrasena "+this.getSystemName()+"";
			String messageBody = "<br> Estimado(a), "+entity.getFirstname()+""+ "<br>"
					+ "La siguiente contraseña provisional fue generada:" + "<br><b><h3>" + contraseñaTemp + "</h3></b><br><br>"
					+ "Siga el enlace para ingresar al sistema, posteriormente podrá cambiar su contraseña desde "
					+ "'mi cuenta', en el menú superior."+ "<br>"
					+ "Fecha: "+emailDateToString() + "<br>"
					+ "<br> <a href='" + this.getSystemUrl() + "'> Click aquí para ingresar al sistema"
					+ "</a><br><br>"
					+ getEmailFooter();
			
			emailController.send(entity.getLogin(), subject, messageBody);

		} catch (Exception e) {
			log.error("sendMailRestorePassword failed", e);
		}

	}

	@Transactional(readOnly = false)
	public void sendMailUpdateUserAccount(Users entity) throws Exception {
		log.debug("sendMensajeActualizacionDatos user successful");

		try {

			String subject = "Actualizacion de datos "+this.getSystemName()+"";
			String messageBody = "<br>Estimado(a) " + entity.getFirstname() + "<br></br>"
					+ "<br>Se han actualizado los datos de su cuenta " + entity.getLogin() + ","
					+ "Si no reconoces esta actividad :"  + "<br>"
					+ "-Cambia tu password"  + "<br>"
					+ "-Comunicate con el administrador"  + "<br>"
					+ emailDateToString() + "<br>"
					+ "<br>para ingresar has click en el siguiente enlace:" 
					+ "<br> <a href='" + this.getSystemUrl() + "'> Ingresar a la aplicación </a><br><br>"
					+ getEmailFooter();

			emailController.send(entity.getLogin(), subject, messageBody);

		} catch (Exception e) {
			log.error("sendMailUpdateUserAccount failed", e);
		}

	}
	

	//GETTERS AND SETTERS------------------------------------------------------------

	//SYSTEM PARAMETERS
	public String getSystemUrl() {
		return systemUrl;
	}


	public void setSystemUrl(String systemUrl) {
		this.systemUrl = systemUrl;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	
	//SYSTEM CONFIG-------------------------------
	@PostConstruct
	public SystemCompanyParameter getCompanyParameter() {
			//log.info("getCompanyParameter");
		try {
				//companyParameter = systemCompanyParameterLogic.getSystemCompanyParameterByCode("MAIN_PARAMS");
				
				if(companyParameter != null){
					this.systemName = companyParameter.getSystemName().trim();
					this.systemUrl = companyParameter.getSystemUrl().trim();
					this.systemLogo = companyParameter.getSystemLogoUrl().trim();
					this.companyLogo = companyParameter.getCompanyLogoUrl().trim();
					this.companyName = companyParameter.getCompanyName().trim();
					this.companyAddress = companyParameter.getCompanyAddress().trim();
					this.companyPhone = companyParameter.getCompanyPhone().trim();
					this.companyMail = companyParameter.getCompanyEmail().trim();
					this.companyWeb = companyParameter.getCompanyWeb().trim();
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return companyParameter;
	}
	public void setCompanyParameter(SystemCompanyParameter companyParameter) {
		this.companyParameter = companyParameter;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getCompanyMail() {
		return companyMail;
	}

	public void setCompanyMail(String companyMail) {
		this.companyMail = companyMail;
	}

	public String getCompanyWeb() {
		return companyWeb;
	}

	public void setCompanyWeb(String companyWeb) {
		this.companyWeb = companyWeb;
	}
	public String getSystemLogo() {
		return systemLogo;
	}
	public void setSystemLogo(String systemLogo) {
		this.systemLogo = systemLogo;
	}
	public String getSystemUrlExternalAccess() {
		try {
			//systemUrlExternalAccess = systemParameterLogic.getParamByCodeTexValue("EMPLOYEE_EXTERNAL_ACCESS");
			if(systemUrlExternalAccess.equals("") != true){
				return systemUrlExternalAccess;
			}
		} catch (Exception e) {
			log.error("getSystemUrlExternalAccess",e);
		}
		return null;
	}
	public void setSystemUrlExternalAccess(String systemUrlExternalAccess) {
		this.systemUrlExternalAccess = systemUrlExternalAccess;
	}

	
	

}
