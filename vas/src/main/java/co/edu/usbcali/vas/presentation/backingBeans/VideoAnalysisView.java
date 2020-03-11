package co.edu.usbcali.vas.presentation.backingBeans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.inputnumber.InputNumber;
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
import co.edu.usbcali.vas.utilities.Fechas;

@ManagedBean
@ViewScoped
public class VideoAnalysisView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(VideoAnalysisView.class);

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

	// ANOMALOUS ALG
	private InputText txtInitTime;
	private InputText txtFinalTime;
	private InputNumber initTime;
	private InputNumber finalTime;
	private InputTextarea txtAnomalousInfo;

	private Date dateInitialTime;
	private Date dateFinalTime;

	// DATE SELECTION
	private org.primefaces.component.calendar.Calendar calInitialTime;
	private org.primefaces.component.calendar.Calendar calFinalTime;
	private Date startDate;
	private Date endDate;
	private Date minDate;
	private Date maxDate;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public VideoAnalysisView() {
		super();
		
		Date currentDate = new Date(); // Fecha actual
		
		/* Setear la hora de las fechas a 00:00:00 */
		minDate = this.setDateTimeToZero(currentDate);		
		maxDate = this.setDateTimeToZero(currentDate);
	}
	
	public Date setDateTimeToZero(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		return calendar.getTime();
	}
	
	// Acción disparada por remoteCommand
	public void processMaxDate() {
		log.info("Acción lanzada desde javascript");
		
		/* Obtener el parámetro lanzado desde el javascript: 
		 * fecha actual + duración del video 
		 */
		String date = FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.getRequestParameterMap()
						.get("maxDate");
		
		/* Convertir el string a date*/
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			maxDate = df.parse(date);
			System.out.println(maxDate);
		} catch (ParseException pe) {
			log.info("Falló el parseo de la fecha: " + pe);
		}
	}
	
	public String getTimeFromDate(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateS = df.format(date);
		String[] dateSplit = dateS.split(" ");
		String timeS = dateSplit[1];
		return timeS;
	}

	// LISTENER--------------------------------------------------------------------------	
	public void startDateSelected() {
		endDate = startDate;
		
		String initTimeS = this.getTimeFromDate(startDate);
		String endTimeS= this.getTimeFromDate(endDate);
		
		log.info("initTimeS: "+initTimeS);
		log.info("endTimeS: "+endTimeS);
		
		RequestContext
			.getCurrentInstance()
			.execute("document.getElementById('frm:accordion:init-time_input').value='" + initTimeS + "';");
		
		RequestContext
			.getCurrentInstance()
			.execute("document.getElementById('frm:accordion:end-time_input').value='" + endTimeS + "';");
	}
	
	public void endDateSelected() {
		String endTimeS = this.getTimeFromDate(endDate);
		
		log.info("endTimeS: "+endTimeS);
		RequestContext
			.getCurrentInstance()
			.execute("document.getElementById('frm:accordion:end-time_input').value='" + endTimeS + "';");
	}
	
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
			log.info("video size: " + this.videoSize);
			this.tempVideoFile = videoTemp.getDescription().trim();
			log.info("videoUrlPlayer: " + videoUrlPlayer);
		}
	}

	public void handleFileUploadAnomalous(FileUploadEvent event) {
		log.info("VideoAnalysisView handleFileUploadAnomalous");
		try {
			if (event.getFile() != null) {
				fileUploaded = event.getFile();
				log.info("getContentType: " + fileUploaded.getContentType());
				log.info("getFileName: " + fileUploaded.getFileName());
				log.info("getSize: " + fileUploaded.getSize());

				// Send to temp location
				this.tempVideoFile = businessDelegatorView.saveVideoToTempLocation(fileUploaded.getContentType(),
						fileUploaded.getFileName(), fileUploaded.getSize(), fileUploaded.getInputstream(), "ANOMALOUS");

				this.videoUrlPlayer = businessDelegatorView.getWeb_server_temp() + tempVideoFile;
				this.videoSize = fileUploaded.getSize();

				log.info("videoUrlPlayer: " + videoUrlPlayer);

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
				
				RequestContext
					.getCurrentInstance()
					.execute("getVideoDuration()");

				this.action_refresh_anomalous();
				// this.getVideoTempDataAnomalous();

			}

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public String action_analyze_alg_anomalous() throws Exception {
		log.info("VAS VideoAnalysisView action_analyze_alg_anomalous");

		Boolean status = false;

		try {

			// VALIDATE FORM
			this.validate_form_anomalous();
			// VALIDATE VAS_SERVER_ MONITOR STATUS
			status = businessDelegatorView.serverMonitorControllerStatus();
			if (status.booleanValue() == false) {
				throw new RuntimeException(
						"El servicio de procesamiento no se encuentra disponible en este monento, contacte a su administrador");
				// TODO EMAIL TO ADMIN
				// TODO TRACE
			}

			if (this.videoUrlPlayer != null) {
				log.info("videoUrlPlayer: " + videoUrlPlayer);

				this.dateInitialTime = FacesUtils.checkDate(calInitialTime);
				this.dateFinalTime = FacesUtils.checkDate(calFinalTime);

				// log.info("dateInitialTime: "+Fechas.dateToStr(dateInitialTime, "HH:mm:ss"));
				// log.info("dateFinalTime: "+Fechas.dateToStr(dateFinalTime, "HH:mm:ss"));

				Integer initTime = this.setDateToSeconds(dateInitialTime);
				Integer finalTime = this.setDateToSeconds(dateFinalTime);

				// log.info("initTime: "+initTime);
				// log.info("finalTime: "+finalTime);

				// 1. SEND DATA TO ANL ALG
				String finalVideo = businessDelegatorView.analyze_anomalousAlgFromTempLocationREST(tempVideoFile,
						this.videoSize.toString(), initTime.toString().trim(), finalTime.toString().trim(),
						txtAnomalousInfo.getValue().toString());
				
				// 2. ANALYZE WITH ALG Notify to VAS_MONITOR
				businessDelegatorView.processVideoWithAnomalousAlgRest();

				FacesUtils.addInfoMessage(
						"El Algoritmo DETECCIÓN DE EVENTOS ANOMALOS ha recibido exitosamente la petición de análisis, "
								+ "Se realizará una notificación tan pronto se obtengan los resultados");
				// GET FINAL VIDEO URL
				this.videoUrlPlayer = businessDelegatorView.getAlg_anl_output_server() + finalVideo;
				log.info("videoUrlPlayer: " + videoUrlPlayer);
				// LOAD VIDEO SOURCE TO HTML COMPONENT
				RequestContext.getCurrentInstance()
						.execute("document.getElementById('videoPlayer').src = '" + this.videoUrlPlayer + "';");

				// CLEAR data
				this.clear_data_anomalous();
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	public Integer setDateToSeconds(Date time) throws Exception {
		log.info("VideoAnalysisView setDateToSeconds");

		Integer totalSeconds = null;

		try {

			log.info("time: " + Fechas.dateToStr(time, "HH:mm:ss"));

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(time);

			Integer hours = calendar.get(Calendar.HOUR_OF_DAY);
			Integer minutes = calendar.get(Calendar.MINUTE);
			Integer seconds = calendar.get(Calendar.SECOND);

			Integer totalHora = hours * 3600;
			Integer totalMin = minutes * 60;

			totalSeconds = totalHora + totalMin + seconds;

			log.info("hours:" + totalHora);
			log.info("minutes:" + totalMin);
			log.info("seconds:" + seconds);
			log.info("total:" + totalSeconds);

		} catch (Exception e) {
			log.error(e.getMessage());
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return totalSeconds;
	}

	public String action_refresh_anomalous() throws Exception {
		log.info("VideoAnalysisView action_refresh_anomalous");

		this.videoTempDataAnomalous = null;

		try {

			this.getVideoTempDataAnomalous();

		} catch (Exception e) {
			log.error(e.getMessage());
			FacesUtils.addErrorMessage(e.getMessage());
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

	public String action_refresh_videoTempAnomalous() throws Exception {
		log.info("VideoAnalysisView action_refresh_videoTempAnomalous");

		this.videoTempDataAnomalous = null;

		try {

			this.getVideoTempDataAnomalous();

		} catch (Exception e) {
			log.error(e.getMessage());
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	public void action_delete_videoTemp_anomalous() throws Exception {
		log.info("VideoAnalysisView action_delete_videoTemp_anomalous");

		try {

			if (videoTemp != null) {
				//businessDelegatorView.deleteVideoTemp(videoTemp.getId(), videoTemp.getDescription().trim());

				action_refresh_anomalous();

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

	public void validate_form_anomalous() throws Exception {
		log.info("validate_form_anomalous");
		try {

			if (this.tempVideoFile == null && this.videoUrlPlayer == null) {
				throw new RuntimeException("Importe un video o seleccione uno cargado para realizar análisis");
			}

			if (this.calInitialTime.getValue() == null || this.calInitialTime.getValue().equals("")) {
				this.calInitialTime.setStyle("border: 2px solid red;");
				throw new RuntimeException("Debe ingresar un tiempo inicial");
			} else {
				this.calInitialTime.setStyle("border: 1px solid #C5DBEC;");
			}

			if (this.calFinalTime.getValue() == null || this.calFinalTime.getValue().equals("")) {
				this.calFinalTime.setStyle("border: 2px solid red;");
				throw new RuntimeException("Debe ingresar un tiempo final");
			} else {
				this.calFinalTime.setStyle("border: 1px solid #C5DBEC;");
			}

			log.info("1. validate_form_anomalous");

			Date initDate = (Date) calInitialTime.getValue();
			Date finalDate = (Date) calFinalTime.getValue();

			if (this.calInitialTime.getValue() != null && this.calFinalTime.getValue() != null) {

				if (initDate.compareTo(finalDate) > 0) {
					this.calFinalTime.setStyle("border: 2px solid red;");
					this.calInitialTime.setStyle("border: 2px solid red;");
					throw new RuntimeException("El tiempo inicial debe ser menor al tiempo final");
				} else {
					this.calInitialTime.setStyle("border: 1px solid #C5DBEC;");
					this.calFinalTime.setStyle("border: 1px solid #C5DBEC;");
				}
			}

			/*
			 * if (txtInitTime.getValue().toString().trim().equals("")) {
			 * txtInitTime.setStyle("border-color: red;"); throw new
			 * RuntimeException("Debe ingresar un tiempo inicial"); }else {
			 * txtInitTime.setStyle("border: 1px solid #C5DBEC;"); }
			 * 
			 * if (txtFinalTime.getValue().toString().trim().equals("")) {
			 * txtFinalTime.setStyle("border: 1px solid red;"); throw new
			 * RuntimeException("Debe ingresar un tiempo final"); }else {
			 * txtFinalTime.setStyle("border: 1px solid #C5DBEC;"); } if
			 * (txtInitTime.getValue().equals(txtFinalTime.getValue()) == true) {
			 * 
			 * txtInitTime.setStyle("border: 1px solid red;");
			 * txtFinalTime.setStyle("border: 1px solid red;");
			 * 
			 * throw new RuntimeException("Los tiempos no pueden ser iguales"); }else {
			 * txtInitTime.setStyle("border: 1px solid #C5DBEC;");
			 * txtFinalTime.setStyle("border: 1px solid #C5DBEC;"); } if
			 * (Integer.valueOf(txtInitTime.getValue().toString().trim()) >
			 * Integer.valueOf(txtFinalTime.getValue().toString().trim())) {
			 * 
			 * txtInitTime.setStyle("border: 1px solid red;");
			 * txtFinalTime.setStyle("border: 1px solid red;");
			 * 
			 * throw new
			 * RuntimeException("El tiempo inicial debe ser menor al tiempo final"); }else {
			 * txtInitTime.setStyle("border: 1px solid #C5DBEC;");
			 * txtFinalTime.setStyle("border: 1px solid #C5DBEC;"); }
			 */

		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new Exception(e.getMessage().replace("java.lang.RuntimeException:", ""));

		}
	}

	public String clear_data_anomalous() throws Exception {
		log.info("clear_data");
		try {

			this.tempVideoFile = null;
			this.videoUrlPlayer = null;
			this.fileUploaded = null;
			initTime = null;
			finalTime = null;
			calInitialTime.setValue(null);
			calFinalTime.setValue(null);
			txtAnomalousInfo.setValue(null);
			// initTimeParam = null;
			// finalTimeParam = null;
			// txtInitTime.setValue(null);
			// txtFinalTime.setValue(null);
			// update datatable
			this.action_refresh_anomalous();
			this.action_refresh_videoTemp();

		} catch (Exception e) {
			throw new Exception(e.getMessage().replace("java.lang.RuntimeException:", ""));

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

	public InputText getTxtInitTime() {
		return txtInitTime;
	}

	public void setTxtInitTime(InputText txtInitTime) {
		this.txtInitTime = txtInitTime;
	}

	public InputText getTxtFinalTime() {
		return txtFinalTime;
	}

	public void setTxtFinalTime(InputText txtFinalTime) {
		this.txtFinalTime = txtFinalTime;
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

	public void setInitTime(InputNumber initTime) {
		this.initTime = initTime;
	}

	public void setFinalTime(InputNumber finalTime) {
		this.finalTime = finalTime;
	}

	public InputNumber getInitTime() {
		return initTime;
	}

	public InputNumber getFinalTime() {
		return finalTime;
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

	public Date getDateInitialTime() {
		return dateInitialTime;
	}

	public void setDateInitialTime(Date dateInitialTime) {
		this.dateInitialTime = dateInitialTime;
	}

	public Date getDateFinalTime() {
		return dateFinalTime;
	}

	public void setDateFinalTime(Date dateFinalTime) {
		this.dateFinalTime = dateFinalTime;
	}

	public org.primefaces.component.calendar.Calendar getCalInitialTime() {
		return calInitialTime;
	}

	public void setCalInitialTime(org.primefaces.component.calendar.Calendar calInitialTime) {
		this.calInitialTime = calInitialTime;
	}

	public org.primefaces.component.calendar.Calendar getCalFinalTime() {
		return calFinalTime;
	}

	public void setCalFinalTime(org.primefaces.component.calendar.Calendar calFinalTime) {
		this.calFinalTime = calFinalTime;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Date getMinDate() {
		return minDate;
	}
	
	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}
	
	public Date getMaxDate() {
		return maxDate;
	}
	
	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

	public List<VideoDTO> getDataTracker() {
		try {
			if (dataTracker == null) {
				dataTracker = null;
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
				dataAnomalous = businessDelegatorView.getDataVideoAnomalous();
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

	public InputTextarea getTxtAnomalousInfo() {
		return txtAnomalousInfo;
	}

	public void setTxtAnomalousInfo(InputTextarea txtAnomalousInfo) {
		this.txtAnomalousInfo = txtAnomalousInfo;
	}

}
