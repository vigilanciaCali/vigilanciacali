package co.edu.usbcali.vas.video.control;

import java.io.BufferedWriter;
import java.io.File;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import co.edu.usbcali.vas.model.control.ISystemVideoLogLogic;
import co.edu.usbcali.vas.utilities.FacesUtils;


@Scope("singleton")
@Service("FolderMonitorLogic")
public class FolderMonitorLogic implements IFolderMonitorLogic {
    private static final Logger log = LoggerFactory.getLogger(FolderMonitorLogic.class);
    
    @Autowired
   	private ISystemVideoLogLogic systemVideoLogLogic;
    
    
    @Override
    public Boolean searchFile(String folder, String videoId) {
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
    
    @Override
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
    
    
    @Override
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
    
    @Override
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
    
    @Override
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
	
	@Override
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
	
	@Override
    public String getAlgStatusFromFile(String fileLocation) {

		log.info("FolderMonitorLogic getAlgStatusFromFile: "+fileLocation);

		String data = "";

		try {
			
			
			List<String> list = new ArrayList<String>();

			Stream<String> stream = Files.lines(Paths.get(fileLocation.trim()));
			list = stream.map(String::toUpperCase).collect(Collectors.toList());
			
			log.info("list.size()"+list.size());
			
			if(list.isEmpty() == false) {
				log.info("list.isEmpty()");
				
				data = list.get(0);
				log.info("AlgStatus: " + data.toUpperCase().trim());
				stream.close();
			} else {
				stream.close();
				FacesUtils.addWarningMessage("No se puede obtener el estado del algoritmo, favor contacte al administrador");
			}


		} catch (Exception e) {
			log.error("getAlgStatusFromFile ERROR", e);
		}

		return data;

	}

	


    
	
    
    
    
    
    
    
    


}
