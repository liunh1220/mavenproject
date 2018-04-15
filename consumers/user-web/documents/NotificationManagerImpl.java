package com.ssm.manager.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.ssm.domain.Notification;
import com.ssm.entity.bo.MailTemplate;
import com.ssm.exception.BusinessException;
import com.ssm.manager.INotificationManager;
import com.ssm.service.INotificationBiz;
import com.ssm.util.Page;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class NotificationManagerImpl implements INotificationManager {

    private static final Logger logger = LoggerFactory.getLogger(NotificationManagerImpl.class);
    
    /*@Autowired
	private JavaMailSender mailSender;*/
    
    @Autowired
	private Configuration freemarkerConfiguration;
    
    @Autowired
    private INotificationBiz notificationBiz;
    
    private Template template;
    
    public List<Notification> list(Notification notification) {
        List<Notification> notifications = notificationBiz.list(notification);
        return notifications;
    }
    
    public Notification getById(String id)
    {
        Notification notification = null;
        try
        {
            notification = notificationBiz.getById(id);
        }
        catch(Exception e)
        {
            logger.error("NotificationManagerImpl.getById()", e);
            throw new BusinessException("NotificationManagerImpl.getById()", e);
        }
        return notification;
    }
    
    public void getByPage(Page page)
    {
        try
        {
            notificationBiz.getByPage(page);
        }
        catch(Exception e)
        {
            logger.error("NotificationManagerImpl.getByPage()", e);
            throw new BusinessException("NotificationManagerImpl.getByPage()", e);
        }
        
    }
    
    public void saveOrUpdate(Notification notification)
    {
        try
        {
            // 保存
            if(notification != null && StringUtils.isEmpty(notification.getId()))
            {
                notificationBiz.save(notification);
            }
            // 修改
            else
            {
                notificationBiz.update(notification);
            }
            
        }
        catch(Exception e)
        {
            logger.error("NotificationManagerImpl.saveOrUpdate()", e);
            throw new BusinessException("NotificationManagerImpl.saveOrUpdate()", e);
        }
    }
    
    public void remove(Notification notification)
    {
        try
        {
            notificationBiz.remove(notification);
        }
        catch(Exception e)
        {
            logger.error("NotificationManagerImpl.remove()", e);
            throw new BusinessException("NotificationManagerImpl.remove()", e);
        }
    }
    
    /**
	 * 发送MIME格式的用户修改通知邮件.
	 */
	public void sendMail(MailTemplate mailTemplate)
	{
		MimeMessage msg = null;//mailSender.createMimeMessage();
		
		try 
		{
			// 是否发送成功
			String isSend = "Y";
			// 邮件内容
			String content = "";
			
			// 实时发送
			if("Y".equals(mailTemplate.getIsRealtime()))
			{
				try
				{
					MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
					helper.setTo(mailTemplate.getTo());
					if(mailTemplate.getCc()!=null){
						helper.setCc(mailTemplate.getCc());
					}
					helper.setFrom(mailTemplate.getFrom(),mailTemplate.getFrom());
					helper.setSubject(mailTemplate.getSubject());
					helper.setValidateAddresses(false);
					template = freemarkerConfiguration.getTemplate(mailTemplate.getFreeMarkerTemplate(), "UTF-8");
					
					content = getContent(mailTemplate.getObject());
					
					helper.setText(content, true);
					
					//附近不为空时，发送附件
					if(mailTemplate.getAttachmentMap() != null && !mailTemplate.getAttachmentMap().isEmpty())
					{
						buildAttachment(helper,mailTemplate.getAttachmentMap());
					}

					//mailSender.send(msg);
					
					logger.info(mailTemplate.getSubject()+"发送邮件成功");
				}
				catch(Exception e)
				{
					logger.error(mailTemplate.getSubject()+"发送邮件失败",e);
					isSend = "N";
					throw new BusinessException(mailTemplate.getSubject()+"发送邮件失败",e);
				}
			}
			if(mailTemplate.isRecord())
			{
				Notification notification = new Notification();
				notification.setTitle(mailTemplate.getSubject());
				notification.setIsRealtime(mailTemplate.getIsRealtime());
				notification.setIsSend(isSend);
				notification.setSendTime(new Date());
				StringBuffer toEmail = new StringBuffer();
				for (String email:mailTemplate.getTo()) {
					toEmail.append(email);
				}
				notification.setToEmails(toEmail.toString());
				notification.setContent(content);
				notification.setType(mailTemplate.getType());// 邮件类型
				
				this.notificationBiz.save(notification);
				
				logger.info("保存发送邮件纪录成功");
			}
		} 
		catch(Exception e) 
		{
			logger.error(mailTemplate.getSubject()+"发送邮件失败", e);
			throw new BusinessException("发送邮件失败",e);
		}
	}
	
	/**
	 * 发送纯文本邮件,不保存发送纪录
	 * @param from 发送邮件地址
	 * @param subject 主题
	 * @param to 接收邮件地址数组
	 * @param content 发送内容
	 */
	public void sendSimpleMail(final String from,final String[] to,final String subject,final String content)
	{
		new Thread(new Runnable() {
			public void run() {
				try {
					SimpleMailMessage msg = new SimpleMailMessage();
					msg.setFrom(from);
					msg.setTo(to);
					msg.setSubject(subject);
					msg.setText(content);

					try {
						//mailSender.send(msg);
						for (String str : to) {
							logger.info("纯文本邮件已发送至" + str);
						}
						
					} catch (MailException e) {
						logger.error(subject+"发送邮件失败", e);
					}
					
				} catch (Exception e) {
					logger.error(subject+"发送邮件线程失败", e);
				}
			}
		}).start();
	}
	
	/**
	 * 发送纯文本邮件,不保存发送纪录
	 * @param from 发送邮件地址
	 * @param subject 主题
	 * @param to 接收邮件地址数组
	 * @param cc 抄送邮件地址组
	 * @param content 发送内容
	 */
	public void sendSimpleMail(final String from,final String[] to,final String[] cc,
			final String subject,final String content) {
		new Thread(new Runnable() {
			public void run() {
				try {
					SimpleMailMessage msg = new SimpleMailMessage();
					msg.setFrom(from);
					msg.setTo(to);
					msg.setCc(cc);
					msg.setSubject(subject);
					msg.setText(content);
					
					try {
						//mailSender.send(msg);
						for (String str : to) {
							logger.info("纯文本邮件已发送至" + str);
						}
						for(String str : cc){
							logger.info("纯文本邮件已抄送至" + str);
						}
						
					} catch (MailException e) {
						logger.error(subject+"发送邮件失败", e);
					}
					
				} catch (Exception e) {
					logger.error(subject+"发送邮件线程失败", e);
				}
			}
		}).start();
	}
	
	/**
	 * 发送纯文本邮件,不保存发送纪录
	 * @param from 发送邮件地址
	 * @param subject 主题
	 * @param to 接收邮件地址数组
	 * @param content 发送内容
	 * @param fileText 附件
	 */
	@Override
	public void sendSimpleMail(final String from,final String[] to,final String subject,final String content,final Map<String,File> attachmentMap)
	{
		new Thread(new Runnable() {
			public void run() {
				try {
					MimeMessage msg = null;//mailSender.createMimeMessage();					
					
					MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
					helper.setTo(to);
					helper.setFrom(from);
					helper.setSubject(subject);
					helper.setValidateAddresses(false);
					helper.setText(content, true);

					
					//附近不为空时，发送附件
					buildAttachment(helper,attachmentMap);
					try {
						//mailSender.send(msg);
						for (String str : to) {
							logger.info("纯文本加附件邮件已发送至" + str);
						}
						
					} catch (MailException e) {
						logger.error(subject+"发送邮件失败", e);
					}
				} catch (Exception e) {
					logger.error(subject+"发送邮件线程失败", e);
				}
			
			}
			}).start();
	}

	/**
	 * 使用Freemarker生成html格式内容.
	 */
	private String getContent(Object object) throws MessagingException 
	{
		try 
		{
			String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, object);
			
			return content;
		} 
		catch (IOException e) 
		{
			logger.error("构造邮件失败,FreeMarker模板不存在", e);
			throw new MessagingException("FreeMarker模板不存在", e);
		} 
		catch (TemplateException e) 
		{
			logger.error("构造邮件失败,FreeMarker处理失败", e);
			throw new MessagingException("FreeMarker处理失败", e);
		}
	}

	/**
	 * 添加附件.
	 */
	private void buildAttachment(MimeMessageHelper helper,Map<String,File> attachmentMap) throws MessagingException 
	{
		try 
		{
			for(Map.Entry<String,File> entry: attachmentMap.entrySet())
			{
				helper.addAttachment(entry.getKey(), attachmentMap.get(entry.getKey()));
			}
		} 
		catch (Exception e) 
		{
			logger.error("构造邮件失败,附件文件不存在", e);
			throw new MessagingException("附件文件不存在", e);
		}
	}
	
	
    
}
