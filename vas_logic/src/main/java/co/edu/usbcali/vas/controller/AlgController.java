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

@Controller
@RequestMapping("/alg")
public class AlgController {
	private static final Logger log = LoggerFactory.getLogger(AlgController.class);

	@Autowired
	private IBusinessDelegatorView businessDelegator;
	
	//TEST
	@CrossOrigin
	@PostMapping(value="/anomalous/")
	public void getDataAnl(@RequestBody String data) throws Exception{
		
		if(data != null) {
			
			log.info("AlgController getData "+data);
			
			String anlResult = data.replace('=', ' ');
			log.info("anlResult"+anlResult);
			
			//businessDelegator.getAnlDataFromRest(data);

		}
		
	}
	
	//TEST
		@CrossOrigin
		@PostMapping(value="/tracker/")
		public @ResponseBody String getDataTrc(@RequestBody String data) throws Exception{
			
			log.info("AlgController getData "+data);
			
			log.info(""+data.replace('=', ' '));
			

			return data;
		}
	

}
