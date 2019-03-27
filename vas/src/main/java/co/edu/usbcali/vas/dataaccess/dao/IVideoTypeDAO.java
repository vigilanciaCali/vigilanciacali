package co.edu.usbcali.vas.dataaccess.dao;

import co.edu.usbcali.vas.dataaccess.api.Dao;
import co.edu.usbcali.vas.model.VideoType;


/**
* Interface for   VideoTypeDAO.
*
*/
public interface IVideoTypeDAO extends Dao<VideoType, Integer> {
	public VideoType getVideoTypeByCode(String code) throws Exception;
}
