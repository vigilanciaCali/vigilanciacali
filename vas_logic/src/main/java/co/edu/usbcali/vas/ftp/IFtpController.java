package co.edu.usbcali.vas.ftp;

import java.io.InputStream;

public interface IFtpController {


	public void download() throws Exception;

	public void upload(String fileName, InputStream inputStream) throws Exception;
	
	
}
