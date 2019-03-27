package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.model.SystemRestLog;
import co.edu.usbcali.vas.model.dto.SystemRestLogDTO;

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
public interface ISystemRestLogLogic {
    public List<SystemRestLog> getSystemRestLog() throws Exception;

    /**
         * Save an new SystemRestLog entity
         */
    public void saveSystemRestLog(SystemRestLog entity)
        throws Exception;

    /**
         * Delete an existing SystemRestLog entity
         *
         */
    public void deleteSystemRestLog(SystemRestLog entity)
        throws Exception;

    /**
        * Update an existing SystemRestLog entity
        *
        */
    public void updateSystemRestLog(SystemRestLog entity)
        throws Exception;

    /**
         * Load an existing SystemRestLog entity
         *
         */
    public SystemRestLog getSystemRestLog(Long id) throws Exception;

    public List<SystemRestLog> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SystemRestLog> findPageSystemRestLog(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSystemRestLog() throws Exception;

    public List<SystemRestLogDTO> getDataSystemRestLog()
        throws Exception;
}
