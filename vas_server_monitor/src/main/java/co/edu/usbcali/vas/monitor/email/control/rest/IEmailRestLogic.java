package co.edu.usbcali.vas.monitor.email.control.rest;

import co.edu.usbcali.vas.model.dto.AlgorithmDTO;
import co.edu.usbcali.vas.model.dto.VideoTransactionDTO;

public interface IEmailRestLogic {

	public void sendMailProcessingError(AlgorithmDTO algorithmDTO);

	public void sendMailProcessingFinished(VideoTransactionDTO videoTransactionDTO);

	public void sendMailProcessingProcess(VideoTransactionDTO videoTransactionDTO);


   
}
