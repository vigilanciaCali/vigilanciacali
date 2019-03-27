package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.model.MailTemplate;
import co.edu.usbcali.vas.model.dto.MailTemplateDTO;

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
public interface IMailTemplateLogic {
    public List<MailTemplate> getMailTemplate() throws Exception;

    /**
         * Save an new MailTemplate entity
         */
    public void saveMailTemplate(MailTemplate entity) throws Exception;

    /**
         * Delete an existing MailTemplate entity
         *
         */
    public void deleteMailTemplate(MailTemplate entity)
        throws Exception;

    /**
        * Update an existing MailTemplate entity
        *
        */
    public void updateMailTemplate(MailTemplate entity)
        throws Exception;

    /**
         * Load an existing MailTemplate entity
         *
         */
    public MailTemplate getMailTemplate(Integer id) throws Exception;

    public List<MailTemplate> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<MailTemplate> findPageMailTemplate(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberMailTemplate() throws Exception;

    public List<MailTemplateDTO> getDataMailTemplate()
        throws Exception;
}
