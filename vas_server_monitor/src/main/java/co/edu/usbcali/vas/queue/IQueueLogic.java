package co.edu.usbcali.vas.queue;

import co.edu.usbcali.vas.model.dto.AlgorithmDTO;
import co.edu.usbcali.vas.model.dto.VideoTransactionDTO;

public interface IQueueLogic {

	public AlgorithmDTO receiveFirstMessageAnlQueue() throws Exception;
	public AlgorithmDTO receiveFirstMessageTrcQueue() throws Exception;
	public void sendMessageToVasOutQueue(VideoTransactionDTO videoTransactionDTO) throws Exception;

	
	
	
   
}
