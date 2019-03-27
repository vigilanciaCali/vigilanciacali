package co.edu.usbcali.vas.model.dto;

import java.io.Serializable;

public class AlgorithmDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String videoFileTemp;
	private String videoFile;
	private String tempVideoFolder;
	private String outputVideoFolder;
	private String externalProgramLocation;
	private String initTimeParam;
	private String finalTimeParam;
	// TRACKER
	private String posXParam;
	private String posYParam;
	private String posX2Param;
	private String posY2Param;

	public String getVideoFileTemp() {
		return videoFileTemp;
	}

	public void setVideoFileTemp(String videoFileTemp) {
		this.videoFileTemp = videoFileTemp;
	}

	public String getVideoFile() {
		return videoFile;
	}

	public void setVideoFile(String videoFile) {
		this.videoFile = videoFile;
	}

	public String getTempVideoFolder() {
		return tempVideoFolder;
	}

	public void setTempVideoFolder(String tempVideoFolder) {
		this.tempVideoFolder = tempVideoFolder;
	}

	public String getOutputVideoFolder() {
		return outputVideoFolder;
	}

	public void setOutputVideoFolder(String outputVideoFolder) {
		this.outputVideoFolder = outputVideoFolder;
	}

	public String getExternalProgramLocation() {
		return externalProgramLocation;
	}

	public void setExternalProgramLocation(String externalProgramLocation) {
		this.externalProgramLocation = externalProgramLocation;
	}

	public String getInitTimeParam() {
		return initTimeParam;
	}

	public void setInitTimeParam(String initTimeParam) {
		this.initTimeParam = initTimeParam;
	}

	public String getFinalTimeParam() {
		return finalTimeParam;
	}

	public void setFinalTimeParam(String finalTimeParam) {
		this.finalTimeParam = finalTimeParam;
	}

	public String getPosXParam() {
		return posXParam;
	}

	public void setPosXParam(String posXParam) {
		this.posXParam = posXParam;
	}

	public String getPosYParam() {
		return posYParam;
	}

	public void setPosYParam(String posYParam) {
		this.posYParam = posYParam;
	}

	public String getPosX2Param() {
		return posX2Param;
	}

	public void setPosX2Param(String posX2Param) {
		this.posX2Param = posX2Param;
	}

	public String getPosY2Param() {
		return posY2Param;
	}

	public void setPosY2Param(String posY2Param) {
		this.posY2Param = posY2Param;
	}

}
