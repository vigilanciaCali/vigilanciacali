package co.edu.usbcali.vas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.edu.usbcali.vas.presentation.businessDelegate.IBusinessDelegatorView;

@Controller
@RequestMapping("/monitoring")
public class MonitoringController {
	private static final Logger log = LoggerFactory.getLogger(MonitoringController.class);
	
	@Autowired
	private IBusinessDelegatorView businessDelegator;

	//CHECK AVAILABILITY--------------------------------------------------
		@PostMapping(value = "/checkHddAvailable/")
		public @ResponseBody Boolean checkHddAvailable() throws Exception {
			log.info("VAS_SERVER_MONITOR MonitorController checkHddAvailable");

			Boolean status = false;
			try {

				status = businessDelegator.validate_system_folderHddMonitoring();
				log.info("VAS_SERVER_MONITOR checkHddAvailable status: " + status);

			} catch (Exception e) {
				log.error("VAS_SERVER_MONITOR checkHddAvailable", e.getMessage(), e);
				throw e;
			}
			return status;

		}

	
	

}
