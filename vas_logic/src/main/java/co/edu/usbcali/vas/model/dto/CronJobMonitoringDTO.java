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
public class CronJobMonitoringDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CronJobMonitoringDTO.class);
    private String active;
    private String args;
    private String code;
    private Date createdAt;
    private String createdBy;
    private String description;
    private String function;
    private Integer id;
    private Integer intervalNumber;
    private String intervalType;
    private Date nextcall;
    private Integer numbercall;
    private Integer priority;
    private Date updatedAt;
    private String updatedBy;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIntervalNumber() {
        return intervalNumber;
    }

    public void setIntervalNumber(Integer intervalNumber) {
        this.intervalNumber = intervalNumber;
    }

    public String getIntervalType() {
        return intervalType;
    }

    public void setIntervalType(String intervalType) {
        this.intervalType = intervalType;
    }

    public Date getNextcall() {
        return nextcall;
    }

    public void setNextcall(Date nextcall) {
        this.nextcall = nextcall;
    }

    public Integer getNumbercall() {
        return numbercall;
    }

    public void setNumbercall(Integer numbercall) {
        this.numbercall = numbercall;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
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
}
