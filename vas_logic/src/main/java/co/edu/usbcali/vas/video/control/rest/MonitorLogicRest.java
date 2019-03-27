package co.edu.usbcali.vas.video.control.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import co.edu.usbcali.vas.model.control.ISystemParameterLogic;
import co.edu.usbcali.vas.model.dto.AlgorithmDTO;
import co.edu.usbcali.vas.utilities.Constantes;


@Scope("singleton")
@Service("MonitorLogicRest")
public class MonitorLogicRest implements IMonitorLogicRest {
    private static final Logger log = LoggerFactory.getLogger(MonitorLogicRest.class);
    
    @Autowired
   	private ISystemParameterLogic systemParameterLogic;

    
    @Override
    @Transactional(readOnly = false)
    public void processVideoWithAnomalousAlgRest(AlgorithmDTO entity) throws Exception {
        log.info("VAS MonitorLogicRest processVideoWithAnomalousAlgRest ");
        
        
        try {
			if (entity != null) {
				
				String url = this.getServiceController() + "monitor/requestAnlAlg/";
				log.info("url: "+url);
				RestTemplate restTemplate = new RestTemplate();
				restTemplate.postForObject(url, entity, AlgorithmDTO.class);
				
				//log.info("VAS MonitorLogicRest ANL STATUS:"+status);
			}

        } catch (Exception e) {
            log.error("VAS processVideoWithAnomalousAlgRest failed", e);
        
     }

       
    }
    
    @Override
    @Transactional(readOnly = false)
    public Boolean monitorControllerStatus() throws Exception {
        log.info("VAS MonitorLogicRest monitorControllerStatus ");
        
        Boolean status = false;
        try {

				String url = this.getServiceController() + "test/status/";
				log.info("url: "+url);
				RestTemplate restTemplate = new RestTemplate();
				status = restTemplate.postForObject(url, status, Boolean.class);
				
				log.info("VAS_MONITOR controller STATUS: "+status);



        } catch (Exception e) {
            log.error("controllerStatus failed", e);
        } 
        
        return status;

    }
    
    @Override
    @Transactional(readOnly = false)
    public String processVideoWithTrackerAlgRest(AlgorithmDTO entity) throws Exception {
        log.info("MonitorLogicRest processVideoWithTrackerAlgRest ");
        
        String status = "";
        try {
			if (entity != null) {
				
				String url = this.getServiceController() + "monitor/requestTrcAlg/";
				log.info("url: "+url);
				RestTemplate restTemplate = new RestTemplate();
				status = restTemplate.postForObject(url, entity, String.class);
				
				log.info("VAS MonitorLogicRest TRC STATUS: "+status);
			}

        } catch (Exception e) {
            log.error("processVideoWithTrackerAlgRest failed", e);
            throw e;
     }
        return status;

    }
    
    public String getServiceController() {
		
    	String serviceController = "";
    	try {
			serviceController = systemParameterLogic.getParamByCodeTexValue(Constantes.SERVICE_CONTROLLER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviceController;
	}
    
    
    
    


}
