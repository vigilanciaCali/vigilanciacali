package co.edu.usbcali.vas.video.control.rest;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import co.edu.usbcali.vas.model.Users;
import co.edu.usbcali.vas.model.VideoStatus;
import co.edu.usbcali.vas.model.VideoTransaction;
import co.edu.usbcali.vas.model.VideoType;
import co.edu.usbcali.vas.model.control.ISystemParameterLogic;
import co.edu.usbcali.vas.model.control.IUsersLogic;
import co.edu.usbcali.vas.model.control.IVideoStatusLogic;
import co.edu.usbcali.vas.model.control.IVideoTransactionLogic;
import co.edu.usbcali.vas.model.control.IVideoTypeLogic;
import co.edu.usbcali.vas.model.dto.AlgorithmDTO;
import co.edu.usbcali.vas.queue.IQueueLogic;
import co.edu.usbcali.vas.security.control.ISecurityLogic;
import co.edu.usbcali.vas.trace.ISystemLogRestLogic;
import co.edu.usbcali.vas.utilities.Constantes;
import co.edu.usbcali.vas.utilities.FacesUtils;
import co.edu.usbcali.vas.utilities.Fechas;

@Scope("singleton")
@Service("VideoAnalysisLogicRest")
public class VideoAnalysisLogicRest implements IVideoAnalysisLogicRest {
	private static final Logger log = LoggerFactory.getLogger(VideoAnalysisLogicRest.class);

	@Autowired
	private IVideoTransactionLogic videoTransactionLogic;

	@Autowired
	private IVideoTypeLogic videoTypeLogic;

	@Autowired
	private IVideoStatusLogic statusLogic;

	@Autowired
	private IUsersLogic usersLogic;

	@Autowired
	private ISystemParameterLogic systemParameterLogic;

	@Autowired
	private IMonitorLogicRest monitorLogicRest;

	@Autowired
	private IQueueLogic queueLogic;
	
	@Autowired
	private ISecurityLogic securityLogic;
	
	@Autowired
	private ISystemLogRestLogic systemLogRestLogic;

	// Remote WebServer
	private String web_server = "";
	private String web_server_temp = "";
	// Server output location
	private String alg_anl_output_server;
	private String alg_trc_output_server;

	private String video_format = "";
	private String input_video_folder = "";

	private String output_alg_anl_video_folder;
	private String output_alg_trc_video_folder;

	private String temp_video_folder = "";

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String analyze_anomalousAlgFromTempLocationREST(String videoFileTemp, String videoLenght,
			String initTimeParam, String finalTimeParam, String info) throws Exception {
		log.info("1.VAS VideoAnalysisLogicREST analyze_anomalousAlgFromTempLocationREST---------------------------------------------");

		String videoFile = null;
		String pictureFile = null;
		String videoFileGenerated = null;
		AlgorithmDTO algorithmDTO = null;
		try {

			log.info("videoFileTemp: " + videoFileTemp);
			log.info("videoSize: " + videoLenght);
			log.info("initTimeParam: " + initTimeParam);
			log.info("finalTimeParam: " + finalTimeParam);

			// Generate VideoUniqueId+format
			videoFileGenerated = this.generateVideoFileId();
			videoFile = videoFileGenerated+ "." + this.getVideo_format();
			// Generate pictureFileNme
			pictureFile = videoFileGenerated+".png";

			// Trace log
			systemLogRestLogic.save_systemLogRest("VAS", "INIT PROCESS ANOMALOUS ALG - "+videoFile);
			//systemVideoLogLogic.save_systemVideoLog("INIT PROCESS", "ANOMALOUS ALG", videoFile);

			// PROCESS VIDEO ANALYTICS ALG BY REST--------------------------------------------------------

			// CHECK ALG PROCESSING STATUS
			log.info("VAS checkAnlAlgAvailable");
			Boolean algAnlStatus = this.monitorLogicRest.checkAnlAlgAvailable();
			log.info("VAS checkAnlAlgAvailable status:" +algAnlStatus);
			
			if (algAnlStatus.booleanValue() == true) {
				log.info("VAS algAnlStatus TRUE");
				// SET alg DATA
				Users user = (Users) FacesUtils.getfromSession(Constantes.SESSION_USER_OBJECT);
				
				algorithmDTO = this.setAlgRestData(
						this.securityLogic.generateRandomString(),
						user.getId().toString(),
						user.getLogin().toString().trim().toLowerCase(),
						videoFileTemp, videoFile,
						pictureFile,
						this.getLocalTemp_video_folder(), 
						this.getOutput_alg_anl_video_folder(),
						this.getAlg_anl_output_server()+videoFile,
						videoLenght, info, initTimeParam, finalTimeParam,
						"", "", "", "");

				// 2. SAVE VIDEO_TRANSACTION TO DB--------------------------------------------------------------
				log.info("VideoAnalysisLogic saveVideoTransaction: "+algorithmDTO.getVideoTransactionId());
				this.saveVideoTransactionAnl(algorithmDTO);
				// QUEUE VIDEO TO SQS
				this.queueLogic.sendMessageAnlQueue(algorithmDTO);
				//trace
				systemLogRestLogic.save_systemLogRest("VAS", "SAVE VIDEO_TRANSACTION ANOMALOUS ALG - "+algorithmDTO.getVideoTransactionId().trim());
				
				//systemVideoLogLogic.save_systemVideoLog("SAVE VIDEO_TRANSACTION", "ANOMALOUS" , algorithmDTO.getVideoTransactionId().trim());
				
				log.debug("save Video successful");

			} else {
				log.info("VAS algAnlStatus FALSE");
				
				//TRACE
				systemLogRestLogic.save_systemLogRest("VAS", "ANOMALOUS ALG NOT AVAILABLE");
				
				// SET alg DATA
				algorithmDTO = this.setAlgRestData(this.securityLogic.generateRandomString(),
						FacesUtils.getfromSession(Constantes.SESSION_USER_ID).toString(), 
						FacesUtils.getfromSession(Constantes.SESSION_USER_LOGIN).toString(),
						videoFileTemp, videoFile,
						pictureFile,
						this.getLocalTemp_video_folder(), 
						this.getOutput_alg_anl_video_folder(),
						this.getAlg_anl_output_server()+videoFile,
						videoLenght, info, initTimeParam, finalTimeParam,
						"", "", "", "");
				
				//1. SAVE TRANSACTION
				this.saveVideoTransactionAnl(algorithmDTO);
				//2. QUEUE VIDEO
				this.queueLogic.sendMessageAnlQueue(algorithmDTO);
				//TRACE
				systemLogRestLogic.save_systemLogRest("VAS", "SAVE VIDEO_TRANSACTION ANOMALOUS ALG - "+algorithmDTO.getVideoTransactionId().trim());
				//3. WAIT FOR ANL
				this.monitorLogicRest.waitForAnlAlgAvailable();
				//TRACE
				systemLogRestLogic.save_systemLogRest("VAS", "waitForAnlAlgAvailable ANOMALOUS ALG");
				
				
			}

		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error ejecutando VideoAnalisis");
			log.error("VideoAnalysisLogic uploadVideoToFolder failed", e);
			throw e;
		}
		return videoFile;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String analyze_trackerAlgFromTempLocationREST(String videoFileTemp, String videoSize, String posXParam,
			String posYParam, String posX2Param, String posY2Param, String info) throws Exception {
		log.info("2. VideoAnalysisLogic analyze_trackerAlgFromTempLocationREST------------------------------");

		String videoFile = null;
		AlgorithmDTO algorithmDTO = null;
		
		try {
			// 1.Set local name
			log.info("videoFileTemp: " + videoFileTemp);
			log.info("videoSize: " + videoSize);
			
			
			//572
			log.info("posXParam: " + posXParam);
			//435
			log.info("posYParam: " + posYParam);
			//668
			log.info("posX2Param: " + posX2Param);
			//693
			log.info("posY2Param: " + posY2Param);

			// Generate VideoUniqueId
			videoFile = this.generateVideoFileId()+ "." + this.getVideo_format();
			//TRACE
			systemLogRestLogic.save_systemLogRest("VAS", "2. INIT PROCESS TRACKER ALG - "+videoFile);

			// PROCESS VIDEO ANALYTICS ALG BY REST--------------------------------------------------------

			// CHECK ALG PROCESSING STATUS
			Boolean algStatus = this.monitorLogicRest.checkTrcAlgAvailable();
			log.info("VAS checkTrcAlgAvailable status:" +algStatus);
			
			//TRACE
			systemLogRestLogic.save_systemLogRest("VAS", "2.1 checkTrcAlgAvailable - "+algStatus.toString());
	
			// PROCESS VIDEO ANALYTICS ALG
			
			if (algStatus.booleanValue() == true) {
				log.info("VAS algAnlStatus TRUE");
				// SET alg DATA
				
				Integer width = (Integer.valueOf(posX2Param) - Integer.valueOf(posXParam) +1);
				//(x2 - x1) +1
				Integer hight = (Integer.valueOf(posY2Param) - Integer.valueOf(posYParam) +1);
				//(y2 -y1) +1
				
				algorithmDTO = this.setAlgRestData(
						//VIDEO TRANSACTION ID
						this.securityLogic.generateRandomString(),
						//USER ID
						FacesUtils.getfromSession(Constantes.SESSION_USER_ID).toString(),
						//USER EMAIL
						FacesUtils.getfromSession(Constantes.SESSION_USER_LOGIN).toString(),
						//VIDEO FILETEMP
						videoFileTemp,
						//VIDEO FILE
						videoFile,
						//PICTURE FILE
						"",
						//TEMP VIDEO FOLDER
						this.getLocalTemp_video_folder(), 
						//OUTPUT VIDEO FOLDER
						this.getOutput_alg_trc_video_folder(),
						//URL
						this.getAlg_trc_output_server()+videoFile,
						//LENGHT
						videoSize, 
						//INFO
						info, 
						//ANL ALG PARAMS
						"", "",
						//TRC PARAMS
						posXParam, posYParam, 
						//X2 width
						width.toString(), 
						//Y2 hight
						hight.toString());

				// 2. SAVE VIDEO_TRANSACTION TO DB--------------------------------------------------------------
				log.info("VideoAnalysisLogic saveVideoTransaction: "+algorithmDTO.getVideoTransactionId());
				this.saveVideoTransactionTrc(algorithmDTO);
				//TRACE
				systemLogRestLogic.save_systemLogRest("VAS", "2.2 saveVideoTransactionTrc - "+algorithmDTO.getVideoTransactionId());
				
				// QUEUE VIDEO TO SQS
				this.queueLogic.sendMessageTrcQueue(algorithmDTO);
				//TRACE
				systemLogRestLogic.save_systemLogRest("VAS", "2.3 sendMessageTrcQueue - "+algorithmDTO.getVideoTransactionId().trim());
			
				log.debug("save Video successful");

			} else {
				log.info("VAS algAnlStatus FALSE");
				
				//TRACE
				systemLogRestLogic.save_systemLogRest("VAS", "TRACKER ALG NOT AVAILABLE");
				
				// SET alg DATA
				algorithmDTO = this.setAlgRestData(
						this.securityLogic.generateRandomString(),
						FacesUtils.getfromSession(Constantes.SESSION_USER_ID).toString(),
						FacesUtils.getfromSession(Constantes.SESSION_USER_LOGIN).toString(),
						videoFileTemp, videoFile,
						"",
						this.getLocalTemp_video_folder(), 
						this.getOutput_alg_trc_video_folder(),
						this.getAlg_trc_output_server()+videoFile,
						videoSize, info, "", "",
						posXParam, posYParam, posX2Param, posY2Param);
				//1. SAVE TRANSACTION
				this.saveVideoTransactionTrc(algorithmDTO);
				//2. QUEUE VIDEO
				this.queueLogic.sendMessageTrcQueue(algorithmDTO);
				//TRACE
				systemLogRestLogic.save_systemLogRest("VAS", "SAVE VIDEO_TRANSACTION TRACKER ALG - "+algorithmDTO.getVideoTransactionId().trim());
				//3. WAIT FOR AUTO DEQUEUE PROCESS 
				this.monitorLogicRest.waitForTrcAlgAvailable();
				//TRACE
				systemLogRestLogic.save_systemLogRest("VAS", "waitForTrcAlgAvailable TRACKER ALG");
				
				
			}
	
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error ejecutando VideoAnalisis");
			log.error("VideoAnalysisLogic analyze_trackerAlgFromTempLocationREST failed", e);
			//TRACE
			systemLogRestLogic.save_systemLogRest("VAS", " analyze_trackerAlgFromTempLocationREST ERROR: "+ e.getMessage());
			throw e;
		}
		return videoFile;
	}

	public String generateVideoFileId() {
		String videoFile = null;
		videoFile = Fechas.dateToStr(Fechas.actualDateWithTimeStamp(), Constantes.FORMATO_FECHA_VIDEO);
		log.info("videoFile: " + videoFile);
		return videoFile;
	}

	public AlgorithmDTO setAlgRestData(
			String videoTransactionId,
			String userId, String userEmail, 
			String videoFileTemp, String videoFile, 
			String pictureFile,
			String tempVideoFolder,
			String outputVideoFolder,
			String url,
			String lenght, String info, 
			String initTimeParam, String finalTimeParam,
			String posXParam, String posYParam, String posX2Param, String posY2Param) {

		AlgorithmDTO algDTO = new AlgorithmDTO();
		algDTO.setVideoTransactionId((videoTransactionId != null) ? videoTransactionId : null);
		algDTO.setUserId((userId != null) ? userId : null);
		algDTO.setUserEmail((userEmail != null) ? userEmail : null);
		algDTO.setVideoFileTemp((videoFileTemp != null) ? videoFileTemp : null);
		algDTO.setVideoFile((videoFile != null) ? videoFile : null);
		algDTO.setTempVideoFolder((tempVideoFolder != null) ? tempVideoFolder : null);
		algDTO.setPictureFile((pictureFile != null) ? pictureFile : null);
		algDTO.setOutputVideoFolder((outputVideoFolder != null) ? outputVideoFolder : null);
		algDTO.setUrl(url);
		algDTO.setLenght((lenght != null) ? lenght : null);
		algDTO.setInfo((info != null) ? info : null);
		// ANOMALOUS
		algDTO.setInitTimeParam((initTimeParam != null) ? initTimeParam : null);
		algDTO.setFinalTimeParam((finalTimeParam != null) ? finalTimeParam : null);
		// TRACKER
		algDTO.setPosXParam((posXParam != null) ? posXParam : null);
		algDTO.setPosYParam((posYParam != null) ? posYParam : null);
		algDTO.setPosX2Param((posX2Param != null) ? posX2Param : null);
		algDTO.setPosY2Param((posY2Param != null) ? posY2Param : null);

		return algDTO;
	}

	public void saveVideoTransactionAnl(AlgorithmDTO algorithmDTO) throws Exception {
		log.info("VAS VideoAnalysisLogicREST saveVideoTransaction");

		try {

			VideoTransaction entity = new VideoTransaction();

			entity.setVideoTransactionId(algorithmDTO.getVideoTransactionId().trim());
			entity.setDescription(algorithmDTO.getVideoFile());
			entity.setLenght(algorithmDTO.getLenght());
			entity.setAnalyticData("InitTime: " + algorithmDTO.getInitTimeParam() + " Final Time: " + algorithmDTO.getFinalTimeParam());
			entity.setUrl(algorithmDTO.getUrl());
			entity.setUrlPicture(this.getAlg_anl_output_server() + algorithmDTO.getPictureFile());
			entity.setCreatedBy(String.valueOf(FacesUtils.getfromSession(Constantes.SESSION_USER_LOGIN)));
			entity.setCreatedAt(new Date());
			entity.setInfo(algorithmDTO.getInfo());

			Users user = usersLogic
					.getUsers(Integer.valueOf(FacesUtils.getfromSession(Constantes.SESSION_USER_ID).toString()));
			entity.setUsers(user);

			VideoType videoType = videoTypeLogic.getVideoTypeByCode(Constantes.ANOMALOUS);
			entity.setVideoType(videoType);
			
			VideoStatus videoStatus = statusLogic.getVideoStatusByCode(Constantes.NEW);
			entity.setVideoStatus(videoStatus);

			// Save
			videoTransactionLogic.saveVideoTransaction(entity);

			log.debug("save saveVideoTransaction successful");
		} catch (Exception e) {
			log.error("VAS VideoAnalysisLogicREST saveVideoTransaction failed", e);
			throw e;
		}
	}
	//TEST
	public void saveVideoTransactionAnlRestTest(AlgorithmDTO algorithmDTO) throws Exception {
		log.info("VAS VideoAnalysisLogicREST saveVideoTransactionAnlRestTest TEST");

		try {

			VideoTransaction entity = new VideoTransaction();

			entity.setVideoTransactionId(algorithmDTO.getVideoTransactionId().trim());
			entity.setDescription(algorithmDTO.getVideoFile());
			entity.setLenght(algorithmDTO.getLenght());
			entity.setAnalyticData("InitTime: " + algorithmDTO.getInitTimeParam() + " Final Time: " + algorithmDTO.getFinalTimeParam());
			entity.setUrl(algorithmDTO.getUrl());
			entity.setUrlPicture(this.getAlg_anl_output_server() + algorithmDTO.getPictureFile());
			entity.setCreatedBy("System");
			entity.setCreatedAt(new Date());
			entity.setInfo(algorithmDTO.getInfo());

			Users user = usersLogic.getUsers(4);
			entity.setUsers(user);

			VideoType videoType = videoTypeLogic.getVideoTypeByCode(Constantes.ANOMALOUS);
			entity.setVideoType(videoType);
			
			VideoStatus videoStatus = statusLogic.getVideoStatusByCode(Constantes.NEW);
			entity.setVideoStatus(videoStatus);

			// Save
			videoTransactionLogic.saveVideoTransaction(entity);

			log.debug("save saveVideoTransaction successful");
		} catch (Exception e) {
			log.error("VAS VideoAnalysisLogicREST saveVideoTransaction failed", e);
			throw e;
		}
	}
	
	public void saveVideoTransactionTrc(AlgorithmDTO algorithmDTO) throws Exception {
		log.info("VAS VideoAnalysisLogicREST saveVideoTransactionTrc");

		try {

			VideoTransaction entity = new VideoTransaction();

			entity.setVideoTransactionId(algorithmDTO.getVideoTransactionId().trim());
			entity.setDescription(algorithmDTO.getVideoFile());
			entity.setLenght(algorithmDTO.getLenght());
			entity.setAnalyticData("PosX: " + algorithmDTO.getPosXParam() 
				+" PosY: " + algorithmDTO.getPosYParam()
				+" PosX2: " + algorithmDTO.getPosX2Param() 
				+" PosY2: " + algorithmDTO.getPosY2Param());
			entity.setUrl(algorithmDTO.getUrl());
			entity.setCreatedBy(String.valueOf(FacesUtils.getfromSession(Constantes.SESSION_USER_LOGIN)));
			entity.setCreatedAt(new Date());
			entity.setInfo(algorithmDTO.getInfo());

			Users user = usersLogic
					.getUsers(Integer.valueOf(FacesUtils.getfromSession(Constantes.SESSION_USER_ID).toString()));
			entity.setUsers(user);

			VideoType videoType = videoTypeLogic.getVideoTypeByCode(Constantes.TRACKER);
			entity.setVideoType(videoType);
			
			VideoStatus videoStatus = statusLogic.getVideoStatusByCode(Constantes.NEW);
			entity.setVideoStatus(videoStatus);

			// Save
			videoTransactionLogic.saveVideoTransaction(entity);

			log.debug("save saveVideoTransactionTrc successful");
		} catch (Exception e) {
			log.error("VAS VideoAnalysisLogicREST saveVideoTransactionTrc failed", e);
			throw e;
		}
	}
	
	//TEST
	public void saveVideoTransactionTrcTest(AlgorithmDTO algorithmDTO) throws Exception {
		log.info("VAS VideoAnalysisLogicREST saveVideoTransactionTrc Test");

		try {

			VideoTransaction entity = new VideoTransaction();

			entity.setVideoTransactionId(algorithmDTO.getVideoTransactionId().trim());
			entity.setDescription(algorithmDTO.getVideoFile());
			entity.setLenght(algorithmDTO.getLenght());
			entity.setAnalyticData("PosX: " + algorithmDTO.getPosXParam() 
				+" PosY: " + algorithmDTO.getPosYParam()
				+" PosX2: " + algorithmDTO.getPosX2Param() 
				+" PosY2: " + algorithmDTO.getPosY2Param());
			entity.setUrl(algorithmDTO.getUrl());
			entity.setCreatedBy("System");
			entity.setCreatedAt(new Date());
			entity.setInfo(algorithmDTO.getInfo());

			Users user = usersLogic.getUsers(4);
			entity.setUsers(user);

			VideoType videoType = videoTypeLogic.getVideoTypeByCode(Constantes.TRACKER);
			entity.setVideoType(videoType);
			
			VideoStatus videoStatus = statusLogic.getVideoStatusByCode(Constantes.NEW);
			entity.setVideoStatus(videoStatus);

			// Save
			videoTransactionLogic.saveVideoTransaction(entity);

			log.debug("save saveVideoTransactionTrc successful");
		} catch (Exception e) {
			log.error("VAS VideoAnalysisLogicREST saveVideoTransactionTrc failed", e);
			throw e;
		}
	}
	
	//TEST------------------------------------------------------------------------
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String analyze_anomalousAlgFromTempLocationRestTest(String videoFileTemp, String videoLenght,
			String initTimeParam, String finalTimeParam, String info) throws Exception {
		log.info("1.VAS VideoAnalysisLogicREST analyze_anomalousAlgFromTempLocationRestTest TEST---------------------------------------------");

		String videoFile = null;
		String pictureFile = null;
		String videoFileGenerated = null;
		AlgorithmDTO algorithmDTO = null;
		try {

			log.info("videoFileTemp: " + videoFileTemp);
			log.info("videoSize: " + videoLenght);
			log.info("initTimeParam: " + initTimeParam);
			log.info("finalTimeParam: " + finalTimeParam);

			// Generate VideoUniqueId+format
			videoFileGenerated = this.generateVideoFileId();
			videoFile = videoFileGenerated+ "." + this.getVideo_format();
			// Generate pictureFileNme
			pictureFile = videoFileGenerated+".png";

			// TRACE
			//systemLogRestLogic.save_systemLogRest("VAS", "1. INIT PROCESS ANOMALOUS ALG TEST- "+videoFile);

			// PROCESS VIDEO ANALYTICS ALG BY REST--------------------------------------------------------

			// CHECK ALG PROCESSING STATUS
			log.info("VAS checkAnlAlgAvailable TEST");
			Boolean algAnlStatus = this.monitorLogicRest.checkAnlAlgAvailable();
			log.info("VAS checkAnlAlgAvailable status TEST:" +algAnlStatus);
			
			//TRACE
			//systemLogRestLogic.save_systemLogRest("VAS", "2 checkAnlAlgAvailable TEST - "+algAnlStatus.toString());
			
			
			if (algAnlStatus.booleanValue() == true) {
				log.info("VAS algAnlStatus TRUE");
				// SET alg DATA				
				algorithmDTO = this.setAlgRestData(
						this.securityLogic.generateRandomString(),
						"4",
						"julyangordon@gmail.com",
						videoFileTemp, videoFile,
						pictureFile,
						this.getLocalTemp_video_folder(), 
						this.getOutput_alg_anl_video_folder(),
						this.getAlg_anl_output_server()+videoFile,
						videoLenght, info, initTimeParam, finalTimeParam,
						"", "", "", "");

				// 2. SAVE VIDEO_TRANSACTION TO DB--------------------------------------------------------------
				log.info("VideoAnalysisLogic saveVideoTransaction TEST: "+algorithmDTO.getVideoTransactionId());
				this.saveVideoTransactionAnlRestTest(algorithmDTO);
				
				//TRACE
				//systemLogRestLogic.save_systemLogRest("VAS", "3. saveVideoTransactionAnlRestTest TEST - "+algorithmDTO.getVideoTransactionId());
				
				// QUEUE VIDEO TO SQS
				this.queueLogic.sendMessageAnlQueue(algorithmDTO);
				//trace
				//systemLogRestLogic.save_systemLogRest("VAS", "4. sendMessageAnlQueue TEST "+algorithmDTO.getVideoTransactionId().trim());
				
				//NOTIFY MONITOR SERVER
				//monitorLogicRest.processVideoWithAnomalousAlgRest();
				
				
				log.debug("save Video successful");

			} 

		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error ejecutando VideoAnalisis");
			log.error("VideoAnalysisLogic uploadVideoToFolder failed", e);
			throw e;
		}
		return videoFile;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String analyze_trackerAlgFromTempLocationRestTEST(String videoFileTemp, String videoSize, String posXParam,
			String posYParam, String posX2Param, String posY2Param, String info) throws Exception {
		log.info("2. VideoAnalysisLogic analyze_trackerAlgFromTempLocationRestTEST-TEST-----------------------------");

		String videoFile = null;
		AlgorithmDTO algorithmDTO = null;
		
		try {
			// 1.Set local name
			log.info("videoFileTemp: " + videoFileTemp);
			log.info("videoSize: " + videoSize);

			log.info("posXParam: " + posXParam);
			log.info("posYParam: " + posYParam);
			log.info("posX2Param: " + posX2Param);
			log.info("posY2Param: " + posY2Param);

			// Generate VideoUniqueId
			videoFile = this.generateVideoFileId()+ "." + this.getVideo_format();
			//TRACE
			systemLogRestLogic.save_systemLogRest("VAS", "1. INIT PROCESS TRACKER ALG TEST - "+videoFile);

			// PROCESS VIDEO ANALYTICS ALG BY REST--------------------------------------------------------

			// CHECK ALG PROCESSING STATUS
			Boolean algStatus = this.monitorLogicRest.checkTrcAlgAvailable();
			log.info("VAS checkTrcAlgAvailable status:" +algStatus);
			
			//TRACE
			systemLogRestLogic.save_systemLogRest("VAS", "2. checkTrcAlgAvailable TEST- "+algStatus.toString());
	
			// PROCESS VIDEO ANALYTICS ALG
			
			if (algStatus.booleanValue() == true) {
				log.info("VAS algAnlStatus TRUE");
				// SET alg DATA
				
				Integer width = (Integer.valueOf(posX2Param) - Integer.valueOf(posXParam) +1);
				//(x2 - x1) +1
				Integer hight = (Integer.valueOf(posY2Param) - Integer.valueOf(posYParam) +1);
				//(y2 -y1) +1
				
				algorithmDTO = this.setAlgRestData(
						//VIDEO TRANSACTION ID
						this.securityLogic.generateRandomString(),
						//USER ID
						"4",
						//USER EMAIL
						"julyangordon@gmail.com",
						//VIDEO FILETEMP
						videoFileTemp,
						//VIDEO FILE
						videoFile,
						//PICTURE FILE
						"",
						//TEMP VIDEO FOLDER
						this.getLocalTemp_video_folder(), 
						//OUTPUT VIDEO FOLDER
						this.getOutput_alg_trc_video_folder(),
						//URL
						this.getAlg_trc_output_server()+videoFile,
						//LENGHT
						videoSize, 
						//INFO
						info, 
						//ANL ALG PARAMS
						"", "",
						//TRC PARAMS
						posXParam, posYParam, 
						//X2 width
						width.toString(), 
						//Y2 hight
						hight.toString());

				// 2. SAVE VIDEO_TRANSACTION TO DB TEST--------------------------------------------------------------
				log.info("VideoAnalysisLogic saveVideoTransaction: "+algorithmDTO.getVideoTransactionId());
				this.saveVideoTransactionTrcTest(algorithmDTO);
				//TRACE
				systemLogRestLogic.save_systemLogRest("VAS", "3. saveVideoTransactionTrc TEST- "+algorithmDTO.getVideoTransactionId());
				
				// QUEUE VIDEO TO SQS
				this.queueLogic.sendMessageTrcQueue(algorithmDTO);
				//TRACE
				systemLogRestLogic.save_systemLogRest("VAS", "4. sendMessageTrcQueue TEST- "+algorithmDTO.getVideoTransactionId().trim());
			
				//NOTIFY MONITOR SERVER
				monitorLogicRest.processVideoWithTrackerAlgRest();

			} 
	
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error ejecutando VideoAnalisis");
			log.error("VideoAnalysisLogic analyze_trackerAlgFromTempLocationREST failed", e);
			//TRACE
			systemLogRestLogic.save_systemLogRest("VAS", " analyze_trackerAlgFromTempLocationREST ERROR: "+ e.getMessage());
			throw e;
		}
		return videoFile;
	}
	
	//-------------------------------------------------------------------------

	public void monitor_searchuploadedVideoRestService(String videoId) {
		log.info("2. monitor_searchuploadedVideoRestService");
		try {
			if (videoId != null) {

				String url = "http://localhost:8080/vas_monitor/controller/video/search/";
				// String url = Constantes.MONITOR_REST_SERVICE_UPLOADED_VIDEO;

				log.info("url: " + url);
				RestTemplate restTemplate = new RestTemplate();
				String status = restTemplate.postForObject(url, videoId, String.class);

				log.info("status: " + status);

				if (status.equals("LOCATED") == false) {
					FacesUtils.addErrorMessage(
							"Se encontró un error ubicando el video enviado, por favor cargue el video nuevamente");
					log.error("Se encontró un error ubicando el video enviado: " + videoId);
				}
			}

		} catch (Exception e) {
			log.error("VideoAnalysisLogic monitor_searchuploadedVideoRestService failed", e);
		}

	}

	public String getWeb_server() {
		try {
			this.web_server = systemParameterLogic.getParamByCodeTexValue(Constantes.WEB_SERVER);
			log.info("getWeb_server: " + web_server);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return web_server;
	}

	public void setWeb_server(String web_server) {
		this.web_server = web_server;
	}

	public String getVideo_format() {
		try {
			this.video_format = systemParameterLogic.getParamByCodeTexValue(Constantes.VIDEO_FORMAT);
			log.info("getVideo_format: " + video_format);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return video_format;
	}

	public void setVideo_format(String video_format) {
		this.video_format = video_format;
	}

	public String getInput_video_folder() {
		try {
			this.input_video_folder = systemParameterLogic.getParamByCodeTexValue(Constantes.INPUT_VIDEO_FOLDER);
			log.info("getInput_video_folder: " + input_video_folder);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return input_video_folder;
	}

	public void setInput_video_folder(String input_video_folder) {
		this.input_video_folder = input_video_folder;
	}

	public String getTemp_video_folder() throws Exception {
		try {
			this.temp_video_folder = systemParameterLogic.getParamByCodeTexValue(Constantes.TEMP_VIDEO_FOLDER);
			log.info("temp_video_folder: " + temp_video_folder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp_video_folder;
	}

	public void setTemp_video_folder(String temp_video_folder) {
		this.temp_video_folder = temp_video_folder;
	}

	public String getLocalTemp_video_folder() throws Exception {

		String localTempVideoFolder = "";
		try {

			localTempVideoFolder = systemParameterLogic.getParamByCodeTexValue(Constantes.TEMP_VIDEO_FOLDER);
			log.info("getLocalTemp_video_folder: " + localTempVideoFolder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return localTempVideoFolder;
	}

	public String getWeb_server_temp() throws Exception {
		try {

			this.web_server_temp = getWeb_server()
					+ systemParameterLogic.getParamByCodeTexValue(Constantes.WEB_SERVER_TEMP);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return web_server_temp;
	}

	public void setWeb_server_temp(String web_server_temp) {
		this.web_server_temp = web_server_temp;
	}

	public String getAlg_anl_output_server() {
		try {

			this.alg_anl_output_server = getWeb_server()
					+ systemParameterLogic.getParamByCodeTexValue(Constantes.ALG_ANL_OUTPUT_SERVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alg_anl_output_server;
	}

	public void setAlg_anl_output_server(String alg_anl_output_server) {
		this.alg_anl_output_server = alg_anl_output_server;
	}

	public String getAlg_trc_output_server() {
		try {
			this.alg_trc_output_server = getWeb_server()
					+ systemParameterLogic.getParamByCodeTexValue(Constantes.ALG_TRC_OUTPUT_SERVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alg_trc_output_server;
	}

	public void setAlg_trc_output_server(String alg_trc_output_server) {
		this.alg_trc_output_server = alg_trc_output_server;
	}

	public String getOutput_alg_anl_video_folder() {
		try {
			this.output_alg_anl_video_folder = systemParameterLogic
					.getParamByCodeTexValue(Constantes.OUTPUT_ALG_ANL_VIDEO_FOLDER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output_alg_anl_video_folder;
	}

	public void setOutput_alg_anl_video_folder(String output_alg_anl_video_folder) {
		this.output_alg_anl_video_folder = output_alg_anl_video_folder;
	}

	public String getOutput_alg_trc_video_folder() {
		try {
			this.output_alg_trc_video_folder = systemParameterLogic
					.getParamByCodeTexValue(Constantes.OUTPUT_ALG_TRC_VIDEO_FOLDER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output_alg_trc_video_folder;
	}

	public void setOutput_alg_trc_video_folder(String output_alg_trc_video_folder) {
		this.output_alg_trc_video_folder = output_alg_trc_video_folder;
	}
	


}
