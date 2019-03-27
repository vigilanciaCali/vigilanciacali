package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.model.Ticket;
import co.edu.usbcali.vas.model.dto.TicketDTO;

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
public interface ITicketLogic {
    public List<Ticket> getTicket() throws Exception;

    /**
         * Save an new Ticket entity
         */
    public void saveTicket(Ticket entity) throws Exception;

    /**
         * Delete an existing Ticket entity
         *
         */
    public void deleteTicket(Ticket entity) throws Exception;

    /**
        * Update an existing Ticket entity
        *
        */
    public void updateTicket(Ticket entity) throws Exception;

    /**
         * Load an existing Ticket entity
         *
         */
    public Ticket getTicket(Long id) throws Exception;

    public List<Ticket> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Ticket> findPageTicket(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTicket() throws Exception;

    public List<TicketDTO> getDataTicket() throws Exception;
}
