package com.ssm.domain;

import java.io.Serializable;

import java.util.Date;

import com.ssm.base.BaseDomain;
import com.ssm.util.Page;

public class Job extends BaseDomain implements Serializable{
    
	private static final long serialVersionUID = -2054259349816535264L;

	/**
     * 调度ID
     */
    private String id;
    
    /**
     * 调度名称
     */
    private String name;
    
    /**
     * 调度组
     */
    private String groupName;
    
    /**
     * 调度时间配置
     */
    private String cronExpression;
    
    /**
     * 调度类全路径(包括包路径),且此类要实现org.quartz.Job接口
     */
    private String jobClass;
    
    private String jobParam;
    
    /**
     * 指定调度运行IP
     */
    private String runningIp;
    
    /**
     * 状态。RUNNING:运行中,STOP:停止,WAITTING:等待
     */
    private String status;
    
    private String statusDesc;
    
    /**
     * 调度级别,1:重要 2:普通 3:次要. 这个值可决定告警级别
     */
    private String grade;
    
    /**
     * 描述
     */
    private String description;
    
    /**
     * 创建人
     */
    private String createBy;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 更新人
     */
    private String updateBy;
    
    /**
     * 更新时间
     */
    private Date updateTime;
    
    /**
     * 是否启用
     */
    private String enabledFlag;
    
    private Page page;
    
    
    public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getJobParam() {
		return jobParam;
	}

	public void setJobParam(String jobParam) {
		this.jobParam = jobParam;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getId()
    {
        return id;
    }
        
    public void setId(String id)
    {
        this.id = id;
    }
        
    public String getName()
    {
        return name;
    }
        
    public void setName(String name)
    {
        this.name = name;
    }
        
    public String getGroupName()
    {
        return groupName;
    }
        
    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }
        
    public String getCronExpression()
    {
        return cronExpression;
    }
        
    public void setCronExpression(String cronExpression)
    {
        this.cronExpression = cronExpression;
    }
        
    public String getJobClass()
    {
        return jobClass;
    }
        
    public void setJobClass(String jobClass)
    {
        this.jobClass = jobClass;
    }
        
    public String getRunningIp()
    {
        return runningIp;
    }
        
    public void setRunningIp(String runningIp)
    {
        this.runningIp = runningIp;
    }
        
    public String getStatus()
    {
        return status;
    }
        
    public void setStatus(String status)
    {
        this.status = status;
    }
        
    public String getDescription()
    {
        return description;
    }
        
    public void setDescription(String description)
    {
        this.description = description;
    }
        
    public String getCreateBy()
    {
        return createBy;
    }
        
    public void setCreateBy(String createBy)
    {
        this.createBy = createBy;
    }
        
    public Date getCreateTime()
    {
        return createTime;
    }
        
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
        
    public String getUpdateBy()
    {
        return updateBy;
    }
        
    public void setUpdateBy(String updateBy)
    {
        this.updateBy = updateBy;
    }
        
    public Date getUpdateTime()
    {
        return updateTime;
    }
        
    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }
        
    public String getEnabledFlag()
    {
        return enabledFlag;
    }
        
    public void setEnabledFlag(String enabledFlag)
    {
        this.enabledFlag = enabledFlag;
    }

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
        
}
