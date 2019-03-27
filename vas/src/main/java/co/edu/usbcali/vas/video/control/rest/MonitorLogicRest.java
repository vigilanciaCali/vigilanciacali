package co.edu.usbcali.vas.video.control.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import co.edu.usbcali.vas.model.control.ISystemParameterLogic;
import co.edu.usbcali.vas.model.dto.AlgorithmDTO;
import co.edu.usbcali.vas.utilities.Constantes;

@Scope("singleton")
@Service("MonitorLogicRest")
public class MonitorLogicRest implements IMonitorLogicRest {
	private static final Logger log = LoggerFactory.getLogger(MonitorLogicRest.class);

	@Autowired
	private ISystemParameterLogic systemParameterLogic;

	@Autowired
	private ITelnetLogic telnetLogic;

	@Override
	@Transactional(readOnly = false)
	public void processVideoWithAnomalousAlgRest(AlgorithmDTO entity) throws Exception {
		log.info("VAS MonitorLogicRest processVideoWithAnomalousAlgRest ");

		try {
			if (entity != null) {

				String url = this.getServerMonitorController() + "monitor/requestanlalg/";
				log.info("url: " + url);
				RestTemplate restTemplate = new RestTemplate();
				restTemplate.postForObject(url, entity, AlgorithmDTO.class);
				// log.info("VAS MonitorLogicRest ANL STATUS:"+status);
			}

		} catch (Exception e) {
			log.error("VAS processVideoWithAnomalousAlgRest failed", e);

		}

	}
	
	@Override
	@Transactional(readOnly = false)
	public Boolean checkAnlAlgAvailable() throws Exception {
		log.info("VAS MonitorLogicRest checkAnlAlgAvailable ");

		Boolean status = false;
		try {
				String url = this.getServerMonitorController() + "monitor/checkalganlavaiable/";
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

	@Override
	@Transactional(readOnly = false)
	public String processVideoWithTrackerAlgRest(AlgorithmDTO entity) throws Exception {
		log.info("MonitorLogicRest processVideoWithTrackerAlgRest ");

		String status = "";
		try {
			if (entity != null) {

				String url = this.getServerMonitorController() + "monitor/requesttrcalg/";
				log.info("url: " + url);
				RestTemplate restTemplate = new RestTemplate();
				status = restTemplate.postForObject(url, entity, String.class);

				log.info("VAS MonitorLogicRest TRC STATUS: " + status);
			}

		} catch (Exception e) {
			log.error("processVideoWithTrackerAlgRest failed", e);
			throw e;
		}
		return status;

	}

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
			server = systemParameterLogic.getParamByCodeTexValue(Constantes.APP_SERVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return server;
	}

	public String getAppServerPort() {

		String port = "";
		try {
			port = systemParameterLogic.getParamByCodeTexValue(Constantes.APP_SERVER_PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return port;
	}

}
