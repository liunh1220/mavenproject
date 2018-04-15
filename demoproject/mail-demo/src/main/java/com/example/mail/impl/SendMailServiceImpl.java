package com.example.mail.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.example.mail.ISendMailService;
import com.example.mail.model.SendMailInfo;

public class SendMailServiceImpl implements ISendMailService
{
  @Autowired
  private JavaMailSender emailSender;

  @Autowired
  private FreeMarkerConfigurer freeMarkerConfigurer;

  public void sendEmail(final SendMailInfo mailInfo)
    throws Exception
  {
    MimeMessagePreparator preparator = new MimeMessagePreparator()
    {
      public void prepare(MimeMessage mimeMessage)
        throws Exception
      {
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, mailInfo.getEncoding());

        messageHelper.setSubject(mailInfo.getSubject());

        messageHelper.setFrom(mailInfo.getFrom(), mailInfo.getFromTitle());
        messageHelper.setText(mailInfo.getText());
        Template t=null;
        if (null != mailInfo.getVmTemplate())
        {
          Configuration configuration = SendMailServiceImpl.this.freeMarkerConfigurer.getConfiguration();
          t = configuration.getTemplate(mailInfo.getVmTemplate());
          messageHelper.setText(FreeMarkerTemplateUtils.processTemplateIntoString(t, mailInfo.getModel()), true);
        }

        if (null != mailInfo.getFiles())
        {
          for (String s : mailInfo.getFiles())
          {
            FileSystemResource file = new FileSystemResource(new File(s));

            if ((s.toLowerCase().endsWith(".jpg")) || (s.toLowerCase().endsWith(".jpeg")) || 
              (s.toLowerCase().endsWith(".png")) || (s.toLowerCase().endsWith(".gif")) || 
              (s.toLowerCase().endsWith(".bmp")))
            {
              messageHelper.addInline(MimeUtility.encodeWord(file.getFilename()), file);
            }
            else
            {
              messageHelper.addAttachment(MimeUtility.encodeWord(file.getFilename()), file);
            }
          }

        }

        messageHelper.setTo(mailInfo.getTo());
        if (null != mailInfo.getCc())
        {
          messageHelper.setCc(mailInfo.getCc());
        }
        if (null != mailInfo.getBcc())
        {
          messageHelper.setBcc(mailInfo.getBcc());
        }
      }
    };
    this.emailSender.send(preparator);
  }
}