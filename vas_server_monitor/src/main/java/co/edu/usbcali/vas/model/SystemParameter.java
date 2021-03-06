package co.edu.usbcali.vas.model;
// Generated Jun 10, 2018 8:14:55 PM by Hibernate Tools 5.1.0.Final


import java.util.Date;

/**
 * SystemParameter generated by hbm2java
 */
public class SystemParameter  implements java.io.Serializable {


     private Integer id;
     private String name;
     private String code;
     private String textValue;
     private Integer intValue;
     private Long doubleValue;
     private Date dateValue;
     private Date timeValue;
     private Boolean booleanValue;
     private Date createdAt;
     private Date updatedAt;
     private String createdBy;
     private String updatedBy;

    public SystemParameter() {
    }

	
    public SystemParameter(Integer id, String code, Date createdAt, String createdBy) {
        this.id = id;
        this.code = code;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }
    public SystemParameter(Integer id, String name, String code, String textValue, Integer intValue, Long doubleValue, Date dateValue, Date timeValue, Boolean booleanValue, Date createdAt, Date updatedAt, String createdBy, String updatedBy) {
       this.id = id;
       this.name = name;
       this.code = code;
       this.textValue = textValue;
       this.intValue = intValue;
       this.doubleValue = doubleValue;
       this.dateValue = dateValue;
       this.timeValue = timeValue;
       this.booleanValue = booleanValue;
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
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    public String getTextValue() {
        return this.textValue;
    }
    
    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }
    public Integer getIntValue() {
        return this.intValue;
    }
    
    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }
    public Long getDoubleValue() {
        return this.doubleValue;
    }
    
    public void setDoubleValue(Long doubleValue) {
        this.doubleValue = doubleValue;
    }
    public Date getDateValue() {
        return this.dateValue;
    }
    
    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }
    public Date getTimeValue() {
        return this.timeValue;
    }
    
    public void setTimeValue(Date timeValue) {
        this.timeValue = timeValue;
    }
    public Boolean getBooleanValue() {
        return this.booleanValue;
    }
    
    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
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


