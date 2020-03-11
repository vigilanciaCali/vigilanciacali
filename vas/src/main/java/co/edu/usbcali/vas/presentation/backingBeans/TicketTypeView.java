package co.edu.usbcali.vas.presentation.backingBeans;

import co.edu.usbcali.vas.exceptions.*;
import co.edu.usbcali.vas.model.*;
import co.edu.usbcali.vas.model.dto.TicketTypeDTO;
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
public class TicketTypeView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TicketTypeView.class);
    private InputText txtCode;
    private InputText txtCreatedBy;
    private InputText txtDescription;
    private InputText txtUpdatedBy;
    private InputText txtId;
    private Calendar txtCreatedAt;
    private Calendar txtUpdatedAt;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<TicketTypeDTO> data;
    private TicketTypeDTO selectedTicketType;
    private TicketType entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public TicketTypeView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            TicketTypeDTO ticketTypeDTO = (TicketTypeDTO) e.getObject();

            if (txtCode == null) {
                txtCode = new InputText();
            }

            txtCode.setValue(ticketTypeDTO.getCode());

            if (txtCreatedBy == null) {
                txtCreatedBy = new InputText();
            }

            txtCreatedBy.setValue(ticketTypeDTO.getCreatedBy());

            if (txtDescription == null) {
                txtDescription = new InputText();
            }

            txtDescription.setValue(ticketTypeDTO.getDescription());

            if (txtUpdatedBy == null) {
                txtUpdatedBy = new InputText();
            }

            txtUpdatedBy.setValue(ticketTypeDTO.getUpdatedBy());

            if (txtId == null) {
                txtId = new InputText();
            }

            txtId.setValue(ticketTypeDTO.getId());

            if (txtCreatedAt == null) {
                txtCreatedAt = new Calendar();
            }

            txtCreatedAt.setValue(ticketTypeDTO.getCreatedAt());

            if (txtUpdatedAt == null) {
                txtUpdatedAt = new Calendar();
            }

            txtUpdatedAt.setValue(ticketTypeDTO.getUpdatedAt());

            Integer id = FacesUtils.checkInteger(txtId);
            entity = businessDelegatorView.getTicketType(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedTicketType = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedTicketType = null;

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
            entity = (id != null) ? businessDelegatorView.getTicketType(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCode.setDisabled(false);
            txtCreatedBy.setDisabled(false);
            txtDescription.setDisabled(false);
            txtUpdatedBy.setDisabled(false);
            txtCreatedAt.setDisabled(false);
            txtUpdatedAt.setDisabled(false);
            txtId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCode.setValue(entity.getCode());
            txtCode.setDisabled(false);
            txtCreatedAt.setValue(entity.getCreatedAt());
            txtCreatedAt.setDisabled(false);
            txtCreatedBy.setValue(entity.getCreatedBy());
            txtCreatedBy.setDisabled(false);
            txtDescription.setValue(entity.getDescription());
            txtDescription.setDisabled(false);
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
        selectedTicketType = (TicketTypeDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedTicketType"));
        txtCode.setValue(selectedTicketType.getCode());
        txtCode.setDisabled(false);
        txtCreatedAt.setValue(selectedTicketType.getCreatedAt());
        txtCreatedAt.setDisabled(false);
        txtCreatedBy.setValue(selectedTicketType.getCreatedBy());
        txtCreatedBy.setDisabled(false);
        txtDescription.setValue(selectedTicketType.getDescription());
        txtDescription.setDisabled(false);
        txtUpdatedAt.setValue(selectedTicketType.getUpdatedAt());
        txtUpdatedAt.setDisabled(false);
        txtUpdatedBy.setValue(selectedTicketType.getUpdatedBy());
        txtUpdatedBy.setDisabled(false);
        txtId.setValue(selectedTicketType.getId());
        txtId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedTicketType == null) && (entity == null)) {
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
            entity = new TicketType();

            Integer id = FacesUtils.checkInteger(txtId);

            entity.setCode(FacesUtils.checkString(txtCode));
            entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
            entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
            entity.setDescription(FacesUtils.checkString(txtDescription));
            entity.setId(id);
            entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
            businessDelegatorView.saveTicketType(entity);
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
                Integer id = new Integer(selectedTicketType.getId());
                entity = businessDelegatorView.getTicketType(id);
            }

            entity.setCode(FacesUtils.checkString(txtCode));
            entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
            entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
            entity.setDescription(FacesUtils.checkString(txtDescription));
            entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
            businessDelegatorView.updateTicketType(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedTicketType = (TicketTypeDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedTicketType"));

            Integer id = new Integer(selectedTicketType.getId());
            entity = businessDelegatorView.getTicketType(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer id = FacesUtils.checkInteger(txtId);
            entity = businessDelegatorView.getTicketType(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteTicketType(entity);
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
            selectedTicketType = (TicketTypeDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedTicketType"));

            Integer id = new Integer(selectedTicketType.getId());
            entity = businessDelegatorView.getTicketType(id);
            businessDelegatorView.deleteTicketType(entity);
            data.remove(selectedTicketType);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String code, Date createdAt,
        String createdBy, String description, Integer id, Date updatedAt,
        String updatedBy) throws Exception {
        try {
            entity.setCode(FacesUtils.checkString(code));
            entity.setCreatedAt(FacesUtils.checkDate(createdAt));
            entity.setCreatedBy(FacesUtils.checkString(createdBy));
            entity.setDescription(FacesUtils.checkString(description));
            entity.setUpdatedAt(FacesUtils.checkDate(updatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(updatedBy));
            businessDelegatorView.updateTicketType(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("TicketTypeView").requestRender();
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

    public List<TicketTypeDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataTicketType();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<TicketTypeDTO> ticketTypeDTO) {
        this.data = ticketTypeDTO;
    }

    public TicketTypeDTO getSelectedTicketType() {
        return selectedTicketType;
    }

    public void setSelectedTicketType(TicketTypeDTO ticketType) {
        this.selectedTicketType = ticketType;
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
