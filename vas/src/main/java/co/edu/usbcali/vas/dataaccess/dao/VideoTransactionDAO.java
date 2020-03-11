package co.edu.usbcali.vas.dataaccess.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.vas.dataaccess.api.HibernateDaoImpl;
import co.edu.usbcali.vas.model.VideoTransaction;

@Scope("singleton")
@Repository("VideoTransactionDAO")
public class VideoTransactionDAO extends HibernateDaoImpl<VideoTransaction, Long>
    implements IVideoTransactionDAO {
    private static final Logger log = LoggerFactory.getLogger(VideoTransactionDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IVideoTransactionDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IVideoTransactionDAO) ctx.getBean("VideoTransactionDAO");
    }
    
    @Override
	public VideoTransaction getVideoTransactionByTransactionId(String videoTransactionId) throws Exception {
    	VideoTransaction video = null;
		try {
			video = (VideoTransaction) sessionFactory.getCurrentSession().getNamedQuery("getVideoTransactionByTransactionId")
					.setParameter("pVideoTransactionId", videoTransactionId)
					.getSingleResult();

		} catch (Exception e) {
			log.error("VAS VideoTransactionDAO error ",e.getMessage());
		}
		return video;
	}
    
    
}
