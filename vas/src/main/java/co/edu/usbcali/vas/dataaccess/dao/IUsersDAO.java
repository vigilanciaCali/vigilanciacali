package co.edu.usbcali.vas.dataaccess.dao;

import co.edu.usbcali.vas.dataaccess.api.Dao;
import co.edu.usbcali.vas.model.Users;


/**
* Interface for   UsersDAO.
*
*/
public interface IUsersDAO extends Dao<Users, Integer> {
	public Users getUserByLogin(String login) throws Exception;
}
