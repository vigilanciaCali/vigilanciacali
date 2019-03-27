package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.model.SystemConfig;
import co.edu.usbcali.vas.model.dto.SystemConfigDTO;

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
public interface ISystemConfigLogic {
    public List<SystemConfig> getSystemConfig() throws Exception;

    /**
         * Save an new SystemConfig entity
         */
    public void saveSystemConfig(SystemConfig entity) throws Exception;

    /**
         * Delete an existing SystemConfig entity
         *
         */
    public void deleteSystemConfig(SystemConfig entity)
        throws Exception;

    /**
        * Update an existing SystemConfig entity
        *
        */
    public void updateSystemConfig(SystemConfig entity)
        throws Exception;

    /**
         * Load an existing SystemConfig entity
         *
         */
    public SystemConfig getSystemConfig(Integer id) throws Exception;

    public List<SystemConfig> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SystemConfig> findPageSystemConfig(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSystemConfig() throws Exception;

    public List<SystemConfigDTO> getDataSystemConfig()
        throws Exception;
}
