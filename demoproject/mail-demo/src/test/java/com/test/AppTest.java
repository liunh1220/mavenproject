package com.test;

import org.junit.Test;

import com.example.mail.ISendMailService;
import com.example.mail.impl.SendMailServiceImpl;
import com.example.mail.model.SendMailInfo;

import junit.framework.TestCase;

public class AppTest extends TestCase
{
	@Test
    public void testApp() throws Exception
    {
		String[] toUrl = {""};
		SendMailInfo sendMailInfo = new SendMailInfo();
		sendMailInfo.setFrom("");
		sendMailInfo.setTo(toUrl);
		sendMailInfo.setSubject("test");
		sendMailInfo.setText("test ------------------------");
		
		ISendMailService sendMailService = new SendMailServiceImpl();
		sendMailService.sendEmail(sendMailInfo);
    }
}
