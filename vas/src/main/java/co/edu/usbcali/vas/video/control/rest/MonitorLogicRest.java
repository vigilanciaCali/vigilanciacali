package co.edu.usbcali.vas.video.control.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import co.edu.usbcali.vas.model.control.ISystemParameterLogic;
import co.edu.usbcali.vas.utilities.Constantes;

@Scope("singleton")
@Service("MonitorLogicRest")
public class MonitorLogicRest implements IMonitorLogicRest {
	private static final Logger log = LoggerFactory.getLogger(MonitorLogicRest.class);

	@Autowired
	private ISystemParameterLogic systemParameterLogic;

	@Autowired
	private ITelnetLogic telnetLogic;

	
	//SERVER STATUS----------------------------------------------------------------

	@Override
	@Transactional(readOnly = false)
	public Boolean serverMonitorControllerStatus() throws Exception {
		log.info("VAS MonitorLogicRest serverMonitorControllerStatus ");

		Boolean status = false;
		try {

			Boolean connectionAvailable = telnetLogic.isTelnetConnectionpAvailable(this.getAppServer(),
					this.getAppServerPort());

			if (connectionAvailable.booleanValue() == true) {

				String url = this.getServerMonitorController() + "status/available/";
				log.info("url: " + url);
				RestTemplate restTemplate = new RestTemplate();
				status = restTemplate.postForObject(url, status, Boolean.class);

				log.info("VAS serverMonitorControllerStatus STATUS: " + status);

			} else {
				status = false;
			}

		} catch (Exception e) {
			log.error("controllerStatus failed", e);
		}

		return status;

	}
	
	//ALG STATUS--------------------------------------------------------------------- 
	@Override
	@Transactional(readOnly = false)
	public Boolean checkAnlAlgAvailable() throws Exception {
		log.info("VAS MonitorLogicRest checkAnlAlgAvailable ");

		Boolean status = false;
		try {
				String url = this.getServerMonitorController() + "monitor/checkAnlAlgAvailable/";
				log.info("url: " + url);
				RestTemplate restTemplate = new RestTemplate();
				status = restTemplate.postForObject(url, status, Boolean.class);
				// log.info("VAS MonitorLogicRest ANL STATUS:"+status);
		} catch (Exception e) {
			log.error("VAS checkAnlAlgAvailable failed", e);
		}
		return status;
	}
	
	@Override
	@Transactional(readOnly = false)
	public Boolean checkTrcAlgAvailable() throws Exception {
		log.info("VAS MonitorLogicRest checkTrcAlgAvailable ");

		Boolean status = false;
		try {
				String url = this.getServerMonitorController() + "monitor/checkTrcAlgAvailable/";
				log.info("url: " + url);
				RestTemplate restTemplate = new RestTemplate();
				status = restTemplate.postForObject(url, status, Boolean.class);
				// log.info("VAS MonitorLogicRest ANL STATUS:"+status);

		} catch (Exception e) {
			log.error("VAS checkTrcAlgAvailable failed", e);

		}
		return status;
	}
	
	//WAIT FOR ALG--------------------------------------------------------------------
	@Override
	@Transactional(readOnly = false)
	public void waitForAnlAlgAvailable() throws Exception {
		log.info("VAS MonitorLogicRest waitForAnlAlgAvailable ");

		try {
				String url = this.getServerMonitorController() + "monitor/waitForAnlAlgAvailable/";
				log.info("url: " + url);
				RestTemplate restTemplate = new RestTemplate();
				restTemplate.postForObject(url, "", String.class);
				// log.info("VAS MonitorLogicRest ANL STATUS:"+status);
		} catch (Exception e) {
			log.error("VAS waitForAnlAlgAvailable failed", e);
		}
	}
	
	@Override
	@Transactional(readOnly = false)
	public void waitForTrcAlgAvailable() throws Exception {
		log.info("VAS MonitorLogicRest waitForTrcAlgAvailable ");

		try {
				String url = this.getServerMonitorController() + "monitor/waitForTrcAlgAvailable/";
				log.info("url: " + url);
				RestTemplate restTemplate = new RestTemplate();
				restTemplate.postForObject(url, "", String.class);
				// log.info("VAS MonitorLogicRest ANL STATUS:"+status);
		} catch (Exception e) {
			log.error("VAS waitForTrcAlgAvailable failed", e);
		}
	}

	//PROCESS----------------------------------------------------------------------------
	@Override
	@Transactional(readOnly = false)
	public void processVideoWithAnomalousAlgRest() throws Exception {
		log.info("VAS MonitorLogicRest processVideoWithAnomalousAlgRest ");

		try {
				String url = this.getServerMonitorController() + "monitor/requestAnlAlg/";
				log.info("url: " + url);
				RestTemplate restTemplate = new RestTemplate();
				restTemplate.postForObject(url, "", String.class);
				//log.info("VAS MonitorLogicRest ANL STATUS:"+ response);

		} catch (Exception e) {
			log.error("VAS processVideoWithAnomalousAlgRest failed", e);
		}
	}
	
	@Override
	@Transactional(readOnly = false)
	public void processVideoWithTrackerAlgRest() throws Exception {
		log.info("MonitorLogicRest processVideoWithTrackerAlgRest ");

		try {
	
				String url = this.getServerMonitorController() + "monitor/requestTrclAlg/";
				log.info("url: " + url);
				RestTemplate restTemplate = new RestTemplate();
				restTemplate.postForObject(url, "", String.class);

				//log.info("VAS MonitorLogicRest TRC STATUS: " + status);

		} catch (Exception e) {
			log.error("processVideoWithTrackerAlgRest failed", e);
			throw e;
		}

	}

	//GETTERS & SETTERS------------------------------------------------------------------------------
	public String getServerMonitorController() {

		String serviceController = "";
		try {
			serviceController = systemParameterLogic.getParamByCodeTexValue(Constantes.SERVER_MONITOR_CONTROLLER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviceController;
	}

	public String getAppServer() {

		String server = "";
		try {
			server = systemParameterLogic.getParamByCodeTexValue(Constantes.MONITOR_SERVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return server;
	}

	public String getAppServerPort() {

		String port = "";
		try {
			port = systemParameterLogic.getParamByCodeTexValue(Constantes.MONITOR_SERVER_PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return port;
	}

}
