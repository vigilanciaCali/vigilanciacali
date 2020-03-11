package co.edu.usbcali.vas.cronjob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.vas.monitor.control.IMonitorTrackerLogic;

public class TaskCheckAlgTrcRequestLogic {

	@Autowired
   	private IMonitorTrackerLogic monitorTrackerLogic;

	@Scheduled(fixedRate=30000)
	//@Scheduled(fixedRate=2000)
	@Transactional(readOnly = false)
	public void checkAlgAnlRequest() throws Exception {
	
		try {
			monitorTrackerLogic.validateTrcAlgRequest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
