package co.edu.usbcali.vas.video.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.vas.model.control.ISystemParameterLogic;
import co.edu.usbcali.vas.model.control.ISystemVideoLogLogic;
import co.edu.usbcali.vas.utilities.Constantes;
import co.edu.usbcali.vas.utilities.FacesUtils;


@Scope("singleton")
@Service("MonitorLogic")
public class MonitorLogic implements IMonitorLogic {
    private static final Logger log = LoggerFactory.getLogger(MonitorLogic.class);

    
    @Autowired
   	private ISystemVideoLogLogic systemVideoLogLogic;
    
    @Autowired
   	private ISystemParameterLogic systemParameterLogic;
    
    @Autowired
   	private IFolderMonitorLogic folderMonitorLogic;
    
    
    private String anomalous_program_location = "";
    private String tracker_program_location = "";
    private String input_video_folder = "";
	private String output_video_folder = "";
	//MATLAB
	private String matlab_anomalous_result_file = "";
	private String matlab_tracker_result_file = "";
	private String matlab_anomalous_result_folder = "";
	private String matlab_tracker_result_folder = "";
	//INPUT PARAMS
	private String alg_anl_params_input_file = "";
	private String alg_tracker_params_input_file = "";

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String processVideoWithAnomalousEventsAlg(String videoFileTemp, String videoId, String inputVideoFolder, String outputVideoFolder, 
    		String externalProgramLocation, String initTimeParam, String finalTimeParam) throws Exception {
        log.info("1.1 MonitorLogic processVideoWith ANOMALOUS Alg-------------------------------------------");

        String status = "";
        String processingStatus = "";
        Boolean located = false;
        this.input_video_folder = inputVideoFolder;
        this.output_video_folder = outputVideoFolder;
        this.anomalous_program_location = externalProgramLocation;
        
		try {
			
			if(videoFileTemp != null) {
				located = this.folderMonitorLogic.searchFile(this.input_video_folder, videoFileTemp);
			}
			
			if (located.booleanValue() == true) {
				log.info("videoFileTemp located");
				
				//Validate program location
				//this.validate_system_file(this.anomalous_program_location);
				
				// EXECUTE EXTERNAL PROGRAM ----------------------------
				log.info("1.2 MonitorLogic execute ANOMALOUS ExternalProgram-------------------------------------");
				processingStatus = this.executeExternalProgram_anomalousEventsAlg(videoFileTemp, videoId, initTimeParam, finalTimeParam);
				
				//SEARCH FILE ON OUTPUT FOLDER--------
				if(output_video_folder != null && processingStatus.toString().trim().equals("100") == true) {
					
					log.info("1.3 MonitorLogic searchVideo initiated---------------------------------------------");
					Boolean outputVideoLocated = this.folderMonitorLogic.searchFile(output_video_folder, videoId);
				
					if(outputVideoLocated.booleanValue() == true) {
						status = "VIDEO_FOUND";
					}else {
						status = "VIDEO_NOT_FOUND";
						throw new RuntimeException("Error localizando video analizado, por favor intente de nuevo");
					}
					
				}else {
					log.error("processingStatus: "+processingStatus);
					log.error("output_video_folder: "+output_video_folder);
					
					throw new RuntimeException("Error procesando video con Eventos Anomalos, processing status: "+processingStatus);
					
				}
				

				//FILE WATCHER
				// 3. Monitor OUTPUT(Folder Polling)
				//log.info("1.3 MonitorLogic outputfileWatcher initiated---------------------------------------------");
				//this.outputFileWatcher(output_video_folder);

				//DELETE TEMP FILE
				//log.info("1.4 MonitorLogic deleteVideoFileFromTempFolder initiated----------------------------------");
				//this.deleteVideoFileFromTempFolder(this.input_video_folder + videoFileTemp);


			}else {
				status = "VIDEO_NOT_FOUND";
				throw new RuntimeException("Error localizando video cargado, por favor intente de nuevo");
			}
			
			
			//Leer video desde ubicación local-------------------
           /* File localFileTmp = new File("/home/gordon/www/html/temp/videotemp.mp4");
            //String firstRemoteFile = "Seguimiento.mp4";
            InputStream inputFileTmp = new FileInputStream(localFileTmp);*/

			
		} catch (Exception e) {
			log.error("MonitorLogic processVideoWithAnomalousEventsAlg", e);
			throw e;
		}
		return status;
    }
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String processVideoWithTrackerAlg(String videoFileTemp, String videoId, String inputVideoFolder, String outputVideoFolder, 
    		String externalProgramLocation, String posXParam, String posYParam, String posX2Param, String posY2Param) throws Exception {
        log.info("3. MonitorLogic processVideoWithTrackerAlg");

        String status = "";
        String processingStatus = "";
        Boolean located = false;
        this.input_video_folder = inputVideoFolder;
        this.output_video_folder = outputVideoFolder;
        this.tracker_program_location = externalProgramLocation;
        
		try {
			
			if(videoFileTemp != null) {
				located = this.folderMonitorLogic.searchFile(this.input_video_folder, videoFileTemp);
			}
			
			
			if (located.booleanValue() == true) {
				log.info("videoFileTemp located");
				
				//Validate program location
				//this.validate_system_file(this.tracker_program_location);
				
				// EXECUTE EXTERNAL PROGRAM ----------------------------
				log.info("1.2 MonitorLogic execute TRACKER ExternalProgram-------------------------------------");
				processingStatus = this.executeExternalProgram_trackerAlg(videoFileTemp, videoId, posXParam, posYParam, posX2Param, posY2Param);
				
				
				//SEARCH FILE ON OUTPUT FOLDER--------
				if(output_video_folder != null && processingStatus.toString().trim().equals("100") == true) {
					
					log.info("1.3 MonitorLogic searchVideo initiated---------------------------------------------");
					Boolean outputVideoLocated = this.folderMonitorLogic.searchFile(output_video_folder, videoId);
				
					if(outputVideoLocated.booleanValue() == true) {
						status = "VIDEO_FOUND";
					}else {
						status = "VIDEO_NOT_FOUND";
						throw new RuntimeException("Error localizando video analizado, por favor intente de nuevo");
					}
					
				}else {
					throw new RuntimeException("Error procesando video con Tracker");
				}
				
				//FILE WATCHER
				//log.info("1.3 MonitorLogic outputfileWatcher initiated---------------------------------------------");
				//this.outputFileWatcher(output_video_folder);
				
				//DELETE TEMP FILE
				//this.deleteVideoFileFromTempFolder(this.input_video_folder + videoFileTemp);


			}else {
				
				status = "VIDEO_NOT_FOUND";
				throw new RuntimeException("Error localizando video, por favor intente de nuevo");
			}
			
			//Leer video desde ubicación local-------------------
           /* File localFileTmp = new File("/home/gordon/www/html/temp/videotemp.mp4");
            //String firstRemoteFile = "Seguimiento.mp4";
            InputStream inputFileTmp = new FileInputStream(localFileTmp);*/

			
		} catch (Exception e) {
			log.error("MonitorLogic processVideoWithTrackerAlg failed", e);
			throw e;
		}
		return status;
    }
    
 
    // ANOMALOUS ALG
    public String executeExternalProgram_anomalousEventsAlg(String videoFileTemp, String videoId, String initFrame, String finalFrame){
    	log.info("3.2 MonitorLogic executeExternalProgram ANOMALOUS EventsAlg------------------------------------------------");
		
    	
    	String processingStatus = "";
    	
    	try {
			
			log.info("videoFileTemp: " + videoFileTemp);
			log.info("videoId: " + videoId);
			log.info("initFrame: " + initFrame);
			log.info("finalFrame: " + finalFrame);
			log.info("anomalous_program_location: " + anomalous_program_location);
			log.info("input_video_folder: " + input_video_folder);
			log.info("output_video_folder: " + output_video_folder);
			
			log.info("Program parameters: " +this.anomalous_program_location+ " " + this.input_video_folder + videoFileTemp+" "+initFrame+" "+finalFrame+" "
					+this.output_video_folder + videoId + " " + "0");
			
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
			
			//VALIDATE PROGRAM FILE------------------------------------------------------------
			this.folderMonitorLogic.validate_system_file(this.anomalous_program_location);
			
			//VALIDATE ARG FILE-------------------------------------------------
			//Get file
			this.getAlg_anl_params_input_file();
			//Validate arguments file
			this.folderMonitorLogic.validate_system_file(this.alg_anl_params_input_file);
			
			//Validate matlab result file
			this.folderMonitorLogic.validate_system_file(this.getMatlab_anomalous_result_file());
			
			//Validate output location
			this.folderMonitorLogic.validate_system_folder(this.output_video_folder);
			
			//Set files to Zero
			this.folderMonitorLogic.writeOnFileZero(this.matlab_anomalous_result_file);
			
			//1.RUN C++ PROGRAM-----------------------------------------------------
			Long iniExecTime = System.currentTimeMillis();
			
			Runtime rt = Runtime.getRuntime();
			String command = "echo " + "'" + this.input_video_folder + videoFileTemp + " " + initFrame + " " + finalFrame +" "
					+this.output_video_folder + videoId + " " + "0" + "'" + " > "+ this.alg_anl_params_input_file;
			
			String[] cmdline = { "sh", "-c", command}; 
			
			log.info("MonitorLogic C++ program executed");
			Process p = rt.exec(cmdline);
			
			//echo "/home/javeriana/www/html/vas/temp/Robo.mp4 1 10 /home/javeriana/www/html/vas/anlout/test.mp4 0" > arg.txt
			log.info(command);
			
			//writeOnFile(alg_anl_params_input_file, command);
			
			log.info("C++ program output: "+p.getOutputStream());
			Long finalExecTime = System.currentTimeMillis() - iniExecTime;
			log.info("Tiempo Ejecución: "+finalExecTime);

			//FILE WATCHER MATLAB OUTPUT FILE
			this.folderMonitorLogic.outputFileWatcher(this.getMatlab_anomalous_result_folder());
			
			//SEARCH MATLAB RESULT IN TXT
			String result = this.folderMonitorLogic.getMatLabResultFromFile(this.matlab_anomalous_result_file);
			log.info("matlab_anomalous_result_file: "+matlab_anomalous_result_file);
			
			//CONDITION ON SUCCESS AND ON FAIL
			processingStatus = this.folderMonitorLogic.validateMatLabResult(result);
			
			//DELETE DATA FROM FILES
			log.info("writeOnFileZero");
			this.folderMonitorLogic.writeOnFileZero(alg_anl_params_input_file);
			this.folderMonitorLogic.writeOnFileZero(matlab_anomalous_result_file);
			
			// Trace log
			systemVideoLogLogic.save_systemVideoLog("ANOMALOUS EVENTS ALG EXECUTED", "AbnormalyEventsAlg"+ " - "+"Tiempo Ejecución: "+finalExecTime);
			
			
		} catch (Exception e) {
			
			log.error("executeExternalProgram error",e);
			// Trace log	
			FacesUtils.addWarningMessage(e.getMessage());
			throw new RuntimeException("El programa de EVENTOS ANOMALOS no puede ser ejecutado, favor contacte al administrador");
			
			
			
		}
		return processingStatus;
	}
    
    public String executeExternalProgram_trackerAlg(String videoFileTemp, String videoId, String posXParam, String posYParam, String posX2Param, String posY2Param) {
    	log.info("4. MonitorLogic executeExternalProgram TRACKER Alg");
		
    	String processingStatus = "";
    	
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
			
			//VALIDATE PROGRAM FILE------------------------------------------------------------
			this.folderMonitorLogic.validate_system_file(this.tracker_program_location);
			
			//VALIDATE ARG FILE-------------------------------------------------
			//Get file
			this.getAlg_tracker_params_input_file();
			//Validate arguments file
			this.folderMonitorLogic.validate_system_file(this.alg_tracker_params_input_file);
			
			//Validate matlab result file
			this.folderMonitorLogic.validate_system_file(this.getMatlab_tracker_result_file());
			
			//Validate output location
			this.folderMonitorLogic.validate_system_folder(this.output_video_folder);
			
			//Set files to Zero
			this.folderMonitorLogic.writeOnFileZero(matlab_tracker_result_file);
			
			//1.RUN C++ PROGRAM-----------------------------------------------------
			Long iniExecTime = System.currentTimeMillis();
			
			//Process p = rt.exec("/usr/bin/vlc");
			//Process p = rt.exec("/home/gordon/temp/videonalitica /home/gordon/temp/input/"+videoId+" 3 5 /home/gordon/temp/output/"+videoId+" 40");
			
			Runtime rt = Runtime.getRuntime();
			String command = "echo " + "'" + this.input_video_folder + videoFileTemp +" "
					+ posXParam +" "+ posYParam +" "+ posX2Param +" "+ posY2Param +" "
					+ this.output_video_folder + videoId + " " + "0" + "'" + " > "+ this.alg_tracker_params_input_file;
			
			String[] cmdline = { "sh", "-c", command}; 
			
			Process p = rt.exec(cmdline);
			
			log.info(command);
			//Process p = rt.exec(this.getC_external_program_location() +" " + this.input_video_folder + videoId +" 3 5 "+ this.output_video_folder + videoId +" 40");
			
			log.info("MonitorLogic C++ program executed");
			log.info("C++ program output: "+p.getOutputStream());
			
			Long finalExecTime = System.currentTimeMillis() - iniExecTime;
			//-----------------------------------------------------
			log.info("Tiempo Ejecución TRACKER: "+finalExecTime);
			
			
			//FILE WATCHER MATLAB OUTPUT FILE
			this.folderMonitorLogic.outputFileWatcher(this.getMatlab_tracker_result_folder());
			
			//SEARCH MATLAB RESULT IN TXT
			String result = this.folderMonitorLogic.getMatLabResultFromFile(this.matlab_tracker_result_file);
			log.info("matlab_anomalous_result_file: "+matlab_tracker_result_file);
			
			//CONDITION ON SUCCESS AND ON FAIL
			processingStatus = this.folderMonitorLogic.validateMatLabResult(result);
			
			//DELETE DATA FROM FILES
			log.info("writeOnFileZero");
			this.folderMonitorLogic.writeOnFileZero(alg_tracker_params_input_file);
			this.folderMonitorLogic.writeOnFileZero(matlab_tracker_result_file);
			
			// Trace log
			systemVideoLogLogic.save_systemVideoLog("TRACKER ALG EXECUTED", "TrackerAlg"+ " - "+"Tiempo Ejecución: "+finalExecTime);
			
			
		} catch (Exception e) {
			log.error("executeExternalProgram error",e.getMessage());
			
			FacesUtils.addWarningMessage(e.getMessage());
			this.folderMonitorLogic.writeOnFileZero(this.alg_anl_params_input_file);
			this.folderMonitorLogic.writeOnFileZero(this.matlab_anomalous_result_file);
			
			throw new RuntimeException("El programa TRACKER no puede ser ejecutado, favor contacte al administrador");
		}
		return processingStatus;
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
	
	

	public String getMatlab_anomalous_result_folder() {
		try {
			this.matlab_anomalous_result_folder = systemParameterLogic.
					getParamByCodeTexValue(Constantes.MATLAB_ANOMALOUS_RESULT_FOLDER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matlab_anomalous_result_folder;
	}

	public void setMatlab_anomalous_result_folder(String matlab_anomalous_result_folder) {
		this.matlab_anomalous_result_folder = matlab_anomalous_result_folder;
	}

	public String getMatlab_tracker_result_folder() {
		try {
			this.matlab_tracker_result_folder = systemParameterLogic.
					getParamByCodeTexValue(Constantes.MATLAB_TRACKER_RESULT_FOLDER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matlab_tracker_result_folder;
	}

	public void setMatlab_tracker_result_folder(String matlab_tracker_result_folder) {
		this.matlab_tracker_result_folder = matlab_tracker_result_folder;
	}
    
    

	
    
    
    
    
    
    
    


}
