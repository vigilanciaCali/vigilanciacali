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
	public void sendMessageAnlQueue(AlgorithmDTO algorithmDTO) throws Exception {
		log.info("VAS QueueLogic sendMessage");

		credentials = new BasicAWSCredentials(this.getSQS_ANL_ACCESS_KEYID(), this.getSQS_ANL_SECRETKEY());

		AmazonSQS sqs = new AmazonSQSClient(credentials);

		try {

			String jsonString = new JSONObject()
					.put("videoTransactionId", algorithmDTO.getVideoTransactionId())
					.put("videoFileTemp", algorithmDTO.getVideoFileTemp())
					.put("videoFile", algorithmDTO.getVideoFile())
					.put("pictureFile", algorithmDTO.getPictureFile())
					.put("tempVideoFolder", algorithmDTO.getTempVideoFolder())
					.put("outputVideoFolder", algorithmDTO.getOutputVideoFolder())
					.put("url", algorithmDTO.getUrl())
					.put("lenght", algorithmDTO.getLenght())
					.put("info", algorithmDTO.getInfo())
					.put("initTimeParam", algorithmDTO.getInitTimeParam())
					.put("finalTimeParam", algorithmDTO.getFinalTimeParam())
					.put("posXParam", algorithmDTO.getPosXParam())
					.put("posYParam", algorithmDTO.getPosYParam())
					.put("posX2Param", algorithmDTO.getPosX2Param())
					.put("posY2Param", algorithmDTO.getPosY2Param())
					.put("userId", algorithmDTO.getUserId())
					.put("userEmail", algorithmDTO.getUserEmail())
					.toString();

			log.info("jsonString: "+jsonString);

			SendMessageRequest sendMessageRequest = new SendMessageRequest(this.getSQS_ANL_URL_HOST(), jsonString);

			sendMessageRequest.setMessageGroupId("messageGroup1");
			sendMessageRequest.setMessageDeduplicationId("" + System.nanoTime());

			SendMessageResult sendMessageResult = sqs.sendMessage(sendMessageRequest);
			String sequenceNumber = sendMessageResult.getSequenceNumber();
			String messageId = sendMessageResult.getMessageId();
			log.info("SendMessage succeed with messageId " + messageId + ", sequence number " + sequenceNumber + "\n");

			sqs.shutdown();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("VAS QueueLogic sendMessage", e);
			throw e;
		}

	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void sendMessageTrcQueue(AlgorithmDTO algorithmDTO) throws Exception {
		log.info("VAS QueueLogic sendMessageTrcQueue");

		credentials = new BasicAWSCredentials(this.getSQS_TRC_ACCESS_KEYID(), this.getSQS_TRC_SECRETKEY());

		AmazonSQS sqs = new AmazonSQSClient(credentials);

		try {

			String jsonString = new JSONObject()
					.put("videoTransactionId", algorithmDTO.getVideoTransactionId())
					.put("videoFileTemp", algorithmDTO.getVideoFileTemp())
					.put("videoFile", algorithmDTO.getVideoFile())
					.put("tempVideoFolder", algorithmDTO.getTempVideoFolder())
					.put("outputVideoFolder", algorithmDTO.getOutputVideoFolder())
					.put("url", algorithmDTO.getUrl())
					.put("lenght", algorithmDTO.getLenght())
					.put("info", algorithmDTO.getInfo())
					.put("initTimeParam", algorithmDTO.getInitTimeParam())
					.put("finalTimeParam", algorithmDTO.getFinalTimeParam())
					//CONFIGURACIÓN PARAMETROS
					//X1
					.put("posXParam", algorithmDTO.getPosXParam())
					//Y1
					.put("posYParam", algorithmDTO.getPosYParam())
					//X2 width
					.put("posX2Param", algorithmDTO.getPosX2Param())
					//Y2 hight
					.put("posY2Param", algorithmDTO.getPosY2Param())
					//
					.put("userId", algorithmDTO.getUserId())
					.put("userEmail", algorithmDTO.getUserEmail())
					.toString();

			log.info("jsonString: "+jsonString);

			SendMessageRequest sendMessageRequest = new SendMessageRequest(this.getSQS_TRC_URL_HOST(), jsonString);

			sendMessageRequest.setMessageGroupId("messageGroup1");
			sendMessageRequest.setMessageDeduplicationId("" + System.nanoTime());

			SendMessageResult sendMessageResult = sqs.sendMessage(sendMessageRequest);
			String sequenceNumber = sendMessageResult.getSequenceNumber();
			String messageId = sendMessageResult.getMessageId();
			log.info("sendMessageTrcQueue succeed with messageId " + messageId + ", sequence number " + sequenceNumber + "\n");

			sqs.shutdown();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("VAS QueueLogic sendMessageTrcQueue", e);
			throw e;
		}

	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public VideoTransactionDTO receiveFirstMessageFromVasOutQueue() throws Exception {
		log.info("VAS QueueLogic receiveFirstMessageFromVasOutQueue");

		credentials = new BasicAWSCredentials(this.getSQS_OUT_ACCESS_KEYID(), this.getSQS_OUT_SECRETKEY());
		AmazonSQS sqs = new AmazonSQSClient(credentials);
		VideoTransactionDTO videoTransactionDTO = new VideoTransactionDTO();
		try {
			
			List<Message> messagesList = null;
			messagesList = sqs.receiveMessage(new ReceiveMessageRequest(this.getSQS_OUT_URL_HOST())).getMessages();
				
			if(messagesList.isEmpty() == false && messagesList.size() > 0) {
				
				log.info("messagesList.size(): "+messagesList.size());
				
				Message msgFirst = messagesList.get(messagesList.size()-1);

				JSONObject object = new JSONObject(msgFirst.getBody());
	
				videoTransactionDTO.setVideoTransactionId(object.get("videoTransactionId").toString().trim());
				videoTransactionDTO.setDescription(object.get("description").toString().trim());
				videoTransactionDTO.setId_Users(Integer.valueOf(object.get("userId").toString()));
				videoTransactionDTO.setId_VideoType(Integer.valueOf(object.get("typeId").toString()));
				videoTransactionDTO.setId_VideoStatus(Integer.valueOf(object.get("statusId").toString()));
				videoTransactionDTO.setUrl(object.get("url").toString().trim().toLowerCase());
				
				log.info("VAS QueueLogic receiveFirstMessageFromVasOutQueue: "+videoTransactionDTO.getVideoTransactionId());
				//log.info("algorithmDTO.getVideoId: "+algorithmDTO.getVideoId().toString());
				
				// Delete a message
				log.info("Deleting a message.\n");
				String messageRecieptHandle = msgFirst.getReceiptHandle();
				sqs.deleteMessage(new DeleteMessageRequest(this.getSQS_OUT_URL_HOST(), messageRecieptHandle));
				
			}else {
				videoTransactionDTO = null;
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error("VAS QueueLogic receiveFirstMessageFromVasOutQueue", e);
			throw e;
		}
		return videoTransactionDTO;
		
		
	}

	public Boolean isMessageAvailable() throws Exception {
		log.info("VAS QueueLogic isMessageAvailable");
		Boolean isMessageAvailable = false;
		try {
			//TODO
			
		} catch (Exception e) {
			log.error("VAS isMessageAvailable error", e);
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
