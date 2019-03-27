package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.model.DeviceLog;
import co.edu.usbcali.vas.model.dto.DeviceLogDTO;

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
public interface IDeviceLogLogic {
    public List<DeviceLog> getDeviceLog() throws Exception;

    /**
         * Save an new DeviceLog entity
         */
    public void saveDeviceLog(DeviceLog entity) throws Exception;

    /**
         * Delete an existing DeviceLog entity
         *
         */
    public void deleteDeviceLog(DeviceLog entity) throws Exception;

    /**
        * Update an existing DeviceLog entity
        *
        */
    public void updateDeviceLog(DeviceLog entity) throws Exception;

    /**
         * Load an existing DeviceLog entity
         *
         */
    public DeviceLog getDeviceLog(Long id) throws Exception;

    public List<DeviceLog> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<DeviceLog> findPageDeviceLog(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberDeviceLog() throws Exception;

    public List<DeviceLogDTO> getDataDeviceLog() throws Exception;
}
