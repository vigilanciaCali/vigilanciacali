package co.edu.usbcali.vas.model.control;

import java.util.List;

import co.edu.usbcali.vas.model.SystemLog;
import co.edu.usbcali.vas.model.dto.SystemLogDTO;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface ISystemLogLogic {
    public List<SystemLog> getSystemLog() throws Exception;

    /**
         * Save an new SystemLog entity
         */
    public void saveSystemLog(SystemLog entity) throws Exception;

    /**
         * Delete an existing SystemLog entity
         *
         */
    public void deleteSystemLog(SystemLog entity) throws Exception;

    /**
        * Update an existing SystemLog entity
        *
        */
    public void updateSystemLog(SystemLog entity) throws Exception;

    /**
         * Load an existing SystemLog entity
         *
         */
    public SystemLog getSystemLog(Long id) throws Exception;

    public List<SystemLog> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SystemLog> findPageSystemLog(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSystemLog() throws Exception;

    public List<SystemLogDTO> getDataSystemLog() throws Exception;

	public void save_systemLogRest(SystemLogDTO entity) throws Exception;
}
