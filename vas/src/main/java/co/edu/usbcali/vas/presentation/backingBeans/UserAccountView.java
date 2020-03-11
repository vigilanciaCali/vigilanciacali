package co.edu.usbcali.vas.presentation.backingBeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.password.Password;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.vas.model.Users;
import co.edu.usbcali.vas.presentation.businessDelegate.IBusinessDelegatorView;
import co.edu.usbcali.vas.utilities.Constantes;
import co.edu.usbcali.vas.utilities.FacesUtils;
import co.edu.usbcali.vas.utilities.Utilities;

@ManagedBean
@ViewScoped
public class UserAccountView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(UserAccountView.class);

	private String strName;
	private String strLogin;
	private Password txtPassword;
	private Password txtValidatePassword;

	private Integer userId;
	private Users entity;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;
	
	public UserAccountView() {
		super();

	}
	public String action_close_dialog() {
 		try {
			FacesUtils.hideDialog("dlgAccount");
		} catch (Exception e) {
			e.printStackTrace();
		}
 		
 		return "";
 	}
    
    public String show_dialog() {
		try {
			FacesUtils.showDialog("dlgAccount");
		} catch (Exception e) {
			log.error(e.getMessage());
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	@PostConstruct
	public void loadData() {
		//log.info("UserAccountView loadData");
		try {
			entity = (Users) FacesUtils.getfromSession(Constantes.SESSION_USER_OBJECT);
			strName = (entity.getFirstname());
			strLogin = (entity.getLogin());
		

		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error obteniendo datos de sesión");
			log.error("UserAccountView loadData failed",e);
		}
	}

	/*
	 * Actions Methods
	 */

	public String action_clear_password() {
		txtPassword = null;
		txtValidatePassword = null;

		return "";
	}

	public String action_modify_account() throws Exception {
		log.info("UserAccountView action_modify_account");

		try {
			if (strName == null || strName.toString().trim().equals("")) {
				throw new RuntimeException("Debe ingresar un nombre");
			} 
			/*if (!Utilities.isOnlyLetters(strName.toString().trim())) {
				throw new RuntimeException("El nombre solo puede contener letras");
			}*/

			if (!Utilities.validateEmailAddress(strLogin.toString().trim()) 
					|| strLogin == null || strLogin.toString().trim().equals("")) {
				throw new RuntimeException("Ingrese un Email válido");
			}
			entity.setFirstname(strName.toString().trim());
			entity.setLogin(strLogin.toString().trim());

			//businessDelegatorView.updateUserAccount(entity);

			FacesUtils.addInfoMessage("La cuenta ha sido modificada exitosamente");
			
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage().replace("java.lang.RuntimeException:", ""));

		}
		return "";
	}

	public String action_modify_password() throws Exception {
		log.info("UserAccountView action_modify_password");

		try {
			if (txtPassword.getValue() == null || txtPassword.getValue().toString().trim().equals("")) {
				txtPassword.setStyle("border: 1px solid red;");
  				throw new RuntimeException("Debe ingresar una contraseña");
  			}else {
  				txtPassword.setStyle("border: 1px solid #C5DBEC;");
  			}

			if (txtPassword.getValue().toString().length() < 8) {
				txtPassword.setStyle("border: 1px solid red;");
				throw new RuntimeException("La longitud mínima es de 8 caracteres");
			}else {
  				txtPassword.setStyle("border: 1px solid #C5DBEC;");
  			}

			if (txtPassword.getValue().toString().length() > 20) {
				txtPassword.setStyle("border: 1px solid red;");
				throw new RuntimeException("La longitud máxima es de 20 caracteres");
			}else {
  				txtPassword.setStyle("border: 1px solid #C5DBEC;");
  			}

			if (txtValidatePassword.getValue() == null || txtValidatePassword.getValue().toString().trim().equals("")) {
				txtValidatePassword.setStyle("border: 1px solid red;");
				throw new RuntimeException("Debe confirmar la contraseña");
			}else {
				txtValidatePassword.setStyle("border: 1px solid #C5DBEC;");
  			}

			if (txtPassword.getValue().toString().trim().equals(txtValidatePassword.getValue().toString().trim()) == false) {
				txtValidatePassword.setStyle("border: 1px solid red;");
				txtPassword.setStyle("border: 1px solid red;");
				throw new RuntimeException("Las contraseñas no coinciden");
			}else{
				txtPassword.setStyle("border: 1px solid #C5DBEC;");
				txtValidatePassword.setStyle("border: 1px solid #C5DBEC;");
			}

			entity.setPassword(txtValidatePassword.getValue().toString().trim());
			//businessDelegatorView.updateUserPasswordAccount(entity);
			action_clear_password();

			FacesUtils.addInfoMessage("La contraeña ha sido modificada correctamente");

		} catch (Exception e) {
			show_dialog();
			FacesUtils.addErrorMessage(e.getMessage());

		}
		return "";
	}

	/*
	 * Getters and Setters
	 * -------------------------------------------------------------------------
	 */
	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Password getTxtPassword() {
		return txtPassword;
	}

	public void setTxtPassword(Password txtPassword) {
		this.txtPassword = txtPassword;
	}

	public Password getTxtValidatePassword() {
		return txtValidatePassword;
	}

	public void setTxtValidatePassword(Password txtValidatePassword) {
		this.txtValidatePassword = txtValidatePassword;
	}

	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	public String getStrLogin() {
		return strLogin;
	}

	public void setStrLogin(String strLogin) {
		this.strLogin = strLogin;
	}



}
