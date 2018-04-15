package com.example.quartz.entity;


import java.io.Serializable;
import java.util.Date;

public class TSystemTimedTaskRunningTime implements Serializable{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -4075754332134260178L;

    private String runId;

    private String jobId;

    private String duration;

    private Date startTime;

    private Date endTime;

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration == null ? null : duration.trim();
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
}
