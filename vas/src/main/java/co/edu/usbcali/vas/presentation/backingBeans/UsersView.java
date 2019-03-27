package co.edu.usbcali.vas.presentation.backingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.password.Password;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.vas.model.Users;
import co.edu.usbcali.vas.model.dto.UserTypeDTO;
import co.edu.usbcali.vas.model.dto.UsersDTO;
import co.edu.usbcali.vas.presentation.businessDelegate.IBusinessDelegatorView;
import co.edu.usbcali.vas.security.control.Crypt;
import co.edu.usbcali.vas.utilities.Constantes;
import co.edu.usbcali.vas.utilities.FacesUtils;
import co.edu.usbcali.vas.utilities.Utilities;


/**
 * @author Zathura Code Generator http://zathuracode.org/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class UsersView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UsersView.class);
    
    //GENERAL
    private InputText txtFirstname;
    private InputText txtLastname;
    private InputText txtLogin;
    private Password txtPassword;
    private Password txtValidatePassword;
    //STATUS
    private SelectOneMenu somUserActive;
    private boolean txtDeleted;
    private InputTextarea txtNote;
    //AUDIT
    private Calendar txtCreatedAt;
    private InputText txtCreatedBy;
    private Calendar txtUpdatedAt;
    private InputText txtUpdatedBy;
    //SELECT
	private SelectOneMenu somUserType;
	private List<SelectItem> itemsUserType;
	//DATATABLE FILTERS
	private List<SelectItem> itemsTypeFilter;
	//ENTITY DATA
	private List<UsersDTO> listUsersDTOs;
    private UsersDTO selectedUsersDTO;
    private Users entity;
    private Long totalUsers;
    
    private Crypt crypt = new Crypt();

    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public UsersView() {
        super();
    }
    
    //ACTION METHODS-------------------------------------------------------------------
    public String action_close_dialog() {
 		try {
			FacesUtils.hideDialog("dlgUser");
			action_clear_dialog_data();
		} catch (Exception e) {
			e.printStackTrace();
		}
 		
 		return "";
 	}
    
	public String show_dialog() {
		try {

			FacesUtils.showDialog("dlgUser");

		} catch (Exception e) {
			log.error(e.getMessage());
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	public String action_clean_dialog() {
		action_clear();
		show_dialog();

		return "";
	}
	
	public String action_clear_dialog_data() {

		// GENERAL
		txtFirstname.setValue(null);
		txtLastname.setValue(null);
		txtLogin.setValue(null);
		txtPassword = null;
		somUserType.setValue("-1");
		itemsUserType = null;
		// STATUS
		somUserActive.setValue("-1");
		// txtDeleted = false;
		txtNote.setValue(null);
		// AUDIT
		txtCreatedAt.setValue(null);
		txtCreatedBy.setValue(null);
		txtUpdatedAt.setValue(null);
		txtUpdatedBy.setValue(null);
		// ENTITY DATA
		selectedUsersDTO = null;
		entity = null;
		totalUsers = null;
		// RESET INPUTS COLOR
		txtFirstname.setStyle("border: 1px solid #C5DBEC;");
		txtLastname.setStyle("border: 1px solid #C5DBEC;");
		txtLogin.setStyle("border: 1px solid #C5DBEC;");
		somUserType.setStyle("border: 1px solid #C5DBEC;");
		somUserActive.setStyle("border: 1px solid #C5DBEC;");

		return "";
	}

	public String action_clear() {
		action_clear_dialog_data();
		listUsersDTOs = null;

		return "";
	}
	
	//LISTENER--------------------------------------------------------------------------
	public void listener_selected_entity() {

		try {
			this.entity = businessDelegatorView.getUsers(selectedUsersDTO.getId());
		} catch (Exception e) {
			entity = null;
		}
		if (entity != null) {

			// GENERAL
			txtFirstname.setValue((entity.getFirstname() != null) ? entity.getFirstname() : null);
			txtLastname.setValue((entity.getLastname() != null) ? entity.getLastname() : null);
			txtLogin.setValue((entity.getLogin() != null) ? entity.getLogin() : null);
			somUserType.setValue((entity.getUserType() != null) ? entity.getUserType().getId() : null);
			txtPassword = null;
			//STATUS
			somUserActive.setValue((entity.getActive() != null) ? entity.getActive() : null);
			//txtDeleted = entity.getDeleted().booleanValue();
			txtNote.setValue((entity.getNote() != null) ? entity.getNote() : null);
			// AUDIT
			txtCreatedAt.setValue((entity.getCreatedAt() != null) ? entity.getCreatedAt() : null);
			txtCreatedBy.setValue((entity.getCreatedBy() != null) ? entity.getCreatedBy() : null);
			txtUpdatedAt.setValue((entity.getUpdatedAt() != null) ? entity.getUpdatedAt() : null);
			txtUpdatedBy.setValue((entity.getUpdatedBy() != null) ? entity.getUpdatedBy() : null);

		}
	}

	//ACTIONS------------------------------------------------------------------------------------
	
	public String action_save() {
		try {
			if (entity == null && selectedUsersDTO == null) {
				action_create();
			} else {	
				action_modify();
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage().replace("java.lang.Exception:", ""));
		}
		return "";
	}
	
	public String action_create() throws Exception {
		try {
			entity = new Users();
			validate_form();
			set_objects();

			businessDelegatorView.saveUsers(entity);

			//FacesUtils.addInfoMessage("info");
			FacesUtils.addInfoMessage("El usuario ha sido creado exitosamente");

			action_clear();
		} catch (RuntimeException e) {
			entity = null;
			show_dialog();
			log.error(e.getMessage());
			FacesUtils.addErrorMessage(e.getMessage());
			
		}
		return "";
	}

	public String action_modify() throws Exception {
		try {
			validate_form_modify();
			set_object_modify();

			businessDelegatorView.updateUsers(entity);

			show_dialog();
			FacesUtils.addInfoMessage("El usuario ha sido modificado exitosamente");
		} catch (Exception e) {
			log.error(e.getMessage());
			FacesUtils.addErrorMessage(e.getMessage());
			show_dialog();
		}
		return "";
	}

	public void validate_form() throws Exception {
		log.info("usuarioView action_validar_formulario");
		try {

			if (txtFirstname.getValue() == null || txtFirstname.getValue().toString().trim().equals("")) {
				txtFirstname.setStyle("border: 1px solid red;");
				throw new RuntimeException("Debe ingresar un nombre");
			}else {
				txtFirstname.setStyle("border: 1px solid #C5DBEC;");
			}
			
			if (!Utilities.isOnlyLetters(txtFirstname.getValue().toString().trim())) {
				txtFirstname.setStyle("border: 1px solid red;");
				throw new RuntimeException("El nombre solo puede contener letras");
			}else {
				txtFirstname.setStyle("border: 1px solid #C5DBEC;");
			}
			
			if (txtLastname.getValue() == null || txtLastname.getValue().toString().trim().equals("")) {
				txtLastname.setStyle("border: 1px solid red;");
				throw new RuntimeException("Debe ingresar un apellido");
			}else {
				txtLastname.setStyle("border: 1px solid #C5DBEC;");
			}
			
			if (!Utilities.isOnlyLetters(txtLastname.getValue().toString().trim())) {
				txtLastname.setStyle("border: 1px solid red;");
				throw new RuntimeException("El apellido solo puede contener letras");
			}else {
				txtLastname.setStyle("border: 1px solid #C5DBEC;");
			}
			
			if (!Utilities.validateEmailAddress(txtLogin.getValue().toString().trim()) 
					|| txtLogin.getValue() == null || txtLogin.getValue().toString().trim().equals("")) {
				txtLogin.setStyle("border: 1px solid red;");
				throw new RuntimeException("Ingrese un Email válido");
			}else {
				txtLogin.setStyle("border: 1px solid #C5DBEC;");
			}

			if (somUserType.getValue() == null || somUserType.getValue().toString().trim().equals("-1")) {
				somUserType.setStyle("border: 1px solid red;");
				throw new RuntimeException("Debe seleccionar un tipo de usuario");
			}else {
				somUserType.setStyle("border: 1px solid #C5DBEC;");
			}

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

			if (somUserActive.getValue() == null || somUserActive.getValue().toString().trim().equals("-1")) {
				somUserActive.setStyle("border: 1px solid red;");
				throw new RuntimeException("Debe seleccionar si se encuentra activo o inactivo");
			}else {
				somUserActive.setStyle("border: 1px solid #C5DBEC;");
			}

		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new Exception(e.getMessage().replace("java.lang.RuntimeException:", ""));

		}
	}

	public String set_objects() {
		log.info("usuarioView set_objects");
			//GENERAL
			entity.setFirstname(txtFirstname.getValue().toString().trim());
			entity.setLastname(txtLastname.getValue().toString().trim());
			entity.setLogin(txtLogin.getValue().toString().trim().toLowerCase());
			entity.setPassword(txtValidatePassword.getValue().toString().trim());
			//STATUS
			entity.setActive(Boolean.parseBoolean(somUserActive.getValue().toString().trim()));
			//entity.setDeleted(txtDeleted);
			entity.setNote(txtNote.getValue().toString());
			//DEPENDENCY
			entity.setIdUserType(Integer.parseInt(somUserType.getValue().toString().trim()));
			//AUDIT
			entity.setCreatedBy(FacesUtils.getfromSession(Constantes.SESSION_USER_LOGIN).toString());
			entity.setCreatedAt(new Date());
			

		return "";
	}
	
	public void validate_form_modify() throws Exception {
		log.info("usuarioView action_validar_formulario");
		try {

			//validateMail();
			
			if (txtFirstname.getValue() == null || txtFirstname.getValue().toString().trim().equals("")) {
				txtFirstname.setStyle("border: 1px solid red;");
				throw new RuntimeException("Debe ingresar un nombre");
			}else {
				txtFirstname.setStyle("border: 1px solid #C5DBEC;");
			}
			
			if (!Utilities.isOnlyLetters(txtFirstname.getValue().toString().trim())) {
				txtFirstname.setStyle("border: 1px solid red;");
				throw new RuntimeException("El nombre solo puede contener letras");
			}else {
				txtFirstname.setStyle("border: 1px solid #C5DBEC;");
			}
			
			if (txtLastname.getValue() == null || txtLastname.getValue().toString().trim().equals("")) {
				txtLastname.setStyle("border: 1px solid red;");
				throw new RuntimeException("Debe ingresar un apellido");
			}else {
				txtLastname.setStyle("border: 1px solid #C5DBEC;");
			}
			
			if (!Utilities.isOnlyLetters(txtLastname.getValue().toString().trim())) {
				txtLastname.setStyle("border: 1px solid red;");
				throw new RuntimeException("El apellido solo puede contener letras");
			}else {
				txtLastname.setStyle("border: 1px solid #C5DBEC;");
			}
			
			if (!Utilities.validateEmailAddress(txtLogin.getValue().toString().trim()) 
					|| txtLogin.getValue() == null || txtLogin.getValue().toString().trim().equals("")) {
				txtLogin.setStyle("border: 1px solid red;");
				throw new RuntimeException("Ingrese un Email válido");
			}else {
				txtLogin.setStyle("border: 1px solid #C5DBEC;");
			}

			if (somUserType.getValue() == null || somUserType.getValue().toString().trim().equals("-1")) {
				somUserType.setStyle("border: 1px solid red;");
				throw new RuntimeException("Debe seleccionar un tipo de usuario");
			}else {
				somUserType.setStyle("border: 1px solid #C5DBEC;");
			}
			
			//validar password solo si se ingresa uno nuevo
			if (txtPassword.getValue().toString().trim().equals("") == false
					&& txtValidatePassword.getValue().toString().trim().equals("") == false) {
				
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
				//validacion
				if (txtValidatePassword.getValue().toString().length() < 8) {
					txtValidatePassword.setStyle("border: 1px solid red;");
					throw new RuntimeException("La longitud mínima es de 8 caracteres");
				}else {
					txtValidatePassword.setStyle("border: 1px solid #C5DBEC;");
	  			}

				if (txtValidatePassword.getValue().toString().length() > 20) {
					txtValidatePassword.setStyle("border: 1px solid red;");
					throw new RuntimeException("La longitud máxima es de 20 caracteres");
				}else {
					txtValidatePassword.setStyle("border: 1px solid #C5DBEC;");
	  			}

				if (txtPassword.getValue().toString().trim().equals(txtValidatePassword.getValue().toString().trim()) == false) {
					txtValidatePassword.setStyle("border: 1px solid red;");
					txtPassword.setStyle("border: 1px solid red;");
					throw new RuntimeException("Las contraseñas no coinciden, intente de nuevo");
				}else{
					txtPassword.setStyle("border: 1px solid #C5DBEC;");
					txtValidatePassword.setStyle("border: 1px solid #C5DBEC;");
				}				
				
			}
			
			if (somUserActive.getValue() == null || somUserActive.getValue().toString().trim().equals("-1")) {
				somUserActive.setStyle("border: 1px solid red;");
				throw new RuntimeException("Debe seleccionar si se encuentra activo o inactivo");
			}else {
				somUserActive.setStyle("border: 1px solid #C5DBEC;");
			}

		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new Exception(e.getMessage().replace("java.lang.RuntimeException:", ""));

		}
	}

	public String set_object_modify() {
		log.info("usuarioView action_setters");
		try {
			//GENERAL
			entity.setFirstname(txtFirstname.getValue().toString().trim());
			entity.setLastname(txtLastname.getValue().toString().trim());
			entity.setLogin(txtLogin.getValue().toString().trim().toLowerCase());
			//PASSWORD
			if (txtPassword.getValue().toString().trim().equals("") == false
					&& txtValidatePassword.getValue().toString().trim().equals("") == false) {

				String passwordCrypt = crypt.securePassword(txtValidatePassword.getValue().toString().trim());
				entity.setPassword(passwordCrypt);
			}
			//STATUS
			entity.setActive(Boolean.parseBoolean(somUserActive.getValue().toString().trim()));
			//entity.setDeleted(txtDeleted);
			entity.setNote(txtNote.getValue().toString());
			//DEPENDENCY
			entity.setIdUserType(Integer.parseInt(somUserType.getValue().toString().trim()));
			//AUDIT
			entity.setUpdatedBy(FacesUtils.getfromSession(Constantes.SESSION_USER_LOGIN).toString());
			entity.setUpdatedAt(new Date());

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage().replace("java.lang.Exception:", ""));
		}
		return "";
	}
	
	/*public void validateMail() throws Exception  {
		log.info("usersView validateMail");
		try {
			
			if(businessDelegatorView.validateUserLogin(txtLogin.getValue().toString().trim(), 
					entity.getId().toString().trim(), Long.parseLong(FacesUtils.getfromSession(Constantes.SESSION_CUSTOMER_ID).toString())) == false){
				
				FacesUtils.addWarningMessage("Aviso");
 				throw new RuntimeException("Este correo ya se encuentra registrado, verifique e intente nuevamente"); 			
			}
				
		}  catch (RuntimeException e) {
			log.error("validarMail failed", e);
			throw e;
		} 

	}*/
    
    //GETTERS AND SETTERS---------------------------------------------------------------

    public InputText getTxtFirstname() {
        return txtFirstname;
    }

    public void setTxtFirstname(InputText txtFirstname) {
        this.txtFirstname = txtFirstname;
    }

    public InputText getTxtLastname() {
        return txtLastname;
    }

    public void setTxtLastname(InputText txtLastname) {
        this.txtLastname = txtLastname;
    }

    public InputText getTxtLogin() {
        return txtLogin;
    }

    public void setTxtLogin(InputText txtLogin) {
        this.txtLogin = txtLogin;
    }

    public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
    }

    public IBusinessDelegatorView getBusinessDelegatorView() {
        return businessDelegatorView;
    }

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

	public List<UsersDTO> getListUsersDTOs() {
		 try {
	            if (listUsersDTOs == null) {
	            	listUsersDTOs = businessDelegatorView.getDataUsers();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		return listUsersDTOs;
	}

	public void setListUsersDTOs(List<UsersDTO> listUsersDTOs) {
		this.listUsersDTOs = listUsersDTOs;
	}

	public Long getTotalUsers() {
		try {	
			totalUsers = businessDelegatorView.findTotalNumberUsers();
		} catch (Exception e) {
			log.error("getTotalUsers failed", e);
		}
		return totalUsers;
	}

	public void setTotalUsers(Long totalUsers) {
		this.totalUsers = totalUsers;
	}

	public SelectOneMenu getSomUserActive() {
		return somUserActive;
	}

	public void setSomUserActive(SelectOneMenu somUserActive) {
		this.somUserActive = somUserActive;
	}

	public SelectOneMenu getSomUserType() {
		return somUserType;
	}

	public void setSomUserType(SelectOneMenu somUserType) {
		this.somUserType = somUserType;
	}

	public UsersDTO getSelectedUsersDTO() {
		return selectedUsersDTO;
	}

	public void setSelectedUsersDTO(UsersDTO selectedUsersDTO) {
		this.selectedUsersDTO = selectedUsersDTO;
	}

	public List<SelectItem> getItemsUserType() {
		try {
			if (itemsUserType == null) {
				List<UserTypeDTO> listUsuarioTipoDTOs = businessDelegatorView.getDataUserType();
				itemsUserType = new ArrayList<SelectItem>();

				listUsuarioTipoDTOs.forEach(
						usuTipo -> itemsUserType.add(new SelectItem(usuTipo.getId(), usuTipo.getDescription())));
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage().replace("java.lang.Exception:", ""));
		}
		return itemsUserType;
	}

	public void setItemsUserType(List<SelectItem> itemsUserType) {
		this.itemsUserType = itemsUserType;
	}

	public Calendar getTxtCreatedAt() {
		return txtCreatedAt;
	}

	public void setTxtCreatedAt(Calendar txtCreatedAt) {
		this.txtCreatedAt = txtCreatedAt;
	}

	public InputText getTxtCreatedBy() {
		return txtCreatedBy;
	}

	public void setTxtCreatedBy(InputText txtCreatedBy) {
		this.txtCreatedBy = txtCreatedBy;
	}

	public Calendar getTxtUpdatedAt() {
		return txtUpdatedAt;
	}

	public void setTxtUpdatedAt(Calendar txtUpdatedAt) {
		this.txtUpdatedAt = txtUpdatedAt;
	}

	public InputText getTxtUpdatedBy() {
		return txtUpdatedBy;
	}

	public void setTxtUpdatedBy(InputText txtUpdatedBy) {
		this.txtUpdatedBy = txtUpdatedBy;
	}

	public boolean isTxtDeleted() {
		return txtDeleted;
	}

	public void setTxtDeleted(boolean txtDeleted) {
		this.txtDeleted = txtDeleted;
	}

	public InputTextarea getTxtNote() {
		return txtNote;
	}

	public void setTxtNote(InputTextarea txtNote) {
		this.txtNote = txtNote;
	}

	public List<SelectItem> getItemsTypeFilter() {
		try {
			if (itemsTypeFilter == null) {
				List<UserTypeDTO> listStatusDTOs = businessDelegatorView.getDataUserType();
				itemsTypeFilter = new ArrayList<SelectItem>();

				listStatusDTOs.forEach(
						status -> itemsTypeFilter.add(new SelectItem(status.getDescription(), status.getDescription())));
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage().replace("java.lang.Exception:", ""));
		}
		return itemsTypeFilter;
	}

	public void setItemsTypeFilter(List<SelectItem> itemsTypeFilter) {
		this.itemsTypeFilter = itemsTypeFilter;
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

	
}
