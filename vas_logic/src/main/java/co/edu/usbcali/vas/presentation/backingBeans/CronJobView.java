package co.edu.usbcali.vas.presentation.backingBeans;

import co.edu.usbcali.vas.exceptions.*;
import co.edu.usbcali.vas.model.*;
import co.edu.usbcali.vas.model.dto.CronJobDTO;
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
public class CronJobView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CronJobView.class);
    private InputText txtActive;
    private InputText txtArgs;
    private InputText txtCode;
    private InputText txtCreatedBy;
    private InputText txtDescription;
    private InputText txtFunction;
    private InputText txtIntervalNumber;
    private InputText txtIntervalType;
    private InputText txtNumbercall;
    private InputText txtPriority;
    private InputText txtUpdatedBy;
    private InputText txtId;
    private Calendar txtCreatedAt;
    private Calendar txtNextcall;
    private Calendar txtUpdatedAt;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<CronJobDTO> data;
    private CronJobDTO selectedCronJob;
    private CronJob entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public CronJobView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            CronJobDTO cronJobDTO = (CronJobDTO) e.getObject();

            if (txtActive == null) {
                txtActive = new InputText();
            }

            txtActive.setValue(cronJobDTO.getActive());

            if (txtArgs == null) {
                txtArgs = new InputText();
            }

            txtArgs.setValue(cronJobDTO.getArgs());

            if (txtCode == null) {
                txtCode = new InputText();
            }

            txtCode.setValue(cronJobDTO.getCode());

            if (txtCreatedBy == null) {
                txtCreatedBy = new InputText();
            }

            txtCreatedBy.setValue(cronJobDTO.getCreatedBy());

            if (txtDescription == null) {
                txtDescription = new InputText();
            }

            txtDescription.setValue(cronJobDTO.getDescription());

            if (txtFunction == null) {
                txtFunction = new InputText();
            }

            txtFunction.setValue(cronJobDTO.getFunction());

            if (txtIntervalNumber == null) {
                txtIntervalNumber = new InputText();
            }

            txtIntervalNumber.setValue(cronJobDTO.getIntervalNumber());

            if (txtIntervalType == null) {
                txtIntervalType = new InputText();
            }

            txtIntervalType.setValue(cronJobDTO.getIntervalType());

            if (txtNumbercall == null) {
                txtNumbercall = new InputText();
            }

            txtNumbercall.setValue(cronJobDTO.getNumbercall());

            if (txtPriority == null) {
                txtPriority = new InputText();
            }

            txtPriority.setValue(cronJobDTO.getPriority());

            if (txtUpdatedBy == null) {
                txtUpdatedBy = new InputText();
            }

            txtUpdatedBy.setValue(cronJobDTO.getUpdatedBy());

            if (txtId == null) {
                txtId = new InputText();
            }

            txtId.setValue(cronJobDTO.getId());

            if (txtCreatedAt == null) {
                txtCreatedAt = new Calendar();
            }

            txtCreatedAt.setValue(cronJobDTO.getCreatedAt());

            if (txtNextcall == null) {
                txtNextcall = new Calendar();
            }

            txtNextcall.setValue(cronJobDTO.getNextcall());

            if (txtUpdatedAt == null) {
                txtUpdatedAt = new Calendar();
            }

            txtUpdatedAt.setValue(cronJobDTO.getUpdatedAt());

            Integer id = FacesUtils.checkInteger(txtId);
            entity = businessDelegatorView.getCronJob(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedCronJob = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedCronJob = null;

        if (txtActive != null) {
            txtActive.setValue(null);
            txtActive.setDisabled(true);
        }

        if (txtArgs != null) {
            txtArgs.setValue(null);
            txtArgs.setDisabled(true);
        }

        if (txtCode != null) {
            txtCode.setValue(null);
            txtCode.setDisabled(true);
        }

        if (txtCreatedBy != null) {
            txtCreatedBy.setValue(null);
            txtCreatedBy.setDisabled(true);
        }

        if (txtDescription != null) {
            txtDescription.setValue(null);
            txtDescription.setDisabled(true);
        }

        if (txtFunction != null) {
            txtFunction.setValue(null);
            txtFunction.setDisabled(true);
        }

        if (txtIntervalNumber != null) {
            txtIntervalNumber.setValue(null);
            txtIntervalNumber.setDisabled(true);
        }

        if (txtIntervalType != null) {
            txtIntervalType.setValue(null);
            txtIntervalType.setDisabled(true);
        }

        if (txtNumbercall != null) {
            txtNumbercall.setValue(null);
            txtNumbercall.setDisabled(true);
        }

        if (txtPriority != null) {
            txtPriority.setValue(null);
            txtPriority.setDisabled(true);
        }

        if (txtUpdatedBy != null) {
            txtUpdatedBy.setValue(null);
            txtUpdatedBy.setDisabled(true);
        }

        if (txtCreatedAt != null) {
            txtCreatedAt.setValue(null);
            txtCreatedAt.setDisabled(true);
        }

        if (txtNextcall != null) {
            txtNextcall.setValue(null);
            txtNextcall.setDisabled(true);
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

    public void listener_txtNextcall() {
        Date inputDate = (Date) txtNextcall.getValue();
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
            entity = (id != null) ? businessDelegatorView.getCronJob(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActive.setDisabled(false);
            txtArgs.setDisabled(false);
            txtCode.setDisabled(false);
            txtCreatedBy.setDisabled(false);
            txtDescription.setDisabled(false);
            txtFunction.setDisabled(false);
            txtIntervalNumber.setDisabled(false);
            txtIntervalType.setDisabled(false);
            txtNumbercall.setDisabled(false);
            txtPriority.setDisabled(false);
            txtUpdatedBy.setDisabled(false);
            txtCreatedAt.setDisabled(false);
            txtNextcall.setDisabled(false);
            txtUpdatedAt.setDisabled(false);
            txtId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActive.setValue(entity.getActive());
            txtActive.setDisabled(false);
            txtArgs.setValue(entity.getArgs());
            txtArgs.setDisabled(false);
            txtCode.setValue(entity.getCode());
            txtCode.setDisabled(false);
            txtCreatedAt.setValue(entity.getCreatedAt());
            txtCreatedAt.setDisabled(false);
            txtCreatedBy.setValue(entity.getCreatedBy());
            txtCreatedBy.setDisabled(false);
            txtDescription.setValue(entity.getDescription());
            txtDescription.setDisabled(false);
            txtFunction.setValue(entity.getFunction());
            txtFunction.setDisabled(false);
            txtIntervalNumber.setValue(entity.getIntervalNumber());
            txtIntervalNumber.setDisabled(false);
            txtIntervalType.setValue(entity.getIntervalType());
            txtIntervalType.setDisabled(false);
            txtNextcall.setValue(entity.getNextcall());
            txtNextcall.setDisabled(false);
            txtNumbercall.setValue(entity.getNumbercall());
            txtNumbercall.setDisabled(false);
            txtPriority.setValue(entity.getPriority());
            txtPriority.setDisabled(false);
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
        selectedCronJob = (CronJobDTO) (evt.getComponent().getAttributes()
                                           .get("selectedCronJob"));
        txtActive.setValue(selectedCronJob.getActive());
        txtActive.setDisabled(false);
        txtArgs.setValue(selectedCronJob.getArgs());
        txtArgs.setDisabled(false);
        txtCode.setValue(selectedCronJob.getCode());
        txtCode.setDisabled(false);
        txtCreatedAt.setValue(selectedCronJob.getCreatedAt());
        txtCreatedAt.setDisabled(false);
        txtCreatedBy.setValue(selectedCronJob.getCreatedBy());
        txtCreatedBy.setDisabled(false);
        txtDescription.setValue(selectedCronJob.getDescription());
        txtDescription.setDisabled(false);
        txtFunction.setValue(selectedCronJob.getFunction());
        txtFunction.setDisabled(false);
        txtIntervalNumber.setValue(selectedCronJob.getIntervalNumber());
        txtIntervalNumber.setDisabled(false);
        txtIntervalType.setValue(selectedCronJob.getIntervalType());
        txtIntervalType.setDisabled(false);
        txtNextcall.setValue(selectedCronJob.getNextcall());
        txtNextcall.setDisabled(false);
        txtNumbercall.setValue(selectedCronJob.getNumbercall());
        txtNumbercall.setDisabled(false);
        txtPriority.setValue(selectedCronJob.getPriority());
        txtPriority.setDisabled(false);
        txtUpdatedAt.setValue(selectedCronJob.getUpdatedAt());
        txtUpdatedAt.setDisabled(false);
        txtUpdatedBy.setValue(selectedCronJob.getUpdatedBy());
        txtUpdatedBy.setDisabled(false);
        txtId.setValue(selectedCronJob.getId());
        txtId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedCronJob == null) && (entity == null)) {
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
            entity = new CronJob();

            Integer id = FacesUtils.checkInteger(txtId);

            entity.setActive(FacesUtils.checkString(txtActive));
            entity.setArgs(FacesUtils.checkString(txtArgs));
            entity.setCode(FacesUtils.checkString(txtCode));
            entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
            entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
            entity.setDescription(FacesUtils.checkString(txtDescription));
            entity.setFunction(FacesUtils.checkString(txtFunction));
            entity.setId(id);
            entity.setIntervalNumber(FacesUtils.checkInteger(txtIntervalNumber));
            entity.setIntervalType(FacesUtils.checkString(txtIntervalType));
            entity.setNextcall(FacesUtils.checkDate(txtNextcall));
            entity.setNumbercall(FacesUtils.checkInteger(txtNumbercall));
            entity.setPriority(FacesUtils.checkInteger(txtPriority));
            entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
            businessDelegatorView.saveCronJob(entity);
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
                Integer id = new Integer(selectedCronJob.getId());
                entity = businessDelegatorView.getCronJob(id);
            }

            entity.setActive(FacesUtils.checkString(txtActive));
            entity.setArgs(FacesUtils.checkString(txtArgs));
            entity.setCode(FacesUtils.checkString(txtCode));
            entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
            entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
            entity.setDescription(FacesUtils.checkString(txtDescription));
            entity.setFunction(FacesUtils.checkString(txtFunction));
            entity.setIntervalNumber(FacesUtils.checkInteger(txtIntervalNumber));
            entity.setIntervalType(FacesUtils.checkString(txtIntervalType));
            entity.setNextcall(FacesUtils.checkDate(txtNextcall));
            entity.setNumbercall(FacesUtils.checkInteger(txtNumbercall));
            entity.setPriority(FacesUtils.checkInteger(txtPriority));
            entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
            businessDelegatorView.updateCronJob(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedCronJob = (CronJobDTO) (evt.getComponent().getAttributes()
                                               .get("selectedCronJob"));

            Integer id = new Integer(selectedCronJob.getId());
            entity = businessDelegatorView.getCronJob(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer id = FacesUtils.checkInteger(txtId);
            entity = businessDelegatorView.getCronJob(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteCronJob(entity);
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
            selectedCronJob = (CronJobDTO) (evt.getComponent().getAttributes()
                                               .get("selectedCronJob"));

            Integer id = new Integer(selectedCronJob.getId());
            entity = businessDelegatorView.getCronJob(id);
            businessDelegatorView.deleteCronJob(entity);
            data.remove(selectedCronJob);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String active, String args, String code,
        Date createdAt, String createdBy, String description, String function,
        Integer id, Integer intervalNumber, String intervalType, Date nextcall,
        Integer numbercall, Integer priority, Date updatedAt, String updatedBy)
        throws Exception {
        try {
            entity.setActive(FacesUtils.checkString(active));
            entity.setArgs(FacesUtils.checkString(args));
            entity.setCode(FacesUtils.checkString(code));
            entity.setCreatedAt(FacesUtils.checkDate(createdAt));
            entity.setCreatedBy(FacesUtils.checkString(createdBy));
            entity.setDescription(FacesUtils.checkString(description));
            entity.setFunction(FacesUtils.checkString(function));
            entity.setIntervalNumber(FacesUtils.checkInteger(intervalNumber));
            entity.setIntervalType(FacesUtils.checkString(intervalType));
            entity.setNextcall(FacesUtils.checkDate(nextcall));
            entity.setNumbercall(FacesUtils.checkInteger(numbercall));
            entity.setPriority(FacesUtils.checkInteger(priority));
            entity.setUpdatedAt(FacesUtils.checkDate(updatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(updatedBy));
            businessDelegatorView.updateCronJob(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("CronJobView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtActive() {
        return txtActive;
    }

    public void setTxtActive(InputText txtActive) {
        this.txtActive = txtActive;
    }

    public InputText getTxtArgs() {
        return txtArgs;
    }

    public void setTxtArgs(InputText txtArgs) {
        this.txtArgs = txtArgs;
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

    public InputText getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(InputText txtDescription) {
        this.txtDescription = txtDescription;
    }

    public InputText getTxtFunction() {
        return txtFunction;
    }

    public void setTxtFunction(InputText txtFunction) {
        this.txtFunction = txtFunction;
    }

    public InputText getTxtIntervalNumber() {
        return txtIntervalNumber;
    }

    public void setTxtIntervalNumber(InputText txtIntervalNumber) {
        this.txtIntervalNumber = txtIntervalNumber;
    }

    public InputText getTxtIntervalType() {
        return txtIntervalType;
    }

    public void setTxtIntervalType(InputText txtIntervalType) {
        this.txtIntervalType = txtIntervalType;
    }

    public InputText getTxtNumbercall() {
        return txtNumbercall;
    }

    public void setTxtNumbercall(InputText txtNumbercall) {
        this.txtNumbercall = txtNumbercall;
    }

    public InputText getTxtPriority() {
        return txtPriority;
    }

    public void setTxtPriority(InputText txtPriority) {
        this.txtPriority = txtPriority;
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

    public Calendar getTxtNextcall() {
        return txtNextcall;
    }

    public void setTxtNextcall(Calendar txtNextcall) {
        this.txtNextcall = txtNextcall;
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

    public List<CronJobDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataCronJob();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<CronJobDTO> cronJobDTO) {
        this.data = cronJobDTO;
    }

    public CronJobDTO getSelectedCronJob() {
        return selectedCronJob;
    }

    public void setSelectedCronJob(CronJobDTO cronJob) {
        this.selectedCronJob = cronJob;
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
