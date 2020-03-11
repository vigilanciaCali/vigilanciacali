package co.edu.usbcali.vas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.edu.usbcali.vas.model.dto.VideoTransactionDTO;
import co.edu.usbcali.vas.presentation.businessDelegate.IBusinessDelegatorView;

@Controller
@RequestMapping("/videoTransaction")
public class VideoTransactionController {
	private static final Logger log = LoggerFactory.getLogger(VideoTransactionController.class);

	@Autowired
	private IBusinessDelegatorView businessDelegator;
	
	//GET VIDEO TRANSACTION BY ID
	@CrossOrigin
	@PostMapping(value="/getById/")
	public @ResponseBody VideoTransactionDTO getVideoTransactionById(@RequestBody String videoTransactionoId) throws Exception{
		log.info("VAS VideoTransactionController getVideoTransactionById");
		
		VideoTransactionDTO videoTransactionDTO = null;
		if(videoTransactionoId != null) {
			
			log.info("getVideoTransactionById: "+videoTransactionoId);
			
			videoTransactionDTO = businessDelegator.getVideoTransactionDTOByTransactionId(videoTransactionoId.trim());
		}
		return videoTransactionDTO;
	}
	
	@CrossOrigin
	@PostMapping(value="/updateStatus/")
	public ResponseEntity<String> updateVideoTransactionStatus(@RequestBody VideoTransactionDTO videoTransactionDTO) throws Exception{
		log.info("VAS VideoTransactionController updateVideoTransactionStatus");
		try {
				log.info("videoTransactionId: "+videoTransactionDTO.getVideoTransactionId());
				log.info("getDescription: "+videoTransactionDTO.getDescription());
				log.info("getId_Users: "+videoTransactionDTO.getId_Users().toString());
				log.info("getId_VideoStatus: "+videoTransactionDTO.getId_VideoStatus().toString());
				log.info("getId_VideoType: "+videoTransactionDTO.getId_VideoType().toString());
				
				businessDelegator.updateVideoTransactionStatusByTransactionIdRest(videoTransactionDTO);	
			
		} catch (Exception e) {
			log.error("VAS updateVideoTransactionStatus", e.getMessage(), e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().body("updateVideoTransactionStatus received");
		
	}


	

}
