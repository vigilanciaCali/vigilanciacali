package co.edu.usbcali.vas.model;
// Generated Jun 10, 2018 8:14:55 PM by Hibernate Tools 5.1.0.Final


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Users generated by hbm2java
 */
public class Users  implements java.io.Serializable {


     private Integer id;
     private UserType userType;
     private String firstname;
     private String lastname;
     private Boolean active;
     private Boolean deleted;
     private String login;
     private String password;
     private Boolean passwordSystemGen;
     private String sessionHash;
     private String token;
     private Date lastConnectionDate;
     private Boolean onSession;
     private String note;
     private Date createdAt;
     private Date updatedAt;
     private String createdBy;
     private String updatedBy;
     private Set<MessageBox> messageBoxes = new HashSet<MessageBox>(0);
     private Set<Video> videos = new HashSet<Video>(0);
     private Set<Document> documents = new HashSet<Document>(0);
     private Set<Ticket> tickets = new HashSet<Ticket>(0);

    public Users() {
    }

	
    public Users(Integer id, UserType userType, String firstname, Boolean active, String login, String password, Date createdAt, String createdBy) {
        this.id = id;
        this.userType = userType;
        this.firstname = firstname;
        this.active = active;
        this.login = login;
        this.password = password;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }
    public Users(Integer id, UserType userType, String firstname, String lastname, Boolean active, Boolean deleted, String login, String password, Boolean passwordSystemGen, String sessionHash, String token, Date lastConnectionDate, Boolean onSession, String note, Date createdAt, Date updatedAt, String createdBy, String updatedBy, Set<MessageBox> messageBoxes, Set<Video> videos, Set<Document> documents, Set<Ticket> tickets) {
       this.id = id;
       this.userType = userType;
       this.firstname = firstname;
       this.lastname = lastname;
       this.active = active;
       this.deleted = deleted;
       this.login = login;
       this.password = password;
       this.passwordSystemGen = passwordSystemGen;
       this.sessionHash = sessionHash;
       this.token = token;
       this.lastConnectionDate = lastConnectionDate;
       this.onSession = onSession;
       this.note = note;
       this.createdAt = createdAt;
       this.updatedAt = updatedAt;
       this.createdBy = createdBy;
       this.updatedBy = updatedBy;
       this.messageBoxes = messageBoxes;
       this.videos = videos;
       this.documents = documents;
       this.tickets = tickets;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public UserType getUserType() {
        return this.userType;
    }
    
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    public String getFirstname() {
        return this.firstname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return this.lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public Boolean getActive() {
        return this.active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
    public Boolean getDeleted() {
        return this.deleted;
    }
    
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
    public String getLogin() {
        return this.login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public Boolean getPasswordSystemGen() {
        return this.passwordSystemGen;
    }
    
    public void setPasswordSystemGen(Boolean passwordSystemGen) {
        this.passwordSystemGen = passwordSystemGen;
    }
    public String getSessionHash() {
        return this.sessionHash;
    }
    
    public void setSessionHash(String sessionHash) {
        this.sessionHash = sessionHash;
    }
    public String getToken() {
        return this.token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    public Date getLastConnectionDate() {
        return this.lastConnectionDate;
    }
    
    public void setLastConnectionDate(Date lastConnectionDate) {
        this.lastConnectionDate = lastConnectionDate;
    }
    public Boolean getOnSession() {
        return this.onSession;
    }
    
    public void setOnSession(Boolean onSession) {
        this.onSession = onSession;
    }
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
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
    public Set<MessageBox> getMessageBoxes() {
        return this.messageBoxes;
    }
    
    public void setMessageBoxes(Set<MessageBox> messageBoxes) {
        this.messageBoxes = messageBoxes;
    }
    public Set<Video> getVideos() {
        return this.videos;
    }
    
    public void setVideos(Set<Video> videos) {
        this.videos = videos;
    }
    public Set<Document> getDocuments() {
        return this.documents;
    }
    
    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }
    public Set<Ticket> getTickets() {
        return this.tickets;
    }
    
    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }




}


