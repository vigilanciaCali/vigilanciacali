package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.model.SystemCompanyParameter;
import co.edu.usbcali.vas.model.dto.SystemCompanyParameterDTO;

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
public interface ISystemCompanyParameterLogic {
    public List<SystemCompanyParameter> getSystemCompanyParameter()
        throws Exception;

    /**
         * Save an new SystemCompanyParameter entity
         */
    public void saveSystemCompanyParameter(SystemCompanyParameter entity)
        throws Exception;

    /**
         * Delete an existing SystemCompanyParameter entity
         *
         */
    public void deleteSystemCompanyParameter(SystemCompanyParameter entity)
        throws Exception;

    /**
        * Update an existing SystemCompanyParameter entity
        *
        */
    public void updateSystemCompanyParameter(SystemCompanyParameter entity)
        throws Exception;

    /**
         * Load an existing SystemCompanyParameter entity
         *
         */
    public SystemCompanyParameter getSystemCompanyParameter(Integer id)
        throws Exception;

    public List<SystemCompanyParameter> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SystemCompanyParameter> findPageSystemCompanyParameter(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberSystemCompanyParameter()
        throws Exception;

    public List<SystemCompanyParameterDTO> getDataSystemCompanyParameter()
        throws Exception;
}
