package com.ssm.entity.bo;

import java.io.File;
import java.util.Map;

public class MailTemplate {
	//发送目标,不能为空
	private String[] to;
	//抄送目标
	private String[] cc;
	
	//发送来源
	private String from;
	
	//主题
	private String subject;
	
	//FreeMarker模板文件,不能为空
	private String freeMarkerTemplate;
	
	//FreeMarker模板参数
	private Object object;
	
	//附件
	private Map<String,File> attachmentMap;
	
	// 是否保存发送记录到数据库
	private boolean isRecord = true;
	
	
	
	/**
     * 通知类别
     */
    private String type;
	
    /**
     * 是否实时
     */
    private String isRealtime = "Y";
    
    /**
     * 是否发送,'Y':已发送,'N':未发送
     */
    private String isSend;
    
	public Map<String, File> getAttachmentMap() {
		return attachmentMap;
	}

	public void setAttachmentMap(Map<String, File> attachmentMap) {
		this.attachmentMap = attachmentMap;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getFreeMarkerTemplate() {
		return freeMarkerTemplate;
	}

	public void setFreeMarkerTemplate(String freeMarkerTemplate) {
		this.freeMarkerTemplate = freeMarkerTemplate;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsRealtime() {
		return isRealtime;
	}

	public void setIsRealtime(String isRealtime) {
		this.isRealtime = isRealtime;
	}

	public String getIsSend() {
		return isSend;
	}

	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}
	
	public boolean isRecord() {
		return isRecord;
	}

	public void setRecord(boolean isRecord) {
		this.isRecord = isRecord;
	}

	public String[] getCc() {
		return cc;
	}

	public void setCc(String[] cc) {
		this.cc = cc;
	}
	
}
