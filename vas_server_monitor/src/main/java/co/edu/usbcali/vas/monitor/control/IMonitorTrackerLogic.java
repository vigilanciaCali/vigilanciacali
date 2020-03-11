package co.edu.usbcali.vas.monitor.control;

import co.edu.usbcali.vas.model.dto.AlgorithmDTO;

public interface IMonitorTrackerLogic {

	public void processVideoWithTrackerEventsAlg() throws Exception;

	public void startTrcAlgRequest() throws Exception;

	public void stopTrcAlgRequest() throws Exception;

	public void validateTrcAlgRequest() throws Exception;

	public void trackerDetetectionResult(AlgorithmDTO data) throws Exception;

	
	
   
}
