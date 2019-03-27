package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.model.MailServer;
import co.edu.usbcali.vas.model.dto.MailServerDTO;

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
public interface IMailServerLogic {
    public List<MailServer> getMailServer() throws Exception;

    /**
         * Save an new MailServer entity
         */
    public void saveMailServer(MailServer entity) throws Exception;

    /**
         * Delete an existing MailServer entity
         *
         */
    public void deleteMailServer(MailServer entity) throws Exception;

    /**
        * Update an existing MailServer entity
        *
        */
    public void updateMailServer(MailServer entity) throws Exception;

    /**
         * Load an existing MailServer entity
         *
         */
    public MailServer getMailServer(Integer id) throws Exception;

    public List<MailServer> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<MailServer> findPageMailServer(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberMailServer() throws Exception;

    public List<MailServerDTO> getDataMailServer() throws Exception;
}
