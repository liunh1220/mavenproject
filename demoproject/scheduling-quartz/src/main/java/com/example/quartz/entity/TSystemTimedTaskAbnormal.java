package com.example.quartz.entity;


import java.io.Serializable;
import java.util.Date;

public class TSystemTimedTaskAbnormal implements Serializable{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -7636227771542674116L;

    private String abnmId;

    private String jobId;

    private Date createTime;

    private String abnmContent;

    public String getAbnmId() {
        return abnmId;
    }

    public void setAbnmId(String abnmId) {
        this.abnmId = abnmId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAbnmContent() {
        return abnmContent;
    }

    public void setAbnmContent(String abnmContent) {
        this.abnmContent = abnmContent == null ? null : abnmContent.trim();
    }
}
