package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.model.TicketType;
import co.edu.usbcali.vas.model.dto.TicketTypeDTO;

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
public interface ITicketTypeLogic {
    public List<TicketType> getTicketType() throws Exception;

    /**
         * Save an new TicketType entity
         */
    public void saveTicketType(TicketType entity) throws Exception;

    /**
         * Delete an existing TicketType entity
         *
         */
    public void deleteTicketType(TicketType entity) throws Exception;

    /**
        * Update an existing TicketType entity
        *
        */
    public void updateTicketType(TicketType entity) throws Exception;

    /**
         * Load an existing TicketType entity
         *
         */
    public TicketType getTicketType(Integer id) throws Exception;

    public List<TicketType> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<TicketType> findPageTicketType(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTicketType() throws Exception;

    public List<TicketTypeDTO> getDataTicketType() throws Exception;
}
