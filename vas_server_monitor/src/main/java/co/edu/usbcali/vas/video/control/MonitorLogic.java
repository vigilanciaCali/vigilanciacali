package co.edu.usbcali.vas.video.control;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import co.edu.usbcali.vas.model.control.ISystemParameterLogic;
import co.edu.usbcali.vas.model.control.ISystemVideoLogLogic;
import co.edu.usbcali.vas.model.dto.AlgorithmDTO;
import co.edu.usbcali.vas.utilities.Constantes;


@Scope("singleton")
@Service("MonitorLogic")
public class MonitorLogic implements IMonitorLogic {
    private static final Logger log = LoggerFactory.getLogger(MonitorLogic.class);

    
    @Autowired
   	private ISystemVideoLogLogic systemVideoLogLogic;
    
    @Autowired
   	private ISystemParameterLogic systemParameterLogic;
    
    private String anomalous_program_location = "";
    private String tracker_program_location = "";
    private String input_video_folder = "";
	private String output_video_folder = "";
	private String matlab_anomalous_result_file = "";
	private String matlab_tracker_result_file = "";
	private String alg_anl_params_input_file = "";
	private String alg_tracker_params_input_file = "";

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String processVideoWithAnomalousEventsAlg(String videoFileTemp, String videoId, String inputVideoFolder, String outputVideoFolder, 
    		String externalProgramLocation, String initTimeParam, String finalTimeParam) throws Exception {
        log.info("1.1 VAS_SERVER_MONITOR MonitorLogic processVideoWith ANOMALOUS Alg-------------------------------------------");

        String status = "";
        Boolean located = false;
        this.input_video_folder = inputVideoFolder;
        this.output_video_folder = outputVideoFolder;
        this.anomalous_program_location = externalProgramLocation;
        
		try {
			
			located = this.searchVideo(videoFileTemp);
			
			if (located.booleanValue() == true) {
				log.info(videoFileTemp+ " videoFileTemp LOCATED");
				
				//CALL ALG ANL SERVICE
				log.info("1.2 VAS_SERVER_MONITOR call alg anl service");
				
				// EXECUTE EXTERNAL PROGRAM ----------------------------
				//log.info("1.2 VAS_SERVER_MONITOR MonitorLogic execute ANOMALOUS ExternalProgram-------------------------------------");
				//this.executeExternalProgram_anomalousEventsAlg(videoFileTemp, videoId, initTimeParam, finalTimeParam);
				
				//SEARCH MATLAB RESULT IN TXT
				//log.info("1.3 VAS_SERVER_MONITOR MonitorLogic getMatLabResultFromFile-------------------------------------");
				//status = this.getMatLabResultFromFile(this.getMatlab_anomalous_result_file());
				
				//log.info("EXTERNAL PROGRAM ANL STATUS" + status);
				//ACTIONS ON SUCCESS / FAIL
				//this.validateMatLabResult(status);
				
				//FILE WATCHER
				// 3. Monitor OUTPUT(Folder Polling)
				//ACTIONS ON SUCCESS / FAIL
				//this.validateFileWatcherResult(status);

				//DELETE TEMP FILE
				//log.info("1.4 MonitorLogic deleteVideoFileFromTempFolder initiated----------------------------------");
				//this.deleteVideoFileFromTempFolder(this.input_video_folder + videoFileTemp);
				this.sendToProcessAnomalousAlg();

			}else {
				return status = "VIDEO_NOT_FOUND";
			}
			
			//Leer video desde ubicación local-------------------
           /* File localFileTmp = new File("/home/gordon/www/html/temp/videotemp.mp4");
            //String firstRemoteFile = "Seguimiento.mp4";
            InputStream inputFileTmp = new FileInputStream(localFileTmp);*/

			
		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR MonitorLogic processVideoWithAnomalousEventsAlg", e);
		
		}
		return status;
    }
    
	@Transactional(readOnly = false)
	public void sendToProcessAnomalousAlg() throws Exception {
		log.info("VAS_SERVER_MONITOR MonitorLogic sendToProcessAnomalousAlg ");

		
		try {
			
			Boolean status = this.checkVasControllerStatus();
			
			if(status.booleanValue() == true) {
				String url = this.getVasServerController() + "alg/anlsent/";
				log.info("url: " + url);
				RestTemplate restTemplate = new RestTemplate();
				Boolean sent = restTemplate.postForObject(url, true, Boolean.class);
				log.info("VAS_SERVER_MONITOR MonitorLogic sendToProcessAnomalousAlg: "+sent.booleanValue());
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
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String processVideoWithTrackerAlg(String videoFileTemp, String videoId, String inputVideoFolder, String outputVideoFolder, 
    		String externalProgramLocation, String posXParam, String posYParam, String posX2Param, String posY2Param) throws Exception {
        log.info("3. VAS_MONITOR MonitorLogic processVideoWithTrackerAlg");

        String status = "";
        Boolean located = false;
        this.input_video_folder = inputVideoFolder;
        this.output_video_folder = outputVideoFolder;
        this.tracker_program_location = externalProgramLocation;
        
		try {
			
			located = this.searchVideo(videoFileTemp);
			
			if (located.booleanValue() == true) {
				log.info(videoFileTemp+ " videoFileTemp LOCATED");
				
				//Validate program location
				//this.validate_system_file(this.tracker_program_location);
				
				// EXECUTE EXTERNAL PROGRAM ----------------------------
				log.info("1.2 VAS_MONITOR MonitorLogic execute TRACKER ExternalProgram-------------------------------------");
				this.executeExternalProgram_trackerAlg(videoFileTemp, videoId, posXParam, posYParam, posX2Param, posY2Param);
				
				//SEARCH MATLAB RESULT IN TXT
				status = this.getMatLabResultFromFile(this.getMatlab_anomalous_result_file());
				log.info("VAS_MONITOR matlab_tracker_result_file: "+matlab_tracker_result_file);
				
				log.info("EXTERNAL PROGRAM TRC STATUS" + status);
				//ACTIONS ON SUCCESS / FAIL
				this.validateMatLabResult(status);
				
				//FILE WATCHER
				// 3. Monitor OUTPUT(Folder Polling)
				//ACTIONS ON SUCCESS / FAIL
				this.validateFileWatcherResult(status);
				
				//DELETE TEMP FILE
				//this.deleteVideoFileFromTempFolder(this.input_video_folder + videoFileTemp);


			}else {
				
				return status = "VIDEO_NOT_FOUND";
				//throw new RuntimeException("Error localizando video, por favor intente de nuevo");
			}
			

			
			//Leer video desde ubicación local-------------------
           /* File localFileTmp = new File("/home/gordon/www/html/temp/videotemp.mp4");
            //String firstRemoteFile = "Seguimiento.mp4";
            InputStream inputFileTmp = new FileInputStream(localFileTmp);*/
			

			log.debug("save Video successful");
			
		} catch (Exception e) {
			log.error("MonitorLogic searchVideo", e);
			
		}
		return status;
    }
    
    public Boolean searchVideo(String videoId) {
    	log.info("VAS_MONITOR MonitorLogic searchVideo: "+videoId);
    	
    	Boolean located = false;
		try {
			
			String dirPath = this.input_video_folder;
			log.info("input_video_folder: "+input_video_folder);
			
			validate_system_folder(input_video_folder);
			
			File dir = new File(dirPath);
			String[] files = dir.list();
			
			if (files.length == 0) {
				log.error("The directory is empty");
				throw new RuntimeException("El directorio de entrada está vacio");
			} else {
				
				for (String localFile : files) {
					
					log.info("localFile: "+localFile);
					
					if(localFile.trim().equals(videoId)) {
						log.info("input_video_folder Video located");
						located = true;
						return located;
					}else {
						located = false;
					}
				}
			}
			
	
			
		} catch (Exception e) {
			log.error("VAS_MONITOR searchVideo error",e);
		}
		return located;
		
	}
    
    public void executeExternalProgram_anomalousEventsAlg(String videoFileTemp, String videoId, String initFrame, String finalFrame){
    	log.info("3.2 VAS_MONITOR MonitorLogic executeExternalProgram ANOMALOUS EventsAlg");
    	
		try {
			
			log.info("videoFileTemp: " + videoFileTemp);
			log.info("videoId: " + videoId);
			log.info("initFrame: " + initFrame);
			log.info("finalFrame: " + finalFrame);
			log.info("anomalous_program_location: " + anomalous_program_location);
			log.info("input_video_folder: " + input_video_folder);
			log.info("output_video_folder: " + output_video_folder);
			
			log.info("VAS_MONITOR Program parameters: " +this.anomalous_program_location+ " " + this.input_video_folder + videoFileTemp+" "+initFrame+" "+finalFrame+" "
					+this.output_video_folder + videoId + " " + "0");
		
			//1.RUN C++ PROGRAM-----------------------------------------------------
			Long iniExecTime = System.currentTimeMillis();
			
			//Runtime rt = Runtime.getRuntime();
			//Process p = rt.exec("/usr/bin/vlc");
			//Process p = rt.exec("/home/gordon/temp/videonalitica /home/gordon/temp/input/"+videoId+" 3 5 /home/gordon/temp/output/"+videoId+" 40");
			/*String command = this.anomalous_program_location+ " " + this.input_video_folder + videoFileTemp+" "+initFrame+" "+finalFrame+" "
					+this.output_video_folder + videoId + " " + "0";*/
			//String command = this.input_video_folder + videoFileTemp+" "+initFrame+" "+finalFrame+" "
				//	+this.output_video_folder + videoId + " " + "0";
			//Process p = rt.exec(command);
			//Process p = rt.exec("vlc");
			//Process p = rt.exec(this.getC_external_program_location() +" " + this.input_video_folder + videoId +" 3 5 "+ this.output_video_folder + videoId +" 40");
			
			//WRITE ON FILE------------------------------------------------------------
			this.getAlg_anl_params_input_file();
			//Validate location
			this.validate_system_file(this.alg_anl_params_input_file);
			
			Runtime rt = Runtime.getRuntime();
			String command = "echo " + "'" + this.input_video_folder + videoFileTemp + " " + initFrame + " " + finalFrame +" "
					+this.output_video_folder + videoId + " " + "0" + "'" + " > "+ this.alg_anl_params_input_file;
			
			String[] cmdline = { "sh", "-c", command}; 
			
			Process p = rt.exec(cmdline);
			
			//echo "/home/javeriana/www/html/vas/temp/Robo.mp4 1 10 /home/javeriana/www/html/vas/anlout/test.mp4 0" > arg.txt
			
			log.info(command);
			/*log.info("WRITE ON FILE - fileName: "+alg_anl_params_input_file);
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			String content = command;
			bw.write(content);
			log.info("MonitorLogic File Writed");
			bw.close();*/
			
			log.info("VAS_MONITOR MonitorLogic C++ program executed");
			log.info("VAS_MONITOR C++ program output: "+p.getOutputStream());
			
			Long finalExecTime = System.currentTimeMillis() - iniExecTime;
			//-----------------------------------------------------
			log.info("Tiempo Ejecución: "+finalExecTime);
			
			// Trace log
			systemVideoLogLogic.save_systemVideoLog("VAS_MONITOR ANOMALOUS EVENTS ALG EXECUTED", "AbnormalyEventsAlg"+ " - "+"Tiempo Ejecución: "+finalExecTime);
			
		} catch (Exception e) {
			log.error("VAS_MONITOR executeExternalProgram error",e);
			// Trace log	

			throw new RuntimeException("El programa de EVENTOS ANOMALOS no puede ser ejecutado, favor contacte al administrador");
			
		}

	}
    
    public String executeExternalProgram_trackerAlg(String videoFileTemp, String videoId, String posXParam, String posYParam, String posX2Param, String posY2Param) {
    	log.info("VAS_MONITOR MonitorLogic executeExternalProgram TRACKER Alg");
		try {
			
			log.info("videoFileTemp: " + videoFileTemp);
			log.info("videoId: " + videoId);
			log.info("posXParam: " + posXParam);
			log.info("posYParam: " + posYParam);
			log.info("posX2Param: " + posX2Param);
			log.info("posYParam: " + posY2Param);
			log.info("tracker_program_location: " + tracker_program_location);
			log.info("input_video_folder: " + input_video_folder);
			log.info("output_video_folder: " + output_video_folder);
			
			//1.RUN C++ PROGRAM-----------------------------------------------------
			Long iniExecTime = System.currentTimeMillis();
			
			
			//Process p = rt.exec("/usr/bin/vlc");
			//Process p = rt.exec("/home/gordon/temp/videonalitica /home/gordon/temp/input/"+videoId+" 3 5 /home/gordon/temp/output/"+videoId+" 40");
			
			//WRITE ON FILE------------------------------------------------------------
			this.getAlg_tracker_params_input_file();
			//Validate location
			this.validate_system_file(this.alg_tracker_params_input_file);
			
			Runtime rt = Runtime.getRuntime();
			String command = "echo " + "'" + this.input_video_folder + videoFileTemp +" "
					+ posXParam +" "+ posYParam +" "+ posX2Param +" "+ posY2Param +" "
					+ this.output_video_folder + videoId + " " + "0" + "'" + " > "+ this.alg_tracker_params_input_file;
			
			String[] cmdline = { "sh", "-c", command}; 
			
			Process p = rt.exec(cmdline);
			
			log.info(command);
			//Process p = rt.exec(this.getC_external_program_location() +" " + this.input_video_folder + videoId +" 3 5 "+ this.output_video_folder + videoId +" 40");
			
			log.info("VAS_MONITOR MonitorLogic C++ program executed");
			log.info("VAS_MONITOR C++ program output: "+p.getOutputStream());
			
			Long finalExecTime = System.currentTimeMillis() - iniExecTime;
			//-----------------------------------------------------
			log.info("Tiempo Ejecución TRACKER: "+finalExecTime);
			
			//SEARCH MATLAB RESULT IN TXT
			String result = this.getMatLabResultFromFile(this.getMatlab_tracker_result_file());
			log.info("matlab_tracker_result_file: "+matlab_tracker_result_file);
			
			//CONDITION ON SUCCESS AND ON FAIL
			this.validateMatLabResult(result);
			
			// Trace log
			systemVideoLogLogic.save_systemVideoLog("TRACKER ALG EXECUTED", "TrackerAlg"+ " - "+"Tiempo Ejecución: "+finalExecTime);
			
			
		} catch (Exception e) {
			log.error("VAS_MONITOR executeExternalProgram error",e.getMessage());
			

			throw new RuntimeException("El programa TRACKER no puede ser ejecutado, favor contacte al administrador");
		}
		return "";
	}
    
    public String outputfileWatcher() {
    	log.info("VAS_MONITOR output fileWatcher");
    	
    	String status = "";
    	
		try {
			Path output_folder = Paths.get(this.output_video_folder); 
			log.info("VAS_MONITOR output_video_folder: "+output_video_folder);
			
			WatchService watcher = output_folder.getFileSystem().newWatchService();
			output_folder.register(watcher, StandardWatchEventKinds.ENTRY_CREATE,
					StandardWatchEventKinds.ENTRY_DELETE, 
					StandardWatchEventKinds.ENTRY_MODIFY);
			
			WatchKey watckKey = watcher.take();
			
			List<WatchEvent<?>> events = watckKey.pollEvents();
	           for (WatchEvent event : events) {
	                if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
	                    log.info("FILE_CREATED: " + event.context().toString());
	                    watckKey.reset();
	                    status = "FILE_CREATED";
	                }
	                if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
	                	log.info("FILE_DELETED " + event.context().toString());
	                	status = "FILE_DELETED";
	                }
	                if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
	                	log.info("FILE_MODIFIED: " + event.context().toString());
	                	status = "FILE_MODIFIED";
	                }
	            }
	                 
	    // Trace log
		systemVideoLogLogic.save_systemVideoLog("VAS_MONITOR FILE WATCHER FINISHED", "File Watcher");
			
		} catch (Exception e) {
			status = "FILE_WATCHER_FAIL";
			log.error("VAS_MONITOR outputfileWatcher error",e);

		}
		return status;
	}

    public String getMatLabResultFromFile(String fileLocation) {
    	log.info("VAS_MONITOR matlab_anomalous_result_file: "+matlab_anomalous_result_file);
    	
    	String data = "";
    	try {
    		
        	List<String> list = new ArrayList<String>();
    		
    		Stream<String> stream = Files.lines(Paths.get(fileLocation.trim()));
    		list = stream
    				.map(String::toUpperCase)
    				.collect(Collectors.toList());
    		data = list.get(0);
    		
    		log.info("VAS_MONITOR MATLAB RESULT: "+data.toUpperCase().trim());
    		stream.close();
    		
		} catch (Exception e) {
			log.error("VAS_MONITOR getMatLabResultFromFile ERROR",e);
		}

		return data;
    	
    }
    
    public void validateMatLabResult(String result) {
    	
    	if(result != null && result.equals("") == false) {
    		
    		if(result.equals("10")) {
    			//CALL VAS REST SERVICE 
        		
        	}
    		
    	}
   	
    }
    
    public void validateFileWatcherResult(String result) {
    	
    	if(result != null && result.equals("") == false) {
    		
    		if(result.equals("100")) {
				log.info("1.3 VAS_MONITOR MonitorLogic outputfileWatcher initiated---------------------------------------------");
				
				result = this.outputfileWatcher();
				log.info("VAS_MONITOR FileWatcher Status: "+result);

				//TODO DETELE CODE FROM FILE
			}
    		
    	}
   	
    }
    
    public void deleteVideoFileFromTempFolder(String videoLocation) {
    	log.info("VAS_MONITOR deleteVideoFileFromTempFolder");
    	
    	if(videoLocation != null && videoLocation.equals("") == false) {
    		
    	
    		File file = new File(videoLocation);
    		if(file.exists() && file.isFile() && file.canRead()) {
    			file.delete();
    			log.info("DELETED");
    		}
    		
    	}
   	
    }

	public String getMatlab_anomalous_result_file() {
		try {
			this.matlab_anomalous_result_file = systemParameterLogic.getParamByCodeTexValue(Constantes.MATLAB_ANOMALOUS_RESULT_FILE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matlab_anomalous_result_file;
	}

	public void setMatlab_anomalous_result_file(String matlab_anomalous_result_file) {
		this.matlab_anomalous_result_file = matlab_anomalous_result_file;
	}

	public String getMatlab_tracker_result_file() {
		try {
			this.matlab_tracker_result_file = systemParameterLogic.getParamByCodeTexValue(Constantes.MATLAB_TRACKER_RESULT_FILE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matlab_tracker_result_file;
	}
	
	public void setMatlab_tracker_result_file(String matlab_tracker_result_file) {
		this.matlab_tracker_result_file = matlab_tracker_result_file;
	}
	
	public String getAlg_anl_params_input_file() {
		try {
			this.alg_anl_params_input_file = systemParameterLogic.getParamByCodeTexValue(Constantes.ALG_ANL_PARAMS_INPUT_FILE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alg_anl_params_input_file;
	}

	public void setAlg_anl_params_input_file(String alg_anl_params_input_file) {
		this.alg_anl_params_input_file = alg_anl_params_input_file;
	}
	

	public String getAlg_tracker_params_input_file() {
		try {
			this.alg_tracker_params_input_file = systemParameterLogic.getParamByCodeTexValue(Constantes.ALG_TRC_PARAMS_INPUT_FILE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alg_tracker_params_input_file;
	}

	public void setAlg_tracker_params_input_file(String alg_tracker_params_input_file) {
		this.alg_tracker_params_input_file = alg_tracker_params_input_file;
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
    	log.info("VAS_MONITOR validate_system_file: " + fileLocation);

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
    
    public String getVasServerController() {

		String serviceController = "";
		try {
			serviceController = systemParameterLogic.getParamByCodeTexValue(Constantes.VAS_SERVER_CONTROLLER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviceController;
	}


	
    
    
    
    
    
    
    


}
