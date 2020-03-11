package co.edu.usbcali.vas.notificator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("singleton")
@Service("ServiceNotificatorLogic")
public class ServiceNotificatorLogic implements IServiceNotificatorLogic {
    private static final Logger log = LoggerFactory.getLogger(ServiceNotificatorLogic.class);



   /* @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveSystemVideoLog(SystemVideoLog entity)
        throws Exception {
        log.debug("saving SystemVideoLog instance");

        try {
            if (entity.getActionDate() == null) {
                throw new ZMessManager().new EmptyFieldException("actionDate");
            }

            if (entity.getActionName() == null) {
                throw new ZMessManager().new EmptyFieldException("actionName");
            }

            if ((entity.getIp() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getIp(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("ip");
            }

            if (entity.getUserId() == null) {
                throw new ZMessManager().new EmptyFieldException("userId");
            }

            if ((entity.getUserName() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUserName(), 255) == false)) {
                throw new ZMessManager().new NotValidFormatException("userName");
            }

            systemVideoLogDAO.save(entity);

            log.debug("save SystemVideoLog successful");
        } catch (Exception e) {
            log.error("save SystemVideoLog failed", e);
            throw e;
        } finally {
        }
    }*/

    
}
