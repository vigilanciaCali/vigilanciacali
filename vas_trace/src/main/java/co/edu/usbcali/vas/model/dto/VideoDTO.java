package co.edu.usbcali.vas.model.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public class VideoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VideoDTO.class);
    private String analyticData;
    private Date createdAt;
    private String createdBy;
    private String description;
    private String format;
    private Long id;
    private String info;
    private String lenght;
    private String mimeType;
    private String src;
    private String type;
    private Long transferTime;
    private Long analysisTime;
    private Date updatedAt;
    private String updatedBy;
    private String url;
    private String videoData;
    private Integer id_Users;

    public String getAnalyticData() {
        return analyticData;
    }

    public void setAnalyticData(String analyticData) {
        this.analyticData = analyticData;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLenght() {
        return lenght;
    }

    public void setLenght(String lenght) {
        this.lenght = lenght;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVideoData() {
        return videoData;
    }

    public void setVideoData(String videoData) {
        this.videoData = videoData;
    }

    public Integer getId_Users() {
        return id_Users;
    }

    public void setId_Users(Integer id_Users) {
        this.id_Users = id_Users;
    }

	public Long getTransferTime() {
		return transferTime;
	}

	public void setTransferTime(Long transferTime) {
		this.transferTime = transferTime;
	}

	public Long getAnalysisTime() {
		return analysisTime;
	}

	public void setAnalysisTime(Long analysisTime) {
		this.analysisTime = analysisTime;
	}
}
