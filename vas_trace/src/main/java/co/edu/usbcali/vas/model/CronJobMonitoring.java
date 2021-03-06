package co.edu.usbcali.vas.model;
// Generated Jun 10, 2018 8:14:55 PM by Hibernate Tools 5.1.0.Final


import java.util.Date;

/**
 * CronJobMonitoring generated by hbm2java
 */
public class CronJobMonitoring  implements java.io.Serializable {


     private Integer id;
     private String description;
     private String code;
     private String function;
     private String intervalType;
     private Integer intervalNumber;
     private String args;
     private Integer numbercall;
     private Date nextcall;
     private Integer priority;
     private String active;
     private Date createdAt;
     private Date updatedAt;
     private String createdBy;
     private String updatedBy;

    public CronJobMonitoring() {
    }

	
    public CronJobMonitoring(Integer id, String description, String code, Date createdAt, String createdBy) {
        this.id = id;
        this.description = description;
        this.code = code;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }
    public CronJobMonitoring(Integer id, String description, String code, String function, String intervalType, Integer intervalNumber, String args, Integer numbercall, Date nextcall, Integer priority, String active, Date createdAt, Date updatedAt, String createdBy, String updatedBy) {
       this.id = id;
       this.description = description;
       this.code = code;
       this.function = function;
       this.intervalType = intervalType;
       this.intervalNumber = intervalNumber;
       this.args = args;
       this.numbercall = numbercall;
       this.nextcall = nextcall;
       this.priority = priority;
       this.active = active;
       this.createdAt = createdAt;
       this.updatedAt = updatedAt;
       this.createdBy = createdBy;
       this.updatedBy = updatedBy;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    public String getFunction() {
        return this.function;
    }
    
    public void setFunction(String function) {
        this.function = function;
    }
    public String getIntervalType() {
        return this.intervalType;
    }
    
    public void setIntervalType(String intervalType) {
        this.intervalType = intervalType;
    }
    public Integer getIntervalNumber() {
        return this.intervalNumber;
    }
    
    public void setIntervalNumber(Integer intervalNumber) {
        this.intervalNumber = intervalNumber;
    }
    public String getArgs() {
        return this.args;
    }
    
    public void setArgs(String args) {
        this.args = args;
    }
    public Integer getNumbercall() {
        return this.numbercall;
    }
    
    public void setNumbercall(Integer numbercall) {
        this.numbercall = numbercall;
    }
    public Date getNextcall() {
        return this.nextcall;
    }
    
    public void setNextcall(Date nextcall) {
        this.nextcall = nextcall;
    }
    public Integer getPriority() {
        return this.priority;
    }
    
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    public String getActive() {
        return this.active;
    }
    
    public void setActive(String active) {
        this.active = active;
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


