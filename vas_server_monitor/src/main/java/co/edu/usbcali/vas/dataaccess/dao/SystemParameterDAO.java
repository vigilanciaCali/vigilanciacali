package co.edu.usbcali.vas.dataaccess.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.vas.dataaccess.api.HibernateDaoImpl;
import co.edu.usbcali.vas.model.SystemParameter;


/**
 * A data access object (DAO) providing persistence and search support for
 * SystemParameter entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.SystemParameter
 */
@Scope("singleton")
@Repository("SystemParameterDAO")
public class SystemParameterDAO extends HibernateDaoImpl<SystemParameter, Integer>
    implements ISystemParameterDAO {
    private static final Logger log = LoggerFactory.getLogger(SystemParameterDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static ISystemParameterDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (ISystemParameterDAO) ctx.getBean("SystemParameterDAO");
    }
    
    @Override
   	public SystemParameter getSystemParameterByCode(String code) throws Exception {
    	SystemParameter systemParameter = null;
   		try {
   			systemParameter = (SystemParameter) sessionFactory.getCurrentSession().getNamedQuery("getSystemParameterByCode")
   					.setParameter("pCode", code).getSingleResult();

   		} catch (Exception e) {
   			log.error(e.getMessage());
   			throw e;
   		}
   		return systemParameter;
   	}
}
