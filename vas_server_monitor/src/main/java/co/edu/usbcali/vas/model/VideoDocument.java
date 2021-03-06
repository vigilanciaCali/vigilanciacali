package co.edu.usbcali.vas.model;
// Generated Jun 10, 2018 8:14:55 PM by Hibernate Tools 5.1.0.Final


import java.util.Date;

/**
 * VideoDocument generated by hbm2java
 */
public class VideoDocument  implements java.io.Serializable {


     private Long id;
     private Video video;
     private byte[] source;
     private String mimeType;
     private String description;
     private Date createdAt;
     private Date updatedAt;
     private String createdBy;
     private String updatedBy;

    public VideoDocument() {
    }

	
    public VideoDocument(Long id, Video video, byte[] source, String mimeType, String description, Date createdAt, String createdBy) {
        this.id = id;
        this.video = video;
        this.source = source;
        this.mimeType = mimeType;
        this.description = description;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }
    public VideoDocument(Long id, Video video, byte[] source, String mimeType, String description, Date createdAt, Date updatedAt, String createdBy, String updatedBy) {
       this.id = id;
       this.video = video;
       this.source = source;
       this.mimeType = mimeType;
       this.description = description;
       this.createdAt = createdAt;
       this.updatedAt = updatedAt;
       this.createdBy = createdBy;
       this.updatedBy = updatedBy;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public Video getVideo() {
        return this.video;
    }
    
    public void setVideo(Video video) {
        this.video = video;
    }
    public byte[] getSource() {
        return this.source;
    }
    
    public void setSource(byte[] source) {
        this.source = source;
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




}


