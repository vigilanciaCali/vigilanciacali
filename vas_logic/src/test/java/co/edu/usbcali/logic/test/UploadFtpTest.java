package co.edu.usbcali.logic.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.usbcali.vas.ftp.IFtpController;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class UploadFtpTest {
	private final static Logger log = Logger.getLogger("");

	@Autowired
	private IFtpController ftpController;

	@Test
	public void ftpTest() throws Exception {

		//ftpController.upload();
		//ftpController.download();

	}

}
