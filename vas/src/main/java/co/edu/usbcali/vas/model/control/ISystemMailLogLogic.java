package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.model.SystemMailLog;
import co.edu.usbcali.vas.model.dto.SystemMailLogDTO;

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
public interface ISystemMailLogLogic {
    public List<SystemMailLog> getSystemMailLog() throws Exception;

    /**
         * Save an new SystemMailLog entity
         */
    public void saveSystemMailLog(SystemMailLog entity)
        throws Exception;

    /**
         * Delete an existing SystemMailLog entity
         *
         */
    public void deleteSystemMailLog(SystemMailLog entity)
        throws Exception;

    /**
        * Update an existing SystemMailLog entity
        *
        */
    public void updateSystemMailLog(SystemMailLog entity)
        throws Exception;

    /**
         * Load an existing SystemMailLog entity
         *
         */
    public SystemMailLog getSystemMailLog(Long id) throws Exception;

    public List<SystemMailLog> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SystemMailLog> findPageSystemMailLog(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSystemMailLog() throws Exception;

    public List<SystemMailLogDTO> getDataSystemMailLog()
        throws Exception;
}
