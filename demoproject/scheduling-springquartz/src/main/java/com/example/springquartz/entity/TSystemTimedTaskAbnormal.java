package com.example.springquartz.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务异常
 * @author Administrator
 *
 */
public class TSystemTimedTaskAbnormal implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1358331712780860929L;

	/** 主键ID */
    private String abnmId;

    /** 定时任务ID */
    private String jobId;

    /** 创建时间 */
    private Date createTime;

    /** 异常内容 */
    private String abnmContent;

    public String getAbnmId() {
        return abnmId;
    }

    public void setAbnmId(String abnmId) {
        this.abnmId = abnmId == null ? null : abnmId.trim();
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
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