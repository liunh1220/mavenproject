package com.example.springquartz.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务管理
 * @author Administrator
 *
 */
public class TSystemTimedTaskManage implements Serializable{
	   /**
	 * 
	 */
	private static final long serialVersionUID = 7257119581986288784L;

	/** 主键ID */
    private String jobId;

    /** 任务名称 */
    private String jobName;

    /** 执行类 */
    private String executionClazz;

    /** 执行方法 */
    private String executionMathod;

    /** 执行时间 */
    private String executionTime;

    /** 启用状态；'NONE','LOCKED','ERROR','COMPLETE','PAUSED','NORMAL' */
    private String enabledStatus;

    /** 任务最近运行开始时间 */
    private Date startTime;

    /** 任务最近运行结束时间 */
    private Date endTime;

    /** 创建时间 */
    private Date createTime;

    /** 扩展参数 */
    private String extendParam;

    /** 备注 */
    private String remark;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public String getExecutionClazz() {
        return executionClazz;
    }

    public void setExecutionClazz(String executionClazz) {
        this.executionClazz = executionClazz == null ? null : executionClazz.trim();
    }

    public String getExecutionMathod() {
        return executionMathod;
    }

    public void setExecutionMathod(String executionMathod) {
        this.executionMathod = executionMathod == null ? null : executionMathod.trim();
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime == null ? null : executionTime.trim();
    }

    public String getEnabledStatus() {
        return enabledStatus;
    }

    public void setEnabledStatus(String enabledStatus) {
        this.enabledStatus = enabledStatus == null ? null : enabledStatus.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getExtendParam() {
        return extendParam;
    }

    public void setExtendParam(String extendParam) {
        this.extendParam = extendParam == null ? null : extendParam.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}