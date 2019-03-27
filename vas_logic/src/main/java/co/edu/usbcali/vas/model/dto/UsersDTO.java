package co.edu.usbcali.vas.model.dto;

import java.io.Serializable;
import java.util.Date;


public class UsersDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean active;
    private Date createdAt;
    private String createdBy;
    private Boolean deleted;
    private String firstname;
    private Integer id;
    private Date lastConnectionDate;
    private String lastname;
    private String login;
    private String note;
    private Boolean onSession;
    private String password;
    private Boolean passwordSystemGen;
    private String sessionHash;
    private String token;
    private Date updatedAt;
    private String updatedBy;
    private Integer id_UserType;
  //new
    private String userType;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getLastConnectionDate() {
        return lastConnectionDate;
    }

    public void setLastConnectionDate(Date lastConnectionDate) {
        this.lastConnectionDate = lastConnectionDate;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getOnSession() {
        return onSession;
    }

    public void setOnSession(Boolean onSession) {
        this.onSession = onSession;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getPasswordSystemGen() {
        return passwordSystemGen;
    }

    public void setPasswordSystemGen(Boolean passwordSystemGen) {
        this.passwordSystemGen = passwordSystemGen;
    }

    public String getSessionHash() {
        return sessionHash;
    }

    public void setSessionHash(String sessionHash) {
        this.sessionHash = sessionHash;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public Integer getId_UserType() {
        return id_UserType;
    }

    public void setId_UserType(Integer id_UserType) {
        this.id_UserType = id_UserType;
    }

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
