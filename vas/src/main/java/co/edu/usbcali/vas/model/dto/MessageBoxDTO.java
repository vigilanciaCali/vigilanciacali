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
public class MessageBoxDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(MessageBoxDTO.class);
    private Date createdAt;
    private String createdBy;
    private Long id;
    private String link;
    private String message;
    private String pictureLink;
    private String read;
    private String sent;
    private String subject;
    private Date updatedAt;
    private String updatedBy;
    private Integer id_Users;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getSent() {
        return sent;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    public Integer getId_Users() {
        return id_Users;
    }

    public void setId_Users(Integer id_Users) {
        this.id_Users = id_Users;
    }
}
