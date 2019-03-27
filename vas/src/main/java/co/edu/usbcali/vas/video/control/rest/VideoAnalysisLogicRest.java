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
import co.edu.usbcali.vas.model.Video;
import co.edu.usbcali.vas.model.VideoType;
import co.edu.usbcali.vas.model.control.ISystemParameterLogic;
import co.edu.usbcali.vas.model.control.ISystemVideoLogLogic;
import co.edu.usbcali.vas.model.control.IUsersLogic;
import co.edu.usbcali.vas.model.control.IVideoLogic;
import co.edu.usbcali.vas.model.control.IVideoTypeLogic;
import co.edu.usbcali.vas.model.dto.AlgorithmDTO;
import co.edu.usbcali.vas.queue.IQueueLogic;
import co.edu.usbcali.vas.utilities.Constantes;
import co.edu.usbcali.vas.utilities.FacesUtils;
import co.edu.usbcali.vas.utilities.Fechas;

@Scope("singleton")
@Service("VideoAnalysisLogicRest")
public class VideoAnalysisLogicRest implements IVideoAnalysisLogicRest {
	private static final Logger log = LoggerFactory.getLogger(VideoAnalysisLogicRest.class);

	@Autowired
	private IVideoLogic videoLogic;

	@Autowired
	private IVideoTypeLogic videoTypeLogic;

	@Autowired
	private IUsersLogic usersLogic;

	@Autowired
	private ISystemVideoLogLogic systemVideoLogLogic;

	@Autowired
	private ISystemParameterLogic systemParameterLogic;

	@Autowired
	private IMonitorLogicRest monitorLogicRest;

	@Autowired
	private IQueueLogic queueLogic;

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

	private String external_program_alg_anl_location = "";
	private String external_program_alg_trc_location = "";

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String analyze_anomalousAlgFromTempLocationREST(String videoFileTemp, String videoLenght,
			String initTimeParam, String finalTimeParam, String info) throws Exception {
		log.info("1.VAS VideoAnalysisLogicREST analyze_anomalousAlgFromTempLocationREST---------------------------------");

		String videoFile = null;

		try {

			log.info("videoFileTemp: " + videoFileTemp);
			log.info("videoSize: " + videoLenght);
			log.info("initTimeParam: " + initTimeParam);
			log.info("finalTimeParam: " + finalTimeParam);

			// Generate VideoUniqueId
			videoFile = this.generateVideoFileId();

			// Trace log
			systemVideoLogLogic.save_systemVideoLog("ANOMALOUS ALG - INIT PROCESS", "VIDEO_ID: " + videoFile);

			// SET alg DATA
			AlgorithmDTO algorithmDTO = this.setAlgRestData(
					FacesUtils.getfromSession(Constantes.SESSION_USER_ID).toString(), videoFileTemp, videoFile,
					this.getLocalTemp_video_folder(), this.getOutput_alg_anl_video_folder(),
					this.getExternal_program_alg_anl_location(), videoLenght, info, initTimeParam, finalTimeParam, "",
					"", "", "");

			// PROCESS VIDEO ANALYTICS ALG BY REST--------------------------------------------------------

			// CHECK ALG PROCESSING STATUS
			Boolean algAnlStatus = this.monitorLogicRest.checkAnlAlgAvailable();

			if (algAnlStatus.booleanValue() == true) {

				// QUEUE VIDEO TO SQS
				this.queueLogic.sendMessage(algorithmDTO);

				// TODO Este trace debe ser creado desde el monitor para cerrar el ciclo
				systemVideoLogLogic.save_systemVideoLog("ANOMALOUS ALG - FINISH PROCESS", "VIDEO_ID: " + videoFile);

				// 2. SAVE VIDEO TO DB--------------------------------------------------------------
				//TODO el video debe ser actualizado por SERVER_MONITOR
				this.saveVideo(algorithmDTO);
				
				log.debug("save Video successful");

			} else {
				// QUEUE VIDEO
				this.queueLogic.sendMessage(algorithmDTO);
				FacesUtils.addWarningMessage("Algoritmo ANOMALOUS no está listo para procesar");
			}

		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error ejecutando VideoAnalisis");
			log.error("VideoAnalysisLogic uploadVideoToFolder failed", e);
			throw e;
		}
		return videoFile;
	}

	public String generateVideoFileId() {
		String videoFile = null;
		String timeStamp = Fechas.dateToStr(Fechas.actualDateWithTimeStamp(), Constantes.FORMATO_FECHA_VIDEO);
		videoFile = timeStamp + "." + this.getVideo_format();
		log.info("videoFile: " + videoFile);
		return videoFile;
	}

	public AlgorithmDTO setAlgRestData(String userId, String videoFileTemp, String videoFile, String tempVideoFolder,
			String outputVideoFolder, String externalProgramLocation, String initTimeParam, String finalTimeParam,
			String posXParam, String posYParam, String posX2Param, String posY2Param, String lenght, String info) {

		AlgorithmDTO algDTO = new AlgorithmDTO();
		algDTO.setUserId((userId != null) ? userId : null);
		algDTO.setVideoFileTemp((videoFileTemp != null) ? videoFileTemp : null);
		algDTO.setVideoFile((videoFile != null) ? videoFile : null);
		algDTO.setTempVideoFolder((tempVideoFolder != null) ? tempVideoFolder : null);
		algDTO.setOutputVideoFolder((outputVideoFolder != null) ? outputVideoFolder : null);
		algDTO.setExternalProgramLocation((externalProgramLocation != null) ? externalProgramLocation : null);
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

	public void saveVideo(AlgorithmDTO algorithmDTO) throws Exception {
		log.info("VideoAnalysisLogicREST saveVideo");

		try {

			Video entity = new Video();

			entity.setDescription(algorithmDTO.getVideoFile());
			entity.setLenght(algorithmDTO.getLenght());
			entity.setAnalyticData("InitTime: " + algorithmDTO.getInitTimeParam() + "Final Time: " + algorithmDTO.getFinalTimeParam());
			entity.setUrl(this.getAlg_anl_output_server() + algorithmDTO.getVideoFile());
			entity.setCreatedBy(String.valueOf(FacesUtils.getfromSession(Constantes.SESSION_USER_LOGIN)));
			entity.setCreatedAt(new Date());
			entity.setInfo(algorithmDTO.getInfo());

			Users user = usersLogic
					.getUsers(Integer.valueOf(FacesUtils.getfromSession(Constantes.SESSION_USER_ID).toString()));
			entity.setUsers(user);

			VideoType videoType = videoTypeLogic.getVideoTypeByCode(Constantes.ANOMALOUS);
			entity.setVideoType(videoType);

			// Save
			videoLogic.saveVideo(entity);

			log.debug("save Video successful");
		} catch (Exception e) {
			log.error("VideoAnalysisLogicREST saveVideo failed", e);
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String analyze_trackerAlgFromTempLocationREST(String videoFileTemp, String videoSize, String posXParam,
			String posYParam, String posX2Param, String posY2Param) throws Exception {
		log.info("2. VideoAnalysisLogic analyze_trackerAlgFromTempLocationREST------------------------------");

		String videoFile = null;
		try {
			// 1.Set local name
			log.info("videoFileTemp: " + videoFileTemp);
			log.info("videoSize: " + videoSize);
			log.info("posXParam: " + posXParam);
			log.info("posYParam: " + posYParam);
			log.info("posX2Param: " + posX2Param);
			log.info("posY2Param: " + posY2Param);

			// Generate VideoId
			String timeStamp = Fechas.dateToStr(Fechas.actualDateWithTimeStamp(), Constantes.FORMATO_FECHA_VIDEO);
			videoFile = timeStamp + "." + this.getVideo_format();
			log.info("videoFile: " + videoFile);

			// Trace log
			systemVideoLogLogic.save_systemVideoLog("ANOMALOUS ALG - INIT PROCESS", "VIDEO_ID: " + videoFile);

			// Time
			Long initTransferTime = System.currentTimeMillis();

			// PROCESS VIDEO ANALYTICS ALG
			// TODO
			/*
			 * this.monitorLogicRest.processVideoWithTrackerAlg(videoFileTemp, videoFile,
			 * this.getTemp_video_folder(), this.getOutput_alg_trc_video_folder(),
			 * this.getExternal_program_alg_trc_location(), posXParam, posYParam,
			 * posX2Param, posY2Param);
			 */
			Long finalTransferTime = System.currentTimeMillis() - initTransferTime;
			// Trace log
			systemVideoLogLogic.save_systemVideoLog("ANOMALOUS ALG - FINISH PROCESS",
					"VIDEO_ID: " + videoFile + " TRANSFER_TIME: " + finalTransferTime.toString());

			// 2. SAVE VIDEO TO
			// DB--------------------------------------------------------------
			Video entity = new Video();

			entity.setDescription(videoFile);
			entity.setLenght(videoSize);
			entity.setAnalyticData("posXParam: " + posXParam + " posYParam: " + posYParam + "posX2Param:" + posX2Param
					+ "posY2Param: " + posY2Param);
			entity.setTransferTime(finalTransferTime);
			entity.setUrl(this.getAlg_trc_output_server() + videoFile);
			entity.setCreatedBy(String.valueOf(FacesUtils.getfromSession(Constantes.SESSION_USER_LOGIN)));
			entity.setCreatedAt(new Date());

			Users user = usersLogic
					.getUsers(Integer.valueOf(FacesUtils.getfromSession(Constantes.SESSION_USER_ID).toString()));
			entity.setUsers(user);
			// Save
			videoLogic.saveVideo(entity);

			log.debug("save Video successful");

		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error ejecutando VideoAnalisis");
			log.error("VideoAnalysisLogic uploadVideoToFolder failed", e);
			throw e;
		}
		return videoFile;
	}

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

	public String getExternal_program_alg_anl_location() {
		try {
			this.external_program_alg_anl_location = systemParameterLogic
					.getParamByCodeTexValue(Constantes.EXTERNAL_PROGRAM_ALG_ANL_LOCATION);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return external_program_alg_anl_location;
	}

	public void setExternal_program_alg_anl_location(String external_program_alg_anl_location) {
		this.external_program_alg_anl_location = external_program_alg_anl_location;
	}

	public String getExternal_program_alg_trc_location() {
		try {
			this.external_program_alg_trc_location = systemParameterLogic
					.getParamByCodeTexValue(Constantes.EXTERNAL_PROGRAM_ALG_TRC_LOCATION);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return external_program_alg_trc_location;
	}

	public void setExternal_program_alg_trc_location(String external_program_alg_trc_location) {
		this.external_program_alg_trc_location = external_program_alg_trc_location;
	}

}
