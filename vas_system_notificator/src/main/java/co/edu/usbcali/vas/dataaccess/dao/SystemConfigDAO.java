package co.edu.usbcali.vas.dataaccess.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.vas.dataaccess.api.HibernateDaoImpl;
import co.edu.usbcali.vas.model.SystemConfig;


@Scope("singleton")
@Repository("SystemConfigDAO")
public class SystemConfigDAO extends HibernateDaoImpl<SystemConfig, Integer>
    implements ISystemConfigDAO {
    private static final Logger log = LoggerFactory.getLogger(SystemConfigDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static ISystemConfigDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (ISystemConfigDAO) ctx.getBean("SystemConfigDAO");
    }
    
    @Override
   	public SystemConfig getSystemConfigByCode(String code) throws Exception {
    	SystemConfig  systemConfig = null;
   		try {
   			systemConfig = (SystemConfig) sessionFactory.getCurrentSession().getNamedQuery("getSystemConfigByCode")
   					.setParameter("pCode", code).getSingleResult();

   		} catch (Exception e) {
   			log.error(e.getMessage());
   			throw e;
   		}
   		return systemConfig;
   	}
    
    
    
}
