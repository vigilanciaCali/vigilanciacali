package co.edu.usbcali.vas.presentation.backingBeans;

import co.edu.usbcali.vas.exceptions.*;
import co.edu.usbcali.vas.model.*;
import co.edu.usbcali.vas.model.dto.MessageBoxDTO;
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
public class MessageBoxView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(MessageBoxView.class);
    private InputText txtCreatedBy;
    private InputText txtLink;
    private InputText txtMessage;
    private InputText txtPictureLink;
    private InputText txtRead;
    private InputText txtSent;
    private InputText txtSubject;
    private InputText txtUpdatedBy;
    private InputText txtId_Users;
    private InputText txtId;
    private Calendar txtCreatedAt;
    private Calendar txtUpdatedAt;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<MessageBoxDTO> data;
    private MessageBoxDTO selectedMessageBox;
    private MessageBox entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public MessageBoxView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            MessageBoxDTO messageBoxDTO = (MessageBoxDTO) e.getObject();

            if (txtCreatedBy == null) {
                txtCreatedBy = new InputText();
            }

            txtCreatedBy.setValue(messageBoxDTO.getCreatedBy());

            if (txtLink == null) {
                txtLink = new InputText();
            }

            txtLink.setValue(messageBoxDTO.getLink());

            if (txtMessage == null) {
                txtMessage = new InputText();
            }

            txtMessage.setValue(messageBoxDTO.getMessage());

            if (txtPictureLink == null) {
                txtPictureLink = new InputText();
            }

            txtPictureLink.setValue(messageBoxDTO.getPictureLink());

            if (txtRead == null) {
                txtRead = new InputText();
            }

            txtRead.setValue(messageBoxDTO.getRead());

            if (txtSent == null) {
                txtSent = new InputText();
            }

            txtSent.setValue(messageBoxDTO.getSent());

            if (txtSubject == null) {
                txtSubject = new InputText();
            }

            txtSubject.setValue(messageBoxDTO.getSubject());

            if (txtUpdatedBy == null) {
                txtUpdatedBy = new InputText();
            }

            txtUpdatedBy.setValue(messageBoxDTO.getUpdatedBy());

            if (txtId_Users == null) {
                txtId_Users = new InputText();
            }

            txtId_Users.setValue(messageBoxDTO.getId_Users());

            if (txtId == null) {
                txtId = new InputText();
            }

            txtId.setValue(messageBoxDTO.getId());

            if (txtCreatedAt == null) {
                txtCreatedAt = new Calendar();
            }

            txtCreatedAt.setValue(messageBoxDTO.getCreatedAt());

            if (txtUpdatedAt == null) {
                txtUpdatedAt = new Calendar();
            }

            txtUpdatedAt.setValue(messageBoxDTO.getUpdatedAt());

            Long id = FacesUtils.checkLong(txtId);
            entity = businessDelegatorView.getMessageBox(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedMessageBox = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedMessageBox = null;

        if (txtCreatedBy != null) {
            txtCreatedBy.setValue(null);
            txtCreatedBy.setDisabled(true);
        }

        if (txtLink != null) {
            txtLink.setValue(null);
            txtLink.setDisabled(true);
        }

        if (txtMessage != null) {
            txtMessage.setValue(null);
            txtMessage.setDisabled(true);
        }

        if (txtPictureLink != null) {
            txtPictureLink.setValue(null);
            txtPictureLink.setDisabled(true);
        }

        if (txtRead != null) {
            txtRead.setValue(null);
            txtRead.setDisabled(true);
        }

        if (txtSent != null) {
            txtSent.setValue(null);
            txtSent.setDisabled(true);
        }

        if (txtSubject != null) {
            txtSubject.setValue(null);
            txtSubject.setDisabled(true);
        }

        if (txtUpdatedBy != null) {
            txtUpdatedBy.setValue(null);
            txtUpdatedBy.setDisabled(true);
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
            entity = (id != null) ? businessDelegatorView.getMessageBox(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCreatedBy.setDisabled(false);
            txtLink.setDisabled(false);
            txtMessage.setDisabled(false);
            txtPictureLink.setDisabled(false);
            txtRead.setDisabled(false);
            txtSent.setDisabled(false);
            txtSubject.setDisabled(false);
            txtUpdatedBy.setDisabled(false);
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
            txtLink.setValue(entity.getLink());
            txtLink.setDisabled(false);
            txtMessage.setValue(entity.getMessage());
            txtMessage.setDisabled(false);
            txtPictureLink.setValue(entity.getPictureLink());
            txtPictureLink.setDisabled(false);
            txtRead.setValue(entity.getRead());
            txtRead.setDisabled(false);
            txtSent.setValue(entity.getSent());
            txtSent.setDisabled(false);
            txtSubject.setValue(entity.getSubject());
            txtSubject.setDisabled(false);
            txtUpdatedAt.setValue(entity.getUpdatedAt());
            txtUpdatedAt.setDisabled(false);
            txtUpdatedBy.setValue(entity.getUpdatedBy());
            txtUpdatedBy.setDisabled(false);
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
        selectedMessageBox = (MessageBoxDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedMessageBox"));
        txtCreatedAt.setValue(selectedMessageBox.getCreatedAt());
        txtCreatedAt.setDisabled(false);
        txtCreatedBy.setValue(selectedMessageBox.getCreatedBy());
        txtCreatedBy.setDisabled(false);
        txtLink.setValue(selectedMessageBox.getLink());
        txtLink.setDisabled(false);
        txtMessage.setValue(selectedMessageBox.getMessage());
        txtMessage.setDisabled(false);
        txtPictureLink.setValue(selectedMessageBox.getPictureLink());
        txtPictureLink.setDisabled(false);
        txtRead.setValue(selectedMessageBox.getRead());
        txtRead.setDisabled(false);
        txtSent.setValue(selectedMessageBox.getSent());
        txtSent.setDisabled(false);
        txtSubject.setValue(selectedMessageBox.getSubject());
        txtSubject.setDisabled(false);
        txtUpdatedAt.setValue(selectedMessageBox.getUpdatedAt());
        txtUpdatedAt.setDisabled(false);
        txtUpdatedBy.setValue(selectedMessageBox.getUpdatedBy());
        txtUpdatedBy.setDisabled(false);
        txtId_Users.setValue(selectedMessageBox.getId_Users());
        txtId_Users.setDisabled(false);
        txtId.setValue(selectedMessageBox.getId());
        txtId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedMessageBox == null) && (entity == null)) {
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
            entity = new MessageBox();

            Long id = FacesUtils.checkLong(txtId);

            entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
            entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
            entity.setId(id);
            entity.setLink(FacesUtils.checkString(txtLink));
            entity.setMessage(FacesUtils.checkString(txtMessage));
            entity.setPictureLink(FacesUtils.checkString(txtPictureLink));
            entity.setRead(FacesUtils.checkString(txtRead));
            entity.setSent(FacesUtils.checkString(txtSent));
            entity.setSubject(FacesUtils.checkString(txtSubject));
            entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
            entity.setUsers((FacesUtils.checkInteger(txtId_Users) != null)
                ? businessDelegatorView.getUsers(FacesUtils.checkInteger(
                        txtId_Users)) : null);
            businessDelegatorView.saveMessageBox(entity);
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
                Long id = new Long(selectedMessageBox.getId());
                entity = businessDelegatorView.getMessageBox(id);
            }

            entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
            entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
            entity.setLink(FacesUtils.checkString(txtLink));
            entity.setMessage(FacesUtils.checkString(txtMessage));
            entity.setPictureLink(FacesUtils.checkString(txtPictureLink));
            entity.setRead(FacesUtils.checkString(txtRead));
            entity.setSent(FacesUtils.checkString(txtSent));
            entity.setSubject(FacesUtils.checkString(txtSubject));
            entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
            entity.setUsers((FacesUtils.checkInteger(txtId_Users) != null)
                ? businessDelegatorView.getUsers(FacesUtils.checkInteger(
                        txtId_Users)) : null);
            businessDelegatorView.updateMessageBox(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedMessageBox = (MessageBoxDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedMessageBox"));

            Long id = new Long(selectedMessageBox.getId());
            entity = businessDelegatorView.getMessageBox(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long id = FacesUtils.checkLong(txtId);
            entity = businessDelegatorView.getMessageBox(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteMessageBox(entity);
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
            selectedMessageBox = (MessageBoxDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedMessageBox"));

            Long id = new Long(selectedMessageBox.getId());
            entity = businessDelegatorView.getMessageBox(id);
            businessDelegatorView.deleteMessageBox(entity);
            data.remove(selectedMessageBox);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Date createdAt, String createdBy,
        Long id, String link, String message, String pictureLink, String read,
        String sent, String subject, Date updatedAt, String updatedBy,
        Integer id_Users) throws Exception {
        try {
            entity.setCreatedAt(FacesUtils.checkDate(createdAt));
            entity.setCreatedBy(FacesUtils.checkString(createdBy));
            entity.setLink(FacesUtils.checkString(link));
            entity.setMessage(FacesUtils.checkString(message));
            entity.setPictureLink(FacesUtils.checkString(pictureLink));
            entity.setRead(FacesUtils.checkString(read));
            entity.setSent(FacesUtils.checkString(sent));
            entity.setSubject(FacesUtils.checkString(subject));
            entity.setUpdatedAt(FacesUtils.checkDate(updatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(updatedBy));
            businessDelegatorView.updateMessageBox(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("MessageBoxView").requestRender();
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

    public InputText getTxtLink() {
        return txtLink;
    }

    public void setTxtLink(InputText txtLink) {
        this.txtLink = txtLink;
    }

    public InputText getTxtMessage() {
        return txtMessage;
    }

    public void setTxtMessage(InputText txtMessage) {
        this.txtMessage = txtMessage;
    }

    public InputText getTxtPictureLink() {
        return txtPictureLink;
    }

    public void setTxtPictureLink(InputText txtPictureLink) {
        this.txtPictureLink = txtPictureLink;
    }

    public InputText getTxtRead() {
        return txtRead;
    }

    public void setTxtRead(InputText txtRead) {
        this.txtRead = txtRead;
    }

    public InputText getTxtSent() {
        return txtSent;
    }

    public void setTxtSent(InputText txtSent) {
        this.txtSent = txtSent;
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

    public List<MessageBoxDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataMessageBox();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<MessageBoxDTO> messageBoxDTO) {
        this.data = messageBoxDTO;
    }

    public MessageBoxDTO getSelectedMessageBox() {
        return selectedMessageBox;
    }

    public void setSelectedMessageBox(MessageBoxDTO messageBox) {
        this.selectedMessageBox = messageBox;
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
