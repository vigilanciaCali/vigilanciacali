package co.edu.usbcali.vas.monitorintegration.control.rest;

import java.io.File;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import co.edu.usbcali.vas.model.control.ISystemParameterLogic;
import co.edu.usbcali.vas.model.dto.AlgorithmDTO;
import co.edu.usbcali.vas.model.dto.VideoTransactionDTO;
import co.edu.usbcali.vas.monitor.control.IMonitorLogic;
import co.edu.usbcali.vas.monitor.control.IMonitorTrackerLogic;
import co.edu.usbcali.vas.monitor.email.control.rest.IEmailRestLogic;
import co.edu.usbcali.vas.trace.ISystemLogRestLogic;
import co.edu.usbcali.vas.utilities.Constantes;

@Scope("singleton")
@Service("MonitorIntegrationRestLogic")
public class MonitorIntegrationRestLogic implements IMonitorIntegrationRestLogic {
	private static final Logger log = LoggerFactory.getLogger(MonitorIntegrationRestLogic.class);

	@Autowired
	private ISystemParameterLogic systemParameterLogic;
	@Autowired
	private IMonitorLogic monitorLogic;
	@Autowired
	private IMonitorTrackerLogic monitorTrackerLogic;
	@Autowired
	private IEmailRestLogic emailRestLogic;
	@Autowired
	private ISystemLogRestLogic systemLogRestLogic;

	private Integer TIME_WAITFOR_ALG;
	private Integer TEST_TIME;

	/*
	 * http://localhost:5000/tracking 
	 * http://localhost:5000/anomalyDetetection
	 * http://localhost:5000/anomalyDetetectionState
	 * http://localhost:5000/trackingState
	 */

	private static String ANOMALY_DETECTION_STATE = "anomalyDetetectionState";
	private static String TRACKING_STATE = "trackingState";
	private static String TRACKING = "tracking";
	private static String ANOMALY_DETECTION = "anomalyDetetection";

	// VALIDATE STATE-------------------------------------------------------
	@Override
	@Transactional(readOnly = false)
	public Boolean isAnlAlgAvailable() throws Exception {
		log.info("VAS_SERVER_MONITOR MonitorIntegrationRestLogic isAnlAlgAvailable ");

		Boolean isAvailable = false;
		try {
			
			//TEST MODE
			 if(this.isTestModeActivated().booleanValue() == true) {
				 log.info("VAS_SERVER_MONITOR Anl Alg TEST MODE ACTIVATED----------------------");
				 isAvailable = true;
			 }else {
				 
				 String url = this.getServerAlgController() + ANOMALY_DETECTION_STATE;
					log.info("url: " + url);
					
					RestTemplate restTemplate = new RestTemplate();
					String data = restTemplate.getForObject(url, String.class);
					
					JSONObject object = new JSONObject(data);
					String result = object.get("flag_anl").toString();
					log.info("result: "+result);
					
					if(result.equals("true")) {
						isAvailable = true;
					}else {
						isAvailable = false;
					} 
			 }

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR isAnlAlgAvailable failed", e);
			isAvailable = false;
			systemLogRestLogic.save_systemLogRest("SERVER_MONITOR ERROR", "isAnlAlgAvailable - "+e);
		}
		return isAvailable;
	}

	@Override
	@Transactional(readOnly = false)
	public Boolean isTrcAlgAvailable() throws Exception {
		log.info("VAS_SERVER_MONITOR MonitorIntegrationRestLogic isTrcAlgAvailable ");

		Boolean isAvailable = false;
		try {
			
			 if(this.isTestModeActivated().booleanValue() == true) {
				 log.info("VAS_SERVER_MONITOR Traceker Alg TEST MODE ACTIVATED----------------------");
				 isAvailable = true;
			 }else {
				 String url = this.getServerAlgController() + TRACKING_STATE;
					log.info("url: " + url);
					RestTemplate restTemplate = new RestTemplate();
					String data = restTemplate.getForObject(url, String.class);
					
					JSONObject object = new JSONObject(data);
					String result = object.get("flag_tr").toString();
					log.info("result: "+result);
					
					if(result.equals("true")) {
						isAvailable = true;
					}else {
						isAvailable = false;
					} 
			 }
			

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR isTrcAlgAvailable failed", e);
			isAvailable = false;
		}
		return isAvailable;
	}

	// WAIT FOR
	// AVAILABILITY------------------------------------------------------------------------------

	@Override
	@Transactional(readOnly = false)
	public Boolean waitForAnlAlgAvailable() throws Exception {
		log.info("VAS_SERVER_MONITOR MonitorIntegrationRestLogic waitForAnlAlgAvailable ");

		Boolean isAvailable = false;
		try {
			while (isAvailable.booleanValue() == false) {
				log.info("waitForAnlAlgAvailables");
				Thread.sleep(this.getTIME_WAITFOR_ALG());
				log.info("waitForAnlAlgAvailable 30seg");

				// TEST
				// isAvailable = this.isAlgAvailableTest();

				isAvailable = this.isAnlAlgAvailable();

			}

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR waitForAnlAlgAvailable failed", e);
			isAvailable = false;
		}
		return isAvailable;
	}

	@Override
	@Transactional(readOnly = false)
	public Boolean waitForTrcAlgAvailable() throws Exception {
		log.info("VAS_SERVER_MONITOR MonitorIntegrationRestLogic waitForTrcAlgAvailable ");

		Boolean isAvailable = false;
		try {
			while (isAvailable.booleanValue() == false) {
				log.info("waitForTrcAlgAvailable");
				Thread.sleep(this.getTIME_WAITFOR_ALG());
				log.info("waitForTrcAlgAvailable");

				// TEST
				// isAvailable = this.isAlgAvailableTest();

				isAvailable = this.isTrcAlgAvailable();

			}

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR waitForAnlAlgAvailable failed", e);
			isAvailable = false;
		}
		return isAvailable;
	}

	// LAST VIDEO
	// PROCESSED------------------------------------------------------------------------------
	@Override
	@Transactional(readOnly = false)
	public String getLastVideoProcessedByAnlAlg() throws Exception {
		log.info("VAS_SERVER_MONITOR MonitorIntegrationRestLogic getLastVideoProcessedByAnlAlg ");

		String lastVideoData = "";
		try {

			String url = this.getServerAlgController() + ANOMALY_DETECTION;
			log.info("url: " + url);
			RestTemplate restTemplate = new RestTemplate();
			lastVideoData = restTemplate.getForObject(url, String.class);

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR getLastVideoProcessedByAnlAlg failed", e);
			lastVideoData = "";
		}
		return lastVideoData;
	}
	
	@Override
	@Transactional(readOnly = false)
	public String getLastVideoProcessedByTrcAlg() throws Exception {
		log.info("VAS_SERVER_MONITOR MonitorIntegrationRestLogic getLastVideoProcessedByTrcAlg ");

		String lastVideoData = "";
		try {

			String url = this.getServerAlgController() + TRACKING;
			log.info("url: " + url);
			RestTemplate restTemplate = new RestTemplate();
			lastVideoData = restTemplate.getForObject(url, String.class);

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR getLastVideoProcessedByTrcAlg failed", e);
			lastVideoData = "";
		}
		return lastVideoData;
	}

	// SEND TO PROCESS----------------------------------------------------------
	@Override
	@Transactional(readOnly = false)
	public Boolean sendDataToAnlService(AlgorithmDTO algorithmDTO) throws Exception {
		log.info("VAS_SERVER_MONITOR MonitorIntegrationRestLogic sendDataToAnlService ");

		Boolean sent = false;
		String jsonString = "";
		try {

			 //TEST MODE
			 if(this.isTestModeActivated().booleanValue() == true) {
				 log.info("VAS_SERVER_MONITOR Anl Alg TEST MODE ACTIVATED----------------------");
				 
				 //TRACE
				 systemLogRestLogic.save_systemLogRest("SERVER_MONITOR - External System", "7. sendDataToAnlService TEST MODE: "
						 +algorithmDTO.getInitTimeParam() + " - " + algorithmDTO.getFinalTimeParam()
						 + algorithmDTO.getVideoFile() + " - " + algorithmDTO.getInfo());
					
				 Thread.sleep(this.getTEST_TIME());
				 
				 //CREATE VIDEO
				 File file = new File(this.getOutputAlgAnlVideoFolder() + algorithmDTO.getVideoFile());
				 if (file.createNewFile())
				 {
				     log.info("File is created!");
				 } else {
					 log.info("File already exists.");
				 }
				 algorithmDTO.setErrorCode("100");
				 algorithmDTO.setMessage("ANOMALY DETECTION FINISHED SUCCESFULLY");
				 //TEST
				 this.monitorLogic.anomalyDetetectionResult(algorithmDTO);
				 
			 }else {
				 log.info("isTestModeActivated FALSE ");
				 
				 systemLogRestLogic.save_systemLogRest("SERVER_MONITOR", "sendDataToAnlService TEST MODE OFF");
				 
				 JSONObject parameters = new JSONObject();
				 parameters.put("time_start", algorithmDTO.getInitTimeParam());
				 parameters.put("time_end", algorithmDTO.getFinalTimeParam());

				 JSONArray parametersArray = new JSONArray();
				 parametersArray.put(parameters);
				 
				 jsonString = new JSONObject()
							.put("name_video_in", algorithmDTO.getVideoFileTemp())
							.put("name_video_out", algorithmDTO.getVideoFile())
							.put("parameters", parametersArray)
							.put("path_video_in", algorithmDTO.getTempVideoFolder())
							.put("path_video_out", algorithmDTO.getOutputVideoFolder())
							.put("video_transaction_id", algorithmDTO.getVideoTransactionId())
							.toString();
				 log.info("jsonString: "+jsonString);
				 
				 algorithmDTO.setJsonString(jsonString);
					
				 HttpHeaders headers = new HttpHeaders();
				 headers.setContentType(MediaType.APPLICATION_JSON);
				 HttpEntity<String> request = new HttpEntity<String>(jsonString, headers);
				 
				 String url = this.getServerAlgController() + "anomalyDetetection";
				 log.info("url: " + url);
				 RestTemplate restTemplate = new RestTemplate();
				 restTemplate.postForObject(url, request, String.class);
				 
				 /*String url = this.getServerAlgController() + "anomalyDetetection";
				 log.info("url: " + url);
				 RestTemplate restTemplate = new RestTemplate();
				 restTemplate.postForObject(url, jsonString, String.class);*/
				 
				 sent = true; 
				 
				 //NOTIFY TRACE
				 if(algorithmDTO.getUserEmail().equals("") == false || algorithmDTO.getUserEmail() != null) {
					 
					 systemLogRestLogic.save_systemLogRest("SERVER_MONITOR", "sendDataToAnlService, jsonString: "+jsonString);
					 
					 VideoTransactionDTO videoTransactionDTO = new VideoTransactionDTO();
					 videoTransactionDTO.setUserEmail(algorithmDTO.getUserEmail().trim().toLowerCase());
					 videoTransactionDTO.setAnalyticData(jsonString);
					 videoTransactionDTO.setVideoType("ANOMALOS");
					 
					 this.emailRestLogic.sendMailProcessingProcess(videoTransactionDTO); 
				 }
				
			 }
		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR sendDataToAnlService failed", e);
			sent = false;	
			//TRACE
			systemLogRestLogic.save_systemLogRest("SERVER_MONITOR ERROR", "sendDataToAnlService - JsonString: "+jsonString+" Exception"+e);
			//SEND EMAIL
			this.emailRestLogic.sendMailProcessingError(algorithmDTO);
		}
		return sent;
	}
	
	@Override
	@Transactional(readOnly = false)
	public Boolean sendDataToTrcService(AlgorithmDTO algorithmDTO) throws Exception {
		log.info("VAS_SERVER_MONITOR MonitorIntegrationRestLogic sendDataToTrcService ");

		Boolean sent = false;
		String jsonString = "";
		
		try {

		 //TEST MODE
		 if(this.isTestModeActivated().booleanValue() == true) {
			 
			 log.info("SERVER_MONITOR Anl Alg TEST MODE ACTIVATED----------------------");
			 
			 systemLogRestLogic.save_systemLogRest("SERVER_MONITOR - External System", "7. sendDataToTrcService TEST MODE: "
					 + algorithmDTO.getPosXParam() + " - " + algorithmDTO.getPosX2Param()
					 + algorithmDTO.getPosYParam() + " - " + algorithmDTO.getPosY2Param()
					 + algorithmDTO.getVideoFile() + " - " + algorithmDTO.getInfo()
					 );
				
			 Thread.sleep(this.getTEST_TIME());
			 
			 //CREATE VIDEO
			 File file = new File(this.getOutputAlgTrcVideoFolder() + algorithmDTO.getVideoFile());
			 if (file.createNewFile())
			 {
			     log.info("File is created!");
			 } else {
				 log.info("File already exists.");
			 }
			 algorithmDTO.setErrorCode("100");
			 algorithmDTO.setMessage("TRACKER FINISHED SUCCESFULLY");
			 //TEST
			 this.monitorTrackerLogic.trackerDetetectionResult(algorithmDTO);
			 
		 }else {
			 log.info("SERVER_MONITOR isTestModeActivated FALSE ");
			 
			 systemLogRestLogic.save_systemLogRest("SERVER_MONITOR", "6.5 sendDataToTrcService TEST MODE OFF");
			
			 JSONObject parameters = new JSONObject();
			 parameters.put("time_start", "");
			 parameters.put("x", algorithmDTO.getPosXParam());
			 parameters.put("y", algorithmDTO.getPosYParam());
			 parameters.put("width", algorithmDTO.getPosX2Param());
			 parameters.put("hight", algorithmDTO.getPosY2Param());

			 JSONArray parametersArray = new JSONArray();
			 parametersArray.put(parameters);
			 
			 jsonString = new JSONObject()
						.put("name_video_in", algorithmDTO.getVideoFileTemp())
						.put("name_video_out", algorithmDTO.getVideoFile())
						.put("parameters", parametersArray)
						.put("path_video_in", algorithmDTO.getTempVideoFolder())
						.put("path_video_out", algorithmDTO.getOutputVideoFolder())
						.put("video_transaction_id", algorithmDTO.getVideoTransactionId())
						.toString();
			 log.info("jsonString: "+jsonString);
			 
			 algorithmDTO.setJsonString(jsonString);
				
			 HttpHeaders headers = new HttpHeaders();
			 headers.setContentType(MediaType.APPLICATION_JSON);
			 HttpEntity<String> request = new HttpEntity<String>(jsonString, headers);
			 
			 String url = this.getServerAlgController() + "tracking";
			 log.info("url: " + url);
			 RestTemplate restTemplate = new RestTemplate();
			 restTemplate.postForObject(url, request, String.class);
			 
			 /*String url = this.getServerAlgController() + "tracking";
			 log.info("url: " + url);
			 RestTemplate restTemplate = new RestTemplate();
			 restTemplate.postForObject(url, jsonString, String.class);*/
			 
			 sent = true;
			 
			 //NOTIFY
			 if(algorithmDTO.getUserEmail().equals("") == false || algorithmDTO.getUserEmail() != null) {
				 
				 systemLogRestLogic.save_systemLogRest("SERVER_MONITOR", "6.5.1 sendMailProcessingProcess, jsonString: "+jsonString);
				 
				 VideoTransactionDTO videoTransactionDTO = new VideoTransactionDTO();
				 videoTransactionDTO.setUserEmail(algorithmDTO.getUserEmail().trim().toLowerCase());
				 videoTransactionDTO.setAnalyticData(jsonString);
				 videoTransactionDTO.setVideoType("TRACKER");
				 
				 this.emailRestLogic.sendMailProcessingProcess(videoTransactionDTO); 
			 }
		 }
	
		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR sendDataToTrcService failed", e);
			sent = false;	
			//TRACE
			systemLogRestLogic.save_systemLogRest("SERVER_MONITOR ERROR", "sendDataToTrcService - JsonString: "+jsonString+" Exception"+e);
			//SEND EMAIL
			this.emailRestLogic.sendMailProcessingError(algorithmDTO);
		}
		return sent;
	}

	
	//GETTERS SETTER----------------------------------------------------

	public String getServerAlgController() {

		String serviceController = "";
		try {
			serviceController = systemParameterLogic.getParamByCodeTexValue(Constantes.SERVER_ALGORITHM_CONTROLLER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviceController;
	}

	public Boolean isAlgAvailableTest() {

		Boolean isAvailable = false;
		try {
			isAvailable = systemParameterLogic.getParamByCodeBooleanValue(Constantes.ALG_ANL_AVAILABLE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isAvailable;
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
	
	public String getOutputAlgAnlVideoFolder() {

		String outputFolder = "";
		try {
			outputFolder = systemParameterLogic.getParamByCodeTexValue(Constantes.OUTPUT_ALG_ANL_VIDEO_FOLDER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputFolder;
	}
	
	public String getOutputAlgTrcVideoFolder() {

		String outputFolder = "";
		try {
			outputFolder = systemParameterLogic.getParamByCodeTexValue(Constantes.OUTPUT_ALG_TRC_VIDEO_FOLDER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputFolder;
	}

	public Integer getTIME_WAITFOR_ALG() {
		try {
			TIME_WAITFOR_ALG = systemParameterLogic.getParamByCodeNumValue(Constantes.TIME_WAITFOR_ALG);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TIME_WAITFOR_ALG;
	}

	public Integer getTEST_TIME() {
		try {
			TEST_TIME = systemParameterLogic.getParamByCodeNumValue(Constantes.TEST_TIME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TEST_TIME;
	}

	public void setTIME_WAITFOR_ALG(Integer tIME_WAITFOR_ALG) {
		TIME_WAITFOR_ALG = tIME_WAITFOR_ALG;
	}

	public void setTEST_TIME(Integer tEST_TIME) {
		TEST_TIME = tEST_TIME;
	}

}
