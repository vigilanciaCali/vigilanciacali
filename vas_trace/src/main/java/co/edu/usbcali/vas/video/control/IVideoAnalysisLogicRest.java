package co.edu.usbcali.vas.video.control;

import java.io.InputStream;


public interface IVideoAnalysisLogicRest {


	public void analyze_trackerAlg(String contentType, String fileName, Long size, InputStream inputFile) throws Exception;

	public void analyze_anomalousAlg(String contentType, String fileName, Long size, InputStream inputFile) throws Exception;

	public void uploadVideoToFtp(String contentType, String fileName, Long size, InputStream inputFile) throws Exception;

	public String saveVideoToTempLocation(String contentType, String fileName, Long size, InputStream inputFile)
			throws Exception;

	public String getTemp_video_folder() throws Exception;

	public String getWeb_server_temp() throws Exception;

	public String analyze_anomalousAlgFromTempLocation(String videoFileTemp, String videoSize, String initTimeParam,
			String finalTimeParam) throws Exception;

	public String analyze_trackerAlgFromTempLocation(String videoFileTemp, String videoSize, String posXParam,
			String posYParam, String posX2Param, String posY2Param) throws Exception;

	public String getAlg_anl_output_server();

	public String getAlg_trc_output_server();
   
}
