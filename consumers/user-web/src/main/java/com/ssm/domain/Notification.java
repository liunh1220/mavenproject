package com.ssm.domain;

import java.io.Serializable;

import java.util.Date;

import com.ssm.base.BaseDomain;

public class Notification extends BaseDomain implements Serializable{
    
	private static final long serialVersionUID = 1755570030373376627L;

	/**
     * 唯一标识
     */
    private String id;
    
    /**
     * 通知标题
     */
    private String title;
    
    /**
     * 通知类别
     */
    private String type;
    
    /**
     * 接收E-Mail地址列表,多个地址以逗号隔开
     */
    private String toEmails;
    
    /**
     * 通知内容
     */
    private String content;
    
    /**
     * 是否实时
     */
    private String isRealtime;
    
    /**
     * 是否发送,'Y':已发送,'N':未发送
     */
    private String isSend;
    
    /**
     * 发送时间
     */
    private Date sendTime;
    
    public String getId()
    {
        return id;
    }
        
    public void setId(String id)
    {
        this.id = id;
    }
        
    public String getTitle()
    {
        return title;
    }
        
    public void setTitle(String title)
    {
        this.title = title;
    }
        
    public String getType()
    {
        return type;
    }
        
    public void setType(String type)
    {
        this.type = type;
    }
        
    public String getToEmails()
    {
        return toEmails;
    }
        
    public void setToEmails(String toEmails)
    {
        this.toEmails = toEmails;
    }
        
    public String getContent()
    {
        return content;
    }
        
    public void setContent(String content)
    {
        this.content = content;
    }
        
    public String getIsRealtime()
    {
        return isRealtime;
    }
        
    public void setIsRealtime(String isRealtime)
    {
        this.isRealtime = isRealtime;
    }
        
    public String getIsSend()
    {
        return isSend;
    }
        
    public void setIsSend(String isSend)
    {
        this.isSend = isSend;
    }
        
    public Date getSendTime()
    {
        return sendTime;
    }
        
    public void setSendTime(Date sendTime)
    {
        this.sendTime = sendTime;
    }
        
}
