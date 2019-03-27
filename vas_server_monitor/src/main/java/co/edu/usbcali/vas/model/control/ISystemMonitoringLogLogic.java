package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.model.SystemMonitoringLog;
import co.edu.usbcali.vas.model.dto.SystemMonitoringLogDTO;

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
public interface ISystemMonitoringLogLogic {
    public List<SystemMonitoringLog> getSystemMonitoringLog()
        throws Exception;

    /**
         * Save an new SystemMonitoringLog entity
         */
    public void saveSystemMonitoringLog(SystemMonitoringLog entity)
        throws Exception;

    /**
         * Delete an existing SystemMonitoringLog entity
         *
         */
    public void deleteSystemMonitoringLog(SystemMonitoringLog entity)
        throws Exception;

    /**
        * Update an existing SystemMonitoringLog entity
        *
        */
    public void updateSystemMonitoringLog(SystemMonitoringLog entity)
        throws Exception;

    /**
         * Load an existing SystemMonitoringLog entity
         *
         */
    public SystemMonitoringLog getSystemMonitoringLog(Long id)
        throws Exception;

    public List<SystemMonitoringLog> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SystemMonitoringLog> findPageSystemMonitoringLog(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberSystemMonitoringLog() throws Exception;

    public List<SystemMonitoringLogDTO> getDataSystemMonitoringLog()
        throws Exception;
}
