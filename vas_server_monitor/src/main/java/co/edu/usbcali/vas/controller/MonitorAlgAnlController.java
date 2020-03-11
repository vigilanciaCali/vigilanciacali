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
public class MonitorAlgAnlController {
	private static final Logger log = LoggerFactory.getLogger(MonitorAlgAnlController.class);

	@Autowired
	private IBusinessDelegatorView businessDelegator;

	//Process request from VAS
	//Data is already on vasanl queue
	@PostMapping(value = "/requestAnlAlg/")
	public ResponseEntity<String> requestAnlAlg() throws Exception {
		log.info("1. VAS_SERVER_MONITOR MonitorController requestAnlAlg");

		try {

			businessDelegator.startAnlAlgRequest();

			return ResponseEntity.ok().body("requestAnlAlg received");
		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR requestAnlAlg", e.getMessage(), e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
	

	//CHECK AVAILABILITY--------------------------------------------------
	@PostMapping(value = "/checkAnlAlgAvailable/")
	public @ResponseBody Boolean checkAnlAlgAvailable() throws Exception {
		log.info("VAS_SERVER_MONITOR MonitorController checkAnlAlgAvailable");

		Boolean status = false;
		try {

			status = businessDelegator.isAnlAlgAvailable();
			log.info("VAS_SERVER_MONITOR checkAnlAlgAvailable status: " + status);

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR checkAnlAlgAvailable", e.getMessage(), e);
			throw e;
		}
		return status;

	}
	
	//TEST ALG AVAILBILITY---------------------------------------------------------
	@GetMapping(value = "/checkAnlAlgAvailableTest/")
	public @ResponseBody Boolean checkAnlAlgAvailableTest() throws Exception {
		log.info("VAS_SERVER_MONITOR MonitorController checkAnlAlgAvailableTest");

		Boolean status = false;
		try {

			status = businessDelegator.isAnlAlgAvailable();
			log.info("VAS_SERVER_MONITOR checkAnlAlgAvailableTest status: " + status);

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR checkAnlAlgAvailableTest", e.getMessage(), e);
			throw e;
		}
		return status;

	}

	//--------------------------------------------------
	@PostMapping(value = "/waitForAnlAlgAvailable/")
	public void waitForAnlAlgAvailable() throws Exception {
		log.info("VAS_SERVER_MONITOR MonitorController waitForAnlAlgAvailable");

		try {
			businessDelegator.startAnlAlgRequest();
		
		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR checkTrcAlgAvailable", e.getMessage(), e);
			throw e;
		}
	}

	//ALG FINISHED-----------------------------------------------------------
	//MESSAGE FROM ALG
	@PostMapping(value = "/anomalyDetetectionResult",
			consumes = "application/json", 
			produces = "application/json")
	public void anomalyDetetectionResult(@RequestBody AlgorithmDTO data) throws Exception {
		log.info("1. VAS_SERVER_MONITOR MonitorController anomalyDetetectionResult");

		try {
			if (data != null) {
				log.info("getErrorCode: " + data.getErrorCode());
				//100 OK
				log.info("getMessage: " + data.getMessage());
				log.info("getTransactionId: " + data.getVideoTransactionId());
				//log.info("videoId: " + data.getVideoFile());
				
				businessDelegator.anomalyDetetectionResult(data);
			}

		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR anomalyDetetectionResult", e.getMessage(), e);
		}
	}
	
	//PROCESS PENDING VIDEOS
	@GetMapping(value = "/processPendingAnlAlg/")
	public void processPendingAnlAlg() throws Exception {
		log.info("1. VAS_SERVER_MONITOR MonitorController processPendingAnlAlg");

		try {	
				//businessDelegator.processAnlAlgFinished(videoId);
		
		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR trcalgfinished", e.getMessage(), e);
		}
	}
	
	//TEST------------------------------------------------------------------------------------------
	//call MonitorIntegration sendDataToAnlService
	@PostMapping(value = "/sendDataToAnlService/")
	public ResponseEntity<String> sendDataToAnlService(@RequestBody AlgorithmDTO data) throws Exception {
		log.info("1. VAS_SERVER_MONITOR MonitorController sendDataToAnlService");

		try {
			log.info("getVideoFileTemp: " + data.getVideoFileTemp());
			log.info("getVideoFile: " + data.getVideoFile());
			log.info("getInitTimeParam: " + data.getInitTimeParam());
			log.info("getFinalTimeParam: " + data.getFinalTimeParam());
			log.info("getTempVideoFolder: " + data.getTempVideoFolder());
			log.info("dagetOutputVideoFolderta: " + data.getOutputVideoFolder());
			log.info("getVideoTransactionId: " + data.getVideoTransactionId());

			businessDelegator.sendDataToAnlService(data);

			return ResponseEntity.ok().body("requestAnlAlg received");
		} catch (Exception e) {
			log.error("VAS_SERVER_MONITOR sendDataToAnlService", e.getMessage(), e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	
	
	
	
	

}
