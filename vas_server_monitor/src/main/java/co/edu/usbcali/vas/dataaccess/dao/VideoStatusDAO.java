package co.edu.usbcali.vas.dataaccess.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.vas.dataaccess.api.HibernateDaoImpl;
import co.edu.usbcali.vas.model.VideoStatus;


/**
 * A data access object (DAO) providing persistence and search support for
 * VideoStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.VideoStatus
 */
@Scope("singleton")
@Repository("VideoStatusDAO")
public class VideoStatusDAO extends HibernateDaoImpl<VideoStatus, Integer>
    implements IVideoStatusDAO {
    private static final Logger log = LoggerFactory.getLogger(VideoStatusDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IVideoStatusDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IVideoStatusDAO) ctx.getBean("VideoStatusDAO");
    }
    
    @Override
   	public VideoStatus getVideoStatusByCode(String code) throws Exception {
    	VideoStatus videoStatus = null;
   		try {
   			videoStatus = (VideoStatus) sessionFactory.getCurrentSession().getNamedQuery("getVideoStatusByCode")
   					.setParameter("pCode", code).getSingleResult();

   		} catch (Exception e) {
   			log.error(e.getMessage());
   			throw e;
   		}
   		return videoStatus;
   	}
    
}
