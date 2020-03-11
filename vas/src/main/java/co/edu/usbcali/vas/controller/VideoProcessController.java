package co.edu.usbcali.vas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.usbcali.vas.model.dto.AlgorithmDTO;
import co.edu.usbcali.vas.presentation.businessDelegate.IBusinessDelegatorView;

@Controller
@RequestMapping("/videoProcess")
public class VideoProcessController {
	private static final Logger log = LoggerFactory.getLogger(VideoProcessController.class);

	@Autowired
	private IBusinessDelegatorView businessDelegator;
	
	@PostMapping(value="/anl")
	public ResponseEntity<String> processAnlAlg(@RequestBody AlgorithmDTO algorithmDTO) throws Exception{
		log.info("VAS VideoProcessController processAnlAlg");
		try {
				
				businessDelegator.analyze_anomalousAlgFromTempLocationRestTEST(
						algorithmDTO.getVideoFileTemp(), 
						algorithmDTO.getLenght(), 
						algorithmDTO.getInitTimeParam(), 
						algorithmDTO.getFinalTimeParam(),
						algorithmDTO.getInfo());
			
		} catch (Exception e) {
			log.error("VAS VideoProcessController processAnlAlg failed", e.getMessage(), e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().body("processAnlAlg received");	
	}

	@PostMapping(value="/trc")
	public ResponseEntity<String> processTrcAlg(@RequestBody AlgorithmDTO algorithmDTO) throws Exception{
		log.info("VAS VideoProcessController processTrcAlg");
		try {
				
				businessDelegator.analyze_trackerAlgFromTempLocationRestTEST(
						algorithmDTO.getVideoFileTemp(),
						algorithmDTO.getLenght(), 
						algorithmDTO.getPosXParam(),
						algorithmDTO.getPosYParam(),
						algorithmDTO.getPosX2Param(),
						algorithmDTO.getPosY2Param(),
						algorithmDTO.getInfo());
			
		} catch (Exception e) {
			log.error("VAS VideoProcessController processTrcAlg failed", e.getMessage(), e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().body("processTrcAlg received");	
	}


	

}
