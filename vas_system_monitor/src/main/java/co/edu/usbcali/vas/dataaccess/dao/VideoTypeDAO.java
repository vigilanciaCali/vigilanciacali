package co.edu.usbcali.vas.dataaccess.dao;

import co.edu.usbcali.vas.dataaccess.api.HibernateDaoImpl;
import co.edu.usbcali.vas.model.VideoType;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


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
}
