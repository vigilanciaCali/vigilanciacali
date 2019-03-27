package co.edu.usbcali.vas.dataaccess.dao;

import co.edu.usbcali.vas.dataaccess.api.Dao;
import co.edu.usbcali.vas.model.VideoStatus;


/**
* Interface for   VideoStatusDAO.
*
*/
public interface IVideoStatusDAO extends Dao<VideoStatus, Integer> {
	public VideoStatus getVideoStatusByCode(String code) throws Exception;
}
