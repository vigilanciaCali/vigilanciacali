package co.edu.usbcali.vas.dataaccess.dao;

import co.edu.usbcali.vas.dataaccess.api.Dao;
import co.edu.usbcali.vas.model.VideoTransaction;


/**
* Interface for   VideoTransactionDAO.
*
*/
public interface IVideoTransactionDAO extends Dao<VideoTransaction, Long> {
	
	public VideoTransaction getVideoTransactionByTransactionId(String videoTransactionId) throws Exception;
}
