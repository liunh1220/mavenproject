package com.ssm.entity.bo;

import com.ssm.util.DateUtil;

public class JobLog {
	
	// 调度ID
	private String jobId;
	
	// 调度执行ID
	private String fireInstanceId;
	
	// 调度本次执行时间
	private String fireTime;
	
	// 调度下次执行时间
	private String nextFireTime;
	
	// 调度本次执行完成时间
	private String finishTime;
	
	// 运行状态
	private String status;
	
	// 花费
	private String cost;
	
	// 调度执行描述
	private String desc;

	public String getFireInstanceId() {
		return fireInstanceId;
	}

	public void setFireInstanceId(String fireInstanceId) {
		this.fireInstanceId = fireInstanceId;
	}
	
	public String getFireTime() {
		return fireTime;
	}

	public void setFireTime(String fireTime) {
		this.fireTime = fireTime;
	}

	public String getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(String nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStatus() {
		if(fireTime != null && finishTime == null)
		{
			status = "运行中";
		}
		if(fireTime != null && finishTime != null)
		{
			status = "成功";
		}
		if(desc != null)
		{
			status = "失败";
		}
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCost() {
		if(fireTime != null && finishTime != null)
		{
			cost = (double)(DateUtil.getOffsetBetweenTimes(finishTime, fireTime))/1000d+"s";
		}
		else
		{
			cost = "";
		}
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

}
