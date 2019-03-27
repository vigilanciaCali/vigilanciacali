package co.edu.usbcali.vas.video.control.rest;

import org.apache.commons.net.telnet.TelnetClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import co.edu.usbcali.vas.model.control.ISystemParameterLogic;
import co.edu.usbcali.vas.utilities.Constantes;


@Scope("singleton")
@Service("TelnetLogic")
public class TelnetLogic implements ITelnetLogic {
    private static final Logger log = LoggerFactory.getLogger(TelnetLogic.class);

    @Autowired
   	private ISystemParameterLogic systemParameterLogic;
    
    private String SFTP_HOST = "";
    private String SFTP_PORT = "";
    private String SFTP_USER = "";
    private String SFTP_PASS = "";

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

	public String getSFTP_HOST() {
		try {
			this.SFTP_HOST = systemParameterLogic.getParamByCodeTexValue(Constantes.SFTP_HOST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SFTP_HOST;
	}

	public void setSFTP_HOST(String sFTP_HOST) {
		SFTP_HOST = sFTP_HOST;
	}

	public String getSFTP_PORT() {
		try {
			this.SFTP_PORT = systemParameterLogic.getParamByCodeTexValue(Constantes.SFTP_PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SFTP_PORT;
	}

	public void setSFTP_PORT(String sFTP_PORT) {
		SFTP_PORT = sFTP_PORT;
	}

	public String getSFTP_USER() {
		try {
			this.SFTP_USER = systemParameterLogic.getParamByCodeTexValue(Constantes.SFTP_USER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SFTP_USER;
	}

	public void setSFTP_USER(String sFTP_USER) {
		SFTP_USER = sFTP_USER;
	}

	public String getSFTP_PASS() {
		try {
			this.SFTP_PASS = systemParameterLogic.getParamByCodeTexValue(Constantes.SFTP_PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SFTP_PASS;
	}

	public void setSFTP_PASS(String sFTP_PASS) {
		SFTP_PASS = sFTP_PASS;
	}
    





}
