package co.edu.usbcali.vas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.usbcali.vas.presentation.businessDelegate.IBusinessDelegatorView;



@Controller
@RequestMapping("/video")
public class VideoController {
	private static final Logger log = LoggerFactory.getLogger(VideoController.class);

	@Autowired
	private IBusinessDelegatorView businessDelegator;
	
	//TEST
	@CrossOrigin
	@RequestMapping(value="/search/", method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json"})
	public String search(@RequestBody String videoId) throws Exception{
		log.info("VideoController search: "+videoId);
		
		String status = "";
		try {
			//call method to look for videoId in InPut Folder
			//status = businessDelegator.searchVideo(videoId);
			log.info("status: "+status);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	

}
