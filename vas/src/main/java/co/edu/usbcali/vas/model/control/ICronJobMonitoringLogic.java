package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.model.CronJobMonitoring;
import co.edu.usbcali.vas.model.dto.CronJobMonitoringDTO;

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
public interface ICronJobMonitoringLogic {
    public List<CronJobMonitoring> getCronJobMonitoring()
        throws Exception;

    /**
         * Save an new CronJobMonitoring entity
         */
    public void saveCronJobMonitoring(CronJobMonitoring entity)
        throws Exception;

    /**
         * Delete an existing CronJobMonitoring entity
         *
         */
    public void deleteCronJobMonitoring(CronJobMonitoring entity)
        throws Exception;

    /**
        * Update an existing CronJobMonitoring entity
        *
        */
    public void updateCronJobMonitoring(CronJobMonitoring entity)
        throws Exception;

    /**
         * Load an existing CronJobMonitoring entity
         *
         */
    public CronJobMonitoring getCronJobMonitoring(Integer id)
        throws Exception;

    public List<CronJobMonitoring> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<CronJobMonitoring> findPageCronJobMonitoring(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberCronJobMonitoring() throws Exception;

    public List<CronJobMonitoringDTO> getDataCronJobMonitoring()
        throws Exception;
}
