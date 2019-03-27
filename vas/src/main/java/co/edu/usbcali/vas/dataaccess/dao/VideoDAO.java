package co.edu.usbcali.vas.dataaccess.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.vas.dataaccess.api.HibernateDaoImpl;
import co.edu.usbcali.vas.model.Video;


/**
 * A data access object (DAO) providing persistence and search support for
 * Video entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.Video
 */
@Scope("singleton")
@Repository("VideoDAO")
public class VideoDAO extends HibernateDaoImpl<Video, Long> implements IVideoDAO {
    private static final Logger log = LoggerFactory.getLogger(VideoDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IVideoDAO getFromApplicationContext(ApplicationContext ctx) {
        return (IVideoDAO) ctx.getBean("VideoDAO");
    }
    
    @SuppressWarnings("unchecked")
	@Override
  	public List<Video> getVideoDataByIdASC() throws Exception {
    	List<Video> video = null;
  		try {
  			video = (List<Video>) sessionFactory.getCurrentSession().getNamedQuery("getVideoDataByIdASC")
  	  				.getResultList();
  	  		
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return video;	
  	}
    
    @SuppressWarnings("unchecked")
	@Override
  	public List<Video> getVideoDataByTrackerById() throws Exception {
    	List<Video> video = null;
  		try {
  			video = (List<Video>) sessionFactory.getCurrentSession().getNamedQuery("getVideoDataByTracker")
  	  				.getResultList();
  	  		
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return video;	
  	}
    
    @SuppressWarnings("unchecked")
   	@Override
     	public List<Video> getVideoDataByAnomalousById() throws Exception {
       	List<Video> video = null;
     		try {
     			video = (List<Video>) sessionFactory.getCurrentSession().getNamedQuery("getVideoDataByAnomalous")
     	  				.getResultList();
     	  		
   		} catch (Exception e) {
   			log.error(e.getMessage());
   		}
   		return video;	
     	}
    
    
    
}
