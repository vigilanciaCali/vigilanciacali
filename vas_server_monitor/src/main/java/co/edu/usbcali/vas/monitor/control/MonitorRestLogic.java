package co.edu.usbcali.vas.monitor.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import co.edu.usbcali.vas.model.control.ISystemParameterLogic;
import co.edu.usbcali.vas.model.dto.VideoTransactionDTO;
import co.edu.usbcali.vas.trace.ISystemLogRestLogic;
import co.edu.usbcali.vas.utilities.Constantes;


@Scope("singleton")
@Service("MonitorRestLogic")
public class MonitorRestLogic implements IMonitorRestLogic {
    private static final Logger log = LoggerFactory.getLogger(MonitorRestLogic.class);

    @Autowired
   	private ISystemParameterLogic systemParameterLogic;
    @Autowired
	private ISystemLogRestLogic systemLogRestLogic;

    @Override
    @Transactional(readOnly = false)
	public VideoTransactionDTO getVideoTransactionByIdRest(String videoTransactionId) throws Exception {
		log.info("VAS_SERVER_MONITOR MonitorLogic getVideoTransactionById ");
		
		VideoTransactionDTO videoTransactionDTO = null;
		try {

			if(videoTransactionId != null) {
				
				String url = this.getVasServiceController() + "videoTransaction/getById/";
				log.info("url: " + url);
				RestTemplate restTemplate = new RestTemplate();
				videoTransactionDTO = restTemplate.postForObject(url, videoTransactionId, VideoTransactionDTO.class);
			}	
		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR getVideoTransactionById failed", e);
			//TRACE
			systemLogRestLogic.save_systemLogRest("SERVER_MONITOR", "MonitorRestLogic getVideoTransactionByIdRest ERROR: "+e.getMessage());
		}
		return videoTransactionDTO;
	}
    
    @Override
    @Transactional(readOnly = false)
	public void updateVideoTransactionStatus(VideoTransactionDTO videoTransactionDTO) throws Exception {
		log.info("VAS_SERVER_MONITOR MonitorLogicREST updateVideoTransactionStatus ");
	
		try {

			if(videoTransactionDTO != null) {
				
				String url = this.getVasServiceController() + "videoTransaction/updateStatus/";
				log.info("url: " + url);
				RestTemplate restTemplate = new RestTemplate();
				restTemplate.postForObject(url, videoTransactionDTO, String.class);
			}	
		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR updateVideoTransactionStatus failed", e);
		}
	}
 	
	@Transactional(readOnly = false)
	public Boolean checkVasControllerStatus() throws Exception {
		log.info("VAS_SERVER_MONITOR MonitorLogic vasControllerStatus ");

		Boolean status = false;
		try {
				String url = this.getVasServiceController() + "status/available/";
				log.info("url: " + url);
				RestTemplate restTemplate = new RestTemplate();
				status = restTemplate.postForObject(url, status, Boolean.class);

				log.info("VAS_SERVER_MONITOR controller STATUS: " + status);


		} catch (Exception e) {
			log.error("controllerStatus failed", e);
		}

		return status;

	}
	
	@Override
	@Transactional(readOnly = false)
	public void processAlgResult() throws Exception {
		log.info("VAS_SERVER_MONITOR MonitorLogicRest processAlgResult ");

		try {
				String url = this.getVasServiceController() + "alg/algResult/";
				log.info("url: " + url);
				RestTemplate restTemplate = new RestTemplate();
				restTemplate.postForObject(url, "", String.class);
	
		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR processAlgResult failed", e);
		}
	}
    
 
    
    public String getVasServiceController() {

		String serviceController = "";
		try {
			serviceController = systemParameterLogic.getParamByCodeTexValue(Constantes.VAS_CONTROLLER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviceController;
	}


	
    
    
    
    
    
    
    


}
