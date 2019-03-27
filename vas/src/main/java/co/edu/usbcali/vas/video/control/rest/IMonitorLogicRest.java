package co.edu.usbcali.vas.video.control.rest;

import co.edu.usbcali.vas.model.dto.AlgorithmDTO;

public interface IMonitorLogicRest {


	public void processVideoWithAnomalousAlgRest(AlgorithmDTO entity) throws Exception;

	public String processVideoWithTrackerAlgRest(AlgorithmDTO entity) throws Exception;

	public Boolean serverMonitorControllerStatus() throws Exception;

	public Boolean checkAnlAlgAvailable() throws Exception;


	
   
}
