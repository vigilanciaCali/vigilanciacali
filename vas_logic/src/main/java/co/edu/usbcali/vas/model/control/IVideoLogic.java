package co.edu.usbcali.vas.model.control;

import java.util.List;

import co.edu.usbcali.vas.model.Video;
import co.edu.usbcali.vas.model.dto.VideoDTO;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface IVideoLogic {
    public List<Video> getVideo() throws Exception;

    /**
         * Save an new Video entity
         */
    public void saveVideo(Video entity) throws Exception;

    /**
         * Delete an existing Video entity
         *
         */
    public void deleteVideo(Video entity) throws Exception;

    /**
        * Update an existing Video entity
        *
        */
    public void updateVideo(Video entity) throws Exception;

    /**
         * Load an existing Video entity
         *
         */
    public Video getVideo(Long id) throws Exception;

    public List<Video> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Video> findPageVideo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVideo() throws Exception;

    public List<VideoDTO> getDataVideo() throws Exception;

	public void getAnlDataFromRest(String data) throws Exception;

	public List<VideoDTO> getDataVideoTracker() throws Exception;

	public List<VideoDTO> getDataVideoAnomalous() throws Exception;
}
