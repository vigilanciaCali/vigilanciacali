package co.edu.usbcali.vas.presentation.backingBeans;

import co.edu.usbcali.vas.exceptions.*;
import co.edu.usbcali.vas.model.*;
import co.edu.usbcali.vas.model.dto.VideoDTO;
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
public class VideoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VideoView.class);
    private InputText txtAnalyticData;
    private InputText txtCreatedBy;
    private InputText txtDescription;
    private InputText txtFormat;
    private InputText txtInfo;
    private InputText txtLenght;
    private InputText txtMimeType;
    private InputText txtSrc;
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
    private List<VideoDTO> data;
    private VideoDTO selectedVideo;
    private Video entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public VideoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            VideoDTO videoDTO = (VideoDTO) e.getObject();

            if (txtAnalyticData == null) {
                txtAnalyticData = new InputText();
            }

            txtAnalyticData.setValue(videoDTO.getAnalyticData());

            if (txtCreatedBy == null) {
                txtCreatedBy = new InputText();
            }

            txtCreatedBy.setValue(videoDTO.getCreatedBy());

            if (txtDescription == null) {
                txtDescription = new InputText();
            }

            txtDescription.setValue(videoDTO.getDescription());

            if (txtFormat == null) {
                txtFormat = new InputText();
            }

            txtFormat.setValue(videoDTO.getFormat());

            if (txtInfo == null) {
                txtInfo = new InputText();
            }

            txtInfo.setValue(videoDTO.getInfo());

            if (txtLenght == null) {
                txtLenght = new InputText();
            }

            txtLenght.setValue(videoDTO.getLenght());

            if (txtMimeType == null) {
                txtMimeType = new InputText();
            }

            txtMimeType.setValue(videoDTO.getMimeType());

            if (txtSrc == null) {
                txtSrc = new InputText();
            }

            txtSrc.setValue(videoDTO.getSrc());

            if (txtType == null) {
                txtType = new InputText();
            }

            txtType.setValue(videoDTO.getType());

            if (txtUpdatedBy == null) {
                txtUpdatedBy = new InputText();
            }

            txtUpdatedBy.setValue(videoDTO.getUpdatedBy());

            if (txtUrl == null) {
                txtUrl = new InputText();
            }

            txtUrl.setValue(videoDTO.getUrl());

            if (txtVideoData == null) {
                txtVideoData = new InputText();
            }

            txtVideoData.setValue(videoDTO.getVideoData());

            if (txtId_Users == null) {
                txtId_Users = new InputText();
            }

            txtId_Users.setValue(videoDTO.getId_Users());

            if (txtId == null) {
                txtId = new InputText();
            }

            txtId.setValue(videoDTO.getId());

            if (txtCreatedAt == null) {
                txtCreatedAt = new Calendar();
            }

            txtCreatedAt.setValue(videoDTO.getCreatedAt());

            if (txtUpdatedAt == null) {
                txtUpdatedAt = new Calendar();
            }

            txtUpdatedAt.setValue(videoDTO.getUpdatedAt());

            Long id = FacesUtils.checkLong(txtId);
            entity = businessDelegatorView.getVideo(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedVideo = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedVideo = null;

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
            entity = (id != null) ? businessDelegatorView.getVideo(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtAnalyticData.setDisabled(false);
            txtCreatedBy.setDisabled(false);
            txtDescription.setDisabled(false);
            txtFormat.setDisabled(false);
            txtInfo.setDisabled(false);
            txtLenght.setDisabled(false);
            txtMimeType.setDisabled(false);
            txtSrc.setDisabled(false);
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
           // txtType.setValue(entity.getType());
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
        selectedVideo = (VideoDTO) (evt.getComponent().getAttributes()
                                       .get("selectedVideo"));
        txtAnalyticData.setValue(selectedVideo.getAnalyticData());
        txtAnalyticData.setDisabled(false);
        txtCreatedAt.setValue(selectedVideo.getCreatedAt());
        txtCreatedAt.setDisabled(false);
        txtCreatedBy.setValue(selectedVideo.getCreatedBy());
        txtCreatedBy.setDisabled(false);
        txtDescription.setValue(selectedVideo.getDescription());
        txtDescription.setDisabled(false);
        txtFormat.setValue(selectedVideo.getFormat());
        txtFormat.setDisabled(false);
        txtInfo.setValue(selectedVideo.getInfo());
        txtInfo.setDisabled(false);
        txtLenght.setValue(selectedVideo.getLenght());
        txtLenght.setDisabled(false);
        txtMimeType.setValue(selectedVideo.getMimeType());
        txtMimeType.setDisabled(false);
        txtSrc.setValue(selectedVideo.getSrc());
        txtSrc.setDisabled(false);
        txtType.setValue(selectedVideo.getType());
        txtType.setDisabled(false);
        txtUpdatedAt.setValue(selectedVideo.getUpdatedAt());
        txtUpdatedAt.setDisabled(false);
        txtUpdatedBy.setValue(selectedVideo.getUpdatedBy());
        txtUpdatedBy.setDisabled(false);
        txtUrl.setValue(selectedVideo.getUrl());
        txtUrl.setDisabled(false);
        txtVideoData.setValue(selectedVideo.getVideoData());
        txtVideoData.setDisabled(false);
        txtId_Users.setValue(selectedVideo.getId_Users());
        txtId_Users.setDisabled(false);
        txtId.setValue(selectedVideo.getId());
        txtId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedVideo == null) && (entity == null)) {
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
            entity = new Video();

            Long id = FacesUtils.checkLong(txtId);

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
            //entity.setType(FacesUtils.checkString(txtType));
            entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
            entity.setUrl(FacesUtils.checkString(txtUrl));
            entity.setVideoData(FacesUtils.checkString(txtVideoData));
            entity.setUsers((FacesUtils.checkInteger(txtId_Users) != null)
                ? businessDelegatorView.getUsers(FacesUtils.checkInteger(
                        txtId_Users)) : null);
            businessDelegatorView.saveVideo(entity);
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
                Long id = new Long(selectedVideo.getId());
                entity = businessDelegatorView.getVideo(id);
            }

            entity.setAnalyticData(FacesUtils.checkString(txtAnalyticData));
            entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
            entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
            entity.setDescription(FacesUtils.checkString(txtDescription));
            entity.setFormat(FacesUtils.checkString(txtFormat));
            entity.setInfo(FacesUtils.checkString(txtInfo));
            entity.setLenght(FacesUtils.checkString(txtLenght));
            entity.setMimeType(FacesUtils.checkString(txtMimeType));
            entity.setSrc(FacesUtils.checkString(txtSrc));
           // entity.setType(FacesUtils.checkString(txtType));
            entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
            entity.setUrl(FacesUtils.checkString(txtUrl));
            entity.setVideoData(FacesUtils.checkString(txtVideoData));
            entity.setUsers((FacesUtils.checkInteger(txtId_Users) != null)
                ? businessDelegatorView.getUsers(FacesUtils.checkInteger(
                        txtId_Users)) : null);
            businessDelegatorView.updateVideo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedVideo = (VideoDTO) (evt.getComponent().getAttributes()
                                           .get("selectedVideo"));

            Long id = new Long(selectedVideo.getId());
            entity = businessDelegatorView.getVideo(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long id = FacesUtils.checkLong(txtId);
            entity = businessDelegatorView.getVideo(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteVideo(entity);
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
            selectedVideo = (VideoDTO) (evt.getComponent().getAttributes()
                                           .get("selectedVideo"));

            Long id = new Long(selectedVideo.getId());
            entity = businessDelegatorView.getVideo(id);
            businessDelegatorView.deleteVideo(entity);
            data.remove(selectedVideo);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String analyticData, Date createdAt,
        String createdBy, String description, String format, Long id,
        String info, String lenght, String mimeType, String src, String type,
        Date updatedAt, String updatedBy, String url, String videoData,
        Integer id_Users) throws Exception {
        try {
            entity.setAnalyticData(FacesUtils.checkString(analyticData));
            entity.setCreatedAt(FacesUtils.checkDate(createdAt));
            entity.setCreatedBy(FacesUtils.checkString(createdBy));
            entity.setDescription(FacesUtils.checkString(description));
            entity.setFormat(FacesUtils.checkString(format));
            entity.setInfo(FacesUtils.checkString(info));
            entity.setLenght(FacesUtils.checkString(lenght));
            entity.setMimeType(FacesUtils.checkString(mimeType));
            entity.setSrc(FacesUtils.checkString(src));
          //  entity.setType(FacesUtils.checkString(type));
            entity.setUpdatedAt(FacesUtils.checkDate(updatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(updatedBy));
            entity.setUrl(FacesUtils.checkString(url));
            entity.setVideoData(FacesUtils.checkString(videoData));
            businessDelegatorView.updateVideo(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("VideoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
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

    public List<VideoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataVideo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<VideoDTO> videoDTO) {
        this.data = videoDTO;
    }

    public VideoDTO getSelectedVideo() {
        return selectedVideo;
    }

    public void setSelectedVideo(VideoDTO video) {
        this.selectedVideo = video;
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
