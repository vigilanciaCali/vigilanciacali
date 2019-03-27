package co.edu.usbcali.vas.presentation.backingBeans;

import co.edu.usbcali.vas.exceptions.*;
import co.edu.usbcali.vas.model.*;
import co.edu.usbcali.vas.model.dto.TicketDTO;
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
public class TicketView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TicketView.class);
    private InputText txtCreatedBy;
    private InputText txtMessage;
    private InputText txtNote;
    private InputText txtStatus;
    private InputText txtSubject;
    private InputText txtUpdatedBy;
    private InputText txtId_TicketType;
    private InputText txtId_Users;
    private InputText txtId;
    private Calendar txtCreatedAt;
    private Calendar txtUpdatedAt;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<TicketDTO> data;
    private TicketDTO selectedTicket;
    private Ticket entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public TicketView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            TicketDTO ticketDTO = (TicketDTO) e.getObject();

            if (txtCreatedBy == null) {
                txtCreatedBy = new InputText();
            }

            txtCreatedBy.setValue(ticketDTO.getCreatedBy());

            if (txtMessage == null) {
                txtMessage = new InputText();
            }

            txtMessage.setValue(ticketDTO.getMessage());

            if (txtNote == null) {
                txtNote = new InputText();
            }

            txtNote.setValue(ticketDTO.getNote());

            if (txtStatus == null) {
                txtStatus = new InputText();
            }

            txtStatus.setValue(ticketDTO.getStatus());

            if (txtSubject == null) {
                txtSubject = new InputText();
            }

            txtSubject.setValue(ticketDTO.getSubject());

            if (txtUpdatedBy == null) {
                txtUpdatedBy = new InputText();
            }

            txtUpdatedBy.setValue(ticketDTO.getUpdatedBy());

            if (txtId_TicketType == null) {
                txtId_TicketType = new InputText();
            }

            txtId_TicketType.setValue(ticketDTO.getId_TicketType());

            if (txtId_Users == null) {
                txtId_Users = new InputText();
            }

            txtId_Users.setValue(ticketDTO.getId_Users());

            if (txtId == null) {
                txtId = new InputText();
            }

            txtId.setValue(ticketDTO.getId());

            if (txtCreatedAt == null) {
                txtCreatedAt = new Calendar();
            }

            txtCreatedAt.setValue(ticketDTO.getCreatedAt());

            if (txtUpdatedAt == null) {
                txtUpdatedAt = new Calendar();
            }

            txtUpdatedAt.setValue(ticketDTO.getUpdatedAt());

            Long id = FacesUtils.checkLong(txtId);
            entity = businessDelegatorView.getTicket(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedTicket = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedTicket = null;

        if (txtCreatedBy != null) {
            txtCreatedBy.setValue(null);
            txtCreatedBy.setDisabled(true);
        }

        if (txtMessage != null) {
            txtMessage.setValue(null);
            txtMessage.setDisabled(true);
        }

        if (txtNote != null) {
            txtNote.setValue(null);
            txtNote.setDisabled(true);
        }

        if (txtStatus != null) {
            txtStatus.setValue(null);
            txtStatus.setDisabled(true);
        }

        if (txtSubject != null) {
            txtSubject.setValue(null);
            txtSubject.setDisabled(true);
        }

        if (txtUpdatedBy != null) {
            txtUpdatedBy.setValue(null);
            txtUpdatedBy.setDisabled(true);
        }

        if (txtId_TicketType != null) {
            txtId_TicketType.setValue(null);
            txtId_TicketType.setDisabled(true);
        }

        if (txtId_Users != null) {
            txtId_Users.setValue(null);
            txtId_Users.setDisabled(true);
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
            Long id = FacesUtils.checkLong(txtId);
            entity = (id != null) ? businessDelegatorView.getTicket(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCreatedBy.setDisabled(false);
            txtMessage.setDisabled(false);
            txtNote.setDisabled(false);
            txtStatus.setDisabled(false);
            txtSubject.setDisabled(false);
            txtUpdatedBy.setDisabled(false);
            txtId_TicketType.setDisabled(false);
            txtId_Users.setDisabled(false);
            txtCreatedAt.setDisabled(false);
            txtUpdatedAt.setDisabled(false);
            txtId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCreatedAt.setValue(entity.getCreatedAt());
            txtCreatedAt.setDisabled(false);
            txtCreatedBy.setValue(entity.getCreatedBy());
            txtCreatedBy.setDisabled(false);
            txtMessage.setValue(entity.getMessage());
            txtMessage.setDisabled(false);
            txtNote.setValue(entity.getNote());
            txtNote.setDisabled(false);
            txtStatus.setValue(entity.getStatus());
            txtStatus.setDisabled(false);
            txtSubject.setValue(entity.getSubject());
            txtSubject.setDisabled(false);
            txtUpdatedAt.setValue(entity.getUpdatedAt());
            txtUpdatedAt.setDisabled(false);
            txtUpdatedBy.setValue(entity.getUpdatedBy());
            txtUpdatedBy.setDisabled(false);
            txtId_TicketType.setValue(entity.getTicketType().getId());
            txtId_TicketType.setDisabled(false);
            txtId_Users.setValue(entity.getUsers().getId());
            txtId_Users.setDisabled(false);
            txtId.setValue(entity.getId());
            txtId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedTicket = (TicketDTO) (evt.getComponent().getAttributes()
                                         .get("selectedTicket"));
        txtCreatedAt.setValue(selectedTicket.getCreatedAt());
        txtCreatedAt.setDisabled(false);
        txtCreatedBy.setValue(selectedTicket.getCreatedBy());
        txtCreatedBy.setDisabled(false);
        txtMessage.setValue(selectedTicket.getMessage());
        txtMessage.setDisabled(false);
        txtNote.setValue(selectedTicket.getNote());
        txtNote.setDisabled(false);
        txtStatus.setValue(selectedTicket.getStatus());
        txtStatus.setDisabled(false);
        txtSubject.setValue(selectedTicket.getSubject());
        txtSubject.setDisabled(false);
        txtUpdatedAt.setValue(selectedTicket.getUpdatedAt());
        txtUpdatedAt.setDisabled(false);
        txtUpdatedBy.setValue(selectedTicket.getUpdatedBy());
        txtUpdatedBy.setDisabled(false);
        txtId_TicketType.setValue(selectedTicket.getId_TicketType());
        txtId_TicketType.setDisabled(false);
        txtId_Users.setValue(selectedTicket.getId_Users());
        txtId_Users.setDisabled(false);
        txtId.setValue(selectedTicket.getId());
        txtId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedTicket == null) && (entity == null)) {
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
            entity = new Ticket();

            Long id = FacesUtils.checkLong(txtId);

            entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
            entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
            entity.setId(id);
            entity.setMessage(FacesUtils.checkString(txtMessage));
            entity.setNote(FacesUtils.checkString(txtNote));
            entity.setStatus(FacesUtils.checkString(txtStatus));
            entity.setSubject(FacesUtils.checkString(txtSubject));
            entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
            entity.setTicketType((FacesUtils.checkInteger(txtId_TicketType) != null)
                ? businessDelegatorView.getTicketType(FacesUtils.checkInteger(
                        txtId_TicketType)) : null);
            entity.setUsers((FacesUtils.checkInteger(txtId_Users) != null)
                ? businessDelegatorView.getUsers(FacesUtils.checkInteger(
                        txtId_Users)) : null);
            businessDelegatorView.saveTicket(entity);
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
                Long id = new Long(selectedTicket.getId());
                entity = businessDelegatorView.getTicket(id);
            }

            entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
            entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
            entity.setMessage(FacesUtils.checkString(txtMessage));
            entity.setNote(FacesUtils.checkString(txtNote));
            entity.setStatus(FacesUtils.checkString(txtStatus));
            entity.setSubject(FacesUtils.checkString(txtSubject));
            entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
            entity.setTicketType((FacesUtils.checkInteger(txtId_TicketType) != null)
                ? businessDelegatorView.getTicketType(FacesUtils.checkInteger(
                        txtId_TicketType)) : null);
            entity.setUsers((FacesUtils.checkInteger(txtId_Users) != null)
                ? businessDelegatorView.getUsers(FacesUtils.checkInteger(
                        txtId_Users)) : null);
            businessDelegatorView.updateTicket(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedTicket = (TicketDTO) (evt.getComponent().getAttributes()
                                             .get("selectedTicket"));

            Long id = new Long(selectedTicket.getId());
            entity = businessDelegatorView.getTicket(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long id = FacesUtils.checkLong(txtId);
            entity = businessDelegatorView.getTicket(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteTicket(entity);
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
            selectedTicket = (TicketDTO) (evt.getComponent().getAttributes()
                                             .get("selectedTicket"));

            Long id = new Long(selectedTicket.getId());
            entity = businessDelegatorView.getTicket(id);
            businessDelegatorView.deleteTicket(entity);
            data.remove(selectedTicket);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Date createdAt, String createdBy,
        Long id, String message, String note, String status, String subject,
        Date updatedAt, String updatedBy, Integer id_TicketType,
        Integer id_Users) throws Exception {
        try {
            entity.setCreatedAt(FacesUtils.checkDate(createdAt));
            entity.setCreatedBy(FacesUtils.checkString(createdBy));
            entity.setMessage(FacesUtils.checkString(message));
            entity.setNote(FacesUtils.checkString(note));
            entity.setStatus(FacesUtils.checkString(status));
            entity.setSubject(FacesUtils.checkString(subject));
            entity.setUpdatedAt(FacesUtils.checkDate(updatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(updatedBy));
            businessDelegatorView.updateTicket(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("TicketView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCreatedBy() {
        return txtCreatedBy;
    }

    public void setTxtCreatedBy(InputText txtCreatedBy) {
        this.txtCreatedBy = txtCreatedBy;
    }

    public InputText getTxtMessage() {
        return txtMessage;
    }

    public void setTxtMessage(InputText txtMessage) {
        this.txtMessage = txtMessage;
    }

    public InputText getTxtNote() {
        return txtNote;
    }

    public void setTxtNote(InputText txtNote) {
        this.txtNote = txtNote;
    }

    public InputText getTxtStatus() {
        return txtStatus;
    }

    public void setTxtStatus(InputText txtStatus) {
        this.txtStatus = txtStatus;
    }

    public InputText getTxtSubject() {
        return txtSubject;
    }

    public void setTxtSubject(InputText txtSubject) {
        this.txtSubject = txtSubject;
    }

    public InputText getTxtUpdatedBy() {
        return txtUpdatedBy;
    }

    public void setTxtUpdatedBy(InputText txtUpdatedBy) {
        this.txtUpdatedBy = txtUpdatedBy;
    }

    public InputText getTxtId_TicketType() {
        return txtId_TicketType;
    }

    public void setTxtId_TicketType(InputText txtId_TicketType) {
        this.txtId_TicketType = txtId_TicketType;
    }

    public InputText getTxtId_Users() {
        return txtId_Users;
    }

    public void setTxtId_Users(InputText txtId_Users) {
        this.txtId_Users = txtId_Users;
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

    public List<TicketDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataTicket();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<TicketDTO> ticketDTO) {
        this.data = ticketDTO;
    }

    public TicketDTO getSelectedTicket() {
        return selectedTicket;
    }

    public void setSelectedTicket(TicketDTO ticket) {
        this.selectedTicket = ticket;
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
