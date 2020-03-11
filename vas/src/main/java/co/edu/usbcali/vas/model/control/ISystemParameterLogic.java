package co.edu.usbcali.vas.model.control;

import java.util.Date;
import java.util.List;

import co.edu.usbcali.vas.model.SystemParameter;
import co.edu.usbcali.vas.model.dto.SystemParameterDTO;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface ISystemParameterLogic {
    public List<SystemParameter> getSystemParameter() throws Exception;

    /**
         * Save an new SystemParameter entity
         */
    public void saveSystemParameter(SystemParameter entity)
        throws Exception;

    /**
         * Delete an existing SystemParameter entity
         *
         */
    public void deleteSystemParameter(SystemParameter entity)
        throws Exception;

    /**
        * Update an existing SystemParameter entity
        *
        */
    public void updateSystemParameter(SystemParameter entity)
        throws Exception;

    /**
         * Load an existing SystemParameter entity
         *
         */
    public SystemParameter getSystemParameter(Integer id)
        throws Exception;

    public List<SystemParameter> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SystemParameter> findPageSystemParameter(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberSystemParameter() throws Exception;

    public List<SystemParameterDTO> getDataSystemParameter()
        throws Exception;

    public String getParamByCodeTexValue(String code) throws Exception;

    public Integer getParamByCodeNumValue(String code) throws Exception;

    public Date getParamByCodeDateValue(String code) throws Exception;

    public Boolean getParamByCodeBooleanValue(String code) throws Exception;

	public SystemParameter getSystemParamByCode(String code) throws Exception;

	public long getParamByCodeLongValue(String code) throws Exception;
    
    
    
    
}
