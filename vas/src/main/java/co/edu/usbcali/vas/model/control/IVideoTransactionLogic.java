package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.model.VideoTransaction;
import co.edu.usbcali.vas.model.dto.VideoTransactionDTO;

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
public interface IVideoTransactionLogic {
    public List<VideoTransaction> getVideoTransaction()
        throws Exception;

    /**
         * Save an new VideoTransaction entity
         */
    public void saveVideoTransaction(VideoTransaction entity)
        throws Exception;

    /**
         * Delete an existing VideoTransaction entity
         *
         */
    public void deleteVideoTransaction(VideoTransaction entity)
        throws Exception;

    /**
        * Update an existing VideoTransaction entity
        *
        */
    public void updateVideoTransaction(VideoTransaction entity)
        throws Exception;

    /**
         * Load an existing VideoTransaction entity
         *
         */
    public VideoTransaction getVideoTransaction(Long id)
        throws Exception;

    public List<VideoTransaction> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<VideoTransaction> findPageVideoTransaction(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberVideoTransaction() throws Exception;

    public List<VideoTransactionDTO> getDataVideoTransaction()
        throws Exception;

	public VideoTransaction getVideoTransactionByTransactionId(String videoTransactionId) throws Exception;

	public void updateVideoTransactionStatusByTransactionIdRest(VideoTransactionDTO videoTransactionDTO) throws Exception;

	public VideoTransactionDTO getVideoTransactionDTOByTransactionId(String videoTransactionId) throws Exception;
	
	
	
}
