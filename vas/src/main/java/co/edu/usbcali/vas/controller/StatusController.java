package co.edu.usbcali.vas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/status")
public class StatusController {
	private static final Logger log = LoggerFactory.getLogger(StatusController.class);

	//TEST
		@CrossOrigin
		@GetMapping(value = "/test/")
		public @ResponseBody Boolean keepalive() throws Exception{
			log.info("VAS Status Controller Test");
			Boolean alive = true;

			return alive;
		}
		
		//TEST
			@CrossOrigin
			@PostMapping(value = "/available/")
			public @ResponseBody Boolean status() throws Exception{
				log.info("VAS Status Controller");
				Boolean isAvailable = true;
				log.info("available: " + isAvailable.toString());
				return isAvailable;
			}
	

}
