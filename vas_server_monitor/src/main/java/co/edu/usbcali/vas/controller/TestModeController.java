package co.edu.usbcali.vas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.edu.usbcali.vas.model.SystemParameter;
import co.edu.usbcali.vas.presentation.businessDelegate.IBusinessDelegatorView;
import co.edu.usbcali.vas.utilities.Constantes;

@Controller
@RequestMapping("/testMode")
public class TestModeController {
	private static final Logger log = LoggerFactory.getLogger(TestModeController.class);

	@Autowired
	private IBusinessDelegatorView businessDelegator;
	

	@CrossOrigin
	@PostMapping(value = "/update/")
	public ResponseEntity<String>  updateTestMode(@RequestBody String status) throws Exception {
		log.info("VAS_SERVER_MONITOR TestModeController updateTestMode");
		
		try {
			log.info("updateTestMode status: "+status.toString());
			
			SystemParameter entity = businessDelegator.getSystemParamByCode(Constantes.TEST_MODE);
			
			if(entity != null) {
				log.info("entity "+entity.getBooleanValue().toString());

				if(status.equals("true") == true) {
					log.info("entity.setBooleanValue(true ");
					entity.setBooleanValue(true);
				}else {
					entity.setBooleanValue(false);
				}
					
				businessDelegator.updateSystemParameter(entity);
			}
			
			return ResponseEntity.ok().body("updateTestMode received");
		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR updateTestMode", e.getMessage(), e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		

	}

	@CrossOrigin
	@GetMapping(value = "/status/")
	public @ResponseBody Boolean testMode() throws Exception {
			log.info("VAS_SERVER_MONITOR TestModeController testMode");
			
			SystemParameter entity = businessDelegator.getSystemParamByCode(Constantes.TEST_MODE);
			log.info("testMode  "+entity.getBooleanValue().toString());
			return entity.getBooleanValue().booleanValue();
			
	
		}



}
