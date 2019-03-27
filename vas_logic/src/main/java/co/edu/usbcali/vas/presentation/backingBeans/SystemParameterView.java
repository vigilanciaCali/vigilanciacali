package co.edu.usbcali.vas.presentation.backingBeans;

import co.edu.usbcali.vas.exceptions.*;
import co.edu.usbcali.vas.model.*;
import co.edu.usbcali.vas.model.dto.SystemParameterDTO;
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
public class SystemParameterView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SystemParameterView.class);
    private InputText txtBooleanValue;
    private InputText txtCode;
    private InputText txtCreatedBy;
    private InputText txtDoubleValue;
    private InputText txtIntValue;
    private InputText txtName;
    private InputText txtTextValue;
    private InputText txtUpdatedBy;
    private InputText txtId;
    private Calendar txtCreatedAt;
    private Calendar txtDateValue;
    private Calendar txtTimeValue;
    private Calendar txtUpdatedAt;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SystemParameterDTO> data;
    private SystemParameterDTO selectedSystemParameter;
    private SystemParameter entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public SystemParameterView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            SystemParameterDTO systemParameterDTO = (SystemParameterDTO) e.getObject();

            if (txtBooleanValue == null) {
                txtBooleanValue = new InputText();
            }

            txtBooleanValue.setValue(systemParameterDTO.getBooleanValue());

            if (txtCode == null) {
                txtCode = new InputText();
            }

            txtCode.setValue(systemParameterDTO.getCode());

            if (txtCreatedBy == null) {
                txtCreatedBy = new InputText();
            }

            txtCreatedBy.setValue(systemParameterDTO.getCreatedBy());

            if (txtDoubleValue == null) {
                txtDoubleValue = new InputText();
            }

            txtDoubleValue.setValue(systemParameterDTO.getDoubleValue());

            if (txtIntValue == null) {
                txtIntValue = new InputText();
            }

            txtIntValue.setValue(systemParameterDTO.getIntValue());

            if (txtName == null) {
                txtName = new InputText();
            }

            txtName.setValue(systemParameterDTO.getName());

            if (txtTextValue == null) {
                txtTextValue = new InputText();
            }

            txtTextValue.setValue(systemParameterDTO.getTextValue());

            if (txtUpdatedBy == null) {
                txtUpdatedBy = new InputText();
            }

            txtUpdatedBy.setValue(systemParameterDTO.getUpdatedBy());

            if (txtId == null) {
                txtId = new InputText();
            }

            txtId.setValue(systemParameterDTO.getId());

            if (txtCreatedAt == null) {
                txtCreatedAt = new Calendar();
            }

            txtCreatedAt.setValue(systemParameterDTO.getCreatedAt());

            if (txtDateValue == null) {
                txtDateValue = new Calendar();
            }

            txtDateValue.setValue(systemParameterDTO.getDateValue());

            if (txtTimeValue == null) {
                txtTimeValue = new Calendar();
            }

            txtTimeValue.setValue(systemParameterDTO.getTimeValue());

            if (txtUpdatedAt == null) {
                txtUpdatedAt = new Calendar();
            }

            txtUpdatedAt.setValue(systemParameterDTO.getUpdatedAt());

            Integer id = FacesUtils.checkInteger(txtId);
            entity = businessDelegatorView.getSystemParameter(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedSystemParameter = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedSystemParameter = null;

        if (txtBooleanValue != null) {
            txtBooleanValue.setValue(null);
            txtBooleanValue.setDisabled(true);
        }

        if (txtCode != null) {
            txtCode.setValue(null);
            txtCode.setDisabled(true);
        }

        if (txtCreatedBy != null) {
            txtCreatedBy.setValue(null);
            txtCreatedBy.setDisabled(true);
        }

        if (txtDoubleValue != null) {
            txtDoubleValue.setValue(null);
            txtDoubleValue.setDisabled(true);
        }

        if (txtIntValue != null) {
            txtIntValue.setValue(null);
            txtIntValue.setDisabled(true);
        }

        if (txtName != null) {
            txtName.setValue(null);
            txtName.setDisabled(true);
        }

        if (txtTextValue != null) {
            txtTextValue.setValue(null);
            txtTextValue.setDisabled(true);
        }

        if (txtUpdatedBy != null) {
            txtUpdatedBy.setValue(null);
            txtUpdatedBy.setDisabled(true);
        }

        if (txtCreatedAt != null) {
            txtCreatedAt.setValue(null);
            txtCreatedAt.setDisabled(true);
        }

        if (txtDateValue != null) {
            txtDateValue.setValue(null);
            txtDateValue.setDisabled(true);
        }

        if (txtTimeValue != null) {
            txtTimeValue.setValue(null);
            txtTimeValue.setDisabled(true);
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

    public void listener_txtDateValue() {
        Date inputDate = (Date) txtDateValue.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtTimeValue() {
        Date inputDate = (Date) txtTimeValue.getValue();
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
                ? businessDelegatorView.getSystemParameter(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtBooleanValue.setDisabled(false);
            txtCode.setDisabled(false);
            txtCreatedBy.setDisabled(false);
            txtDoubleValue.setDisabled(false);
            txtIntValue.setDisabled(false);
            txtName.setDisabled(false);
            txtTextValue.setDisabled(false);
            txtUpdatedBy.setDisabled(false);
            txtCreatedAt.setDisabled(false);
            txtDateValue.setDisabled(false);
            txtTimeValue.setDisabled(false);
            txtUpdatedAt.setDisabled(false);
            txtId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtBooleanValue.setValue(entity.getBooleanValue());
            txtBooleanValue.setDisabled(false);
            txtCode.setValue(entity.getCode());
            txtCode.setDisabled(false);
            txtCreatedAt.setValue(entity.getCreatedAt());
            txtCreatedAt.setDisabled(false);
            txtCreatedBy.setValue(entity.getCreatedBy());
            txtCreatedBy.setDisabled(false);
            txtDateValue.setValue(entity.getDateValue());
            txtDateValue.setDisabled(false);
            txtDoubleValue.setValue(entity.getDoubleValue());
            txtDoubleValue.setDisabled(false);
            txtIntValue.setValue(entity.getIntValue());
            txtIntValue.setDisabled(false);
            txtName.setValue(entity.getName());
            txtName.setDisabled(false);
            txtTextValue.setValue(entity.getTextValue());
            txtTextValue.setDisabled(false);
            txtTimeValue.setValue(entity.getTimeValue());
            txtTimeValue.setDisabled(false);
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
        selectedSystemParameter = (SystemParameterDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedSystemParameter"));
        txtBooleanValue.setValue(selectedSystemParameter.getBooleanValue());
        txtBooleanValue.setDisabled(false);
        txtCode.setValue(selectedSystemParameter.getCode());
        txtCode.setDisabled(false);
        txtCreatedAt.setValue(selectedSystemParameter.getCreatedAt());
        txtCreatedAt.setDisabled(false);
        txtCreatedBy.setValue(selectedSystemParameter.getCreatedBy());
        txtCreatedBy.setDisabled(false);
        txtDateValue.setValue(selectedSystemParameter.getDateValue());
        txtDateValue.setDisabled(false);
        txtDoubleValue.setValue(selectedSystemParameter.getDoubleValue());
        txtDoubleValue.setDisabled(false);
        txtIntValue.setValue(selectedSystemParameter.getIntValue());
        txtIntValue.setDisabled(false);
        txtName.setValue(selectedSystemParameter.getName());
        txtName.setDisabled(false);
        txtTextValue.setValue(selectedSystemParameter.getTextValue());
        txtTextValue.setDisabled(false);
        txtTimeValue.setValue(selectedSystemParameter.getTimeValue());
        txtTimeValue.setDisabled(false);
        txtUpdatedAt.setValue(selectedSystemParameter.getUpdatedAt());
        txtUpdatedAt.setDisabled(false);
        txtUpdatedBy.setValue(selectedSystemParameter.getUpdatedBy());
        txtUpdatedBy.setDisabled(false);
        txtId.setValue(selectedSystemParameter.getId());
        txtId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedSystemParameter == null) && (entity == null)) {
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
            entity = new SystemParameter();

            Integer id = FacesUtils.checkInteger(txtId);

            //entity.setBooleanValue(FacesUtils.checkString(txtBooleanValue));
            entity.setCode(FacesUtils.checkString(txtCode));
            entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
            entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
            entity.setDateValue(FacesUtils.checkDate(txtDateValue));
            entity.setDoubleValue(FacesUtils.checkLong(txtDoubleValue));
            entity.setId(id);
            entity.setIntValue(FacesUtils.checkInteger(txtIntValue));
            entity.setName(FacesUtils.checkString(txtName));
            entity.setTextValue(FacesUtils.checkString(txtTextValue));
            entity.setTimeValue(FacesUtils.checkDate(txtTimeValue));
            entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
            businessDelegatorView.saveSystemParameter(entity);
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
                Integer id = new Integer(selectedSystemParameter.getId());
                entity = businessDelegatorView.getSystemParameter(id);
            }

            //entity.setBooleanValue(FacesUtils.checkString(txtBooleanValue));
            entity.setCode(FacesUtils.checkString(txtCode));
            entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
            entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
            entity.setDateValue(FacesUtils.checkDate(txtDateValue));
            entity.setDoubleValue(FacesUtils.checkLong(txtDoubleValue));
            entity.setIntValue(FacesUtils.checkInteger(txtIntValue));
            entity.setName(FacesUtils.checkString(txtName));
            entity.setTextValue(FacesUtils.checkString(txtTextValue));
            entity.setTimeValue(FacesUtils.checkDate(txtTimeValue));
            entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
            businessDelegatorView.updateSystemParameter(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedSystemParameter = (SystemParameterDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedSystemParameter"));

            Integer id = new Integer(selectedSystemParameter.getId());
            entity = businessDelegatorView.getSystemParameter(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer id = FacesUtils.checkInteger(txtId);
            entity = businessDelegatorView.getSystemParameter(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteSystemParameter(entity);
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
            selectedSystemParameter = (SystemParameterDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedSystemParameter"));

            Integer id = new Integer(selectedSystemParameter.getId());
            entity = businessDelegatorView.getSystemParameter(id);
            businessDelegatorView.deleteSystemParameter(entity);
            data.remove(selectedSystemParameter);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String booleanValue, String code,
        Date createdAt, String createdBy, Date dateValue, Long doubleValue,
        Integer id, Integer intValue, String name, String textValue,
        Date timeValue, Date updatedAt, String updatedBy)
        throws Exception {
        try {
           // entity.setBooleanValue(FacesUtils.checkString(booleanValue));
            entity.setCode(FacesUtils.checkString(code));
            entity.setCreatedAt(FacesUtils.checkDate(createdAt));
            entity.setCreatedBy(FacesUtils.checkString(createdBy));
            entity.setDateValue(FacesUtils.checkDate(dateValue));
            entity.setDoubleValue(FacesUtils.checkLong(doubleValue));
            entity.setIntValue(FacesUtils.checkInteger(intValue));
            entity.setName(FacesUtils.checkString(name));
            entity.setTextValue(FacesUtils.checkString(textValue));
            entity.setTimeValue(FacesUtils.checkDate(timeValue));
            entity.setUpdatedAt(FacesUtils.checkDate(updatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(updatedBy));
            businessDelegatorView.updateSystemParameter(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("SystemParameterView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtBooleanValue() {
        return txtBooleanValue;
    }

    public void setTxtBooleanValue(InputText txtBooleanValue) {
        this.txtBooleanValue = txtBooleanValue;
    }

    public InputText getTxtCode() {
        return txtCode;
    }

    public void setTxtCode(InputText txtCode) {
        this.txtCode = txtCode;
    }

    public InputText getTxtCreatedBy() {
        return txtCreatedBy;
    }

    public void setTxtCreatedBy(InputText txtCreatedBy) {
        this.txtCreatedBy = txtCreatedBy;
    }

    public InputText getTxtDoubleValue() {
        return txtDoubleValue;
    }

    public void setTxtDoubleValue(InputText txtDoubleValue) {
        this.txtDoubleValue = txtDoubleValue;
    }

    public InputText getTxtIntValue() {
        return txtIntValue;
    }

    public void setTxtIntValue(InputText txtIntValue) {
        this.txtIntValue = txtIntValue;
    }

    public InputText getTxtName() {
        return txtName;
    }

    public void setTxtName(InputText txtName) {
        this.txtName = txtName;
    }

    public InputText getTxtTextValue() {
        return txtTextValue;
    }

    public void setTxtTextValue(InputText txtTextValue) {
        this.txtTextValue = txtTextValue;
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

    public Calendar getTxtDateValue() {
        return txtDateValue;
    }

    public void setTxtDateValue(Calendar txtDateValue) {
        this.txtDateValue = txtDateValue;
    }

    public Calendar getTxtTimeValue() {
        return txtTimeValue;
    }

    public void setTxtTimeValue(Calendar txtTimeValue) {
        this.txtTimeValue = txtTimeValue;
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

    public List<SystemParameterDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataSystemParameter();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<SystemParameterDTO> systemParameterDTO) {
        this.data = systemParameterDTO;
    }

    public SystemParameterDTO getSelectedSystemParameter() {
        return selectedSystemParameter;
    }

    public void setSelectedSystemParameter(SystemParameterDTO systemParameter) {
        this.selectedSystemParameter = systemParameter;
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
