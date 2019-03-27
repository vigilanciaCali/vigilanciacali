package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.model.SystemCronLog;
import co.edu.usbcali.vas.model.dto.SystemCronLogDTO;

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
public interface ISystemCronLogLogic {
    public List<SystemCronLog> getSystemCronLog() throws Exception;

    /**
         * Save an new SystemCronLog entity
         */
    public void saveSystemCronLog(SystemCronLog entity)
        throws Exception;

    /**
         * Delete an existing SystemCronLog entity
         *
         */
    public void deleteSystemCronLog(SystemCronLog entity)
        throws Exception;

    /**
        * Update an existing SystemCronLog entity
        *
        */
    public void updateSystemCronLog(SystemCronLog entity)
        throws Exception;

    /**
         * Load an existing SystemCronLog entity
         *
         */
    public SystemCronLog getSystemCronLog(Long id) throws Exception;

    public List<SystemCronLog> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SystemCronLog> findPageSystemCronLog(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSystemCronLog() throws Exception;

    public List<SystemCronLogDTO> getDataSystemCronLog()
        throws Exception;
}
