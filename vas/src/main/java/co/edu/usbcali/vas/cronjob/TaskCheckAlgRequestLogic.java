package co.edu.usbcali.vas.cronjob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.vas.video.control.IMonitorLogic;

public class TaskCheckAlgRequestLogic {

	@Autowired
   	private IMonitorLogic monitorLogic;
	// @Scheduled(fixedRate=10000)
	// @Scheduled(cron = "0 30 15 * * ?")
	// SEG MIN HOR DIA MES DIASEM


// SCHEDULED TASKS--------------------------------------------------------------------
	
	//@Scheduled(cron = "0 00 07 * * ?")
	@Scheduled(fixedRate = 30000)
	//@Scheduled(fixedRate = 2000)
	@Transactional(readOnly = false)
	public void checkAlgRequest() throws Exception {
	
		try {
			monitorLogic.validateAlgOutResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	

}
