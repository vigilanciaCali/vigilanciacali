package co.edu.usbcali.vas.video.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import co.edu.usbcali.vas.ftp.IFtpController;
import co.edu.usbcali.vas.model.Users;
import co.edu.usbcali.vas.model.Video;
import co.edu.usbcali.vas.model.VideoTemp;
import co.edu.usbcali.vas.model.control.ISystemParameterLogic;
import co.edu.usbcali.vas.model.control.ISystemVideoLogLogic;
import co.edu.usbcali.vas.model.control.IUsersLogic;
import co.edu.usbcali.vas.model.control.IVideoLogic;
import co.edu.usbcali.vas.model.control.IVideoTempLogic;
import co.edu.usbcali.vas.model.dto.AlgorithmDTO;
import co.edu.usbcali.vas.sftp.ISftpLogic;
import co.edu.usbcali.vas.utilities.Constantes;
import co.edu.usbcali.vas.utilities.FacesUtils;
import co.edu.usbcali.vas.utilities.Fechas;


@Scope("singleton")
@Service("VideoAnalysisLogic")
public class VideoAnalysisLogic implements IVideoAnalysisLogic {
    private static final Logger log = LoggerFactory.getLogger(VideoAnalysisLogic.class);


    @Autowired
	private IFtpController ftpController;
    
    @Autowired
	private IVideoLogic videoLogic;
    
    @Autowired
	private IVideoTempLogic videoTempLogic;
    
    @Autowired
   	private IUsersLogic usersLogic;
    
    @Autowired
   	private ISystemVideoLogLogic systemVideoLogLogic;
    
    @Autowired
   	private ISystemParameterLogic systemParameterLogic;
    
    @Autowired
   	private IMonitorLogic monitorLogic;
    
    @Autowired
   	private ISftpLogic sftpLogic;
    
    @Autowired
   	private IFolderMonitorLogic folderMonitorLogic;
    
    //Remote WebServer
	private String web_server = "";
	private String web_server_temp = "";
	//Server output location
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
    public String saveVideoToTempLocation(String contentType, String fileName, Long size, InputStream inputFile, String type) throws Exception {
        log.info("0. VAS VideoAnalysisLogic saveVideoToTempLocation-------------------------------------");

		try {
			
			log.info("contentType: "+contentType);
			log.info("fileName: " +fileName);
			log.info("size: "+size);
			log.info("inputFile:" +inputFile);
			
			//1. Generate VideoId
			/*String timeStamp = Fechas.dateToStr(Fechas.actualDateWithTimeStamp(), Constantes.FORMATO_FECHA_VIDEO);
			videoFile = "TEMP_" + timeStamp + "."+ this.getVideo_format();
			log.info("videoFile: " + videoFile);*/
			
			//Trace log
			systemVideoLogLogic.save_systemVideoLog("TEMP VIDEO - INIT FILE COPY", "VIDEO_ID: " + fileName);
			
			//Copy
			Long initTransferTime = System.currentTimeMillis();
			
			//2. SAVE VIDEO TO TEMP LOCATION------------------------------------------------------
			//this.saveFileToTempLocation(this.getTemp_video_folder(), inputFile, fileName);
			
			//2. SAVE VIDEO TO SFTP TEMP LOCATION--------------------------------------------------
			sftpLogic.uploadToSftp(this.getTemp_video_folder(), inputFile, fileName);
	
			log.info("uploadToSftp guardar ubicacion temporal");
			
			//---------------------
			
			Long finalTransferTime = System.currentTimeMillis() - initTransferTime;
			//Trace log
			systemVideoLogLogic.save_systemVideoLog("TEMP VIDEO - FINISH FILE COPY", "VIDEO_ID: " + fileName + " TRANSFER_TIME: " + finalTransferTime.toString());
			
			//3. SAVE VIDEOTEMP TO DB--------------------------------------------------------------
			VideoTemp entity = new VideoTemp();
			
			entity.setDescription(fileName);
			entity.setLenght(String.valueOf(size).trim());
			entity.setFormat(contentType);
			entity.setUrl(this.getWeb_server_temp() + fileName.trim());
			entity.setSrc(this.getTemp_video_folder() + fileName.trim());
			entity.setCreatedBy(String.valueOf(FacesUtils.getfromSession(Constantes.SESSION_USER_LOGIN)));
			entity.setCreatedAt(new Date());
			entity.setType(type);
			
			Users user = usersLogic.getUsers(Integer.valueOf(FacesUtils.getfromSession(Constantes.SESSION_USER_ID).toString()));
			entity.setUsers(user);
			
			log.info("3 VideoAnalysisLogic saveVideo to DB----------------------------------");
	
			//Save
			videoTempLogic.saveVideoTemp(entity);

			log.debug("save Video successful");
			
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error saveVideoToTempLocation");
			log.error("VideoAnalysisLogic saveVideoToTempLocation failed", e);
			throw e;
		}
		return fileName;
    }
	
	@Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String analyze_anomalousAlgFromTempLocation(String videoFileTemp, String videoSize, String initTimeParam, String finalTimeParam, String info) throws Exception {
        log.info("1. VAS VideoAnalysisLogic analyze ANOMALOUS AlgFromTempLocation---------------------------------");
        
        String videoFile = null;
        
		try {
			
			//1.Set local name
			log.info("videoFileTemp: " + videoFileTemp);
			log.info("videoSize: " + videoSize);
			log.info("initTimeParam: " + initTimeParam);
			log.info("finalTimeParam: " + finalTimeParam);
			log.info("info: " + info);
				
				//Generate VideoId
				String timeStamp = Fechas.dateToStr(Fechas.actualDateWithTimeStamp(), Constantes.FORMATO_FECHA_VIDEO);
				
				videoFile = timeStamp + "." +  this.getVideo_format();;
				log.info("videoFile: " + videoFile);
								
				//Trace log
				systemVideoLogLogic.save_systemVideoLog("ANOMALOUS ALG - INIT PROCESS", "VIDEO_ID: " + videoFile);
				
				//Time
				Long initProcessingTime = System.currentTimeMillis();
				
				//PROCESS VIDEO ANALYTICS ALG TO LOCAL
				this.monitorLogic.processVideoWithAnomalousEventsAlg(
						videoFileTemp, videoFile, this.getTemp_video_folder(), this.getOutput_alg_anl_video_folder(),
						this.getExternal_program_alg_anl_location(), initTimeParam, finalTimeParam);
					
					Long finalProcessingTime = System.currentTimeMillis() - initProcessingTime;
					//Trace log
					systemVideoLogLogic.save_systemVideoLog("ANOMALOUS ALG - FINISH PROCESS", "VIDEO_ID: " + videoFile + " PROCESSING_TIME: " + finalProcessingTime.toString());
					
					//2. SAVE VIDEO TO DB--------------------------------------------------------------
					Video entity = new Video();
					
					entity.setDescription(videoFile);
					entity.setLenght(videoSize);
					entity.setType(Constantes.ALG_TYPE_ANOMALOUS);
					entity.setInfo(info);
					entity.setAnalyticData("InitTime: "+ initTimeParam +"Final Time: "+finalTimeParam);
					entity.setTransferTime(finalProcessingTime);
					entity.setUrl(this.getAlg_anl_output_server() + videoFile);
					entity.setCreatedBy(String.valueOf(FacesUtils.getfromSession(Constantes.SESSION_USER_LOGIN)));
					entity.setCreatedAt(new Date());
					
					Users user = usersLogic.getUsers(Integer.valueOf(FacesUtils.getfromSession(Constantes.SESSION_USER_ID).toString()));
					entity.setUsers(user);
					
					log.info("1.5 VideoAnalysisLogic saveVideo to DB----------------------------------");
			
					//Save
					videoLogic.saveVideo(entity);

					log.debug("save Video successful");
	
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error ejecutando VideoAnalisis");
			log.error("VideoAnalysisLogic uploadVideoToFolder failed", e);
			throw e;
		}
		return videoFile;
    }
	
	    
	public AlgorithmDTO setAlgRestData(String videoFileTemp, String videoFile, String tempVideoFolder, String outputVideoFolder,
			String externalProgramLocation, String initTimeParam, String finalTimeParam, 
			String posXParam, String posYParam, String posX2Param, String posY2Param) {
		
		AlgorithmDTO algDTO = new AlgorithmDTO();
		algDTO.setVideoFileTemp((videoFileTemp != null)? videoFileTemp : null);
		algDTO.setVideoFile((videoFile != null)? videoFile : null);
		algDTO.setTempVideoFolder((tempVideoFolder != null)? tempVideoFolder : null);
		algDTO.setOutputVideoFolder((outputVideoFolder != null)? outputVideoFolder : null);
		algDTO.setExternalProgramLocation((externalProgramLocation != null)? externalProgramLocation : null);
		algDTO.setInitTimeParam((initTimeParam != null)? initTimeParam : null);
		algDTO.setFinalTimeParam((finalTimeParam != null)? finalTimeParam : null);
		//TRACKER
		algDTO.setPosXParam((posXParam != null)? posXParam : null);
		algDTO.setPosYParam((posYParam != null)? posYParam : null);
		algDTO.setPosX2Param((posX2Param != null)? posX2Param : null);
		algDTO.setPosY2Param((posY2Param != null)? posY2Param : null);
		
		return algDTO;
		
	}
	
	@Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String analyze_trackerAlgFromTempLocation(String videoFileTemp, String videoSize, 
    		String posXParam, String posYParam, String posX2Param, String posY2Param, String info) throws Exception {
        log.info("2. VideoAnalysisLogic analyze TRACKER AlgFromTempLocation------------------------------");
        
        String videoFile = null;
		try {
			
			//1.Set local name
			log.info("videoFileTemp: " + videoFileTemp);
			log.info("videoSize: " + videoSize);
			log.info("posXParam: " + posXParam);
			log.info("posYParam: " + posYParam);
			log.info("posX2Param: " + posX2Param);
			log.info("posY2Param: " + posY2Param);
			log.info("info: " + info);
				
				//Generate VideoId
				String timeStamp = Fechas.dateToStr(Fechas.actualDateWithTimeStamp(), Constantes.FORMATO_FECHA_VIDEO);

				videoFile = timeStamp + "." + this.getVideo_format();
				log.info("videoFile: " + videoFile);
				
				//Trace log
				systemVideoLogLogic.save_systemVideoLog("TRACKER ALG - INIT PROCESS", "VIDEO_ID: " + videoFile);
				
				//Time
				Long initTransferTime = System.currentTimeMillis();
				
				//PROCESS VIDEO ANALYTICS ALG
				this.monitorLogic.processVideoWithTrackerAlg(videoFileTemp, videoFile, this.getTemp_video_folder(),
						this.getOutput_alg_trc_video_folder(), this.getExternal_program_alg_trc_location(), posXParam, posYParam, posX2Param, posY2Param);
				
				Long finalTransferTime = System.currentTimeMillis() - initTransferTime;
				//Trace log
				systemVideoLogLogic.save_systemVideoLog("TRACKER ALG - FINISH PROCESS", "VIDEO_ID: " + videoFile + " PROCESSING_TIME: " + finalTransferTime.toString());
				
				//2. SAVE VIDEO TO DB--------------------------------------------------------------
				Video entity = new Video();
				
				entity.setDescription(videoFile);
				entity.setLenght(videoSize);
				entity.setType(Constantes.ALG_TYPE_TRACKER);
				entity.setInfo(info);
				entity.setAnalyticData("posXParam: "+ posXParam +" posYParam: "+ posYParam + "posX2Param:" + posX2Param + "posY2Param: "+posY2Param);
				entity.setTransferTime(finalTransferTime);
				entity.setUrl(this.getAlg_trc_output_server() + videoFile);
				entity.setCreatedBy(String.valueOf(FacesUtils.getfromSession(Constantes.SESSION_USER_LOGIN)));
				entity.setCreatedAt(new Date());
				
				Users user = usersLogic.getUsers(Integer.valueOf(FacesUtils.getfromSession(Constantes.SESSION_USER_ID).toString()));
				entity.setUsers(user);
				//Save
				videoLogic.saveVideo(entity);

				log.debug("save Video successful");
	
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error ejecutando VideoAnalisis con Tracker");
			log.error("VideoAnalysisLogic analyze_trackerAlgFromTempLocation failed", e);
			throw e;
		}
		return videoFile;
    }
	
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void analyze_anomalousAlg(String contentType, String fileName, Long size, InputStream inputFile) throws Exception {
        log.info("1. VideoAnalysisLogic analyze_anomalousAlg");

		try {
			
			
			//1. Generate VideoId
			String timeStamp = Fechas.dateToStr(Fechas.actualDateWithTimeStamp(), Constantes.FORMATO_FECHA_VIDEO);
			String videoFile = timeStamp + "."+ this.getVideo_format();
			log.info("videoFile: " + videoFile);
			
			//1. Save video in local location
			byte[] buffer = new byte[inputFile.available()];
			inputFile.read(buffer);
			
			log.info("inputFile.read(buffer");
			
			//Trace log
			systemVideoLogLogic.save_systemVideoLog("ANOMALOUS ALG - INIT FILE COPY", "VIDEO_ID: " + videoFile);
			
			//Copy
			Long initTransferTime = System.currentTimeMillis();
			
			File targetFile = new File(this.getInput_video_folder() + videoFile);
			OutputStream outStream = new FileOutputStream(targetFile);
			outStream.write(buffer);
			outStream.close();
			
			log.info("outStream.write guardar ubicacion local");
			Long finalTransferTime = System.currentTimeMillis() - initTransferTime;
			//Trace log
			systemVideoLogLogic.save_systemVideoLog("ANOMALOUS ALG - FINISH FILE COPY", "VIDEO_ID: " + videoFile + " TRANSFER_TIME: " + finalTransferTime.toString());
			
			
			//Leer video desde ubicación TEMP
           /* File localFileTmp = new File("/home/gordon/www/html/temp/videotemp.mp4");
            //String firstRemoteFile = "Seguimiento.mp4";
            InputStream inputFileTmp = new FileInputStream(localFileTmp);*/
			
			//2. SAVE VIDEO TO DB--------------------------------------------------------------
			Video entity = new Video();
			
			entity.setDescription(videoFile);
			entity.setType(contentType);
			entity.setLenght(size.toString());
			entity.setAnalyticData("");
			entity.setUrl(this.getWeb_server() + this.getAlg_anl_output_server() + videoFile);
			//transfer
			entity.setTransferTime(finalTransferTime);
			entity.setCreatedBy(String.valueOf(FacesUtils.getfromSession(Constantes.SESSION_USER_LOGIN)));
			entity.setCreatedAt(new Date());
			
			Users user = usersLogic.getUsers(Integer.valueOf(FacesUtils.getfromSession(Constantes.SESSION_USER_ID).toString()));
			entity.setUsers(user);
			//Save
			videoLogic.saveVideo(entity);
			
			//3. CALL MONITOR REST Service-------------------------------------------------------------
			
			//monitor_keepalive();

			
			//this.monitor_searchuploadedVideoRestService(videoFile);
			//this.monitorLogic.processVideoWithAnomalousEventsAlg(videoFile, this.input_video_folder, this.getOutput_video_folder(), this.getAnomalous_external_program_location());
			

			log.debug("save Video successful");
			
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error ejecutando VideoAnalisis");
			log.error("VideoAnalysisLogic uploadVideoToFolder failed", e);
			throw e;
		}
    }
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void analyze_trackerAlg(String contentType, String fileName, Long size, InputStream inputFile) throws Exception {
        log.info("1. VideoAnalysisLogic analyze_trackerAlg");

		try {
			
			
			//1. Generate VideoId
			String timeStamp = Fechas.dateToStr(Fechas.actualDateWithTimeStamp(), Constantes.FORMATO_FECHA_VIDEO);
			String videoFile = timeStamp + "."+ this.getVideo_format();
			log.info("videoFile: " + videoFile);
			
			//1. Save video in local location
			byte[] buffer = new byte[inputFile.available()];
			inputFile.read(buffer);
			
			log.info("inputFile.read(buffer");
			
			//Trace log
			systemVideoLogLogic.save_systemVideoLog("INIT FILE COPY", "VIDEO_ID: " + videoFile);
			
			//Copy
			Long initTransferTime = System.currentTimeMillis();
			
			File targetFile = new File(this.getInput_video_folder() + videoFile);
			OutputStream outStream = new FileOutputStream(targetFile);
			outStream.write(buffer);
			outStream.close();
			
			log.info("outStream.write guardar ubicacion local");
			Long finalTransferTime = System.currentTimeMillis() - initTransferTime;
			//Trace log
			systemVideoLogLogic.save_systemVideoLog("FINISH FILE COPY", "VIDEO_ID: " + videoFile + " TRANSFER_TIME: " + finalTransferTime.toString());
			
			
			//Leer video desde ubicación TEMP
           /* File localFileTmp = new File("/home/gordon/www/html/temp/videotemp.mp4");
            //String firstRemoteFile = "Seguimiento.mp4";
            InputStream inputFileTmp = new FileInputStream(localFileTmp);*/
			
			//2. SAVE VIDEO TO DB--------------------------------------------------------------
			Video entity = new Video();
			
			entity.setDescription(videoFile);
			entity.setType(contentType);
			entity.setLenght(size.toString());
			entity.setAnalyticData("");
			entity.setUrl(this.getWeb_server() + this.getAlg_trc_output_server() + videoFile);
			//transfer
			entity.setTransferTime(finalTransferTime);
			entity.setCreatedBy(String.valueOf(FacesUtils.getfromSession(Constantes.SESSION_USER_LOGIN)));
			entity.setCreatedAt(new Date());
			
			Users user = usersLogic.getUsers(Integer.valueOf(FacesUtils.getfromSession(Constantes.SESSION_USER_ID).toString()));
			entity.setUsers(user);
			//Save
			videoLogic.saveVideo(entity);
			
			//3. CALL MONITOR REST Service-------------------------------------------------------------
			
			//monitor_keepalive();
			FacesUtils.addWarningMessage("El proceso de análisis ha iniciado!");
			FacesUtils.addWarningMessage("Espere un momento por favor...");
			
			//this.monitor_searchuploadedVideoRestService(videoFile);
			//this.monitorLogic.processVideoWithTrackerAlg(videoFile, this.input_video_folder, this.getOutput_video_folder());
			
			
			FacesUtils.addInfoMessage("El proceso de análisis Finalizado");
			

			log.debug("save Video successful");
			
		} catch (Exception e) {
			FacesUtils.addErrorMessage("Error ejecutando VideoAnalisis");
			log.error("VideoAnalysisLogic uploadVideoToFolder failed", e);
			throw e;
		}
    }
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void uploadVideoToFtp(String contentType, String fileName, Long size, InputStream inputFile) throws Exception {
        log.info("VideoAnalysisLogic uploadVideoToFtp");

		try {
			
			//VideoId
			String timeStamp = Fechas.dateToStr(Fechas.actualDateWithTimeStamp(), Constantes.FORMATO_FECHA_VIDEO);
			String videoFile = timeStamp + ".mp4";
			log.info("videoFile: " + videoFile);

			//UPLOAD TO FTP----------------------------------------------------------
			//Trace log
			systemVideoLogLogic.save_systemVideoLog("INIT FTP TRANSFER", "VIDEO_ID: "+"videoFile");
			//Upload
			Long initTransferTime = System.currentTimeMillis();
			//FTP
			ftpController.upload(videoFile, inputFile);
			Long finalTransferTime = System.currentTimeMillis() - initTransferTime;
			//Trace log
			systemVideoLogLogic.save_systemVideoLog("FINISH FTP TRANSFER", "VIDEO_ID: "+"videoFile"+" TRANSFER_TIME: "+finalTransferTime.toString());
			
			//SAVE VIDEO--------------------------------------------------------------
			Video entity = new Video();
			
			entity.setDescription(videoFile);
			entity.setType(contentType);
			entity.setLenght(size.toString());
			entity.setAnalyticData("");
			entity.setUrl(this.getWeb_server() + this.getAlg_anl_output_server() + videoFile);
			//transfer
			entity.setTransferTime(finalTransferTime);
			entity.setCreatedBy(String.valueOf(FacesUtils.getfromSession(Constantes.SESSION_USER_LOGIN)));
			entity.setCreatedAt(new Date());
			
			Users user = usersLogic.getUsers(Integer.valueOf(FacesUtils.getfromSession(Constantes.SESSION_USER_ID).toString()));
			entity.setUsers(user);
			//Save
			videoLogic.saveVideo(entity);
			
			//CALL MONITOR REST Service-------------------------------------------------------------
			monitor_keepalive();
			FacesUtils.addWarningMessage("El proceso de análisis ha iniciado!");
			FacesUtils.addWarningMessage("Espere un momento por favor...");
			monitor_searchuploadedVideoRestService(videoFile);
			
			/*if(response.eq) {
				FacesUtils.addInfoMessage("El proceso de análisis Finalizado");
			}else {
				FacesUtils.addErrorMessage("El proceso de análisis ha Fallado");
			}*/
			
			

			log.debug("save Video successful");
			
		} catch (Exception e) {
			log.error("save Video failed", e);
			throw e;
		}
    }
    
    public void monitor_searchuploadedVideoRestService(String videoId) {
    	log.info("2. monitor_searchuploadedVideoRestService");
    	try {
			if (videoId != null) {
				
				String url = "http://localhost:8080/vas_monitor/controller/video/search/";
				//String url = Constantes.MONITOR_REST_SERVICE_UPLOADED_VIDEO;
				
				log.info("url: "+url);
				RestTemplate restTemplate = new RestTemplate();
				String status = restTemplate.postForObject(url, videoId, String.class);
				
				log.info("status: "+status);
				
				if(status.equals("LOCATED") == false) {
					FacesUtils.addErrorMessage("Se encontró un error ubicando el video enviado, por favor cargue el video nuevamente");
					log.error("Se encontró un error ubicando el video enviado: "+videoId );
				}
			}

        } catch (Exception e) {
            log.error("VideoAnalysisLogic monitor_searchuploadedVideoRestService failed", e);
     }
    	
    	
    }
    
    public Boolean searchVideo(String videoId) {
    	log.info("VideoAnalysisLogic searchVideo");
    	
    	Boolean located = false;
		try {
			
			String dirPath = this.getTemp_video_folder();
			File dir = new File(dirPath);
			String[] files = dir.list();
			
			if (files.length == 0) {
				log.error("The directory is empty");
				throw new RuntimeException("El directorio de entrada está vacio");
			} else {
				
				for (String localFile : files) {
					
					log.info(localFile);
					if(localFile.trim().equals(videoId)) {
						log.info("getTemp_video_folder Video located");
						located = true;
						return located;
	
					}else {
						located = false;
						return located;
					}
				}
			}
			
	
			
		} catch (Exception e) {
			log.error("VideoAnalysisLogic searchVideo error",e);
		}
		return located;
		
	}
    
    public void validate_system_folder(String folderLocation) {
    	
    	File  location = new File(folderLocation.trim());
    	
    	if (!location.exists()) {
    		throw new RuntimeException("La ruta: " + location.getPath()+" No existe");
    	}
    	if (!location.isDirectory()) {
    		throw new RuntimeException("La ruta: " + location.getPath()+" No es un directorio");
    	}
    	if (!location.canRead()) {
    		throw new RuntimeException("La ruta: " + location.getPath()+" No puede leerse");
    	}
    	
    }
    
    public void validate_system_file(String fileLocation) {

    	File location = new File(fileLocation);
    	
    	if (!location.exists()) {
    		throw new RuntimeException("El archivo: " + location.getPath()+" No existe");
    	}
    	if (!location.isFile()) {
    		throw new RuntimeException("El archivo: " + location.getPath()+" No es un archivo válido");
    	}
    	if (!location.canRead()) {
    		throw new RuntimeException("El archivo: " + location.getPath()+" No puede leerse");
    	}
    		
    }
    
    public void monitor_keepalive() {
    	
    	try {	
				String url = "http://localhost:8080/vas_monitor/controller/test/keepalive/";
			
				log.info("url: "+url);
				RestTemplate restTemplate = new RestTemplate();
				String response = restTemplate.postForObject(url, "", String.class);
				log.info("response: "+response);
			

        } catch (Exception e) {
            log.error("VideoAnalysisLogic monitor_keepalive failed", e);
    
     }
    	
    	
    }
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteVideoTemp(Long id, String fileName) throws Exception {
		try {

			if(fileName != null && id != null) {
				
				String file = this.getTemp_video_folder() + fileName;
				this.folderMonitorLogic.validate_system_file(file);
				
				this.folderMonitorLogic.deleteVideoFileFromFolder(file);
				
				VideoTemp entity = videoTempLogic.getVideoTemp(id);
				videoTempLogic.deleteVideoTemp(entity);
			}
			
		} catch (Exception e) {
			log.error("deleteVideoTemp failed", e);
            throw e;
			
		}
	
	}
    
 
    
	public String saveFileToTempLocation(String folder, InputStream file, String fileName) {
		try {

			// read input file
			byte[] buffer = new byte[file.available()];
			file.read(buffer);

			File targetFile = new File(folder + fileName);

			validate_system_folder(temp_video_folder);

			OutputStream outStream = new FileOutputStream(targetFile);
			outStream.write(buffer);
			outStream.close();

			log.info("outStream.write guardar ubicacion temporal");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String getWeb_server() {
		try {
			this.web_server = systemParameterLogic.getParamByCodeTexValue(Constantes.WEB_SERVER);
			log.info("getWeb_server: "+web_server);
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
			log.info("getVideo_format: "+video_format);
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
			log.info("getInput_video_folder: "+input_video_folder);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return input_video_folder;
	}

	public void setInput_video_folder(String input_video_folder) {
		this.input_video_folder = input_video_folder;
	}

	@Override
	public String getTemp_video_folder() throws Exception{
		try {
			this.temp_video_folder = systemParameterLogic.getParamByCodeTexValue(Constantes.TEMP_VIDEO_FOLDER);
			log.info("temp_video_folder: "+temp_video_folder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp_video_folder;
	}

	public void setTemp_video_folder(String temp_video_folder) {
		this.temp_video_folder = temp_video_folder;
	}

	public String getLocalTemp_video_folder() throws Exception{
		
		String localTempVideoFolder = "";
		try {
			
			localTempVideoFolder = systemParameterLogic.getParamByCodeTexValue(Constantes.LOCAL_TEMP_VIDEO_FOLDER);
			log.info("getLocalTemp_video_folder: "+localTempVideoFolder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return localTempVideoFolder;
	}
	
	@Override
	public String getWeb_server_temp() throws Exception{
		try {
			
			
			this.web_server_temp = getWeb_server() + systemParameterLogic.getParamByCodeTexValue(Constantes.WEB_SERVER_TEMP);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return web_server_temp;
	}

	public void setWeb_server_temp(String web_server_temp) {
		this.web_server_temp = web_server_temp;
	}

	@Override
	public String getAlg_anl_output_server() {
		try {
			
			this.alg_anl_output_server = getWeb_server() +  systemParameterLogic.getParamByCodeTexValue(Constantes.ALG_ANL_OUTPUT_SERVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alg_anl_output_server;
	}

	public void setAlg_anl_output_server(String alg_anl_output_server) {
		this.alg_anl_output_server = alg_anl_output_server;
	}

	@Override
	public String getAlg_trc_output_server() {
		try {
			this.alg_trc_output_server = getWeb_server() + systemParameterLogic.getParamByCodeTexValue(Constantes.ALG_TRC_OUTPUT_SERVER);
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
			this.output_alg_anl_video_folder = systemParameterLogic.getParamByCodeTexValue(Constantes.OUTPUT_ALG_ANL_VIDEO_FOLDER);
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
			this.output_alg_trc_video_folder = systemParameterLogic.getParamByCodeTexValue(Constantes.OUTPUT_ALG_TRC_VIDEO_FOLDER);
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
			this.external_program_alg_anl_location = systemParameterLogic.getParamByCodeTexValue(Constantes.EXTERNAL_PROGRAM_ALG_ANL_LOCATION);
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
			this.external_program_alg_trc_location = systemParameterLogic.getParamByCodeTexValue(Constantes.EXTERNAL_PROGRAM_ALG_TRC_LOCATION);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return external_program_alg_trc_location;
	}

	public void setExternal_program_alg_trc_location(String external_program_alg_trc_location) {
		this.external_program_alg_trc_location = external_program_alg_trc_location;
	}

}
