package co.edu.usbcali.vas.video.control;

public interface IMonitorLogic {

	public String processVideoWithAnomalousEventsAlg(String videoFileTemp, String videoId, String inputVideoFolder,
			String outputVideoFolder, String externalProgramLocation, String initTimeParam, String finalTimeParam)
			throws Exception;


	public String processVideoWithTrackerAlg(String videoFileTemp, String videoId, String inputVideoFolder,
			String outputVideoFolder, String externalProgramLocation, String posXParam, String posYParam,
			String posX2Param, String posY2Param) throws Exception;

	
   
}
