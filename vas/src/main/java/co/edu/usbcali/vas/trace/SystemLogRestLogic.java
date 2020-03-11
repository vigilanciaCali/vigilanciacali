package co.edu.usbcali.vas.trace;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import co.edu.usbcali.vas.model.control.ISystemParameterLogic;
import co.edu.usbcali.vas.model.dto.SystemLogDTO;
import co.edu.usbcali.vas.utilities.Constantes;

@Scope("singleton")
@Service("SystemLogRestLogic")
public class SystemLogRestLogic implements ISystemLogRestLogic {
	private static final Logger log = LoggerFactory.getLogger(SystemLogRestLogic.class);

	@Autowired
	private ISystemParameterLogic systemParameterLogic;

	// SAVE LOG VAS_TRACE
	// REST------------------------------------------------------------------------
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save_systemLogRest(String actionName, String note) throws Exception {
		log.info("VAS save_systemLogRest save_action");

		try {
			
			SystemLogDTO entity = new SystemLogDTO();
			entity.setActionDate(new Date());
			entity.setActionName(actionName);
			entity.setUserName("system");
			entity.setNote(note);

			String url = this.getSYSTEM_TRACE_CONTROLLER() + "systemlog/save/";
			log.info("url: " + url);
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.postForObject(url, entity, String.class);

		} catch (RuntimeException e) {
			log.error("VAS save_systemLogRest failed", e);
		}

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void save_systemLogRestDTO(SystemLogDTO entity) throws Exception {
		log.info("VAS save_systemLogRestDTO save_action");

		try {
			// CHECK STATUS
				Boolean serviceStatus = this.checkSystemTraceServiceStatus();

				if (serviceStatus.booleanValue() == true) {
					log.info("VAS save_systemLogRest serviceStatus:" + serviceStatus.booleanValue());

					String url = this.getSYSTEM_TRACE_CONTROLLER() + "systemlog/save/";
					log.info("url: " + url);
					RestTemplate restTemplate = new RestTemplate();
					restTemplate.postForObject(url, entity, String.class);

					// log.info("VAS save_systemLogRest STATUS:"+status);
				}else {
					log.error("SYSTEM_TRACE_CONTROLLER NOT AVAILABLE: "+"Action: "+entity.getActionName());
				}
		
		} catch (RuntimeException e) {
			log.error("VAS save_systemLogRest failed", e);
		}

	}
	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Boolean checkSystemTraceServiceStatus() throws Exception {
		log.info("VAS SystemLogRestLogic checkSystemTraceServiceStatus");

		Boolean status = false;

		try {

			// CHECK STATUS
			String urlStatus = this.getSYSTEM_TRACE_CONTROLLER() + "status/available/";
			log.info("url: " + urlStatus);
			RestTemplate restTemplateStatus = new RestTemplate();
			status = restTemplateStatus.postForObject(urlStatus, status, Boolean.class);
			log.info("status: " + status.booleanValue());

		} catch (Exception e) {
			log.error("VAS SystemLogRestLogic checkSystemTraceServiceStatus failed", e);
		}
		return status;
	}

	public String getSYSTEM_TRACE_CONTROLLER() {
		String SYSTEM_TRACE_CONTROLLER = null;
		try {
			SYSTEM_TRACE_CONTROLLER = systemParameterLogic.getParamByCodeTexValue(Constantes.SYSTEM_TRACE_CONTROLLER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SYSTEM_TRACE_CONTROLLER;
	}

}
