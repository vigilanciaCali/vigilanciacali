package co.edu.usbcali.vas.monitor.control;

import java.io.File;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import co.edu.usbcali.vas.model.SystemParameter;
import co.edu.usbcali.vas.model.Video;
import co.edu.usbcali.vas.model.VideoStatus;
import co.edu.usbcali.vas.model.VideoType;
import co.edu.usbcali.vas.model.control.ISystemParameterLogic;
import co.edu.usbcali.vas.model.control.IVideoLogic;
import co.edu.usbcali.vas.model.control.IVideoStatusLogic;
import co.edu.usbcali.vas.model.control.IVideoTypeLogic;
import co.edu.usbcali.vas.model.dto.AlgorithmDTO;
import co.edu.usbcali.vas.model.dto.VideoTransactionDTO;
import co.edu.usbcali.vas.monitor.email.control.rest.IEmailRestLogic;
import co.edu.usbcali.vas.monitorintegration.control.rest.IMonitorIntegrationRestLogic;
import co.edu.usbcali.vas.queue.IQueueLogic;
import co.edu.usbcali.vas.trace.ISystemLogRestLogic;
import co.edu.usbcali.vas.utilities.Constantes;

@Scope("singleton")
@Service("MonitorTrackerLogic")
public class MonitorTrackerLogic implements IMonitorTrackerLogic {
	private static final Logger log = LoggerFactory.getLogger(MonitorTrackerLogic.class);

	@Autowired
	private ISystemParameterLogic systemParameterLogic;
	@Autowired
	private IQueueLogic queueLogic;
	@Autowired
	private IMonitorIntegrationRestLogic integrationRestLogic;
	@Autowired
	private IMonitorRestLogic monitorRestLogic;
	@Autowired
	private IEmailRestLogic emailRestLogic;
	@Autowired
	private ISystemLogRestLogic systemLogRestLogic;
	@Autowired
	private IVideoLogic videoLogic;
	@Autowired
	private IVideoTypeLogic videoTypeLogic;
	@Autowired
	private IVideoStatusLogic videoStatusLogic;

	private Boolean ALG_TRC_REQUEST = false;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void processVideoWithTrackerEventsAlg() throws Exception {
		log.info("6. VAS_SERVER_MONITOR MonitorLogic processVideoWithTrackerEventsAlg-------------------------------------------");

		Boolean located = false;
		Boolean sent = false;

		AlgorithmDTO algorithmDTO = null;
		try {

			// CALL ALG ANL SERVICE
			log.info("6.1 VAS_SERVER_MONITOR isTrcAlgAvailable");
			Boolean serviceAvailable = integrationRestLogic.isTrcAlgAvailable();

			if (serviceAvailable.booleanValue() == true) {
				//TRACE
				systemLogRestLogic.save_systemLogRest("6.1 SERVER_MONITOR", "TRACKER ServiceAvailable");

				algorithmDTO = queueLogic.receiveFirstMessageTrcQueue();
				
				log.info("6.2 VAS_SERVER_MONITOR DEQUEUE NEW MESSAGE: "+algorithmDTO.getVideoTransactionId());

				if (algorithmDTO != null) {

					log.info("getUserId: " + algorithmDTO.getUserId());
					log.info("getUserEmail: " + algorithmDTO.getUserEmail());
					log.info("getVideoFile: " + algorithmDTO.getVideoFile());
					log.info("getVideoFileTemp: " + algorithmDTO.getVideoFileTemp());
					log.info("getPosXParam: " + algorithmDTO.getPosXParam());
					log.info("getPosYParam: " + algorithmDTO.getPosYParam());
					log.info("getPosX2Param: " + algorithmDTO.getPosX2Param());
					log.info("getPosY2Param: " + algorithmDTO.getPosY2Param());
					log.info("getTempVideoFolder: " + algorithmDTO.getTempVideoFolder());
					log.info("getOutputVideoFolder: " + algorithmDTO.getOutputVideoFolder());
					log.info("getVideoTransactionId: " + algorithmDTO.getVideoTransactionId());
					log.info("getUrl: " + algorithmDTO.getUrl());

					//TRACE
					systemLogRestLogic.save_systemLogRest("6.2 SERVER_MONITOR", "receiveFirstMessageTrcQueue: "+algorithmDTO.getVideoTransactionId());
					
					// validate videTemp location
					located = this.searchVideoInFolder(algorithmDTO.getVideoFileTemp(),
							algorithmDTO.getTempVideoFolder());

					if (located.booleanValue() == true) {
						log.info(algorithmDTO.getVideoFileTemp() + " videoFileTemp LOCATED");
						//TRACE
						systemLogRestLogic.save_systemLogRest("6.3 SERVER_MONITOR", "searchVideoInFolder, LOCATED: "+algorithmDTO.getVideoFileTemp());
						
						//SAVE VIDEO & STATUS NEW
						//TODO SAVE VIDEO & STATUS NEW
						this.saveVideo(algorithmDTO);
						//TRACE
						systemLogRestLogic.save_systemLogRest("SERVER_MONITOR", "6.4 saveVideo");
						// SEND TO ANL ALG
						sent = integrationRestLogic.sendDataToTrcService(algorithmDTO);
						
						//TRACE
						systemLogRestLogic.save_systemLogRest("SERVER_MONITOR", "6.6 sendDataToTrcService: "+sent.toString());
						// if send to processing fail
						if (sent == false) {

							integrationRestLogic.sendDataToTrcService(algorithmDTO);

						}else {
							systemLogRestLogic.save_systemLogRest("SERVER_MONITOR", "6.7 sendDataToTrcService FINISHED ");
						}

					} else {
						//TRACE ERROR
						systemLogRestLogic.save_systemLogRest("SERVER_MONITOR ERROR", "searchVideoInFolder located.booleanValue() == false, file: "+algorithmDTO.getVideoFileTemp());
						//NOTIFY
						// SEND EMAIL
						this.emailRestLogic.sendMailProcessingError(algorithmDTO);
					}

				}

				// SERVICE BUSSY
			} else {
				//TRACE
				systemLogRestLogic.save_systemLogRest("SERVER_MONITOR ERROR", "trcService NOT Available");
				// ACTION WAIT UNTIL AVAILABLE (ASK C/30 SEG)
				Boolean isAvailable = integrationRestLogic.waitForTrcAlgAvailable();
				// IF ALG RESPONSE OK
				if (isAvailable.booleanValue() == true) {
					// SEND TO ANL ALG
					integrationRestLogic.sendDataToTrcService(algorithmDTO);
				}
			}

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR MonitorLogic processVideoWithTrackerEventsAlg", e);
			systemLogRestLogic.save_systemLogRest("SERVER_MONITOR ERROR", "processVideoWithTrackerEventsAlg - "+e);
		}
	}
	
	//FUNCTION FOR TEST MODE
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void processVideoWithtTrackerEventsAlgTestMode() throws Exception {
		log.info("2.0 VAS_SERVER_MONITOR MonitorLogic processVideoWithtTrackerEventsAlgTestMode-------------------------------------------");

		AlgorithmDTO algorithmDTO = null;
		try {

				algorithmDTO = queueLogic.receiveFirstMessageTrcQueue();
				log.info("VAS_SERVER_MONITOR DEQUEUE NEW MESSAGE");
				
				if (algorithmDTO != null) {

					log.info("getUserId: " + algorithmDTO.getUserId());
					log.info("getUserEmail: " + algorithmDTO.getUserEmail());
					log.info("getVideoFile: " + algorithmDTO.getVideoFile());
					log.info("getVideoFileTemp: " + algorithmDTO.getVideoFileTemp());
					log.info("getPosXParam: " + algorithmDTO.getPosXParam());
					log.info("getPosYParam: " + algorithmDTO.getPosYParam());
					log.info("getPosX2Param: " + algorithmDTO.getPosX2Param());
					log.info("getPosY2Param: " + algorithmDTO.getPosY2Param());
					log.info("getTempVideoFolder: " + algorithmDTO.getTempVideoFolder());
					log.info("getOutputVideoFolder: " + algorithmDTO.getOutputVideoFolder());
					log.info("getVideoTransactionId: " + algorithmDTO.getVideoTransactionId());

					//TRACE
					systemLogRestLogic.save_systemLogRest("SERVER_MONITOR - External System", "5. receiveFirstMessageTrcQueue TEST MODE: "+algorithmDTO.getVideoTransactionId());
					//SAVE VIDEO & STATUS NEW
					this.saveVideo(algorithmDTO);
					
					//TRACE
					systemLogRestLogic.save_systemLogRest("SERVER_MONITOR - External System", "6. saveVideo TEST MODE");
					// SEND TO ANL ALG
					integrationRestLogic.sendDataToTrcService(algorithmDTO);
				}

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR MonitorLogic processVideoWithAnomalousEventsAlgTestMode", e);
			//TRACE
			systemLogRestLogic.save_systemLogRest("SERVER_MONITOR ERROR", "processVideoWithAnomalousEventsAlgTestMode, exception: "+e);
			
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void startTrcAlgRequest() throws Exception {
		log.info("1.0 VAS_SERVER_MONITOR MonitorLogic startTrcAlgRequest");

		try {

			SystemParameter parameter = systemParameterLogic.getSystemParamByCode(Constantes.ALG_TRC_REQUEST);
			parameter.setBooleanValue(true);
			systemParameterLogic.updateSystemParameter(parameter);
			
			//TRACE
			systemLogRestLogic.save_systemLogRest("SERVER_MONITOR", "4 startTrcAlgRequest TRACKER ALG");

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR MonitorLogic startTrcAlgRequest", e);

		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void stopTrcAlgRequest() throws Exception {
		log.info("1.0 VAS_SERVER_MONITOR MonitorLogic stopTrcAlgRequest");

		try {

			SystemParameter parameter = systemParameterLogic.getSystemParamByCode(Constantes.ALG_TRC_REQUEST);
			parameter.setBooleanValue(false);
			systemParameterLogic.updateSystemParameter(parameter);
			
		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR MonitorLogic stopTrcAlgRequest", e);

		}
	}

	//FUNCTION CALLED BY CRON JOB 
	@Override
	public void validateTrcAlgRequest() throws Exception {
		log.info("VAS_SERVER_MONITOR MonitorLogic validateTrcAlgRequest");

		try {

			Boolean request = this.getALG_TRC_REQUEST().booleanValue();

			if(isTestModeActivated().booleanValue() == true) {
				//TEST MODE
				log.info("-----------------------TEST MODE ON-------------------------------");
				systemLogRestLogic.save_systemLogRest("SERVER_MONITOR ", "TEST MODE ON");
				this.processVideoWithtTrackerEventsAlgTestMode();
				this.stopTrcAlgRequest();
				
				
			}
			if (request.booleanValue() == true && isTestModeActivated().booleanValue() == false) {
				log.info("5. -----------------------TEST MODE OFF-------------------------------");
				systemLogRestLogic.save_systemLogRest("SERVER_MONITOR", "5. TEST MODE OFF");
				this.processVideoWithTrackerEventsAlg();
				this.stopTrcAlgRequest();
				//TRACE
				systemLogRestLogic.save_systemLogRest("SERVER_MONITOR", "7 stopTrcAlgRequest ");
				
			}

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR MonitorLogic validateTrcAlgRequest", e);
			this.stopTrcAlgRequest();
		}
	}

	// ALG RESULT--------------------------------------------------------
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void trackerDetetectionResult(AlgorithmDTO data) throws Exception {
		log.info("8 VAS_SERVER_MONITOR MonitorLogic trackerDetetectionResult FINISHED-------------------------------------------");

		Boolean located = false;
		Video video = null;

		try {

			if (data != null) {
				
				//TRACE
				systemLogRestLogic.save_systemLogRest("SERVER_MONITOR", "8. trackerDetetectionResult: "
						+data.getErrorCode()+" - "+data.getMessage()+" - "+data.getVideoTransactionId());
				
				// GET VIDEO 
				//TODO GET VIDEO DATA local db
				video = videoLogic.getVideoByTransactionId(data.getVideoTransactionId());
				log.info("8.1 VAS_SERVER_MONITOR MonitorLogic getVideoByTransactionId: ");
				
				if(video != null) {
					
					log.info("8.2 VAS_SERVER_MONITOR MonitorLogic trackerDetetectionResult video != null");

					log.info("video getUrl: " + video.getUrl());
					log.info("video getDescription: " + video.getDescription());
					log.info("video getSrc: " + video.getSrc());
					log.info("video getTransactionId: " + video.getTransactionId());
					log.info("video getType: " + video.getVideoType().getDescription());
					
					//TRACE
					systemLogRestLogic.save_systemLogRest("SERVER_MONITOR - External System", "9. trackerDetetectionResult getVideoData localDB: "+video.getDescription());
				
					// GET VIDEO from VAS REST
					//videoTransactionDTO = monitorRestLogic.getVideoTransactionByIdRest(data.getVideoTransactionId().trim());
					
					if (video.getDescription().equals("") != true) {
						located = this.searchVideoInFolder(video.getDescription().trim(), this.getOutputAlgTrcVideoFolder());
						log.info("8.3 VAS_SERVER_MONITOR MonitorLogic searchVideoInFolder: ");
						
					}else {
						//TRACE
						systemLogRestLogic.save_systemLogRest("SERVER_MONITOR", "8.3 trackerDetetectionResult getVideoTransactionById ERROR: "
								+data.getVideoTransactionId());
						log.info("8.3 VAS_SERVER_MONITOR MonitorLogic searchVideoInFolder ERROR VIDEO NOT FOUND ");
						
					}
				}else {
					//TRACE
					systemLogRestLogic.save_systemLogRest("SERVER_MONITOR", "trackerDetetectionResult getVideoTransactionById VIDEO NOT FOUND: "+data.getVideoTransactionId());
				
				}

				if (located.booleanValue() == true) {
					
					video.setAnalyticData(data.getErrorCode().trim() + "-" + data.getMessage());
					
					//SET VIDEO TRANSACTION DTO
					VideoTransactionDTO videoTransactionDTO = new VideoTransactionDTO();
					videoTransactionDTO.setVideoTransactionId(video.getTransactionId());
					videoTransactionDTO.setDescription(video.getDescription());
					videoTransactionDTO.setUrl(video.getUrl());
					videoTransactionDTO.setId_Users(video.getUserId());
					videoTransactionDTO.setId_VideoType(video.getVideoType().getId());
					videoTransactionDTO.setId_VideoStatus(video.getVideoStatus().getId());

					// SEND TO VAS OUT QUEUE
					this.queueLogic.sendMessageToVasOutQueue(videoTransactionDTO);
					//TRACE
					systemLogRestLogic.save_systemLogRest("SERVER_MONITOR", "10. sendMessageToVasOutQueue: "+videoTransactionDTO.getDescription());

					// TODO NOTIFY VAS TO PROCESS
					this.monitorRestLogic.processAlgResult();
					//TRACE
					systemLogRestLogic.save_systemLogRest("SERVER_MONITOR", "11. processTrcResult To VAS Cloud");
					

				} else {
					//TRACE
					systemLogRestLogic.save_systemLogRest("SERVER_MONITOR ERROR", "searchVideoInFolder NOT LOCATED");
					
					// TODO NOTIFY ADMIN

				}
			}

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR MonitorLogic trackerDetetectionResult", e);
			//TRACE
			systemLogRestLogic.save_systemLogRest("SERVER_MONITOR", "trackerDetetectionResult error: "+e);
			

		}
	}

	@Transactional(readOnly = false)
	public AlgorithmDTO getVideoTransactionByIdRest(String videoTransactionId) throws Exception {
		log.info("VAS_SERVER_MONITOR MonitorLogic getVideoTransactionById ");

		AlgorithmDTO algorithmDTO = null;
		try {

			if (videoTransactionId != null) {

				String url = this.getVasServerController() + "videoTransaction/" + videoTransactionId;
				log.info("url: " + url);
				RestTemplate restTemplate = new RestTemplate();
				algorithmDTO = restTemplate.postForObject(url, videoTransactionId, AlgorithmDTO.class);

			}
		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR getVideoTransactionById failed", e);

		}
		return algorithmDTO;
	}

	@Transactional(readOnly = false)
	public Boolean checkVasControllerStatus() throws Exception {
		log.info("VAS_SERVER_MONITOR MonitorLogic vasControllerStatus ");

		Boolean status = false;
		try {
			String url = this.getVasServerController() + "status/available/";
			log.info("url: " + url);
			RestTemplate restTemplate = new RestTemplate();
			status = restTemplate.postForObject(url, status, Boolean.class);

			log.info("VAS_SERVER_MONITOR controller STATUS: " + status);

		} catch (Exception e) {
			log.error("controllerStatus failed", e);
		}

		return status;

	}

	public Boolean searchVideoInFolder(String videoId, String folder) {
		log.info("VAS_SERVER_MONITOR MonitorLogic searchVideoInFolder: " + videoId);

		Boolean located = false;
		try {

			String dirPath = folder;
			log.info("folder: " + folder);
			log.info("videoId: " + videoId);

			validate_system_folder(folder);

			File dir = new File(dirPath);
			String[] files = dir.list();

			if (files.length == 0) {
				log.error("The directory is empty");
				throw new RuntimeException("El directorio de entrada está vacio");
			} else {

				for (String localFile : files) {

					log.info("localFile: " + localFile);

					if (localFile.trim().equals(videoId)) {
						log.info("output_video_folder Video located");
						located = true;
						return located;
					} else {
						located = false;
					}
				}
			}

		} catch (Exception e) {
			log.error("VAS_MONITOR searchVideoInFolder error", e);
		}
		return located;

	}

	public void deleteVideoFileFromTempFolder(String videoLocation) {
		log.info("VAS_MONITOR deleteVideoFileFromTempFolder");

		if (videoLocation != null && videoLocation.equals("") == false) {

			File file = new File(videoLocation);
			if (file.exists() && file.isFile() && file.canRead()) {
				file.delete();
				log.info("DELETED");
			}
		}
	}
	
	public void saveVideo(AlgorithmDTO algorithmDTO) {
		log.info("VAS_MONITOR deleteVideoFileFromTempFolder");

		try {
			if (algorithmDTO != null ) {
				
				Video video = new Video();
				video.setDescription(algorithmDTO.getVideoFile());
				video.setSrc(algorithmDTO.getOutputVideoFolder());
				video.setUrl(algorithmDTO.getUrl());
				video.setTransactionId(algorithmDTO.getVideoTransactionId());
				video.setCreatedAt(new Date());
				video.setCreatedBy("system");
				video.setUserId(Integer.valueOf(algorithmDTO.getUserId().trim()));
				
				VideoStatus videoStatus = videoStatusLogic.getVideoStatus(1);
				video.setVideoStatus(videoStatus);
				
				VideoType videoType = videoTypeLogic.getVideoType(1);
				video.setVideoType(videoType);
				
				videoLogic.saveVideo(video);
			}
		} catch (Exception e) {
			log.error("SERVER_MONITOR MonitorLogic saveVideo failed ",e);
		}
	}

	public void validate_system_folder(String folderLocation) {

		File location = new File(folderLocation.trim());

		if (!location.exists()) {
			throw new RuntimeException("La ruta: " + location.getPath() + " No existe");
		}
		if (!location.isDirectory()) {
			throw new RuntimeException("La ruta: " + location.getPath() + " No es un directorio");
		}
		if (!location.canRead()) {
			throw new RuntimeException("La ruta: " + location.getPath() + " No puede leerse");
		}
	}

	public void validate_system_file(String fileLocation) {
		log.info("VAS_MONITOR validate_system_file: " + fileLocation);

		File location = new File(fileLocation);

		if (!location.exists()) {
			throw new RuntimeException("El archivo: " + location.getPath() + " No existe");
		}
		if (!location.isFile()) {
			throw new RuntimeException("El archivo: " + location.getPath() + " No es un archivo válido");
		}
		if (!location.canRead()) {
			throw new RuntimeException("El archivo: " + location.getPath() + " No puede leerse");
		}
	}

	public String getVasServerController() {

		String serviceController = "";
		try {
			serviceController = systemParameterLogic.getParamByCodeTexValue(Constantes.VAS_CONTROLLER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviceController;
	}

	//
	public String getOutputAlgTrcVideoFolder() {

		String outputFolder = "";
		try {
			outputFolder = systemParameterLogic.getParamByCodeTexValue(Constantes.OUTPUT_ALG_TRC_VIDEO_FOLDER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputFolder;
	}

	public Boolean getALG_TRC_REQUEST() {
		try {
			this.ALG_TRC_REQUEST = systemParameterLogic.getParamByCodeBooleanValue(Constantes.ALG_TRC_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ALG_TRC_REQUEST;
	}

	public void setALG_TRC_REQUEST(Boolean aLG_TRC_REQUEST) {
		ALG_TRC_REQUEST = aLG_TRC_REQUEST;
	}
	public Boolean isTestModeActivated() {

		Boolean isTestModeActivated = false;
		try {
			isTestModeActivated = systemParameterLogic.getParamByCodeBooleanValue(Constantes.TEST_MODE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isTestModeActivated;
	}


}
