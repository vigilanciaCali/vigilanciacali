package co.edu.usbcali.vas.presentation.backingBeans;

import java.util.Date;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;
import org.primefaces.context.RequestContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import co.edu.usbcali.vas.model.SystemLog;
import co.edu.usbcali.vas.model.Users;
import co.edu.usbcali.vas.presentation.businessDelegate.IBusinessDelegatorView;
import co.edu.usbcali.vas.utilities.Constantes;
import co.edu.usbcali.vas.utilities.FacesUtils;
import co.edu.usbcali.vas.utilities.Utilities;

@SessionScoped
@ManagedBean(name = "loginView")
public class LoginView {
	private final static Logger log = Logger.getLogger("");

	// registro
	private InputText txtLogin;
	private InputText txtLoginRestore;
	private Password txtPassword;

	private String txtName;
	private String txtPasswordValid;
	// login usuario
	private String login;
	private String password;
	private String userTypeCode;
	//customer plan
	private String customerPlanCode;
	// Sesion
	private Integer usuarioId;
	private Users user;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	@ManagedProperty(value = "#{authenticationManager}")
	private AuthenticationManager authenticationManager = null;

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	// inicio sesion admin
	// --------------------------------------------------------------------------------------------

	public void validate_form() throws Exception {
		//log.info("loginView validate_form");
		try {

			if (txtLogin.getValue() == null || txtLogin.getValue().toString().trim().equals("")) {
				txtLogin.setStyle("border: 1px solid red;");
				throw new RuntimeException("Ingrese su Email");
			} else {
				txtLogin.setStyle("border: 1px solid #C5DBEC;");
			}

			if (!Utilities.validateEmailAddress(txtLogin.getValue().toString().trim())) {
				txtLogin.setStyle("border: 1px solid red;");
				throw new RuntimeException("Ingrese un Email válido");
			} else {
				txtLogin.setStyle("border: 1px solid #C5DBEC;");
			}

			if (txtPassword.getValue().toString().trim() == null
					|| txtPassword.getValue().toString().trim().equals("")) {
				txtPassword.setStyle("border: 1px solid red;");
				throw new RuntimeException("Ingrese su Contraseña");
			} else {
				txtPassword.setStyle("border: 1px solid #C5DBEC;");
			}

		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new Exception(e.getMessage().replace("java.lang.RuntimeException:", ""));

		}
	}

	public String set_object() {
		//log.info("loginView set_object");
		try {

			login = txtLogin.getValue().toString().trim().toLowerCase();
			password = txtPassword.getValue().toString().trim();

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage().replace("java.lang.Exception:", ""));
		}
		return "";
	}

	public String login() throws Exception {
		//log.info("loginView login");

		this.userTypeCode = null;
		this.customerPlanCode = null;

		try {

			validate_form();
			set_object();

			if (FacesContext.getCurrentInstance().getMessageList() != null
					&& FacesContext.getCurrentInstance().getMessageList().size() < 1) {

				Authentication request = new UsernamePasswordAuthenticationToken(this.getLogin(), this.getPassword());
				Authentication result = authenticationManager.authenticate(request);

				SecurityContext securityContext = SecurityContextHolder.getContext();
				securityContext.setAuthentication(result);

				FacesUtils.getHttpSession(true).setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
				//GET DATA FROM SESSION
				getUser_from_session();
				//SAVE LOG
				action_logSys("LOGIN");
				//UPDATE SESSION STATUS
				//userSessionStatusUpdateOnline();

			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));

			this.userTypeCode = "NONE";
		}
		return userTypeCode;
		
	}
	
	// get user from session
	public String getUser_from_session() throws Exception {
		//log.info("LoginView getUser_from_session");

		this.userTypeCode = FacesUtils.getfromSession(Constantes.SESSION_USER_TYPE).toString();
		
		return "";
	}
	//Escribir en el log del systema
    public String action_logSys(String action) throws Exception {
    	//log.info("LoginView action_logSys");
		
    	try {
    		SystemLog logSys = new SystemLog();
    		Users user = (Users) FacesUtils.getfromSession(Constantes.SESSION_USER_OBJECT);
			logSys.setId(Long.valueOf(user.getId().toString().trim()));
			logSys.setUserName(user.getLogin());
			logSys.setActionDate(new Date());
			logSys.setActionName(action);
			logSys.setNote("");
			logSys.setIp(FacesUtils.getIpClient());
		
	     	businessDelegatorView.saveSystemLog(logSys);
			
		} catch (Exception e) {			
			throw new Exception(e.getMessage().replace("java.lang.RuntimeException:", ""));
		}
    	return "";
    }
    
    public String closeSession() throws Exception {
		log.info("LoginView closeSession");
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {

			//businessDelegatorView.userSessionStatusUpdateOffline((Usuario) FacesUtils.getfromSession(Constantes.SESSION_USER_OBJECT));
			//context.invalidateSession();
			context.redirect(context.getRequestContextPath()+"/index.xhtml/j_spring_security_logout");

		} catch (Exception e) {

			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
		
	}
    /*	
	public String userSessionStatusUpdateOnline() throws Exception {
		log.info("LoginView userSessionStatusUpdateOnline");

		try {
			Usuario user = (Usuario) FacesUtils.getfromSession(Constantes.SESSION_USER_OBJECT);
			user.setOnSession(true);
			businessDelegatorView.userSessionStatusUpdateOnline(user);

		} catch (Exception e) {

			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
		
	}*/

	// password restore-----------------------------------------------------------


	/*public void validate_form_restore() throws Exception {
		try {

			if (txtLoginRestore.getValue().toString().trim().equals("") == true 
					|| !Utilities.validateEmailAddress(txtLoginRestore.getValue().toString().trim())) {
				txtLoginRestore.setStyle("border: 1px solid red;");
				throw new RuntimeException("Debe ingresar un Email válido");
			}else{
				txtLoginRestore.setStyle("border: 1px solid #C5DBEC;");
			}

			if (businessDelegatorView.consultarUsuarioPorLogin(txtLoginRestore.getValue().toString().trim()) == null) {
				txtLoginRestore.setStyle("border: 1px solid red;");
				txtLoginRestore.setValue("");
				throw new RuntimeException("El mail no esta registrado en el sistema, intente de nuevo");
			}else{
				txtLoginRestore.setStyle("border: 1px solid #C5DBEC;");
				
			}

		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new Exception(e.getMessage().replace("java.lang.RuntimeException:", ""));

		}
	}*/
	
	public String passwordRestore() throws Exception {
		log.info("LoginView passwordRestore");
		String estadoConfirmacion = null;

		try {
			
			//this.validate_form_restore();
			
			businessDelegatorView.restoreUserPassword(txtLoginRestore.getValue().toString().trim().toLowerCase());
			estadoConfirmacion = "VALID";
			txtLoginRestore.setValue(null);
			FacesUtils.addInfoMessage("Una contraseña provisional ha sido creada!");
			FacesContext.getCurrentInstance().addMessage("",new FacesMessage("Por favor revise su mail y siga las instrucciones"));
			RequestContext.getCurrentInstance().execute("alert('Una contraseña provisional ha sido creada! Revise su correo y siga las instrucciones')");
			
		} catch (Exception e) {
			estadoConfirmacion = "NOVALID";
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return estadoConfirmacion;

	}

	// ------------------------------------------------------------------------------------------

	public long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getUserType() {
		return userTypeCode;
	}

	public void setUserType(String userType) {
		this.userTypeCode = userType;
	}

	public InputText getTxtLogin() {
		return txtLogin;
	}

	public void setTxtLogin(InputText txtLogin) {
		this.txtLogin = txtLogin;
	}

	public Password getTxtPassword() {
		return txtPassword;
	}

	public void setTxtPassword(Password txtPassword) {
		this.txtPassword = txtPassword;
	}

	public String getTxtName() {
		return txtName;
	}

	public void setTxtName(String txtName) {
		this.txtName = txtName;
	}

	public String getTxtPasswordValid() {
		return txtPasswordValid;
	}

	public void setTxtPasswordValid(String txtPasswordValid) {
		this.txtPasswordValid = txtPasswordValid;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public InputText getTxtLoginRestore() {
		return txtLoginRestore;
	}

	public void setTxtLoginRestore(InputText txtLoginRestore) {
		this.txtLoginRestore = txtLoginRestore;
	}

	public String getCustomerPlanCode() {
		return customerPlanCode;
	}

	public void setCustomerPlanCode(String customerPlanCode) {
		this.customerPlanCode = customerPlanCode;
	}



}
