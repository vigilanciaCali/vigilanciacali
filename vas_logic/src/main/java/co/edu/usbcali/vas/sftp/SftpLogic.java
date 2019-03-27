package co.edu.usbcali.vas.sftp;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import co.edu.usbcali.vas.model.control.ISystemParameterLogic;
import co.edu.usbcali.vas.utilities.Constantes;


@Scope("singleton")
@Service("SftpLogic")
public class SftpLogic implements ISftpLogic {
    private static final Logger log = LoggerFactory.getLogger(SftpLogic.class);

    @Autowired
   	private ISystemParameterLogic systemParameterLogic;
    
    private String SFTP_HOST = "";
    private String SFTP_PORT = "";
    private String SFTP_USER = "";
    private String SFTP_PASS = "";

    private Session session = null;
    private Channel channel = null;
    private ChannelSftp channelSftp = null;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void uploadToSftp(String temp_video_folder, InputStream inputFile, String fileName) throws Exception {
        log.info("SftpLogic uploadToSftp");



		try {

    		JSch jsch = new JSch();
            session = jsch.getSession(this.getSFTP_USER(), this.getSFTP_HOST(), Integer.valueOf(this.getSFTP_PORT()));
            session.setPassword(this.getSFTP_PASS());
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            
            channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;
            channelSftp.cd(temp_video_folder);
            channelSftp.put(inputFile, fileName);
            
            channelSftp.exit();
            channel.disconnect();
            session.disconnect();
            log.info("Host Session disconnected.");
   
			
		} catch (Exception e) {
			e.printStackTrace();
			channelSftp.exit();
	        channel.disconnect();
	        session.disconnect();
			log.error("SftpLogic uploadToSftp", e);
			throw e;
		}

    }

	public String getSFTP_HOST() {
		try {
			this.SFTP_HOST = systemParameterLogic.getParamByCodeTexValue(Constantes.SFTP_HOST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SFTP_HOST;
	}

	public void setSFTP_HOST(String sFTP_HOST) {
		SFTP_HOST = sFTP_HOST;
	}

	public String getSFTP_PORT() {
		try {
			this.SFTP_PORT = systemParameterLogic.getParamByCodeTexValue(Constantes.SFTP_PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SFTP_PORT;
	}

	public void setSFTP_PORT(String sFTP_PORT) {
		SFTP_PORT = sFTP_PORT;
	}

	public String getSFTP_USER() {
		try {
			this.SFTP_USER = systemParameterLogic.getParamByCodeTexValue(Constantes.SFTP_USER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SFTP_USER;
	}

	public void setSFTP_USER(String sFTP_USER) {
		SFTP_USER = sFTP_USER;
	}

	public String getSFTP_PASS() {
		try {
			this.SFTP_PASS = systemParameterLogic.getParamByCodeTexValue(Constantes.SFTP_PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SFTP_PASS;
	}

	public void setSFTP_PASS(String sFTP_PASS) {
		SFTP_PASS = sFTP_PASS;
	}
    





}
