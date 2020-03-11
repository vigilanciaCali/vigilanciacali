package co.edu.usbcali.vas.monitor.email.control.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.edu.usbcali.vas.model.control.ISystemParameterLogic;
import co.edu.usbcali.vas.model.dto.AlgorithmDTO;
import co.edu.usbcali.vas.model.dto.VideoTransactionDTO;
import co.edu.usbcali.vas.utilities.Constantes;

@Scope("singleton")
@Service("IEmailRestLogic")
public class EmailRestLogic implements IEmailRestLogic {
	private static final Logger log = LoggerFactory.getLogger(EmailRestLogic.class);

	@Autowired
	private ISystemParameterLogic systemParameterLogic;

	@Override
	public void sendMailProcessingError(AlgorithmDTO algorithmDTO) {

		try {

			String url = this.getServerEmailController() + "systemNotificator/error/";
			log.info("url: " + url);
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.postForObject(url, algorithmDTO, String.class);

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR sendMailProcessingError failed", e);
		}

	}
	
	@Override
	public void sendMailProcessingFinished(VideoTransactionDTO videoTransactionDTO) {

		try {

			String url = this.getServerEmailController() + "systemNotificator/finished/";
			log.info("url: " + url);
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.postForObject(url, videoTransactionDTO, VideoTransactionDTO.class);

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR sendMailProcessingFinished failed", e);
		}

	}
	
	@Override
	public void sendMailProcessingProcess(VideoTransactionDTO videoTransactionDTO) {

		try {

			String url = this.getServerEmailController() + "systemNotificator/process/";
			log.info("url: " + url);
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.postForObject(url, videoTransactionDTO, VideoTransactionDTO.class);

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR sendMailProcessingProcess failed", e);
		}

	}


	public String getServerEmailController() {

		String serviceController = "";
		try {
			serviceController = systemParameterLogic.getParamByCodeTexValue(Constantes.SYSTEM_NOTIFICATOR_CONTROLLER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviceController;
	}

}
