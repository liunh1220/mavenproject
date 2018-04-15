package com.example.quartz.entity;


import java.io.Serializable;
import java.util.Date;

public class TSystemTimedTaskManage implements Serializable{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 7752647580594761302L;

    private String jobId;

    private String jobName;

    private String executionClazz;

    private String executionMathod;

    private String executionTime;

    private String enabledStatus;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private String extendParam;

    private String remark;
    
    private String chineseStatus;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
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

    public void setChineseStatus(String chineseStatus) {
        if("ENABLE".equals(chineseStatus)){
            this.chineseStatus="启用";
        }else{
            this.chineseStatus="禁用";
        }
    }
    
    public String getChineseStatus() {
        return chineseStatus;
    }
}
