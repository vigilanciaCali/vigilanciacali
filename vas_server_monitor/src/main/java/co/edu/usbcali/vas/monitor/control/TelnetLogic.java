package co.edu.usbcali.vas.monitor.control;

import org.apache.commons.net.telnet.TelnetClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


@Scope("singleton")
@Service("TelnetLogic")
public class TelnetLogic implements ITelnetLogic {
    private static final Logger log = LoggerFactory.getLogger(TelnetLogic.class);



    @Override
    public Boolean isTelnetConnectionpAvailable(String serverHost, String port) throws Exception {
    	log.info("VAS TELNETLogic isTelnetConnectionpAvailable");
    	Boolean isAvailable = false;
    	try {
        	TelnetClient client = new TelnetClient();
        	client.setConnectTimeout(5000);
        	client.connect(serverHost.trim().toLowerCase(), Integer.valueOf(port.trim()));
        	isAvailable = true;
        	client.disconnect();
		} catch (Exception e) {
			log.error("isTelnetConnectionpAvailable error",e);
			isAvailable = false;
		}
		return isAvailable;
    	
    	
    }

    





}
