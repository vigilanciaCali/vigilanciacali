package co.edu.usbcali.vas.mail;


public interface IEmailController {
	
	public void send(String to, String content, String subject)throws Exception;
	//public void sendWithAttach(String to, String subject, String content, ReportDTO report)throws Exception;

}
