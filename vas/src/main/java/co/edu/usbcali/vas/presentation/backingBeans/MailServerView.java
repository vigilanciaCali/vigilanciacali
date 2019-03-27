package co.edu.usbcali.vas.presentation.backingBeans;

import co.edu.usbcali.vas.exceptions.*;
import co.edu.usbcali.vas.model.*;
import co.edu.usbcali.vas.model.dto.MailServerDTO;
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
public class MailServerView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(MailServerView.class);
    private InputText txtActive;
    private InputText txtCode;
    private InputText txtCreatedBy;
    private InputText txtName;
    private InputText txtSmtpHost;
    private InputText txtSmtpPass;
    private InputText txtSmtpPort;
    private InputText txtSmtpSsl;
    private InputText txtSmtpStarttls;
    private InputText txtSmtpUser;
    private InputText txtUpdatedBy;
    private InputText txtId;
    private Calendar txtCreatedAt;
    private Calendar txtUpdatedAt;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<MailServerDTO> data;
    private MailServerDTO selectedMailServer;
    private MailServer entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public MailServerView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            MailServerDTO mailServerDTO = (MailServerDTO) e.getObject();

            if (txtActive == null) {
                txtActive = new InputText();
            }

            txtActive.setValue(mailServerDTO.getActive());

            if (txtCode == null) {
                txtCode = new InputText();
            }

            txtCode.setValue(mailServerDTO.getCode());

            if (txtCreatedBy == null) {
                txtCreatedBy = new InputText();
            }

            txtCreatedBy.setValue(mailServerDTO.getCreatedBy());

            if (txtName == null) {
                txtName = new InputText();
            }

            txtName.setValue(mailServerDTO.getName());

            if (txtSmtpHost == null) {
                txtSmtpHost = new InputText();
            }

            txtSmtpHost.setValue(mailServerDTO.getSmtpHost());

            if (txtSmtpPass == null) {
                txtSmtpPass = new InputText();
            }

            txtSmtpPass.setValue(mailServerDTO.getSmtpPass());

            if (txtSmtpPort == null) {
                txtSmtpPort = new InputText();
            }

            txtSmtpPort.setValue(mailServerDTO.getSmtpPort());

            if (txtSmtpSsl == null) {
                txtSmtpSsl = new InputText();
            }

            txtSmtpSsl.setValue(mailServerDTO.getSmtpSsl());

            if (txtSmtpStarttls == null) {
                txtSmtpStarttls = new InputText();
            }

            txtSmtpStarttls.setValue(mailServerDTO.getSmtpStarttls());

            if (txtSmtpUser == null) {
                txtSmtpUser = new InputText();
            }

            txtSmtpUser.setValue(mailServerDTO.getSmtpUser());

            if (txtUpdatedBy == null) {
                txtUpdatedBy = new InputText();
            }

            txtUpdatedBy.setValue(mailServerDTO.getUpdatedBy());

            if (txtId == null) {
                txtId = new InputText();
            }

            txtId.setValue(mailServerDTO.getId());

            if (txtCreatedAt == null) {
                txtCreatedAt = new Calendar();
            }

            txtCreatedAt.setValue(mailServerDTO.getCreatedAt());

            if (txtUpdatedAt == null) {
                txtUpdatedAt = new Calendar();
            }

            txtUpdatedAt.setValue(mailServerDTO.getUpdatedAt());

            Integer id = FacesUtils.checkInteger(txtId);
            entity = businessDelegatorView.getMailServer(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedMailServer = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedMailServer = null;

        if (txtActive != null) {
            txtActive.setValue(null);
            txtActive.setDisabled(true);
        }

        if (txtCode != null) {
            txtCode.setValue(null);
            txtCode.setDisabled(true);
        }

        if (txtCreatedBy != null) {
            txtCreatedBy.setValue(null);
            txtCreatedBy.setDisabled(true);
        }

        if (txtName != null) {
            txtName.setValue(null);
            txtName.setDisabled(true);
        }

        if (txtSmtpHost != null) {
            txtSmtpHost.setValue(null);
            txtSmtpHost.setDisabled(true);
        }

        if (txtSmtpPass != null) {
            txtSmtpPass.setValue(null);
            txtSmtpPass.setDisabled(true);
        }

        if (txtSmtpPort != null) {
            txtSmtpPort.setValue(null);
            txtSmtpPort.setDisabled(true);
        }

        if (txtSmtpSsl != null) {
            txtSmtpSsl.setValue(null);
            txtSmtpSsl.setDisabled(true);
        }

        if (txtSmtpStarttls != null) {
            txtSmtpStarttls.setValue(null);
            txtSmtpStarttls.setDisabled(true);
        }

        if (txtSmtpUser != null) {
            txtSmtpUser.setValue(null);
            txtSmtpUser.setDisabled(true);
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
            entity = (id != null) ? businessDelegatorView.getMailServer(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActive.setDisabled(false);
            txtCode.setDisabled(false);
            txtCreatedBy.setDisabled(false);
            txtName.setDisabled(false);
            txtSmtpHost.setDisabled(false);
            txtSmtpPass.setDisabled(false);
            txtSmtpPort.setDisabled(false);
            txtSmtpSsl.setDisabled(false);
            txtSmtpStarttls.setDisabled(false);
            txtSmtpUser.setDisabled(false);
            txtUpdatedBy.setDisabled(false);
            txtCreatedAt.setDisabled(false);
            txtUpdatedAt.setDisabled(false);
            txtId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActive.setValue(entity.getActive());
            txtActive.setDisabled(false);
            txtCode.setValue(entity.getCode());
            txtCode.setDisabled(false);
            txtCreatedAt.setValue(entity.getCreatedAt());
            txtCreatedAt.setDisabled(false);
            txtCreatedBy.setValue(entity.getCreatedBy());
            txtCreatedBy.setDisabled(false);
            txtName.setValue(entity.getName());
            txtName.setDisabled(false);
            txtSmtpHost.setValue(entity.getSmtpHost());
            txtSmtpHost.setDisabled(false);
            txtSmtpPass.setValue(entity.getSmtpPass());
            txtSmtpPass.setDisabled(false);
            txtSmtpPort.setValue(entity.getSmtpPort());
            txtSmtpPort.setDisabled(false);
            txtSmtpSsl.setValue(entity.getSmtpSsl());
            txtSmtpSsl.setDisabled(false);
            txtSmtpStarttls.setValue(entity.getSmtpStarttls());
            txtSmtpStarttls.setDisabled(false);
            txtSmtpUser.setValue(entity.getSmtpUser());
            txtSmtpUser.setDisabled(false);
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
        selectedMailServer = (MailServerDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedMailServer"));
        txtActive.setValue(selectedMailServer.getActive());
        txtActive.setDisabled(false);
        txtCode.setValue(selectedMailServer.getCode());
        txtCode.setDisabled(false);
        txtCreatedAt.setValue(selectedMailServer.getCreatedAt());
        txtCreatedAt.setDisabled(false);
        txtCreatedBy.setValue(selectedMailServer.getCreatedBy());
        txtCreatedBy.setDisabled(false);
        txtName.setValue(selectedMailServer.getName());
        txtName.setDisabled(false);
        txtSmtpHost.setValue(selectedMailServer.getSmtpHost());
        txtSmtpHost.setDisabled(false);
        txtSmtpPass.setValue(selectedMailServer.getSmtpPass());
        txtSmtpPass.setDisabled(false);
        txtSmtpPort.setValue(selectedMailServer.getSmtpPort());
        txtSmtpPort.setDisabled(false);
        txtSmtpSsl.setValue(selectedMailServer.getSmtpSsl());
        txtSmtpSsl.setDisabled(false);
        txtSmtpStarttls.setValue(selectedMailServer.getSmtpStarttls());
        txtSmtpStarttls.setDisabled(false);
        txtSmtpUser.setValue(selectedMailServer.getSmtpUser());
        txtSmtpUser.setDisabled(false);
        txtUpdatedAt.setValue(selectedMailServer.getUpdatedAt());
        txtUpdatedAt.setDisabled(false);
        txtUpdatedBy.setValue(selectedMailServer.getUpdatedBy());
        txtUpdatedBy.setDisabled(false);
        txtId.setValue(selectedMailServer.getId());
        txtId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedMailServer == null) && (entity == null)) {
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
            entity = new MailServer();

            Integer id = FacesUtils.checkInteger(txtId);

            entity.setActive(FacesUtils.checkString(txtActive));
            entity.setCode(FacesUtils.checkString(txtCode));
            entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
            entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
            entity.setId(id);
            entity.setName(FacesUtils.checkString(txtName));
            entity.setSmtpHost(FacesUtils.checkString(txtSmtpHost));
            entity.setSmtpPass(FacesUtils.checkString(txtSmtpPass));
            entity.setSmtpPort(FacesUtils.checkInteger(txtSmtpPort));
            entity.setSmtpSsl(FacesUtils.checkString(txtSmtpSsl));
            entity.setSmtpStarttls(FacesUtils.checkString(txtSmtpStarttls));
            entity.setSmtpUser(FacesUtils.checkString(txtSmtpUser));
            entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
            businessDelegatorView.saveMailServer(entity);
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
                Integer id = new Integer(selectedMailServer.getId());
                entity = businessDelegatorView.getMailServer(id);
            }

            entity.setActive(FacesUtils.checkString(txtActive));
            entity.setCode(FacesUtils.checkString(txtCode));
            entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
            entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
            entity.setName(FacesUtils.checkString(txtName));
            entity.setSmtpHost(FacesUtils.checkString(txtSmtpHost));
            entity.setSmtpPass(FacesUtils.checkString(txtSmtpPass));
            entity.setSmtpPort(FacesUtils.checkInteger(txtSmtpPort));
            entity.setSmtpSsl(FacesUtils.checkString(txtSmtpSsl));
            entity.setSmtpStarttls(FacesUtils.checkString(txtSmtpStarttls));
            entity.setSmtpUser(FacesUtils.checkString(txtSmtpUser));
            entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
            businessDelegatorView.updateMailServer(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedMailServer = (MailServerDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedMailServer"));

            Integer id = new Integer(selectedMailServer.getId());
            entity = businessDelegatorView.getMailServer(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer id = FacesUtils.checkInteger(txtId);
            entity = businessDelegatorView.getMailServer(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteMailServer(entity);
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
            selectedMailServer = (MailServerDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedMailServer"));

            Integer id = new Integer(selectedMailServer.getId());
            entity = businessDelegatorView.getMailServer(id);
            businessDelegatorView.deleteMailServer(entity);
            data.remove(selectedMailServer);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String active, String code,
        Date createdAt, String createdBy, Integer id, String name,
        String smtpHost, String smtpPass, Integer smtpPort, String smtpSsl,
        String smtpStarttls, String smtpUser, Date updatedAt, String updatedBy)
        throws Exception {
        try {
            entity.setActive(FacesUtils.checkString(active));
            entity.setCode(FacesUtils.checkString(code));
            entity.setCreatedAt(FacesUtils.checkDate(createdAt));
            entity.setCreatedBy(FacesUtils.checkString(createdBy));
            entity.setName(FacesUtils.checkString(name));
            entity.setSmtpHost(FacesUtils.checkString(smtpHost));
            entity.setSmtpPass(FacesUtils.checkString(smtpPass));
            entity.setSmtpPort(FacesUtils.checkInteger(smtpPort));
            entity.setSmtpSsl(FacesUtils.checkString(smtpSsl));
            entity.setSmtpStarttls(FacesUtils.checkString(smtpStarttls));
            entity.setSmtpUser(FacesUtils.checkString(smtpUser));
            entity.setUpdatedAt(FacesUtils.checkDate(updatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(updatedBy));
            businessDelegatorView.updateMailServer(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("MailServerView").requestRender();
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

    public InputText getTxtName() {
        return txtName;
    }

    public void setTxtName(InputText txtName) {
        this.txtName = txtName;
    }

    public InputText getTxtSmtpHost() {
        return txtSmtpHost;
    }

    public void setTxtSmtpHost(InputText txtSmtpHost) {
        this.txtSmtpHost = txtSmtpHost;
    }

    public InputText getTxtSmtpPass() {
        return txtSmtpPass;
    }

    public void setTxtSmtpPass(InputText txtSmtpPass) {
        this.txtSmtpPass = txtSmtpPass;
    }

    public InputText getTxtSmtpPort() {
        return txtSmtpPort;
    }

    public void setTxtSmtpPort(InputText txtSmtpPort) {
        this.txtSmtpPort = txtSmtpPort;
    }

    public InputText getTxtSmtpSsl() {
        return txtSmtpSsl;
    }

    public void setTxtSmtpSsl(InputText txtSmtpSsl) {
        this.txtSmtpSsl = txtSmtpSsl;
    }

    public InputText getTxtSmtpStarttls() {
        return txtSmtpStarttls;
    }

    public void setTxtSmtpStarttls(InputText txtSmtpStarttls) {
        this.txtSmtpStarttls = txtSmtpStarttls;
    }

    public InputText getTxtSmtpUser() {
        return txtSmtpUser;
    }

    public void setTxtSmtpUser(InputText txtSmtpUser) {
        this.txtSmtpUser = txtSmtpUser;
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

    public List<MailServerDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataMailServer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<MailServerDTO> mailServerDTO) {
        this.data = mailServerDTO;
    }

    public MailServerDTO getSelectedMailServer() {
        return selectedMailServer;
    }

    public void setSelectedMailServer(MailServerDTO mailServer) {
        this.selectedMailServer = mailServer;
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
