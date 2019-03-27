package co.edu.usbcali.vas.dataaccess.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.vas.dataaccess.api.HibernateDaoImpl;
import co.edu.usbcali.vas.model.Users;


@Scope("singleton")
@Repository("UsersDAO")
public class UsersDAO extends HibernateDaoImpl<Users, Integer>
    implements IUsersDAO {
    private static final Logger log = LoggerFactory.getLogger(UsersDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IUsersDAO getFromApplicationContext(ApplicationContext ctx) {
        return (IUsersDAO) ctx.getBean("UsersDAO");
    }
    
    @Override
  	public Users getUserByLogin(String login) throws Exception {
    	Users user = null;
  		try {
  			user = (Users) sessionFactory.getCurrentSession().getNamedQuery("getUserByLogin")
  	  				.setParameter("pLogin", login).getSingleResult();
  	  		
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return user;	
  	}
}
