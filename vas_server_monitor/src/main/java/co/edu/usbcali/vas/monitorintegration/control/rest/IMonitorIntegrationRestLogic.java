package co.edu.usbcali.vas.monitorintegration.control.rest;

import co.edu.usbcali.vas.model.dto.AlgorithmDTO;

public interface IMonitorIntegrationRestLogic {

	public Boolean isAnlAlgAvailable() throws Exception;

	public Boolean isTrcAlgAvailable() throws Exception;

	public Boolean sendDataToAnlService(AlgorithmDTO algorithmDTO) throws Exception;

	public Boolean waitForAnlAlgAvailable() throws Exception;

	public Boolean waitForTrcAlgAvailable() throws Exception;

	public String getLastVideoProcessedByAnlAlg() throws Exception;

	public String getLastVideoProcessedByTrcAlg() throws Exception;

	public Boolean sendDataToTrcService(AlgorithmDTO algorithmDTO) throws Exception;


   
}
