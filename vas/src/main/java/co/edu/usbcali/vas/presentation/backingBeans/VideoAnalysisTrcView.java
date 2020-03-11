package co.edu.usbcali.vas.presentation.backingBeans;

import java.io.Serializable;
import java.util.List;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.vas.model.Video;
import co.edu.usbcali.vas.model.VideoTemp;
import co.edu.usbcali.vas.model.dto.VideoDTO;
import co.edu.usbcali.vas.model.dto.VideoTempDTO;
import co.edu.usbcali.vas.presentation.businessDelegate.IBusinessDelegatorView;
import co.edu.usbcali.vas.utilities.FacesUtils;

@ManagedBean
@ViewScoped
public class VideoAnalysisTrcView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(VideoAnalysisTrcView.class);

	private UploadedFile fileUploaded;
	private UploadedFile fileTemp;
	private VideoDTO selectedVideoDTO;
	private VideoTempDTO selectedVideoTempDTO;
	private Video entity;
	private VideoTemp videoTemp;
	private String videoUrlPlayer;
	private List<VideoDTO> dataTracker;
	private List<VideoDTO> dataAnomalous;
	private List<VideoTempDTO> videoTempData;
	private List<VideoTempDTO> videoTempDataTracker;
	private List<VideoTempDTO> videoTempDataAnomalous;
	private String tempVideoFile;
	private Long videoSize;

	// TRACKER ALG
	private InputText posX;
	private InputText posY;
	private InputText posX2;
	private InputText posY2;
	private InputTextarea txtTrackerInfo;
	
	//TRACKER DATA
	private String posXParam;
	private String posYParam;
	private String posX2Param;
	private String posY2Param;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public VideoAnalysisTrcView() {
		super();
	}

	// LISTENER--------------------------------------------------------------------------
	public void listener_selected_entity() {
		log.info("listener_selected_entity");
		try {
			this.entity = businessDelegatorView.getVideo(selectedVideoDTO.getId());
		} catch (Exception e) {
			entity = null;
		}
		if (entity != null) {

			this.videoUrlPlayer = entity.getUrl();
			log.info("videoUrlPlayer: " + videoUrlPlayer);
		}
	}

	public void listener_selected_videoTemp() {
		log.info("listener_selected_videoTemp_entity");
		try {
			this.videoTemp = businessDelegatorView.getVideoTemp(selectedVideoTempDTO.getId());
		} catch (Exception e) {
			videoTemp = null;
		}
		if (videoTemp != null) {

			this.videoUrlPlayer = videoTemp.getUrl();
			this.videoSize = Long.valueOf(videoTemp.getLenght());
			this.tempVideoFile = videoTemp.getDescription().trim();
			log.info("videoUrlPlayer: " + videoUrlPlayer);
			
			RequestContext
				.getCurrentInstance()
				.execute("onFileSelected('" + this.videoUrlPlayer + "');");
		}
	}

	public void handleFileUploadTracker(FileUploadEvent event) {
		log.info("VideoAnalysisView handleFileUploadTracker");
		try {
			if (event.getFile() != null) {
				fileUploaded = event.getFile();
				log.info("getContentType tracker: " + fileUploaded.getContentType());
				log.info("getFileName tracker: " + fileUploaded.getFileName());
				log.info("getSize tracker: " + fileUploaded.getSize());

				// Send to temp location
				this.tempVideoFile = businessDelegatorView.saveVideoToTempLocation(fileUploaded.getContentType(),
						fileUploaded.getFileName(), fileUploaded.getSize(), fileUploaded.getInputstream(), "TRACKER");

				this.videoUrlPlayer = businessDelegatorView.getWeb_server_temp() + tempVideoFile;
				this.videoSize = fileUploaded.getSize();

				log.info("videoUrlPlayer tracker: " + videoUrlPlayer);

				// RequestContext.getCurrentInstance().execute("window.scrollTo(0,0);");
				RequestContext.getCurrentInstance()
						.execute("document.getElementById('videoPlayer').src = '" + this.videoUrlPlayer + "';");
				// RequestContext.getCurrentInstance().execute("videoLenght();");

				// FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frm");
				// RequestContext.getCurrentInstance().update("frm");

				FacesUtils.addInfoMessage("El video ha sido cargado exitosamente");
				// finalTimeNumber =
				// Integer.valueOf(FacesUtils.getfromSession("anlFinalTime").toString());
				// log.info("finalTimeNumber: "+finalTimeNumber);
				
				String contentType = fileUploaded.getContentType();
				byte[] contents = fileUploaded.getContents();
				
				RequestContext
					.getCurrentInstance()
					.execute("onFileSelected('" + this.videoUrlPlayer + "')");
				
				this.action_refresh_tracker();
				//this.getVideoTempDataTracker();

			}

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public String action_analyze_alg_tracker() throws Exception {
		log.info("VideoAnalysisView action_analyze_alg_tracker");

		Boolean status = false;

		try {

			this.validate_form_tracker();
			// VALIDATE VAS_MONITOR STATU
			status = businessDelegatorView.serverMonitorControllerStatus();

			if (status.booleanValue() == false) {
				throw new RuntimeException(
						"El servicio de procesamiento no se encuentra disponible en este monento, contacte a su administrador");
			}
			
			if (this.videoUrlPlayer != null) {
				log.info("videoUrlPlayer: " + videoUrlPlayer);

				posXParam = String.valueOf(posX.getValue().toString().trim());
				posYParam = String.valueOf(posY.getValue().toString().trim());
				posX2Param = String.valueOf(posX2.getValue().toString().trim());
				posY2Param = String.valueOf(posY2.getValue().toString().trim());

				// 1. SEND DATA TO ANL TRACKER
				String finalVideo = businessDelegatorView.analyze_trackerAlgFromTempLocationREST(tempVideoFile,
						this.videoSize.toString(), posXParam, posYParam, posX2Param, posY2Param,
						txtTrackerInfo.getValue().toString());

				// 2. ANALYZE WITH ALG Notify to VAS_MONITOR
				businessDelegatorView.processVideoWithTrackerAlgRest();

				FacesUtils.addInfoMessage(
						"El servicio SEGUIDOR DE OBJETOS ha recibido exitosamente la petición de análisis, "
								+ "Se realizará una notificación tan pronto se obtengan los resultados");

				this.videoUrlPlayer = businessDelegatorView.getAlg_trc_output_server() + finalVideo;
				log.info("videoUrlPlayer: " + videoUrlPlayer);
				RequestContext.getCurrentInstance()
						.execute("document.getElementById('videoPlayer').src = '" + this.videoUrlPlayer + "';");

				// FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("frm");
				// RequestContext.getCurrentInstance().update("frm");
				// RequestContext.getCurrentInstance().execute("location.reload();");

				// CLEAR data
				this.clear_data_tracker();
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	public String action_refresh_tracker() throws Exception {
		log.info("VideoAnalysisView action_refresh");

		this.videoTempDataTracker = null;

		try {

			this.getVideoTempDataTracker();

		} catch (Exception e) {
			log.error(e.getMessage());
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	public String action_refresh_videoTempTracker() throws Exception {
		log.info("VideoAnalysisView action_refresh_videoTempTracker");

		this.videoTempDataTracker = null;

		try {

			this.getVideoTempDataTracker();

		} catch (Exception e) {
			log.error(e.getMessage());
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	public void action_delete_videoTemp_tracker() throws Exception {
		log.info("VideoAnalysisView action_delete_videoTemp_tracker");

		try {

			if (videoTemp != null) {
				
				action_refresh_tracker();

				FacesUtils.hideDialog("dlgDeleteVideo");
				FacesUtils.addInfoMessage("Video Eliminado Exitosamente");
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			FacesUtils.addErrorMessage(e.getMessage());
			FacesUtils.hideDialog("dlgDeleteVideo");
		}
	}

	public String action_validate_delete() throws Exception {
		try {

			if (selectedVideoTempDTO == null) {
				throw new RuntimeException("Seleccione un video para eliminar");
			} else {
				FacesUtils.showDialog("dlgDeleteVideo");
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	public void validate_form_tracker() throws Exception {
		log.info("validate_form_tracker");
		try {

			if (this.tempVideoFile == null && this.videoUrlPlayer == null) {
				throw new RuntimeException("Importe un video o seleccione uno cargado para realizar análisis");
			}
			
			//VALIDATE BB
			if (posX.getValue().toString().trim().equals("") == true || posY.getValue().toString().trim().equals("") == true
					|| posX2.getValue().toString().trim().equals("") == true || posY2.getValue().toString().trim().equals("") == true) {
				
				posX.setStyle("border: 2px solid red;");
				posX2.setStyle("border: 2px solid red;");
				posY.setStyle("border: 2px solid red;");
				posY2.setStyle("border: 2px solid red;");
				throw new RuntimeException("Debe Extraer las coordenadas del BoundingBox sobre el video");
			} else {
				posX.setStyle("border: 1px solid #C5DBEC;");
				posY.setStyle("border: 1px solid #C5DBEC;");
				posX2.setStyle("border: 1px solid #C5DBEC;");
				posY2.setStyle("border: 1px solid #C5DBEC;");
			}
			//----------------------------------------
			
			if (posX.getValue() == null) {
				posX.setStyle("border: 2px solid red;");
				throw new RuntimeException("Debe ingresar una posición X");
			} else {
				posX.setStyle("border: 1px solid #C5DBEC;");
			}
			if (posY.getValue() == null) {
				posY.setStyle("border: 2px solid red;");
				throw new RuntimeException("Debe ingresar una posición Y");
			} else {
				posY.setStyle("border: 1px solid #C5DBEC;");
			}
			if (posX2.getValue() == null) {
				posX2.setStyle("border: 2px solid red;");
				throw new RuntimeException("Debe ingresar una posición X2");
			} else {
				posX2.setStyle("border: 1px solid #C5DBEC;");
			}
			if (posY2.getValue() == null) {
				posY2.setStyle("border: 2px solid red;");
				throw new RuntimeException("Debe ingresar una posición Y2");
			} else {
				posY2.setStyle("border: 1px solid #C5DBEC;");
			}

		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new Exception(e.getMessage().replace("java.lang.RuntimeException:", ""));

		}
	}

	public String clear_data_tracker() throws Exception {
		log.info("clear_data");
		try {

			this.tempVideoFile = null;
			this.videoUrlPlayer = null;
			this.fileUploaded = null;
			posX.setValue(null);
			posY.setValue(null);
			posX2.setValue(null);
			posY2.setValue(null);
			txtTrackerInfo.setValue(null);
			// update datatable
			this.action_refresh_tracker();
			this.action_refresh_videoTemp();

		} catch (Exception e) {
			throw new Exception(e.getMessage().replace("java.lang.RuntimeException:", ""));

		}
		return "";
	}

	public String action_refresh_videoTemp() throws Exception {
		log.info("VideoAnalysisView action_refresh_videoTemp");

		this.videoTempData = null;

		try {

			this.getVideoTempData();

		} catch (Exception e) {
			log.error(e.getMessage());
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	// GETTERS AND SETTERS---------------------------

	public TimeZone getTimeZone() {
		return java.util.TimeZone.getDefault();
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public UploadedFile getFileUploaded() {
		return fileUploaded;
	}

	public void setFileUploaded(UploadedFile fileUploaded) {
		this.fileUploaded = fileUploaded;
	}

	public VideoDTO getSelectedVideoDTO() {
		return selectedVideoDTO;
	}

	public void setSelectedVideoDTO(VideoDTO selectedVideoDTO) {
		this.selectedVideoDTO = selectedVideoDTO;
	}

	public String getVideoUrlPlayer() {
		return videoUrlPlayer;
	}

	public void setVideoUrlPlayer(String videoUrlPlayer) {
		this.videoUrlPlayer = videoUrlPlayer;
	}

	public UploadedFile getFileTemp() {
		return fileTemp;
	}

	public void setFileTemp(UploadedFile fileTemp) {
		this.fileTemp = fileTemp;
	}

	public String getTempVideoFile() {
		return tempVideoFile;
	}

	public void setTempVideoFile(String tempVideoFile) {
		this.tempVideoFile = tempVideoFile;
	}

	public InputText getPosX() {
		return posX;
	}

	public void setPosX(InputText posX) {
		this.posX = posX;
	}

	public InputText getPosY() {
		return posY;
	}

	public void setPosY(InputText posY) {
		this.posY = posY;
	}

	public InputText getPosX2() {
		return posX2;
	}

	public void setPosX2(InputText posX2) {
		this.posX2 = posX2;
	}

	public InputText getPosY2() {
		return posY2;
	}

	public void setPosY2(InputText posY2) {
		this.posY2 = posY2;
	}

	public Long getVideoSize() {
		return videoSize;
	}

	public void setVideoSize(Long videoSize) {
		this.videoSize = videoSize;
	}

	public List<VideoTempDTO> getVideoTempData() {
		try {
			if (videoTempData == null) {
				videoTempData = businessDelegatorView.getDataVideoTemp();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return videoTempData;
	}

	public void setVideoTempData(List<VideoTempDTO> videoTempData) {
		this.videoTempData = videoTempData;
	}

	public VideoTempDTO getSelectedVideoTempDTO() {
		return selectedVideoTempDTO;
	}

	public void setSelectedVideoTempDTO(VideoTempDTO selectedVideoTempDTO) {
		this.selectedVideoTempDTO = selectedVideoTempDTO;
	}

	public List<VideoDTO> getDataTracker() {
		try {
			if (dataTracker == null) {
				dataTracker = businessDelegatorView.getDataVideoTracker();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataTracker;
	}

	public void setDataTracker(List<VideoDTO> dataTracker) {
		this.dataTracker = dataTracker;
	}

	public List<VideoDTO> getDataAnomalous() {
		try {
			if (dataAnomalous == null) {
				dataAnomalous = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataAnomalous;
	}

	public void setDataAnomalous(List<VideoDTO> dataAnomalous) {
		this.dataAnomalous = dataAnomalous;
	}

	public List<VideoTempDTO> getVideoTempDataTracker() {
		try {
			if (videoTempDataTracker == null) {
				videoTempDataTracker = businessDelegatorView.getDataVideoTempTracker();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return videoTempDataTracker;
	}

	public void setVideoTempDataTracker(List<VideoTempDTO> videoTempDataTracker) {
		this.videoTempDataTracker = videoTempDataTracker;
	}

	public List<VideoTempDTO> getVideoTempDataAnomalous() {
		try {
			if (videoTempDataAnomalous == null) {
				videoTempDataAnomalous = businessDelegatorView.getDataVideoTempAnomalous();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return videoTempDataAnomalous;
	}

	public void setVideoTempDataAnomalous(List<VideoTempDTO> videoTempDataAnomalous) {
		this.videoTempDataAnomalous = videoTempDataAnomalous;
	}

	public InputTextarea getTxtTrackerInfo() {
		return txtTrackerInfo;
	}

	public void setTxtTrackerInfo(InputTextarea txtTrackerInfo) {
		this.txtTrackerInfo = txtTrackerInfo;
	}

	public String getPosXParam() {
		return posXParam;
	}

	public String getPosYParam() {
		return posYParam;
	}

	public String getPosX2Param() {
		return posX2Param;
	}

	public String getPosY2Param() {
		return posY2Param;
	}

	public void setPosXParam(String posXParam) {
		this.posXParam = posXParam;
	}

	public void setPosYParam(String posYParam) {
		this.posYParam = posYParam;
	}

	public void setPosX2Param(String posX2Param) {
		this.posX2Param = posX2Param;
	}

	public void setPosY2Param(String posY2Param) {
		this.posY2Param = posY2Param;
	}

}
