package co.edu.usbcali.vas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.edu.usbcali.vas.presentation.businessDelegate.IBusinessDelegatorView;
import co.edu.usbcali.vas.model.control.ISystemParameterLogic;
import co.edu.usbcali.vas.model.dto.AlgorithmDTO;

@Controller
@RequestMapping("/monitor")
public class MonitorController {
	private static final Logger log = LoggerFactory.getLogger(MonitorController.class);

	@Autowired
	private IBusinessDelegatorView businessDelegator;
	
	@Autowired
	private ISystemParameterLogic systemParameterLogic;


	@PostMapping(value = "/requestanlalg/")
	public void requestAnlAlg(@RequestBody AlgorithmDTO data) throws Exception {
		log.info("1. VAS_SERVER_MONITOR MonitorController requestAnlAlg");
		
		try {
			
			String status = businessDelegator.processVideoWithAnomalousEventsAlg(
					data.getVideoFileTemp(), data.getVideoFile(), data.getTempVideoFolder(), data.getOutputVideoFolder(), data.getExternalProgramLocation(), 
					data.getInitTimeParam(), data.getFinalTimeParam());

			log.info("VAS_SERVER_MONITOR requestAnlAlg status"+status);
			
		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR requestAnlAlg", e.getMessage(), e);
            throw e;
		}
		
	}
	@PostMapping(value = "/checkalganlavaiable/")
	public @ResponseBody Boolean checkAnlAlgAvailable() throws Exception {
		log.info("VAS_SERVER_MONITOR MonitorController checkAnlAlgAvailable");
		
		Boolean status = false;
		try {

			status = systemParameterLogic.getParamByCodeBooleanValue("ALG_ANL_AVAILABLE");

			log.info("VAS_SERVER_MONITOR requestAnlAlg status: "+status);
			
		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR requestAnlAlg", e.getMessage(), e);
            throw e;
		}
		return status;
		
	}
	
	

}
