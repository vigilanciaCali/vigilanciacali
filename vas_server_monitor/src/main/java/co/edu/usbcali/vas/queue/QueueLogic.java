package co.edu.usbcali.vas.queue;

import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

import co.edu.usbcali.vas.model.control.ISystemParameterLogic;
import co.edu.usbcali.vas.model.dto.AlgorithmDTO;
import co.edu.usbcali.vas.model.dto.VideoTransactionDTO;
import co.edu.usbcali.vas.utilities.Constantes;

@Scope("singleton")
@Service("QueueLogic")
public class QueueLogic implements IQueueLogic {
	private static final Logger log = LoggerFactory.getLogger(QueueLogic.class);

	@Autowired
	private ISystemParameterLogic systemParameterLogic;

	//ANL QUEUE
		private String SQS_ANL_URL_HOST = "";
		private String SQS_ANL_ACCESS_KEYID = "";
		private String SQS_ANL_SECRETKEY = "";
		//TRC QUEUE
		private String SQS_TRC_URL_HOST = "";
		private String SQS_TRC_ACCESS_KEYID = "";
		private String SQS_TRC_SECRETKEY = "";
		//OUT QUEUE
		private String SQS_OUT_URL_HOST = "";
		private String SQS_OUT_ACCESS_KEYID = "";
		private String SQS_OUT_SECRETKEY = "";

	private BasicAWSCredentials credentials = null;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void sendMessageToVasOutQueue(VideoTransactionDTO videoTransactionDTO) throws Exception {
		log.info(" VAS_SERVER_MONITOR QueueLogic sendMessageToVasOutQueue");

		credentials = new BasicAWSCredentials(this.getSQS_OUT_ACCESS_KEYID(), this.getSQS_OUT_SECRETKEY());

		AmazonSQS sqs = new AmazonSQSClient(credentials);

		try {

			String jsonString = new JSONObject()
					.put("videoTransactionId", videoTransactionDTO.getVideoTransactionId())
					.put("description", videoTransactionDTO.getDescription())
					//.put("id", videoTransactionDTO.getId().toString())
					.put("url", videoTransactionDTO.getUrl())
					.put("userId", videoTransactionDTO.getId_Users().toString())
					.put("typeId", videoTransactionDTO.getId_VideoType().toString())
					.put("statusId", videoTransactionDTO.getId_VideoStatus().toString())
					.toString();

			log.info("jsonString: "+jsonString);

			SendMessageRequest sendMessageRequest = new SendMessageRequest(this.getSQS_OUT_URL_HOST(), jsonString);

			sendMessageRequest.setMessageGroupId("messageGroup1");
			sendMessageRequest.setMessageDeduplicationId("" + System.nanoTime());

			SendMessageResult sendMessageResult = sqs.sendMessage(sendMessageRequest);
			String sequenceNumber = sendMessageResult.getSequenceNumber();
			String messageId = sendMessageResult.getMessageId();
			log.info("SendMessage succeed with messageId " + messageId + ", sequence number " + sequenceNumber + "\n");

			sqs.shutdown();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("VAS_SERVER_MONITOR QueueLogic sendMessageToVasOutQueue", e);
			throw e;
		}

	}
	

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public AlgorithmDTO receiveFirstMessageAnlQueue() throws Exception {
		log.info("VAS_SERVER_MONITOR QueueLogic receiveFirstMessageAnlQueue");

		credentials = new BasicAWSCredentials(this.getSQS_ANL_ACCESS_KEYID(), this.getSQS_ANL_SECRETKEY());
		AmazonSQS sqs = new AmazonSQSClient(credentials);
		AlgorithmDTO algorithmDTO = new AlgorithmDTO();
		try {
			
			List<Message> messagesList = null;
			messagesList = sqs.receiveMessage(new ReceiveMessageRequest(this.getSQS_ANL_URL_HOST())).getMessages();
				
			if(messagesList.isEmpty() == false && messagesList.size() > 0) {
				
				log.info("messagesList.size(): "+messagesList.size());
				
				Message msgFirst = messagesList.get(messagesList.size()-1);

				JSONObject object = new JSONObject(msgFirst.getBody());
	
				algorithmDTO.setVideoTransactionId(object.get("videoTransactionId").toString());
				algorithmDTO.setVideoFileTemp(object.get("videoFileTemp").toString());
				algorithmDTO.setVideoFile(object.get("videoFile").toString());
				algorithmDTO.setTempVideoFolder(object.get("tempVideoFolder").toString());
				algorithmDTO.setOutputVideoFolder(object.get("outputVideoFolder").toString());
				algorithmDTO.setUrl(object.get("url").toString());
				algorithmDTO.setLenght(object.get("lenght").toString());
				algorithmDTO.setInitTimeParam(object.get("initTimeParam").toString());
				algorithmDTO.setFinalTimeParam(object.get("finalTimeParam").toString());
				algorithmDTO.setUserId(object.get("userId").toString());
				algorithmDTO.setUserEmail(object.get("userEmail").toString());
				
				log.info("VAS_SERVER_MONITOR QueueLogic receiveFirstMessageAnlQueue: "+algorithmDTO.getVideoTransactionId());
				//log.info("algorithmDTO.getVideoId: "+algorithmDTO.getVideoId().toString());
				
				// Delete a message
				log.info("Deleting a message.\n");
				String messageRecieptHandle = msgFirst.getReceiptHandle();
				sqs.deleteMessage(new DeleteMessageRequest(this.getSQS_ANL_URL_HOST(), messageRecieptHandle));
				
			}else {
				algorithmDTO = null;
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error("VAS_SERVER_MONITOR QueueLogic receiveFirstMessageAnlQueue", e);
			throw e;
		}
		
		return algorithmDTO;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public AlgorithmDTO receiveFirstMessageTrcQueue() throws Exception {
		log.info("VAS_SERVER_MONITOR QueueLogic receiveFirstMessageTrcQueue");

		credentials = new BasicAWSCredentials(this.getSQS_TRC_ACCESS_KEYID(), this.getSQS_TRC_SECRETKEY());
		AmazonSQS sqs = new AmazonSQSClient(credentials);
		AlgorithmDTO algorithmDTO = new AlgorithmDTO();
		try {
			
			List<Message> messagesList = null;
			messagesList = sqs.receiveMessage(new ReceiveMessageRequest(this.getSQS_TRC_URL_HOST())).getMessages();
				
			if(messagesList.isEmpty() == false && messagesList.size() > 0) {
				
				log.info("messagesList.size(): "+messagesList.size());
				
				Message msgFirst = messagesList.get(messagesList.size()-1);

				JSONObject object = new JSONObject(msgFirst.getBody());
	
				algorithmDTO.setVideoTransactionId(object.get("videoTransactionId").toString());
				algorithmDTO.setVideoFileTemp(object.get("videoFileTemp").toString());
				algorithmDTO.setVideoFile(object.get("videoFile").toString());
				algorithmDTO.setTempVideoFolder(object.get("tempVideoFolder").toString());
				algorithmDTO.setOutputVideoFolder(object.get("outputVideoFolder").toString());
				algorithmDTO.setUrl(object.get("url").toString());
				algorithmDTO.setLenght(object.get("lenght").toString());
				//PARAMS
				algorithmDTO.setPosXParam(object.get("posXParam").toString());
				algorithmDTO.setPosYParam(object.get("posYParam").toString());
				algorithmDTO.setPosX2Param(object.get("posX2Param").toString());
				algorithmDTO.setPosY2Param(object.get("posY2Param").toString());
				//USER
				algorithmDTO.setUserId(object.get("userId").toString());
				algorithmDTO.setUserEmail(object.get("userEmail").toString());
				
				log.info("algorithmDTO.videoFile: "+algorithmDTO.getVideoTransactionId());
				//log.info("algorithmDTO.getVideoId: "+algorithmDTO.getVideoId().toString());
				
				// Delete a message
				log.info("Deleting a message.\n");
				String messageRecieptHandle = msgFirst.getReceiptHandle();
				sqs.deleteMessage(new DeleteMessageRequest(this.getSQS_TRC_URL_HOST(), messageRecieptHandle));
				
			
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error("VAS_SERVER_MONITOR QueueLogic receiveFirstMessageTrcQueue", e);
			throw e;
		}
		
		return algorithmDTO;
	}

	public Boolean isMessageAvailableAnlQueue() throws Exception {
		log.info("QueueLogic isMessageAvailableAnlQueue");
		Boolean isMessageAvailable = false;
		try {

			credentials = new BasicAWSCredentials(this.getSQS_ANL_ACCESS_KEYID(), this.getSQS_ANL_SECRETKEY());
			AmazonSQS sqs = new AmazonSQSClient(credentials);

			List<Message> messagesList = null;

			messagesList = sqs.receiveMessage(new ReceiveMessageRequest(this.getSQS_ANL_URL_HOST())).getMessages();

			if (messagesList.size() > 0) {
				isMessageAvailable = true;
			}

		} catch (Exception e) {
			log.error("isSftpAvailable error", e);
			isMessageAvailable = false;
		}
		return isMessageAvailable;
	}
	
	public Boolean isMessageAvailableTrcQueue() throws Exception {
		log.info("QueueLogic isMessageAvailableTrcQueue");
		Boolean isMessageAvailable = false;
		try {

			credentials = new BasicAWSCredentials(this.getSQS_TRC_ACCESS_KEYID(), this.getSQS_TRC_SECRETKEY());
			AmazonSQS sqs = new AmazonSQSClient(credentials);

			List<Message> messagesList = null;

			messagesList = sqs.receiveMessage(new ReceiveMessageRequest(this.getSQS_TRC_URL_HOST())).getMessages();

			if (messagesList.size() > 0) {
				isMessageAvailable = true;
			}

		} catch (Exception e) {
			log.error("QueueLogic isMessageAvailableTrcQueue error", e);
			isMessageAvailable = false;
		}
		return isMessageAvailable;
	}
	

	public String getSQS_ANL_URL_HOST() {
		try {
			this.SQS_ANL_URL_HOST = systemParameterLogic.getParamByCodeTexValue(Constantes.SQS_ANL_URL_HOST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SQS_ANL_URL_HOST;
	}

	public String getSQS_ANL_ACCESS_KEYID() {
		try {
			this.SQS_ANL_ACCESS_KEYID = systemParameterLogic.getParamByCodeTexValue(Constantes.SQS_ANL_ACCESS_KEYID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SQS_ANL_ACCESS_KEYID;
	}

	public String getSQS_ANL_SECRETKEY() {
		try {
			this.SQS_ANL_SECRETKEY = systemParameterLogic.getParamByCodeTexValue(Constantes.SQS_ANL_SECRETKEY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SQS_ANL_SECRETKEY;
	}

	public String getSQS_TRC_URL_HOST() {
		try {
			this.SQS_TRC_URL_HOST = systemParameterLogic.getParamByCodeTexValue(Constantes.SQS_TRC_URL_HOST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SQS_TRC_URL_HOST;
	}

	public String getSQS_TRC_ACCESS_KEYID() {
		try {
			this.SQS_TRC_ACCESS_KEYID = systemParameterLogic.getParamByCodeTexValue(Constantes.SQS_TRC_ACCESS_KEYID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SQS_TRC_ACCESS_KEYID;
	}

	public String getSQS_TRC_SECRETKEY() {
		try {
			this.SQS_TRC_SECRETKEY = systemParameterLogic.getParamByCodeTexValue(Constantes.SQS_TRC_SECRETKEY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SQS_TRC_SECRETKEY;
	}

	public String getSQS_OUT_URL_HOST() {
		try {
			this.SQS_OUT_URL_HOST = systemParameterLogic.getParamByCodeTexValue(Constantes.SQS_OUT_URL_HOST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SQS_OUT_URL_HOST;
	}

	public String getSQS_OUT_ACCESS_KEYID() {
		try {
			this.SQS_OUT_ACCESS_KEYID = systemParameterLogic.getParamByCodeTexValue(Constantes.SQS_OUT_ACCESS_KEYID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SQS_OUT_ACCESS_KEYID;
	}

	public String getSQS_OUT_SECRETKEY() {
		try {
			this.SQS_OUT_SECRETKEY = systemParameterLogic.getParamByCodeTexValue(Constantes.SQS_OUT_SECRETKEY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SQS_OUT_SECRETKEY;
	}

	public void setSQS_ANL_URL_HOST(String sQS_ANL_URL_HOST) {
		SQS_ANL_URL_HOST = sQS_ANL_URL_HOST;
	}

	public void setSQS_ANL_ACCESS_KEYID(String sQS_ANL_ACCESS_KEYID) {
		SQS_ANL_ACCESS_KEYID = sQS_ANL_ACCESS_KEYID;
	}

	public void setSQS_ANL_SECRETKEY(String sQS_ANL_SECRETKEY) {
		SQS_ANL_SECRETKEY = sQS_ANL_SECRETKEY;
	}

	public void setSQS_TRC_URL_HOST(String sQS_TRC_URL_HOST) {
		SQS_TRC_URL_HOST = sQS_TRC_URL_HOST;
	}

	public void setSQS_TRC_ACCESS_KEYID(String sQS_TRC_ACCESS_KEYID) {
		SQS_TRC_ACCESS_KEYID = sQS_TRC_ACCESS_KEYID;
	}

	public void setSQS_TRC_SECRETKEY(String sQS_TRC_SECRETKEY) {
		SQS_TRC_SECRETKEY = sQS_TRC_SECRETKEY;
	}

	public void setSQS_OUT_URL_HOST(String sQS_OUT_URL_HOST) {
		SQS_OUT_URL_HOST = sQS_OUT_URL_HOST;
	}

	public void setSQS_OUT_ACCESS_KEYID(String sQS_OUT_ACCESS_KEYID) {
		SQS_OUT_ACCESS_KEYID = sQS_OUT_ACCESS_KEYID;
	}

	public void setSQS_OUT_SECRETKEY(String sQS_OUT_SECRETKEY) {
		SQS_OUT_SECRETKEY = sQS_OUT_SECRETKEY;
	}

}
