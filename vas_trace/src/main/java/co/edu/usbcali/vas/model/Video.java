package co.edu.usbcali.vas.model;
// Generated Jun 10, 2018 8:14:55 PM by Hibernate Tools 5.1.0.Final


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Video generated by hbm2java
 */
public class Video  implements java.io.Serializable {


     private Long id;
     private Users users;
     private String mimeType;
     private String description;
     private String url;
     private String src;
     private String info;
     private String format;
     private String type;
     private String lenght;
     private String videoData;
     private String analyticData;
     private Long transferTime;
     private Long analysisTime;
     private Date createdAt;
     private Date updatedAt;
     private String createdBy;
     private String updatedBy;
     private Set<VideoDocument> videoDocuments = new HashSet<VideoDocument>(0);

    public Video() {
    }

	
    public Video(Long id, Users users, String mimeType, String description, Date createdAt, String createdBy) {
        this.id = id;
        this.users = users;
        this.mimeType = mimeType;
        this.description = description;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }
    public Video(Long id, Users users, String mimeType, String description, String url, String src, String info, String format, String type, String lenght, String videoData, String analyticData, Date createdAt, Date updatedAt, String createdBy, String updatedBy, Set<VideoDocument> videoDocuments) {
       this.id = id;
       this.users = users;
       this.mimeType = mimeType;
       this.description = description;
       this.url = url;
       this.src = src;
       this.info = info;
       this.format = format;
       this.type = type;
       this.lenght = lenght;
       this.videoData = videoData;
       this.analyticData = analyticData;
       this.createdAt = createdAt;
       this.updatedAt = updatedAt;
       this.createdBy = createdBy;
       this.updatedBy = updatedBy;
       this.videoDocuments = videoDocuments;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public Users getUsers() {
        return this.users;
    }
    
    public void setUsers(Users users) {
        this.users = users;
    }
    public String getMimeType() {
        return this.mimeType;
    }
    
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    public String getSrc() {
        return this.src;
    }
    
    public void setSrc(String src) {
        this.src = src;
    }
    public String getInfo() {
        return this.info;
    }
    
    public void setInfo(String info) {
        this.info = info;
    }
    public String getFormat() {
        return this.format;
    }
    
    public void setFormat(String format) {
        this.format = format;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public String getLenght() {
        return this.lenght;
    }
    
    public void setLenght(String lenght) {
        this.lenght = lenght;
    }
    public String getVideoData() {
        return this.videoData;
    }
    
    public void setVideoData(String videoData) {
        this.videoData = videoData;
    }
    public String getAnalyticData() {
        return this.analyticData;
    }
    
    public void setAnalyticData(String analyticData) {
        this.analyticData = analyticData;
    }
    public Date getCreatedAt() {
        return this.createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Date getUpdatedAt() {
        return this.updatedAt;
    }
    
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public String getUpdatedBy() {
        return this.updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    public Set<VideoDocument> getVideoDocuments() {
        return this.videoDocuments;
    }
    
    public void setVideoDocuments(Set<VideoDocument> videoDocuments) {
        this.videoDocuments = videoDocuments;
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


