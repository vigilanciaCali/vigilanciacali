package co.edu.usbcali.vas.video.control.rest;

public interface IVideoAnalysisLogicRest {


	public String analyze_anomalousAlgFromTempLocationREST(String videoFileTemp, String videoSize, String initTimeParam,
			String finalTimeParam, String info) throws Exception;

	public String analyze_trackerAlgFromTempLocationREST(String videoFileTemp, String videoSize, String posXParam,
			String posYParam, String posX2Param, String posY2Param) throws Exception;


   
}
