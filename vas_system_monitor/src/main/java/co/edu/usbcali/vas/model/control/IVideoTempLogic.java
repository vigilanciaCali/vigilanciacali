package co.edu.usbcali.vas.model.control;

import java.util.List;

import co.edu.usbcali.vas.model.VideoTemp;
import co.edu.usbcali.vas.model.dto.VideoTempDTO;

public interface IVideoTempLogic {
    public List<VideoTemp> getVideoTemp() throws Exception;

    /**
         * Save an new VideoTemp entity
         */
    public void saveVideoTemp(VideoTemp entity) throws Exception;

    /**
         * Delete an existing VideoTemp entity
         *
         */
    public void deleteVideoTemp(VideoTemp entity) throws Exception;

    /**
        * Update an existing VideoTemp entity
        *
        */
    public void updateVideoTemp(VideoTemp entity) throws Exception;

    /**
         * Load an existing VideoTemp entity
         *
         */
    public VideoTemp getVideoTemp(Long id) throws Exception;

    public List<VideoTemp> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VideoTemp> findPageVideoTemp(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVideoTemp() throws Exception;

    public List<VideoTempDTO> getDataVideoTemp() throws Exception;

	public List<VideoTempDTO> getDataVideoTempTracker() throws Exception;

	public List<VideoTempDTO> getDataVideoTempAnomalous() throws Exception;
	
	
}
