package co.edu.usbcali.vas.presentation.backingBeans;

import co.edu.usbcali.vas.exceptions.*;
import co.edu.usbcali.vas.model.*;
import co.edu.usbcali.vas.model.dto.SystemMonitoringLogDTO;
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
public class SystemMonitoringLogView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SystemMonitoringLogView.class);
    private InputText txtActionName;
    private InputText txtNote;
    private InputText txtId;
    private Calendar txtActionDate;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SystemMonitoringLogDTO> data;
    private SystemMonitoringLogDTO selectedSystemMonitoringLog;
    private SystemMonitoringLog entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public SystemMonitoringLogView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            SystemMonitoringLogDTO systemMonitoringLogDTO = (SystemMonitoringLogDTO) e.getObject();

            if (txtActionName == null) {
                txtActionName = new InputText();
            }

            txtActionName.setValue(systemMonitoringLogDTO.getActionName());

            if (txtNote == null) {
                txtNote = new InputText();
            }

            txtNote.setValue(systemMonitoringLogDTO.getNote());

            if (txtId == null) {
                txtId = new InputText();
            }

            txtId.setValue(systemMonitoringLogDTO.getId());

            if (txtActionDate == null) {
                txtActionDate = new Calendar();
            }

            txtActionDate.setValue(systemMonitoringLogDTO.getActionDate());

            Long id = FacesUtils.checkLong(txtId);
            entity = businessDelegatorView.getSystemMonitoringLog(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedSystemMonitoringLog = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedSystemMonitoringLog = null;

        if (txtActionName != null) {
            txtActionName.setValue(null);
            txtActionName.setDisabled(true);
        }

        if (txtNote != null) {
            txtNote.setValue(null);
            txtNote.setDisabled(true);
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
            entity = (id != null)
                ? businessDelegatorView.getSystemMonitoringLog(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActionName.setDisabled(false);
            txtNote.setDisabled(false);
            txtActionDate.setDisabled(false);
            txtId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActionDate.setValue(entity.getActionDate());
            txtActionDate.setDisabled(false);
            txtActionName.setValue(entity.getActionName());
            txtActionName.setDisabled(false);
            txtNote.setValue(entity.getNote());
            txtNote.setDisabled(false);
            txtId.setValue(entity.getId());
            txtId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedSystemMonitoringLog = (SystemMonitoringLogDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedSystemMonitoringLog"));
        txtActionDate.setValue(selectedSystemMonitoringLog.getActionDate());
        txtActionDate.setDisabled(false);
        txtActionName.setValue(selectedSystemMonitoringLog.getActionName());
        txtActionName.setDisabled(false);
        txtNote.setValue(selectedSystemMonitoringLog.getNote());
        txtNote.setDisabled(false);
        txtId.setValue(selectedSystemMonitoringLog.getId());
        txtId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedSystemMonitoringLog == null) && (entity == null)) {
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
            entity = new SystemMonitoringLog();

            Long id = FacesUtils.checkLong(txtId);

            entity.setActionDate(FacesUtils.checkDate(txtActionDate));
            entity.setActionName(FacesUtils.checkString(txtActionName));
            entity.setId(id);
            entity.setNote(FacesUtils.checkString(txtNote));
            businessDelegatorView.saveSystemMonitoringLog(entity);
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
                Long id = new Long(selectedSystemMonitoringLog.getId());
                entity = businessDelegatorView.getSystemMonitoringLog(id);
            }

            entity.setActionDate(FacesUtils.checkDate(txtActionDate));
            entity.setActionName(FacesUtils.checkString(txtActionName));
            entity.setNote(FacesUtils.checkString(txtNote));
            businessDelegatorView.updateSystemMonitoringLog(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedSystemMonitoringLog = (SystemMonitoringLogDTO) (evt.getComponent()
                                                                       .getAttributes()
                                                                       .get("selectedSystemMonitoringLog"));

            Long id = new Long(selectedSystemMonitoringLog.getId());
            entity = businessDelegatorView.getSystemMonitoringLog(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long id = FacesUtils.checkLong(txtId);
            entity = businessDelegatorView.getSystemMonitoringLog(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteSystemMonitoringLog(entity);
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
            selectedSystemMonitoringLog = (SystemMonitoringLogDTO) (evt.getComponent()
                                                                       .getAttributes()
                                                                       .get("selectedSystemMonitoringLog"));

            Long id = new Long(selectedSystemMonitoringLog.getId());
            entity = businessDelegatorView.getSystemMonitoringLog(id);
            businessDelegatorView.deleteSystemMonitoringLog(entity);
            data.remove(selectedSystemMonitoringLog);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Date actionDate, String actionName,
        Long id, String note) throws Exception {
        try {
            entity.setActionDate(FacesUtils.checkDate(actionDate));
            entity.setActionName(FacesUtils.checkString(actionName));
            entity.setNote(FacesUtils.checkString(note));
            businessDelegatorView.updateSystemMonitoringLog(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("SystemMonitoringLogView").requestRender();
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

    public InputText getTxtNote() {
        return txtNote;
    }

    public void setTxtNote(InputText txtNote) {
        this.txtNote = txtNote;
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

    public List<SystemMonitoringLogDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataSystemMonitoringLog();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<SystemMonitoringLogDTO> systemMonitoringLogDTO) {
        this.data = systemMonitoringLogDTO;
    }

    public SystemMonitoringLogDTO getSelectedSystemMonitoringLog() {
        return selectedSystemMonitoringLog;
    }

    public void setSelectedSystemMonitoringLog(
        SystemMonitoringLogDTO systemMonitoringLog) {
        this.selectedSystemMonitoringLog = systemMonitoringLog;
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
