package co.edu.usbcali.vas.model.control;

import java.util.List;

import co.edu.usbcali.vas.model.VideoStatus;
import co.edu.usbcali.vas.model.dto.VideoStatusDTO;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface IVideoStatusLogic {
    public List<VideoStatus> getVideoStatus() throws Exception;

    /**
         * Save an new VideoStatus entity
         */
    public void saveVideoStatus(VideoStatus entity) throws Exception;

    /**
         * Delete an existing VideoStatus entity
         *
         */
    public void deleteVideoStatus(VideoStatus entity) throws Exception;

    /**
        * Update an existing VideoStatus entity
        *
        */
    public void updateVideoStatus(VideoStatus entity) throws Exception;

    /**
         * Load an existing VideoStatus entity
         *
         */
    public VideoStatus getVideoStatus(Integer id) throws Exception;

    public List<VideoStatus> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VideoStatus> findPageVideoStatus(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVideoStatus() throws Exception;

    public List<VideoStatusDTO> getDataVideoStatus() throws Exception;

	public VideoStatus getVideoStatusByCode(String code) throws Exception;
}
