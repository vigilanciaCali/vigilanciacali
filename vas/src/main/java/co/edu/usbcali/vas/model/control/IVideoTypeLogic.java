package co.edu.usbcali.vas.model.control;

import java.util.List;

import co.edu.usbcali.vas.model.VideoType;
import co.edu.usbcali.vas.model.dto.VideoTypeDTO;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface IVideoTypeLogic {
    public List<VideoType> getVideoType() throws Exception;

    /**
         * Save an new VideoType entity
         */
    public void saveVideoType(VideoType entity) throws Exception;

    /**
         * Delete an existing VideoType entity
         *
         */
    public void deleteVideoType(VideoType entity) throws Exception;

    /**
        * Update an existing VideoType entity
        *
        */
    public void updateVideoType(VideoType entity) throws Exception;

    /**
         * Load an existing VideoType entity
         *
         */
    public VideoType getVideoType(Integer id) throws Exception;

    public List<VideoType> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VideoType> findPageVideoType(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVideoType() throws Exception;

    public List<VideoTypeDTO> getDataVideoType() throws Exception;

	public VideoType getVideoTypeByCode(String code) throws Exception;
}
