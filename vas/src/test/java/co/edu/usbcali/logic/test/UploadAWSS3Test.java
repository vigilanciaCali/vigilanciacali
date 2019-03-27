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
public class UploadAWSS3Test {
	private final static Logger log = Logger.getLogger("");
	
	String SFTP_HOST = "190.147.175.45";
    int SFTP_PORT = 22;
    String SFTP_USER = "guest";
    String SFTP_PASS = "guest";
    String SFTP_WORKING_DIR = "temp";

    Session session = null;
    Channel channel = null;
    ChannelSftp channelSftp = null;
	
   
    public void uploadToSftp() {
    	try {
    		
    
			
		} catch (Exception e) {
			 e.printStackTrace();
			
		}
    	
    }
    
    
    @Test
    public void AWSS3() {
    	//recieve();
    }
	

}
