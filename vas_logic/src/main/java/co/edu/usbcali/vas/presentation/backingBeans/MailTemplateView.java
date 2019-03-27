package co.edu.usbcali.vas.presentation.backingBeans;

import co.edu.usbcali.vas.exceptions.*;
import co.edu.usbcali.vas.model.*;
import co.edu.usbcali.vas.model.dto.MailTemplateDTO;
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
public class MailTemplateView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(MailTemplateView.class);
    private InputText txtBodyHtml;
    private InputText txtCreatedBy;
    private InputText txtEmailCc;
    private InputText txtEmailTo;
    private InputText txtLang;
    private InputText txtSubject;
    private InputText txtUpdatedBy;
    private InputText txtId_MailServer;
    private InputText txtId;
    private Calendar txtCreatedAt;
    private Calendar txtUpdatedAt;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<MailTemplateDTO> data;
    private MailTemplateDTO selectedMailTemplate;
    private MailTemplate entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public MailTemplateView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            MailTemplateDTO mailTemplateDTO = (MailTemplateDTO) e.getObject();

            if (txtBodyHtml == null) {
                txtBodyHtml = new InputText();
            }

            txtBodyHtml.setValue(mailTemplateDTO.getBodyHtml());

            if (txtCreatedBy == null) {
                txtCreatedBy = new InputText();
            }

            txtCreatedBy.setValue(mailTemplateDTO.getCreatedBy());

            if (txtEmailCc == null) {
                txtEmailCc = new InputText();
            }

            txtEmailCc.setValue(mailTemplateDTO.getEmailCc());

            if (txtEmailTo == null) {
                txtEmailTo = new InputText();
            }

            txtEmailTo.setValue(mailTemplateDTO.getEmailTo());

            if (txtLang == null) {
                txtLang = new InputText();
            }

            txtLang.setValue(mailTemplateDTO.getLang());

            if (txtSubject == null) {
                txtSubject = new InputText();
            }

            txtSubject.setValue(mailTemplateDTO.getSubject());

            if (txtUpdatedBy == null) {
                txtUpdatedBy = new InputText();
            }

            txtUpdatedBy.setValue(mailTemplateDTO.getUpdatedBy());

            if (txtId_MailServer == null) {
                txtId_MailServer = new InputText();
            }

            txtId_MailServer.setValue(mailTemplateDTO.getId_MailServer());

            if (txtId == null) {
                txtId = new InputText();
            }

            txtId.setValue(mailTemplateDTO.getId());

            if (txtCreatedAt == null) {
                txtCreatedAt = new Calendar();
            }

            txtCreatedAt.setValue(mailTemplateDTO.getCreatedAt());

            if (txtUpdatedAt == null) {
                txtUpdatedAt = new Calendar();
            }

            txtUpdatedAt.setValue(mailTemplateDTO.getUpdatedAt());

            Integer id = FacesUtils.checkInteger(txtId);
            entity = businessDelegatorView.getMailTemplate(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedMailTemplate = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedMailTemplate = null;

        if (txtBodyHtml != null) {
            txtBodyHtml.setValue(null);
            txtBodyHtml.setDisabled(true);
        }

        if (txtCreatedBy != null) {
            txtCreatedBy.setValue(null);
            txtCreatedBy.setDisabled(true);
        }

        if (txtEmailCc != null) {
            txtEmailCc.setValue(null);
            txtEmailCc.setDisabled(true);
        }

        if (txtEmailTo != null) {
            txtEmailTo.setValue(null);
            txtEmailTo.setDisabled(true);
        }

        if (txtLang != null) {
            txtLang.setValue(null);
            txtLang.setDisabled(true);
        }

        if (txtSubject != null) {
            txtSubject.setValue(null);
            txtSubject.setDisabled(true);
        }

        if (txtUpdatedBy != null) {
            txtUpdatedBy.setValue(null);
            txtUpdatedBy.setDisabled(true);
        }

        if (txtId_MailServer != null) {
            txtId_MailServer.setValue(null);
            txtId_MailServer.setDisabled(true);
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
            entity = (id != null) ? businessDelegatorView.getMailTemplate(id)
                                  : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtBodyHtml.setDisabled(false);
            txtCreatedBy.setDisabled(false);
            txtEmailCc.setDisabled(false);
            txtEmailTo.setDisabled(false);
            txtLang.setDisabled(false);
            txtSubject.setDisabled(false);
            txtUpdatedBy.setDisabled(false);
            txtId_MailServer.setDisabled(false);
            txtCreatedAt.setDisabled(false);
            txtUpdatedAt.setDisabled(false);
            txtId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtBodyHtml.setValue(entity.getBodyHtml());
            txtBodyHtml.setDisabled(false);
            txtCreatedAt.setValue(entity.getCreatedAt());
            txtCreatedAt.setDisabled(false);
            txtCreatedBy.setValue(entity.getCreatedBy());
            txtCreatedBy.setDisabled(false);
            txtEmailCc.setValue(entity.getEmailCc());
            txtEmailCc.setDisabled(false);
            txtEmailTo.setValue(entity.getEmailTo());
            txtEmailTo.setDisabled(false);
            txtLang.setValue(entity.getLang());
            txtLang.setDisabled(false);
            txtSubject.setValue(entity.getSubject());
            txtSubject.setDisabled(false);
            txtUpdatedAt.setValue(entity.getUpdatedAt());
            txtUpdatedAt.setDisabled(false);
            txtUpdatedBy.setValue(entity.getUpdatedBy());
            txtUpdatedBy.setDisabled(false);
            txtId_MailServer.setValue(entity.getMailServer().getId());
            txtId_MailServer.setDisabled(false);
            txtId.setValue(entity.getId());
            txtId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedMailTemplate = (MailTemplateDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedMailTemplate"));
        txtBodyHtml.setValue(selectedMailTemplate.getBodyHtml());
        txtBodyHtml.setDisabled(false);
        txtCreatedAt.setValue(selectedMailTemplate.getCreatedAt());
        txtCreatedAt.setDisabled(false);
        txtCreatedBy.setValue(selectedMailTemplate.getCreatedBy());
        txtCreatedBy.setDisabled(false);
        txtEmailCc.setValue(selectedMailTemplate.getEmailCc());
        txtEmailCc.setDisabled(false);
        txtEmailTo.setValue(selectedMailTemplate.getEmailTo());
        txtEmailTo.setDisabled(false);
        txtLang.setValue(selectedMailTemplate.getLang());
        txtLang.setDisabled(false);
        txtSubject.setValue(selectedMailTemplate.getSubject());
        txtSubject.setDisabled(false);
        txtUpdatedAt.setValue(selectedMailTemplate.getUpdatedAt());
        txtUpdatedAt.setDisabled(false);
        txtUpdatedBy.setValue(selectedMailTemplate.getUpdatedBy());
        txtUpdatedBy.setDisabled(false);
        txtId_MailServer.setValue(selectedMailTemplate.getId_MailServer());
        txtId_MailServer.setDisabled(false);
        txtId.setValue(selectedMailTemplate.getId());
        txtId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedMailTemplate == null) && (entity == null)) {
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
            entity = new MailTemplate();

            Integer id = FacesUtils.checkInteger(txtId);

            entity.setBodyHtml(FacesUtils.checkString(txtBodyHtml));
            entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
            entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
            entity.setEmailCc(FacesUtils.checkString(txtEmailCc));
            entity.setEmailTo(FacesUtils.checkString(txtEmailTo));
            entity.setId(id);
            entity.setLang(FacesUtils.checkString(txtLang));
            entity.setSubject(FacesUtils.checkString(txtSubject));
            entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
            entity.setMailServer((FacesUtils.checkInteger(txtId_MailServer) != null)
                ? businessDelegatorView.getMailServer(FacesUtils.checkInteger(
                        txtId_MailServer)) : null);
            businessDelegatorView.saveMailTemplate(entity);
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
                Integer id = new Integer(selectedMailTemplate.getId());
                entity = businessDelegatorView.getMailTemplate(id);
            }

            entity.setBodyHtml(FacesUtils.checkString(txtBodyHtml));
            entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
            entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
            entity.setEmailCc(FacesUtils.checkString(txtEmailCc));
            entity.setEmailTo(FacesUtils.checkString(txtEmailTo));
            entity.setLang(FacesUtils.checkString(txtLang));
            entity.setSubject(FacesUtils.checkString(txtSubject));
            entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
            entity.setMailServer((FacesUtils.checkInteger(txtId_MailServer) != null)
                ? businessDelegatorView.getMailServer(FacesUtils.checkInteger(
                        txtId_MailServer)) : null);
            businessDelegatorView.updateMailTemplate(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedMailTemplate = (MailTemplateDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedMailTemplate"));

            Integer id = new Integer(selectedMailTemplate.getId());
            entity = businessDelegatorView.getMailTemplate(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer id = FacesUtils.checkInteger(txtId);
            entity = businessDelegatorView.getMailTemplate(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteMailTemplate(entity);
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
            selectedMailTemplate = (MailTemplateDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedMailTemplate"));

            Integer id = new Integer(selectedMailTemplate.getId());
            entity = businessDelegatorView.getMailTemplate(id);
            businessDelegatorView.deleteMailTemplate(entity);
            data.remove(selectedMailTemplate);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String bodyHtml, Date createdAt,
        String createdBy, String emailCc, String emailTo, Integer id,
        String lang, String subject, Date updatedAt, String updatedBy,
        Integer id_MailServer) throws Exception {
        try {
            entity.setBodyHtml(FacesUtils.checkString(bodyHtml));
            entity.setCreatedAt(FacesUtils.checkDate(createdAt));
            entity.setCreatedBy(FacesUtils.checkString(createdBy));
            entity.setEmailCc(FacesUtils.checkString(emailCc));
            entity.setEmailTo(FacesUtils.checkString(emailTo));
            entity.setLang(FacesUtils.checkString(lang));
            entity.setSubject(FacesUtils.checkString(subject));
            entity.setUpdatedAt(FacesUtils.checkDate(updatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(updatedBy));
            businessDelegatorView.updateMailTemplate(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("MailTemplateView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtBodyHtml() {
        return txtBodyHtml;
    }

    public void setTxtBodyHtml(InputText txtBodyHtml) {
        this.txtBodyHtml = txtBodyHtml;
    }

    public InputText getTxtCreatedBy() {
        return txtCreatedBy;
    }

    public void setTxtCreatedBy(InputText txtCreatedBy) {
        this.txtCreatedBy = txtCreatedBy;
    }

    public InputText getTxtEmailCc() {
        return txtEmailCc;
    }

    public void setTxtEmailCc(InputText txtEmailCc) {
        this.txtEmailCc = txtEmailCc;
    }

    public InputText getTxtEmailTo() {
        return txtEmailTo;
    }

    public void setTxtEmailTo(InputText txtEmailTo) {
        this.txtEmailTo = txtEmailTo;
    }

    public InputText getTxtLang() {
        return txtLang;
    }

    public void setTxtLang(InputText txtLang) {
        this.txtLang = txtLang;
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

    public InputText getTxtId_MailServer() {
        return txtId_MailServer;
    }

    public void setTxtId_MailServer(InputText txtId_MailServer) {
        this.txtId_MailServer = txtId_MailServer;
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

    public List<MailTemplateDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataMailTemplate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<MailTemplateDTO> mailTemplateDTO) {
        this.data = mailTemplateDTO;
    }

    public MailTemplateDTO getSelectedMailTemplate() {
        return selectedMailTemplate;
    }

    public void setSelectedMailTemplate(MailTemplateDTO mailTemplate) {
        this.selectedMailTemplate = mailTemplate;
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
