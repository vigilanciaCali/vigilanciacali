package co.edu.usbcali.vas.video.control.rest;

public interface IMonitorLogicRest {


	public void processVideoWithAnomalousAlgRest() throws Exception;

	public void processVideoWithTrackerAlgRest() throws Exception;

	public Boolean serverMonitorControllerStatus() throws Exception;

	public Boolean checkAnlAlgAvailable() throws Exception;

	public Boolean checkTrcAlgAvailable() throws Exception;

	public void waitForAnlAlgAvailable() throws Exception;

	public void waitForTrcAlgAvailable() throws Exception;


	
   
}
