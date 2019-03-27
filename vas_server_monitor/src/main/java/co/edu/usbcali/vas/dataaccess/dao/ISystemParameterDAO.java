package co.edu.usbcali.vas.dataaccess.dao;

import co.edu.usbcali.vas.dataaccess.api.Dao;
import co.edu.usbcali.vas.model.SystemParameter;


/**
* Interface for   SystemParameterDAO.
*
*/
public interface ISystemParameterDAO extends Dao<SystemParameter, Integer> {
	
	public SystemParameter getSystemParameterByCode(String code) throws Exception;
}
