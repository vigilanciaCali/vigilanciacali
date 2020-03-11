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
@Service("MonitorLogic")
public class MonitorLogic implements IMonitorLogic {
	private static final Logger log = LoggerFactory.getLogger(MonitorLogic.class);

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

	private Boolean ALG_ANL_REQUEST = false;
	private Boolean ALG_TRC_REQUEST = false;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void processVideoWithAnomalousEventsAlg() throws Exception {
		log.info("2.0 VAS_SERVER_MONITOR MonitorLogic processVideoWith ANOMALOUS Alg-------------------------------------------");

		Boolean located = false;
		Boolean sent = false;

		AlgorithmDTO algorithmDTO = null;
		try {

			// CALL ALG ANL SERVICE
			log.info("2.1 VAS_SERVER_MONITOR isAnlAlgAvailable");
			Boolean anlServiceAvailable = integrationRestLogic.isAnlAlgAvailable();

			if (anlServiceAvailable.booleanValue() == true) {
				
				log.info("2.2 VAS_SERVER_MONITOR anlServiceAvailable.booleanValue() == true");
				
				systemLogRestLogic.save_systemLogRest("SERVER_MONITOR", "anlServiceAvailable");

				algorithmDTO = queueLogic.receiveFirstMessageAnlQueue();
				
				log.info("2.3 VAS_SERVER_MONITOR DEQUEUE NEW MESSAGE receiveFirstMessageAnlQueue");
				log.info("2.4 VAS_SERVER_MONITOR DEQUEUE NEW MESSAGE: "+algorithmDTO.getVideoTransactionId());

				if (algorithmDTO != null) {
					
					log.info("2.5 VAS_SERVER_MONITOR algorithmDTO != null");

					log.info("getUserId: " + algorithmDTO.getUserId());
					log.info("getUserEmail: " + algorithmDTO.getUserEmail());
					log.info("getVideoFile: " + algorithmDTO.getVideoFile());
					log.info("getVideoFileTemp: " + algorithmDTO.getVideoFileTemp());
					log.info("getInitTimeParam: " + algorithmDTO.getInitTimeParam());
					log.info("getFinalTimeParam: " + algorithmDTO.getFinalTimeParam());
					log.info("getTempVideoFolder: " + algorithmDTO.getTempVideoFolder());
					log.info("getOutputVideoFolder: " + algorithmDTO.getOutputVideoFolder());
					log.info("getVideoTransactionId: " + algorithmDTO.getVideoTransactionId());
					log.info("getUrl: " + algorithmDTO.getUrl());

					systemLogRestLogic.save_systemLogRest("SERVER_MONITOR", "receiveFirstMessageAnlQueue, "+algorithmDTO.getVideoTransactionId());
					
					// validate videTemp location
					located = this.searchVideoInFolder(algorithmDTO.getVideoFileTemp(),
							algorithmDTO.getTempVideoFolder());

					if (located.booleanValue() == true) {
						log.info(algorithmDTO.getVideoFileTemp() + " videoFileTemp LOCATED");

						systemLogRestLogic.save_systemLogRest("SERVER_MONITOR", "searchVideoInFolder, LOCATED: "+algorithmDTO.getVideoFileTemp());
						
						//SAVE VIDEO & STATUS NEW
						//TODO SAVE VIDEO & STATUS NEW
						this.saveVideo(algorithmDTO);
						
						systemLogRestLogic.save_systemLogRest("SERVER_MONITOR", "saveVideo");
						// SEND TO ANL ALG
						sent = integrationRestLogic.sendDataToAnlService(algorithmDTO);
						// if send to processing fail
						if (sent == false) {

							integrationRestLogic.sendDataToAnlService(algorithmDTO);

						}else {
							systemLogRestLogic.save_systemLogRest("SERVER_MONITOR", "sendDataToAnlService SENT TO PROCESS ");
							//TODO SAVE VIDEO & STATUS pending
				
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
				systemLogRestLogic.save_systemLogRest("SERVER_MONITOR ERROR", "anlService NOT Available");
				// ACTION WAIT UNTIL AVAILABLE (ASK C/30 SEG)
				Boolean isAvailable = integrationRestLogic.waitForAnlAlgAvailable();
				// IF ALG RESPONSE OK
				if (isAvailable.booleanValue() == true) {
					// SEND TO ANL ALG
					integrationRestLogic.sendDataToAnlService(algorithmDTO);
				}
			}

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR MonitorLogic processVideoWithAnomalousEventsAlg", e);
			systemLogRestLogic.save_systemLogRest("SERVER_MONITOR ERROR", "processVideoWithAnomalousEventsAlg - "+e);
		}
	}
	
	//FUNCTION FOR TEST MODE
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void processVideoWithAnomalousEventsAlgTestMode() throws Exception {
		log.info("2.0 VAS_SERVER_MONITOR MonitorLogic processVideoWithAnomalousEventsAlgTestMode-------------------------------------------");

		AlgorithmDTO algorithmDTO = null;
		try {

				algorithmDTO = queueLogic.receiveFirstMessageAnlQueue();
				log.info("VAS_SERVER_MONITOR DEQUEUE NEW MESSAGE");
				
				if (algorithmDTO != null) {

					log.info("getUserId: " + algorithmDTO.getUserId());
					log.info("getUserEmail: " + algorithmDTO.getUserEmail());
					log.info("getVideoFile: " + algorithmDTO.getVideoFile());
					log.info("getVideoFileTemp: " + algorithmDTO.getVideoFileTemp());
					log.info("getInitTimeParam: " + algorithmDTO.getInitTimeParam());
					log.info("getFinalTimeParam: " + algorithmDTO.getFinalTimeParam());
					log.info("getTempVideoFolder: " + algorithmDTO.getTempVideoFolder());
					log.info("getOutputVideoFolder: " + algorithmDTO.getOutputVideoFolder());
					log.info("getVideoTransactionId: " + algorithmDTO.getVideoTransactionId());
					log.info("getUrl: " + algorithmDTO.getUrl());

					//TRACE
					systemLogRestLogic.save_systemLogRest("SERVER_MONITOR - External System", "5. receiveFirstMessageAnlQueue TEST MODE: "+algorithmDTO.getVideoTransactionId());
					
					//SAVE VIDEO & STATUS NEW
					this.saveVideo(algorithmDTO);
					
					//TRACE
					systemLogRestLogic.save_systemLogRest("SERVER_MONITOR - External System", "6. saveVideo TEST MODE");
					
					// SEND TO ANL ALG
					integrationRestLogic.sendDataToAnlService(algorithmDTO);
				}

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR MonitorLogic processVideoWithAnomalousEventsAlgTestMode", e);
			//TRACE
			systemLogRestLogic.save_systemLogRest("SERVER_MONITOR ERROR", "processVideoWithAnomalousEventsAlgTestMode, exception: "+e);
			
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void startAnlAlgRequest() throws Exception {
		log.info("1.0 VAS_SERVER_MONITOR MonitorLogic startAnlAlgRequest");

		try {

			SystemParameter parameter = systemParameterLogic.getSystemParamByCode(Constantes.ALG_ANL_REQUEST);
			parameter.setBooleanValue(true);
			systemParameterLogic.updateSystemParameter(parameter);

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR MonitorLogic startAnlAlgRequest", e);

		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void stopAnlAlgRequest() throws Exception {
		log.info("1.0 VAS_SERVER_MONITOR MonitorLogic stopAnlAlgRequest");

		try {

			SystemParameter parameter = systemParameterLogic.getSystemParamByCode(Constantes.ALG_ANL_REQUEST);
			parameter.setBooleanValue(false);
			systemParameterLogic.updateSystemParameter(parameter);

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR MonitorLogic stopAnlAlgRequest", e);

		}
	}

	//FUNCTION CALLED BY CRON JOB --------------------------------------------------------
	@Override
	public void validateAnlAlgRequest() throws Exception {
		log.info("VAS_SERVER_MONITOR MonitorLogic validateAnlAlgRequest");

		try {

			Boolean request = this.getALG_ANL_REQUEST().booleanValue();

			if(isTestModeActivated().booleanValue() == true) {
				//TEST MODE
				log.info("-----------------------TEST MODE ON-------------------------------");

				this.processVideoWithAnomalousEventsAlgTestMode();
				this.stopAnlAlgRequest();
				systemLogRestLogic.save_systemLogRest("SERVER_MONITOR - External System", "TEST MODE ON");
				
			}
			if (request.booleanValue() == true && isTestModeActivated().booleanValue() == false) {
				log.info("-----------------------TEST MODE OFF-------------------------------");
				this.processVideoWithAnomalousEventsAlg();
				this.stopAnlAlgRequest();
				log.info("VAS_SERVER_MONITOR MonitorLogic validateAnlAlgRequest call process");
				systemLogRestLogic.save_systemLogRest("SERVER_MONITOR", "TEST MODE OFF");
			}

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR MonitorLogic validateAnlAlgRequest", e);
			this.stopAnlAlgRequest();
		}
	}

	// ALG RESULT--------------------------------------------------------
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void anomalyDetetectionResult(AlgorithmDTO data) throws Exception {
		log.info("2.1 VAS_SERVER_MONITOR MonitorLogic anomalyDetetectionResult FINISHED-------------------------------------------");

		Boolean located = false;
		
		Video video = null;

		try {
			
			if(data.getErrorCode().equals("100") == true) {
				
				log.info("2.2 VAS_SERVER_MONITOR MonitorLogic anomalyDetetectionResult data.getErrorCode().equals(\"100\") == true");

				//TRACE
				systemLogRestLogic.save_systemLogRest("SERVER_MONITOR - External System", "8. anomalyDetetectionResult: "
						+data.getErrorCode()+" - "+data.getMessage()+" - "+data.getVideoTransactionId());
				
				// GET VIDEO 
				//TODO GET VIDEO DATA local db
				video = videoLogic.getVideoByTransactionId(data.getVideoTransactionId());
				log.info("2.3 VAS_SERVER_MONITOR MonitorLogic getVideoByTransactionId: ");

				
				if(video != null) {
					
					log.info("2.2 VAS_SERVER_MONITOR MonitorLogic anomalyDetetectionResult video != null");

					log.info("video getUrl: " + video.getUrl());
					log.info("video getDescription: " + video.getDescription());
					log.info("video getSrc: " + video.getSrc());
					log.info("video getTransactionId: " + video.getTransactionId());
					log.info("video getType: " + video.getVideoType().getDescription());
					
					//TRACE
					systemLogRestLogic.save_systemLogRest("SERVER_MONITOR - External System", "9. anomalyDetetectionResult getVideoData localDB: "+video.getDescription());
				
					// GET VIDEO from VAS REST
					//videoTransactionDTO = monitorRestLogic.getVideoTransactionByIdRest(data.getVideoTransactionId().trim());
					
					if (video.getDescription().equals("") != true) {
						located = this.searchVideoInFolder(video.getDescription().trim(), this.getOutputAlgAnlVideoFolder());
						log.info("2.4 VAS_SERVER_MONITOR MonitorLogic searchVideoInFolder: ");
						
					}else {
						//TRACE
						systemLogRestLogic.save_systemLogRest("SERVER_MONITOR - External System", "anomalyDetetectionResult getVideoTransactionById ERROR: "
								+data.getVideoTransactionId());
						log.info("2.4 VAS_SERVER_MONITOR MonitorLogic searchVideoInFolder ERROR VIDEO NOT FOUND ");
						
					}
				}else {
					//TRACE
					systemLogRestLogic.save_systemLogRest("SERVER_MONITOR - External System", "anomalyDetetectionResult getVideoTransactionById VIDEO NOT FOUND: "+data.getVideoTransactionId());
				
				}

				if (located.booleanValue() == true) {
					
					log.info("2.5 VAS_SERVER_MONITOR MonitorLogic located.booleanValue() == true ");

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
					systemLogRestLogic.save_systemLogRest("SERVER_MONITOR - External System", "10. anomalyDetetectionResult sendMessageToVasOutQueue: "+videoTransactionDTO.getDescription());
					
					//NOTIFY VAS TO PROCESS
					this.monitorRestLogic.processAlgResult();
					
					//TRACE
					systemLogRestLogic.save_systemLogRest("SERVER_MONITOR - External System", "11. processAlgResult to VAS Cloud");
					
				} else {
					//TRACE
					systemLogRestLogic.save_systemLogRest("SERVER_MONITOR - External System ERROR", "searchVideoInFolder NOT LOCATED");
					// TODO NOTIFY ADMIN
				}
			}else {
				
				//TRACE
				systemLogRestLogic.save_systemLogRest("SERVER_MONITOR - External System ERROR", "CODE"+data.getErrorCode());
				// TODO NOTIFY USER
			}

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR MonitorLogic anomalyDetetectionResult", e);
			//TRACE
			systemLogRestLogic.save_systemLogRest("SERVER_MONITOR - External System", "anomalyDetetectionResult error: "+e.getMessage());
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
	public void sendToProcessAnomalousAlg() throws Exception {
		log.info("VAS_SERVER_MONITOR MonitorLogic sendToProcessAnomalousAlg ");

		try {

			Boolean status = this.checkVasControllerStatus();

			if (status.booleanValue() == true) {
				String url = this.getVasServerController() + "alg/anlsent/";
				log.info("url: " + url);
				RestTemplate restTemplate = new RestTemplate();
				Boolean sent = restTemplate.postForObject(url, true, Boolean.class);
				log.info("VAS_SERVER_MONITOR MonitorLogic sendToProcessAnomalousAlg: " + sent.booleanValue());
			}
		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR sendToProcessAnomalousAlg failed", e);

		}
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
				
				VideoType videoType = videoTypeLogic.getVideoType(2);
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
	
	
	//MONITORING
	@Override
	public Boolean validate_system_folderHddMonitoring() throws Exception {

		Boolean valid = true;
		
		try {
			
			File location = new File(this.getOutputAlgAnlVideoFolder().trim());

			if (!location.exists()) {
				throw new RuntimeException("La ruta: " + location.getPath() + " No existe");
			}
			if (!location.isDirectory()) {
				throw new RuntimeException("La ruta: " + location.getPath() + " No es un directorio");
			}
			if (!location.canRead()) {
				throw new RuntimeException("La ruta: " + location.getPath() + " No puede leerse");
			}
			if (!location.canWrite()) {
				throw new RuntimeException("La ruta: " + location.getPath() + " No puede escribirse");
			}	

		} catch (Exception e) {
			valid = false;
			log.error("validate_system_folderHddMonitoring failed",e);
		}
		return valid;
		
		
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
	public String getOutputAlgAnlVideoFolder() {

		String outputFolder = "";
		try {
			outputFolder = systemParameterLogic.getParamByCodeTexValue(Constantes.OUTPUT_ALG_ANL_VIDEO_FOLDER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputFolder;
	}

	public Boolean getALG_ANL_REQUEST() {
		try {
			this.ALG_ANL_REQUEST = systemParameterLogic.getParamByCodeBooleanValue(Constantes.ALG_ANL_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ALG_ANL_REQUEST;
	}

	public Boolean getALG_TRC_REQUEST() {
		try {
			this.ALG_TRC_REQUEST = systemParameterLogic.getParamByCodeBooleanValue(Constantes.ALG_TRC_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ALG_TRC_REQUEST;
	}

	public void setALG_ANL_REQUEST(Boolean aLG_ANL_REQUEST) {
		ALG_ANL_REQUEST = aLG_ANL_REQUEST;
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
