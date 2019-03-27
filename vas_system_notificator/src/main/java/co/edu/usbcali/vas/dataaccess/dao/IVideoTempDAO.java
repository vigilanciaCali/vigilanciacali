package co.edu.usbcali.vas.dataaccess.dao;

import java.util.List;

import co.edu.usbcali.vas.dataaccess.api.Dao;
import co.edu.usbcali.vas.model.VideoTemp;


/**
* Interface for   VideoTempDAO.
*
*/
public interface IVideoTempDAO extends Dao<VideoTemp, Long> {
	
	public List<VideoTemp> getVideoTempDataByIdASC() throws Exception;
	public List<VideoTemp> getDataVideoTempTracker() throws Exception;
	public List<VideoTemp> getDataVideoTempAnomalous() throws Exception;
}
