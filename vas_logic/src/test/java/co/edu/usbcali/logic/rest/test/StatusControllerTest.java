package co.edu.usbcali.logic.rest.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class StatusControllerTest {

	private final static Logger log = Logger.getLogger("");
	@Test
	public void validarLogin() {
		log.info("validarLogin ");
		RestTemplate restTemplate = new RestTemplate();
		String estadoDelRest =  restTemplate.postForObject
				("http://localhost:8080/vas_folder_monitor/controller/test/status/","",String.class);
		
		log.info("Status " + estadoDelRest);
	}

}
