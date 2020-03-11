package co.edu.usbcali.vas.monitor.control;

import co.edu.usbcali.vas.model.dto.AlgorithmDTO;

public interface IMonitorLogic {

	public void anomalyDetetectionResult(AlgorithmDTO data) throws Exception;

	public void processVideoWithAnomalousEventsAlg() throws Exception;

	public void startAnlAlgRequest() throws Exception;

	public void stopAnlAlgRequest() throws Exception;

	public void validateAnlAlgRequest() throws Exception;

	public Boolean validate_system_folderHddMonitoring() throws Exception;


   
}
