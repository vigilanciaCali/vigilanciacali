package co.edu.usbcali.vas.presentation.backingBeans;

import co.edu.usbcali.vas.exceptions.*;
import co.edu.usbcali.vas.model.*;
import co.edu.usbcali.vas.model.dto.VideoTempDTO;
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
public class VideoTempView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VideoTempView.class);
    private InputText txtAnalysisTime;
    private InputText txtAnalyticData;
    private InputText txtCreatedBy;
    private InputText txtDescription;
    private InputText txtFormat;
    private InputText txtInfo;
    private InputText txtLenght;
    private InputText txtMimeType;
    private InputText txtSrc;
    private InputText txtTransferTime;
    private InputText txtType;
    private InputText txtUpdatedBy;
    private InputText txtUrl;
    private InputText txtVideoData;
    private InputText txtId_Users;
    private InputText txtId;
    private Calendar txtCreatedAt;
    private Calendar txtUpdatedAt;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<VideoTempDTO> data;
    private VideoTempDTO selectedVideoTemp;
    private VideoTemp entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public VideoTempView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            VideoTempDTO videoTempDTO = (VideoTempDTO) e.getObject();

            if (txtAnalysisTime == null) {
                txtAnalysisTime = new InputText();
            }

            txtAnalysisTime.setValue(videoTempDTO.getAnalysisTime());

            if (txtAnalyticData == null) {
                txtAnalyticData = new InputText();
            }

            txtAnalyticData.setValue(videoTempDTO.getAnalyticData());

            if (txtCreatedBy == null) {
                txtCreatedBy = new InputText();
            }

            txtCreatedBy.setValue(videoTempDTO.getCreatedBy());

            if (txtDescription == null) {
                txtDescription = new InputText();
            }

            txtDescription.setValue(videoTempDTO.getDescription());

            if (txtFormat == null) {
                txtFormat = new InputText();
            }

            txtFormat.setValue(videoTempDTO.getFormat());

            if (txtInfo == null) {
                txtInfo = new InputText();
            }

            txtInfo.setValue(videoTempDTO.getInfo());

            if (txtLenght == null) {
                txtLenght = new InputText();
            }

            txtLenght.setValue(videoTempDTO.getLenght());

            if (txtMimeType == null) {
                txtMimeType = new InputText();
            }

            txtMimeType.setValue(videoTempDTO.getMimeType());

            if (txtSrc == null) {
                txtSrc = new InputText();
            }

            txtSrc.setValue(videoTempDTO.getSrc());

            if (txtTransferTime == null) {
                txtTransferTime = new InputText();
            }

            txtTransferTime.setValue(videoTempDTO.getTransferTime());

            if (txtType == null) {
                txtType = new InputText();
            }

            txtType.setValue(videoTempDTO.getType());

            if (txtUpdatedBy == null) {
                txtUpdatedBy = new InputText();
            }

            txtUpdatedBy.setValue(videoTempDTO.getUpdatedBy());

            if (txtUrl == null) {
                txtUrl = new InputText();
            }

            txtUrl.setValue(videoTempDTO.getUrl());

            if (txtVideoData == null) {
                txtVideoData = new InputText();
            }

            txtVideoData.setValue(videoTempDTO.getVideoData());

            if (txtId_Users == null) {
                txtId_Users = new InputText();
            }

            txtId_Users.setValue(videoTempDTO.getId_Users());

            if (txtId == null) {
                txtId = new InputText();
            }

            txtId.setValue(videoTempDTO.getId());

            if (txtCreatedAt == null) {
                txtCreatedAt = new Calendar();
            }

            txtCreatedAt.setValue(videoTempDTO.getCreatedAt());

            if (txtUpdatedAt == null) {
                txtUpdatedAt = new Calendar();
            }

            txtUpdatedAt.setValue(videoTempDTO.getUpdatedAt());

            Long id = FacesUtils.checkLong(txtId);
            entity = businessDelegatorView.getVideoTemp(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedVideoTemp = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedVideoTemp = null;

        if (txtAnalysisTime != null) {
            txtAnalysisTime.setValue(null);
            txtAnalysisTime.setDisabled(true);
        }

        if (txtAnalyticData != null) {
            txtAnalyticData.setValue(null);
            txtAnalyticData.setDisabled(true);
        }

        if (txtCreatedBy != null) {
            txtCreatedBy.setValue(null);
            txtCreatedBy.setDisabled(true);
        }

        if (txtDescription != null) {
            txtDescription.setValue(null);
            txtDescription.setDisabled(true);
        }

        if (txtFormat != null) {
            txtFormat.setValue(null);
            txtFormat.setDisabled(true);
        }

        if (txtInfo != null) {
            txtInfo.setValue(null);
            txtInfo.setDisabled(true);
        }

        if (txtLenght != null) {
            txtLenght.setValue(null);
            txtLenght.setDisabled(true);
        }

        if (txtMimeType != null) {
            txtMimeType.setValue(null);
            txtMimeType.setDisabled(true);
        }

        if (txtSrc != null) {
            txtSrc.setValue(null);
            txtSrc.setDisabled(true);
        }

        if (txtTransferTime != null) {
            txtTransferTime.setValue(null);
            txtTransferTime.setDisabled(true);
        }

        if (txtType != null) {
            txtType.setValue(null);
            txtType.setDisabled(true);
        }

        if (txtUpdatedBy != null) {
            txtUpdatedBy.setValue(null);
            txtUpdatedBy.setDisabled(true);
        }

        if (txtUrl != null) {
            txtUrl.setValue(null);
            txtUrl.setDisabled(true);
        }

        if (txtVideoData != null) {
            txtVideoData.setValue(null);
            txtVideoData.setDisabled(true);
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
            entity = (id != null) ? businessDelegatorView.getVideoTemp(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtAnalysisTime.setDisabled(false);
            txtAnalyticData.setDisabled(false);
            txtCreatedBy.setDisabled(false);
            txtDescription.setDisabled(false);
            txtFormat.setDisabled(false);
            txtInfo.setDisabled(false);
            txtLenght.setDisabled(false);
            txtMimeType.setDisabled(false);
            txtSrc.setDisabled(false);
            txtTransferTime.setDisabled(false);
            txtType.setDisabled(false);
            txtUpdatedBy.setDisabled(false);
            txtUrl.setDisabled(false);
            txtVideoData.setDisabled(false);
            txtId_Users.setDisabled(false);
            txtCreatedAt.setDisabled(false);
            txtUpdatedAt.setDisabled(false);
            txtId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtAnalysisTime.setValue(entity.getAnalysisTime());
            txtAnalysisTime.setDisabled(false);
            txtAnalyticData.setValue(entity.getAnalyticData());
            txtAnalyticData.setDisabled(false);
            txtCreatedAt.setValue(entity.getCreatedAt());
            txtCreatedAt.setDisabled(false);
            txtCreatedBy.setValue(entity.getCreatedBy());
            txtCreatedBy.setDisabled(false);
            txtDescription.setValue(entity.getDescription());
            txtDescription.setDisabled(false);
            txtFormat.setValue(entity.getFormat());
            txtFormat.setDisabled(false);
            txtInfo.setValue(entity.getInfo());
            txtInfo.setDisabled(false);
            txtLenght.setValue(entity.getLenght());
            txtLenght.setDisabled(false);
            txtMimeType.setValue(entity.getMimeType());
            txtMimeType.setDisabled(false);
            txtSrc.setValue(entity.getSrc());
            txtSrc.setDisabled(false);
            txtTransferTime.setValue(entity.getTransferTime());
            txtTransferTime.setDisabled(false);
            txtType.setValue(entity.getType());
            txtType.setDisabled(false);
            txtUpdatedAt.setValue(entity.getUpdatedAt());
            txtUpdatedAt.setDisabled(false);
            txtUpdatedBy.setValue(entity.getUpdatedBy());
            txtUpdatedBy.setDisabled(false);
            txtUrl.setValue(entity.getUrl());
            txtUrl.setDisabled(false);
            txtVideoData.setValue(entity.getVideoData());
            txtVideoData.setDisabled(false);
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
        selectedVideoTemp = (VideoTempDTO) (evt.getComponent().getAttributes()
                                               .get("selectedVideoTemp"));
        txtAnalysisTime.setValue(selectedVideoTemp.getAnalysisTime());
        txtAnalysisTime.setDisabled(false);
        txtAnalyticData.setValue(selectedVideoTemp.getAnalyticData());
        txtAnalyticData.setDisabled(false);
        txtCreatedAt.setValue(selectedVideoTemp.getCreatedAt());
        txtCreatedAt.setDisabled(false);
        txtCreatedBy.setValue(selectedVideoTemp.getCreatedBy());
        txtCreatedBy.setDisabled(false);
        txtDescription.setValue(selectedVideoTemp.getDescription());
        txtDescription.setDisabled(false);
        txtFormat.setValue(selectedVideoTemp.getFormat());
        txtFormat.setDisabled(false);
        txtInfo.setValue(selectedVideoTemp.getInfo());
        txtInfo.setDisabled(false);
        txtLenght.setValue(selectedVideoTemp.getLenght());
        txtLenght.setDisabled(false);
        txtMimeType.setValue(selectedVideoTemp.getMimeType());
        txtMimeType.setDisabled(false);
        txtSrc.setValue(selectedVideoTemp.getSrc());
        txtSrc.setDisabled(false);
        txtTransferTime.setValue(selectedVideoTemp.getTransferTime());
        txtTransferTime.setDisabled(false);
        txtType.setValue(selectedVideoTemp.getType());
        txtType.setDisabled(false);
        txtUpdatedAt.setValue(selectedVideoTemp.getUpdatedAt());
        txtUpdatedAt.setDisabled(false);
        txtUpdatedBy.setValue(selectedVideoTemp.getUpdatedBy());
        txtUpdatedBy.setDisabled(false);
        txtUrl.setValue(selectedVideoTemp.getUrl());
        txtUrl.setDisabled(false);
        txtVideoData.setValue(selectedVideoTemp.getVideoData());
        txtVideoData.setDisabled(false);
        txtId_Users.setValue(selectedVideoTemp.getId_Users());
        txtId_Users.setDisabled(false);
        txtId.setValue(selectedVideoTemp.getId());
        txtId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedVideoTemp == null) && (entity == null)) {
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
            entity = new VideoTemp();

            Long id = FacesUtils.checkLong(txtId);

            entity.setAnalysisTime(FacesUtils.checkLong(txtAnalysisTime));
            entity.setAnalyticData(FacesUtils.checkString(txtAnalyticData));
            entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
            entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
            entity.setDescription(FacesUtils.checkString(txtDescription));
            entity.setFormat(FacesUtils.checkString(txtFormat));
            entity.setId(id);
            entity.setInfo(FacesUtils.checkString(txtInfo));
            entity.setLenght(FacesUtils.checkString(txtLenght));
            entity.setMimeType(FacesUtils.checkString(txtMimeType));
            entity.setSrc(FacesUtils.checkString(txtSrc));
            entity.setTransferTime(FacesUtils.checkLong(txtTransferTime));
            entity.setType(FacesUtils.checkString(txtType));
            entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
            entity.setUrl(FacesUtils.checkString(txtUrl));
            entity.setVideoData(FacesUtils.checkString(txtVideoData));
            entity.setUsers((FacesUtils.checkInteger(txtId_Users) != null)
                ? businessDelegatorView.getUsers(FacesUtils.checkInteger(
                        txtId_Users)) : null);
            businessDelegatorView.saveVideoTemp(entity);
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
                Long id = new Long(selectedVideoTemp.getId());
                entity = businessDelegatorView.getVideoTemp(id);
            }

            entity.setAnalysisTime(FacesUtils.checkLong(txtAnalysisTime));
            entity.setAnalyticData(FacesUtils.checkString(txtAnalyticData));
            entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
            entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
            entity.setDescription(FacesUtils.checkString(txtDescription));
            entity.setFormat(FacesUtils.checkString(txtFormat));
            entity.setInfo(FacesUtils.checkString(txtInfo));
            entity.setLenght(FacesUtils.checkString(txtLenght));
            entity.setMimeType(FacesUtils.checkString(txtMimeType));
            entity.setSrc(FacesUtils.checkString(txtSrc));
            entity.setTransferTime(FacesUtils.checkLong(txtTransferTime));
            entity.setType(FacesUtils.checkString(txtType));
            entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
            entity.setUrl(FacesUtils.checkString(txtUrl));
            entity.setVideoData(FacesUtils.checkString(txtVideoData));
            entity.setUsers((FacesUtils.checkInteger(txtId_Users) != null)
                ? businessDelegatorView.getUsers(FacesUtils.checkInteger(
                        txtId_Users)) : null);
            businessDelegatorView.updateVideoTemp(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedVideoTemp = (VideoTempDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedVideoTemp"));

            Long id = new Long(selectedVideoTemp.getId());
            entity = businessDelegatorView.getVideoTemp(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long id = FacesUtils.checkLong(txtId);
            entity = businessDelegatorView.getVideoTemp(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteVideoTemp(entity);
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
            selectedVideoTemp = (VideoTempDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedVideoTemp"));

            Long id = new Long(selectedVideoTemp.getId());
            entity = businessDelegatorView.getVideoTemp(id);
            businessDelegatorView.deleteVideoTemp(entity);
            data.remove(selectedVideoTemp);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long analysisTime, String analyticData,
        Date createdAt, String createdBy, String description, String format,
        Long id, String info, String lenght, String mimeType, String src,
        Long transferTime, String type, Date updatedAt, String updatedBy,
        String url, String videoData, Integer id_Users)
        throws Exception {
        try {
            entity.setAnalysisTime(FacesUtils.checkLong(analysisTime));
            entity.setAnalyticData(FacesUtils.checkString(analyticData));
            entity.setCreatedAt(FacesUtils.checkDate(createdAt));
            entity.setCreatedBy(FacesUtils.checkString(createdBy));
            entity.setDescription(FacesUtils.checkString(description));
            entity.setFormat(FacesUtils.checkString(format));
            entity.setInfo(FacesUtils.checkString(info));
            entity.setLenght(FacesUtils.checkString(lenght));
            entity.setMimeType(FacesUtils.checkString(mimeType));
            entity.setSrc(FacesUtils.checkString(src));
            entity.setTransferTime(FacesUtils.checkLong(transferTime));
            entity.setType(FacesUtils.checkString(type));
            entity.setUpdatedAt(FacesUtils.checkDate(updatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(updatedBy));
            entity.setUrl(FacesUtils.checkString(url));
            entity.setVideoData(FacesUtils.checkString(videoData));
            businessDelegatorView.updateVideoTemp(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("VideoTempView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtAnalysisTime() {
        return txtAnalysisTime;
    }

    public void setTxtAnalysisTime(InputText txtAnalysisTime) {
        this.txtAnalysisTime = txtAnalysisTime;
    }

    public InputText getTxtAnalyticData() {
        return txtAnalyticData;
    }

    public void setTxtAnalyticData(InputText txtAnalyticData) {
        this.txtAnalyticData = txtAnalyticData;
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

    public InputText getTxtFormat() {
        return txtFormat;
    }

    public void setTxtFormat(InputText txtFormat) {
        this.txtFormat = txtFormat;
    }

    public InputText getTxtInfo() {
        return txtInfo;
    }

    public void setTxtInfo(InputText txtInfo) {
        this.txtInfo = txtInfo;
    }

    public InputText getTxtLenght() {
        return txtLenght;
    }

    public void setTxtLenght(InputText txtLenght) {
        this.txtLenght = txtLenght;
    }

    public InputText getTxtMimeType() {
        return txtMimeType;
    }

    public void setTxtMimeType(InputText txtMimeType) {
        this.txtMimeType = txtMimeType;
    }

    public InputText getTxtSrc() {
        return txtSrc;
    }

    public void setTxtSrc(InputText txtSrc) {
        this.txtSrc = txtSrc;
    }

    public InputText getTxtTransferTime() {
        return txtTransferTime;
    }

    public void setTxtTransferTime(InputText txtTransferTime) {
        this.txtTransferTime = txtTransferTime;
    }

    public InputText getTxtType() {
        return txtType;
    }

    public void setTxtType(InputText txtType) {
        this.txtType = txtType;
    }

    public InputText getTxtUpdatedBy() {
        return txtUpdatedBy;
    }

    public void setTxtUpdatedBy(InputText txtUpdatedBy) {
        this.txtUpdatedBy = txtUpdatedBy;
    }

    public InputText getTxtUrl() {
        return txtUrl;
    }

    public void setTxtUrl(InputText txtUrl) {
        this.txtUrl = txtUrl;
    }

    public InputText getTxtVideoData() {
        return txtVideoData;
    }

    public void setTxtVideoData(InputText txtVideoData) {
        this.txtVideoData = txtVideoData;
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

    public List<VideoTempDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataVideoTemp();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<VideoTempDTO> videoTempDTO) {
        this.data = videoTempDTO;
    }

    public VideoTempDTO getSelectedVideoTemp() {
        return selectedVideoTemp;
    }

    public void setSelectedVideoTemp(VideoTempDTO videoTemp) {
        this.selectedVideoTemp = videoTemp;
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
