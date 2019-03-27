package co.edu.usbcali.vas.mail;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.annotation.PostConstruct;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import co.edu.usbcali.vas.model.MailServer;
import co.edu.usbcali.vas.model.SystemMailLog;
import co.edu.usbcali.vas.model.control.IMailServerLogic;
import co.edu.usbcali.vas.model.control.ISystemMailLogLogic;

@Scope("singleton")
@Service("EmailControllerLogic")
public class EmailController implements IEmailController {
	private static final Logger log = LoggerFactory.getLogger(EmailController.class);
	
	@Autowired
	private IMailServerLogic mailServerLogic;
	
	@Autowired
	private ISystemMailLogLogic systemMailLogLogic;

	private String from;
	private String host;
	private String port;
	private String password;

	private Properties properties;

	private MimeMessage message;
	private BodyPart messageBodyPart;
	private MimeBodyPart attachmentBodyPart = new MimeBodyPart();
	private Multipart multipart;
	private Transport transport;
	private Authenticator authenticator;
	
	private StreamedContent fileAttached;
	
	MailServer mailServer;

	private Properties mailProperties() throws Exception {
		
		// Consulta tabla parametros
		/*host = parameterLogic.getParamByCodeTexValue("SMTP");
		port = parameterLogic.getParamByCodeTexValue("SMTPPORT");
		from = parameterLogic.getParamByCodeTexValue("SMTPUSER");
		password = parameterLogic.getParamByCodeTexValue("SMTPPWD");*/

		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		

		authenticator = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				try {

				} catch (Exception e) {
					log.error("EmailController: authenticator PasswordAuthentication  failed", e);
				}
				return new PasswordAuthentication(from, password);
			}
		};

		return properties;

	}

	public synchronized void send(String to, String subject, String content) throws Exception {
		try {

			multipart = new MimeMultipart();
			messageBodyPart = new MimeBodyPart();
			properties = new Properties();
			transport = null;
			message = null;

			messageBodyPart.setContent(content.toString(), "text/html;charset=utf-8");
			multipart.addBodyPart(messageBodyPart);

			Session session = Session.getDefaultInstance(mailProperties(), authenticator);
			message = null;
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setContent(multipart);

			transport = session.getTransport("smtp");
			//LOG 
			saveSystemLog("MAIL SENT", to);
		} catch (NoSuchProviderException e) {
			//LOG 
			saveSystemLog("MAIL ERROR PROVIDER", to);
			log.error("EmailController: Provider Error" + e.getMessage());
		} catch (MessagingException e) {
			//LOG 
			saveSystemLog("MAIL ERROR SENDER", to);
			log.error("EmailController: Sending Error " + e.getMessage());
		} finally {
			thread(transport, message, to);
		}
	}
	
/*	public synchronized void sendWithAttach(String to, String subject, String content, ReportDTO report) throws Exception {
		try {
			
			multipart = new MimeMultipart();
			messageBodyPart = new MimeBodyPart();
			properties = new Properties();
			transport = null;
			message = null;
			
			attachmentBodyPart = new MimeBodyPart();
			ByteArrayDataSource rawData = new ByteArrayDataSource(report.getFile(), "application/pdf");
			
			attachmentBodyPart.setDataHandler(new DataHandler(rawData));
			attachmentBodyPart.setFileName(report.getName());
			multipart.addBodyPart(attachmentBodyPart);
			
			messageBodyPart.setContent(content.toString(), "text/html;charset=utf-8");
			multipart.addBodyPart(messageBodyPart);
			
			Session session = Session.getDefaultInstance(mailProperties(), authenticator);
			message = null;
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setContent(multipart);
			
			transport = session.getTransport("smtp"); 
			//LOG 
			saveSystemLog("MAIL SENT", to);
		} catch (NoSuchProviderException e) {
			//LOG 
			saveSystemLog("MAIL ERROR PROVIDER", to);
			log.error("EmailController:sendWithAttach Provider Error" + to);
		} catch (MessagingException e) {
			//LOG 
			saveSystemLog("MAIL ERROR SENDER", to);
			log.error("EmailController:sendWithAttach Sending Error" + to);
		} finally {
			thread(transport, message, to);
		}
	}
	*/
	/*	public synchronized void sendWithAttach(String to, String subject, String content, List<CustomerOrderPictureDTO> adjuntos) throws Exception {
	try {
		
		multipart = new MimeMultipart();
		messageBodyPart = new MimeBodyPart();
		properties = new Properties();
		transport = null;
		message = null;
		
		if(adjuntos != null){
			for (CustomerOrderPictureDTO adjuntoDTO : adjuntos) {
				try {
					
					attachmentBodyPart = new MimeBodyPart();
					ByteArrayDataSource rawData = new ByteArrayDataSource(null, content);
					attachmentBodyPart.setDataHandler(new DataHandler(rawData));
					attachmentBodyPart.setFileName(adjuntoDTO.getName());
					multipart.addBodyPart(attachmentBodyPart);
					
				} catch (IOException e) {e.printStackTrace();} catch (SQLException e) {e.printStackTrace();}	
			}
		}
		
		messageBodyPart.setContent(content.toString(), "text/html;charset=utf-8");
		multipart.addBodyPart(messageBodyPart);
		
		Session session = Session.getDefaultInstance(mailProperties(), authenticator);
		message = null;
		message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);
		message.setContent(multipart);
		
		transport = session.getTransport("smtp");  
	} catch (NoSuchProviderException e) {
		log.error("### PROVIDER: ERROR " + e.getMessage());
	} catch (MessagingException e) {
		log.error("### EMAIL: NO SE PUDO ENVIAR EL MENSAJE. ERROR " + e.getMessage());
	} finally {
		thread(transport, message, to);
	}
}*/

	public synchronized static void thread(final Transport transport, final Message msg, final String to) {
		new Thread() {
			public void run() {
				try {
					transport.connect();
					Transport.send(msg);
					
					log.info("EmailController: Email Sent to: " + to);
					
				} catch (MessagingException e) {
					
					log.error("EmailController: Email send error " + e.getMessage());
	
				} finally {
					try {
						if (transport.isConnected()) {
							transport.close();
						}
					} catch (MessagingException e) {
						log.error("EmailController: Connection error " + e.getMessage());
					}
					try {
						this.finalize();
						super.finalize();
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	@PostConstruct
	public MailServer getMailServer() {
		//log.info("getMailServer");
		try {
			//mailServer = mailServerLogic.getMailServerByCode("MAIN_SERVER");
			if (mailServer != null) {
				// Consulta tabla parametros
				host = mailServer.getSmtpHost().trim();
				port = mailServer.getSmtpPort().toString().trim();
				from = mailServer.getSmtpUser().trim();
				password = mailServer.getSmtpPass().trim();
			}

		} catch (Exception e) {
			log.error("getMailServerByCode ERROR",e);
		}

		return mailServer;
	}

	public void setMailServer(MailServer mailServer) {
		this.mailServer = mailServer;
	}

	public StreamedContent getFileAttached() {
		return fileAttached;
	}

	public void setFileAttached(StreamedContent fileAttached) {
		this.fileAttached = fileAttached;
	}
	
	public String saveSystemLog(String action, String message) {
		//log.info("saveSystemLog");
		try {
		
			if (action != null && message != null) {
				SystemMailLog entity = new SystemMailLog();
				entity.setActionDate(new Date());
				entity.setActionName(action);
				entity.setNote(message);
				systemMailLogLogic.saveSystemMailLog(entity);
			} 

		} catch (Exception e) {
			log.error("saveSystemLog ERROR",e);
		}

		return "";
	}

}
