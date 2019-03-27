package co.edu.usbcali.logic.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class UploadSFTPTest {
	private final static Logger log = Logger.getLogger("");
	
	String SFTP_HOST = "131.196.208.84";
	//String SFTP_HOST = "172.22.10.118";
    int SFTP_PORT = 22;
    String SFTP_USER = "javeriana";
    String SFTP_PASS = "admin";
    String SFTP_WORKING_DIR = "/home/ubuntu//";
   // String SFTP_WORKING_DIR = "/home/javeriana//Documents/VAS_Files/APP";

    Session session = null;
    Channel channel = null;
    ChannelSftp channelSftp = null;
	
   
    public void uploadToSftp() {
    	try {
    		
    		Long initTransferTime = System.currentTimeMillis();

    		JSch jsch = new JSch();
            session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
            session.setPassword(SFTP_PASS);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            
            channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;
            channelSftp.cd(SFTP_WORKING_DIR);
  
            File f = new File("/home/gordon/Downloads/qps.war");
            //File f = new File("/home/gordon/Downloads/vas.war");
            channelSftp.put(new FileInputStream(f), f.getName());
            
            channelSftp.exit();
            channel.disconnect();
            session.disconnect();
            log.info("Host Session disconnected.");
            
            Long finalTransferTime = System.currentTimeMillis() - initTransferTime;
            log.info("finalTransferTime: "+finalTransferTime.toString());

		} catch (Exception e) {
			 e.printStackTrace();
			 channelSftp.exit();
	         channel.disconnect();
	         session.disconnect();
	         log.info("Host Session disconnected.");
		}
    	
    }
    
 
	public void recieve() {

		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;

		try {

			JSch jsch = new JSch();
			session = jsch.getSession(SFTP_USER, SFTP_HOST, SFTP_PORT);
			session.setPassword(SFTP_PASS);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();

			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;
			channelSftp.cd(SFTP_WORKING_DIR);

			byte[] buffer = new byte[1024];
			BufferedInputStream bis = new BufferedInputStream(channelSftp.get("image.png"));
			File newFile = new File("/home/gordon/Pictures/image.png");
			OutputStream os = new FileOutputStream(newFile);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			int readCount;
			
			while ((readCount = bis.read(buffer)) > 0) {
				bos.write(buffer, 0, readCount);
			}
			bis.close();
			bos.close();

			channelSftp.exit();
			session.disconnect();

		} catch (Exception ex) {

			ex.printStackTrace();

		}
	}
    
    @Test
    public void SFTP() {
    	
    	
    	this.uploadToSftp();
    	
    	
    }
	

}
