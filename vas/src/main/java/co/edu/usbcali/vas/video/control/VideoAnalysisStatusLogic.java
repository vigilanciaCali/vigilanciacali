package co.edu.usbcali.vas.video.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import co.edu.usbcali.vas.model.control.ISystemParameterLogic;
import co.edu.usbcali.vas.utilities.Constantes;


@Scope("singleton")
@Service("VideoAnalysisStatusLogic")
public class VideoAnalysisStatusLogic implements IVideoAnalysisStatusLogic {
    private static final Logger log = LoggerFactory.getLogger(VideoAnalysisStatusLogic.class);

    @Autowired
   	private IFolderMonitorLogic folderMonitorLogic;
    @Autowired
   	private ISystemParameterLogic systemParameterLogic;
    
    private String status_anomalous_file = "";
	private String status_tracker_file = "";
    
    @Override
    public Boolean isAlgAnlAvailable() {
    	log.info("VideoAnalysisStatusLogic isAlgAnlAvailable: ");
    	
    	Boolean available = false;
    	String result = "";
    	
		try {
			
			result = folderMonitorLogic.getAlgStatusFromFile(this.getStatus_anomalous_file());
			if(result.toUpperCase().equals("TRUE") == true) {
				available = true;
			}else {
				available = false;
			}
		
		} catch (Exception e) {
			log.error("VideoAnalysisStatusLogic isAlgAnlAvailable",e);
		}
		return available;
		
	}
    
    @Override
    public Boolean isAlgTrackerAvailable() {
    	log.info("VideoAnalysisStatusLogic isAlgTrackerAvailable: ");
    	
    	Boolean available = false;
    	String result = "";
    	
		try {
			
			result = folderMonitorLogic.getAlgStatusFromFile(this.getStatus_tracker_file());
			if(result.toUpperCase().equals("TRUE") == true) {
				available = true;
			}else {
				available = false;
			}
			
		} catch (Exception e) {
			log.error("VideoAnalysisStatusLogic isAlgTrackerAvailable",e);
		}
		return available;
		
	}

	public String getStatus_anomalous_file() {
		try {
			this.status_anomalous_file = systemParameterLogic.getParamByCodeTexValue(Constantes.STATUS_ANOMALOUS_FILE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status_anomalous_file;
	}

	public String getStatus_tracker_file() {
		try {
			this.status_tracker_file = systemParameterLogic.getParamByCodeTexValue(Constantes.STATUS_TRACKER_FILE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status_tracker_file;
	}

	public void setStatus_anomalous_file(String status_anomalous_file) {
		this.status_anomalous_file = status_anomalous_file;
	}

	public void setStatus_tracker_file(String status_tracker_file) {
		this.status_tracker_file = status_tracker_file;
	}
	
	

}
