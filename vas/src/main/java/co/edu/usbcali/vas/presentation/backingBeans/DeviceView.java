package co.edu.usbcali.vas.presentation.backingBeans;

import co.edu.usbcali.vas.exceptions.*;
import co.edu.usbcali.vas.model.*;
import co.edu.usbcali.vas.model.dto.DeviceDTO;
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
public class DeviceView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(DeviceView.class);
    private InputText txtActive;
    private InputText txtChannelAmount;
    private InputText txtCreatedBy;
    private InputText txtDescription;
    private InputText txtIpAddress;
    private InputText txtLocation;
    private InputText txtLogin;
    private InputText txtManufacturer;
    private InputText txtModel;
    private InputText txtNote;
    private InputText txtPassword;
    private InputText txtPort;
    private InputText txtSerialNumber;
    private InputText txtType;
    private InputText txtUpdatedBy;
    private InputText txtId;
    private Calendar txtCreatedAt;
    private Calendar txtUpdatedAt;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<DeviceDTO> data;
    private DeviceDTO selectedDevice;
    private Device entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public DeviceView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            DeviceDTO deviceDTO = (DeviceDTO) e.getObject();

            if (txtActive == null) {
                txtActive = new InputText();
            }

            txtActive.setValue(deviceDTO.getActive());

            if (txtChannelAmount == null) {
                txtChannelAmount = new InputText();
            }

            txtChannelAmount.setValue(deviceDTO.getChannelAmount());

            if (txtCreatedBy == null) {
                txtCreatedBy = new InputText();
            }

            txtCreatedBy.setValue(deviceDTO.getCreatedBy());

            if (txtDescription == null) {
                txtDescription = new InputText();
            }

            txtDescription.setValue(deviceDTO.getDescription());

            if (txtIpAddress == null) {
                txtIpAddress = new InputText();
            }

            txtIpAddress.setValue(deviceDTO.getIpAddress());

            if (txtLocation == null) {
                txtLocation = new InputText();
            }

            txtLocation.setValue(deviceDTO.getLocation());

            if (txtLogin == null) {
                txtLogin = new InputText();
            }

            txtLogin.setValue(deviceDTO.getLogin());

            if (txtManufacturer == null) {
                txtManufacturer = new InputText();
            }

            txtManufacturer.setValue(deviceDTO.getManufacturer());

            if (txtModel == null) {
                txtModel = new InputText();
            }

            txtModel.setValue(deviceDTO.getModel());

            if (txtNote == null) {
                txtNote = new InputText();
            }

            txtNote.setValue(deviceDTO.getNote());

            if (txtPassword == null) {
                txtPassword = new InputText();
            }

            txtPassword.setValue(deviceDTO.getPassword());

            if (txtPort == null) {
                txtPort = new InputText();
            }

            txtPort.setValue(deviceDTO.getPort());

            if (txtSerialNumber == null) {
                txtSerialNumber = new InputText();
            }

            txtSerialNumber.setValue(deviceDTO.getSerialNumber());

            if (txtType == null) {
                txtType = new InputText();
            }

            txtType.setValue(deviceDTO.getType());

            if (txtUpdatedBy == null) {
                txtUpdatedBy = new InputText();
            }

            txtUpdatedBy.setValue(deviceDTO.getUpdatedBy());

            if (txtId == null) {
                txtId = new InputText();
            }

            txtId.setValue(deviceDTO.getId());

            if (txtCreatedAt == null) {
                txtCreatedAt = new Calendar();
            }

            txtCreatedAt.setValue(deviceDTO.getCreatedAt());

            if (txtUpdatedAt == null) {
                txtUpdatedAt = new Calendar();
            }

            txtUpdatedAt.setValue(deviceDTO.getUpdatedAt());

            Integer id = FacesUtils.checkInteger(txtId);
            entity = businessDelegatorView.getDevice(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedDevice = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedDevice = null;

        if (txtActive != null) {
            txtActive.setValue(null);
            txtActive.setDisabled(true);
        }

        if (txtChannelAmount != null) {
            txtChannelAmount.setValue(null);
            txtChannelAmount.setDisabled(true);
        }

        if (txtCreatedBy != null) {
            txtCreatedBy.setValue(null);
            txtCreatedBy.setDisabled(true);
        }

        if (txtDescription != null) {
            txtDescription.setValue(null);
            txtDescription.setDisabled(true);
        }

        if (txtIpAddress != null) {
            txtIpAddress.setValue(null);
            txtIpAddress.setDisabled(true);
        }

        if (txtLocation != null) {
            txtLocation.setValue(null);
            txtLocation.setDisabled(true);
        }

        if (txtLogin != null) {
            txtLogin.setValue(null);
            txtLogin.setDisabled(true);
        }

        if (txtManufacturer != null) {
            txtManufacturer.setValue(null);
            txtManufacturer.setDisabled(true);
        }

        if (txtModel != null) {
            txtModel.setValue(null);
            txtModel.setDisabled(true);
        }

        if (txtNote != null) {
            txtNote.setValue(null);
            txtNote.setDisabled(true);
        }

        if (txtPassword != null) {
            txtPassword.setValue(null);
            txtPassword.setDisabled(true);
        }

        if (txtPort != null) {
            txtPort.setValue(null);
            txtPort.setDisabled(true);
        }

        if (txtSerialNumber != null) {
            txtSerialNumber.setValue(null);
            txtSerialNumber.setDisabled(true);
        }

        if (txtType != null) {
            txtType.setValue(null);
            txtType.setDisabled(true);
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
            entity = (id != null) ? businessDelegatorView.getDevice(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActive.setDisabled(false);
            txtChannelAmount.setDisabled(false);
            txtCreatedBy.setDisabled(false);
            txtDescription.setDisabled(false);
            txtIpAddress.setDisabled(false);
            txtLocation.setDisabled(false);
            txtLogin.setDisabled(false);
            txtManufacturer.setDisabled(false);
            txtModel.setDisabled(false);
            txtNote.setDisabled(false);
            txtPassword.setDisabled(false);
            txtPort.setDisabled(false);
            txtSerialNumber.setDisabled(false);
            txtType.setDisabled(false);
            txtUpdatedBy.setDisabled(false);
            txtCreatedAt.setDisabled(false);
            txtUpdatedAt.setDisabled(false);
            txtId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActive.setValue(entity.getActive());
            txtActive.setDisabled(false);
            txtChannelAmount.setValue(entity.getChannelAmount());
            txtChannelAmount.setDisabled(false);
            txtCreatedAt.setValue(entity.getCreatedAt());
            txtCreatedAt.setDisabled(false);
            txtCreatedBy.setValue(entity.getCreatedBy());
            txtCreatedBy.setDisabled(false);
            txtDescription.setValue(entity.getDescription());
            txtDescription.setDisabled(false);
            txtIpAddress.setValue(entity.getIpAddress());
            txtIpAddress.setDisabled(false);
            txtLocation.setValue(entity.getLocation());
            txtLocation.setDisabled(false);
            txtLogin.setValue(entity.getLogin());
            txtLogin.setDisabled(false);
            txtManufacturer.setValue(entity.getManufacturer());
            txtManufacturer.setDisabled(false);
            txtModel.setValue(entity.getModel());
            txtModel.setDisabled(false);
            txtNote.setValue(entity.getNote());
            txtNote.setDisabled(false);
            txtPassword.setValue(entity.getPassword());
            txtPassword.setDisabled(false);
            txtPort.setValue(entity.getPort());
            txtPort.setDisabled(false);
            txtSerialNumber.setValue(entity.getSerialNumber());
            txtSerialNumber.setDisabled(false);
            txtType.setValue(entity.getType());
            txtType.setDisabled(false);
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
        selectedDevice = (DeviceDTO) (evt.getComponent().getAttributes()
                                         .get("selectedDevice"));
        txtActive.setValue(selectedDevice.getActive());
        txtActive.setDisabled(false);
        txtChannelAmount.setValue(selectedDevice.getChannelAmount());
        txtChannelAmount.setDisabled(false);
        txtCreatedAt.setValue(selectedDevice.getCreatedAt());
        txtCreatedAt.setDisabled(false);
        txtCreatedBy.setValue(selectedDevice.getCreatedBy());
        txtCreatedBy.setDisabled(false);
        txtDescription.setValue(selectedDevice.getDescription());
        txtDescription.setDisabled(false);
        txtIpAddress.setValue(selectedDevice.getIpAddress());
        txtIpAddress.setDisabled(false);
        txtLocation.setValue(selectedDevice.getLocation());
        txtLocation.setDisabled(false);
        txtLogin.setValue(selectedDevice.getLogin());
        txtLogin.setDisabled(false);
        txtManufacturer.setValue(selectedDevice.getManufacturer());
        txtManufacturer.setDisabled(false);
        txtModel.setValue(selectedDevice.getModel());
        txtModel.setDisabled(false);
        txtNote.setValue(selectedDevice.getNote());
        txtNote.setDisabled(false);
        txtPassword.setValue(selectedDevice.getPassword());
        txtPassword.setDisabled(false);
        txtPort.setValue(selectedDevice.getPort());
        txtPort.setDisabled(false);
        txtSerialNumber.setValue(selectedDevice.getSerialNumber());
        txtSerialNumber.setDisabled(false);
        txtType.setValue(selectedDevice.getType());
        txtType.setDisabled(false);
        txtUpdatedAt.setValue(selectedDevice.getUpdatedAt());
        txtUpdatedAt.setDisabled(false);
        txtUpdatedBy.setValue(selectedDevice.getUpdatedBy());
        txtUpdatedBy.setDisabled(false);
        txtId.setValue(selectedDevice.getId());
        txtId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedDevice == null) && (entity == null)) {
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
            entity = new Device();

            Integer id = FacesUtils.checkInteger(txtId);

            entity.setActive(FacesUtils.checkString(txtActive));
            entity.setChannelAmount(FacesUtils.checkString(txtChannelAmount));
            entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
            entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
            entity.setDescription(FacesUtils.checkString(txtDescription));
            entity.setId(id);
            entity.setIpAddress(FacesUtils.checkString(txtIpAddress));
            entity.setLocation(FacesUtils.checkString(txtLocation));
            entity.setLogin(FacesUtils.checkString(txtLogin));
            entity.setManufacturer(FacesUtils.checkString(txtManufacturer));
            entity.setModel(FacesUtils.checkString(txtModel));
            entity.setNote(FacesUtils.checkString(txtNote));
            entity.setPassword(FacesUtils.checkString(txtPassword));
            entity.setPort(FacesUtils.checkInteger(txtPort));
            entity.setSerialNumber(FacesUtils.checkString(txtSerialNumber));
            entity.setType(FacesUtils.checkString(txtType));
            entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
            businessDelegatorView.saveDevice(entity);
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
                Integer id = new Integer(selectedDevice.getId());
                entity = businessDelegatorView.getDevice(id);
            }

            entity.setActive(FacesUtils.checkString(txtActive));
            entity.setChannelAmount(FacesUtils.checkString(txtChannelAmount));
            entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
            entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
            entity.setDescription(FacesUtils.checkString(txtDescription));
            entity.setIpAddress(FacesUtils.checkString(txtIpAddress));
            entity.setLocation(FacesUtils.checkString(txtLocation));
            entity.setLogin(FacesUtils.checkString(txtLogin));
            entity.setManufacturer(FacesUtils.checkString(txtManufacturer));
            entity.setModel(FacesUtils.checkString(txtModel));
            entity.setNote(FacesUtils.checkString(txtNote));
            entity.setPassword(FacesUtils.checkString(txtPassword));
            entity.setPort(FacesUtils.checkInteger(txtPort));
            entity.setSerialNumber(FacesUtils.checkString(txtSerialNumber));
            entity.setType(FacesUtils.checkString(txtType));
            entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
            businessDelegatorView.updateDevice(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedDevice = (DeviceDTO) (evt.getComponent().getAttributes()
                                             .get("selectedDevice"));

            Integer id = new Integer(selectedDevice.getId());
            entity = businessDelegatorView.getDevice(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer id = FacesUtils.checkInteger(txtId);
            entity = businessDelegatorView.getDevice(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteDevice(entity);
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
            selectedDevice = (DeviceDTO) (evt.getComponent().getAttributes()
                                             .get("selectedDevice"));

            Integer id = new Integer(selectedDevice.getId());
            entity = businessDelegatorView.getDevice(id);
            businessDelegatorView.deleteDevice(entity);
            data.remove(selectedDevice);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String active, String channelAmount,
        Date createdAt, String createdBy, String description, Integer id,
        String ipAddress, String location, String login, String manufacturer,
        String model, String note, String password, Integer port,
        String serialNumber, String type, Date updatedAt, String updatedBy)
        throws Exception {
        try {
            entity.setActive(FacesUtils.checkString(active));
            entity.setChannelAmount(FacesUtils.checkString(channelAmount));
            entity.setCreatedAt(FacesUtils.checkDate(createdAt));
            entity.setCreatedBy(FacesUtils.checkString(createdBy));
            entity.setDescription(FacesUtils.checkString(description));
            entity.setIpAddress(FacesUtils.checkString(ipAddress));
            entity.setLocation(FacesUtils.checkString(location));
            entity.setLogin(FacesUtils.checkString(login));
            entity.setManufacturer(FacesUtils.checkString(manufacturer));
            entity.setModel(FacesUtils.checkString(model));
            entity.setNote(FacesUtils.checkString(note));
            entity.setPassword(FacesUtils.checkString(password));
            entity.setPort(FacesUtils.checkInteger(port));
            entity.setSerialNumber(FacesUtils.checkString(serialNumber));
            entity.setType(FacesUtils.checkString(type));
            entity.setUpdatedAt(FacesUtils.checkDate(updatedAt));
            entity.setUpdatedBy(FacesUtils.checkString(updatedBy));
            businessDelegatorView.updateDevice(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("DeviceView").requestRender();
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

    public InputText getTxtChannelAmount() {
        return txtChannelAmount;
    }

    public void setTxtChannelAmount(InputText txtChannelAmount) {
        this.txtChannelAmount = txtChannelAmount;
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

    public InputText getTxtIpAddress() {
        return txtIpAddress;
    }

    public void setTxtIpAddress(InputText txtIpAddress) {
        this.txtIpAddress = txtIpAddress;
    }

    public InputText getTxtLocation() {
        return txtLocation;
    }

    public void setTxtLocation(InputText txtLocation) {
        this.txtLocation = txtLocation;
    }

    public InputText getTxtLogin() {
        return txtLogin;
    }

    public void setTxtLogin(InputText txtLogin) {
        this.txtLogin = txtLogin;
    }

    public InputText getTxtManufacturer() {
        return txtManufacturer;
    }

    public void setTxtManufacturer(InputText txtManufacturer) {
        this.txtManufacturer = txtManufacturer;
    }

    public InputText getTxtModel() {
        return txtModel;
    }

    public void setTxtModel(InputText txtModel) {
        this.txtModel = txtModel;
    }

    public InputText getTxtNote() {
        return txtNote;
    }

    public void setTxtNote(InputText txtNote) {
        this.txtNote = txtNote;
    }

    public InputText getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(InputText txtPassword) {
        this.txtPassword = txtPassword;
    }

    public InputText getTxtPort() {
        return txtPort;
    }

    public void setTxtPort(InputText txtPort) {
        this.txtPort = txtPort;
    }

    public InputText getTxtSerialNumber() {
        return txtSerialNumber;
    }

    public void setTxtSerialNumber(InputText txtSerialNumber) {
        this.txtSerialNumber = txtSerialNumber;
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

    public List<DeviceDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataDevice();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<DeviceDTO> deviceDTO) {
        this.data = deviceDTO;
    }

    public DeviceDTO getSelectedDevice() {
        return selectedDevice;
    }

    public void setSelectedDevice(DeviceDTO device) {
        this.selectedDevice = device;
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
