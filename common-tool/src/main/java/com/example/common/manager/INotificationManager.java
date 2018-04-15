package com.example.common.manager;

import java.io.File;
import java.util.Map;

import com.example.common.bean.MailTemplate;
import com.example.common.domain.Notification;
import com.example.common.util.Page;

/**
 * @date 2011-11-02 09:49:20
 */
public interface INotificationManager {

    Notification getById(String id);
	
    void getByPage(Page page);
    
    void saveOrUpdate(Notification notification);
	
	void sendMail(MailTemplate mailTemplate);
	
    void remove(Notification notification);
    
    /**
	 * 发送纯文本邮件,不保存发送纪录
	 * @param from 发送邮件地址
	 * @param subject 主题
	 * @param to 接收邮件地址数组
	 * @param content 发送内容
	 */
	void sendSimpleMail(String from,String[] to,String subject,String content);

	void sendSimpleMail(String from, String[] to, String subject, String content, Map<String, File> attachmentMap);
	
	void sendSimpleMail(String from, String[] to, String[] cc, String subject, String content);
}
