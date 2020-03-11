package co.edu.usbcali.vas.trace;

import co.edu.usbcali.vas.model.dto.SystemLogDTO;

public interface ISystemLogRestLogic {

	public void save_systemLogRest(String actionName, String note) throws Exception;

	public void save_systemLogRestDTO(SystemLogDTO entity) throws Exception;
   
}
