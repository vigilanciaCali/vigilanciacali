
package co.edu.usbcali.vas.presentation.backingBeans;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import co.edu.usbcali.vas.presentation.businessDelegate.IBusinessDelegatorView;
import co.edu.usbcali.vas.utilities.Constantes;
import co.edu.usbcali.vas.utilities.FacesUtils;

@SessionScoped
@ManagedBean
public class MenuView {
	private final static Logger log = Logger.getLogger("");

	private String txtId;
	private String txtName;
	private String txtLogin;
	private String txtRol;
	private String txtCustomerName;

	private String urlMenu;
	private String urlBannerExpiracionLicencia;
	private Integer diasRestantesLicencia;

	// URL INDEX
	private String urlPreguntasFrecuentes;
	private String urlManualUsuario;
	private String urlSoporte;
	private Integer userId;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	// -------------------------------------------------------------------------------------------
	@PostConstruct
	public void iniciarSesion() {

		try {

			this.txtName = FacesUtils.getfromSession(Constantes.SESSION_USER_NAME).toString();
			this.txtRol = FacesUtils.getfromSession(Constantes.SESSION_USER_TYPE).toString();
			// consultar nombre cliente en la tabla de parametros
			// this.txtCustomerName =
			// FacesUtils.getfromSession(Constantes.SESSION_CUSTOMER_NAME).toString();

			this.cargarMenu();
			//this.cargarUrlsIndex();

		} catch (Exception e) {
			if (e.getMessage() == null) {
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Error Inicio de Sesion"));
			} else {
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(e.getMessage()));
				Logger.getLogger(MenuView.class.getName()).log(Level.SEVERE, null, e);
			}
		}

	}

	public void cargarMenu() {

		try {
			if (this.txtRol.equals("SYSTEM")) {
				this.urlMenu = "./menu_system.xhtml";
			}
			if (this.txtRol.equals("ADMIN")) {
				this.urlMenu = "./menu_admin.xhtml";
			}
			if (this.txtRol.equals("USER")) {
				this.urlMenu = "./menu_user.xhtml";
			}
	
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error en cargarMenu");

		}
	}

	/*public void cargarUrlsIndex() {

		try {

			this.urlPreguntasFrecuentes = businessDelegatorView
					.getParametroPorCodigoValorTexto(Constantes.URL_PREGUNTAS_FRECUENTES);
			this.urlManualUsuario = businessDelegatorView
					.getParametroPorCodigoValorTexto(Constantes.URL_MANUAL_USUARIO);
			this.urlSoporte = businessDelegatorView.getParametroPorCodigoValorTexto(Constantes.URL_SOPORTE);

		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error en cargarUrlsIndex");

		}
	}*/

	// Limpiar
	// Action-----------------------------------------------------------------------------------------------------
	public String limpiarAction() {

		return "";
	}

	public String getTxtIdentificacion() {
		return txtId;
	}

	public void setTxtIdentificacion(String txtIdentificacion) {
		this.txtId = txtIdentificacion;
	}

	public String getTxtMail() {
		return txtLogin;
	}

	public void setTxtMail(String txtMail) {
		this.txtLogin = txtMail;
	}

	public Integer getUsuarioId() {
		return userId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.userId = usuarioId;
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public String getUrlMenu() {
		return urlMenu;
	}

	public void setUrlMenu(String urlMenu) {
		this.urlMenu = urlMenu;
	}

	public String getTxtName() {
		return txtName;
	}

	public void setTxtName(String txtName) {
		this.txtName = txtName;
	}

	public String getUrlBannerExpiracionLicencia() {
		return urlBannerExpiracionLicencia;
	}

	public void setUrlBannerExpiracionLicencia(String urlBannerExpiracionLicencia) {
		this.urlBannerExpiracionLicencia = urlBannerExpiracionLicencia;
	}

	public Integer getDiasRestantesLicencia() {
		return diasRestantesLicencia;
	}

	public void setDiasRestantesLicencia(Integer diasRestantesLicencia) {
		this.diasRestantesLicencia = diasRestantesLicencia;
	}

	public String getTxtCustomerName() {
		return txtCustomerName;
	}

	public void setTxtCustomerName(String txtCustomerName) {
		this.txtCustomerName = txtCustomerName;
	}

	public String getUrlPreguntasFrecuentes() {
		return urlPreguntasFrecuentes;
	}

	public void setUrlPreguntasFrecuentes(String urlPreguntasFrecuentes) {
		this.urlPreguntasFrecuentes = urlPreguntasFrecuentes;
	}

	public String getUrlManualUsuario() {
		return urlManualUsuario;
	}

	public void setUrlManualUsuario(String urlManualUsuario) {
		this.urlManualUsuario = urlManualUsuario;
	}

	public String getUrlSoporte() {
		return urlSoporte;
	}

	public void setUrlSoporte(String urlSoporte) {
		this.urlSoporte = urlSoporte;
	}

}
