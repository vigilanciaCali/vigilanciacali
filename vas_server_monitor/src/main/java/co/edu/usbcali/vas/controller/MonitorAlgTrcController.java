package co.edu.usbcali.vas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.edu.usbcali.vas.model.dto.AlgorithmDTO;
import co.edu.usbcali.vas.presentation.businessDelegate.IBusinessDelegatorView;

@Controller
@RequestMapping("/monitor")
public class MonitorAlgTrcController {
	private static final Logger log = LoggerFactory.getLogger(MonitorAlgTrcController.class);

	@Autowired
	private IBusinessDelegatorView businessDelegator;


	//Process request from VAS
	//Data is already on trcanl queue
	@PostMapping(value = "/requestTrclAlg/")
	public ResponseEntity<String> requestTrclAlg() throws Exception {
		log.info("1. VAS_SERVER_MONITOR MonitorController requestTrclAlg");

		try {
						
			businessDelegator.startTrcAlgRequest();
			
			return ResponseEntity.ok().body("requestTrclAlg received");
		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR requestTrclAlg", e.getMessage(), e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	//CHECK AVAILABILITY--------------------------------------------------
	@PostMapping(value = "/checkTrcAlgAvailable/")
	public @ResponseBody Boolean checkTrcAlgAvailable() throws Exception {
		log.info("VAS_SERVER_MONITOR MonitorController checkTrcAlgAvailable");

		Boolean status = false;
		try {

			status = businessDelegator.isTrcAlgAvailable();
			log.info("VAS_SERVER_MONITOR checkTrcAlgAvailable status: " + status);

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR checkTrcAlgAvailable", e.getMessage(), e);
			throw e;
		}
		return status;
	}
	
	//TEST ALG AVAILBILITY GET---------------------------------------------------------
	@GetMapping(value = "/checkTrcAlgAvailableTest/")
	public @ResponseBody Boolean checkTrcAlgAvailableTest() throws Exception {
		log.info("VAS_SERVER_MONITOR MonitorController checkTrcAlgAvailableTest");

		Boolean status = false;
		try {

			status = businessDelegator.isTrcAlgAvailable();
			log.info("VAS_SERVER_MONITOR checkTrcAlgAvailableTest status: " + status);

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR checkTrcAlgAvailableTest", e.getMessage(), e);
			throw e;
		}
		return status;
	}
	//--------------------------------------------------
	@PostMapping(value = "/waitForTrcAlgAvailable/")
	public void waitForTrcAlgAvailable() throws Exception {
		log.info("VAS_SERVER_MONITOR MonitorController waitForTrcAlgAvailable");

		try {
			businessDelegator.startTrcAlgRequest();
		
		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR waitForTrcAlgAvailable", e.getMessage(), e);
			throw e;
		}
	}

	//ALG FINISHED-----------------------------------------------------------	
	@PostMapping(value = "/trackingResult",
			consumes = "application/json", 
			produces = "application/json")
	public void trackerDetetectionResult(@RequestBody AlgorithmDTO data) throws Exception {
		log.info("1. VAS_SERVER_MONITOR MonitorController trackingResult");

		try {
			if (data != null) {
				log.info("getErrorCode: " + data.getErrorCode());
				//100 OK
				log.info("getMessage: " + data.getMessage());
				log.info("getTransactionId: " + data.getVideoTransactionId());
				//log.info("videoId: " + data.getVideoFile());

				businessDelegator.trackerDetetectionResult(data);
			}

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR trackingResult", e.getMessage(), e);
		}
	}
	
	//PROCESS PENDING VIDEOS
	@GetMapping(value = "/processPendingTrcAlg/")
	public void processPendingTrcAlg() throws Exception {
		log.info("1. VAS_SERVER_MONITOR MonitorController processPendingTrcAlg");

		try {	
				//businessDelegator.processAnlAlgFinished(videoId);
		
		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR processPendingTrcAlg", e.getMessage(), e);
		}
	}
	
	//TEST------------------------------------------------------------------------------------------
	//call MonitorIntegration sendDataToTrcService
		@PostMapping(value = "/sendDataToTrcService/")
		public ResponseEntity<String> sendDataToTrcService(@RequestBody AlgorithmDTO data) throws Exception {
			log.info("1. VAS_SERVER_MONITOR MonitorController sendDataToTrcService");

			try {
				
				log.info("getVideoFileTemp: " + data.getVideoFileTemp());
				log.info("getVideoFile: " + data.getVideoFile());
				log.info("getPosXParam: " + data.getPosXParam());
				log.info("getPosYParam: " + data.getPosYParam());
				log.info("getPosX2Param: " + data.getPosX2Param());
				log.info("getPosY2Param: " + data.getPosY2Param());
				log.info("getTempVideoFolder: " + data.getTempVideoFolder());
				log.info("dagetOutputVideoFolderta: " + data.getOutputVideoFolder());
				log.info("getVideoTransactionId: " + data.getVideoTransactionId());

				businessDelegator.sendDataToTrcService(data);

				return ResponseEntity.ok().body("requestAnlAlg received");
			} catch (Exception e) {
				log.error("VAS_SERVER_MONITOR sendDataToTrcService", e.getMessage(), e);
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}
	
	
	
	
	
	

}
