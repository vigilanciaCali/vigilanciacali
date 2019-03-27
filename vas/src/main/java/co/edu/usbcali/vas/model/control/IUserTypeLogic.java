package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.model.UserType;
import co.edu.usbcali.vas.model.dto.UserTypeDTO;

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
public interface IUserTypeLogic {
    public List<UserType> getUserType() throws Exception;

    /**
         * Save an new UserType entity
         */
    public void saveUserType(UserType entity) throws Exception;

    /**
         * Delete an existing UserType entity
         *
         */
    public void deleteUserType(UserType entity) throws Exception;

    /**
        * Update an existing UserType entity
        *
        */
    public void updateUserType(UserType entity) throws Exception;

    /**
         * Load an existing UserType entity
         *
         */
    public UserType getUserType(Integer id) throws Exception;

    public List<UserType> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<UserType> findPageUserType(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberUserType() throws Exception;

    public List<UserTypeDTO> getDataUserType() throws Exception;
}
