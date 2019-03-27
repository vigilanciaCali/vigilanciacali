package co.edu.usbcali.vas.dataaccess.dao;

import java.util.List;

import co.edu.usbcali.vas.dataaccess.api.Dao;
import co.edu.usbcali.vas.model.Video;


/**
* Interface for   VideoDAO.
*
*/
public interface IVideoDAO extends Dao<Video, Long> {
	public List<Video> getVideoDataByIdASC() throws Exception;
	public List<Video> getVideoDataByTrackerById() throws Exception;
	public List<Video> getVideoDataByAnomalousById() throws Exception;
	
	
	
}
