package com.example.mail.impl;

import java.util.List;

import com.example.mail.ISendMailService;
import com.example.mail.model.SendMailInfo;

public class SendMailThread extends Thread
{
  private List<SendMailInfo> sendList;
  private ISendMailService sendMailService;

  public SendMailThread()
  {
  }

  public SendMailThread(List<SendMailInfo> sendList, ISendMailService sendMailService)
  {
    this.sendList = sendList;
    this.sendMailService = sendMailService;
  }

  public void run()
  {
    try
    {
      for (SendMailInfo sendMailInfo : this.sendList)
      {
        this.sendMailService.sendEmail(sendMailInfo);
      }

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}