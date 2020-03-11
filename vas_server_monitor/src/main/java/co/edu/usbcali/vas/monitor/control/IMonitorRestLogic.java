package co.edu.usbcali.vas.monitor.control;

import co.edu.usbcali.vas.model.dto.VideoTransactionDTO;

public interface IMonitorRestLogic {

	public VideoTransactionDTO getVideoTransactionByIdRest(String videoTransactionId) throws Exception;

	public void updateVideoTransactionStatus(VideoTransactionDTO videoTransactionDTO) throws Exception;

	public void processAlgResult() throws Exception;


   
}
