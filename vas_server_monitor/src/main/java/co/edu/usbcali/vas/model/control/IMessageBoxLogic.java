package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.model.MessageBox;
import co.edu.usbcali.vas.model.dto.MessageBoxDTO;

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
public interface IMessageBoxLogic {
    public List<MessageBox> getMessageBox() throws Exception;

    /**
         * Save an new MessageBox entity
         */
    public void saveMessageBox(MessageBox entity) throws Exception;

    /**
         * Delete an existing MessageBox entity
         *
         */
    public void deleteMessageBox(MessageBox entity) throws Exception;

    /**
        * Update an existing MessageBox entity
        *
        */
    public void updateMessageBox(MessageBox entity) throws Exception;

    /**
         * Load an existing MessageBox entity
         *
         */
    public MessageBox getMessageBox(Long id) throws Exception;

    public List<MessageBox> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<MessageBox> findPageMessageBox(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberMessageBox() throws Exception;

    public List<MessageBoxDTO> getDataMessageBox() throws Exception;
}
