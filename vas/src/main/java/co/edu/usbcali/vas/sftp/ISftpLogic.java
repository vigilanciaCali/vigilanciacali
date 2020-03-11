package co.edu.usbcali.vas.sftp;

import java.io.InputStream;

public interface ISftpLogic {

	public void uploadToSftp(String temp_video_folder, InputStream inputFile, String fileName) throws Exception;

	public Boolean isSftpAvailable() throws Exception;

	
	
   
}
