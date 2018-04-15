package com.ssm.manager;

import java.io.File;
import java.util.Map;

import com.ssm.domain.Notification;
import com.ssm.entity.bo.MailTemplate;
import com.ssm.util.Page;

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
