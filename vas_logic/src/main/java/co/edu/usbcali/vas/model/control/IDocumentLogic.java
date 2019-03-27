package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.model.Document;
import co.edu.usbcali.vas.model.dto.DocumentDTO;

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
public interface IDocumentLogic {
    public List<Document> getDocument() throws Exception;

    /**
         * Save an new Document entity
         */
    public void saveDocument(Document entity) throws Exception;

    /**
         * Delete an existing Document entity
         *
         */
    public void deleteDocument(Document entity) throws Exception;

    /**
        * Update an existing Document entity
        *
        */
    public void updateDocument(Document entity) throws Exception;

    /**
         * Load an existing Document entity
         *
         */
    public Document getDocument(Long id) throws Exception;

    public List<Document> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Document> findPageDocument(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberDocument() throws Exception;

    public List<DocumentDTO> getDataDocument() throws Exception;
}
