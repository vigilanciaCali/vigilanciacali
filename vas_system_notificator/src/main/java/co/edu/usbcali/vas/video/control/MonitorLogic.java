package co.edu.usbcali.vas.video.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
				located = this.searchVideo(this.input_video_folder, videoFileTemp);
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
					Boolean outputVideoLocated = this.searchVideo(output_video_folder, videoId);
				
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
				located = this.searchVideo(this.input_video_folder, videoFileTemp);
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
					Boolean outputVideoLocated = this.searchVideo(output_video_folder, videoId);
				
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
    
    public Boolean searchVideo(String folder, String videoId) {
    	log.info("MonitorLogic searchVideo: "+videoId);
    	
    	Boolean located = false;
		try {
			
			String dirPath = folder;
			log.info("input_folder: "+folder);
			File dir = new File(dirPath);
			String[] files = dir.list();
			
			if (files.length == 0) {
				log.error("The directory is empty");
				throw new RuntimeException("El directorio de entrada está vacio");
			} else {
				
				for (String localFile : files) {
					
					log.info("localFile: "+localFile);
					
					if(localFile.trim().equals(videoId)) {
						log.info("input_folder Video located");
						located = true;
						return located;
					}else {
						located = false;
					}
				}
			}
			
	
			
		} catch (Exception e) {
			log.error("searchVideo error",e);
		}
		return located;
		
	}
    
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
			this.validate_system_file(this.anomalous_program_location);
			
			//VALIDATE ARG FILE-------------------------------------------------
			//Get file
			this.getAlg_anl_params_input_file();
			//Validate arguments file
			this.validate_system_file(this.alg_anl_params_input_file);
			
			//Validate matlab result file
			this.validate_system_file(this.getMatlab_anomalous_result_file());
			
			//Validate output location
			this.validate_system_folder(this.output_video_folder);
			
			//Set files to Zero
			this.writeOnFileZero(this.matlab_anomalous_result_file);
			
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
			this.outputFileWatcher(this.getMatlab_anomalous_result_folder());
			
			//SEARCH MATLAB RESULT IN TXT
			String result = this.getMatLabResultFromFile(this.matlab_anomalous_result_file);
			log.info("matlab_anomalous_result_file: "+matlab_anomalous_result_file);
			
			//CONDITION ON SUCCESS AND ON FAIL
			processingStatus = this.validateMatLabResult(result);
			
			//DELETE DATA FROM FILES
			log.info("writeOnFileZero");
			this.writeOnFileZero(alg_anl_params_input_file);
			this.writeOnFileZero(matlab_anomalous_result_file);
			
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
			this.validate_system_file(this.tracker_program_location);
			
			//VALIDATE ARG FILE-------------------------------------------------
			//Get file
			this.getAlg_tracker_params_input_file();
			//Validate arguments file
			this.validate_system_file(this.alg_tracker_params_input_file);
			
			//Validate matlab result file
			this.validate_system_file(this.getMatlab_tracker_result_file());
			
			//Validate output location
			this.validate_system_folder(this.output_video_folder);
			
			//Set files to Zero
			this.writeOnFileZero(matlab_tracker_result_file);
			
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
			this.outputFileWatcher(this.getMatlab_tracker_result_folder());
			
			//SEARCH MATLAB RESULT IN TXT
			String result = this.getMatLabResultFromFile(this.matlab_tracker_result_file);
			log.info("matlab_anomalous_result_file: "+matlab_tracker_result_file);
			
			//CONDITION ON SUCCESS AND ON FAIL
			processingStatus = this.validateMatLabResult(result);
			
			//DELETE DATA FROM FILES
			log.info("writeOnFileZero");
			this.writeOnFileZero(alg_tracker_params_input_file);
			this.writeOnFileZero(matlab_tracker_result_file);
			
			// Trace log
			systemVideoLogLogic.save_systemVideoLog("TRACKER ALG EXECUTED", "TrackerAlg"+ " - "+"Tiempo Ejecución: "+finalExecTime);
			
			
		} catch (Exception e) {
			log.error("executeExternalProgram error",e.getMessage());
			
			FacesUtils.addWarningMessage(e.getMessage());
			throw new RuntimeException("El programa TRACKER no puede ser ejecutado, favor contacte al administrador");
		}
		return processingStatus;
	}
    
    public String outputFileWatcher(String folder) {
    	log.info("output fileWatcher");
		try {
			Path output_folder = Paths.get(folder); 
			log.info("output_video_folder: "+folder);
			
			WatchService watcher = output_folder.getFileSystem().newWatchService();
			output_folder.register(watcher, StandardWatchEventKinds.ENTRY_CREATE,
					StandardWatchEventKinds.ENTRY_DELETE, 
					StandardWatchEventKinds.ENTRY_MODIFY);
			
			WatchKey watckKey = watcher.take();
			
			List<WatchEvent<?>> events = watckKey.pollEvents();
	           for (WatchEvent event : events) {
	                if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
	                    log.info("Created: " + event.context().toString());
	                    watckKey.reset();
	                }
	                if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
	                	log.info("Delete: " + event.context().toString());
	                }
	                if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
	                	log.info("Modify: " + event.context().toString());
	                }
	            }
	           
	    // Trace log
		systemVideoLogLogic.save_systemVideoLog("FILE WATCHER FINISHED", "File Watcher");
			
		} catch (Exception e) {
			log.error("outputFileWatcher error",e);
			FacesUtils.addWarningMessage(e.getMessage());
		}
		return "";
	}
    
    public String getMatLabResultFromFile(String fileLocation) {

		log.info("MonitorLogic getMatLabResultFromFile: "+fileLocation);

		String data = "";

		try {
			
			
			List<String> list = new ArrayList<String>();

			Stream<String> stream = Files.lines(Paths.get(fileLocation.trim()));
			list = stream.map(String::toUpperCase).collect(Collectors.toList());
			
			log.info("list.size()"+list.size());
			
			if(list.isEmpty() == false) {
				log.info("list.isEmpty()");
				
				data = list.get(0);
				log.info("getMatLabResult: " + data.toUpperCase().trim());
				stream.close();
			} else {
				stream.close();
				FacesUtils.addWarningMessage("No se puede obtener el resultad del analisis, favor contacte al administrador");
			}


		} catch (Exception e) {
			log.error("getMatLabResultFromFile ERROR", e);
		}

		return data;

	}

	/*public String getMatLabResultFromFile(String fileLocation) {

		log.info("Monitor Logic getMatLabResultFromFile");

		String data = "";
		List<String> list = new ArrayList<>();

		try {
			
			File f = new File(fileLocation);
			
			BufferedReader br = new BufferedReader(new FileReader(f));
	
			while ((data = br.readLine()) != null) {
				log.info("data: "+data);
			}
			br.close();
			
			
			log.info("list: "+list.size());
			
			if(list.isEmpty() == false) {
				data = list.get(list.size() -1);
				log.info("data: "+data);
				br.close();
				return data;
				
			}else {
				br.close();
				FacesUtils.addErrorMessage("Error obteniendo resultados de procesamiento, favor contacte al administrador");
			}
			
			List<String> list = new ArrayList<String>();

			Stream<String> stream = Files.lines(Paths.get(fileLocation.trim()));
			list = stream.map(String::toUpperCase).collect(Collectors.toList());
			data = list.get(0);

			if (data != null && data.equals("") == false) {
				log.info("getMatLabResult: " + data.toUpperCase().trim());
				stream.close();
				return data;

			} else {
				stream.close();
				throw new RuntimeException(
						"El programa TRACKER no puede ser ejecutado, favor contacte al administrador");
			}

		} catch (Exception e) {
			log.error("getMatLabResultFromFile ERROR", e);
		}

		return data.trim();

	}*/
    
    public void writeOnFileZero(String file) {
    	
    	try {
    
    		log.info("writeOnFileZero - fileName: "+file);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			String content = "0";
			bw.write(content);
			log.info("MonitorLogic File 0 Writed");
			bw.close();
			
		} catch (Exception e) {
			log.error("writeOnFileZero ERROR",e);
		}
    	
    }
    
    public void writeOnFile(String file, String content) {
    	
    	try {
    
    		log.info("writeOnFile - fileName: "+file);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(content);
			log.info("MonitorLogic file Writed");
			bw.close();
			
		} catch (Exception e) {
			log.error("writeOnFile ERROR",e);
		}
    	
    }
    
	public String validateMatLabResult(String result) {
		log.info("validateMatLabResult: " + result);

		try {

			if (result != null && result.equals("") == false) {

				if (result.equals("0")) {
					log.info("Matlab Result: " + result);
					FacesUtils.addWarningMessage("Error codigo: 0");
					throw new RuntimeException("El sistema de videoAnalitica no ha podido procesar el video, Codigo Error: 0");

				}
				if (result.equals("10")) {
					log.info("Matlab Result: " + result);
					FacesUtils.addWarningMessage("Error codigo: 10");
					throw new RuntimeException("El sistema de videoAnalitica se ha detenido: Codigo Error: 10");
				}
				if (result.equals("100")) {
					//FacesUtils.addWarningMessage("Procesamiento con eventos anomalos finalizado exitosamente");
					log.info("Matlab Result: " + result);
				}

			}

		} catch (Exception e) {
			log.error("validateMatLabResult error");
			writeOnFileZero(this.alg_anl_params_input_file);
			writeOnFileZero(this.matlab_anomalous_result_file);
		}

		return result;

	}
    
	@Override
    public void deleteVideoFileFromFolder(String videoLocation) {
    	log.info("deleteVideoFileFromTempFolder");
    	
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
    
	@Override
    public void validate_system_file(String fileLocation) {
    	log.info("validate_system_file: " + fileLocation);

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
