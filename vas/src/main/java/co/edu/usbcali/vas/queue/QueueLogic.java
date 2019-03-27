package co.edu.usbcali.vas.queue;

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
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

import co.edu.usbcali.vas.model.control.ISystemParameterLogic;
import co.edu.usbcali.vas.model.dto.AlgorithmDTO;
import co.edu.usbcali.vas.utilities.Constantes;

@Scope("singleton")
@Service("QueueLogic")
public class QueueLogic implements IQueueLogic {
	private static final Logger log = LoggerFactory.getLogger(QueueLogic.class);

	@Autowired
	private ISystemParameterLogic systemParameterLogic;

	private String SQS_URL_HOST = "";
	private String SQS_ACCESS_KEYID = "";
	private String SQS_SECRETKEY = "";

	private BasicAWSCredentials credentials = null;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void sendMessage(AlgorithmDTO algorithmDTO) throws Exception {
		log.info("QueueLogic sendMessage");

		credentials = new BasicAWSCredentials(this.getSQS_ACCESS_KEYID(), this.getSQS_SECRETKEY());

		AmazonSQS sqs = new AmazonSQSClient(credentials);

		try {

			String jsonString = new JSONObject()
					.put("videoFileTemp", algorithmDTO.getVideoFileTemp())
					.put("videoFile", algorithmDTO.getVideoFile())
					.put("tempVideoFolder", algorithmDTO.getTempVideoFolder())
					.put("outputVideoFolder", algorithmDTO.getOutputVideoFolder())
					.put("externalProgramLocation", algorithmDTO.getExternalProgramLocation())
					.put("lenght", algorithmDTO.getLenght())
					.put("info", algorithmDTO.getInfo())
					.put("initTimeParam", algorithmDTO.getInitTimeParam())
					.put("finalTimeParam", algorithmDTO.getFinalTimeParam())
					.put("posXParam", algorithmDTO.getPosXParam())
					.put("posYParam", algorithmDTO.getPosYParam())
					.put("posX2Param", algorithmDTO.getPosX2Param())
					.put("posY2Param", algorithmDTO.getPosY2Param())
					.put("userId", algorithmDTO.getUserId())
					.toString();

			log.info("jsonString: "+jsonString);

			SendMessageRequest sendMessageRequest = new SendMessageRequest(this.getSQS_URL_HOST(), jsonString);

			sendMessageRequest.setMessageGroupId("messageGroup1");
			sendMessageRequest.setMessageDeduplicationId("" + System.nanoTime());

			SendMessageResult sendMessageResult = sqs.sendMessage(sendMessageRequest);
			String sequenceNumber = sendMessageResult.getSequenceNumber();
			String messageId = sendMessageResult.getMessageId();
			log.info("SendMessage succeed with messageId " + messageId + ", sequence number " + sequenceNumber + "\n");

			sqs.shutdown();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("QueueLogic sendMessage", e);
			throw e;
		}

	}

	public Boolean isMessageAvailable() throws Exception {
		log.info("QueueLogic isMessageAvailable");
		Boolean isMessageAvailable = false;
		try {
			//TODO
			
		} catch (Exception e) {
			log.error("isSftpAvailable error", e);
			isMessageAvailable = false;
		}
		return isMessageAvailable;

	}

	public String getSQS_SECRETKEY() {
		try {
			this.SQS_SECRETKEY = systemParameterLogic.getParamByCodeTexValue(Constantes.SQS_SECRETKEY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SQS_SECRETKEY;
	}

	public String getSQS_URL_HOST() {
		try {
			this.SQS_URL_HOST = systemParameterLogic.getParamByCodeTexValue(Constantes.SQS_URL_HOST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SQS_URL_HOST;
	}

	public String getSQS_ACCESS_KEYID() {
		try {
			this.SQS_ACCESS_KEYID = systemParameterLogic.getParamByCodeTexValue(Constantes.SQS_ACCESS_KEYID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SQS_ACCESS_KEYID;
	}
	
	public void setSQS_SECRETKEY(String sQS_SECRETKEY) {
		SQS_SECRETKEY = sQS_SECRETKEY;
	}

	public void setSQS_URL_HOST(String sQS_URL_HOST) {
		SQS_URL_HOST = sQS_URL_HOST;
	}

	public void setSQS_ACCESS_KEYID(String sQS_ACCESS_KEYID) {
		SQS_ACCESS_KEYID = sQS_ACCESS_KEYID;
	}

}
