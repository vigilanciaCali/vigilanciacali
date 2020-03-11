package co.edu.usbcali.vas.model.dto;

import java.io.Serializable;

public class AlgorithmDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String videoId;
	private String videoFileTemp;
	private String videoFile;
	private String tempVideoFolder;
	private String outputVideoFolder;
	private String externalProgramLocation;
	private String initTimeParam;
	private String finalTimeParam;
	private String lenght;
	private String info;
	private String url;
	// TRACKER
	private String posXParam;
	private String posYParam;
	private String posX2Param;
	private String posY2Param;
	//USER
	private String userId;
	private String userEmail;
	//TRANSACTION
	private String videoTransactionId;
	private String message;
	private String errorCode;
	//JSON
	private String jsonString;

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

	public String getVideoId() {
		return videoId;
	}

	public String getLenght() {
		return lenght;
	}

	public String getInfo() {
		return info;
	}

	public String getUserId() {
		return userId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public void setLenght(String lenght) {
		this.lenght = lenght;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getVideoTransactionId() {
		return videoTransactionId;
	}

	public void setVideoTransactionId(String videoTransactionId) {
		this.videoTransactionId = videoTransactionId;
	}

	public String getMessage() {
		return message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}



}
