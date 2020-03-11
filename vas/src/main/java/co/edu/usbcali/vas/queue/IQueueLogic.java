package co.edu.usbcali.vas.queue;

import co.edu.usbcali.vas.model.dto.AlgorithmDTO;
import co.edu.usbcali.vas.model.dto.VideoTransactionDTO;

public interface IQueueLogic {

	public void sendMessageAnlQueue(AlgorithmDTO algorithmDTO) throws Exception;

	public VideoTransactionDTO receiveFirstMessageFromVasOutQueue() throws Exception;

	public void sendMessageTrcQueue(AlgorithmDTO algorithmDTO) throws Exception;

	
	
	
   
}
