package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.model.CronJob;
import co.edu.usbcali.vas.model.dto.CronJobDTO;

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
public interface ICronJobLogic {
    public List<CronJob> getCronJob() throws Exception;

    /**
         * Save an new CronJob entity
         */
    public void saveCronJob(CronJob entity) throws Exception;

    /**
         * Delete an existing CronJob entity
         *
         */
    public void deleteCronJob(CronJob entity) throws Exception;

    /**
        * Update an existing CronJob entity
        *
        */
    public void updateCronJob(CronJob entity) throws Exception;

    /**
         * Load an existing CronJob entity
         *
         */
    public CronJob getCronJob(Integer id) throws Exception;

    public List<CronJob> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<CronJob> findPageCronJob(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberCronJob() throws Exception;

    public List<CronJobDTO> getDataCronJob() throws Exception;
}
