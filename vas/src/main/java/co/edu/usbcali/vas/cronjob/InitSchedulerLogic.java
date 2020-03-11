package co.edu.usbcali.vas.cronjob;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class InitSchedulerLogic {

	/*@Bean
	public TasksKeepAliveTestLogic initTasksKeepAliveTestLogic() {
		return new TasksKeepAliveTestLogic();
	}*/
	//TESTING-----------------------------------------
	/*@Bean
	public TaskKeepAliveLogic initTasksKeepAliveLogic() {
		return new TaskKeepAliveLogic();
	}*/

	@Bean
	public TaskCheckAlgRequestLogic initTaskCheckAlgRequestLogic() {
		return new TaskCheckAlgRequestLogic();
	}

	
	
	
	

}
