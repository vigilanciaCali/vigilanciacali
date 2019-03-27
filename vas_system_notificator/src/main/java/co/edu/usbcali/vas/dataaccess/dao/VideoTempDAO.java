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
import co.edu.usbcali.vas.model.VideoTemp;


@Scope("singleton")
@Repository("VideoTempDAO")
public class VideoTempDAO extends HibernateDaoImpl<VideoTemp, Long>
    implements IVideoTempDAO {
    private static final Logger log = LoggerFactory.getLogger(VideoTempDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IVideoTempDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IVideoTempDAO) ctx.getBean("VideoTempDAO");
    }
    
    @SuppressWarnings("unchecked")
	@Override
  	public List<VideoTemp> getVideoTempDataByIdASC() throws Exception {
    	List<VideoTemp> video = null;
  		try {
  			video = (List<VideoTemp>) sessionFactory.getCurrentSession().getNamedQuery("getVideoTempDataByIdASC")
  	  				.getResultList();
  	  		
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return video;	
  	}
    
    @SuppressWarnings("unchecked")
	@Override
  	public List<VideoTemp> getDataVideoTempTracker() throws Exception {
    	List<VideoTemp> video = null;
  		try {
  			video = (List<VideoTemp>) sessionFactory.getCurrentSession().getNamedQuery("getDataVideoTempTracker")
  	  				.getResultList();
  	  		
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return video;	
  	}
    
    @SuppressWarnings("unchecked")
	@Override
  	public List<VideoTemp> getDataVideoTempAnomalous() throws Exception {
    	List<VideoTemp> video = null;
  		try {
  			video = (List<VideoTemp>) sessionFactory.getCurrentSession().getNamedQuery("getDataVideoTempAnomalous")
  	  				.getResultList();
  	  		
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return video;	
  	}
    
    
    
}
