package co.edu.usbcali.vas.dataaccess.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.vas.dataaccess.api.HibernateDaoImpl;
import co.edu.usbcali.vas.model.Video;


@Scope("singleton")
@Repository("VideoDAO")
public class VideoDAO extends HibernateDaoImpl<Video, Long> implements IVideoDAO {
    private static final Logger log = LoggerFactory.getLogger(VideoDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IVideoDAO getFromApplicationContext(ApplicationContext ctx) {
        return (IVideoDAO) ctx.getBean("VideoDAO");
    }
    
    
	@Override
	public Video getVideoByTransactionId(String transactionId) throws Exception {
		Video video = null;
		try {
			video = (Video) sessionFactory.getCurrentSession().getNamedQuery("getVideoByTransactionId")
					.setParameter("pTransactionId", transactionId)
					.getSingleResult();

		} catch (Exception e) {
			log.error("VideoDAO getVideoByTransactionId error ",e.getMessage());
		}
		return video;
	}
}
