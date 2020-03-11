package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.model.Device;
import co.edu.usbcali.vas.model.dto.DeviceDTO;

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
public interface IDeviceLogic {
    public List<Device> getDevice() throws Exception;

    /**
         * Save an new Device entity
         */
    public void saveDevice(Device entity) throws Exception;

    /**
         * Delete an existing Device entity
         *
         */
    public void deleteDevice(Device entity) throws Exception;

    /**
        * Update an existing Device entity
        *
        */
    public void updateDevice(Device entity) throws Exception;

    /**
         * Load an existing Device entity
         *
         */
    public Device getDevice(Integer id) throws Exception;

    public List<Device> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Device> findPageDevice(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberDevice() throws Exception;

    public List<DeviceDTO> getDataDevice() throws Exception;
}
