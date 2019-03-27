package co.edu.usbcali.vas.ftp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import co.edu.usbcali.vas.model.control.ISystemParameterLogic;
import co.edu.usbcali.vas.utilities.Constantes;
import co.edu.usbcali.vas.utilities.FacesUtils;

@Scope("singleton")
@Service("FtpControllerLogic")
public class FtpController implements IFtpController {
	private static final Logger log = LoggerFactory.getLogger(FtpController.class);
	
	@Autowired
	private ISystemParameterLogic systemParameterLogic;
	

	private String ftp_server = "";
	private Integer ftp_port = 0;
	private String ftp_user = "";
	private String ftp_pass = "";
	
	@Override
	public void upload(String fileName,  InputStream inputStream) throws Exception {
		try {
			
			FTPClient ftpClient = new FTPClient();
			ftpClient.connect(ftp_server, ftp_port);
            ftpClient.login(ftp_user, ftp_pass);
            ftpClient.enterLocalPassiveMode();
 
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            // APPROACH #1: uploads first file using an InputStream
            //File firstLocalFile = new File("/home/gordon/Videos/Seguimiento.mp4");
 
            //String firstRemoteFile = "Seguimiento.mp4";
           // InputStream inputStream = new FileInputStream(firstLocalFile);
 
            log.info("Start uploading file");
            boolean done = ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            
            if (done) {
            	//user message analysing
            	log.info("The file is uploaded successfully.");
            	FacesUtils.addInfoMessage("Video Cargado correctamente al servidor");
            	
            }
			
		} catch (Exception e) {
			log.error("ftp send error",e);
		}
	}
	
	@Override
	public void download() throws Exception {
		try {
			
			FTPClient ftpClient = new FTPClient();
			
			ftpClient.connect(ftp_server, ftp_port);
            ftpClient.login(ftp_user, ftp_pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            // APPROACH #1: using retrieveFile(String, OutputStream)
            String remoteFile1 = "Linux_Config.docx";
            File downloadFile1 = new File("/home/gordon/Documents/ftp/Config.docx");
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
            boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
            outputStream1.close();
 
            if (success) {
                System.out.println("File #1 has been downloaded successfully.");
            }
			
		} catch (Exception e) {
			log.error("ftp send error",e);
		}
	}

	public String getFtp_server() {
		try {
			ftp_server = systemParameterLogic.getParamByCodeTexValue(Constantes.FTP_SERVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ftp_server;
	}

	public void setFtp_server(String ftp_server) {
		this.ftp_server = ftp_server;
	}

	public Integer getFtp_port() {
		try {
			ftp_port = systemParameterLogic.getParamByCodeNumValue(Constantes.FTP_SERVER_PORT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ftp_port;
	}

	public void setFtp_port(Integer ftp_port) {
		this.ftp_port = ftp_port;
	}

	public String getFtp_user() {
		
		try {
			ftp_user = systemParameterLogic.getParamByCodeTexValue(Constantes.FTP_SERVER_USER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ftp_user;
	}

	public void setFtp_user(String ftp_user) {
		this.ftp_user = ftp_user;
	}

	public String getFtp_pass() {
		try {
			ftp_pass = systemParameterLogic.getParamByCodeTexValue(Constantes.FTP_SERVER_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ftp_pass;
	}

	public void setFtp_pass(String ftp_pass) {
		this.ftp_pass = ftp_pass;
	}


}
