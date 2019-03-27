package co.edu.usbcali.vas.presentation.backingBeans;

import co.edu.usbcali.vas.exceptions.*;
import co.edu.usbcali.vas.model.*;
import co.edu.usbcali.vas.model.dto.DeviceLogDTO;
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
public class DeviceLogView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(DeviceLogView.class);
    private InputText txtActionName;
    private InputText txtDeviceId;
    private InputText txtIp;
    private InputText txtNote;
    private InputText txtUserId;
    private InputText txtUserName;
    private InputText txtId;
    private Calendar txtActionDate;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<DeviceLogDTO> data;
    private DeviceLogDTO selectedDeviceLog;
    private DeviceLog entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public DeviceLogView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            DeviceLogDTO deviceLogDTO = (DeviceLogDTO) e.getObject();

            if (txtActionName == null) {
                txtActionName = new InputText();
            }

            txtActionName.setValue(deviceLogDTO.getActionName());

            if (txtDeviceId == null) {
                txtDeviceId = new InputText();
            }

            txtDeviceId.setValue(deviceLogDTO.getDeviceId());

            if (txtIp == null) {
                txtIp = new InputText();
            }

            txtIp.setValue(deviceLogDTO.getIp());

            if (txtNote == null) {
                txtNote = new InputText();
            }

            txtNote.setValue(deviceLogDTO.getNote());

            if (txtUserId == null) {
                txtUserId = new InputText();
            }

            txtUserId.setValue(deviceLogDTO.getUserId());

            if (txtUserName == null) {
                txtUserName = new InputText();
            }

            txtUserName.setValue(deviceLogDTO.getUserName());

            if (txtId == null) {
                txtId = new InputText();
            }

            txtId.setValue(deviceLogDTO.getId());

            if (txtActionDate == null) {
                txtActionDate = new Calendar();
            }

            txtActionDate.setValue(deviceLogDTO.getActionDate());

            Long id = FacesUtils.checkLong(txtId);
            entity = businessDelegatorView.getDeviceLog(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedDeviceLog = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedDeviceLog = null;

        if (txtActionName != null) {
            txtActionName.setValue(null);
            txtActionName.setDisabled(true);
        }

        if (txtDeviceId != null) {
            txtDeviceId.setValue(null);
            txtDeviceId.setDisabled(true);
        }

        if (txtIp != null) {
            txtIp.setValue(null);
            txtIp.setDisabled(true);
        }

        if (txtNote != null) {
            txtNote.setValue(null);
            txtNote.setDisabled(true);
        }

        if (txtUserId != null) {
            txtUserId.setValue(null);
            txtUserId.setDisabled(true);
        }

        if (txtUserName != null) {
            txtUserName.setValue(null);
            txtUserName.setDisabled(true);
        }

        if (txtActionDate != null) {
            txtActionDate.setValue(null);
            txtActionDate.setDisabled(true);
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

    public void listener_txtActionDate() {
        Date inputDate = (Date) txtActionDate.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long id = FacesUtils.checkLong(txtId);
            entity = (id != null) ? businessDelegatorView.getDeviceLog(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActionName.setDisabled(false);
            txtDeviceId.setDisabled(false);
            txtIp.setDisabled(false);
            txtNote.setDisabled(false);
            txtUserId.setDisabled(false);
            txtUserName.setDisabled(false);
            txtActionDate.setDisabled(false);
            txtId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActionDate.setValue(entity.getActionDate());
            txtActionDate.setDisabled(false);
            txtActionName.setValue(entity.getActionName());
            txtActionName.setDisabled(false);
            txtDeviceId.setValue(entity.getDeviceId());
            txtDeviceId.setDisabled(false);
            txtIp.setValue(entity.getIp());
            txtIp.setDisabled(false);
            txtNote.setValue(entity.getNote());
            txtNote.setDisabled(false);
            txtUserId.setValue(entity.getUserId());
            txtUserId.setDisabled(false);
            txtUserName.setValue(entity.getUserName());
            txtUserName.setDisabled(false);
            txtId.setValue(entity.getId());
            txtId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedDeviceLog = (DeviceLogDTO) (evt.getComponent().getAttributes()
                                               .get("selectedDeviceLog"));
        txtActionDate.setValue(selectedDeviceLog.getActionDate());
        txtActionDate.setDisabled(false);
        txtActionName.setValue(selectedDeviceLog.getActionName());
        txtActionName.setDisabled(false);
        txtDeviceId.setValue(selectedDeviceLog.getDeviceId());
        txtDeviceId.setDisabled(false);
        txtIp.setValue(selectedDeviceLog.getIp());
        txtIp.setDisabled(false);
        txtNote.setValue(selectedDeviceLog.getNote());
        txtNote.setDisabled(false);
        txtUserId.setValue(selectedDeviceLog.getUserId());
        txtUserId.setDisabled(false);
        txtUserName.setValue(selectedDeviceLog.getUserName());
        txtUserName.setDisabled(false);
        txtId.setValue(selectedDeviceLog.getId());
        txtId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedDeviceLog == null) && (entity == null)) {
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
            entity = new DeviceLog();

            Long id = FacesUtils.checkLong(txtId);

            entity.setActionDate(FacesUtils.checkDate(txtActionDate));
            entity.setActionName(FacesUtils.checkString(txtActionName));
            entity.setDeviceId(FacesUtils.checkInteger(txtDeviceId));
            entity.setId(id);
            entity.setIp(FacesUtils.checkString(txtIp));
            entity.setNote(FacesUtils.checkString(txtNote));
            entity.setUserId(FacesUtils.checkInteger(txtUserId));
            entity.setUserName(FacesUtils.checkString(txtUserName));
            businessDelegatorView.saveDeviceLog(entity);
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
                Long id = new Long(selectedDeviceLog.getId());
                entity = businessDelegatorView.getDeviceLog(id);
            }

            entity.setActionDate(FacesUtils.checkDate(txtActionDate));
            entity.setActionName(FacesUtils.checkString(txtActionName));
            entity.setDeviceId(FacesUtils.checkInteger(txtDeviceId));
            entity.setIp(FacesUtils.checkString(txtIp));
            entity.setNote(FacesUtils.checkString(txtNote));
            entity.setUserId(FacesUtils.checkInteger(txtUserId));
            entity.setUserName(FacesUtils.checkString(txtUserName));
            businessDelegatorView.updateDeviceLog(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedDeviceLog = (DeviceLogDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedDeviceLog"));

            Long id = new Long(selectedDeviceLog.getId());
            entity = businessDelegatorView.getDeviceLog(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long id = FacesUtils.checkLong(txtId);
            entity = businessDelegatorView.getDeviceLog(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteDeviceLog(entity);
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
            selectedDeviceLog = (DeviceLogDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedDeviceLog"));

            Long id = new Long(selectedDeviceLog.getId());
            entity = businessDelegatorView.getDeviceLog(id);
            businessDelegatorView.deleteDeviceLog(entity);
            data.remove(selectedDeviceLog);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Date actionDate, String actionName,
        Integer deviceId, Long id, String ip, String note, Integer userId,
        String userName) throws Exception {
        try {
            entity.setActionDate(FacesUtils.checkDate(actionDate));
            entity.setActionName(FacesUtils.checkString(actionName));
            entity.setDeviceId(FacesUtils.checkInteger(deviceId));
            entity.setIp(FacesUtils.checkString(ip));
            entity.setNote(FacesUtils.checkString(note));
            entity.setUserId(FacesUtils.checkInteger(userId));
            entity.setUserName(FacesUtils.checkString(userName));
            businessDelegatorView.updateDeviceLog(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("DeviceLogView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtActionName() {
        return txtActionName;
    }

    public void setTxtActionName(InputText txtActionName) {
        this.txtActionName = txtActionName;
    }

    public InputText getTxtDeviceId() {
        return txtDeviceId;
    }

    public void setTxtDeviceId(InputText txtDeviceId) {
        this.txtDeviceId = txtDeviceId;
    }

    public InputText getTxtIp() {
        return txtIp;
    }

    public void setTxtIp(InputText txtIp) {
        this.txtIp = txtIp;
    }

    public InputText getTxtNote() {
        return txtNote;
    }

    public void setTxtNote(InputText txtNote) {
        this.txtNote = txtNote;
    }

    public InputText getTxtUserId() {
        return txtUserId;
    }

    public void setTxtUserId(InputText txtUserId) {
        this.txtUserId = txtUserId;
    }

    public InputText getTxtUserName() {
        return txtUserName;
    }

    public void setTxtUserName(InputText txtUserName) {
        this.txtUserName = txtUserName;
    }

    public Calendar getTxtActionDate() {
        return txtActionDate;
    }

    public void setTxtActionDate(Calendar txtActionDate) {
        this.txtActionDate = txtActionDate;
    }

    public InputText getTxtId() {
        return txtId;
    }

    public void setTxtId(InputText txtId) {
        this.txtId = txtId;
    }

    public List<DeviceLogDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataDeviceLog();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<DeviceLogDTO> deviceLogDTO) {
        this.data = deviceLogDTO;
    }

    public DeviceLogDTO getSelectedDeviceLog() {
        return selectedDeviceLog;
    }

    public void setSelectedDeviceLog(DeviceLogDTO deviceLog) {
        this.selectedDeviceLog = deviceLog;
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
