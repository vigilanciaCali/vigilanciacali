package co.edu.usbcali.vas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/test")
public class TestController {
	private static final Logger log = LoggerFactory.getLogger(TestController.class);

	//TEST
	@CrossOrigin
	@RequestMapping(value="/keepalive/", method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
	public @ResponseBody String test(String test) throws Exception{
		log.info("getEventsByHash REST");
		String alive = "ALIVE";

		return alive;
	}
	

}
