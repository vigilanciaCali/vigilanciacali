package co.edu.usbcali.vas.presentation.backingBeans;

import co.edu.usbcali.vas.exceptions.*;
import co.edu.usbcali.vas.model.*;
import co.edu.usbcali.vas.model.dto.SystemCompanyParameterDTO;
import co.edu.usbcali.vas.presentation.businessDelegate.*;
import co.edu.usbcali.vas.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import org.primefaces.event.RowEditEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 * @author Zathura Code Generator http://zathuracode.org/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class SystemCompanyParameterView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SystemCompanyParameterView.class);
    private InputText txtCode;
    private InputText txtCompanyAddress;
    private InputText txtCompanyEmail;
    private InputText txtCompanyLogoUrl;
    private InputText txtCompanyName;
    private InputText txtCompanyNit;
    private InputText txtCompanyPhone;
    private InputText txtCompanyWeb;
    private InputText txtCreatedBy;
    private InputText txtSystemLogoUrl;
    private InputText txtSystemName;
    private InputText txtSystemUrl;
    private InputText txtSystemVersion;
    private InputText txtUpdatedBy;
    private InputText txtId;
    private Calendar txtCreatedAt;
    private Calendar txtUpdatedAt;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SystemCompanyParameterDTO> data;
    private SystemCompanyParameterDTO selectedSystemCompanyParameter;
    private SystemCompanyParameter entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public SystemCompanyParameterView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            SystemCompanyParameterDTO systemCompanyParameterDTO = (SystemCompanyParameterDTO) e.getObject();

            if (txtCode == null) {
                txtCode = new InputText();
            }

            txtCode.setValue(systemCompanyParameterDTO.getCode());

            if (txtCompanyAddress == null) {
                txtCompanyAddress = new InputText();
            }

            txtCompanyAddress.setValue(systemCompanyParameterDTO.getCompanyAddress());

            if (txtCompanyEmail == null) {
                txtCompanyEmail = new InputText();
            }

            txtCompanyEmail.setValue(systemCompanyParameterDTO.getCompanyEmail());

            if (txtCompanyLogoUrl == null) {
                txtCompanyLogoUrl = new InputText();
            }

            txtCompanyLogoUrl.setValue(systemCompanyParameterDTO.getCompanyLogoUrl());

            if (txtCompanyName == null) {
                txtCompanyName = new InputText();
            }

            txtCompanyName.setValue(systemCompanyParameterDTO.getCompanyName());

            if (txtCompanyNit == null) {
                txtCompanyNit = new InputText();
            }

            txtCompanyNit.setValue(systemCompanyParameterDTO.getCompanyNit());

            if (txtCompanyPhone == null) {
                txtCompanyPhone = new InputText();
            }

            txtCompanyPhone.setValue(systemCompanyParameterDTO.getCompanyPhone());

            if (txtCompanyWeb == null) {
                txtCompanyWeb = new InputText();
            }

            txtCompanyWeb.setValue(systemCompanyParameterDTO.getCompanyWeb());

            if (txtCreatedBy == null) {
                txtCreatedBy = new InputText();
            }

            txtCreatedBy.setValue(systemCompanyParameterDTO.getCreatedBy());

            if (txtSystemLogoUrl == null) {
                txtSystemLogoUrl = new InputText();
            }

            txtSystemLogoUrl.setValue(systemCompanyParameterDTO.getSystemLogoUrl());

            if (txtSystemName == null) {
                txtSystemName = new InputText();
            }

            txtSystemName.setValue(systemCompanyParameterDTO.getSystemName());

            if (txtSystemUrl == null) {
                txtSystemUrl = new InputText();
            }

            txtSystemUrl.setValue(systemCompanyParameterDTO.getSystemUrl());

            if (txtSystemVersion == null) {
                txtSystemVersion = new InputText();
            }

            txtSystemVersion.setValue(systemCompanyParameterDTO.getSystemVersion());

            if (txtUpdatedBy == null) {
                txtUpdatedBy = new InputText();
            }

            txtUpdatedBy.setValue(systemCompanyParameterDTO.getUpdatedBy());

            if (txtId == null) {
                txtId = new InputText();
            }

            txtId.setValue(systemCompanyParameterDTO.getId());

            if (txtCreatedAt == null) {
                txtCreatedAt = new Calendar();
            }

            txtCreatedAt.setValue(systemCompanyParameterDTO.getCreatedAt());

            if (txtUpdatedAt == null) {
                txtUpdatedAt = new Calendar();
            }

            txtUpdatedAt.setValue(systemCompanyParameterDTO.getUpdatedAt());

            Integer id = FacesUtils.checkInteger(txtId);
            entity = businessDelegatorView.getSystemCompanyParameter(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedSystemCompanyParameter = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedSystemCompanyParameter = null;

        if (txtCode != null) {
            txtCode.setValue(null);
            txtCode.setDisabled(true);
        }

        if (txtCompanyAddress != null) {
            txtCompanyAddress.setValue(null);
            txtCompanyAddress.setDisabled(true);
        }

        if (txtCompanyEmail != null) {
            txtCompanyEmail.setValue(null);
            txtCompanyEmail.setDisabled(true);
        }

        if (txtCompanyLogoUrl != null) {
            txtCompanyLogoUrl.setValue(null);
            txtCompanyLogoUrl.setDisabled(true);
        }

        if (txtCompanyName != null) {
            txtCompanyName.setValue(null);
            txtCompanyName.setDisabled(true);
        }

        if (txtCompanyNit != null) {
            txtCompanyNit.setValue(null);
            txtCompanyNit.setDisabled(true);
        }

        if (txtCompanyPhone != null) {
            txtCompanyPhone.setValue(null);
            txtCompanyPhone.setDisabled(true);
        }

        if (txtCompanyWeb != null) {
            txtCompanyWeb.setValue(null);
            txtCompanyWeb.setDisabled(true);
        }

        if (txtCreatedBy != null) {
            txtCreatedBy.setValue(null);
            txtCreatedBy.setDisabled(true);
        }

        if (txtSystemLogoUrl != null) {
            txtSystemLogoUrl.setValue(null);
            txtSystemLogoUrl.setDisabled(true);
        }

        if (txtSystemName != null) {
            txtSystemName.setValue(null);
            txtSystemName.setDisabled(true);
        }

        if (txtSystemUrl != null) {
            txtSystemUrl.setValue(null);
            txtSystemUrl.setDisabled(true);
        }

        if (txtSystemVersion != null) {
            txtSystemVersion.setValue(null);
            txtSystemVersion.setDisabled(true);
        }

        if (txtUpdatedBy != null) {
            txtUpdatedBy.setValue(null);
            txtUpdatedBy.setDisabled(true);
        }

        if (txtCreatedAt != null) {
            txtCreatedAt.setValue(null);
            txtCreatedAt.setDisabled(true);
        }

        if (txtUpdatedAt != null) {
            txtUpdatedAt.setValue(null);
            txtUpdatedAt.setDisabled(true);
        }

        if (txtId != null) {
            txtId.setValue(null);
            txtId.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtCreatedAt() {
        Date inputDate = (Date) txtCreatedAt.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtUpdatedAt() {
        Date inputDate = (Date) txtUpdatedAt.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Integer id = FacesUtils.checkInteger(txtId);
            entity = (id != null)
                ? businessDelegatorView.getSystemCompanyParameter(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCode.setDisabled(false);
            txtCompanyAddress.setDisabled(false);
            txtCompanyEmail.setDisabled(false);
            txtCompanyLogoUrl.setDisabled(false);
            txtCompanyName.setDisabled(false);
            txtCompanyNit.setDisabled(false);
            txtCompanyPhone.setDisabled(false);
            txtCompanyWeb.setDisabled(false);
            txtCreatedBy.setDisabled(false);
            txtSystemLogoUrl.setDisabled(false);
            txtSystemName.setDisabled(false);
            txtSystemUrl.setDisabled(false);
            txtSystemVersion.setDisabled(false);
            txtUpdatedBy.setDisabled(false);
            txtCreatedAt.setDisabled(false);
            txtUpdatedAt.setDisabled(false);
            txtId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCode.setValue(entity.getCode());
            txtCode.setDisabled(false);
            txtCompanyAddress.setValue(entity.getCompanyAddress());
            txtCompanyAddress.setDisabled(false);
            txtCompanyEmail.setValue(entity.getCompanyEmail());
            txtCompanyEmail.setDisabled(false);
            txtCompanyLogoUrl.setValue(entity.getCompanyLogoUrl());
            txtCompanyLogoUrl.setDisabled(false);
            txtCompanyName.setValue(entity.getCompanyName());
            txtCompanyName.setDisabled(false);
            txtCompanyNit.setValue(entity.getCompanyNit());
            txtCompanyNit.setDisabled(false);
            txtCompanyPhone.setValue(entity.getCompanyPhone());
            txtCompanyPhone.setDisabled(false);
            txtCompanyWeb.setValue(entity.getCompanyWeb());
            txtCompanyWeb.setDisabled(false);
            txtCreatedAt.setValue(entity.getCreatedAt());
            txtCreatedAt.setDisabled(false);
            txtCreatedBy.setValue(entity.getCreatedBy());
            txtCreatedBy.setDisabled(false);
            txtSystemLogoUrl.setValue(entity.getSystemLogoUrl());
            txtSystemLogoUrl.setDisabled(false);
            txtSystemName.setValue(entity.getSystemName());
            txtSystemName.setDisabled(false);
            txtSystemUrl.setValue(entity.getSystemUrl());
            txtSystemUrl.setDisabled(false);
            txtSystemVersion.setValue(entity.getSystemVersion());
            txtSystemVersion.setDisabled(false);
            txtUpdatedAt.setValue(entity.getUpdatedAt());
            txtUpdatedAt.setDisabled(false);
            txtUpdatedBy.setValue(entity.getUpdatedBy());
            txtUpdatedBy.setDisabled(false);
            txtId.setValue(entity.getId());
            txtId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedSystemCompanyParameter = (SystemCompanyParameterDTO) (evt.getComponent()
                                                                         .getAttributes()
                                                                         .get("selectedSystemCompanyParameter"));
        txtCode.setValue(selectedSystemCompanyParameter.getCode());
        txtCode.setDisabled(false);
        txtCompanyAddress.setValue(selectedSystemCompanyParameter.getCompanyAddress());
        txtCompanyAddress.setDisabled(false);
        txtCompanyEmail.setValue(selectedSystemCompanyParameter.getCompanyEmail());
        txtCompanyEmail.setDisabled(false);
        txtCompanyLogoUrl.setValue(selectedSystemCompanyParameter.getCompanyLogoUrl());
        txtCompanyLogoUrl.setDisabled(false);
        txtCompanyName.setValue(selectedSystemCompanyParameter.getCompanyName());
        txtCompanyName.setDisabled(false);
        txtCompanyNit.setValue(selectedSystemCompanyParameter.getCompanyNit());
        txtCompanyNit.setDisabled(false);
        txtCompanyPhone.setValue(selectedSystemCompanyParameter.getCompanyPhone());
        txtCompanyPhone.setDisabled(false);
        txtCompanyWeb.setValue(selectedSystemCompanyParameter.getCompanyWeb());
        txtCompanyWeb.setDisabled(false);
        txtCreatedAt.setValue(selectedSystemCompanyParameter.getCreatedAt());
        txtCreatedAt.setDisabled(false);
        txtCreatedBy.setValue(selectedSystemCompanyParameter.getCreatedBy());
        txtCreatedBy.setDisabled(false);
        txtSystemLogoUrl.setValue(selectedSystemCompanyParameter.getSystemLogoUrl());
        txtSystemLogoUrl.setDisabled(false);
        txtSystemName.setValue(selectedSystemCompanyParameter.getSystemName());
        txtSystemName.setDisabled(false);
        txtSystemUrl.setValue(selectedSystemCompanyParameter.getSystemUrl());
        txtSystemUrl.setDisabled(false);
        txtSystemVersion.setValue(selectedSystemCompanyParameter.getSystemVersion());
        txtSystemVersion.setDisabled(false);
        txtUpdatedAt.setValue(selectedSystemCompanyParameter.getUpdatedAt());
        txtUpdatedAt.setDisabled(false);
        txtUpdatedBy.setValue(selectedSystemCompanyParameter.getUpdatedBy());
        txtUpdatedBy.setDisabled(false);
        txtId.setValue(selectedSystemCompanyParameter.getId());
        txtId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedSystemCompanyParameter == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }

            data = null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        try {
            entity = new SystemCompanyParameter();

            Integer id = FacesUtils.checkInteger(txtId);

            entity.setCode(FacesUtils.checkString(txtCode));
            entity.setCompanyAddress(FacesUtils.checkString(txtCompanyAddress));
            entity.setCompanyEmail(FacesUtils.checkString(txtCompanyEmail));
            entity.setCompanyLogoUrl(FacesUtils.checkString(txtCompanyLogoUrl));
            entity.setCompanyName(FacesUtils.checkString(txtCompanyName));
            entity.setCompanyNit(FacesUtils.checkString(txtCompanyNit));
            entity.setCompanyPhone(FacesUtils.checkString(txtCompanyPhone));
            entity.setCompanyWeb(FacesUtils.checkString(txtCompanyWeb));
            entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
            entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
            entity.setId(id);
            entity.setSystemLogoUrl(FacesUtils.checkString(txtSystemLogoUrl));
            entity.setSystemName(FacesUtils.checkString(txtSystemName));
            entity.setSystemUrl(FacesUtils.checkString(txtSystemUrl));
            entity.setSystemVersion(FacesUtils.checkString(txtSystemVersion));
            entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
            businessDelegatorView.saveSystemCompanyParameter(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Integer id = new Integer(selectedSystemCompanyParameter.getId());
                entity = businessDelegatorView.getSystemCompanyParameter(id);
            }

            entity.setCode(FacesUtils.checkString(txtCode));
            entity.setCompanyAddress(FacesUtils.checkString(txtCompanyAddress));
            entity.setCompanyEmail(FacesUtils.checkString(txtCompanyEmail));
            entity.setCompanyLogoUrl(FacesUtils.checkString(txtCompanyLogoUrl));
            entity.setCompanyName(FacesUtils.checkString(txtCompanyName));
            entity.setCompanyNit(FacesUtils.checkString(txtCompanyNit));
            entity.setCompanyPhone(FacesUtils.checkString(txtCompanyPhone));
            entity.setCompanyWeb(FacesUtils.checkString(txtCompanyWeb));
            entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
            entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
            entity.setSystemLogoUrl(FacesUtils.checkString(txtSystemLogoUrl));
            entity.setSystemName(FacesUtils.checkString(txtSystemName));
            entity.setSystemUrl(FacesUtils.checkString(txtSystemUrl));
            entity.setSystemVersion(FacesUtils.checkString(txtSystemVersion));
            entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
            businessDelegatorView.updateSystemCompanyParameter(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedSystemCompanyParameter = (SystemCompanyParameterDTO) (evt.getComponent()
                                                                             .getAttributes()
                                                                             .get("selectedSystemCompanyParameter"));

            Integer id = new Integer(selectedSystemCompanyParameter.getId());
            entity = businessDelegatorView.getSystemCompanyParameter(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer id = FacesUtils.checkInteger(txtId);
            entity = businessDelegatorView.getSystemCompanyParameter(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteSystemCompanyParameter(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
            data = null;
        } catch (Exception e) {
            throw e;
        }
    }

    public String action_closeDialog() {
        setShowDialog(false);
        action_clear();

        return "";
    }

    public String actionDeleteDataTableEditable(ActionEvent evt) {
        try {
            selectedSystemCompanyParameter = (SystemCompanyParameterDTO) (evt.getComponent()
                                                                             .getAttributes()
                                                                             .get("selectedSystemCompanyParameter"));

            Integer id = new Integer(selectedSystemCompanyParameter.getId());
            entity = businessDelegatorView.getSystemCompanyParameter(id);
            businessDelegatorView.deleteSystemCompanyParameter(entity);
            data.remove(selectedSystemCompanyParameter);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String code, String companyAddress,
        String companyEmail, String companyLogoUrl, String companyName,
        String companyNit, String companyPhone, String companyWeb,
        Date createdAt, String createdBy, Integer id, String systemLogoUrl,
        String systemName, String systemUrl, String systemVersion,
        Date updatedAt, String updatedBy) throws Exception {
        try {
            entity.setCode(FacesUtils.checkString(code));
            entity.setCompanyAddress(FacesUtils.checkString(companyAddress));
            entity.setCompanyEmail(FacesUtils.checkString(companyEmail));
            entity.setCompanyLogoUrl(FacesUtils.checkString(companyLogoUrl));
            entity.setCompanyName(FacesUtils.checkString(companyName));
            entity.setCompanyNit(FacesUtils.checkString(companyNit));
            entity.setCompanyPhone(FacesUtils.checkString(companyPhone));
            entity.setCompanyWeb(FacesUtils.checkString(companyWeb));
            entity.setCreatedAt(FacesUtils.checkDate(createdAt));
            entity.setCreatedBy(FacesUtils.checkString(createdBy));
            entity.setSystemLogoUrl(FacesUtils.checkString(systemLogoUrl));
            entity.setSystemName(FacesUtils.checkString(systemName));
            entity.setSystemUrl(FacesUtils.checkString(systemUrl));
            entity.setSystemVersion(FacesUtils.checkString(systemVersion));
            entity.setUpdatedAt(FacesUtils.checkDate(updatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(updatedBy));
            businessDelegatorView.updateSystemCompanyParameter(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("SystemCompanyParameterView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCode() {
        return txtCode;
    }

    public void setTxtCode(InputText txtCode) {
        this.txtCode = txtCode;
    }

    public InputText getTxtCompanyAddress() {
        return txtCompanyAddress;
    }

    public void setTxtCompanyAddress(InputText txtCompanyAddress) {
        this.txtCompanyAddress = txtCompanyAddress;
    }

    public InputText getTxtCompanyEmail() {
        return txtCompanyEmail;
    }

    public void setTxtCompanyEmail(InputText txtCompanyEmail) {
        this.txtCompanyEmail = txtCompanyEmail;
    }

    public InputText getTxtCompanyLogoUrl() {
        return txtCompanyLogoUrl;
    }

    public void setTxtCompanyLogoUrl(InputText txtCompanyLogoUrl) {
        this.txtCompanyLogoUrl = txtCompanyLogoUrl;
    }

    public InputText getTxtCompanyName() {
        return txtCompanyName;
    }

    public void setTxtCompanyName(InputText txtCompanyName) {
        this.txtCompanyName = txtCompanyName;
    }

    public InputText getTxtCompanyNit() {
        return txtCompanyNit;
    }

    public void setTxtCompanyNit(InputText txtCompanyNit) {
        this.txtCompanyNit = txtCompanyNit;
    }

    public InputText getTxtCompanyPhone() {
        return txtCompanyPhone;
    }

    public void setTxtCompanyPhone(InputText txtCompanyPhone) {
        this.txtCompanyPhone = txtCompanyPhone;
    }

    public InputText getTxtCompanyWeb() {
        return txtCompanyWeb;
    }

    public void setTxtCompanyWeb(InputText txtCompanyWeb) {
        this.txtCompanyWeb = txtCompanyWeb;
    }

    public InputText getTxtCreatedBy() {
        return txtCreatedBy;
    }

    public void setTxtCreatedBy(InputText txtCreatedBy) {
        this.txtCreatedBy = txtCreatedBy;
    }

    public InputText getTxtSystemLogoUrl() {
        return txtSystemLogoUrl;
    }

    public void setTxtSystemLogoUrl(InputText txtSystemLogoUrl) {
        this.txtSystemLogoUrl = txtSystemLogoUrl;
    }

    public InputText getTxtSystemName() {
        return txtSystemName;
    }

    public void setTxtSystemName(InputText txtSystemName) {
        this.txtSystemName = txtSystemName;
    }

    public InputText getTxtSystemUrl() {
        return txtSystemUrl;
    }

    public void setTxtSystemUrl(InputText txtSystemUrl) {
        this.txtSystemUrl = txtSystemUrl;
    }

    public InputText getTxtSystemVersion() {
        return txtSystemVersion;
    }

    public void setTxtSystemVersion(InputText txtSystemVersion) {
        this.txtSystemVersion = txtSystemVersion;
    }

    public InputText getTxtUpdatedBy() {
        return txtUpdatedBy;
    }

    public void setTxtUpdatedBy(InputText txtUpdatedBy) {
        this.txtUpdatedBy = txtUpdatedBy;
    }

    public Calendar getTxtCreatedAt() {
        return txtCreatedAt;
    }

    public void setTxtCreatedAt(Calendar txtCreatedAt) {
        this.txtCreatedAt = txtCreatedAt;
    }

    public Calendar getTxtUpdatedAt() {
        return txtUpdatedAt;
    }

    public void setTxtUpdatedAt(Calendar txtUpdatedAt) {
        this.txtUpdatedAt = txtUpdatedAt;
    }

    public InputText getTxtId() {
        return txtId;
    }

    public void setTxtId(InputText txtId) {
        this.txtId = txtId;
    }

    public List<SystemCompanyParameterDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataSystemCompanyParameter();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(
        List<SystemCompanyParameterDTO> systemCompanyParameterDTO) {
        this.data = systemCompanyParameterDTO;
    }

    public SystemCompanyParameterDTO getSelectedSystemCompanyParameter() {
        return selectedSystemCompanyParameter;
    }

    public void setSelectedSystemCompanyParameter(
        SystemCompanyParameterDTO systemCompanyParameter) {
        this.selectedSystemCompanyParameter = systemCompanyParameter;
    }

    public CommandButton getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(CommandButton btnSave) {
        this.btnSave = btnSave;
    }

    public CommandButton getBtnModify() {
        return btnModify;
    }

    public void setBtnModify(CommandButton btnModify) {
        this.btnModify = btnModify;
    }

    public CommandButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(CommandButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public CommandButton getBtnClear() {
        return btnClear;
    }

    public void setBtnClear(CommandButton btnClear) {
        this.btnClear = btnClear;
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

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
}
