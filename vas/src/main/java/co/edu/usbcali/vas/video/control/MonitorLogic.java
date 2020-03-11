package co.edu.usbcali.vas.video.control;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.vas.model.SystemParameter;
import co.edu.usbcali.vas.model.Users;
import co.edu.usbcali.vas.model.Video;
import co.edu.usbcali.vas.model.VideoStatus;
import co.edu.usbcali.vas.model.VideoTransaction;
import co.edu.usbcali.vas.model.VideoType;
import co.edu.usbcali.vas.model.control.ISystemParameterLogic;
import co.edu.usbcali.vas.model.control.IUsersLogic;
import co.edu.usbcali.vas.model.control.IVideoLogic;
import co.edu.usbcali.vas.model.control.IVideoStatusLogic;
import co.edu.usbcali.vas.model.control.IVideoTransactionLogic;
import co.edu.usbcali.vas.model.control.IVideoTypeLogic;
import co.edu.usbcali.vas.model.dto.VideoTransactionDTO;
import co.edu.usbcali.vas.monitor.email.control.rest.IEmailRestLogic;
import co.edu.usbcali.vas.queue.IQueueLogic;
import co.edu.usbcali.vas.trace.ISystemLogRestLogic;
import co.edu.usbcali.vas.utilities.Constantes;
import co.edu.usbcali.vas.utilities.Fechas;


@Scope("singleton")
@Service("MonitorLogic")
public class MonitorLogic implements IMonitorLogic {
    private static final Logger log = LoggerFactory.getLogger(MonitorLogic.class);

    
    @Autowired
   	private ISystemParameterLogic systemParameterLogic;
    
    @Autowired
   	private IQueueLogic queueLogic;
    
    @Autowired
   	private IVideoTransactionLogic videoTransactionLogic;
    
    @Autowired
   	private IVideoStatusLogic videoStatusLogic;
    
    @Autowired
   	private IVideoLogic videoLogic;
    
    @Autowired
   	private IVideoTypeLogic videoTypeLogic;
    
    @Autowired
   	private IUsersLogic usersLogic;
    @Autowired
   	private IEmailRestLogic emailRestLogic;
    @Autowired
	private ISystemLogRestLogic systemLogRestLogic;
    
    private Boolean ALG_RESULT = false;
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void processVideoAnalyticResult() throws Exception {
        log.info("2.0 VAS MonitorLogic processVideoAnalyticResult-------------------------------------------");

		try {
	
			VideoTransactionDTO videoTransactionDTOTemp = queueLogic.receiveFirstMessageFromVasOutQueue();
		        log.info("VAS DEQUEUE NEW MESSAGE VAS OUT");
		        
		        if(videoTransactionDTOTemp != null) {
		        	 log.info("getVideoTransactionId: " + videoTransactionDTOTemp.getVideoTransactionId());
		        	 //TRACE
		        	 systemLogRestLogic.save_systemLogRest("VAS", "12. processVideoAnalyticResult - receiveFirstMessageFromVasOutQueue: " 
		        	 		 + videoTransactionDTOTemp.getVideoTransactionId());
		        	 
		        	 this.updateVideoTransactionStatus(videoTransactionDTOTemp.getVideoTransactionId());
		        	 VideoTransaction videoTransaction = videoTransactionLogic.getVideoTransactionByTransactionId(videoTransactionDTOTemp.getVideoTransactionId());
		        	 
		        	 //TRACE
		        	 systemLogRestLogic.save_systemLogRest("VAS", "13. getVideoTransactionByTransactionId");
		        	 this.saveVideo(videoTransaction);
		        	 //TRACE
		        	 systemLogRestLogic.save_systemLogRest("VAS", "14. saveVideo");
		        
		        	//EMAIL USER
					this.sendMailProcessingFinished(videoTransaction);
					//TRACE
					systemLogRestLogic.save_systemLogRest("VAS", "15. sendMailProcessingFinished ");
		    
		        }
		       
		} catch (Exception e) {
			log.error("VAS MonitorLogic processVideoAnalyticResult", e);
			systemLogRestLogic.save_systemLogRest("VAS", "processVideoAnalyticResult ERROR: "+e);
		}
    }
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void processVideoAnalyticResultTest() throws Exception {
        log.info("2.0 VAS MonitorLogic processVideoAnalyticResult TEST-------------------------------------------");

		try {
	
			VideoTransactionDTO videoTransactionDTOTemp = queueLogic.receiveFirstMessageFromVasOutQueue();
		        log.info("VAS DEQUEUE NEW MESSAGE VAS OUT");
		        
		        if(videoTransactionDTOTemp != null) {
		        	 log.info("getVideoTransactionId: " + videoTransactionDTOTemp.getVideoTransactionId());
		        	 
		        	 this.updateVideoTransactionStatus(videoTransactionDTOTemp.getVideoTransactionId());
		        	 VideoTransaction videoTransaction = videoTransactionLogic.getVideoTransactionByTransactionId(videoTransactionDTOTemp.getVideoTransactionId());

		        	 this.saveVideo(videoTransaction);

		        }
		       
		} catch (Exception e) {
			log.error("VAS MonitorLogic processVideoAnalyticResult", e);
			systemLogRestLogic.save_systemLogRest("VAS", "processVideoAnalyticResult ERROR: "+e);
		}
    }
    
	@Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void startAlgResult() throws Exception {
        log.info("1.0 VAS MonitorLogic startAlgResult");

		try {
			
			SystemParameter parameter = systemParameterLogic.getSystemParamByCode(Constantes.ALG_RESULT);
			parameter.setBooleanValue(true);
			systemParameterLogic.updateSystemParameter(parameter);
			//TRACE
			systemLogRestLogic.save_systemLogRest("VAS", "startAlgResult");
			
		} catch (Exception e) {
			log.error("VAS MonitorLogic startAlgResult", e);
		
		}
    }
	
	@Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void stopAlgResult() throws Exception {
        log.info("1.0 VAS MonitorLogic stopAlgResult");

		try {
			
			SystemParameter parameter = systemParameterLogic.getSystemParamByCode(Constantes.ALG_RESULT);
			parameter.setBooleanValue(false);
			systemParameterLogic.updateSystemParameter(parameter);

		} catch (Exception e) {
			log.error("VAS MonitorLogic stopAlgResult", e);
		
		}
    }
	
	//Cron process to validate available vasOut request
	@Override
	public void validateAlgOutResult() throws Exception {
	        log.info("VAS MonitorLogic validateAlgOutResult");

			try {
				
				Boolean result = this.getALG_RESULT().booleanValue();
				
				if(isTestModeActivated().booleanValue() == true) {
					//TEST MODE
					log.info("-----------------------TEST MODE ON-------------------------------");

					this.processVideoAnalyticResultTest();
				}
				
				log.info("VAS MonitorLogic validateAlgOutResult: "+result);
				if(result.booleanValue() == true) {
				log.info("-----------------------TEST MODE OFF-------------------------------");
					this.processVideoAnalyticResult();
					this.stopAlgResult();
					log.info("VAS MonitorLogic validateAlgOutResult call process");
				}
		
			} catch (Exception e) {
				log.error("VAS MonitorLogic validateAnlAlgRequest", e);
			
			}
	    }
	
	//UPDATED TRANSACTION STATUS to processed
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateVideoTransactionStatus(String videoTransactionId) throws Exception {
        log.info("VAS MonitorLogic updateVideoTransaction");

		try {
			
			VideoTransaction entity = videoTransactionLogic.getVideoTransactionByTransactionId(videoTransactionId);

			entity.setUpdatedAt(new Date());
			entity.setUpdatedBy("system@vas.com");
  
    		VideoStatus videoStatus = videoStatusLogic.getVideoStatusByCode(Constantes.PROCESSED);
    		entity.setVideoStatus(videoStatus);
		
			videoTransactionLogic.updateVideoTransaction(entity);
			
			//systemLogRestLogic.save_systemLogRest("VAS", "updateVideoTransactionStatus");

		} catch (Exception e) {
			log.error("VAS MonitorLogic updateVideoTransaction", e);
			systemLogRestLogic.save_systemLogRest("VAS", "updateVideoTransactionStatus ERROR"+e);
		
		}
    }
    
  //UPDATED TRANSACTION STATUS to processed
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveVideo(VideoTransaction videoTransaction) throws Exception {
        log.info("VAS MonitorLogic saveVideo");

		try {
			
			Video entity = new Video();
			entity.setCreatedAt(new Date());
			entity.setCreatedBy("system@vas.com");
			entity.setUrl(videoTransaction.getUrl());
			entity.setUrlPicture(videoTransaction.getUrlPicture());
			entity.setDescription(this.generateVideoFileId()+"."+this.getVideo_format());
			entity.setAnalyticData(videoTransaction.getAnalyticData());
			
			Users user = usersLogic.getUsers(videoTransaction.getUsers().getId());
			entity.setUsers(user);
			
			VideoType videoType = videoTypeLogic.getVideoType(videoTransaction.getVideoType().getId());
			entity.setVideoType(videoType);
			
			VideoStatus videoStatus = videoStatusLogic.getVideoStatus(videoTransaction.getVideoStatus().getId());
			entity.setVideoStatus(videoStatus);
			
			log.info("getUrl: "+videoTransaction.getUrl());
			log.info("getUrlPicture: "+videoTransaction.getUrlPicture());
			log.info("getDescription: "+videoTransaction.getDescription());
			log.info("getAnalyticData: "+videoTransaction.getAnalyticData());
			log.info("videoTransaction.getUsers().getId(): "+videoTransaction.getUsers().getId());
			log.info("videoTransaction.getVideoType().getId()): "+videoTransaction.getVideoType().getId());
			log.info("videoTransaction.getVideoStatus().getId(): "+videoTransaction.getVideoStatus().getId());
			
			videoLogic.saveVideo(entity);
			
		} catch (Exception e) {
			log.error("VAS MonitorLogic saveVideo ERROR", e);
			systemLogRestLogic.save_systemLogRest("VAS", "saveVideo ERROR: "+videoTransaction.getDescription()+" Exception: "+e);
		
		}
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void sendMailProcessingFinished(VideoTransaction videoTransaction) throws Exception {
        log.info("VAS MonitorLogic sendMailProcessingFinished");

        Users user = null;
		try {
			
			user = usersLogic.getUsers(videoTransaction.getUsers().getId());
			if(user != null) {
				
				VideoTransactionDTO videoTransactionDTO = new VideoTransactionDTO();
				videoTransactionDTO.setUserEmail(user.getLogin());
				
				VideoType videoType = videoTypeLogic.getVideoType(videoTransaction.getVideoType().getId());
				videoTransactionDTO.setVideoType(videoType.getDescription());
				videoTransactionDTO.setAnalyticData(videoTransaction.getAnalyticData());
				videoTransactionDTO.setUrl(videoTransaction.getUrl());
				
				this.emailRestLogic.sendMailProcessingFinished(videoTransactionDTO);
			}
		
		} catch (Exception e) {
			log.error("VAS MonitorLogic sendMailProcessingFinished", e);
			systemLogRestLogic.save_systemLogRest("VAS", "sendMailProcessingFinished ERROR: "+user.getLogin()+" Exception: "+e);
		
		}
    }

	public Boolean getALG_RESULT() {
		try {
			this.ALG_RESULT = systemParameterLogic.getParamByCodeBooleanValue(Constantes.ALG_RESULT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ALG_RESULT;
	}

	public void setALG_RESULT(Boolean aLG_RESULT) {
		ALG_RESULT = aLG_RESULT;
	}
	
	public String getVideo_format() {
		String videoFormat  = "";
		try {
			videoFormat = systemParameterLogic.getParamByCodeTexValue(Constantes.VIDEO_FORMAT);
			log.info("getVideo_format: " + videoFormat);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return videoFormat;
	}

	public String generateVideoFileId() {
		String videoFile = null;
		videoFile = Fechas.dateToStr(Fechas.actualDateWithTimeStamp(), Constantes.FORMATO_FECHA_VIDEO);
		log.info("videoFile: " + videoFile);
		return videoFile;
	}
	
	public Boolean isTestModeActivated() {

		Boolean isTestModeActivated = false;
		try {
			isTestModeActivated = systemParameterLogic.getParamByCodeBooleanValue(Constantes.TEST_MODE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isTestModeActivated;
	}
	
  

	
    
    
    
    
    
    
    


}
