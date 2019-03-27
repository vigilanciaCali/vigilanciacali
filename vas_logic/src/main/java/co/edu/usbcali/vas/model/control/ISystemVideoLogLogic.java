package co.edu.usbcali.vas.model.control;

import java.util.List;

import co.edu.usbcali.vas.model.SystemVideoLog;
import co.edu.usbcali.vas.model.dto.SystemVideoLogDTO;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface ISystemVideoLogLogic {
    public List<SystemVideoLog> getSystemVideoLog() throws Exception;

    /**
         * Save an new SystemVideoLog entity
         */
    public void saveSystemVideoLog(SystemVideoLog entity)
        throws Exception;

    /**
         * Delete an existing SystemVideoLog entity
         *
         */
    public void deleteSystemVideoLog(SystemVideoLog entity)
        throws Exception;

    /**
        * Update an existing SystemVideoLog entity
        *
        */
    public void updateSystemVideoLog(SystemVideoLog entity)
        throws Exception;

    /**
         * Load an existing SystemVideoLog entity
         *
         */
    public SystemVideoLog getSystemVideoLog(Long id) throws Exception;

    public List<SystemVideoLog> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SystemVideoLog> findPageSystemVideoLog(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSystemVideoLog() throws Exception;

    public List<SystemVideoLogDTO> getDataSystemVideoLog()
        throws Exception;

	public String save_systemVideoLog(String action, String nota) throws Exception;
}
