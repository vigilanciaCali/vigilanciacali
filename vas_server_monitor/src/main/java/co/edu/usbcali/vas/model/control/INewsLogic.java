package co.edu.usbcali.vas.model.control;

import co.edu.usbcali.vas.model.News;
import co.edu.usbcali.vas.model.dto.NewsDTO;

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
public interface INewsLogic {
    public List<News> getNews() throws Exception;

    /**
         * Save an new News entity
         */
    public void saveNews(News entity) throws Exception;

    /**
         * Delete an existing News entity
         *
         */
    public void deleteNews(News entity) throws Exception;

    /**
        * Update an existing News entity
        *
        */
    public void updateNews(News entity) throws Exception;

    /**
         * Load an existing News entity
         *
         */
    public News getNews(Long id) throws Exception;

    public List<News> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<News> findPageNews(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberNews() throws Exception;

    public List<NewsDTO> getDataNews() throws Exception;
}
