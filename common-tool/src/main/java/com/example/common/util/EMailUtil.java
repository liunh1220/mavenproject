package com.example.common.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.common.bean.MailTemplate;
import com.example.common.manager.INotificationManager;

/**
 * 发送邮件工具类
 *
 */
public class EMailUtil {
	@Autowired
	private static ApplicationContextUtil appUtil;
	
	public static void sendMail(MailTemplate mailTemplate)
	{
		INotificationManager notificationManager = (INotificationManager)appUtil.getBean("notificationManagerImpl");
		notificationManager.sendMail(mailTemplate);
	}
	
}
