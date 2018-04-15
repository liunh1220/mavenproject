package com.example.mail;

import com.example.mail.model.SendMailInfo;

public abstract interface ISendMailService
{
  public abstract void sendEmail(SendMailInfo paramSendMailInfo)
    throws Exception;
}