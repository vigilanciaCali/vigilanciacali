package co.edu.usbcali.vas.cronjob;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class InitSchedulerLogic {

	

	@Bean
	public TaskCheckAlgAnlRequestLogic initTaskCheckAlgAnlRequestLogic() {
		return new TaskCheckAlgAnlRequestLogic();
	}
	@Bean
	public TaskCheckAlgTrcRequestLogic initTaskCheckAlgTrcRequestLogic() {
		return new TaskCheckAlgTrcRequestLogic();
	}
	
	

}
