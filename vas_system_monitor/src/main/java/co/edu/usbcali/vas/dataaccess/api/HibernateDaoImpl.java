package co.edu.usbcali.vas.dataaccess.api;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.SessionFactoryUtils;


/**
 *
 * @author <a href="mailto:dgomez@vortexbird.com">Diego A Gomez</a>
 * @project zathuracode
 * @class HibernateDaoImpl
 * @date Nov 26, 2016
 *
 */
@SuppressWarnings({"unchecked",
    "rawtypes"
})
public class HibernateDaoImpl<T, PK extends Serializable> implements Dao<T, PK> {
    @Autowired
    private SessionFactory sessionFactory;
    private Class<T> entityClass;
    private Logger log = null;
    private boolean cacheQueries = false;
    private String queryCacheRegion = "zathuraCache";
    private int fetchSize = 0;
    private int maxResults = 0;

    public HibernateDaoImpl() {
        super();
        this.entityClass = (Class<T>) ((ParameterizedType) getClass()
                                                               .getGenericSuperclass()).getActualTypeArguments()[0];
        log = LoggerFactory.getLogger(entityClass);
    }

    public void save(T newEntity) throws DaoException {
        getSession().save(newEntity);
    }

    public T findById(PK id) {
        return (T) getSession().get(entityClass, id);
    }

    public T load(PK id) {
        return (T) getSession().load(entityClass, id);
    }

    public void update(T entity) throws DaoException {
        getSession().update(entity);
    }

    public void saveOrUpdate(T entity) throws DaoException {
        getSession().saveOrUpdate(entity);
    }

    public void saveOrUpdate(T newEntity, boolean flush)
        throws DaoException {
        saveOrUpdate(newEntity);

        if (flush) {
            getSession().flush();
        }
    }

    public T merge(T entity) throws DaoException {
        return (T) getSession().merge(entity);
    }

    /**
     * You cannot call EntityManager.persist() or EntityManager.remove() on a
     * Detached object. {@link http ://openejb.apache.org/3.0/jpa-concepts.html}
     */
    public void delete(T entity) throws DaoException {
        getSession().delete(entity);
    }

    public void deleteById(PK id) throws DaoException {
        T toRemove = findById(id);
        getSession().delete(toRemove);
    }

    public void deleteByProperty(String tableName, String propertyName,
        Object value) throws DaoException {
        String queryString = "delete from " + tableName +
            " as model where model." + propertyName + "= ?";
        Query queryObject = getSession().createQuery(queryString);
        queryObject.setParameter(0, value);
        queryObject.executeUpdate();
    }

    public Long count() {
        Query query = createQuery("select count(*) from " +
                entityClass.getName());

        Long result = (Long) query.getSingleResult();

        return (result != null) ? result : 0;
    }

    public List<T> find(String queryString) {
        return find(queryString, (Object[]) null);
    }

    public List<T> find(String queryString, Object value) {
        return find(queryString, new Object[] { value });
    }

    public List findOther(String queryString, Object value) {
        return find(queryString, new Object[] { value });
    }

    public List<T> find(String queryString, Object... values) {
        Query queryObject = createQuery(queryString);

        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                queryObject.setParameter(i, values[i]);
            }
        }

        return queryObject.getResultList();
    }

    public List<T> findByNamedParam(String queryString, String paramName,
        Object value) {
        return findByNamedParam(queryString, new String[] { paramName },
            new Object[] { value });
    }

    public List<T> findByNamedParam(String queryString, String[] paramNames,
        Object[] values) {
        if (paramNames.length != values.length) {
            throw new IllegalArgumentException(
                "Length of paramNames array must match length of values array");
        }

        Query queryObject = createQuery(queryString);

        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                applyNamedParameterToQuery(queryObject, paramNames[i], values[i]);
            }
        }

        return queryObject.getResultList();
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Query createQuery(String queryString) {
        Query query = getSession().createQuery(queryString);
        prepareQuery(query);

        return query;
    }

    protected Query createSQLQuery(String queryString) {
        Query query = getSession().createNativeQuery(queryString);
        prepareQuery(query);

        return query;
    }

    /**
     * Apply the given name parameter to the given Query object.
     *
     * @param queryObject
     *            the Query object
     * @param paramName
     *            the name of the parameter
     * @param value
     *            the value of the parameter
     * @throws HibernateException
     *             if thrown by the Query object
     */
    protected void applyNamedParameterToQuery(Query queryObject,
        String paramName, Object value) throws HibernateException {
        if (value instanceof Collection) {
            queryObject.setParameterList(paramName, (Collection) value);
        } else if (value instanceof Object[]) {
            queryObject.setParameterList(paramName, (Object[]) value);
        } else {
            queryObject.setParameter(paramName, value);
        }
    }

    /**
     * Prepare the given Query object, applying cache settings and/or a
     * transaction timeout.
     *
     * @param queryObject
     *            the Query object to prepare
     * @see #setCacheQueries
     * @see #setQueryCacheRegion
     * @see SessionFactoryUtils#applyTransactionTimeout
     */
    protected void prepareQuery(Query queryObject) {
        if (isCacheQueries()) {
            queryObject.setCacheable(true);

            if (getQueryCacheRegion() != null) {
                queryObject.setCacheRegion(getQueryCacheRegion());
            }
        }

        if (getFetchSize() > 0) {
            queryObject.setFetchSize(getFetchSize());
        }

        if (getMaxResults() > 0) {
            queryObject.setMaxResults(getMaxResults());
        }
    }

    public boolean isCacheQueries() {
        return cacheQueries;
    }

    public void setCacheQueries(boolean cacheQueries) {
        this.cacheQueries = cacheQueries;
    }

    public String getQueryCacheRegion() {
        return queryCacheRegion;
    }

    public void setQueryCacheRegion(String queryCacheRegion) {
        this.queryCacheRegion = queryCacheRegion;
    }

    public int getFetchSize() {
        return fetchSize;
    }

    public void setFetchSize(int fetchSize) {
        this.fetchSize = fetchSize;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void deleteAll() throws DaoException {
        String queryString = "delete from " + entityClass.getName();
        Query queryObject = getSession().createQuery(queryString);
        queryObject.executeUpdate();
    }

    /**
     *
     * @param sort
     * @return
     */
    public Order prepareOrderForCriteria(String sort) {
        char symbol = sort.charAt(0);
        int dot = sort.indexOf(".");
        String originalSort = symbol + sort.substring(dot + 1, sort.length());
        sort = sort.substring(dot + 1, sort.length());

        return originalSort.startsWith("+") ? Order.asc(sort) : Order.desc(sort);
    }

    /**
     *
     */
    public List<T> findAll() {
        DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);

        return criteria.getExecutableCriteria(getSession()).list();
    }

    @Override
    public List<T> findPage(String sortColumnName, boolean sortAscending,
        int startRow, int maxResults) {
        log.debug("findPage " + entityClass.getName());

        if ((sortColumnName != null) && (sortColumnName.length() > 0)) {
            try {
                String queryString = "select model from " +
                    entityClass.getName() + " model order by model." +
                    sortColumnName + " " + (sortAscending ? "asc" : "desc");

                return sessionFactory.getCurrentSession()
                                     .createQuery(queryString)
                                     .setFirstResult(startRow)
                                     .setMaxResults(maxResults).getResultList();
            } catch (RuntimeException re) {
                throw re;
            }
        } else {
            try {
                String queryString = "select model from " +
                    entityClass.getName() + " model";

                return sessionFactory.getCurrentSession()
                                     .createQuery(queryString)
                                     .setFirstResult(startRow)
                                     .setMaxResults(maxResults).getResultList();
            } catch (RuntimeException re) {
                log.error("findPage " + entityClass.getName() + " failed", re);
                throw re;
            }
        }
    }

    @Override
    public List<T> findByCriteria(String whereCondition) {
        log.debug("finding " + entityClass.getName() + " " + whereCondition);

        try {
            String where = ((whereCondition == null) ||
                (whereCondition.length() == 0)) ? "" : ("where " +
                whereCondition);
            final String queryString = "select model from " +
                entityClass.getName() + " model " + where;

            List<T> entitiesList = sessionFactory.getCurrentSession()
                                                 .createQuery(queryString)
                                                 .getResultList();

            return entitiesList;
        } catch (RuntimeException re) {
            log.error("find By Criteria failed", re);
            throw re;
        }
    }

    @Override
    public List<T> findByProperty(String propertyName, Object value) {
        log.debug("finding " + entityClass.getName() +
            " instance with property: " + propertyName + ", value: " + value);

        try {
            String queryString = "from " + entityClass.getName() +
                " as model where model." + propertyName + "= ?";
            Query queryObject = sessionFactory.getCurrentSession()
                                              .createQuery(queryString);
            queryObject.setParameter(0, value);

            return queryObject.getResultList();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }
}
