package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.model.VideoDocument;
import co.edu.usbcali.vas.model.dto.VideoDocumentDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface IVideoDocumentLogic {
    public List<VideoDocument> getVideoDocument() throws Exception;

    /**
         * Save an new VideoDocument entity
         */
    public void saveVideoDocument(VideoDocument entity)
        throws Exception;

    /**
         * Delete an existing VideoDocument entity
         *
         */
    public void deleteVideoDocument(VideoDocument entity)
        throws Exception;

    /**
        * Update an existing VideoDocument entity
        *
        */
    public void updateVideoDocument(VideoDocument entity)
        throws Exception;

    /**
         * Load an existing VideoDocument entity
         *
         */
    public VideoDocument getVideoDocument(Long id) throws Exception;

    public List<VideoDocument> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VideoDocument> findPageVideoDocument(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberVideoDocument() throws Exception;

    public List<VideoDocumentDTO> getDataVideoDocument()
        throws Exception;
}
