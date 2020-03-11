package co.edu.usbcali.vas.dataaccess.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.vas.dataaccess.api.HibernateDaoImpl;
import co.edu.usbcali.vas.model.VideoType;


@Scope("singleton")
@Repository("VideoTypeDAO")
public class VideoTypeDAO extends HibernateDaoImpl<VideoType, Integer>
    implements IVideoTypeDAO {
    private static final Logger log = LoggerFactory.getLogger(VideoTypeDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IVideoTypeDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IVideoTypeDAO) ctx.getBean("VideoTypeDAO");
    }
    
    @Override
   	public VideoType getVideoTypeByCode(String code) throws Exception {
    	VideoType videoType = null;
   		try {
   			videoType = (VideoType) sessionFactory.getCurrentSession().getNamedQuery("getVideoTypeByCode")
   					.setParameter("pCode", code).getSingleResult();

   		} catch (Exception e) {
   			log.error(e.getMessage());
   			throw e;
   		}
   		return videoType;
   	}
}
